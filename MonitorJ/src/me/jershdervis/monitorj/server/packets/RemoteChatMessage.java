package me.jershdervis.monitorj.server.packets;

import me.jershdervis.monitorj.MonitorJ;
import me.jershdervis.monitorj.server.BaseServerClient;
import me.jershdervis.monitorj.server.PacketTask;
import me.jershdervis.monitorj.server.Packets;
import me.jershdervis.monitorj.ui.components.RemoteChatWindow;

import java.io.IOException;

/**
 * Created by Josh on 20/06/2015.
 */
public class RemoteChatMessage extends PacketTask {


    public RemoteChatMessage() {
        super(Packets.REMOTE_CHAT_MESSAGE);
    }

    @Override
    public void run(BaseServerClient client) throws IOException {
        String receivedMessage = client.getDataInputStream().readUTF();
        RemoteChatWindow chatWindow = MonitorJ.getInstance().getUi().getRemoteChatWindowForm();
        chatWindow.chatBoxModel.addElement(client.CLIENT_USER_NAME + ": " + receivedMessage);
        chatWindow.chatBoxList.ensureIndexIsVisible(chatWindow.chatBoxModel.getSize() - 1);
    }
}
