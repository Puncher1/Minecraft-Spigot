package com.puncher.jumpandrun.JumpAndRun;

import com.puncher.jumpandrun.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class JumpAndRunGeneral {
    private static ArrayList<JumpAndRunPlayer> JumpAndRunPlayers = new ArrayList<>();

    // JumpAndRunPlayers
    public void addJarPlayer(JumpAndRunPlayer player)
    {
        JumpAndRunPlayers.add(player);
    }

    public void removeJarPlayer(JumpAndRunPlayer player)
    {
        JumpAndRunPlayers.remove(player);
    }

    public ArrayList<JumpAndRunPlayer> getJumpAndRunPlayers()
    {
        return JumpAndRunPlayers;
    }

    // Random Locations
    public Location randomStartLocation(World world, Location triggerLocation, int y, int X_range, int Z_range) {
        int randomX = ThreadLocalRandom.current().nextInt(X_range - (2 * X_range), X_range + 1);
        int randomZ = ThreadLocalRandom.current().nextInt(Z_range - (2 * Z_range), Z_range + 1);

        return new Location(
                world,
                triggerLocation.getBlockX() + randomX,
                triggerLocation.getBlockY() + y,
                triggerLocation.getBlockZ() + randomZ
        );
    }

    public Location randomNextLocation(World world, Location currentLocation) {
        int randomY = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        int randomX = 0;
        int randomZ = 0;

        if (randomY == 0) {
            randomX = ThreadLocalRandom.current().nextInt(0, 4 + 1);

            if (randomX == 4) {
                randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            } else if (randomX == 3) {
                randomZ = ThreadLocalRandom.current().nextInt(0, 4 + 1);
            } else if (randomX == 2) {
                randomZ = ThreadLocalRandom.current().nextInt(0, 4 + 1);
            } else if (randomX == 1) {
                randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
            } else if (randomX == 0) {
                randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
            }
        } else if (randomY == 1) {
            randomX = ThreadLocalRandom.current().nextInt(0, 4 + 1);

            if (randomX == 4) {
                randomZ = ThreadLocalRandom.current().nextInt(0, 1 + 1);
            } else if (randomX == 3) {
                randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            } else if (randomX == 2) {
                randomZ = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            } else if (randomX == 1) {
                randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
            } else if (randomX == 0) {
                randomZ = ThreadLocalRandom.current().nextInt(2, 4 + 1);
            }
        }

        boolean randomMinusPlusX = ThreadLocalRandom.current().nextBoolean();
        boolean randomMinusPlusZ = ThreadLocalRandom.current().nextBoolean();

        if (randomMinusPlusX) {
            randomX = randomX - (randomX * 2);
        }

        if (randomMinusPlusZ) {
            randomZ = randomZ - (randomZ * 2);
        }

        return new Location(
                world,
                currentLocation.getBlockX() + randomX,
                currentLocation.getBlockY() + randomY,
                currentLocation.getBlockZ() + randomZ
        );
    }

    // Actionbar
    private static void sendActionBar(JumpAndRunPlayer jarPlayer, String message) {
        jarPlayer.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public void runJarActionBar() {
        new BukkitRunnable() {
            @Override
            public void run() {

                for (JumpAndRunPlayer jarPlayer_temp : JumpAndRunPlayers)
                {
                    sendActionBar(jarPlayer_temp, jarPlayer_temp.getMessage());
                }
            }
        }.runTaskTimer(Main.getInstance(), 1, 1);
    }
}