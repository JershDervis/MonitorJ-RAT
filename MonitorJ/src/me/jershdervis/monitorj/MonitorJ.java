package me.jershdervis.monitorj;

import me.jershdervis.monitorj.eventapi.events.EventClientConnect;
import me.jershdervis.monitorj.eventapi.events.EventClientDisconnect;
import me.jershdervis.monitorj.eventapi.events.EventReceivePacket;
import me.jershdervis.monitorj.server.PacketTaskManager;
import me.jershdervis.monitorj.server.ServerManager;
import me.jershdervis.monitorj.ui.UserInterface;
import me.jershdervis.monitorj.util.GeoIP;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Josh on 18/06/2015.
 */
public class MonitorJ {

    /**
     * Initialized within this classes constructor
     */
    private static MonitorJ instance;

    /**
     * Program Event initialization
     */
    public final EventClientConnect EVENT_CLIENT_CONNECT = new EventClientConnect();
    public final EventClientDisconnect EVENT_CLIENT_DISCONNECT = new EventClientDisconnect();
    public final EventReceivePacket EVENT_RECEIVE_PACKET = new EventReceivePacket();

    /**
     * Initialized within this classes constructor
     */
    private final PacketTaskManager packetTaskManager;
    private final ServerManager serverManager;
    private final UserInterface ui;
    private final GeoIP geoIP;

    /**
     * Initializes:
     * - MonitorJ instance
     * - PacketTaskManager packetTaskManager
     * - ServerManager serverManager
     * - UserInterface ui
     */
    public MonitorJ() {
        instance = this;

        this.packetTaskManager = new PacketTaskManager();
        this.serverManager = new ServerManager();
        this.geoIP = new GeoIP();

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

    /**
     * Gets the current classes instance
     * @return
     */
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

    /**
     * Gets the PacketTaskManager class
     * @return
     */
    public PacketTaskManager getPacketTaskManager() {
        return this.packetTaskManager;
    }

    /**
     * Returns the UserInterface class
     * @return
     */
    public UserInterface getUi() {
        return this.ui;
    }

    /**
     * Returns the GeoIP class
     * @return
     */
    public GeoIP getGeoIP() {
        return this.geoIP;
    }
}
