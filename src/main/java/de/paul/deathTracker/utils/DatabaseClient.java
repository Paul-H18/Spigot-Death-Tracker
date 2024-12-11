package de.paul.deathTracker.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseClient {

    private HikariConfig dbConfig;
    private FileConfiguration config;
    private String tableName;

    public DatabaseClient() {
        this.config = ConfigManager.getConfigFile();
        this.dbConfig = new HikariConfig();

        String url = "jdbc:mysql://" + config.get("db.hostname") + ":" + config.get("db.port") + "/" + config.get("db.db");

        this.dbConfig.setJdbcUrl(url);
        this.dbConfig.setUsername((String) config.get("db.user"));
        this.dbConfig.setPassword((String) config.get("db.password"));

        this.dbConfig.setMaximumPoolSize(20);
        this.dbConfig.setMinimumIdle(5);
        this.dbConfig.setIdleTimeout(40000);
        this.dbConfig.setMaxLifetime(200000);
        this.dbConfig.setConnectionTimeout(10000);

        this.tableName = (String) config.get("db.table-name");

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "DeathTracker Initialized Database!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "DeathTracker check: " + this.tableExists(this.tableName));

        if(!this.tableExists(this.tableName)) {
            DatabaseTableMigration migration = new DatabaseTableMigration(this.dbConfig);
        }

    }

    public void createDeathEntry(/*Player player, Location location*/) {
        DataSource data = new HikariDataSource(this.dbConfig);
        try (Connection connection = data.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);

            while (resultSet.next()) {
                Bukkit.getConsoleSender().sendMessage("ID: " + resultSet.getInt("id") + "\nName: " + resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLatestDeath() {
        DataSource data = new HikariDataSource(this.dbConfig);
        try (Connection connection = data.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.tableName);

            while (resultSet.next()) {
                Bukkit.getConsoleSender().sendMessage("ID: " + resultSet.getInt("id") + "\nName: " + resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean tableExists(String tableName) {
        DataSource data = new HikariDataSource(this.dbConfig);

        try(Connection c = data.getConnection()) {
            DatabaseMetaData metaData = c.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, tableName, null);

            return resultSet.next();
        } catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error while checking if Database " + tableName + " exists!");
            e.printStackTrace();
        }

        return false;
    }

}
