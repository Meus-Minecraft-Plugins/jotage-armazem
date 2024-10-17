package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.menus.UpgradeArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.Jotage_armazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class CactusArmazemGuiListener implements Listener {
    private final CactusStorageManager cactusStorageManager;
    private final ValorUpgrade valorUpgrade;

    public CactusArmazemGuiListener(CactusStorageManager cactusStorageManager, ValorUpgrade valorUpgrade) {
        this.cactusStorageManager = cactusStorageManager;
        this.valorUpgrade = valorUpgrade;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();

        if (guiName.equals("Armazem de Cactos")) {
            e.setCancelled(true);

            if (e.getSlot() == 11 && e.getCurrentItem() != null) {
                Economy economy = Jotage_armazem.getEconomy();
                int cactusCount = cactusStorageManager.getCactusCount(playerUUID);
                player.sendMessage(String.format("§aVocê vendeu §6" + cactusCount + "§a cactos por §6§l" + economy.format(cactusCount * valorUpgrade.getCactusValue(playerUUID))));
                economy.depositPlayer(player, cactusCount * valorUpgrade.getCactusValue(playerUUID));
                cactusStorageManager.clearCactus(playerUUID);
                player.closeInventory();
            }

            if (e.getSlot() == 13 && e.getCurrentItem() != null) {
                new UpgradeArmazemGUI(valorUpgrade).openUpgradeArmazemGUI(playerUUID);
            }
        }
    }
}
