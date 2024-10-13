package com.joaoguedes.jotagearmazem;

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
            new CactusArmazemGUI().openArmazem(player, storedCactus);
        } else {
            commandSender.sendMessage("§cApenas jogadores podem usar este comando.");
        }
    return true;
    }
}
