package ro.kmagic.support.legacy;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.bukkit.entity.Player;
import ro.kmagic.libapi.utils.CustomError;
import java.util.Arrays;
import java.sql.DriverManager;
import org.bukkit.Bukkit;
import java.sql.Driver;
import java.io.File;
import java.sql.Connection;
import java.util.Collections;

public class SQLite {
    private Connection connection;
    private static SQLite instance;
    
    public static SQLite getInstance() {
        return SQLite.instance;
    }
    
    private void connect() {
        final File obj = new File("plugins/ThePit/", "ThePitProfiles.db");
        if (!obj.exists()) {
            return;
        }
        if (this.connected()) {
            return;
        }
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                return;
            }
            DriverManager.registerDriver((Driver)Bukkit.getServer().getClass().getClassLoader().loadClass("org.sqlite.JDBC").newInstance());
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + obj);
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Cannot create an instance of the legacy database.", "In case that this error doesn't disappear, your player accounts might me corrupted."));
        }
    }
    
    public static boolean legacyExists() {
        return new File("plugins/ThePit/", "ThePitProfiles.db").exists();
    }
    
    private boolean connected() {
        if (this.connection == null) {
            return false;
        }
        try {
            return !this.connection.isClosed();
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't retrieve the status of the legacy database."));
            return false;
        }
    }
    
    public void close() {
        if (!this.connected()) {
            return;
        }
        try {
            this.connection.close();
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't close the legacy database connection."));
        }
    }
    
    public ResultSet getResultSet(final Player player, final String str) {
        if (!this.connected()) {
            this.connect();
        }
        try {
            final ResultSet executeQuery = this.connection.prepareStatement("SELECT * FROM " + str + " WHERE UUID = '" + player.getUniqueId() + "';").executeQuery();
            if (executeQuery.next()) {
                return executeQuery;
            }
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't get ResultSet from the legacy database."));
        }
        return null;
    }
    
    public int getInt(final Player player, final String str, final String str2) {
        if (!this.connected()) {
            this.connect();
        }
        try {
            final ResultSet executeQuery = this.connection.prepareStatement("SELECT " + str + " FROM " + str2 + " WHERE UUID = '" + player.getUniqueId() + "';").executeQuery();
            if (executeQuery.next()) {
                return executeQuery.getInt(str);
            }
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't get Integer from the legacy database."));
        }
        return 0;
    }
    
    public String getString(final Player player, final String str, final String str2) {
        if (!this.connected()) {
            this.connect();
        }
        try {
            final ResultSet executeQuery = this.connection.prepareStatement("SELECT " + str + " FROM " + str2 + " WHERE UUID = '" + player.getUniqueId() + "';").executeQuery();
            if (executeQuery.next()) {
                return executeQuery.getString(str);
            }
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't get String from the legacy database."));
        }
        return "";
    }
    
    public boolean hasAccount(final Player player, final String str) {
        if (!this.connected()) {
            this.connect();
        }
        try {
            final PreparedStatement prepareStatement = this.connection.prepareStatement("SELECT UUID FROM " + str + " WHERE UUID = '" + player.getUniqueId() + "';");
            return prepareStatement != null && prepareStatement.executeQuery().next();
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't get account from the legacy database."));
            return false;
        }
    }
    
    public boolean contains(final Player player, final String str) {
        if (!this.connected()) {
            this.connect();
        }
        if (!this.hasAccount(player, str)) {
            return false;
        }
        try {
            return this.connection.prepareStatement("SELECT * FROM " + str + " WHERE UUID = '" + player.getUniqueId() + "';").executeQuery().next();
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't get info from the legacy database."));
            return false;
        }
    }
    
    public void deletePlayer(final Player player, final String str) {
        if (!this.connected()) {
            this.connect();
        }
        try {
            this.connection.prepareStatement("DELETE FROM " + str + " WHERE UUID = '" + player.getUniqueId() + "';").execute();
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't delete player from the legacy database."));
        }
    }
    
    static {
        SQLite.instance = new SQLite();
    }
}
