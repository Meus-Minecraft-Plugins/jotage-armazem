package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.CactusStorageManager;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventManager {
    public static void registerEvents(JavaPlugin plugin, CactusStorageManager cactusStorageManager, CactusArmazemGUI cactusArmazemGUI) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new CactusDropListener(cactusStorageManager), plugin);

        pm.registerEvents(new ArmazemGuiListener(cactusStorageManager), plugin);

        pm.registerEvents(new CactusArmazemGuiListener(cactusStorageManager), plugin);
    }
}
