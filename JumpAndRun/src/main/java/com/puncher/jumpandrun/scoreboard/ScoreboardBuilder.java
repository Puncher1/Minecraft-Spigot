package com.puncher.jumpandrun.scoreboard;

import com.puncher.jumpandrun.JumpAndRun.JumpAndRunPlayer;
import com.puncher.jumpandrun.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class ScoreboardBuilder {

    public void setScoreboard(Player player, int jarHighscore_int)
    {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("Localhost", "dummy", "Localhost");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        String onlinePlayersTag = ChatColor.BLACK + "";
        String jarHighscooreTag = ChatColor.WHITE + "";

        Score blankLineTitle = obj.getScore(" ");
        Score onlineTitle = obj.getScore(ChatColor.WHITE + "Online:");
        Score blankLineOnlinePlayers = obj.getScore(" ");
        Score jarTitle = obj.getScore(ChatColor.WHITE + "Jump n' Run:");

        Team onlinePlayers = board.registerNewTeam("onlinePlayers");
        onlinePlayers.addEntry(onlinePlayersTag);
        onlinePlayers.setPrefix(ChatColor.GREEN + "" + Bukkit.getOnlinePlayers().size() + ChatColor.GRAY + "/" + Bukkit.getMaxPlayers());

        Team jarHighscore = board.registerNewTeam("jarHighscore");
        jarHighscore.addEntry(jarHighscooreTag);
        jarHighscore.setPrefix(ChatColor.GOLD + "" + jarHighscore_int + ChatColor.GRAY + "/20");

        blankLineTitle.setScore(6);
        onlineTitle.setScore(5);
        obj.getScore(onlinePlayersTag).setScore(4);
        blankLineOnlinePlayers.setScore(3);
        jarTitle.setScore(2);
        obj.getScore(jarHighscooreTag).setScore(1);

        player.setScoreboard(board);
    }

    public void runScoreboard() {
        new BukkitRunnable() {
            @Override
            public void run() {             // TODO: nicht

                String[] players = {"5headPuncher", "Puncher2"};
                int[] highscores = {10, 5};

                for (int i = 0; i < 2; i++)
                {
                    if (Bukkit.getPlayer(players[i]) == null)
                    {
                        continue;
                    }
                    setScoreboard(Bukkit.getPlayer(players[i]), highscores[i]);
                }
                
            }
        }.runTaskTimer(Main.getInstance(), 1, 1);
    }


}
