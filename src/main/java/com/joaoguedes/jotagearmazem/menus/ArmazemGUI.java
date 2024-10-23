package com.joaoguedes.jotagearmazem.menus;

import com.joaoguedes.jotagearmazem.utils.CustomHead;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ArmazemGUI {

    public void openArmazem(Player player) {
        // Criando um item das cactos
        Inventory armazem = Bukkit.createInventory(null, 27, "Armazem");

        // Criando um item das canas
        ItemStack cactusItem = CustomHead.getCustomTextureHead(CustomHead.cactusHead());
        ItemMeta cactusMeta = cactusItem.getItemMeta();
        cactusMeta.setDisplayName("§a§lArmazém de Cactus");

        List<String> cactusLore = new ArrayList<>();
        cactusLore.add("");
        cactusLore.add("§7 ◆ Clique para acessar o armazém.");
        cactusLore.add("");
        cactusMeta.setLore(cactusLore);
        cactusItem.setItemMeta(cactusMeta);

        armazem.setItem(12, cactusItem);

        // Criando um item das canas
        ItemStack caneItem = CustomHead.getCustomTextureHead(CustomHead.caneHead());
        ItemMeta caneMeta = caneItem.getItemMeta();
        caneMeta.setDisplayName("§a§lArmazém de Cana");

        List<String> caneLore = new ArrayList<>();
        caneLore.add("");
        caneLore.add("§7 ◆ Clique para acessar o armazém.");
        caneLore.add("");
        caneLore.add("§c- EM BREVE -");
        caneMeta.setLore(caneLore);
        caneItem.setItemMeta(caneMeta);

        armazem.setItem(14, caneItem);

        player.openInventory(armazem);
    }
}
