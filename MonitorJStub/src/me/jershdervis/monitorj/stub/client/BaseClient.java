package me.jershdervis.monitorj.stub.client;

import me.jershdervis.monitorj.stub.MonitorJStub;

import javax.net.SocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Josh on 18/06/2015.
 */
public class BaseClient implements Runnable {

    private final long RECONNECT_DELAY = 10000L;

    private Socket serverSocketConnection;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    private final String address;
    private final int port;

    public BaseClient(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        reconnect:
        while (true) {
            /**
             * Establish connection to server
             * If fails it will wait the delayed time and rerun through here
             */
            try {
                this.serverSocketConnection = this.connect(this.address, this.port);
                this.outputStream = new DataOutputStream(serverSocketConnection.getOutputStream());
                this.inputStream = new DataInputStream(serverSocketConnection.getInputStream());
                System.out.println("Connection Success!");
            } catch (IOException e) {
                e.printStackTrace();

                this.delayReconnection();
                continue reconnect;
            }

            /**
             * Connection has been established successfully
             * Call the Connect Event
             */
            MonitorJStub.getInstance().EVENT_CONNECT.call(this);

            /**
             * While the current socket isn't closed
             */
            while (!this.serverSocketConnection.isClosed()) {
                try {
                    int packet;
                    while ((packet = this.inputStream.readByte()) > -1) //Waits till input detected
                        MonitorJStub.getInstance().EVENT_RECEIVE_PACKET.call(packet, inputStream, outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /**
                 * Calls the disconnect event as well as starting the reconnection process.
                 */
                MonitorJStub.getInstance().EVENT_DISCONNECT.call(this);
                this.delayReconnection();
                continue reconnect;
            }

            this.delayReconnection();
            continue reconnect; //Retry connection
        }
    }

    /**
     * Used to delay client reconnection
     */
    private void delayReconnection() {
        try {
            Thread.sleep(this.RECONNECT_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create Socket connection. Returns the socket that was created.
     * @param address
     * @param port
     * @return
     * @throws IOException
     */
    private Socket connect(String address, int port) throws IOException {
        System.out.println("Attempting to connect to " + address + ":" + port);
        return SocketFactory.getDefault().createSocket(address, port);
    }

    /**
     * Gets the Socket Object the the current server
     * @return
     */
    public Socket getServerSocketConnection() {
        return this.serverSocketConnection;
    }

    /**
     * Gets the DataOutputStream Object of the current server
     * @return
     */
    public DataOutputStream getDataOutputStream() {
        return this.outputStream;
    }

    /**
     * Gets the DataInputStream Object of the current server
     * @return
     */
    public DataInputStream getDataInputStream() {
        return this.inputStream;
    }
}
