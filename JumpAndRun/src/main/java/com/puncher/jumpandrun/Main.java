package com.puncher.jumpandrun;

import com.puncher.jumpandrun.JumpAndRun.JumpAndRunGeneral;
import com.puncher.jumpandrun.listeners.PlayerListener;
import com.puncher.jumpandrun.scoreboard.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onLoad()
    {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new PlayerListener(), this);
        new JumpAndRunGeneral().runJarActionBar();
        new ScoreboardBuilder().runScoreboard();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance()
    {
        return instance;
    }
}
