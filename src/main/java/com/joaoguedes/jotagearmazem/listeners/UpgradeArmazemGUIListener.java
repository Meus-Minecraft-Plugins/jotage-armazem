package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.menus.UpgradeArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.AutoSell;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class UpgradeArmazemGUIListener implements Listener {

    private final CactusStorageManager cactusStorageManager;
    private final AutoSell autoSell;

    public UpgradeArmazemGUIListener(CactusStorageManager cactusStorageManager, AutoSell autoSell) {
        this.cactusStorageManager = cactusStorageManager;
        this.autoSell = autoSell;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();
        Economy economy = JotageArmazem.getEconomy();
        PlayerDataManager playerDataManager = JotageArmazem.getInstance().getPlayerDataManager();
        ValorUpgrade valorUpgrade = JotageArmazem.getInstance().getValorUpgrade();
        LimitUpgrade limitUpgrade = JotageArmazem.getInstance().getLimitUpgrade();
        FortuneUpgrade fortuneUpgrade = JotageArmazem.getInstance().getFortuneUpgrade();

        if (guiName.equals("Upgrade de Cactos")) {
            e.setCancelled(true);

            if (e.getSlot() == 11 && e.getCurrentItem() != null) {
                long currentPrice = valorUpgrade.getPrice(playerDataManager.loadValueLevel(playerUUID));
                if (economy.getBalance(player) >= currentPrice) {
                    valorUpgrade.upgradeValue(player);
                    if (valorUpgrade.getLevel(playerUUID) >= valorUpgrade.getMaxLevel()) {
                        economy.withdrawPlayer(player, currentPrice);
                        UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI();
                        ItemStack newValorItem = upgradeGUI.createValorItem(playerUUID);
                        e.getInventory().setItem(11, newValorItem);
                    }

                } else {
                    player.sendMessage("§2§l ARMAZEM §7◆ §cSaldo insuficiente para realizar o upgrade. Custo: §e" + economy.format(currentPrice));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                }
            }

            if (e.getSlot() == 13 && e.getCurrentItem() != null) {
                long currentPrice = fortuneUpgrade.getPrice(fortuneUpgrade.getLevel(playerUUID));
                if (economy.getBalance(player) >= currentPrice) {
                    fortuneUpgrade.upgradeFortune(player);
                    if (fortuneUpgrade.getLevel(playerUUID) >= fortuneUpgrade.getMaxLevel()) {
                        economy.withdrawPlayer(player, currentPrice);
                        UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI();
                        ItemStack newFortuneItem = upgradeGUI.createFortunaItem(playerUUID);
                        e.getInventory().setItem(13, newFortuneItem);
                    }
                } else {
                    player.sendMessage("§2§l ARMAZEM §7◆ §cSaldo insuficiente para realizar o upgrade. Custo: §e" + economy.format(currentPrice));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                }
            }

            if (e.getSlot() == 15 && e.getCurrentItem() != null) {
                long currentPrice = limitUpgrade.getPrice(limitUpgrade.getLevel(playerUUID));
                if (economy.getBalance(player) >= currentPrice) {
                    limitUpgrade.upgradeLimit(player);
                    if (limitUpgrade.getLevel(playerUUID) >= limitUpgrade.getMaxLevel()) {
                        economy.withdrawPlayer(player, currentPrice);
                        UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI();
                        ItemStack newLimiteItem = upgradeGUI.createLimiteItem(playerUUID);
                        e.getInventory().setItem(15, newLimiteItem);
                    }
                } else {
                    player.sendMessage("§2§l ARMAZEM §7◆ §cSaldo insuficiente para realizar o upgrade. Custo: §e" + economy.format(currentPrice));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                }
            }

            if (e.getSlot() == 26 && e.getCurrentItem() != null) {
                CactusArmazemGUI cactusArmazemGUI = new CactusArmazemGUI(cactusStorageManager, autoSell);
                cactusArmazemGUI.openCactoArmazem(playerUUID);
            }
        }
    }
}
