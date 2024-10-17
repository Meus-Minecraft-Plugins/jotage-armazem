package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.menus.UpgradeArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class UpgradeArmazemGUIListener implements Listener {

    private final ValorUpgrade valorUpgrade;
    private final CactusStorageManager cactusStorageManager;
    private final FortuneUpgrade fortuneUpgrade;

    public UpgradeArmazemGUIListener(ValorUpgrade valorUpgrade, CactusStorageManager cactusStorageManager, FortuneUpgrade fortuneUpgrade) {
        this.valorUpgrade = valorUpgrade;
        this.cactusStorageManager = cactusStorageManager;
        this.fortuneUpgrade = fortuneUpgrade;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();
        Economy economy = JotageArmazem.getEconomy();

        if (guiName.equals("Upgrade de Cactos")) {
            e.setCancelled(true);
            if (e.getSlot() == 11 && e.getCurrentItem() != null) {
                long currentPrice = valorUpgrade.getCurrentPrice(playerUUID);
                if (economy.getBalance(player) >= currentPrice) {
                    valorUpgrade.applyValorUpgrade(player);
                    economy.withdrawPlayer(player, currentPrice);
                    UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI(valorUpgrade, fortuneUpgrade);
                    ItemStack newValorItem = upgradeGUI.createValorItem(playerUUID);
                    e.getInventory().setItem(11, newValorItem);
                } else {
                    player.sendMessage("§cSaldo insuficiente para realizar o upgrade. Custo: §e" + economy.format(currentPrice));
                }
            }

            if (e.getSlot() == 13 && e.getCurrentItem() != null) {
                long currentPrice = fortuneUpgrade.getCurrentPrice(playerUUID);
                if (economy.getBalance(player) >= currentPrice) {
                    fortuneUpgrade.applyFortuneUpgrade(player);
                    economy.withdrawPlayer(player, currentPrice);
                    UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI(valorUpgrade, fortuneUpgrade);
                    ItemStack newFortuneItem = upgradeGUI.createFortunaItem(playerUUID);
                    e.getInventory().setItem(13, newFortuneItem);
                }
            }

            if (e.getSlot() == 26 && e.getCurrentItem() != null) {
                CactusArmazemGUI cactusArmazemGUI = new CactusArmazemGUI(cactusStorageManager, valorUpgrade, fortuneUpgrade);
                cactusArmazemGUI.openCactoArmazem(playerUUID);
            }
        }
    }
}
