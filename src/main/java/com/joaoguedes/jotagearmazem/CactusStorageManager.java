package com.joaoguedes.jotagearmazem;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CactusStorageManager {
    private Map<Player, Integer> cactusArmazem = new HashMap<>();

    public void addCactus(Player player, int amount) {
        int currentAmount = cactusArmazem.getOrDefault(player, 0);
        cactusArmazem.put(player, currentAmount + amount);
    }

    public int getCactusCount(Player player) {
        return cactusArmazem.getOrDefault(player, 0);
    }

    public void clearCactus(Player player) {
        cactusArmazem.put(player, 0);
    }
}
