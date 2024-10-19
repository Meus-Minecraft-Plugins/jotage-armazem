package com.joaoguedes.jotagearmazem.listeners;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CactusDropListener implements Listener {
    private final CactusStorageManager cactusStorageManager;
    private final PlotAPI plotAPI;
    private final LimitUpgrade limitUpgrade;

    public CactusDropListener(CactusStorageManager cactusStorageManager, LimitUpgrade limitUpgrade) {
        this.cactusStorageManager = cactusStorageManager;
        this.plotAPI = new PlotAPI();
        this.limitUpgrade = limitUpgrade;
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent e) {
        Item item = e.getEntity();
        ItemStack itemStack = item.getItemStack();

        if (itemStack.getType() == Material.CACTUS) {
            Plot plot = plotAPI.getPlot(item.getLocation());
            if (plot != null && plot.hasOwner()) {
                UUID playerUUID = plot.getOwners().iterator().next();

                if (playerUUID != null) {
                    if (!(cactusStorageManager.getCactusCount(playerUUID) > (limitUpgrade.getLimitValue(playerUUID) - 1))) {
                        cactusStorageManager.addCactus(playerUUID, itemStack.getAmount());
                    }
                    item.remove();
                }
            }
        }
    }
}
