package me.jershdervis.monitorj;

import me.jershdervis.monitorj.eventapi.events.EventClientConnect;
import me.jershdervis.monitorj.eventapi.events.EventClientDisconnect;
import me.jershdervis.monitorj.eventapi.events.EventReceivePacket;
import me.jershdervis.monitorj.server.PacketTaskManager;
import me.jershdervis.monitorj.server.ServerManager;
import me.jershdervis.monitorj.ui.UserInterface;

import javax.swing.*;

/**
 * Created by Josh on 18/06/2015.
 */
public class MonitorJ {

    private static MonitorJ instance;

    public final EventClientConnect EVENT_CLIENT_CONNECT = new EventClientConnect();
    public final EventClientDisconnect EVENT_CLIENT_DISCONNECT = new EventClientDisconnect();
    public final EventReceivePacket EVENT_RECEIVE_PACKET = new EventReceivePacket();

    private final PacketTaskManager packetTaskManager;
    private final ServerManager serverManager;
    private final UserInterface ui;

    public MonitorJ() {
        instance = this;

        this.packetTaskManager = new PacketTaskManager();
        this.serverManager = new ServerManager();

        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //Create UserInterface:
        this.ui = new UserInterface();
        java.awt.EventQueue.invokeLater(() -> {
            ui.setLocationRelativeTo(null);
            ui.setVisible(true);
        });
        //
    }

    public static MonitorJ getInstance() {
        return instance;
    }

    /**
     * Gets the ServerManager class
     * @return
     */
    public ServerManager getServerManager() {
        return this.serverManager;
    }

    public UserInterface getUi() {
        return this.ui;
    }
}
