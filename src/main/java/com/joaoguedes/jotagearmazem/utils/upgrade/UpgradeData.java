package com.joaoguedes.jotagearmazem.utils.upgrade;

import java.util.HashMap;
import java.util.UUID;

public class UpgradeData {

    private final HashMap<UUID, Integer> playerLevels = new HashMap<>();
    private final HashMap<UUID, Long> playerPrices = new HashMap<>();

    public int getLevel(UUID playerUUID) {
        return playerLevels.getOrDefault(playerUUID, 1);
    }

    public void setLevel(UUID playerUUID, int level) {
        playerLevels.put(playerUUID, level);
    }

    public long getPrice(UUID playerUUID) {
        return playerPrices.getOrDefault(playerUUID, 1L);
    }

    public void setPrice(UUID playerUUID, long price) {
        playerPrices.put(playerUUID, price);
    }
}
