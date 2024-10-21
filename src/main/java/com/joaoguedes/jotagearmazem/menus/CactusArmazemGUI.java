package com.joaoguedes.jotagearmazem.menus;

import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.FortuneUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.LimitUpgrade;
import com.joaoguedes.jotagearmazem.utils.upgrade.upgrades.ValorUpgrade;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CactusArmazemGUI {
    private final CactusStorageManager cactusStorageManager;
    private final ValorUpgrade valorUpgrade;
    private final FortuneUpgrade fortuneUpgrade;
    private final LimitUpgrade limitUpgrade;

    public CactusArmazemGUI(CactusStorageManager cactusStorageManager, ValorUpgrade valorUpgrade, FortuneUpgrade fortuneUpgrade, LimitUpgrade limitUpgrade) {
        this.cactusStorageManager = cactusStorageManager;
        this.valorUpgrade = valorUpgrade;
        this.fortuneUpgrade = fortuneUpgrade;
        this.limitUpgrade = limitUpgrade;
    }

    public void openCactoArmazem(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);

        Inventory cactoArmazem = Bukkit.createInventory(null, 27, "Armazem de Cactos");
        cactoArmazem.setItem(11, createVenderItem(playerUUID));
        cactoArmazem.setItem(13, createUpgradeItem(playerUUID));
        cactoArmazem.setItem(15, createAutoSellItem());
        cactoArmazem.setItem(26, createBackItem());

        player.openInventory(cactoArmazem);
    }

    public ItemStack createVenderItem(UUID playerUUID) {
        // Cactus Venda Slot
        int cactusCount = cactusStorageManager.getCactusCount(playerUUID);
        ItemStack venderItem = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
        ItemMeta venderMeta = venderItem.getItemMeta();
        venderMeta.setDisplayName("§aVender");

        List<String> venderLore = new ArrayList<>();

        venderLore.add("");
        Economy economy = JotageArmazem.getEconomy();
        venderLore.add("§7Quantidade: §a" + economy.format(cactusCount));
        long totalValue = cactusCount * valorUpgrade.getCactusValue(playerUUID) * fortuneUpgrade.getFortuneValue(playerUUID);
        venderLore.add("§7Valor individual: §a" + economy.format(valorUpgrade.getCactusValue(playerUUID)));
        venderLore.add("§7Valor total: §a" + economy.format(totalValue));
        venderLore.add("");
        venderLore.add("§aClique para vender!");
        venderLore.add("");

        venderMeta.setLore(venderLore);
        venderItem.setItemMeta(venderMeta);

        return venderItem;
    }

    public ItemStack createUpgradeItem(UUID playerUUID) {
        // Cactus Venda Slot
        ItemStack upgradeItem = new ItemStack(Material.REDSTONE, 1);
        ItemMeta upgradeMeta = upgradeItem.getItemMeta();
        upgradeMeta.setDisplayName("§cUpgrades");

        Economy economy = JotageArmazem.getEconomy();

        List<String> upgradeLore = new ArrayList<>();
        upgradeLore.add("");
        upgradeLore.add("§7Valor: §a" + economy.format(valorUpgrade.getCactusValue(playerUUID)));
        upgradeLore.add("§7Fortuna: §a" + fortuneUpgrade.getFortuneValue(playerUUID));
        upgradeLore.add("§7Limite: §a " + economy.format(limitUpgrade.getLimitValue(playerUUID)));
        upgradeLore.add("");
        if (valorUpgrade.getCurrentLevel(playerUUID) == JotageArmazem.getPluginConfig().getInt("upgrades.valor.maxlevel") && fortuneUpgrade.getCurrentLevel(playerUUID) == JotageArmazem.getPluginConfig().getInt("upgrades.fortune.maxlevel") && limitUpgrade.getCurrentLevel(playerUUID) == JotageArmazem.getPluginConfig().getInt("upgrades.limit.maxlevel")) {
            upgradeMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1,true);
            upgradeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            upgradeLore.add("§6§lMAX");
        }
        upgradeLore.add("");
        upgradeLore.add("§aClique para gerenciar os upgrades!");
        upgradeLore.add("");


        upgradeMeta.setLore(upgradeLore);
        upgradeItem.setItemMeta(upgradeMeta);

        return upgradeItem;
    }

    public ItemStack createAutoSellItem() {
        // Cactus AutoSell Slot
        ItemStack autoSellItem = new ItemStack(Material.LEVER, 1);
        ItemMeta autoSellMeta = autoSellItem.getItemMeta();
        autoSellMeta.setDisplayName("§eVenda Automática");

        List<String> autoSellLore = new ArrayList<>();
        autoSellLore.add("");
        autoSellLore.add("§7Status: §cOFF");
        autoSellLore.add("");
        autoSellLore.add("§aClique para ligar a venda automática!");
        autoSellLore.add("");
        autoSellLore.add("§c- EM BREVE -");


        autoSellMeta.setLore(autoSellLore);
        autoSellItem.setItemMeta(autoSellMeta);

        return autoSellItem;
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
