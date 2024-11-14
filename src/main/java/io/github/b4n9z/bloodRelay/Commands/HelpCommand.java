package io.github.b4n9z.bloodRelay.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§cBloodRelay§f Plugin Commands:");
        sender.sendMessage("/§cBloodRelay§e transferMaxHealth§f <targetPlayer> <Amount> - Transfers your max health.");
        sender.sendMessage("/§cBloodRelay§e transferHealth§f <targetPlayer> <Amount> - Transfers your health.");
        sender.sendMessage("/§cBloodRelay§e help§f - Shows this help message.");
        return true;
    }
}