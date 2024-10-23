package com.joaoguedes.jotagearmazem.utils.upgrade.upgrades;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeBase;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ValorUpgrade extends UpgradeBase {

    private final HashMap<UUID, Long> playerCactusValues = new HashMap<>();
    private final String upgradeType = "valor";

    public ValorUpgrade(UpgradeData upgradeData, int maxLevel, long inicialprice) {
        super(upgradeData, maxLevel, inicialprice);
    }

    public long calculatePrice(int currentLevel, long price) {
        return (price * 2) * currentLevel;
    }

    private long calculateNewCactusValue(int currentLevel, long cactusValue) {
        return cactusValue + (currentLevel * cactusValue);
    }

    public void applyValorUpgrade(Player player) {

        UUID playerUUID = player.getUniqueId();

        int currentLevel = upgradeData.getLevel(playerUUID);
        super.applyUpgrade(player);

        player.sendMessage("§2§l ARMAZEM §7◆ §fValor melhorado! §7(§f " + (currentLevel) + "§7 ➝ §f" + (currentLevel + 1) + " §7)");

        long newCactusValue = calculateNewCactusValue(currentLevel, JotageArmazem.getPluginConfig().getLong("upgrades.valor.inicialcactusvalue"));

        if (currentLevel < maxLevel) {
            setCactusValue(playerUUID, newCactusValue);
        }
    }

    public long getCactusValue(UUID playerUUID) {
        return playerCactusValues.getOrDefault(playerUUID, JotageArmazem.getPluginConfig().getLong("upgrades.valor.inicialcactusvalue"));
    }

    public void setCactusValue(UUID playerUUID, long value) {
        playerCactusValues.put(playerUUID, value);
    }

    public long getInicialPrice() {
        return inicialPrice;
    }

}
