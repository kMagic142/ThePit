package ro.kmagic.libapi.database;

import ro.kmagic.libapi.database.utils.ResultSet;
import ro.kmagic.libapi.database.utils.ColumnInfo;
import java.util.List;
import ro.kmagic.libapi.utils.FileManager;
import java.util.UUID;
import java.util.HashMap;

public class FlatFile extends Database
{
    private final HashMap<UUID, FileManager> profile;
    private static FlatFile instance;
    
    public FlatFile() {
        this.profile = new HashMap<UUID, FileManager>();
    }
    
    public static FlatFile getInstance() {
        return FlatFile.instance;
    }
    
    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.FLAT_FILE;
    }
    
    @Override
    public boolean hasAccount(final UUID uuid, final String s) {
        final FileManager profile = this.getProfile(uuid);
        return FileManager.exists(uuid.toString(), "Accounts") && profile.contains(s);
    }
    
    @Override
    public void createPlayer(final UUID uuid, final String str, final List<ColumnInfo> list) {
        final FileManager profile = this.getProfile(uuid);
        String str2 = "";
        int n = 1;
        for (final ColumnInfo columnInfo : list) {
            if (!columnInfo.getColumnName().equalsIgnoreCase("UUID") || columnInfo.getColumnName().equalsIgnoreCase("Player")) {
                profile.set(str + "." + columnInfo.getColumnName(), columnInfo.getValue().replace("'", ""));
                if (n != 0) {
                    n = 0;
                    str2 = columnInfo.getColumnName();
                }
                else {
                    str2 = str2 + ", " + columnInfo.getColumnName();
                }
            }
        }
    }
    
    @Override
    public ResultSet getResultSet(final UUID uuid, final String str) {
        final HashMap<Object, Object> hashMap = new HashMap<>();
        final FileManager profile = this.getProfile(uuid);
        if (!FileManager.exists(uuid.toString(), "Accounts")) {
            return new ResultSet(hashMap);
        }
        if (!profile.contains(str)) {
            return new ResultSet(hashMap);
        }
        if (profile.getConfigurationSection(str).getKeys(false).size() == 0) {
            return new ResultSet(hashMap);
        }
        for (final String s : profile.getConfigurationSection(str).getKeys(false)) {
            hashMap.put(s, profile.getObject(str + "." + s));
        }
        return new ResultSet(hashMap);
    }
    
    @Override
    public boolean contains(final UUID uuid, final String s) {
        return this.hasAccount(uuid, s) && this.getProfile(uuid).contains(s);
    }
    
    @Override
    public String getString(final UUID uuid, final String s, final String s2) {
        if (!this.hasAccount(uuid, s2)) {
            return "";
        }
        final FileManager profile = this.getProfile(uuid);
        if (profile.contains(s2 + "." + s)) {
            return profile.getString(s2 + "." + s);
        }
        return "";
    }
    
    @Override
    public void setString(final UUID uuid, final String s, final String str, final String str2) {
        this.getProfile(uuid).set(str2 + "." + str, s);
    }
    
    @Override
    public boolean getBoolean(final UUID uuid, final String s, final String s2) {
        if (!this.hasAccount(uuid, s2)) {
            return false;
        }
        final FileManager profile = this.getProfile(uuid);
        return profile.contains(s2 + "." + s) && profile.getBoolean(s2 + "." + s);
    }
    
    @Override
    public void setBoolean(final UUID uuid, final boolean b, final String str, final String str2) {
        this.getProfile(uuid).set(str2 + "." + str, b);
    }
    
    @Override
    public int getInt(final UUID uuid, final String s, final String s2) {
        if (!this.hasAccount(uuid, s2)) {
            return 0;
        }
        final FileManager profile = this.getProfile(uuid);
        if (profile.contains(s2 + "." + s)) {
            return profile.getInt(s2 + "." + s);
        }
        return 0;
    }
    
    @Override
    public void setInt(final UUID uuid, final int i, final String str, final String str2) {
        this.getProfile(uuid).set(str2 + "." + str, i);
    }
    
    @Override
    public long getLong(final UUID uuid, final String s, final String s2) {
        if (!this.hasAccount(uuid, s2)) {
            return 0L;
        }
        final FileManager profile = this.getProfile(uuid);
        if (profile.contains(s2 + "." + s)) {
            return profile.getLong(s2 + "." + s);
        }
        return 0L;
    }
    
    @Override
    public void setLong(final UUID uuid, final long l, final String str, final String str2) {
        this.getProfile(uuid).set(str2 + "." + str, l);
    }
    
    @Override
    public float getFloat(final UUID uuid, final String s, final String s2) {
        if (!this.hasAccount(uuid, s2)) {
            return 0.0f;
        }
        final FileManager profile = this.getProfile(uuid);
        if (profile.contains(s2 + "." + s)) {
            return (float)profile.getDouble(s2 + "." + s);
        }
        return 0.0f;
    }
    
    @Override
    public void setFloat(final UUID uuid, final float f, final String str, final String str2) {
        this.getProfile(uuid).set(str2 + "." + str, f);
    }
    
    @Override
    public double getDouble(final UUID uuid, final String s, final String s2) {
        if (!this.hasAccount(uuid, s2)) {
            return 0.0;
        }
        final FileManager profile = this.getProfile(uuid);
        if (profile.contains(s2 + "." + s)) {
            return profile.getDouble(s2 + "." + s);
        }
        return 0.0;
    }
    
    @Override
    public void setDouble(final UUID uuid, final double d, final String str, final String str2) {
        this.getProfile(uuid).set(str2 + "." + str, d);
    }
    
    @Override
    public void deletePlayer(final UUID uuid, final String s) {
        FileManager.getFile(uuid.toString(), "Accounts").delete();
    }
    
    @Override
    public FileManager getProfile(final UUID key) {
        if (this.profile.containsKey(key)) {
            return this.profile.get(key);
        }
        final FileManager value = new FileManager(key.toString(), "Accounts");
        this.profile.put(key, value);
        return value;
    }
    
    static {
        FlatFile.instance = new FlatFile();
    }
}
