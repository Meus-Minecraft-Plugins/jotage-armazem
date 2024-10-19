package com.joaoguedes.jotagearmazem.menus;

import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UpgradeArmazemGUI {

    private final ValorUpgrade valorUpgrade;
    private final FortuneUpgrade fortuneUpgrade;
    private final LimitUpgrade limitUpgrade;

    public UpgradeArmazemGUI(ValorUpgrade valorUpgrade, FortuneUpgrade fortuneUpgrade, LimitUpgrade limitUpgrade) {
        this.valorUpgrade = valorUpgrade;
        this.fortuneUpgrade = fortuneUpgrade;
        this.limitUpgrade = limitUpgrade;
    }

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
        // Valor Upgrade Slot
        ItemStack valorItem = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta valorMeta = valorItem.getItemMeta();
        valorMeta.setDisplayName("§dValor");

        List<String> valorLore = new ArrayList<>();

        Economy economy = JotageArmazem.getEconomy();

        valorLore.add("§7Aumenta o valor unitário");
        valorLore.add("§7de cada cacto.");
        valorLore.add("");
        valorLore.add("§fNível ≻ §7" + valorUpgrade.getCurrentLevel(playerUUID) + "/" + economy.format(valorUpgrade.getMaxLevel()));
        valorLore.add("§fValor ≻ §7" + economy.format(valorUpgrade.getCactusValue(playerUUID)));
        valorLore.add("");
        valorLore.add("§a➜ §fPreço ≻ §e" + economy.format(valorUpgrade.getCurrentPrice(playerUUID)));
        valorLore.add("");
        valorLore.add("§a▎ Clique para evoluir!");

        valorMeta.setLore(valorLore);
        valorItem.setItemMeta(valorMeta);

        return valorItem;
    }

    public ItemStack createFortunaItem(UUID playerUUID) {
        // Valor Upgrade Slot
        ItemStack fortunaItem = new ItemStack(Material.EMERALD, 1);
        ItemMeta fortunaMeta = fortunaItem.getItemMeta();
        fortunaMeta.setDisplayName("§dFortuna");

        List<String> fortunaLore = new ArrayList<>();

        Economy economy = JotageArmazem.getEconomy();

        fortunaLore.add("§7Multiplica o drop dos");
        fortunaLore.add("§7cactos.");
        fortunaLore.add("");
        fortunaLore.add("§fNível ≻ §7" + fortuneUpgrade.getCurrentLevel(playerUUID) + "/" + fortuneUpgrade.getMaxLevel());
        fortunaLore.add("§fFortuna ≻ §7" + fortuneUpgrade.getFortuneValue(playerUUID));
        fortunaLore.add("");
        fortunaLore.add("§a➜ §fPreço ≻ §e" + economy.format(fortuneUpgrade.getCurrentPrice(playerUUID)));
        fortunaLore.add("");
        fortunaLore.add("§a▎ Clique para evoluir!");

        fortunaMeta.setLore(fortunaLore);
        fortunaItem.setItemMeta(fortunaMeta);

        return fortunaItem;
    }

    public ItemStack createLimiteItem(UUID playerUUID) {
        // Valor Upgrade Slot
        ItemStack limiteItem = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta limiteMeta = limiteItem.getItemMeta();
        limiteMeta.setDisplayName("§dLimite");

        List<String> limiteLore = new ArrayList<>();

        Economy economy = JotageArmazem.getEconomy();

        limiteLore.add("§7Aumenta a capacidade do");
        limiteLore.add("§7armazém.");
        limiteLore.add("");
        limiteLore.add("§fNível ≻ §7" + limitUpgrade.getCurrentLevel(playerUUID) + "/" + limitUpgrade.getMaxLevel());
        limiteLore.add("§fLimite ≻ §7" + limitUpgrade.getLimitValue(playerUUID));
        limiteLore.add("");
        limiteLore.add("§a➜ §fPreço ≻ §e" + economy.format(limitUpgrade.getCurrentPrice(playerUUID)));
        limiteLore.add("");
        limiteLore.add("§a▎ Clique para evoluir!");

        limiteMeta.setLore(limiteLore);
        limiteItem.setItemMeta(limiteMeta);

        return limiteItem;
    }

    public ItemStack createBackItem() {
        // Valor Upgrade Slot
        ItemStack backItem = new ItemStack(Material.ARROW, 1);
        ItemMeta backMeta = backItem.getItemMeta();
        backMeta.setDisplayName("§cVoltar");

        List<String> backLore = new ArrayList<>();

        backLore.add("");
        backLore.add("§f▎ Clique para voltar a página!");
        backLore.add("");

        backMeta.setLore(backLore);
        backItem.setItemMeta(backMeta);

        return backItem;
    }
}
