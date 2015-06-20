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

    static {
        try {
            CLIENT_CONNECT = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "connect.png")));
            CLIENT_DISCONNECT = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(IMAGES_LOCATION + "disconnect.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
