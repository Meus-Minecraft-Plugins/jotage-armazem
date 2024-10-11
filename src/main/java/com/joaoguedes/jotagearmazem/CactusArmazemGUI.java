package com.joaoguedes.jotagearmazem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CactusArmazemGUI {
    public void openArmazem(Player player, int storedCactus) {
        Inventory armazem = Bukkit.createInventory(null, 9, "Armazém de Cactos");

        ItemStack cactusItem = new ItemStack(Material.CACTUS, 1);
        ItemMeta meta = cactusItem.getItemMeta();
        meta.setDisplayName("§aCactos Armazenados");

        List<String> lore = new ArrayList<>();
        lore.add("§7Quantidade: §e" + storedCactus);
        meta.setLore(lore);
        cactusItem.setItemMeta(meta);

        armazem.setItem(4, cactusItem);

        player.openInventory(armazem);
    }
}
