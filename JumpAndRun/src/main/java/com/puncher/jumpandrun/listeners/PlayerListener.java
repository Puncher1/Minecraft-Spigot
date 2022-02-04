package com.puncher.jumpandrun.listeners;

import com.puncher.jumpandrun.JumpAndRun.JumpAndRunGeneral;
import com.puncher.jumpandrun.JumpAndRun.JumpAndRunPlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.*;

public class PlayerListener implements Listener {

    JumpAndRunGeneral generalJar = new JumpAndRunGeneral();
    private final int countMax = 20;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        // TODO: Highscore
        Player currentPlayer = event.getPlayer();
        JumpAndRunPlayer jarPlayer = null;
        boolean isPlayerPlaying = false;

        for (JumpAndRunPlayer jarPlayer_temp: generalJar.getJumpAndRunPlayers())
        {
            if (jarPlayer_temp.getPlayer() == currentPlayer)
            {
                jarPlayer = jarPlayer_temp;
                isPlayerPlaying = true;
                break;
            }
        }

        double player_x = currentPlayer.getLocation().getBlockX();
        double player_y = currentPlayer.getLocation().getBlockY();
        double player_z = currentPlayer.getLocation().getBlockZ();
        Block playerBlock = currentPlayer.getLocation().getBlock();
        Location playerLocation = currentPlayer.getLocation();
        World world = currentPlayer.getWorld();

