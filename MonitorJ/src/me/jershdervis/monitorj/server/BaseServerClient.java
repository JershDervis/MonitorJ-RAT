package me.jershdervis.monitorj.server;

import me.jershdervis.monitorj.MonitorJ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Josh on 18/06/2015.
 */
public class BaseServerClient implements Runnable {

    public String CLIENT_HWID;
    public String CLIENT_PC_NAME;
    public String CLIENT_USER_NAME;
    public String CLIENT_OS;
    public String CLIENT_IP;
    public int CLIENT_PORT;

    private final BaseServer host;
    private final Socket clientSocketConnection;
    private final DataOutputStream outputStream;
    private final DataInputStream inputStream;

    //Should i use both a Buffered Stream and Data Stream?
    public BaseServerClient(BaseServer host, Socket clientSocket) throws IOException {
        this.host = host;
        this.clientSocketConnection = clientSocket;
        this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
        this.inputStream = new DataInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        MonitorJ.getInstance().EVENT_CLIENT_CONNECT.call(host, this);

        while(!this.clientSocketConnection.isClosed()) { //While this socket to the client is not closed
            try {
                int packet;
                while ((packet = this.inputStream.readByte()) > -1) //Waits till packet received
                    MonitorJ.getInstance().EVENT_RECEIVE_PACKET.call(packet, this);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    this.clientSocketConnection.close();
                    this.host.getClientList().remove(this);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        MonitorJ.getInstance().EVENT_CLIENT_DISCONNECT.call(host, this);
    }

    /**
     * Return the ServerSocket the client is connected to
     * @return
     */
    public BaseServer getClientServerHost() {
        return this.host;
    }

    /**
     * Gets the Socket Object the the current client
     * @return
     */
    public Socket getClientSocket() {
        return this.clientSocketConnection;
    }

    /**
     * Gets the DataOutputStream Object of the current client
     * @return
     */
    public DataOutputStream getDataOutputStream() {
        return this.outputStream;
    }

    /**
     * Gets the DataInputStream Object of the current client
     * @return
     */
    public DataInputStream getDataInputStream() {
        return this.inputStream;
    }
}
