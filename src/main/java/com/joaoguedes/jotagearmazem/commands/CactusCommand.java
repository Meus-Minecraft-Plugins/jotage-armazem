package com.joaoguedes.jotagearmazem.commands;

import com.joaoguedes.jotagearmazem.CactusStorageManager;
import com.joaoguedes.jotagearmazem.menus.ArmazemGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CactusCommand implements CommandExecutor {
    private final CactusStorageManager cactusStorageManager;

    public CactusCommand(CactusStorageManager cactusStorageManager) {
        this.cactusStorageManager = cactusStorageManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            UUID ownerUUID = player.getUniqueId();

            int storedCactus = cactusStorageManager.getCactusCount(ownerUUID);
            new ArmazemGUI().openArmazem(player);
        } else {
            commandSender.sendMessage("§cApenas jogadores podem usar este comando.");
        }
    return true;
    }
}
