package com.puncher.jumpandrun.listeners;

import com.puncher.jumpandrun.JumpAndRun.JumpAndRun;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerListener implements Listener {

    private ArrayList<JumpAndRun> JumpAndRunPlayers = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player currentPlayer = event.getPlayer();
        boolean isPlayerPlaying = false;

        for (JumpAndRun jarPlayer: JumpAndRunPlayers)
        {
            System.out.println(jarPlayer.);
            if (jarPlayer == currentPlayer)
            {
                isPlayerPlaying = true;
                break;
            }
        }

        if (isPlayerPlaying)
        {
            System.out.println("Player is Playing");
        }
        else
        {
            double player_x = currentPlayer.getLocation().getBlockX();
            double player_y = currentPlayer.getLocation().getBlockY();
            double player_z = currentPlayer.getLocation().getBlockZ();
            Block playerBlock = currentPlayer.getLocation().getBlock();
            Location playerLocation = currentPlayer.getLocation();
            World world = currentPlayer.getWorld();

            if ((player_x == -253) && (player_y == 83) && (player_z == 310)
                && (playerBlock.getType() == Material.OAK_PRESSURE_PLATE))
            {
                Location triggerLocation = new Location(world, (double)-253, (double)83, (double)310);
                JumpAndRun jarPlayer = new JumpAndRun(currentPlayer);
                jarPlayer.setCurrentBlockLocation(randomStartLocation(world, triggerLocation, 5, 10, 10));
                jarPlayer.setNextBlockLocation(randomNextLocation(world, jarPlayer.getCurrentBlockLocation()));

                Map<String, Material> blockMaterial = jarPlayer.getBlockMaterial();
                jarPlayer.getCurrentBlockLocation().getBlock().setType(blockMaterial.get("Terracotta"));
                jarPlayer.getNextBlockLocation().getBlock().setType(blockMaterial.get("Wool"));

                currentPlayer.teleport(jarPlayer.getCurrentBlockLocation().add((double)0.5, (double)1, (double)0.5));
                world.playSound(playerLocation, Sound.ENTITY_ITEM_PICKUP, 30, 1);

                JumpAndRunPlayers.add(jarPlayer);
            }
        }

//        Player player = event.getPlayer();
//        World world = player.getWorld();
//        // TODO: Class for every JumpAndRund competitor.
//        // TODO: Checking if block has changed (otherwise ignore)
//        // TODO: Actionbar Counte (ev. Highscore?)
//        if (event.isCancelled())
//        {
//            System.out.println("return");
//            return;
//        }
//
//        Location playerLocation = player.getLocation();
//        double player_x = playerLocation.getBlockX();
//        double player_y = playerLocation.getBlockY();
//        double player_z = playerLocation.getBlockZ();
//        Block playerBlock = playerLocation.getBlock();
//
//        if ((player_x == -253) && (player_y == 83) && (player_z == 310)
//                && (playerBlock.getType() == Material.OAK_PRESSURE_PLATE))
//        {
//            world.playSound(playerLocation, Sound.ENTITY_ITEM_PICKUP, 30, 1);
//
//            currentBlockLocation = new Location(
//                    world,
//                    -253,
//                    89,
//                    310
//            );
//            nextBlockLocation = new Location(
//                    world,
//                    currentBlockLocation.getBlockX(),
//                    currentBlockLocation.getBlockY() + 1,
//                    currentBlockLocation.getBlockZ() + 3
//            );
//
//            Location newPlayerLocation = new Location(
//                    world,
//                    currentBlockLocation.getBlockX() + 0.5,
//                    currentBlockLocation.getBlockY() + 1,
//                    currentBlockLocation.getBlockZ() + 0.5
//            );
//            player.teleport(newPlayerLocation);
//
//            currentBlockLocation.getBlock().setType(Material.LIGHT_BLUE_TERRACOTTA);
//            nextBlockLocation.getBlock().setType(Material.CYAN_WOOL);
//
//            playerLocation = player.getLocation();
//            player_y = playerLocation.getBlockY();
//
//            isJumpAndRunRunning = true;
//        }
//
//        if (isJumpAndRunRunning)
//        {
//            if (player_y < currentBlockLocation.getBlockY())
//            {
//                isJumpAndRunRunning = false;
//                currentBlockLocation.getBlock().setType(Material.AIR);
//                nextBlockLocation.getBlock().setType(Material.AIR);
//                world.playSound(playerLocation, Sound.ENTITY_ITEM_BREAK, 30, 1);
//            }
//
//            if ((player_x == nextBlockLocation.getBlockX()) && (player_y == nextBlockLocation.getBlockY() + 1) &&
//                    (player_z == nextBlockLocation.getBlockZ()))
//            {
//                currentBlockLocation.getBlock().setType(Material.AIR);
//                nextBlockLocation.getBlock().setType(Material.LIGHT_BLUE_TERRACOTTA);
//
//                currentBlockLocation = nextBlockLocation;
//
//
//                int randomY = ThreadLocalRandom.current().nextInt(0, 1 + 1);
//                int randomX = 0;
//                int randomZ = 0;
//
//                if (randomY == 0)
//                {
//                    randomX = ThreadLocalRandom.current().nextInt(0, 4 + 1);
//
//                    if (randomX == 4)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
//                    }
//                    else if (randomX == 3)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(0, 4 + 1);
//                    }
//                    else if (randomX == 2)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(0, 4 + 1);
//                    }
//                    else if (randomX == 1)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
//                    }
//                    else if (randomX == 0)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
//                    }
//                }
//
//                else if (randomY == 1)
//                {
//                    randomX = ThreadLocalRandom.current().nextInt(0, 4 + 1);
//
//                    if (randomX == 4)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(0, 1 + 1);
//                    }
//                    else if (randomX == 3)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
//                    }
//                    else if (randomX == 2)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
//                    }
//                    else if (randomX == 1)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
//                    }
//                    else if (randomX == 0)
//                    {
//                        randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
//                    }
//                }
//
//                boolean randomMinusPlusX = ThreadLocalRandom.current().nextBoolean();
//                boolean randomMinusPlusZ = ThreadLocalRandom.current().nextBoolean();
//
//                if (randomMinusPlusX)
//                {
//                    randomX = randomX - (randomX * 2);
//                }
//
//                if (randomMinusPlusZ)
//                {
//                    randomZ = randomZ - (randomZ * 2);
//                }
//
//                nextBlockLocation = new Location(
//                        world,
//                        currentBlockLocation.getBlockX() + randomX,
//                        currentBlockLocation.getBlockY() + randomY,
//                        currentBlockLocation.getBlockZ() + randomZ
//                );
//
//                nextBlockLocation.getBlock().setType(Material.CYAN_WOOL);
//                world.playSound(playerLocation, Sound.ENTITY_ITEM_PICKUP, 30, 1);
//
//            }
//
//        }
    }

    private Location randomStartLocation(World world, Location triggerLocation, int y, int X_range, int Z_range)
    {
        int randomX = ThreadLocalRandom.current().nextInt(X_range - (2*X_range), X_range + 1);
        int randomZ = ThreadLocalRandom.current().nextInt(Z_range - (2*Z_range), Z_range + 1);

        return new Location(
                world,
                triggerLocation.getBlockX() + randomX,
                triggerLocation.getBlockY() + y,
                triggerLocation.getBlockZ() + randomZ
        );
    }

    private Location randomNextLocation(World world, Location currentLocation)
    {
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

        return new Location(
                world,
                currentLocation.getBlockX() + randomX,
                currentLocation.getBlockY() + randomY,
                currentLocation.getBlockZ() + randomZ
        );
    }
}
