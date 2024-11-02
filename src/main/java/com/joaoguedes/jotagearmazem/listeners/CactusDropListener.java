package com.joaoguedes.jotagearmazem.listeners;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

import java.util.UUID;

public class CactusDropListener implements Listener {
    private final CactusStorageManager cactusStorageManager;
    private final PlotAPI plotAPI;
    private final LimitUpgrade limitUpgrade = JotageArmazem.getInstance().getLimitUpgrade();
    private final FortuneUpgrade fortuneUpgrade = JotageArmazem.getInstance().getFortuneUpgrade();

    public CactusDropListener(CactusStorageManager cactusStorageManager) {
        this.cactusStorageManager = cactusStorageManager;
        this.plotAPI = new PlotAPI();
    }

    @EventHandler
    public void onCactusGrow(BlockGrowEvent e) {
        if (e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.CACTUS) {
            Plot plot = plotAPI.getPlot(e.getBlock().getLocation());
            e.setCancelled(true);

            if (plot != null && plot.hasOwner()) {
                UUID playerUUID = plot.getOwners().iterator().next();
                PlayerDataManager playerDataManager = JotageArmazem.getInstance().getPlayerDataManager();
                int totalCactus = playerDataManager.loadCactusCount(playerUUID);

                if (playerUUID != null) {
                    if (!(totalCactus >= (limitUpgrade.getValue(limitUpgrade.getLevel(playerUUID))))) {
                        int amountToAdd = fortuneUpgrade.getValue(fortuneUpgrade.getLevel(playerUUID));
                        cactusStorageManager.addCactus(playerUUID, amountToAdd);
                    }
                }
            }
        }
    }
}
