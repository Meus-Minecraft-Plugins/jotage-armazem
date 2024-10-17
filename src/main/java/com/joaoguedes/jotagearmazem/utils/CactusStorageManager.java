package com.joaoguedes.jotagearmazem.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CactusStorageManager {
    private final Map<UUID, Integer> cactusArmazem = new HashMap<>();

    public void addCactus(UUID playerUUID, int amount) {
        int currentAmount = cactusArmazem.getOrDefault(playerUUID, 0);
        cactusArmazem.put(playerUUID, currentAmount + amount);
    }

    public int getCactusCount(UUID playerUUID) {
        return cactusArmazem.getOrDefault(playerUUID, 0);
    }
    public void clearCactus(UUID playerUUID) {
        cactusArmazem.put(playerUUID, 0);
    }
}
