package com.wirtgh.economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommand implements CommandExecutor {
    private final DatabaseManager dbManager;

    public EconomyCommand(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Команда доступна только игрокам!");
            return true;
        }

        double currentBalance = dbManager.getBalance(player.getUniqueId());
        BankGUI.openBankMenu(player, currentBalance);

        return true;
    }
}