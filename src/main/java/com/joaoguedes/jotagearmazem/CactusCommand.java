package com.joaoguedes.jotagearmazem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CactusCommand implements CommandExecutor {
    private final CactusStorageManager cactusStorageManager;

    public CactusCommand(CactusStorageManager cactusStorageManager) {
        this.cactusStorageManager = cactusStorageManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            int storedCactus = cactusStorageManager.getCactusCount(player);
            new CactusArmazemGUI().openArmazem(player, storedCactus);
        } else {
            commandSender.sendMessage("§cApenas jogadores podem usar este comando.");
        }
    return true;
    }
}
