package de.paul.deathTracker;

import de.paul.deathTracker.eventListeners.DeathEventListener;
import de.paul.deathTracker.utils.DatabaseClient;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import de.paul.deathTracker.utils.ConfigManager;


public final class DeathTracker extends JavaPlugin {

    public static DatabaseClient dbClient;

    @Override
    public void onEnable() {

        if(!ConfigManager.getConfig().exists()) {
            saveDefaultConfig();
            ConfigManager.reloadConfig();
        }

        dbClient = new DatabaseClient();

        FileConfiguration config = ConfigManager.getConfigFile();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Enabled DeathTracker!");
        Bukkit.getConsoleSender().sendMessage("Username: " + config.get("db.user"));


        loadEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void loadEvents() {
        getServer().getPluginManager().registerEvents(new DeathEventListener(), this);

    }

}
