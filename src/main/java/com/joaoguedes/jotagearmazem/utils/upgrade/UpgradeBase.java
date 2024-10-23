package com.joaoguedes.jotagearmazem.utils.upgrade;

import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class UpgradeBase {
    protected final UpgradeData upgradeData;
    protected int maxLevel;
    protected long inicialPrice;

    public UpgradeBase(UpgradeData upgradeData, int maxLevel, long inicialPrice) {
        this.upgradeData = upgradeData;
        this.maxLevel = maxLevel;
        this.inicialPrice = inicialPrice;
    }

    public abstract long calculatePrice(int currentLevel, long inicialPrice);

    public void applyUpgrade(Player player) {
        UUID playerUUID = player.getUniqueId();
        int currentLevel = upgradeData.getLevel(playerUUID);

        if (currentLevel < maxLevel) {
            currentLevel++;
            long newPrice = calculatePrice(currentLevel, inicialPrice);

            upgradeData.setLevel(playerUUID, currentLevel);
            upgradeData.setPrice(playerUUID, newPrice);

        } else {
            player.sendMessage("§2§l ARMAZEM §cVocê chegou ao nível máximo!");
        }
    }

    public int getCurrentLevel(UUID playerUUID) {
        return upgradeData.getLevel(playerUUID);
    }

    public long getCurrentPrice(UUID playerUUID) {
        return upgradeData.getPrice(playerUUID);
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
