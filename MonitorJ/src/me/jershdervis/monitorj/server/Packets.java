package me.jershdervis.monitorj.server;

/**
 * Created by Josh on 23/06/2015.
 */
public enum Packets {

    PING(1),

    RESTART_CLIENT_APPLICATION(2),
    DISCONNECT_CLIENT(3),
    SHUTDOWN_CLIENT_APPLICATION(4),
    UNINSTALL_CLIENT_APPLICATION(5),

    RESTART_CLIENT_SYSTEM(6), //TODO
    SHUTDOWN_CLIENT_SYSTEM(7), //TODO

    REMOTE_DESKTOP_START(8),
    REMOTE_DESKTOP_STOP(9),
    REMOTE_DESKTOP_IMAGE(10),

    REMOTE_CHAT_START(11),
    REMOTE_CHAT_STOP(12),
    REMOTE_CHAT_MESSAGE(13),
    ;

    private int packet;

    Packets(int packet) {
        this.packet = packet;
    }

    public int getPacketID() {
        return this.packet;
    }
}
