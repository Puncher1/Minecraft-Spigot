package com.puncher.testing.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;

import java.sql.Time;
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

            Location locationGoldOre = block.getLocation().add(0.5, 1, 0.5);
            Location startLocationArmorStand = locationGoldOre;
            Location endLocationArmorStand = block.getLocation().add(0.5, 1, 1.5);

            ArmorStand armorstand = block.getWorld().spawn(locationGoldOre, ArmorStand.class);
            armorstand.setVisible(false);
            armorstand.setGravity(true);
            armorstand.setCustomNameVisible(true);
            armorstand.setMarker(true);
            armorstand.setCustomName(ChatColor.GREEN + "+" + ChatColor.YELLOW + " 2.5 " + ChatColor.GREEN +
                    "M");

            double pitchVector = ((locationGoldOre.getPitch()) * Math.PI) / 180;
            double yawVector = ((locationGoldOre.getYaw()) * Math.PI) / 180;
            double xVector = Math.sin(pitchVector) * Math.cos(yawVector);
            double yVector = Math.sin(pitchVector) * Math.sin(yawVector);
            double zVector = Math.cos(pitchVector);

            Vector vectorArmorStand = new Vector(xVector, zVector, yVector);
            armorstand.setVelocity(vectorArmorStand.multiply(3));
            armorstand.teleport(armorstand.getLocation().add(vectorArmorStand));
        }

    }
}
