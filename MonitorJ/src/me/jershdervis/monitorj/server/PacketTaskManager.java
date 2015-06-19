package me.jershdervis.monitorj.server;

import me.jershdervis.monitorj.eventapi.EventManager;
import me.jershdervis.monitorj.eventapi.EventTarget;
import me.jershdervis.monitorj.eventapi.events.EventReceivePacket;
import me.jershdervis.monitorj.server.packets.*;

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
    }

    @EventTarget
    public void onReceivePacket(EventReceivePacket event) {
        for(PacketTask task : packetTasks) {
            if(event.getPacketID() == task.getPacketID()) {
                try {
                    task.run(event.getClient());
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
