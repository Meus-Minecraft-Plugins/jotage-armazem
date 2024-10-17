package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.Jotage_armazem;
import com.joaoguedes.jotagearmazem.menus.UpgradeArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.upgrade.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class UpgradeArmazemGUIListener implements Listener {

    private final ValorUpgrade valorUpgrade;

    public UpgradeArmazemGUIListener(ValorUpgrade valorUpgrade) {
        this.valorUpgrade = valorUpgrade;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();
        Economy economy = Jotage_armazem.getEconomy();

        if (guiName.equals("Upgrade de Cactos")) {
            e.setCancelled(true);
            if (e.getSlot() == 11 && e.getCurrentItem() != null) {
                long currentPrice = valorUpgrade.getCurrentPrice(playerUUID);
                if (economy.getBalance(player) >= currentPrice) {
                    valorUpgrade.applyUpgrade(player);
                    economy.withdrawPlayer(player, currentPrice);
                    UpgradeArmazemGUI upgradeGUI = new UpgradeArmazemGUI(valorUpgrade);
                    ItemStack novoValorItem = upgradeGUI.createValorItem(playerUUID);
                    e.getInventory().setItem(11, novoValorItem);
                } else {
                    player.sendMessage("§cSaldo insuficiente para realizar o upgrade. Custo: §e" + economy.format(currentPrice));
                }
            }

            if (e.getSlot() == 13 && e.getCurrentItem() != null) {
            }
        }
    }
}
