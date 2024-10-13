package com.joaoguedes.jotagearmazem;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Jotage_armazem extends JavaPlugin {
    private CactusStorageManager cactusStorageManager;

    @Override
    public void onEnable() {
        cactusStorageManager = new CactusStorageManager();

        if (getServer().getPluginManager().getPlugin("PlotSquared") == null) {
            getLogger().severe("PlotSquared não encontrado! O plugin será desativado.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getConsoleSender().sendMessage("§3[JotaGe-Armazem] Enabled!");

        getServer().getPluginManager().registerEvents(new CactusDropListener(cactusStorageManager), this);
        getServer().getPluginManager().registerEvents(new CactusArmazemListener(), this);
        Objects.requireNonNull(getCommand("armazem")).setExecutor(new CactusCommand(cactusStorageManager));
    }
}
