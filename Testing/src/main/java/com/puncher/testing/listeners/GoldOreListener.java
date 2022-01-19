package com.puncher.testing.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GoldOreListener implements Listener {

    @EventHandler
    public void onGoldOreDestroyed(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        Material material = block.getType();

        if (material == Material.GOLD_ORE)
        {
            Location locationOfBlock = block.getLocation();
            Material newMaterial = Material.STONE;

            System.out.println(locationOfBlock.getBlock());
            locationOfBlock.getBlock().setType(newMaterial);
        }

    }
}
