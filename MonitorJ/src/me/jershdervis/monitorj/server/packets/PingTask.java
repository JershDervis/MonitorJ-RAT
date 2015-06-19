package me.jershdervis.monitorj.server.packets;

import me.jershdervis.monitorj.MonitorJ;
import me.jershdervis.monitorj.server.BaseServerClient;
import me.jershdervis.monitorj.server.PacketTask;
import me.jershdervis.monitorj.server.Packets;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

/**
 * Created by Josh on 19/06/2015.
 */
public class PingTask extends PacketTask {

    public PingTask() {
        super(Packets.PING);
    }

    @Override
    public void run(BaseServerClient client) throws IOException {
        client.CLIENT_HWID = client.getDataInputStream().readUTF();
        client.CLIENT_PC_NAME = client.getDataInputStream().readUTF();
        client.CLIENT_USER_NAME = client.getDataInputStream().readUTF();
        client.CLIENT_OS = client.getDataInputStream().readUTF();
        client.CLIENT_IP = client.getClientSocket().getInetAddress().getHostAddress();
        client.CLIENT_PORT = client.getClientServerHost().getServerSocket().getLocalPort();

        Object[] row = new Object[]{
                client.CLIENT_HWID, client.CLIENT_PC_NAME, client.CLIENT_USER_NAME, client.CLIENT_OS, client.CLIENT_IP, client.CLIENT_PORT
        };
        ((DefaultTableModel) MonitorJ.getInstance().getUi().clientListTable.getModel()).addRow(row);
    }
}
