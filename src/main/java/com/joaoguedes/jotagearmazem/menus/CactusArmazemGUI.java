package com.joaoguedes.jotagearmazem.menus;

import com.joaoguedes.jotagearmazem.CactusStorageManager;
import com.joaoguedes.jotagearmazem.listeners.CactusArmazemGuiListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CactusArmazemGUI {
    private final CactusStorageManager cactusStorageManager;

    public CactusArmazemGUI(CactusStorageManager cactusStorageManager) {
        this.cactusStorageManager = cactusStorageManager;
    }

    public void openCactoArmazem(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);

        Inventory cactoArmazem = Bukkit.createInventory(null, 27, "Armazem de Cactos");
        cactoArmazem.setItem(11, createVenderItem(playerUUID));
        cactoArmazem.setItem(13, createUpgradeItem());
        cactoArmazem.setItem(15, createAutoSellItem());

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
        venderLore.add("§7Quantidade: §a" + cactusCount);
        int totalValue = cactusCount * 50;
        venderLore.add("§7Valor: §a" + totalValue);
        venderLore.add("");
        venderLore.add("§aClique para vender!");
        venderLore.add("");

        venderMeta.setLore(venderLore);
        venderItem.setItemMeta(venderMeta);

        return venderItem;
    }

    public ItemStack createUpgradeItem() {
        // Cactus Venda Slot
        ItemStack upgradeItem = new ItemStack(Material.REDSTONE, 1);
        ItemMeta upgradeMeta = upgradeItem.getItemMeta();
        upgradeMeta.setDisplayName("§cUpgrades");

        List<String> upgradeLore = new ArrayList<>();
        upgradeLore.add("");
        upgradeLore.add("§7Valor: §aX");
        upgradeLore.add("§7Fortuna: §aX");
        upgradeLore.add("§7Limite: §aX");
        upgradeLore.add("");
        upgradeLore.add("§aClique para gerenciar os upgrades!");
        upgradeLore.add("");
        upgradeLore.add("§c- EM BREVE -");


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
}
