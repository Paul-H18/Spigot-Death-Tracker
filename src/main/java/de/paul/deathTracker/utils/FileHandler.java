package de.paul.deathTracker.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;


import java.io.File;
import java.io.IOException;

public class FileHandler {

    private File dataFolder;

    public FileHandler(File dataFolder) {
        this.dataFolder = dataFolder;

        this.createJsonFile();
    }

    public void createJsonFile() {

        File json = new File(this.dataFolder, "deaths.json");

        if (!this.dataFolder.exists()) {
            if(this.dataFolder.mkdirs()) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Created DeathTracker data Folder!");
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Found DeathTracker data Folder!");
        }

        if(!json.exists()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "DeathTracker JSON not found, creating...");
            try {
                if (json.createNewFile()) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "DeathTracker JSON created!.");
                }
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "DeathTracker JSON could not be created!");
                e.printStackTrace();
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "DeathTracker JSON found!");
        }

    }

}
