package io.github.b4n9z.bloodRelay.Commands;

import io.github.b4n9z.bloodRelay.BloodRelay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class MainCommand implements CommandExecutor {
    private final Map<String, CommandExecutor> subCommands = new HashMap<>();

    public MainCommand(BloodRelay plugin) {
        subCommands.put("transferMaxHealth", new TransferMaxHealthCommand(plugin));
        subCommands.put("transferHealth", new TransferHealthCommand(plugin));
        subCommands.put("help", new HelpCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Please specify a subcommand.");
            return false;
        }

        CommandExecutor executor = subCommands.get(args[0].toLowerCase());
        if (executor == null) {
            sender.sendMessage("Unknown subcommand.");
            return false;
        }

        return executor.onCommand(sender, command, label, args);
    }
}
