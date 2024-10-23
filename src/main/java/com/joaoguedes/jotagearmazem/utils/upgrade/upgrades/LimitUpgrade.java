package com.joaoguedes.jotagearmazem.utils.upgrade.upgrades;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeBase;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class LimitUpgrade extends UpgradeBase {

    private final HashMap<UUID, Long> playerLimitValue = new HashMap<>();
    private final String upgradeType = "limit";

    public LimitUpgrade(UpgradeData upgradeData, int maxLevel, long inicialPrice) {
        super(upgradeData, maxLevel, inicialPrice);
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
        super.applyUpgrade(player);

        if (currentLevel != maxLevel) player.sendMessage("§2§l ARMAZEM §7◆ §fLimite melhorado! §7(§f " + (currentLevel) + "§7 ➝ §f" + (currentLevel + 1) + " §7)");

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

    public long getInicialPrice() {
        return inicialPrice;
    }
}

