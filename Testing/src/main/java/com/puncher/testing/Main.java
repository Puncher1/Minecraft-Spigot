package com.puncher.testing;

import com.puncher.testing.commands.TimerCommand;
import com.puncher.testing.listeners.JoinListener;
import com.puncher.testing.listeners.LeaveListener;
import com.puncher.testing.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private Timer timer;

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

        // Commands
        getCommand("timer").setExecutor(new TimerCommand());

        // Others
        timer = new Timer(false, 0);
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance(){
        return instance;
    }

    public Timer getTimer() {
        return timer;
    }
}
