package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.CactusStorageManager;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class ArmazemGuiListener implements Listener {
    private final CactusStorageManager cactusStorageManager;

    public ArmazemGuiListener(CactusStorageManager cactusStorageManager) {
        this.cactusStorageManager = cactusStorageManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();

        if (guiName.equals("Armazem")) {
            e.setCancelled(true);

            if (e.getSlot() == 12) {
                new CactusArmazemGUI(cactusStorageManager).openCactoArmazem(playerUUID);
            }
        }

    }
}
