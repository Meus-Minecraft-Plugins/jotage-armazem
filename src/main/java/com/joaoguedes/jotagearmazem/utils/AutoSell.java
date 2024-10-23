package com.joaoguedes.jotagearmazem.utils;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AutoSell {

    private final Map<UUID, Integer> autoSellStatus = new HashMap<>();

    public void enableAutoSell(Player player, CactusStorageManager cactusStorageManager, ValorUpgrade valorUpgrade) {
        UUID playerUUID = player.getUniqueId();

        int taskId = new BukkitRunnable() {
            @Override
            public void run() {
                int cactusCount = cactusStorageManager.getCactusCount(playerUUID);
                long sellValue = cactusCount * valorUpgrade.getCactusValue(playerUUID);
                Economy economy = JotageArmazem.getEconomy();

                if (cactusCount > 0) {
                    economy.depositPlayer(player, sellValue);
                    cactusStorageManager.clearCactus(playerUUID);
                    ActionBarAPI.sendActionBar(player, "§a§lVENDA! §fVocê recebeu §2$§a" + economy.format(sellValue) + "§f Coins.");
                }
            }
        }.runTaskTimer(JotageArmazem.getInstance(), 0, 100L).getTaskId();

        autoSellStatus.put(playerUUID, taskId);
        player.sendMessage("§2§l ARMAZEM §7◆ §fAutoSell status §7➝ §aON");
    }

    public void disableAutoSell(Player player) {
        UUID playerUUID = player.getUniqueId();

        if (autoSellStatus.containsKey(playerUUID)) {
            int taskId = autoSellStatus.get(playerUUID);
            JotageArmazem.getInstance().getServer().getScheduler().cancelTask(taskId);
            autoSellStatus.remove(playerUUID);
            player.sendMessage("§2§l ARMAZEM §7◆ §fAutoSell status §7➝ §cOFF");
        }
    }

    public boolean isAutoSellActive(Player player) {
        return autoSellStatus.containsKey(player.getUniqueId());
    }

    public void disableAutoSellOnLogout(Player player) {
        if (isAutoSellActive(player)) {
            disableAutoSell(player);
        }
    }

}
