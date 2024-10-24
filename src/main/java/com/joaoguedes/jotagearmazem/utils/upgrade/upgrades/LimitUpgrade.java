package com.joaoguedes.jotagearmazem.utils.upgrade.upgrades;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LimitUpgrade {

    private final int inicialLimitValue;
    private final long inicialPrice;
    private final PlayerDataManager playerDataManager;
    private final int maxLevel;

    public LimitUpgrade(PlayerDataManager playerDataManager, int inicialLimitValue, long inicialPrice, int maxLevel) {
        this.playerDataManager = playerDataManager;
        this.inicialLimitValue = inicialLimitValue;
        this.inicialPrice = inicialPrice;
        this.maxLevel = maxLevel;
    }

    public long getValue(int currentLevel) {
        return inicialLimitValue * (currentLevel * currentLevel * currentLevel);
    }

    public long getPrice(int currentLevel) {
        return currentLevel * inicialPrice;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getLevel(UUID playerUUID) {
        return playerDataManager.loadLimitLevel(playerUUID);
    }

    public void setLevel(UUID playerUUID, int level) {
        playerDataManager.setLimitLevel(playerUUID, level);
    }

    public void upgradeLimit(Player player) {

        UUID playerUUID = player.getUniqueId();

        int currentLevel = playerDataManager.loadLimitLevel(playerUUID);

        if (currentLevel < maxLevel) {
            playerDataManager.setLimitLevel(playerUUID, (currentLevel + 1));
        } else {
            player.sendMessage("§2§l ARMAZEM §cVocê chegou ao nível máximo!");
        }

        if (currentLevel != maxLevel) player.sendMessage("§2§l ARMAZEM §7◆ §fLimite melhorado! §7(§f " + (currentLevel) + "§7 ➝ §f" + (currentLevel + 1) + " §7)");

    }

}
