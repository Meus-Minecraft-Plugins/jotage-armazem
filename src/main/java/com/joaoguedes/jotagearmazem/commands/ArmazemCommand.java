package com.joaoguedes.jotagearmazem.commands;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.menus.ArmazemGUI;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ArmazemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        if (command.getName().equalsIgnoreCase("armazem")) {
            if (strings.length == 0) {
                Player player = (Player) commandSender;

                new ArmazemGUI().openArmazem(player);
            } else if (strings.length == 1 && strings[0].equalsIgnoreCase("reload")) {
                JotageArmazem.getInstance().reloadConfig();

                FileConfiguration config = JotageArmazem.getInstance().getConfig();
                PlayerDataManager playerDataManager = JotageArmazem.getInstance().getPlayerDataManager();

                ValorUpgrade newValorUpgrade = new ValorUpgrade(
                        playerDataManager,
                        config.getLong("upgrades.valor.inicialcactusvalue"),
                        config.getLong("upgrades.valor.inicialprice"),
                        config.getInt("upgrades.valor.maxlevel")
                );

                FortuneUpgrade newFortuneUpgrade = new FortuneUpgrade(
                        playerDataManager,
                        config.getInt("upgrades.fortune.inicialfortunevalue"),
                        config.getLong("upgrades.fortune.inicialprice"),
                        config.getInt("upgrades.fortune.maxlevel")
                );

                LimitUpgrade newLimitUpgrade = new LimitUpgrade(
                        playerDataManager,
                        config.getInt("upgrades.limit.iniciallimitvalue"),
                        config.getLong("upgrades.limit.inicialprice"),
                        config.getInt("upgrades.limit.maxlevel")
                );

                JotageArmazem.getInstance().setValorUpgrade(newValorUpgrade);
                JotageArmazem.getInstance().setFortuneUpgrade(newFortuneUpgrade);
                JotageArmazem.getInstance().setLimitUpgrade(newLimitUpgrade);

                commandSender.sendMessage("§aConfiguração do plugin recarregada com sucesso!");
                return true;
            }
        }

    return true;
    }
}
