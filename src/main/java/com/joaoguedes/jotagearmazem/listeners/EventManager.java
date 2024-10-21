package com.joaoguedes.jotagearmazem.listeners;

import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventManager {
    public static void registerEvents(JavaPlugin plugin, CactusStorageManager cactusStorageManager, ValorUpgrade valorUpgrade, FortuneUpgrade fortuneUpgrade, LimitUpgrade limitUpgrade, CactusArmazemGUI cactusArmazemGUI) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new CactusDropListener(cactusStorageManager, limitUpgrade, fortuneUpgrade), plugin);

        pm.registerEvents(new ArmazemGuiListener(cactusStorageManager, valorUpgrade, fortuneUpgrade, limitUpgrade), plugin);

        pm.registerEvents(new CactusArmazemGuiListener(cactusStorageManager, valorUpgrade, fortuneUpgrade, limitUpgrade, cactusArmazemGUI), plugin);

        pm.registerEvents(new UpgradeArmazemGUIListener(valorUpgrade, cactusStorageManager, fortuneUpgrade, limitUpgrade), plugin);
    }
}
