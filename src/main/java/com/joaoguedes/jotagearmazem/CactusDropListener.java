package com.joaoguedes.jotagearmazem;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
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

    public CactusDropListener(CactusStorageManager cactusStorageManager) {
        this.cactusStorageManager = cactusStorageManager;
        this.plotAPI = new PlotAPI();
    }

    // Evento para itens dropados no mundo (como o cacto quebrando naturalmente)
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent e) {
        Item item = e.getEntity();
        ItemStack itemStack = item.getItemStack();

        if (itemStack.getType() == Material.CACTUS) {
            Plot plot = plotAPI.getPlot(item.getLocation());
            if (plot != null && plot.hasOwner()) {
                UUID ownerUUID = plot.getOwners().iterator().next();

                if (ownerUUID != null) {
                    cactusStorageManager.addCactus(ownerUUID, itemStack.getAmount());
                    item.remove();
                }
            }
        }
    }
}
