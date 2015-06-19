package me.jershdervis.monitorj.stub.eventapi.events;

import me.jershdervis.monitorj.stub.eventapi.Event;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Created by Josh on 19/06/2015.
 */
public class EventReceivePacket extends Event {

    private int packetID;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Event call(int packetID, DataInputStream inputStream, DataOutputStream outputStream) {
        this.packetID = packetID;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        return super.call();
    }

    public int getPacketID() {
        return this.packetID;
    }

    public DataInputStream getInputStream() {
        return this.inputStream;
    }

    public DataOutputStream getOutputStream() {
        return this.outputStream;
    }
}
