package io.github.b4n9z.bloodRelay.Commands;

import io.github.b4n9z.bloodRelay.BloodRelay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    private final TransferHealthCommand transferHealthCommand;
    private final TransferMaxHealthCommand transferMaxHealthCommand;
    private final HelpCommand helpCommand;

    public MainCommand(BloodRelay plugin) {
        this.transferHealthCommand = new TransferHealthCommand(plugin);
        this.transferMaxHealthCommand = new TransferMaxHealthCommand(plugin);
        this.helpCommand = new HelpCommand();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Please specify a subcommand.");
            return false;
        }

        String subcommand = args[0];
        return switch (subcommand) {
            case "transferHealth" -> transferHealthCommand.onCommand(sender, command, label, args);
            case "transferMaxHealth" -> transferMaxHealthCommand.onCommand(sender, command, label, args);
            case "help" -> helpCommand.onCommand(sender, command, label, args);
            default -> {
                sender.sendMessage("Invalid subcommand.");
                yield true;
            }
        };
    }
}
