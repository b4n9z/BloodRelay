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

public class TransferMaxHealthCommand implements CommandExecutor {
    private final BloodRelay plugin;

    public TransferMaxHealthCommand(BloodRelay plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!(player.isOp()) || !(player.hasPermission("br.transferMaxHealth"))) {
                sender.sendMessage("You do not have permission to use this command.");
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
                sender.sendMessage("Invalid number format for max health.");
                return false;
            }

            TextComponent confirmMessage = new TextComponent("Are you sure you want to transfer " + amount + " max health from " + sourcePlayer.getName() + " to " + targetPlayer.getName() + "? ");
            confirmMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/BloodRelay confirmTransferMaxHealth"));

            TextComponent yesButton = new TextComponent("[Yes]");
            yesButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/BloodRelay confirmTransferMaxHealth yes " + targetPlayer.getName() + " " + amount));
            yesButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Confirm the transfer")));

            TextComponent noButton = new TextComponent("[No]");
            noButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/BloodRelay confirmTransferMaxHealth no"));
            noButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Cancel the transfer")));

            player.spigot().sendMessage(confirmMessage);
            player.spigot().sendMessage(yesButton);
            player.spigot().sendMessage(noButton);

            return true;
        } else if (args.length == 4 && args[0].equalsIgnoreCase("confirmTransferMaxHealth")) {
            if (args[1].equalsIgnoreCase("yes")) {
                Player sourcePlayer = (Player) sender;
                Player targetPlayer = Bukkit.getPlayer(args[2]);
                if (targetPlayer != null) {
                    double amount;
                    try {
                        amount = Double.parseDouble(args[3]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("Invalid number format for max health.");
                        return false;
                    }

                    double sourceMaxHealth = HealthManager.getMaxHealth(sourcePlayer);
                    if (sourceMaxHealth < amount) {
                        sender.sendMessage("You do not have enough max health to transfer.");
                        return false;
                    }

                    // Transfer max health
                    HealthManager.setMaxHealth(sourceMaxHealth - amount, sourcePlayer);
                    double targetMaxHealth = HealthManager.getMaxHealth(targetPlayer);
                    HealthManager.setMaxHealth(targetMaxHealth + amount, targetPlayer);

                    sender.sendMessage("Max health successfully transferred to " + targetPlayer.getName());
                } else {
                    sender.sendMessage("Player not found.");
                }
            } else {
                sender.sendMessage("Max health transfer cancelled.");
            }
            return true;
        } else {
            sender.sendMessage("Usage: /BloodRelay transfer <targetPlayer> <amount>");
            return false;
        }
    }
}