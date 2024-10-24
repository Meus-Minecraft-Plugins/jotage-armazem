package com.joaoguedes.jotagearmazem.utils.upgrade.upgrades;

import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ValorUpgrade {

    private final long inicialCactusValue;
    private final long inicialPrice;
    private final PlayerDataManager playerDataManager;
    private final int maxLevel;

    public ValorUpgrade(PlayerDataManager playerDataManager, long inicialCactusValue, long inicialPrice, int maxLevel) {
        this.playerDataManager = playerDataManager;
        this.inicialCactusValue = inicialCactusValue;
        this.inicialPrice = inicialPrice;
        this.maxLevel = maxLevel;
    }

    public long getValue(int currentLevel) { return inicialCactusValue * currentLevel; }

    public long getPrice(int currentLevel) {
        return inicialPrice * (currentLevel * currentLevel);
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void upgradeValue(Player player) {

        UUID playerUUID = player.getUniqueId();

        int currentLevel = playerDataManager.loadValueLevel(playerUUID);

        if (currentLevel < maxLevel) {
            playerDataManager.setValueLevel(playerUUID, (currentLevel + 1));
        } else {
            player.sendMessage("§2§l ARMAZEM §cVocê chegou ao nível máximo!");
        }

        if (currentLevel != maxLevel) player.sendMessage("§2§l ARMAZEM §7◆ §fValor melhorado! §7(§f " + (currentLevel) + "§7 ➝ §f" + (currentLevel + 1) + " §7)");

    }

}
