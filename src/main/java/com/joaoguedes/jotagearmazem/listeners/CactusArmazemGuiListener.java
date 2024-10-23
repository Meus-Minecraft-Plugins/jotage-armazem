package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.menus.ArmazemGUI;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.menus.UpgradeArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.AutoSell;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class CactusArmazemGuiListener implements Listener {
    private final CactusStorageManager cactusStorageManager;
    private final ValorUpgrade valorUpgrade;
    private final FortuneUpgrade fortuneUpgrade;
    private final LimitUpgrade limitUpgrade;
    private final CactusArmazemGUI cactusArmazemGUI;
    private final AutoSell autoSell;

    public CactusArmazemGuiListener(CactusStorageManager cactusStorageManager, ValorUpgrade valorUpgrade, FortuneUpgrade fortuneUpgrade, LimitUpgrade limitUpgrade, CactusArmazemGUI cactusArmazemGUI, AutoSell autoSell) {
        this.cactusStorageManager = cactusStorageManager;
        this.valorUpgrade = valorUpgrade;
        this.fortuneUpgrade = fortuneUpgrade;
        this.limitUpgrade = limitUpgrade;
        this.cactusArmazemGUI = cactusArmazemGUI;
        this.autoSell = autoSell;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();

        if (guiName.equals("Armazem de Cactos")) {
            e.setCancelled(true);
            int cactusCount = cactusStorageManager.getCactusCount(playerUUID);
            Economy economy = JotageArmazem.getEconomy();

            if (e.getSlot() == 11 && e.getCurrentItem() != null) {
                player.sendMessage(String.format("§2§l ARMAZEM §7◆ §fVocê vendeu §6" + cactusCount + "§f cactos por §6§l" + economy.format(cactusCount * valorUpgrade.getCactusValue(playerUUID))));
                economy.depositPlayer(player, cactusCount * valorUpgrade.getCactusValue(playerUUID));
                cactusStorageManager.clearCactus(playerUUID);
                cactusArmazemGUI.openCactoArmazem(playerUUID);
            }

            if (e.getSlot() == 13 && e.getCurrentItem() != null) {
                new UpgradeArmazemGUI(valorUpgrade, fortuneUpgrade, limitUpgrade).openUpgradeArmazemGUI(playerUUID);
            }

            if (e.getSlot() == 15 && e.getCurrentItem() != null) {
                if (autoSell.isAutoSellActive(player)) {
                    autoSell.disableAutoSell(player);
                } else {
                    autoSell.enableAutoSell(player, cactusStorageManager, valorUpgrade);
                }

                cactusArmazemGUI.openCactoArmazem(playerUUID);
            }

            if (e.getSlot() == 26 && e.getCurrentItem() != null) {
                ArmazemGUI armazemGUI = new ArmazemGUI();
                armazemGUI.openArmazem(player);
            }
        }
    }
}
