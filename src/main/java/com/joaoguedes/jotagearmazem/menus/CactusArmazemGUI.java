package com.joaoguedes.jotagearmazem.menus;

import com.joaoguedes.jotagearmazem.utils.AutoSell;
import com.joaoguedes.jotagearmazem.utils.CactusStorageManager;
import com.joaoguedes.jotagearmazem.JotageArmazem;
import com.joaoguedes.jotagearmazem.utils.CustomHead;
import com.joaoguedes.jotagearmazem.utils.data.PlayerDataManager;
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

    private final ItemStack backHead = CustomHead.getCustomTextureHead(CustomHead.backHead());
    private final PlayerDataManager playerDataManager = JotageArmazem.getInstance().getPlayerDataManager();
    private final ValorUpgrade valorUpgrade = JotageArmazem.getInstance().getValorUpgrade();
    private final LimitUpgrade limitUpgrade = JotageArmazem.getInstance().getLimitUpgrade();
    private final FortuneUpgrade fortuneUpgrade = JotageArmazem.getInstance().getFortuneUpgrade();

    private final CactusStorageManager cactusStorageManager;
    private final AutoSell autoSell;

    public CactusArmazemGUI(CactusStorageManager cactusStorageManager, AutoSell autoSell) {
        this.cactusStorageManager = cactusStorageManager;
        this.autoSell = autoSell;
    }

    public void openCactoArmazem(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);

        Inventory cactoArmazem = Bukkit.createInventory(null, 27, "Armazem de Cactos");
        cactoArmazem.setItem(11, createVenderItem(playerUUID));
        cactoArmazem.setItem(13, createUpgradeItem(playerUUID));
        cactoArmazem.setItem(15, createAutoSellItem(autoSell.isAutoSellActive(player)));
        cactoArmazem.setItem(26, createBackItem());

        player.openInventory(cactoArmazem);
    }

    public ItemStack createVenderItem(UUID playerUUID) {
        // Cactus Venda Slot
        int cactusCount = playerDataManager.loadCactusCount(playerUUID);
        ItemStack venderItem = CustomHead.getCustomTextureHead(CustomHead.cactusBagHead());
        ItemMeta venderMeta = venderItem.getItemMeta();
        venderMeta.setDisplayName("§aVender");

        List<String> venderLore = new ArrayList<>();

        venderLore.add("");
        Economy economy = JotageArmazem.getEconomy();
        venderLore.add("§7Quantidade: §a" + economy.format(cactusCount));
        long totalValue = cactusCount * valorUpgrade.getValue(playerDataManager.loadValueLevel(playerUUID));
        venderLore.add("§7Valor individual: §a" + economy.format(valorUpgrade.getValue(playerDataManager.loadValueLevel(playerUUID))));
        venderLore.add("§7Valor total: §a" + economy.format(totalValue));
        venderLore.add("");
        venderLore.add("§7 ◆ §aClique para vender!");
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
        upgradeLore.add("§7Valor: §a" + economy.format(valorUpgrade.getValue(playerDataManager.loadValueLevel(playerUUID))));
        upgradeLore.add("§7Fortuna: §a" + fortuneUpgrade.getValue(fortuneUpgrade.getLevel(playerUUID)));
        upgradeLore.add("§7Limite: §a " + economy.format(limitUpgrade.getValue(limitUpgrade.getLevel(playerUUID))));
        upgradeLore.add("");
        if (playerDataManager.loadValueLevel(playerUUID) == valorUpgrade.getMaxLevel() && fortuneUpgrade.getLevel(playerUUID) == fortuneUpgrade.getMaxLevel() && limitUpgrade.getLevel(playerUUID) == limitUpgrade.getMaxLevel()) {
            upgradeMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1,true);
            upgradeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            upgradeLore.add("§6§lMAX");
            upgradeLore.add("");
        }
        upgradeLore.add("§7 ◆ §aClique para gerenciar os upgrades!");
        upgradeLore.add("");


        upgradeMeta.setLore(upgradeLore);
        upgradeItem.setItemMeta(upgradeMeta);

        return upgradeItem;
    }

    public ItemStack createAutoSellItem(Boolean isEnable) {
        // Cactus AutoSell Slot
        ItemStack autoSellItem = isEnable ? CustomHead.getCustomTextureHead(CustomHead.checkedHead()) : CustomHead.getCustomTextureHead(CustomHead.unCheckedHead());
        ItemMeta autoSellMeta = autoSellItem.getItemMeta();
        autoSellMeta.setDisplayName("§eVenda Automática");

        List<String> autoSellLore = new ArrayList<>();
        autoSellLore.add("");
        autoSellLore.add(isEnable ? "§7Status: §aON" : "§7Status: §cOFF");
        autoSellLore.add("");
        autoSellLore.add("§7 ◆ §aClique para ligar a venda automática!");
        autoSellLore.add("");

        autoSellMeta.setLore(autoSellLore);
        autoSellItem.setItemMeta(autoSellMeta);

        return autoSellItem;
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
