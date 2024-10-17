package com.joaoguedes.jotagearmazem.menus;

import com.joaoguedes.jotagearmazem.Jotage_armazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.ValorUpgrade;
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

    public UpgradeArmazemGUI(ValorUpgrade valorUpgrade) {
        this.valorUpgrade = valorUpgrade;
    }

    public void openUpgradeArmazemGUI(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);

        Inventory upgradeArmazem = Bukkit.createInventory(null, 27, "Upgrade de Cactos");
        upgradeArmazem.setItem(11, createValorItem(playerUUID));
        upgradeArmazem.setItem(13, createFortunaItem(playerUUID));
        upgradeArmazem.setItem(15, createLimiteItem(playerUUID));

        player.openInventory(upgradeArmazem);
    }

    public ItemStack createValorItem(UUID playerUUID) {
        // Valor Upgrade Slot
        ItemStack valorItem = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta valorMeta = valorItem.getItemMeta();
        valorMeta.setDisplayName("§dValor");

        List<String> valorLore = new ArrayList<>();

        Economy economy = Jotage_armazem.getEconomy();

        valorLore.add("§7Aumenta o valor unitário");
        valorLore.add("§7de cada cacto.");
        valorLore.add("");
        valorLore.add("§fNível ≻ §7" + valorUpgrade.getCurrentLevel(playerUUID) + "/" + economy.format(valorUpgrade.getMaxLevel(playerUUID)));
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

        fortunaLore.add("§7Multiplica o drop dos");
        fortunaLore.add("§7cactos.");
        fortunaLore.add("");
        fortunaLore.add("§fNível ≻ §71/100");
        fortunaLore.add("§fFortuna ≻ §71x");
        fortunaLore.add("");
        fortunaLore.add("§a➜ §fPreço ≻ §e10M");
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

        limiteLore.add("§7Aumenta a capacidade do");
        limiteLore.add("§7armazém.");
        limiteLore.add("");
        limiteLore.add("§fNível ≻ §71/100");
        limiteLore.add("§fLimite ≻ §75K");
        limiteLore.add("");
        limiteLore.add("§a➜ §fPreço ≻ §e10M");
        limiteLore.add("");
        limiteLore.add("§a▎ Clique para evoluir!");

        limiteMeta.setLore(limiteLore);
        limiteItem.setItemMeta(limiteMeta);

        return limiteItem;
    }
}
