package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.menus.UpgradeArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
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
    private final LimitUpgrade limitUpgrade;

    public UpgradeArmazemGUIListener(ValorUpgrade valorUpgrade, CactusStorageManager cactusStorageManager, FortuneUpgrade fortuneUpgrade, LimitUpgrade limitUpgrade) {
        this.valorUpgrade = valorUpgrade;
        this.cactusStorageManager = cactusStorageManager;
        this.fortuneUpgrade = fortuneUpgrade;
        this.limitUpgrade = limitUpgrade;
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
                    if (valorUpgrade.getCurrentLevel(playerUUID) == 1) {
                        economy.withdrawPlayer(player, JotageArmazem.getPluginConfig().getLong("upgrades.valor.inicialprice"));
                    } else {
                        economy.withdrawPlayer(player, currentPrice);
                    }
                    UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI(valorUpgrade, fortuneUpgrade, limitUpgrade);
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
                    if (fortuneUpgrade.getCurrentLevel(playerUUID) == 1) {
                        economy.withdrawPlayer(player, JotageArmazem.getPluginConfig().getLong("upgrades.fortune.inicialprice"));
                    } else {
                        economy.withdrawPlayer(player, currentPrice);
                    }
                    UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI(valorUpgrade, fortuneUpgrade, limitUpgrade);
                    ItemStack newFortuneItem = upgradeGUI.createFortunaItem(playerUUID);
                    e.getInventory().setItem(13, newFortuneItem);
                } else {
                    player.sendMessage("§cSaldo insuficiente para realizar o upgrade. Custo: §e" + economy.format(currentPrice));
                }
            }

            if (e.getSlot() == 15 && e.getCurrentItem() != null) {
                long currentPrice = limitUpgrade.getCurrentPrice(playerUUID);
                if (economy.getBalance(player) >= currentPrice) {
                    limitUpgrade.applyLimitUpgrade(player);
                    if (limitUpgrade.getCurrentLevel(playerUUID) == 2) {
                        economy.withdrawPlayer(player, JotageArmazem.getPluginConfig().getLong("upgrades.limit.inicialprice"));
                    } else {
                        economy.withdrawPlayer(player, currentPrice);
                    }
                    UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI(valorUpgrade, fortuneUpgrade, limitUpgrade);
                    ItemStack newLimiteItem = upgradeGUI.createLimiteItem(playerUUID);
                    e.getInventory().setItem(15, newLimiteItem);
                } else {
                    player.sendMessage("§cSaldo insuficiente para realizar o upgrade. Custo: §e" + economy.format(currentPrice));
                }
            }

            if (e.getSlot() == 26 && e.getCurrentItem() != null) {
                CactusArmazemGUI cactusArmazemGUI = new CactusArmazemGUI(cactusStorageManager, valorUpgrade, fortuneUpgrade, limitUpgrade);
                cactusArmazemGUI.openCactoArmazem(playerUUID);
            }
        }
    }
}