        if (isPlayerPlaying)
        {
            if (jarPlayer.getHasWon())
            {
                if (jarPlayer.getPlayer().getLocation().getBlockY() < jarPlayer.getCurrentBlockLocation().getBlockY())
                {
                    Location currLoc = jarPlayer.getCurrentBlockLocation();
                    int currX = currLoc.getBlockX();
                    int currY = currLoc.getBlockY();
                    int currZ = currLoc.getBlockZ();
                    Location[] winningPlatform = {
                            new Location(world, currX + 1, currY, currZ),
                            new Location(world, currX + 2, currY, currZ),
                            new Location(world, currX + 3, currY, currZ),
                            new Location(world, currX - 1, currY, currZ),
                            new Location(world, currX - 2, currY, currZ),
                            new Location(world, currX - 3, currY, currZ),
                            new Location(world, currX, currY, currZ + 1),
                            new Location(world, currX, currY, currZ + 2),
                            new Location(world, currX, currY, currZ + 3),
                            new Location(world, currX, currY, currZ - 1),
                            new Location(world, currX, currY, currZ - 2),
                            new Location(world, currX, currY, currZ - 3),
                            new Location(world, currX + 1, currY, currZ + 1),
                            new Location(world, currX + 1, currY, currZ - 1),
                            new Location(world, currX - 1, currY, currZ + 1),
                            new Location(world, currX - 1, currY, currZ - 1),
                            new Location(world, currX + 1, currY, currZ + 2),
                            new Location(world, currX + 1, currY, currZ - 2),
                            new Location(world, currX - 1, currY, currZ + 2),
                            new Location(world, currX - 1, currY, currZ - 2),
                            new Location(world, currX + 2, currY, currZ + 1),
                            new Location(world, currX + 2, currY, currZ - 1),
                            new Location(world, currX - 2, currY, currZ + 1),
                            new Location(world, currX - 2, currY, currZ - 1)
                    };

                    for (Location blockLocation: winningPlatform)
                    {
                        blockLocation.getBlock().setType(Material.AIR);
                    }
                    jarPlayer.getCurrentBlockLocation().getBlock().setType(Material.AIR);
                    jarPlayer.setHasWon(false);
                    generalJar.removeJarPlayer(jarPlayer);
                }
            }
            else
            {
                if (player_y < jarPlayer.getCurrentBlockLocation().getBlockY())
                {
                    generalJar.removeJarPlayer(jarPlayer);
                    jarPlayer.getCurrentBlockLocation().getBlock().setType(Material.AIR);
                    jarPlayer.getNextBlockLocation().getBlock().setType(Material.AIR);
                    world.playSound(playerLocation, Sound.ENTITY_ITEM_BREAK, 30, 1);
                    jarPlayer.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("")); // to remove count instantly
                }
                else if (playerLocation.getBlockX() == jarPlayer.getNextBlockLocation().getBlockX() &&
                        playerLocation.getBlockY() - 1 == jarPlayer.getNextBlockLocation().getBlockY() &&
                        playerLocation.getBlockZ() == jarPlayer.getNextBlockLocation().getBlockZ())
                {
                    if (jarPlayer.getJumpCount() + 1 >= 20)
                    {
                        jarPlayer.getNextBlockLocation().getBlock().setType(Material.CHISELED_QUARTZ_BLOCK);
                        jarPlayer.getCurrentBlockLocation().getBlock().setType(Material.AIR);
                        jarPlayer.setCurrentBlockLocation(jarPlayer.getNextBlockLocation());

                        Location currLoc = jarPlayer.getNextBlockLocation();
                        int currX = currLoc.getBlockX();
                        int currY = currLoc.getBlockY();
                        int currZ = currLoc.getBlockZ();
                        Location[] winningPlatform = {
                                new Location(world, currX + 1, currY, currZ),
                                new Location(world, currX + 2, currY, currZ),
                                new Location(world, currX + 3, currY, currZ),
                                new Location(world, currX - 1, currY, currZ),
                                new Location(world, currX - 2, currY, currZ),
                                new Location(world, currX - 3, currY, currZ),
                                new Location(world, currX, currY, currZ + 1),
                                new Location(world, currX, currY, currZ + 2),
                                new Location(world, currX, currY, currZ + 3),
                                new Location(world, currX, currY, currZ - 1),
                                new Location(world, currX, currY, currZ - 2),
                                new Location(world, currX, currY, currZ - 3),
                                new Location(world, currX + 1, currY, currZ + 1),
                                new Location(world, currX + 1, currY, currZ - 1),
                                new Location(world, currX - 1, currY, currZ + 1),
                                new Location(world, currX - 1, currY, currZ - 1),
                                new Location(world, currX + 1, currY, currZ + 2),
                                new Location(world, currX + 1, currY, currZ - 2),
                                new Location(world, currX - 1, currY, currZ + 2),
                                new Location(world, currX - 1, currY, currZ - 2),
                                new Location(world, currX + 2, currY, currZ + 1),
                                new Location(world, currX + 2, currY, currZ - 1),
                                new Location(world, currX - 2, currY, currZ + 1),
                                new Location(world, currX - 2, currY, currZ - 1)
                        };

                        for (Location blockLocation: winningPlatform)
                        {
                            blockLocation.getBlock().setType(Material.WHITE_STAINED_GLASS);
                        }
                        jarPlayer.setMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Gewonnen!");
                        jarPlayer.setHasWon(true);
                    }

                    else
                    {
                        jarPlayer.getCurrentBlockLocation().getBlock().setType(Material.AIR);
                        jarPlayer.getNextBlockLocation().getBlock().setType(jarPlayer.getBlockMaterial().get("Terracotta"));

                        jarPlayer.setCurrentBlockLocation(jarPlayer.getNextBlockLocation());

                        jarPlayer.setNextBlockLocation(generalJar.randomNextLocation(world, jarPlayer.getCurrentBlockLocation()));
                        jarPlayer.getNextBlockLocation().getBlock().setType(jarPlayer.getBlockMaterial().get("Wool"));
                        world.playSound(playerLocation, Sound.ENTITY_ITEM_PICKUP, 30, 1);
                        jarPlayer.incJumpCount();
                    }

                    if ((countMax - jarPlayer.getJumpCount()) <= 10 && !jarPlayer.getHasWon())
                    {
                        int jumpsRemaining = countMax - jarPlayer.getJumpCount();
                        jarPlayer.setMessage(ChatColor.YELLOW + "Count: " + ChatColor.GREEN + jarPlayer.getJumpCount() +
                                ChatColor.GRAY + " | " + ChatColor.GOLD + "Remaining: " + ChatColor.GREEN + jumpsRemaining);
                    }
                    else
                    {
                        if (!jarPlayer.getHasWon())
                        {
                            jarPlayer.setMessage(ChatColor.YELLOW + "Count: " +ChatColor.GREEN + jarPlayer.getJumpCount());
                        }
                    }

                }
            }

        }
