package com.puncher.testing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "HALLO");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
