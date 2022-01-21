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

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();

        if (event.isCancelled() ||
            event.getFrom().getBlock().getLocation() == event.getFrom().getBlock().getLocation())
        {
            return;
        }

        Location playerLocation = player.getLocation();
        double player_x = playerLocation.getX();
        double player_y = playerLocation.getY();
        double player_z = playerLocation.getZ();
        Block playerBlock = playerLocation.getBlock();
        Location targetLocation = new Location(player.getWorld(), 124, 76, -90);

        if ((player_x > 124 && player_x < 125) && (player_y == 76) && (player_z > -90 && player_z < -89)
                && playerBlock.getType() == Material.OAK_PRESSURE_PLATE)
        {
            Location newLocation = new Location(player.getWorld(), 124.5, 80, -89.5, +90, 0);
            player.teleport(newLocation);
        }




    }
}
