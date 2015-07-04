package me.jershdervis.monitorj.stub.client;

/**
 * Created by Josh on 19/06/2015.
 */
public enum Packets {

    PING(1),

    RESTART_CLIENT_APPLICATION(2),
    DISCONNECT_CLIENT(3),
    SHUTDOWN_CLIENT_APPLICATION(4),
    UNINSTALL_CLIENT_APPLICATION(5),

    SLEEP_CLIENT_SYSTEM(6),
    LOGOFF_CLIENT_SYSTEM(7),
    RESTART_CLIENT_SYSTEM(8),
    SHUTDOWN_CLIENT_SYSTEM(9),

    REMOTE_DESKTOP_START(10),
    REMOTE_DESKTOP_STOP(11),
    REMOTE_DESKTOP_IMAGE(12),

    REMOTE_CHAT_START(13),
    REMOTE_CHAT_STOP(14),
    REMOTE_CHAT_MESSAGE(15),
    ;

    private int packet;

    Packets(int packet) {
        this.packet = packet;
    }

    public int getPacketID() {
        return this.packet;
    }
}
