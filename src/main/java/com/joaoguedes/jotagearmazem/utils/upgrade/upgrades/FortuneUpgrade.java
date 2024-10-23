package com.joaoguedes.jotagearmazem.utils.upgrade.upgrades;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeBase;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class FortuneUpgrade extends UpgradeBase {

    private final HashMap<UUID, Integer> playerFortuneValues = new HashMap<>();
    private final String upgradeType = "fortune";

    public FortuneUpgrade(UpgradeData upgradeData, int maxLevel, long inicialPrice) {
        super(upgradeData, maxLevel, inicialPrice);
    }

    public long calculatePrice(int currentLevel, long price) {
        return (price * 2) * currentLevel;
    }

    private int calculateNewFortuneValue(int currentLevel, int inicialFortune) {
        return inicialFortune + currentLevel;
    }

    public void applyFortuneUpgrade(Player player) {

        UUID playerUUID = player.getUniqueId();

        int currentLevel = upgradeData.getLevel(playerUUID);
        super.applyUpgrade(player);

        if (currentLevel != maxLevel) player.sendMessage("§2§l ARMAZEM §7◆ §fFortuna melhorada! §7(§f " + (currentLevel) + "§7 ➝ §f" + (currentLevel + 1) + " §7)");

        int newFortuneValue = calculateNewFortuneValue(currentLevel, JotageArmazem.getPluginConfig().getInt("upgrades.fortune.inicialfortunevalue"));

        if (currentLevel < maxLevel) {
            setFortuneValue(playerUUID, newFortuneValue);
        }
    }

    public int getFortuneValue(UUID playerUUID) {
        return playerFortuneValues.getOrDefault(playerUUID, JotageArmazem.getPluginConfig().getInt("upgrades.fortune.inicialfortunevalue"));
    }

    public void setFortuneValue(UUID playerUUID, int fortuneLevel) {
        playerFortuneValues.put(playerUUID, fortuneLevel);
    }

    public long getInicialPrice() {
        return inicialPrice;
    }
}
