package com.joaoguedes.jotagearmazem;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CactusBreakListener implements Listener {
    private final CactusStorageManager cactusStorageManager;

    public CactusBreakListener(CactusStorageManager cactusStorageManager) {
        this.cactusStorageManager = cactusStorageManager;
    }

    @EventHandler
    public void onCactusBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block.getType() == Material.CACTUS) {
            Player player = e.getPlayer();
            cactusStorageManager.addCactus(player, 1);
            e.setDropItems(false);
        }
    }
}
