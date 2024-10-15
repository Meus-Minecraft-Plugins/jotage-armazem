package com.joaoguedes.jotagearmazem.menus;

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
        // Criando a GUI principal do armazém com 27 slots
        Inventory armazem = Bukkit.createInventory(null, 27, "Armazem");

        // Criando um item representando os cactos
        ItemStack cactusItem = new ItemStack(Material.CACTUS, 1);
        ItemMeta cactusMeta = cactusItem.getItemMeta();
        cactusMeta.setDisplayName("§aArmazém de Cactos");

        List<String> cactusLore = new ArrayList<>();
        cactusLore.add("");
        cactusLore.add("§7Clique para acessar o armazém de cactos");
        cactusLore.add("");
        cactusMeta.setLore(cactusLore);
        cactusItem.setItemMeta(cactusMeta);

        // Definindo o item no slot 12
        armazem.setItem(12, cactusItem);

        // Criando um item representando as canas
        ItemStack caneItem = new ItemStack(Material.SUGAR_CANE, 1);
        ItemMeta caneMeta = caneItem.getItemMeta();
        caneMeta.setDisplayName("§aArmazém de Cana");

        List<String> caneLore = new ArrayList<>();
        caneLore.add("");
        caneLore.add("§7Clique para acessar o armazém das canas!");
        caneLore.add("");
        caneLore.add("§c- EM BREVE -");
        caneMeta.setLore(caneLore);
        caneItem.setItemMeta(caneMeta);

        // Definindo o item no slot 14
        armazem.setItem(14, caneItem);

        // Exibindo a GUI para o jogador
        player.openInventory(armazem);
    }
}
