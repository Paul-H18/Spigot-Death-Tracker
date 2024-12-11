package de.paul.deathTracker.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.paul.deathTracker.DeathTracker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableMigration {

    private HikariConfig dbConfig;

    public DatabaseTableMigration(HikariConfig dbConfig) {
        this.dbConfig = dbConfig;
        this.migrate();
    }

    private void migrate() {
        DataSource data = new HikariDataSource(this.dbConfig);

        String createTableSQL = "CREATE TABLE "+ ConfigManager.getConfigFile().get("db.table-name") + "(" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "uuid VARCHAR(255) NOT NULL, " +
                "name VARCHAR(255), " +
                "x BIGINT, " +
                "y BIGINT, " +
                "z BIGINT, " +
                "dimension VARCHAR(255), " +
                "died_at TIMESTAMP NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";


        try (Connection connection = data.getConnection();
             Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableSQL);
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Created Table " + ConfigManager.getConfigFile().get("db.table-name") + "!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
