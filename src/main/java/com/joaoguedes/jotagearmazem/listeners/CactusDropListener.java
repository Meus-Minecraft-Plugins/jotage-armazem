package com.joaoguedes.jotagearmazem.listeners;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CactusDropListener implements Listener {
    private final CactusStorageManager cactusStorageManager;
    private final PlotAPI plotAPI;
    private final LimitUpgrade limitUpgrade;
    private final FortuneUpgrade fortuneUpgrade;

    public CactusDropListener(CactusStorageManager cactusStorageManager, LimitUpgrade limitUpgrade, FortuneUpgrade fortuneUpgrade) {
        this.cactusStorageManager = cactusStorageManager;
        this.plotAPI = new PlotAPI();
        this.limitUpgrade = limitUpgrade;
        this.fortuneUpgrade = fortuneUpgrade;
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent e) {
        Item item = e.getEntity();
        ItemStack itemStack = item.getItemStack();

        if (itemStack.getType() == Material.CACTUS) {
            Plot plot = plotAPI.getPlot(item.getLocation());
            if (plot != null && plot.hasOwner()) {
                UUID playerUUID = plot.getOwners().iterator().next();
                Player player = Bukkit.getPlayer(playerUUID);
                int totalCactus = cactusStorageManager.getCactusCount(playerUUID);

                if (playerUUID != null) {
                    if (!(totalCactus >= (limitUpgrade.getLimitValue(playerUUID)))) {
                        cactusStorageManager.addCactus(playerUUID, itemStack.getAmount() * fortuneUpgrade.getFortuneValue(playerUUID));
                    }
                    item.remove();
                }
            }
        }
    }
}
