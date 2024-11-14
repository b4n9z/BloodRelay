package io.github.b4n9z.bloodRelay.Commands;

import io.github.b4n9z.bloodRelay.BloodRelay;
import io.github.b4n9z.bloodRelay.Managers.HealthManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TransferHealthCommand implements CommandExecutor {
    private final BloodRelay plugin;

    public TransferHealthCommand(BloodRelay plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!(player.isOp()) || !(player.hasPermission("br.transferHealth"))) {
                player.sendMessage("You do not have permission to use this command.");
                return false;
            }
        } else {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        if (args.length == 3) {
            Player sourcePlayer = (Player) sender;
            Player targetPlayer = Bukkit.getPlayer(args[1]);
            if (targetPlayer == null) {
                sender.sendMessage("Player not found.");
                return false;
            }

            double amount;
            try {
                amount = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage("Invalid number format for health.");
                return false;
            }

            TextComponent confirmMessage = new TextComponent("Are you sure you want to transfer " + amount + " health from " + sourcePlayer.getName() + " to " + targetPlayer.getName() + "? ");
            confirmMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/BloodRelay confirmTransferHealth"));

            TextComponent yesButton = new TextComponent("[Yes]");
            yesButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/BloodRelay confirmTransferHealth yes " + targetPlayer.getName() + " " + amount));
            yesButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Confirm the health transfer")));

            TextComponent noButton = new TextComponent("[No]");
            noButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/BloodRelay confirmTransferHealth no"));
            noButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Cancel the health transfer")));

            player.spigot().sendMessage(confirmMessage);
            player.spigot().sendMessage(yesButton);
            player.spigot().sendMessage(noButton);

            return true;
        } else if (args.length == 4 && args[0].equalsIgnoreCase("confirmTransferHealth")) {
            if (args[1].equalsIgnoreCase("yes")) {
                Player sourcePlayer = (Player) sender;
                Player targetPlayer = Bukkit.getPlayer(args[2]);
                if (targetPlayer != null) {
                    double amount;
                    try {
                        amount = Double.parseDouble(args[3]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("Invalid number format for health.");
                        return false;
                    }

                    double sourceHealth = sourcePlayer.getHealth();
                    if (sourceHealth < amount) {
                        sender.sendMessage("You do not have enough health to transfer.");
                        return false;
                    }

                    // Transfer current health
                    sourcePlayer.setHealth(sourceHealth - amount);
                    double targetHealth = targetPlayer.getHealth();
                    targetPlayer.setHealth(Math.min(targetHealth + amount, HealthManager.getMaxHealth(targetPlayer)));

                    sender.sendMessage("Health successfully transferred to " + targetPlayer.getName());
                } else {
                    sender.sendMessage("Player not found.");
                }
            } else {
                sender.sendMessage("Health transfer cancelled.");
            }
            return true;
        } else {
            sender.sendMessage("Usage: /BloodRelay transferHealth <targetPlayer> <amount>");
            return false;
        }
    }
}