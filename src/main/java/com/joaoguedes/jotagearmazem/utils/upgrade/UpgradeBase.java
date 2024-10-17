package com.joaoguedes.jotagearmazem.utils.upgrade;

import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class UpgradeBase {
    protected final UpgradeData upgradeData;
    protected int maxLevel;

    public UpgradeBase(UpgradeData upgradeData, int maxLevel) {
        this.upgradeData = upgradeData;
        this.maxLevel = maxLevel;
    }

    public abstract long calculatePrice(int currentLevel, long price);

    public void applyUpgrade(Player player) {
        UUID playerUUID = player.getUniqueId();
        int currentLevel = upgradeData.getLevel(playerUUID);
        long inicialPrice = upgradeData.getInicialPrice();

        if (currentLevel < maxLevel) {
            currentLevel++;
            long newPrice = calculatePrice(currentLevel, inicialPrice);

            upgradeData.setLevel(playerUUID, currentLevel);
            upgradeData.setPrice(playerUUID, newPrice);

            player.sendMessage("§aSUCESSO! §fNovo nível: §6" + currentLevel);
        } else {
            player.sendMessage("§cVocê chegou ao nível máximo!");
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
