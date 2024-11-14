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

        if (args.length == 1) {
            if (sender.hasPermission("br.transferMaxHealth")) {
                completions.add("transferMaxHealth");
            }
            if (sender.hasPermission("br.transferHealth")) {
                completions.add("transferHealth");
            }
            if (sender.hasPermission("dp.help")) {
                completions.add("help");
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("transferMaxHealth")) {
            // Autocomplete player names
            for (Player player : Bukkit.getOnlinePlayers()) {
                completions.add(player.getName());
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("transferHealth")) {
            // Autocomplete player names
            for (Player player : Bukkit.getOnlinePlayers()) {
                completions.add(player.getName());
            }
        }
        return completions;
    }
}
