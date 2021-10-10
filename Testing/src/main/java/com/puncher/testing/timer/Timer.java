package com.puncher.testing.timer;

import com.puncher.testing.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {

    private boolean running;
    private int time;

    public Timer(boolean running, int time){
        this.running = running;
        this.time = time;

        run();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public void sendActionBar() {

        for (Player player: Bukkit.getOnlinePlayers()){

            if (!isRunning()){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED.toString() + "Timer pausiert."));

                continue;
            }
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + getTime()));

        }
        
    }

    private void run(){
        new BukkitRunnable(){
            @Override
            public void run(){
                sendActionBar();

                if (!isRunning()){
                    return;
                }
                setTime(getTime() + 1);

            }
        }.runTaskTimer(Main.getInstance(), 20, 20);

    }
}
