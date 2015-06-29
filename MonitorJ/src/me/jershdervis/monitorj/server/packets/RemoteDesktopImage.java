package me.jershdervis.monitorj.server.packets;

import me.jershdervis.monitorj.server.BaseServerClient;
import me.jershdervis.monitorj.server.PacketTask;
import me.jershdervis.monitorj.server.Packets;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Josh on 22/06/2015.
 */
public class RemoteDesktopImage extends PacketTask {

    private JLabel displayLabel = null;

    public RemoteDesktopImage() {
        super(Packets.REMOTE_DESKTOP_IMAGE.getPacketID());
    }

    @Override
    public void run(BaseServerClient client) throws IOException {
        if(displayLabel == null)
            displayLabel = client.getRemoteDesktopFrame().jLabel1;

        DataInputStream dis = client.getDataInputStream();
        InputStream inImage = new ByteArrayInputStream(readBytes(dis));
        BufferedImage bi = ImageIO.read(inImage);

        Image image = bi.getScaledInstance(displayLabel.getWidth(), displayLabel.getHeight(), Image.SCALE_FAST);

        displayLabel.setIcon(new ImageIcon(image));
    }

    /**
     * Reads a full byte array length from DataInputStream
     * @param dis
     * @return
     * @throws IOException
     */
    private byte[] readBytes(DataInputStream dis) throws IOException{
        int len = dis.readInt();

        byte[] data = new byte[len];
        if (len > 0) {
            dis.readFully(data);
        }
        return data;
    }
}
