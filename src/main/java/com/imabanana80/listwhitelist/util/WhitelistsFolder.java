package com.imabanana80.listwhitelist.util;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.Objects;

public class WhitelistsFolder {
    public static void createWhitelistsFolder() {
        if (Bukkit.getPluginsFolder().listFiles() != null){
            boolean exists = false;
            for (File f : Objects.requireNonNull(Bukkit.getPluginsFolder().listFiles())) {
                if (f.getName().equals("whitelists" + File.separator)) {
                    exists = true;
                    break;
                }
            }
            if (!exists){
                new File(Bukkit.getPluginsFolder() + File.separator + "whitelists" + File.separator).mkdirs();
            }
        }
    }
}
