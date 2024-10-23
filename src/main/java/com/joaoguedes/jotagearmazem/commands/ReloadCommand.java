package com.joaoguedes.jotagearmazem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public ReloadCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("armazemreload")) {
            plugin.reloadConfig();  // Recarrega a configuração
            sender.sendMessage("§aConfiguração do plugin recarregada com sucesso!");
            return true;
        }
        return false;
    }
}
