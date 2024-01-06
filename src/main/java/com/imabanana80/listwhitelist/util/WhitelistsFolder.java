package com.imabanana80.listwhitelist.util;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.Objects;

public class WhitelistsFolder {
    public static void createWhitelistsFolder() {
        if (Bukkit.getPluginsFolder().listFiles() != null){
            Boolean exists = false;
            for (File f : Objects.requireNonNull(Bukkit.getPluginsFolder().listFiles())) {
                if (f.getName().equals("whitelists\\")){
                    exists = true;
                }
            }
            if (!exists){
                new File(Bukkit.getPluginsFolder() + "\\whitelists\\").mkdirs();
            }
        }
    }
}
