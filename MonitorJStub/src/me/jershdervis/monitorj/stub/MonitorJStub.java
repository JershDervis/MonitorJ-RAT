package me.jershdervis.monitorj.stub;

import me.jershdervis.monitorj.stub.client.BaseClient;
import me.jershdervis.monitorj.stub.client.PacketTaskManager;
import me.jershdervis.monitorj.stub.eventapi.events.EventConnect;
import me.jershdervis.monitorj.stub.eventapi.events.EventDisconnect;
import me.jershdervis.monitorj.stub.eventapi.events.EventReceivePacket;
import me.jershdervis.monitorj.stub.secure.StartupMonitor;
import me.jershdervis.monitorj.stub.ui.RemoteChatWindow;

import java.io.IOException;

/**
 * Created by Josh on 18/06/2015.
 */
public class MonitorJStub {

    private static MonitorJStub instance;

    private final BaseClient clientServerConnection;

    //Interrupt when uninstalling
    private final Thread regPersist;

    public final EventConnect EVENT_CONNECT = new EventConnect();
    public final EventDisconnect EVENT_DISCONNECT = new EventDisconnect();
    public final EventReceivePacket EVENT_RECEIVE_PACKET = new EventReceivePacket();

    private final RemoteChatWindow chatWindow;

    private final String regKey;

    public MonitorJStub(String ip, int port, String key) throws IOException {
        instance = this;

        this.regKey = key;

        this.chatWindow = new RemoteChatWindow();

        //Initialize Packet Manager
        new PacketTaskManager();

        this.regPersist = new Thread(new StartupMonitor(key));
        this.regPersist.start();

        new Thread(clientServerConnection = new BaseClient(ip, port)).start();
    }

    public static MonitorJStub getInstance() {
        return instance;
    }

    public String getRegKey() {
        return this.regKey;
    }

    public Thread getRegPersistThread() {
        return this.regPersist;
    }

    public RemoteChatWindow getChatWindow() {
        return this.chatWindow;
    }

    public BaseClient getClientServerConnection() {
        return this.clientServerConnection;
    }
}
