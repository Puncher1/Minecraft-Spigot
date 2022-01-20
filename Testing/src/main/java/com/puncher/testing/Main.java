package com.puncher.testing;

import com.puncher.testing.listeners.BlockListener;
import com.puncher.testing.listeners.JoinListener;
import com.puncher.testing.listeners.LeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onLoad(){
        instance = this;
    }

    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();

        // Listeners
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new LeaveListener(), this);
        manager.registerEvents(new BlockListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
