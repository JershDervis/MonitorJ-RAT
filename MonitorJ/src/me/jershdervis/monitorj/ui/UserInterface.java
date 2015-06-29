package me.jershdervis.monitorj.ui;

import me.jershdervis.monitorj.MonitorJ;
import me.jershdervis.monitorj.server.BaseServerClient;
import me.jershdervis.monitorj.server.Packets;
import me.jershdervis.monitorj.server.ServerManager;
import me.jershdervis.monitorj.ui.components.AddSocketForm;
import me.jershdervis.monitorj.ui.components.RemoteChatFrame;
import me.jershdervis.monitorj.ui.components.RemoteDesktopFrame;
import me.jershdervis.monitorj.util.ResourceLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Josh on 18/06/2015.
 */
public class UserInterface extends javax.swing.JFrame {

    private final AddSocketForm addSocketForm;

    /**
     * Creates new form UserInterface
     */
    public UserInterface() {
        initComponents();
        this.addSocketForm = new AddSocketForm(this);
    }

    private void initComponents() {

        clientOptionMenu = new javax.swing.JPopupMenu();
        socketOptionMenu = new javax.swing.JPopupMenu();
        addSocketMenuItem = new javax.swing.JMenuItem();
        removeSocketMenuItem = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        clientPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        clientListTable = new javax.swing.JTable();

        compilerPanel = new javax.swing.JPanel();
        pluginCenterPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pluginCenterTable = new javax.swing.JTable();
        socketManagerPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        socketTable = new javax.swing.JTable();
        addSocketButton = new javax.swing.JButton();
        removeSocketButton = new javax.swing.JButton();

        JMenuItem addSocket = new JMenuItem("Add Socket");
        socketOptionMenu.add(addSocket);
        JMenuItem removeSocket = new JMenuItem("Remove Socket");
        socketOptionMenu.add(removeSocket);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MonitorJ v0.1");

        clientListTable.setModel(new ClientTableModel());
        clientListTable.setDefaultRenderer(JLabel.class, new ClientTableCellRenderer());

        clientListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.isPopupTrigger())
                    showClientPopupMenu(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (evt.isPopupTrigger())
                    showClientPopupMenu(evt);
            }
        });
        jScrollPane1.setViewportView(clientListTable);

        javax.swing.GroupLayout clientPanelLayout = new javax.swing.GroupLayout(clientPanel);
        clientPanel.setLayout(clientPanelLayout);
        clientPanelLayout.setHorizontalGroup(
                clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        clientPanelLayout.setVerticalGroup(
                clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );
        jTabbedPane1.setFocusable(false); //No dotted selection box around tab
        jTabbedPane1.addTab("Client List", ResourceLoader.TAB_CLIENT_LIST, clientPanel);

        //Loads all right click on client options
        this.loadClientPopupMenu();

        javax.swing.GroupLayout compilerPanelLayout = new javax.swing.GroupLayout(compilerPanel);
        compilerPanel.setLayout(compilerPanelLayout);
        compilerPanelLayout.setHorizontalGroup(
                compilerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 695, Short.MAX_VALUE)
        );
        compilerPanelLayout.setVerticalGroup(
                compilerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 372, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Compiler", ResourceLoader.TAB_COMPILER, compilerPanel);

        pluginCenterTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Icon", "Name", "Description", "Authors", "Version"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane3.setViewportView(pluginCenterTable);

        javax.swing.GroupLayout pluginCenterPanelLayout = new javax.swing.GroupLayout(pluginCenterPanel);
        pluginCenterPanel.setLayout(pluginCenterPanelLayout);
        pluginCenterPanelLayout.setHorizontalGroup(
                pluginCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        pluginCenterPanelLayout.setVerticalGroup(
                pluginCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Plugin Center", ResourceLoader.TAB_PLUGIN_CENTER, pluginCenterPanel);

        socketTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Name", "Port", "Description"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        socketTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                socketTableMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                socketTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(socketTable);

        addSocketButton.setText("Add Socket");
        addSocketButton.setPreferredSize(new java.awt.Dimension(107, 23));
        addSocketButton.addActionListener(evt -> addSocketButtonActionPerformed(evt));

        removeSocketButton.setText("Remove Socket");
//        removeSocketButton.setEnabled(false);
        removeSocketButton.addActionListener(evt -> removeSocketButtonActionPerformed(evt));

        javax.swing.GroupLayout socketManagerPanelLayout = new javax.swing.GroupLayout(socketManagerPanel);
        socketManagerPanel.setLayout(socketManagerPanelLayout);
        socketManagerPanelLayout.setHorizontalGroup(
                socketManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                        .addGroup(socketManagerPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addSocketButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeSocketButton)
                                .addContainerGap())
        );
        socketManagerPanelLayout.setVerticalGroup(
                socketManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(socketManagerPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(socketManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addSocketButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(removeSocketButton))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Socket Manager", ResourceLoader.TAB_SOCKET_MANAGER, socketManagerPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
        );

        pack();
    }

    private void addSocketButtonActionPerformed(java.awt.event.ActionEvent evt) {
        addSocketForm.setAlwaysOnTop(true);
        addSocketForm.setLocationRelativeTo(null);
        addSocketForm.setVisible(true);
    }

    private void removeSocketButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (socketTable.getSelectedRow() != -1) {
            int port = (int) socketTable.getModel().getValueAt(socketTable.getSelectedRow(), 1);
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you'd like to stop listening on port: " + socketTable.getModel().getValueAt(socketTable.getSelectedRow(), 1), "Are you sure?", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                ServerManager.instance.closeServerOnPort(port);
                DefaultTableModel model = (DefaultTableModel) socketTable.getModel();
                model.removeRow(socketTable.getSelectedRow());
            }
        }
    }

    private void socketTableMousePressed(java.awt.event.MouseEvent evt) {
        int r = socketTable.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < socketTable.getRowCount())
            socketTable.setRowSelectionInterval(r, r);
        else
            socketTable.clearSelection();

        if (socketTable.getSelectedRow() < 0)
            return;
        if (evt.isPopupTrigger() && evt.getComponent() instanceof javax.swing.JTable)
            socketOptionMenu.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    private void socketTableMouseReleased(java.awt.event.MouseEvent evt) {
        if (evt.isPopupTrigger() && evt.getComponent() instanceof javax.swing.JTable)
            socketOptionMenu.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    private void showClientPopupMenu(MouseEvent e) {
        int r = clientListTable.rowAtPoint(e.getPoint());
        if (r >= 0 && r < clientListTable.getRowCount())
            clientListTable.setRowSelectionInterval(r, r);
        else
            clientListTable.clearSelection();

        if (clientListTable.getSelectedRow() < 0)
            return;

        clientOptionMenu.show(clientListTable, e.getX(), e.getY());
    }

    private void loadClientPopupMenu() {
        JMenu connectionSubMenu = new JMenu("Connection");
        connectionSubMenu.setIcon(ResourceLoader.CLIENT_CONNECTION_MENU);

        JMenuItem restartClientApp = new JMenuItem("Restart", ResourceLoader.CLIENT_CONNECTION_RESTART);
        restartClientApp.addActionListener(evt -> {
            BaseServerClient selectedClient = ServerManager.instance.getClientBySelectedRow();
            try {
                selectedClient.getDataOutputStream().writeByte(Packets.RESTART_CLIENT_APPLICATION.getPacketID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        JMenuItem disconnectClient = new JMenuItem("Disconnect", ResourceLoader.CLIENT_CONNECTION_DISCONNECT);
        disconnectClient.addActionListener(evt -> {
            BaseServerClient selectedClient = ServerManager.instance.getClientBySelectedRow();
            ((DefaultTableModel) MonitorJ.getInstance().getUi().clientListTable.getModel()).removeRow(ServerManager.instance.getRowByClient(selectedClient));
            try {
                selectedClient.getDataOutputStream().writeByte(Packets.DISCONNECT_CLIENT.getPacketID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        JMenuItem shutdownClientApp = new JMenuItem("Shutdown", ResourceLoader.CLIENT_CONNECTION_SHUTDOWN);
        shutdownClientApp.addActionListener(evt -> {
            BaseServerClient selectedClient = ServerManager.instance.getClientBySelectedRow();
            ((DefaultTableModel) MonitorJ.getInstance().getUi().clientListTable.getModel()).removeRow(ServerManager.instance.getRowByClient(selectedClient));
            try {
                selectedClient.getDataOutputStream().writeByte(Packets.SHUTDOWN_CLIENT_APPLICATION.getPacketID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        JMenuItem uninstallClientApp = new JMenuItem("Uninstall", ResourceLoader.CLIENT_CONNECTION_UNINSTALL);
        uninstallClientApp.addActionListener(evt -> {
            BaseServerClient selectedClient = ServerManager.instance.getClientBySelectedRow();
            ((DefaultTableModel) MonitorJ.getInstance().getUi().clientListTable.getModel()).removeRow(ServerManager.instance.getRowByClient(selectedClient));
            try {
                selectedClient.getDataOutputStream().writeByte(Packets.UNINSTALL_CLIENT_APPLICATION.getPacketID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JMenu systemSubMenu = new JMenu("System");
        systemSubMenu.setIcon(ResourceLoader.CLIENT_SYSTEM_MENU);

        JMenuItem restartClientSystem = new JMenuItem("Restart", ResourceLoader.CLIENT_CONNECTION_UNINSTALL);
        restartClientSystem.addActionListener(evt -> {
            BaseServerClient selectedClient = ServerManager.instance.getClientBySelectedRow();
            ((DefaultTableModel) MonitorJ.getInstance().getUi().clientListTable.getModel()).removeRow(ServerManager.instance.getRowByClient(selectedClient));
            try {
                selectedClient.getDataOutputStream().writeByte(Packets.UNINSTALL_CLIENT_APPLICATION.getPacketID());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        JMenu surveillanceSubMenu = new JMenu("Surveillance");
        surveillanceSubMenu.setIcon(ResourceLoader.CLIENT_SURVEILLANCE_MENU);

        JMenuItem remoteDesktop = new JMenuItem("Remote Desktop", ResourceLoader.CLIENT_SURVEILLANCE_REMOTE_DESKTOP);
        remoteDesktop.addActionListener(evt -> {
            BaseServerClient selectedClient = ServerManager.instance.getClientBySelectedRow();
            selectedClient.getRemoteDesktopFrame().setTitle("Remote Desktop with " + selectedClient.CLIENT_PC_NAME + ":" + selectedClient.CLIENT_USER_NAME);
            selectedClient.getRemoteDesktopFrame().setVisible(true);
        });

        JMenu toolsSubMenu = new JMenu("Tools");
        toolsSubMenu.setIcon(ResourceLoader.CLIENT_TOOLS_MENU);

        JMenuItem remoteChat = new JMenuItem("Remote Chat", ResourceLoader.CLIENT_TOOLS_REMOTE_CHAT);
        remoteChat.addActionListener(evt -> {
            BaseServerClient selectedClient = ServerManager.instance.getClientBySelectedRow();
            try {
                selectedClient.getDataOutputStream().writeByte(Packets.REMOTE_CHAT_START.getPacketID());
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectedClient.getRemoteDesktopFrame().setTitle("Remote Chat with " + selectedClient.CLIENT_PC_NAME + ":" + selectedClient.CLIENT_USER_NAME);
            selectedClient.getRemoteChatFrame().setVisible(true);
        });

        connectionSubMenu.add(restartClientApp);
        connectionSubMenu.add(disconnectClient);
        connectionSubMenu.add(shutdownClientApp);
        connectionSubMenu.add(uninstallClientApp);
        clientOptionMenu.add(connectionSubMenu);


        clientOptionMenu.add(systemSubMenu);

        surveillanceSubMenu.add(remoteDesktop);
        clientOptionMenu.add(surveillanceSubMenu);

        toolsSubMenu.add(remoteChat);
        clientOptionMenu.add(toolsSubMenu);
    }

    private javax.swing.JButton addSocketButton;
    private javax.swing.JMenuItem addSocketMenuItem;
    public javax.swing.JTable clientListTable;
    private javax.swing.JPopupMenu clientOptionMenu;
    private javax.swing.JPanel clientPanel;
    private javax.swing.JPanel compilerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pluginCenterPanel;
    public javax.swing.JTable pluginCenterTable;
    private javax.swing.JButton removeSocketButton;
    private javax.swing.JMenuItem removeSocketMenuItem;
    private javax.swing.JPanel socketManagerPanel;
    public javax.swing.JPopupMenu socketOptionMenu;
    public javax.swing.JTable socketTable;
}
