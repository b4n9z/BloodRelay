package io.github.b4n9z.bloodRelay.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("br.transferMaxHealth")) {
                commands.add("transferMaxHealth");
            }
            if (sender.hasPermission("br.transferHealth")) {
                commands.add("transferHealth");
            }
            if (sender.hasPermission("dp.help")) {
                commands.add("help");
            }
            for (String commandOption : commands) {
                if (commandOption.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(commandOption);
                }
            }
        } else if (args.length == 2) {
            if (args[0].equals("transferHealth") || args[0].equals("transferMaxHealth")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    completions.add(player.getName());
                }
            }
        } else if (args.length == 3) {
            if (args[0].equals("transferHealth") || args[0].equals("transferMaxHealth")) {
                completions.add("<amount>");
            }
        }
        return completions;
    }
}
