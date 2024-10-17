package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.upgrade.ValorUpgrade;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class EventManager {
    public static void registerEvents(JavaPlugin plugin, CactusStorageManager cactusStorageManager, CactusArmazemGUI cactusArmazemGUI, ValorUpgrade valorUpgrade) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new CactusDropListener(cactusStorageManager), plugin);

        pm.registerEvents(new ArmazemGuiListener(cactusStorageManager, valorUpgrade), plugin);

        pm.registerEvents(new CactusArmazemGuiListener(cactusStorageManager, valorUpgrade), plugin);

        pm.registerEvents(new UpgradeArmazemGUIListener((valorUpgrade)), plugin);
    }
}
