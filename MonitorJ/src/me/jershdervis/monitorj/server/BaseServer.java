package me.jershdervis.monitorj.server;

import me.jershdervis.monitorj.MonitorJ;
import me.jershdervis.monitorj.eventapi.EventTarget;
import me.jershdervis.monitorj.eventapi.events.EventClientConnect;
import me.jershdervis.monitorj.eventapi.events.EventClientDisconnect;

import javax.net.ServerSocketFactory;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Created by Josh on 18/06/2015.
 */
public class BaseServer implements Runnable {

    //Stores all Client Connection objects
    private ArrayList<BaseServerClient> clientList = ServerManager.instance.allClients;

    private final ServerSocket serverSocket;

    public BaseServer(int port) throws IOException {
        this.serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
    }

    @Override
    public void run() {
        try {
            while (!this.serverSocket.isClosed()) {
                new Thread(new BaseServerClient(this, this.serverSocket.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(this.serverSocket != null && !this.serverSocket.isClosed()) {
                try {
                    this.serverSocket.close();
                    System.out.println("Closed Socket Server");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @EventTarget
    public void onClientConnect(EventClientConnect event) {
        if(event.getClientServer() == this) {
            System.out.println("NEW CLIENT CONNECTION ON PORT: " + event.getClientServer().getServerSocket().getLocalPort());
            this.clientList.add(event.getClient());
            try {
                event.getClient().getDataOutputStream().writeByte(Packets.PING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @EventTarget
    public void onClientDisconnect(EventClientDisconnect event) {
        if(event.getClientServer() == this) {
            System.out.println("LOST CLIENT CONNECTION ON PORT: " + event.getClientServer().getServerSocket().getLocalPort());
            this.clientList.remove(event.getClient());
            ((DefaultTableModel) MonitorJ.getInstance().getUi().clientListTable.getModel()).removeRow(ServerManager.instance.getRowByClient(event.getClient()));
        }
    }

    /**
     * Gets ArrayList of BaseServerClient objects
     * @return
     */
    public ArrayList<BaseServerClient> getClientList() {
        return this.clientList;
    }

    /**
     * Gets the ServerSocket Object
     * @return
     */
    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }
}
