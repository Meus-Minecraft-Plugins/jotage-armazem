package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.AutoSell;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventManager {
    public static void registerEvents(JavaPlugin plugin, CactusStorageManager cactusStorageManager, CactusArmazemGUI cactusArmazemGUI, AutoSell autoSell) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new CactusDropListener(cactusStorageManager), plugin);

        pm.registerEvents(new ArmazemGuiListener(cactusStorageManager, autoSell), plugin);

        pm.registerEvents(new CactusArmazemGuiListener(cactusStorageManager, cactusArmazemGUI, autoSell), plugin);

        pm.registerEvents(new UpgradeArmazemGUIListener(cactusStorageManager, autoSell), plugin);

        pm.registerEvents(new PlayerQuitListener(autoSell), plugin);
    }
}
