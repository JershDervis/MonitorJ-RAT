package me.jershdervis.monitorj.stub.client;

/**
 * Created by Josh on 19/06/2015.
 */
public class Packets {
    public static final int PING = 1;

    public static final int REMOTE_CHAT_START = 2; //When received display RemoteChat window
    public static final int REMOTE_CHAT_STOP = 3;  //When received hide the RemoteChat window
    public static final int REMOTE_CHAT_MESSAGE = 4; //Send this packet when the client sends a message.

    /**
     * Connection Packets
     */
    public static final int RESTART_CLIENT_APPLICATION = 5;
    public static final int DISCONNECT_CLIENT = 6;
    public static final int SHUTDOWN_CLIENT_APPLICATION = 7;
    public static final int UNINSTALL_CLIENT_APPLICATION = 8;
}
