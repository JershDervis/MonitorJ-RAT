package me.jershdervis.monitorj.stub.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Josh on 19/06/2015.
 */
public abstract class PacketTask {

    private final int packetID;

    public PacketTask(int packetID) {
        this.packetID = packetID;
    }

    public abstract void run(DataInputStream inputStream, DataOutputStream outputStream) throws IOException;

    public int getPacketID() {
        return this.packetID;
    }
}
