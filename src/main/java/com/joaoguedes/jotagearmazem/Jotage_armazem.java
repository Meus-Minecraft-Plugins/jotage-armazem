package com.joaoguedes.jotagearmazem;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Jotage_armazem extends JavaPlugin {
    private CactusStorageManager cactusStorageManager;

    @Override
    public void onEnable() {
        cactusStorageManager = new CactusStorageManager();

        getServer().getConsoleSender().sendMessage("§aJotaGe-Armazem enabled!");

        getServer().getPluginManager().registerEvents(new CactusBreakListener(cactusStorageManager), this);
        Objects.requireNonNull(getCommand("armazem")).setExecutor(new CactusCommand(cactusStorageManager));
    }
}
