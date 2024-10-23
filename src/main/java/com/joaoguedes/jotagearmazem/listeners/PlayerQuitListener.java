package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.utils.AutoSell;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final AutoSell autoSell;

    public PlayerQuitListener(AutoSell autoSell) {
        this.autoSell = autoSell;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        autoSell.disableAutoSell(player);
    }
}
