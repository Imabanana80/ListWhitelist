package com.imabanana80.listwhitelist;

import com.imabanana80.listwhitelist.commands.ListwhitelistCommand;
import com.imabanana80.listwhitelist.util.WhitelistsFolder;
import org.bukkit.plugin.java.JavaPlugin;

public final class ListWhitelist extends JavaPlugin {

    private static ListWhitelist plugin;

    @Override
    public void onEnable() {
        plugin = this;
        WhitelistsFolder.createWhitelistsFolder();
        getCommand("listwhitelist").setExecutor(new ListwhitelistCommand());
        getCommand("listwhitelist").setTabCompleter(new ListwhitelistCommand());
    }

    public static ListWhitelist getPlugin() {
        return plugin;
    }
}
