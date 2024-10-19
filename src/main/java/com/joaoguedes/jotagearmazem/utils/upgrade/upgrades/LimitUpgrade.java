package com.joaoguedes.jotagearmazem.utils.upgrade.upgrades;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeBase;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class LimitUpgrade extends UpgradeBase {

    private final HashMap<UUID, Long> playerLimitValue = new HashMap<>();

    public LimitUpgrade(UpgradeData upgradeData, int maxLevel) {
        super(upgradeData, maxLevel);
    }

    public long calculatePrice(int currentLevel, long price) {
        return (price * 2) * currentLevel;
    }

    private int calculateNewLimitValue(int currentLevel, int limitValue) {
        return limitValue * (currentLevel * 10 * currentLevel);
    }

    public void applyLimitUpgrade(Player player) {

        UUID playerUUID = player.getUniqueId();
        int currentLevel = upgradeData.getLevel(playerUUID);

        upgradeData.setInicialPrice(JotageArmazem.getPluginConfig().getLong("upgrades.limit.inicialprice"));

        super.applyUpgrade(player);

        long newLimitValue = calculateNewLimitValue(currentLevel, JotageArmazem.getPluginConfig().getInt("upgrades.limit.iniciallimitvalue"));

        if (currentLevel < maxLevel) {
            setLimitValue(playerUUID, newLimitValue);
        }
    }

    public long getLimitValue(UUID playerUUID) {
        return playerLimitValue.getOrDefault(playerUUID, JotageArmazem.getPluginConfig().getLong("upgrades.limit.iniciallimitvalue"));
    }

    public void setLimitValue(UUID playerUUID, long value) {
        playerLimitValue.put(playerUUID, value);
    }
}

