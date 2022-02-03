package com.puncher.jumpandrun.JumpAndRun;

import com.puncher.jumpandrun.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class JumpAndRunPlayer {

    private Player player;
    private Location currentBlockLocation;
    private Location nextBlockLocation;

    private Map<String, Material> blockMaterial = new HashMap<>();

    private int jumpCount;

    // Init
    public JumpAndRunPlayer(Player playerInit)
    {
        this.player = playerInit;

        Material[] woolList = {
                Material.BLACK_WOOL,
                Material.BLUE_WOOL,
                Material.BROWN_WOOL,
                Material.CYAN_WOOL,
                Material.GRAY_WOOL,
                Material.GREEN_WOOL,
                Material.LIGHT_BLUE_WOOL,
                Material.LIGHT_GRAY_WOOL,
                Material.MAGENTA_WOOL,
                Material.ORANGE_WOOL,
                Material.PINK_WOOL,
                Material.PURPLE_WOOL,
                Material.RED_WOOL,
                Material.WHITE_WOOL,
                Material.YELLOW_WOOL
        };

        Map<Material, Material> JumpAndRunMaterials = new HashMap<>();
        JumpAndRunMaterials.put(Material.BLACK_WOOL, Material.BLACK_TERRACOTTA);
        JumpAndRunMaterials.put(Material.BLUE_WOOL, Material.BLUE_TERRACOTTA);
        JumpAndRunMaterials.put(Material.BROWN_WOOL, Material.BROWN_TERRACOTTA);
        JumpAndRunMaterials.put(Material.CYAN_WOOL, Material.CYAN_TERRACOTTA);
        JumpAndRunMaterials.put(Material.GRAY_WOOL, Material.GRAY_TERRACOTTA);
        JumpAndRunMaterials.put(Material.GREEN_WOOL, Material.GREEN_TERRACOTTA);
        JumpAndRunMaterials.put(Material.LIGHT_BLUE_WOOL, Material.LIGHT_BLUE_TERRACOTTA);
        JumpAndRunMaterials.put(Material.LIGHT_GRAY_WOOL, Material.LIGHT_GRAY_TERRACOTTA);
        JumpAndRunMaterials.put(Material.MAGENTA_WOOL, Material.MAGENTA_TERRACOTTA);
        JumpAndRunMaterials.put(Material.ORANGE_WOOL, Material.ORANGE_TERRACOTTA);
        JumpAndRunMaterials.put(Material.PINK_WOOL, Material.PINK_TERRACOTTA);
        JumpAndRunMaterials.put(Material.PURPLE_WOOL, Material.PURPLE_TERRACOTTA);
        JumpAndRunMaterials.put(Material.RED_WOOL, Material.RED_TERRACOTTA);
        JumpAndRunMaterials.put(Material.WHITE_WOOL, Material.WHITE_TERRACOTTA);
        JumpAndRunMaterials.put(Material.YELLOW_WOOL, Material.YELLOW_TERRACOTTA);

        int woolIndex = ThreadLocalRandom.current().nextInt(0, woolList.length + 1);

        Material woolMaterial = woolList[woolIndex];
        Material terracottaMaterial = JumpAndRunMaterials.get(woolMaterial);

        this.blockMaterial.put("Wool", woolMaterial);
        this.blockMaterial.put("Terracotta", terracottaMaterial);

        this.jumpCount = 0;

    }

    // Player
    private void setPlayer(Player playerSet)
    {
        player = playerSet;
    }

    public Player getPlayer()
    {
        return player;
    }

    // CurrentBlock
    public void setCurrentBlockLocation(Location blockLocation)
    {
        currentBlockLocation = blockLocation;
    }

    public Location getCurrentBlockLocation()
    {
        return currentBlockLocation;
    }

    // NextBlock
    public void setNextBlockLocation(Location blockLocation)
    {
        nextBlockLocation = blockLocation;
    }

    public Location getNextBlockLocation()
    {
        return nextBlockLocation;
    }

    // blockMaterial
    public Map<String, Material> getBlockMaterial()
    {
        return blockMaterial;
    }

    // jumpCount
    public int getJumpCount()
    {
        return jumpCount;
    }

    public void setJumpCount(int count)
    {
        jumpCount = count;
    }

    public void incJumpCount()
    {
        jumpCount++;
    }

    public void decJumpCount()
    {
        jumpCount--;
    }
}