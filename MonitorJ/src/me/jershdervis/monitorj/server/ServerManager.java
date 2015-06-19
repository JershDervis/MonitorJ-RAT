package me.jershdervis.monitorj.server;

import me.jershdervis.monitorj.MonitorJ;
import me.jershdervis.monitorj.eventapi.EventManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Josh on 18/06/2015.
 */
public class ServerManager {

    public static ServerManager instance;

    public ArrayList<BaseServer> servers = new ArrayList<BaseServer>();
    public ArrayList<BaseServerClient> allClients = new ArrayList<BaseServerClient>();

    public ServerManager() {
        if(instance == null)
            instance = this;
    }

    public void listenOnPort(int port) throws IOException {
        BaseServer toAdd = new BaseServer(port);
        EventManager.register(toAdd);
        this.servers.add(toAdd);
        new Thread(toAdd).start();
    }

    public void closeServerOnPort(int port) {
        BaseServer closingServer = this.getBaseServerByPort(port);
        if(closingServer != null && !closingServer.getServerSocket().isClosed()) {
            try {
                //Disconnect all clients, so the client knows the server has been closed.
                for(BaseServerClient client : closingServer.getClientList()) {
                    client.getClientSocket().close();
                }
                closingServer.getServerSocket().close();
                this.servers.remove(closingServer);
                EventManager.unregister(closingServer);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Unable to close server.");
            }
        }
    }

    public BaseServer getBaseServerByPort(int port) {
        for(BaseServer s : servers) {
            if(s.getServerSocket().getLocalPort() == port)
                return s;
        }
        return null;
    }

    public int getRowByClient(BaseServerClient client) {
        JTable table = MonitorJ.getInstance().getUi().clientListTable;
        for(int curRow = 0; curRow < table.getModel().getRowCount(); curRow++) {
            if(table.getModel().getValueAt(curRow, 0) == client.CLIENT_HWID) {
                return curRow;
            }
        }
        return -1;
    }

    public BaseServerClient getClientByRow(int row) {
        JTable table = MonitorJ.getInstance().getUi().clientListTable;
        for(BaseServerClient client : ServerManager.instance.allClients) {
            if(table.getModel().getValueAt(row, 0) == client.CLIENT_HWID) {
                return client;
            }
        }
        return null;
    }
}
