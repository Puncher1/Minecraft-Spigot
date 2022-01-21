package com.puncher.jumpandrun.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Powerable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    private static boolean isJumpAndRunRunning = false;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();

        if (event.isCancelled())            // TODO: Checking if block has changed (otherwise ignore)
        {
            System.out.println("return");
            return;
        }

        Location playerLocation = player.getLocation();
        double player_x = playerLocation.getX();
        double player_y = playerLocation.getY();
        double player_z = playerLocation.getZ();
        Block playerBlock = playerLocation.getBlock();
        Location currentBlockLocation = new Location(player.getWorld(), 124.5, 80, -89.5);
        Location nextBlockLocation = new Location(player.getWorld(), 127.5, 81, -89.5);

        if ((player_x > 124 && player_x < 125) && (player_y == 76) && (player_z > -90 && player_z < -89)
                && playerBlock.getType() == Material.OAK_PRESSURE_PLATE)
        {
            Location newPlayerLocation = new Location(player.getWorld(), 124.5, 81, -89.5, -90, 0);
            player.teleport(newPlayerLocation);
            currentBlockLocation.getBlock().setType(Material.LIGHT_BLUE_TERRACOTTA);
            nextBlockLocation.getBlock().setType(Material.CYAN_WOOL);

            playerLocation = player.getLocation();
            player_y = playerLocation.getY();

            isJumpAndRunRunning = true;
        }

        if (isJumpAndRunRunning)
        {
            System.out.println((int)player_y);
            System.out.println((int)currentBlockLocation.getY());
            if ((int)player_y < (int)currentBlockLocation.getY())
            {
                isJumpAndRunRunning = false;
                currentBlockLocation.getBlock().setType(Material.AIR);
                nextBlockLocation.getBlock().setType(Material.AIR);
            }
        }




    }
}
