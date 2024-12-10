package de.paul.deathTracker.eventListeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEventListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        p.sendMessage(ChatColor.GREEN + "Haha you died! ~Marz");

        Bukkit.getConsoleSender().sendMessage(p.getName() + " died!");
    }
}
