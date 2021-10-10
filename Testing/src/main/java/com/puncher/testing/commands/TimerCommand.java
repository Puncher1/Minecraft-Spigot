package com.puncher.testing.commands;

import com.puncher.testing.Main;
import com.puncher.testing.timer.Timer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0){
            sendUsage(sender);
            return true;
        }
        switch (args[0].toLowerCase()){
            case "resume": {
                Timer timer = Main.getInstance().getTimer();

                if (timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Der Timer läuft bereits.");
                    break;
                }
                timer.setRunning(true);
                sender.sendMessage(ChatColor.GREEN + "Der Timer wurde gestartet.");
                break;
            }
            case "pause": {
                Timer timer = Main.getInstance().getTimer();

                if (!timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Der Timer läuft nicht.");
                    break;
                }
                timer.setRunning(false);
                sender.sendMessage(ChatColor.GREEN + "Der Timer wurde pausiert.");
                break;
            }

            case "time": {
                Timer timer = Main.getInstance().getTimer();

                if (args.length != 2){
                    sender.sendMessage(ChatColor.GRAY + "Verwendung" + ChatColor.DARK_GRAY + ": " + ChatColor.BLUE +
                            "/timer time <time>");
                    return true;
                }

                try {
                    timer.setTime(Integer.parseInt(args[1]));
                    sender.sendMessage(ChatColor.GREEN + "Die Zeit wurde auf " + args[1] + "s gesetzt.");

                } catch(NumberFormatException e){
                    sender.sendMessage(ChatColor.DARK_RED + "Der Parameter 2 muss eine Zahl sein." +
                            "\n" + ChatColor.GRAY + "Verwendung" + ChatColor.DARK_GRAY + ": " + ChatColor.BLUE +
                            "/timer time <time>");

                }
                break;
            }
            case "reset": {
                Timer timer = Main.getInstance().getTimer();

                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage(ChatColor.GREEN + "Der Timer wurde zurückgesetzt.");
                break;
            }
            default: {
                sendUsage(sender);
                break;
            }

        }
        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(ChatColor.GRAY + "Verwendung" + ChatColor.DARK_GRAY + ": " + ChatColor.BLUE +
                "/timer resume, /timer pause, /timer time <time>, /time reset");
    }
}
