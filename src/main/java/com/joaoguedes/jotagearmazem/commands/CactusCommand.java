package com.joaoguedes.jotagearmazem.commands;

import com.joaoguedes.jotagearmazem.menus.ArmazemGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CactusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            new ArmazemGUI().openArmazem(player);
        } else {
            commandSender.sendMessage("§cApenas jogadores podem usar este comando.");
        }
    return true;
    }
}
