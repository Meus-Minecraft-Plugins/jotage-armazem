package com.joaoguedes.jotagearmazem;

import com.joaoguedes.jotagearmazem.commands.CactusCommand;
import com.joaoguedes.jotagearmazem.listeners.EventManager;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Jotage_armazem extends JavaPlugin {

    @Override
    public void onEnable() {
        CactusStorageManager cactusStorageManager = new CactusStorageManager();
        CactusArmazemGUI cactusArmazemGUI = new CactusArmazemGUI(cactusStorageManager);

        if (getServer().getPluginManager().getPlugin("PlotSquared") == null) {
            getLogger().severe("PlotSquared não encontrado! O plugin será desativado.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getConsoleSender().sendMessage("§d[JotaGe-Armazem] iniciado com sucesso!");

        EventManager.registerEvents(this, cactusStorageManager, cactusArmazemGUI);

        Objects.requireNonNull(getCommand("armazem")).setExecutor(new CactusCommand(cactusStorageManager));
    }
}
