package com.joaoguedes.jotagearmazem.utils.upgrade.upgrades;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeBase;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeData;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class FortuneUpgrade {

    private final int inicialFortuneValue;
    private final long inicialPrice;
    private final PlayerDataManager playerDataManager;
    private final int maxLevel;

    public FortuneUpgrade(PlayerDataManager playerDataManager, int inicialFortuneValue, long inicialPrice, int maxLevel) {
        this.playerDataManager = playerDataManager;
        this.inicialFortuneValue = inicialFortuneValue;
        this.inicialPrice = inicialPrice;
        this.maxLevel = maxLevel;
    }

    public int getValue(int currentLevel) {
        return inicialFortuneValue * currentLevel;
    }

    // Calculate the price to ugprade based on currentLevel
    public long getPrice(int currentLevel) { return inicialPrice * (currentLevel * currentLevel); }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getLevel(UUID playerUUID) {
        return playerDataManager.loadFortuneLevel(playerUUID);
    }

    public void setLevel(UUID playerUUID, int level) {
        playerDataManager.setFortuneLevel(playerUUID, level);
    }

    public void upgradeFortune(Player player) {

        UUID playerUUID = player.getUniqueId();

        int currentLevel = playerDataManager.loadFortuneLevel(playerUUID);

        if (currentLevel < maxLevel) {
            playerDataManager.setFortuneLevel(playerUUID, (currentLevel + 1));
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        } else {
            player.sendMessage("§2§l ARMAZEM §cVocê chegou ao nível máximo!");
        }

        if (currentLevel != maxLevel) player.sendMessage("§2§l ARMAZEM §7◆ §fFortuna melhorada! §7(§f " + (currentLevel) + "§7 ➝ §f" + (currentLevel + 1) + " §7)");

    }
}
