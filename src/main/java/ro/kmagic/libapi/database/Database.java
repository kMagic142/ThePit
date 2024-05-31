package ro.kmagic.libapi.database;

import ro.kmagic.libapi.utils.FileManager;
import ro.kmagic.libapi.database.utils.ResultSet;
import ro.kmagic.libapi.database.utils.ColumnInfo;
import java.util.UUID;
import ro.kmagic.libapi.database.utils.Column;
import java.util.List;
import ro.kmagic.libapi.database.utils.MySQLCredentials;

public abstract class Database
{
    private static MySQLCredentials mySQLCredentials;
    private DatabaseType dbType;
    
    public abstract DatabaseType getDatabaseType();
    
    public void createTable(final String s, final List<Column> list) {
    }
    
    public void addColumn(final String s, final Column column) {
    }
    
    public abstract boolean hasAccount(final UUID p0, final String p1);
    
    public abstract void createPlayer(final UUID p0, final String p1, final List<ColumnInfo> p2);
    
    public abstract ResultSet getResultSet(final UUID p0, final String p1);
    
    public abstract boolean contains(final UUID p0, final String p1);
    
    public abstract String getString(final UUID p0, final String p1, final String p2);
    
    public abstract void setString(final UUID p0, final String p1, final String p2, final String p3);
    
    public abstract boolean getBoolean(final UUID p0, final String p1, final String p2);
    
    public abstract void setBoolean(final UUID p0, final boolean p1, final String p2, final String p3);
    
    public abstract int getInt(final UUID p0, final String p1, final String p2);
    
    public abstract void setInt(final UUID p0, final int p1, final String p2, final String p3);
    
    public abstract long getLong(final UUID p0, final String p1, final String p2);
    
    public abstract void setLong(final UUID p0, final long p1, final String p2, final String p3);
    
    public abstract float getFloat(final UUID p0, final String p1, final String p2);
    
    public abstract void setFloat(final UUID p0, final float p1, final String p2, final String p3);
    
    public abstract double getDouble(final UUID p0, final String p1, final String p2);
    
    public abstract void setDouble(final UUID p0, final double p1, final String p2, final String p3);
    
    public abstract void deletePlayer(final UUID p0, final String p1);
    
    public void prepareStatement(final String s) {
    }
    
    public void close() {
    }
    
    public FileManager getProfile(final UUID uuid) {
        return null;
    }
    
    public static MySQLCredentials getMySQLCredentials() {
        return Database.mySQLCredentials;
    }
    
    static {
        Database.mySQLCredentials = new MySQLCredentials();
    }
    
    public enum DatabaseType
    {
        MYSQL, 
        FLAT_FILE
    }
}
