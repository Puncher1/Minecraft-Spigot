package com.puncher.jumpandrun.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.concurrent.ThreadLocalRandom;

public class PlayerListener implements Listener {

    private static boolean isJumpAndRunRunning = false;
    private static Location currentBlockLocation;
    private static Location nextBlockLocation;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        World world = player.getWorld();

        if (event.isCancelled())            // TODO: Checking if block has changed (otherwise ignore)
        {
            System.out.println("return");
            return;
        }

        Location playerLocation = player.getLocation();
        double player_x = playerLocation.getBlockX();
        double player_y = playerLocation.getBlockY();
        double player_z = playerLocation.getBlockZ();
        Block playerBlock = playerLocation.getBlock();

        if ((player_x == -253) && (player_y == 83) && (player_z == 310)
                && (playerBlock.getType() == Material.OAK_PRESSURE_PLATE))
        {
            currentBlockLocation = new Location(
                    world,
                    -253,
                    89,
                    310
            );
            nextBlockLocation = new Location(
                    world,
                    currentBlockLocation.getBlockX(),
                    currentBlockLocation.getBlockY() + 1,
                    currentBlockLocation.getBlockZ() + 3
            );

            Location newPlayerLocation = new Location(
                    world,
                    currentBlockLocation.getBlockX() + 0.5,
                    currentBlockLocation.getBlockY() + 1,
                    currentBlockLocation.getBlockZ() + 0.5
            );
            player.teleport(newPlayerLocation);

            currentBlockLocation.getBlock().setType(Material.LIGHT_BLUE_TERRACOTTA);
            nextBlockLocation.getBlock().setType(Material.CYAN_WOOL);

            playerLocation = player.getLocation();
            player_y = playerLocation.getBlockY();

            isJumpAndRunRunning = true;
        }

        if (isJumpAndRunRunning)
        {
            if (player_y < currentBlockLocation.getBlockY())
            {
                isJumpAndRunRunning = false;
                currentBlockLocation.getBlock().setType(Material.AIR);
                nextBlockLocation.getBlock().setType(Material.AIR);
                world.playSound(playerLocation, Sound.ENTITY_ITEM_BREAK, 30, 1);
            }

            if ((player_x == nextBlockLocation.getBlockX()) && (player_y == nextBlockLocation.getBlockY() + 1) &&
                    (player_z == nextBlockLocation.getBlockZ()))
            {
                currentBlockLocation.getBlock().setType(Material.AIR);
                nextBlockLocation.getBlock().setType(Material.LIGHT_BLUE_TERRACOTTA);

                currentBlockLocation = nextBlockLocation;


                int randomY = ThreadLocalRandom.current().nextInt(0, 1 + 1);
                int randomX = 0;
                int randomZ = 0;

                if (randomY == 0)
                {
                    randomX = ThreadLocalRandom.current().nextInt(0, 4 + 1);

                    if (randomX == 4)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                    }
                    else if (randomX == 3)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(0, 4 + 1);
                    }
                    else if (randomX == 2)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(0, 4 + 1);
                    }
                    else if (randomX == 1)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
                    }
                    else if (randomX == 0)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
                    }
                }

                else if (randomY == 1)
                {
                    randomX = ThreadLocalRandom.current().nextInt(0, 4 + 1);

                    if (randomX == 4)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(0, 1 + 1);
                    }
                    else if (randomX == 3)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                    }
                    else if (randomX == 2)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                    }
                    else if (randomX == 1)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
                    }
                    else if (randomX == 0)
                    {
                        randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
                    }
                }

                boolean randomMinusPlusX = ThreadLocalRandom.current().nextBoolean();
                boolean randomMinusPlusZ = ThreadLocalRandom.current().nextBoolean();

                if (randomMinusPlusX)
                {
                    randomX = randomX - (randomX * 2);
                }

                if (randomMinusPlusZ)
                {
                    randomZ = randomZ - (randomZ * 2);
                }

                nextBlockLocation = new Location(
                        world,
                        currentBlockLocation.getBlockX() + randomX,
                        currentBlockLocation.getBlockY() + randomY,
                        currentBlockLocation.getBlockZ() + randomZ
                );

                nextBlockLocation.getBlock().setType(Material.CYAN_WOOL);
                world.playSound(playerLocation, Sound.ENTITY_ITEM_PICKUP, 30, 1);

            }

        }




    }
}
