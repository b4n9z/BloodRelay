package io.github.b4n9z.bloodRelay;

import io.github.b4n9z.bloodRelay.Commands.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class BloodRelay extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerEventsAndCommands();
        getLogger().info("BloodRelay has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("BloodRelay has been disabled!");
    }

    private void registerEventsAndCommands() {
        //Register Command
        CommandExecutor mainCommand = new MainCommand(this);
        this.getCommand("BloodRelay").setExecutor(mainCommand);
        this.getCommand("br").setExecutor(mainCommand);
        //Register Completer
        this.getCommand("BloodRelay").setTabCompleter(new MainCommandCompleter());
        this.getCommand("br").setTabCompleter(new MainCommandCompleter());
    }
}
