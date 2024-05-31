package ro.kmagic.libapi.database.utils;

import java.io.IOException;
import ro.kmagic.libapi.utils.CustomError;
import java.util.Arrays;
import ro.kmagic.libapi.API;
import java.io.File;
import java.util.Collections;

import org.bukkit.configuration.file.YamlConfiguration;

public class MySQLCredentials
{
    private YamlConfiguration yml;
    private File file;
    
    public MySQLCredentials() {
        if (API.isOnlyFlatFile()) {
            return;
        }
        final String string = "plugins/" + API.getPlugin().getDescription().getName() + "/";
        new File(string).mkdirs();
        this.file = new File(string, "MySQL.yml");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            }
            catch (IOException ex) {
                CustomError.print(ex, this.getClass(), Arrays.asList("Can't create MySQL credentials file", "with path: " + string));
            }
        }
        (this.yml = YamlConfiguration.loadConfiguration(this.file)).addDefault("Enabled", false);
        this.yml.addDefault("Host", "127.0.0.1");
        this.yml.addDefault("Port", 3306);
        this.yml.addDefault("Database", "MyDatabase");
        this.yml.addDefault("SSL", false);
        this.yml.addDefault("Username", "root");
        this.yml.addDefault("Password", "MyPassword");
        this.yml.options().copyDefaults(true);
        this.save();
    }
    
    public void reload() {
        this.yml = YamlConfiguration.loadConfiguration(this.file);
    }
    
    private void save() {
        try {
            this.yml.save(this.file);
        }
        catch (IOException ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't save file: " + this.file.getName()));
        }
    }
    
    public boolean isEnabled() {
        return this.yml.getBoolean("Enabled");
    }
    
    public String getHost() {
        return this.yml.getString("Host");
    }
    
    public int getPort() {
        return this.yml.getInt("Port");
    }
    
    public String getDatabase() {
        return this.yml.getString("Database");
    }
    
    public boolean isSSLCertifiedEnabled() {
        return this.yml.getBoolean("SSL");
    }
    
    public String getUsername() {
        return this.yml.getString("Username");
    }
    
    public String getPassword() {
        return this.yml.getString("Password");
    }
    
    public void setString(final String s, final String s2) {
        this.yml.set(s, s2);
        this.save();
    }
    
    public void setInt(final String s, final int i) {
        this.yml.set(s, i);
        this.save();
    }
    
    public void setBoolean(final String s, final boolean b) {
        this.yml.set(s, b);
        this.save();
    }
}
