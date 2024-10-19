package com.joaoguedes.jotagearmazem;

import com.joaoguedes.jotagearmazem.commands.CactusCommand;
import com.joaoguedes.jotagearmazem.listeners.EventManager;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.UpgradeData;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class JotageArmazem extends JavaPlugin {
    private static Economy economy = null;
    private static FileConfiguration config;

    @Override
    public void onEnable() {
        config = this.getConfig();

        CactusStorageManager cactusStorageManager = new CactusStorageManager();
        ValorUpgrade valorUpgrade = new ValorUpgrade(new UpgradeData(), config.getInt("upgrades.valor.maxlevel"));
        FortuneUpgrade fortuneUpgrade = new FortuneUpgrade(new UpgradeData(), config.getInt("upgrades.fortune.maxlevel"));
        LimitUpgrade limitUpgrade = new LimitUpgrade(new UpgradeData(), config.getInt("upgrades.limit.maxlevel"));
        CactusArmazemGUI cactusArmazemGUI = new CactusArmazemGUI(cactusStorageManager, valorUpgrade, fortuneUpgrade, limitUpgrade);

        if (!setupEconomy()) {
            getLogger().severe("Dependência do Vault não encontrada! O plugin será desativado.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getServer().getPluginManager().getPlugin("PlotSquared") == null) {
            getLogger().severe("PlotSquared não encontrado! O plugin será desativado.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getConsoleSender().sendMessage("§d[JotaGe-Armazem] iniciado com sucesso!");

        EventManager.registerEvents(this, cactusStorageManager, valorUpgrade, fortuneUpgrade, limitUpgrade);

        Objects.requireNonNull(getCommand("armazem")).setExecutor(new CactusCommand());

        saveDefaultConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        economy = rsp.getProvider();
        return economy != null;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static FileConfiguration getPluginConfig() {
        return config;
    }
}
