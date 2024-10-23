package com.joaoguedes.jotagearmazem.utils;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CactusStorageManager {
    private final Map<UUID, Integer> cactusArmazem = new HashMap<>();
    private final PlayerDataManager playerDataManager = JotageArmazem.getInstance().getPlayerDataManager();

    public void addCactus(UUID playerUUID, int amount) {
        int currentAmount = playerDataManager.loadCactusCount(playerUUID);
        playerDataManager.saveCactusCount(playerUUID, currentAmount + amount);
    }

    public int getCactusCount(UUID playerUUID) {
        return playerDataManager.loadCactusCount(playerUUID);
    }

    public void clearCactus(UUID playerUUID) {
        playerDataManager.saveCactusCount(playerUUID, 0);
    }
}