//        else if (jarPlayer.getHasWon())
//        {
//            if (jarPlayer.getPlayer().getLocation().getBlockY() < jarPlayer.getCurrentBlockLocation().getBlockY())
//            {
//                Location currLoc = jarPlayer.getNextBlockLocation();
//                int currX = currLoc.getBlockX();
//                int currY = currLoc.getBlockY();
//                int currZ = currLoc.getBlockZ();
//                Location[] winningPlatform = {
//                        new Location(world, currX + 1, currY, currZ),
//                        new Location(world, currX + 2, currY, currZ),
//                        new Location(world, currX + 3, currY, currZ),
//                        new Location(world, currX - 1, currY, currZ),
//                        new Location(world, currX - 2, currY, currZ),
//                        new Location(world, currX - 3, currY, currZ),
//                        new Location(world, currX, currY, currZ + 1),
//                        new Location(world, currX, currY, currZ + 2),
//                        new Location(world, currX, currY, currZ + 3),
//                        new Location(world, currX, currY, currZ - 1),
//                        new Location(world, currX, currY, currZ - 2),
//                        new Location(world, currX, currY, currZ - 3),
//                        new Location(world, currX + 1, currY, currZ + 1),
//                        new Location(world, currX + 1, currY, currZ - 1),
//                        new Location(world, currX - 1, currY, currZ + 1),
//                        new Location(world, currX - 1, currY, currZ - 1),
//                        new Location(world, currX + 1, currY, currZ + 2),
//                        new Location(world, currX + 1, currY, currZ - 2),
//                        new Location(world, currX - 1, currY, currZ + 2),
//                        new Location(world, currX - 1, currY, currZ - 2),
//                        new Location(world, currX + 2, currY, currZ + 1),
//                        new Location(world, currX + 2, currY, currZ - 1),
//                        new Location(world, currX - 2, currY, currZ + 1),
//                        new Location(world, currX - 2, currY, currZ - 1)
//                };
//
//                for (Location blockLocation: winningPlatform)
//                {
//                    blockLocation.getBlock().setType(Material.AIR);
//                }
//                jarPlayer.getCurrentBlockLocation().getBlock().setType(Material.AIR);
//
//            }
//
//            jarPlayer.setHasWon(false);
//        }
        else
        {
            if ((player_x == -253) && (player_y == 83) && (player_z == 310)
                && (playerBlock.getType() == Material.OAK_PRESSURE_PLATE))
            {
                Location triggerLocation = new Location(world, (double)-253, (double)83, (double)310);
                JumpAndRunPlayer newJarPlayer = new JumpAndRunPlayer(currentPlayer);
                newJarPlayer.setCurrentBlockLocation(generalJar.randomStartLocation(world, triggerLocation, 5, 10, 10));
                newJarPlayer.setNextBlockLocation(generalJar.randomNextLocation(world, newJarPlayer.getCurrentBlockLocation()));

                Map<String, Material> blockMaterial = newJarPlayer.getBlockMaterial();
                newJarPlayer.getCurrentBlockLocation().getBlock().setType(blockMaterial.get("Terracotta"));
                newJarPlayer.getNextBlockLocation().getBlock().setType(blockMaterial.get("Wool"));

                Location currentBlockLocation = newJarPlayer.getCurrentBlockLocation();
                Location teleportLocation = new Location(world, currentBlockLocation.getX() + 0.5, currentBlockLocation.getY() + 1, currentBlockLocation.getZ() + 0.5);

                currentPlayer.teleport(teleportLocation);
                generalJar.addJarPlayer(newJarPlayer);

                world.playSound(playerLocation, Sound.ENTITY_ITEM_PICKUP, 30, 1);
                newJarPlayer.setMessage(ChatColor.YELLOW + "Count: " +ChatColor.GREEN + newJarPlayer.getJumpCount());
            }
        }
    }
}
