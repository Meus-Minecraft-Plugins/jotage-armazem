package com.joaoguedes.jotagearmazem.utils.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataManager {

    private final File playerDataFolder;

    public PlayerDataManager(File pluginFolder) {
        this.playerDataFolder = new File(pluginFolder, "playerdata");
        if (!playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
        }
    }

    public void saveCactusCount(UUID playerUUID, int cactusCount) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        playerData.set("cactusCount", cactusCount);

        try {
            playerData.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadCactusCount(UUID playerUUID) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        if (!playerFile.exists()) {
            return 0;
        }

        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        return playerData.getInt("cactusCount", 0);
    }

    public void setValueLevel(UUID playerUUID, int valueLevel) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        playerData.set("valuelevel", valueLevel);

        try {
            playerData.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadValueLevel(UUID playerUUID) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        if (!playerFile.exists()) {
            return 1;
        }

        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        return playerData.getInt("valuelevel", 1);
    }

    public void setLimitLevel(UUID playerUUID, int valueLevel) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        playerData.set("limitlevel", valueLevel);

        try {
            playerData.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadLimitLevel(UUID playerUUID) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        if (!playerFile.exists()) {
            return 1;
        }

        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        return playerData.getInt("limitlevel", 1);
    }

    public void setFortuneLevel(UUID playerUUID, int valueLevel) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        playerData.set("fortunelevel", valueLevel);

        try {
            playerData.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadFortuneLevel(UUID playerUUID) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        if (!playerFile.exists()) {
            return 1;
        }

        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        return playerData.getInt("fortunelevel", 1);
    }
}