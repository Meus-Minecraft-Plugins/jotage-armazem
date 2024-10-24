package com.joaoguedes.jotagearmazem.menus;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.CustomHead;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UpgradeArmazemGUI {

    private final ItemStack backHead = CustomHead.getCustomTextureHead(CustomHead.backHead());
    private final PlayerDataManager playerDataManager = JotageArmazem.getInstance().getPlayerDataManager();

    public void openUpgradeArmazemGUI(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);

        Inventory upgradeArmazem = Bukkit.createInventory(null, 27, "Upgrade de Cactos");
        upgradeArmazem.setItem(11, createValorItem(playerUUID));
        upgradeArmazem.setItem(13, createFortunaItem(playerUUID));
        upgradeArmazem.setItem(15, createLimiteItem(playerUUID));
        upgradeArmazem.setItem(26, createBackItem());

        player.openInventory(upgradeArmazem);
    }

    public ItemStack createValorItem(UUID playerUUID) {
        ValorUpgrade valorUpgrade2 = JotageArmazem.getInstance().getValorUpgrade();

        // Valor Upgrade Slot
        ItemStack valorItem = CustomHead.getCustomTextureHead(CustomHead.valueHead());
        ItemMeta valorMeta = valorItem.getItemMeta();
        valorMeta.setDisplayName("§dValor");

        List<String> valorLore = new ArrayList<>();

        Economy economy = JotageArmazem.getEconomy();

        valorLore.add("§7Aumenta o valor unitário");
        valorLore.add("§7de cada cacto.");
        valorLore.add("");
        valorLore.add("§f Nível ≻ §7" + playerDataManager.loadValueLevel(playerUUID) + "/" + valorUpgrade2.getMaxLevel());
        valorLore.add("§f Valor ≻ §7" + economy.format(valorUpgrade2.getValue(playerDataManager.loadValueLevel(playerUUID))));
        valorLore.add("");
        if (playerDataManager.loadValueLevel(playerUUID) == valorUpgrade2.getMaxLevel()) {
            valorLore.add("§a➜ §fNível ≻ §6§lMAX");
        } else {
            valorLore.add("§a➜ §fPreço ≻ §e" + economy.format(valorUpgrade2.getPrice(playerDataManager.loadValueLevel(playerUUID))));
        }
        valorLore.add("");
        valorLore.add("§7 ● §aClique para evoluir!");
        valorLore.add("");

        valorMeta.setLore(valorLore);
        valorItem.setItemMeta(valorMeta);

        return valorItem;
    }

    public ItemStack createFortunaItem(UUID playerUUID) {
        FortuneUpgrade fortuneUpgrade = JotageArmazem.getInstance().getFortuneUpgrade();

        // Valor Upgrade Slot
        ItemStack fortunaItem = CustomHead.getCustomTextureHead(CustomHead.fortuneHead());
        ItemMeta fortunaMeta = fortunaItem.getItemMeta();
        fortunaMeta.setDisplayName("§dFortuna");

        List<String> fortunaLore = new ArrayList<>();

        Economy economy = JotageArmazem.getEconomy();

        fortunaLore.add("§7Multiplica o drop dos");
        fortunaLore.add("§7cactos.");
        fortunaLore.add("");
        fortunaLore.add("§f Nível ≻ §7" + fortuneUpgrade.getLevel(playerUUID) + "/" + fortuneUpgrade.getMaxLevel());
        fortunaLore.add("§f Fortuna ≻ §7" + fortuneUpgrade.getValue(fortuneUpgrade.getLevel(playerUUID)));
        fortunaLore.add("");
        if (fortuneUpgrade.getLevel(playerUUID) == fortuneUpgrade.getMaxLevel()) {
            fortunaLore.add("§a➜ §fNível ≻ §6§lMAX");
        } else {
            fortunaLore.add("§a➜ §fPreço ≻ §e" + economy.format(fortuneUpgrade.getPrice(fortuneUpgrade.getLevel(playerUUID))));
        }
        fortunaLore.add("");
        fortunaLore.add("§7 ● §aClique para evoluir!");
        fortunaLore.add("");

        fortunaMeta.setLore(fortunaLore);
        fortunaItem.setItemMeta(fortunaMeta);

        return fortunaItem;
    }

    public ItemStack createLimiteItem(UUID playerUUID) {
        LimitUpgrade limitUpgrade = JotageArmazem.getInstance().getLimitUpgrade();

        // Valor Upgrade Slot
        ItemStack limiteItem = CustomHead.getCustomTextureHead(CustomHead.limitHead());
        ItemMeta limiteMeta = limiteItem.getItemMeta();
        limiteMeta.setDisplayName("§dLimite");

        List<String> limiteLore = new ArrayList<>();

        Economy economy = JotageArmazem.getEconomy();

        limiteLore.add("§7Aumenta a capacidade do");
        limiteLore.add("§7armazém.");
        limiteLore.add("");
        limiteLore.add("§f Nível ≻ §7" + limitUpgrade.getLevel(playerUUID) + "/" + limitUpgrade.getMaxLevel());
        limiteLore.add("§f Limite ≻ §7" + limitUpgrade.getValue(limitUpgrade.getLevel(playerUUID)));
        limiteLore.add("");
        if (limitUpgrade.getLevel(playerUUID) == limitUpgrade.getMaxLevel()) {
            limiteLore.add("§a➜ §fNível ≻ §6§lMAX");
        } else {
            limiteLore.add("§a➜ §fPreço ≻ §e" + economy.format(limitUpgrade.getPrice(limitUpgrade.getLevel(playerUUID))));
        }
        limiteLore.add("");
        limiteLore.add("§7 ● §aClique para evoluir!");
        limiteLore.add("");

        limiteMeta.setLore(limiteLore);
        limiteItem.setItemMeta(limiteMeta);

        return limiteItem;
    }

    public ItemStack createBackItem() {
        // Valor Upgrade Slot
        ItemStack backItem = backHead;
        ItemMeta backMeta = backItem.getItemMeta();
        backMeta.setDisplayName(" ");

        List<String> backLore = new ArrayList<>();

        backLore.add("§7 ● §cVOLTAR §7●");
        backLore.add("");

        backMeta.setLore(backLore);
        backItem.setItemMeta(backMeta);

        return backItem;
    }
}
