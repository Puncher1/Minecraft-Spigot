package com.puncher.testing.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.concurrent.TimeUnit;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        Material material = block.getType();
        if (material == Material.GOLD_ORE) {

            event.setCancelled(true);
            block.setType(Material.STONE);

            float zPos = 1;
            for (int i = 0; i < 10; i++)
            {
                Location locationGoldOre = block.getLocation().add(0.5, zPos, 0.5);
                ArmorStand armorstand = block.getWorld().spawn(locationGoldOre, ArmorStand.class);
                armorstand.setVisible(false);
                armorstand.setGravity(false);
                armorstand.setCustomNameVisible(true);
                armorstand.setMarker(true);
                armorstand.setCustomName(ChatColor.GREEN + "+" + ChatColor.YELLOW + " 2.5 " + ChatColor.GREEN +
                        "M");
                try
                {
                    TimeUnit.MILLISECONDS.wait(1000);
                }
                catch (InterruptedException e) {}
                armorstand.remove();
                zPos += 1;
            }
        }

    }
}
