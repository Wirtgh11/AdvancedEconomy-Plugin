package com.wirtgh.economy;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private DatabaseManager dbManager;

    @Override
    public void onEnable() {
        this.dbManager = new DatabaseManager(getDataFolder());

        if (getCommand("bank") != null) {
            getCommand("bank").setExecutor(new EconomyCommand(dbManager));
        }

        getLogger().info("AdvancedEconomy успешно включен!");
    }

    @Override
    public void onDisable() {
        if (dbManager != null) {
            dbManager.close();
        }
        getLogger().info("AdvancedEconomy отключен.");
    }
}