package com.imabanana80.listwhitelist.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListwhitelistCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            if (strings.length > 1) {
                if (strings[1] != null) {
                    BufferedReader reader = null;
                    try {
                        String path = Bukkit.getPluginsFolder().getPath() + File.separator + "whitelists" + File.separator + strings[1];
                        reader = new BufferedReader(new FileReader(path));
                    } catch (FileNotFoundException ignored) {}
                    if (reader != null) {
                        if (strings[0].equals("add")) {
                            for (String sp : reader.lines().toList()){
                                OfflinePlayer ap = Bukkit.getOfflinePlayer(sp);
                                if (ap != null){
                                    ap.setWhitelisted(true);
                                    p.sendMessage(Component.text("Added " + ap.getName() + " to the whitelist"));
                                } else {
                                    p.sendMessage(Component.text("\"" + sp + "\" does not exist").color(TextColor.color(0xFF5555)));
                                }
                            }
                        } else if (strings[0].equals("remove")) {
                            for (String sp : reader.lines().toList()){
                                OfflinePlayer ap = Bukkit.getOfflinePlayer(sp);
                                if (ap != null){
                                    ap.setWhitelisted(false);
                                    p.sendMessage(Component.text("Removed " + ap.getName() + " from the whitelist"));
                                } else {
                                    p.sendMessage(Component.text("\"" + sp + "\" does not exist").color(TextColor.color(0xFF5555)));
                                }
                            }
                        }
                    } else if (strings[1].equals("all")) {
                        for (OfflinePlayer ap : Bukkit.getServer().getWhitelistedPlayers()) {
                            ap.setWhitelisted(false);
                            p.sendMessage(Component.text("Removed " + ap.getName() + " from the whitelist"));
                        }
                    } else {
                        p.sendMessage(Component.text("File does not exist").color(TextColor.color(0xFF5555)));
                    }

                }

            } else {
                p.sendMessage(Component.text("Unknown or incomplete command").color(TextColor.color(0xFF5555)));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1){
            return Arrays.asList("add", "remove");
        } else if (strings.length == 2){
            File whitelistsfolder = new File(Bukkit.getPluginsFolder().getPath() + File.separator + "whitelists" + File.separator);
            List<String> fileList = new ArrayList<>();
            for (File file : whitelistsfolder.listFiles()){
                fileList.add(file.getName());
            }
            if (strings[0].equals("remove")) {
                fileList.add("all");
            }
            return fileList;
        } else {
            return Arrays.asList("");
        }
    }
}
