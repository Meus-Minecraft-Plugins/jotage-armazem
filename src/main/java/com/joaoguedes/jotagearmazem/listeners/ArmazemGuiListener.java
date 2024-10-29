package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.utils.AutoSell;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class ArmazemGuiListener implements Listener {
    private final CactusStorageManager cactusStorageManager;
    private final AutoSell autoSell;

    public ArmazemGuiListener(CactusStorageManager cactusStorageManager, AutoSell autoSell) {
        this.cactusStorageManager = cactusStorageManager;
        this.autoSell = autoSell;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();

        if (guiName.equals("Armazem")) {
            e.setCancelled(true);

            if (e.getSlot() == 13) {
                new CactusArmazemGUI(cactusStorageManager, autoSell).openCactoArmazem(playerUUID);
            }
        }

    }
}
