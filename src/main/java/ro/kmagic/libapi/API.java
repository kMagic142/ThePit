package ro.kmagic.libapi;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import ro.kmagic.libapi.utils.AnvilUtil;
import ro.kmagic.libapi.database.FlatFile;
import ro.kmagic.libapi.database.MySQL;
import ro.kmagic.libapi.database.DatabaseLog;
import org.bukkit.event.Listener;
import ro.kmagic.libapi.utils.Assist;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.versionsupport.v1_8_r3.v1_8_R3;
import org.bukkit.Bukkit;
import ro.kmagic.libapi.exceptions.InitializationException;
import ro.kmagic.libapi.database.Database;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import org.bukkit.plugin.java.JavaPlugin;

public class API
{
    private static JavaPlugin plugin;
    private static VersionSupport versionSupport;
    private static Database database;
    private static String version;
    private static boolean onlyFlatFile;
    
    public API(final JavaPlugin plugin) {
        if (plugin == null) {
            throw new InitializationException("The plugin is null.");
        }
        if (API.plugin == null) {
            API.plugin = plugin;
            try {
                Class.forName("org.spigotmc.SpigotConfig");
            }
            catch (ClassNotFoundException ex) {
                throw new InitializationException("api can run only on &cSpigot &eor &cPaperSpigot");
            }
            API.version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            API.versionSupport = new v1_8_R3();

            Materials.createCache();
            getVersionSupport().registerPlayerPickup();
            Bukkit.getPluginManager().registerEvents(new Assist(), getPlugin());
            return;
        }
        throw new InitializationException("API is already initialized.");
    }
    
    public API initDB() {
        final Database.DatabaseType databaseType = Database.getMySQLCredentials().isEnabled() ? Database.DatabaseType.MYSQL : Database.DatabaseType.FLAT_FILE;
        registerEvent(new DatabaseLog());
        switch (databaseType) {
            case MYSQL: {
                API.database = MySQL.getInstance();
                break;
            }
            case FLAT_FILE: {
                API.database = FlatFile.getInstance();
                break;
            }
        }
        return this;
    }
    
    public API initDBFlatFile() {
        API.onlyFlatFile = true;
        API.database = FlatFile.getInstance();
        return this;
    }
    
    public API initAnvilUtil() {
        new AnvilUtil();
        return this;
    }
    
    public static Plugin getPlugin() {
        return API.plugin;
    }
    
    public static VersionSupport getVersionSupport() {
        return API.versionSupport;
    }
    
    public static Database getDatabase() {
        return API.database;
    }
    
    public static String getVersion() {
        return API.version;
    }
    
    public static boolean isOnlyFlatFile() {
        return API.onlyFlatFile;
    }
    
    public static void registerEvent(final Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, API.plugin);
    }
    
    public static boolean isPluginEnabled(final String s) {
        return Bukkit.getPluginManager().isPluginEnabled(s);
    }
    
    public static void print(final String s) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }
    
    static {
        API.plugin = null;
        API.onlyFlatFile = false;
    }
}
