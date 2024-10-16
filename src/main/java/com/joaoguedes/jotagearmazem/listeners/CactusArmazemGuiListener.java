package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.CactusStorageManager;
import com.joaoguedes.jotagearmazem.Jotage_armazem;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class CactusArmazemGuiListener implements Listener {
    private final CactusStorageManager cactusStorageManager;

    public CactusArmazemGuiListener(CactusStorageManager cactusStorageManager) {
        this.cactusStorageManager = cactusStorageManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();

        if (guiName.equals("Armazem de Cactos")) {
            e.setCancelled(true);
        }

        if (e.getSlot() == 11 && e.getCurrentItem() != null) {
            Economy economy = Jotage_armazem.getEconomy();
            long invidualCactusValue = Jotage_armazem.getPluginConfig().getLong("cactus.individualValue");
            int cactusCount = cactusStorageManager.getCactusCount(playerUUID);
            player.sendMessage(String.format("§aVocê vendeu §6" + cactusCount + "§a cactos por §6§l" + economy.format(cactusCount * invidualCactusValue)));
            economy.depositPlayer(player, cactusCount * invidualCactusValue);
            cactusStorageManager.clearCactus(playerUUID);
            player.closeInventory();
        }
    }
}
