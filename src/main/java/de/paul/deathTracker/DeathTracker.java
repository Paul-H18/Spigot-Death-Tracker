package de.paul.deathTracker;

import de.paul.deathTracker.eventListeners.DeathEventListener;
import de.paul.deathTracker.utils.FileHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathTracker extends JavaPlugin {

    @Override
    public void onEnable() {

        FileHandler handler = new FileHandler(getDataFolder());

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Enabled DeathTracker!");


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
