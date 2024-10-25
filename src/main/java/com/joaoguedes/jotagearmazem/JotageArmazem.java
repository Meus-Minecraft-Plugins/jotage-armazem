package com.joaoguedes.jotagearmazem;

import com.joaoguedes.jotagearmazem.commands.ArmazemCommand;
import com.joaoguedes.jotagearmazem.listeners.EventManager;
import com.joaoguedes.jotagearmazem.menus.CactusArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.AutoSell;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
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
    private FileConfiguration config;
    private static JotageArmazem instance;
    private PlayerDataManager playerDataManager;
    private ValorUpgrade valorUpgrade;
    private LimitUpgrade limitUpgrade;
    private FortuneUpgrade fortuneUpgrade;
    private int cactusToAdd;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = this.getConfig();
        instance = this;

        playerDataManager = new PlayerDataManager(getDataFolder());
        valorUpgrade = new ValorUpgrade(playerDataManager, config.getLong("upgrades.valor.inicialcactusvalue"), config.getLong("upgrades.valor.inicialprice"), config.getInt("upgrades.valor.maxlevel"));
        fortuneUpgrade = new FortuneUpgrade(playerDataManager, config.getInt("upgrades.fortune.inicialfortunevalue"), config.getLong("upgrades.fortune.inicialprice"), config.getInt("upgrades.fortune.maxlevel"));
        limitUpgrade = new LimitUpgrade(playerDataManager, config.getInt("upgrades.limit.iniciallimitvalue"), config.getLong("upgrades.limit.inicialprice"), config.getInt("upgrades.limit.maxlevel"));

        CactusStorageManager cactusStorageManager = new CactusStorageManager();
        AutoSell autoSell = new AutoSell();
        CactusArmazemGUI cactusArmazemGUI = new CactusArmazemGUI(cactusStorageManager, autoSell);

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

        Objects.requireNonNull(getCommand("armazem")).setExecutor(new ArmazemCommand());

        EventManager.registerEvents(this, cactusStorageManager, cactusArmazemGUI, autoSell);


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

    public FileConfiguration getPluginConfig() {
        return config;
    }

    public static JotageArmazem getInstance() { return instance; }

    public PlayerDataManager getPlayerDataManager() { return playerDataManager; }

    public ValorUpgrade getValorUpgrade() { return valorUpgrade; }

    public void setValorUpgrade(ValorUpgrade valorUpgrade) {
        this.valorUpgrade = valorUpgrade;
    }

    public FortuneUpgrade getFortuneUpgrade() { return fortuneUpgrade; }

    public void setFortuneUpgrade(FortuneUpgrade fortuneUpgrade) { this.fortuneUpgrade = fortuneUpgrade; }

    public LimitUpgrade getLimitUpgrade() { return limitUpgrade; }

    public void setLimitUpgrade(LimitUpgrade limitUpgrade) { this.limitUpgrade = limitUpgrade; }
}
