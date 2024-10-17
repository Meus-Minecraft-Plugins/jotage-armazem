package com.joaoguedes.jotagearmazem.utils.upgrade;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ValorUpgrade {

    private final int maxLevel;
    private final HashMap<UUID, Integer> playerLevels;
    private final HashMap<UUID, Long> playerPrices;
    private final HashMap<UUID, Long> playerCactusValues;

    public ValorUpgrade(int maxLevel, HashMap<UUID, Integer> playerLevels, HashMap<UUID, Long> playerPrices, HashMap<UUID, Integer> playerCactusValues) {
        this.maxLevel = maxLevel;
        this.playerLevels = new HashMap<>(); // Nível do Upgrade
        this.playerPrices = new HashMap<>(); // Valor pra realizar o upgrade
        this.playerCactusValues = new HashMap<>(); // Valor de venda do cacto
    }

    public long calculatePrice(int currentLevel) {
        return 10000000L * currentLevel;
    }
    public long calculateCactusValue(int currentLevel) {
        return 5000000L + (currentLevel * 5000000L);
    }


    public void applyUpgrade(Player player) {
        UUID playerUUID = player.getUniqueId();

        int currentLevel = playerLevels.getOrDefault(playerUUID, 1);

        if (currentLevel < maxLevel) {
            currentLevel++;
            long newPrice = calculatePrice(currentLevel);
            long newCactusValue = calculateCactusValue(currentLevel);

            playerLevels.put(playerUUID, currentLevel);
            playerPrices.put(playerUUID, newPrice);
            playerCactusValues.put(playerUUID, newCactusValue);

            player.sendMessage("§aSUCESSO! §fNovo nível: §6" + currentLevel);
        } else {
            player.sendMessage("§cVocẽ chegou no valor máximo!");
        }
    }


    public int getCurrentLevel(UUID playerUUID) {
        return playerLevels.getOrDefault(playerUUID, 1);
    }

    public long getCurrentPrice(UUID playerUUID) {
        return playerPrices.getOrDefault(playerUUID, 10000000L);
    }

    public long getCactusValue(UUID playerUUID) {
        return playerCactusValues.getOrDefault(playerUUID, 5000000L);
    }

    public int getMaxLevel(UUID playerUUID) {
        return maxLevel;
    }
}
