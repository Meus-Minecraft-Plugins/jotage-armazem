package com.joaoguedes.jotagearmazem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class CactusArmazemListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        InventoryView view = e.getView();

        if (view.getTitle().equals("Armazem")) {
            e.setCancelled(true);
        }
    }
}
