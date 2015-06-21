package me.jershdervis.monitorj.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by Josh on 20/06/2015.
 */
public class ResourceLoader {

    public static final String IMAGES_LOCATION = "me/jershdervis/monitorj/resources/images/";

    public static ImageIcon CLIENT_CONNECT;
    public static ImageIcon CLIENT_DISCONNECT;

    public static ImageIcon CLIENT_CONNECTION_MENU;
    public static ImageIcon CLIENT_CONNECTION_RESTART;
    public static ImageIcon CLIENT_CONNECTION_DISCONNECT;
    public static ImageIcon CLIENT_CONNECTION_SHUTDOWN;
    public static ImageIcon CLIENT_CONNECTION_UNINSTALL;

    public static ImageIcon CLIENT_REMOTE_CHAT;

    static {
        try {
            CLIENT_CONNECT = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "connect.png")));
            CLIENT_DISCONNECT = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "disconnect.png")));

            //SUB-MENU ICON
            CLIENT_CONNECTION_MENU = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "connection.png")));
            CLIENT_CONNECTION_RESTART = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "connection_restart.png")));
            CLIENT_CONNECTION_DISCONNECT = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "connection_disconnect.png")));
            CLIENT_CONNECTION_SHUTDOWN = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "connection_shutdown.png")));
            CLIENT_CONNECTION_UNINSTALL = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "connection_uninstall.png")));

            CLIENT_REMOTE_CHAT = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "remotechat.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
