package me.jershdervis.monitorj.stub;

import me.jershdervis.monitorj.stub.client.BaseClient;
import me.jershdervis.monitorj.stub.client.PacketTaskManager;
import me.jershdervis.monitorj.stub.eventapi.events.EventConnect;
import me.jershdervis.monitorj.stub.eventapi.events.EventDisconnect;
import me.jershdervis.monitorj.stub.eventapi.events.EventReceivePacket;
import me.jershdervis.monitorj.stub.secure.StartupMonitor;

import java.io.IOException;

/**
 * Created by Josh on 18/06/2015.
 */
public class MonitorJStub {

    private static MonitorJStub instance;

    public final EventConnect EVENT_CONNECT = new EventConnect();
    public final EventDisconnect EVENT_DISCONNECT = new EventDisconnect();
    public final EventReceivePacket EVENT_RECEIVE_PACKET = new EventReceivePacket();

    private final String ip;
    private final int port;

    public MonitorJStub(String ip, int port) throws IOException {
        instance = this;
        this.ip = ip;
        this.port = port;

        //Initialize Packet Manager
        new PacketTaskManager();

        new Thread(new StartupMonitor("RegKeyNameHere")).start();

        new Thread(new BaseClient(ip, port)).start();
    }

    public static MonitorJStub getInstance() {
        return instance;
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }
}
