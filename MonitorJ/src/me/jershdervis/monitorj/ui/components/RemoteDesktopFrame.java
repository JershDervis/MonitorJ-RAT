package me.jershdervis.monitorj.ui.components;

import me.jershdervis.monitorj.eventapi.EventManager;
import me.jershdervis.monitorj.eventapi.EventTarget;
import me.jershdervis.monitorj.eventapi.events.EventClientDisconnect;
import me.jershdervis.monitorj.server.BaseServer;
import me.jershdervis.monitorj.server.BaseServerClient;
import me.jershdervis.monitorj.server.Packets;
import me.jershdervis.monitorj.util.ResourceLoader;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by Josh on 22/06/2015.
 */
public class RemoteDesktopFrame extends javax.swing.JFrame {

    private final BaseServerClient client;

    private javax.swing.JButton playBtn;
    private javax.swing.JButton stopBtn;
    private javax.swing.JLabel statsLabel;
    public javax.swing.JLabel jLabel1;

    public RemoteDesktopFrame(BaseServerClient client) {
        EventManager.register(this);
        this.client = client;
        initComponents();
        setIconImage(ResourceLoader.CLIENT_SURVEILLANCE_REMOTE_DESKTOP.getImage());
        setLocationRelativeTo(null);
    }


    @EventTarget
    public void onClientDisconnect(EventClientDisconnect event) {
        if(event.getClient() == client)
            this.setVisible(false);
    }

    private void initComponents() {
        playBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        statsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                playBtn.setEnabled(true);
                stopBtn.setEnabled(false);
                try {
                    client.getDataOutputStream().writeByte(Packets.REMOTE_DESKTOP_STOP.getPacketID());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        playBtn.setIcon(ResourceLoader.BUTTON_PLAY);
        playBtn.addActionListener(evt -> {
            System.out.println("Requesting desktop stream");
            stopBtn.setEnabled(true);
            playBtn.setEnabled(false);
            try {
                this.client.getDataOutputStream().writeByte(Packets.REMOTE_DESKTOP_START.getPacketID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stopBtn.setIcon(ResourceLoader.BUTTON_PAUSE);
        stopBtn.setEnabled(false);
        stopBtn.addActionListener(evt -> {
            playBtn.setEnabled(true);
            stopBtn.setEnabled(false);
            try {
                this.client.getDataOutputStream().writeByte(Packets.REMOTE_DESKTOP_STOP.getPacketID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        statsLabel.setText("Could put some stats here..");

        javax.swing.GroupLayout newImagePanelLayout = new javax.swing.GroupLayout(jLabel1);
        jLabel1.setLayout(newImagePanelLayout);
        newImagePanelLayout.setHorizontalGroup(
                newImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        newImagePanelLayout.setVerticalGroup(
                newImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 264, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(statsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                                .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(statsLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
        );

        pack();
    }
}
