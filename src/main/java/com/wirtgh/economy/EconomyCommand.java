package com.wirtgh.economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class BankGUI {

    public static void openBankMenu(Player player, double balance) {
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "💰 Ваш Банковский Счёт");

        ItemStack balanceItem = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = balanceItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Текущий баланс");
            meta.setLore(Collections.singletonList(ChatColor.YELLOW + "Баланс: " + ChatColor.GREEN + "$" + balance));
            balanceItem.setItemMeta(meta);
        }

        gui.setItem(13, balanceItem);
        player.openInventory(gui);
    }
}