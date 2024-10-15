package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.CactusStorageManager;
import com.joaoguedes.jotagearmazem.Jotage_armazem;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CactusArmazemGuiListener implements Listener {
    private final CactusArmazemGUI cactusArmazemGUI;
    private final JavaPlugin plugin;
    private final CactusStorageManager cactusStorageManager;

    public CactusArmazemGuiListener(CactusArmazemGUI cactusArmazemGUI, JavaPlugin plugin, CactusStorageManager cactusStorageManager) {
        this.cactusArmazemGUI = cactusArmazemGUI;
        this.plugin = plugin;
        this.cactusStorageManager = cactusStorageManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        String guiName = e.getView().getTitle();

        if (guiName.equals("Armazem de Cactos")) {
            e.setCancelled(true);
        }

        if (e.getSlot() == 11 && e.getCurrentItem() != null) {
            player.sendMessage("§aVocê vendeu §6§l" + cactusStorageManager.getCactusCount(playerUUID) + "§a cactos!");
            player.sendMessage("§aE recebeu §6§l" + cactusStorageManager.getCactusCount(playerUUID) * 50 + "§a coins!");
            cactusStorageManager.clearCactus(playerUUID);
            player.closeInventory();
        }
    }
}
