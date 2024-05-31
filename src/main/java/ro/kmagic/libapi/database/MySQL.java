package ro.kmagic.libapi.database;

import ro.kmagic.libapi.database.utils.ColumnInfo;
import ro.kmagic.libapi.database.utils.ResultSet;
import java.util.UUID;

import ro.kmagic.libapi.events.misc.DatabaseChangeEvent;
import org.bukkit.Bukkit;
import ro.kmagic.libapi.database.utils.Column;
import java.util.List;
import java.sql.SQLException;
import ro.kmagic.libapi.utils.CustomError;
import java.util.Arrays;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;

public class MySQL extends Database
{
    private Connection connection;
    private static MySQL instance;
    
    public static MySQL getInstance() {
        return MySQL.instance;
    }
    
    private MySQL() {
        this.connect();
    }
    
    private void connect() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://" + getMySQLCredentials().getHost() + ":" + getMySQLCredentials().getPort() + "/" + getMySQLCredentials().getDatabase() + "?useSSL=" + getMySQLCredentials().isSSLCertifiedEnabled() + "&user=" + getMySQLCredentials().getUsername() + "&password=" + getMySQLCredentials().getPassword() + "&autoReconnect=true&maxReconnects=5&initialTimeout=5&useUnicode=true&characterEncoding=UTF-8");
        try {
            this.connection = hikariDataSource.getConnection();
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't connect to MySQL server!", "Probably MySQL server is closed", "or MySQL login credentials are wrong."));
        }
    }
    
    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.MYSQL;
    }
    
    @Override
    public void createTable(final String str, final List<Column> list) {
        this.toConnect();
        String s = "";
        String str2 = "";
        int n = 1;
        for (final Column column : list) {
            if (n != 0) {
                n = 0;
                s = column.build();
                str2 = column.getName();
            }
            else {
                s = s + ", " + column.build();
                str2 = str2 + ", " + column.getName();
            }
        }
        try {
            this.connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + str + "` (" + s + ");");
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.CREATE, str, str2));
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't create Table in MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
    }
    
    @Override
    public void addColumn(final String str, final Column column) {
        this.toConnect();
        try {
            this.connection.createStatement().executeUpdate("ALTER TABLE `" + str + "` ADD COLUMN " + column.build() + ";");
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.ALTER, str, column.getName()));
        }
        catch (SQLException ex) {}
    }
    
    @Override
    public boolean hasAccount(final UUID uuid, final String str) {
        this.toConnect();
        try {
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str, "Not Defined"));
            return new ResultSet(this.connection.createStatement().executeQuery("SELECT UUID FROM `" + str + "` WHERE UUID = '" + uuid.toString() + "';")).next();
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't get Account from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
            return false;
        }
    }
    
    @Override
    public void createPlayer(final UUID uuid, final String str, final List<ColumnInfo> list) {
        this.toConnect();
        String s = "";
        String s2 = "";
        int n = 1;
        for (final ColumnInfo columnInfo : list) {
            if (n != 0) {
                n = 0;
                s = columnInfo.getColumnName();
                s2 = columnInfo.getValue();
            }
            else {
                s = s + "," + columnInfo.getColumnName();
                s2 = s2 + "," + columnInfo.getValue();
            }
        }
        try {
            this.connection.createStatement().executeUpdate("INSERT INTO `" + str + "` (" + s + ") VALUES (" + s2 + ");");
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.INSERT, str, s));
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't create Player in MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
    }
    
    @Override
    public ResultSet getResultSet(final UUID obj, final String str) {
        this.toConnect();
        try {
            final ResultSet set = new ResultSet(this.connection.createStatement().executeQuery("SELECT * FROM `" + str + "` WHERE UUID = '" + obj + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str, "Not Defined"));
            if (set.next()) {
                return set;
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't get ResultSet from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
        return null;
    }
    
    @Override
    public boolean contains(final UUID uuid, final String str) {
        this.toConnect();
        if (!this.hasAccount(uuid, str)) {
            return false;
        }
        try {
            final ResultSet set = new ResultSet(this.connection.createStatement().executeQuery("SELECT * FROM `" + str + "` WHERE UUID = '" + uuid + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str, "Not Defined"));
            if (set.next()) {
                return set.next();
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't check if MySQL contains an object!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
        return false;
    }
    
    @Override
    public String getString(final UUID obj, final String str, final String str2) {
        this.toConnect();
        if (!this.hasAccount(obj, str2)) {
            return "";
        }
        try {
            final ResultSet set = new ResultSet(this.connection.createStatement().executeQuery("SELECT " + str + " FROM `" + str2 + "` WHERE UUID = '" + obj + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str2, str));
            if (set.next()) {
                return set.getString(str);
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't get String from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
        return "";
    }
    
    @Override
    public void setString(final UUID obj, final String str, final String str2, final String str3) {
        this.toConnect();
        if (!this.hasAccount(obj, str3)) {
            return;
        }
        try {
            this.connection.createStatement().execute("UPDATE `" + str3 + "` SET " + str2 + " = '" + str + "' WHERE UUID = '" + obj + "';");
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.UPDATE, str3, str2));
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't set String in MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
    }
    
    @Override
    public boolean getBoolean(final UUID obj, final String str, final String str2) {
        this.toConnect();
        if (!this.hasAccount(obj, str2)) {
            return false;
        }
        try {
            final ResultSet set = new ResultSet(this.connection.createStatement().executeQuery("SELECT " + str + " FROM `" + str2 + "` WHERE UUID = '" + obj + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str2, str));
            if (set.next()) {
                return Boolean.parseBoolean(set.getString(str));
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't get Boolean from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
        return false;
    }
    
    @Override
    public void setBoolean(final UUID uuid, final boolean b, final String s, final String s2) {
        this.setString(uuid, String.valueOf(b), s, s2);
    }
    
    @Override
    public int getInt(final UUID obj, final String str, final String str2) {
        this.toConnect();
        if (!this.hasAccount(obj, str2)) {
            return 0;
        }
        try {
            final ResultSet set = new ResultSet(this.connection.createStatement().executeQuery("SELECT " + str + " FROM `" + str2 + "` WHERE UUID = '" + obj + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str2, str));
            if (set.next()) {
                return set.getInt(str);
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't get Integer from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
        return 0;
    }
    
    @Override
    public void setInt(final UUID uuid, final int i, final String s, final String s2) {
        this.setString(uuid, String.valueOf(i), s, s2);
    }
    
    @Override
    public long getLong(final UUID obj, final String str, final String str2) {
        this.toConnect();
        if (!this.hasAccount(obj, str2)) {
            return 0L;
        }
        try {
            final ResultSet set = new ResultSet(this.connection.createStatement().executeQuery("SELECT " + str + " FROM `" + str2 + "` WHERE UUID = '" + obj + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str2, str));
            if (set.next()) {
                return set.getLong(str);
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't get Long from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
        return 0L;
    }
    
    @Override
    public void setLong(final UUID uuid, final long l, final String s, final String s2) {
        this.setString(uuid, String.valueOf(l), s, s2);
    }
    
    @Override
    public float getFloat(final UUID obj, final String str, final String str2) {
        this.toConnect();
        if (!this.hasAccount(obj, str2)) {
            return 0.0f;
        }
        try {
            final ResultSet set = new ResultSet(this.connection.createStatement().executeQuery("SELECT " + str + " FROM `" + str2 + "` WHERE UUID = '" + obj + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str2, str));
            if (set.next()) {
                return set.getFloat(str);
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't get Float from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
        return 0.0f;
    }
    
    @Override
    public void setFloat(final UUID uuid, final float f, final String s, final String s2) {
        this.setString(uuid, String.valueOf(f), s, s2);
    }
    
    @Override
    public double getDouble(final UUID obj, final String str, final String str2) {
        this.toConnect();
        if (!this.hasAccount(obj, str2)) {
            return 0.0;
        }
        try {
            final ResultSet set = new ResultSet(this.connection.createStatement().executeQuery("SELECT " + str + " FROM `" + str2 + "` WHERE UUID = '" + obj + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, str2, str));
            if (set.next()) {
                return set.getDouble(str);
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't get Double from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
        return 0.0;
    }
    
    @Override
    public void setDouble(final UUID uuid, final double d, final String s, final String s2) {
        this.setString(uuid, String.valueOf(d), s, s2);
    }
    
    @Override
    public void prepareStatement(final String s) {
        this.toConnect();
        try {
            this.connection.prepareStatement(s).executeUpdate();
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.UPDATE, "Statement's table", "Not Defined"));
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't send statement to MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
    }
    
    @Override
    public void deletePlayer(final UUID uuid, final String str) {
        this.toConnect();
        try {
            this.connection.createStatement().executeUpdate("DELETE FROM `" + str + "` WHERE UUID = '" + uuid.toString() + "';");
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.DELETE, str, "Not Defined"));
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't delete Player from MySQL!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
    }
    
    @Override
    public void close() {
        if (this.connection == null) {
            return;
        }
        try {
            this.connection.close();
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't close MySQL connection!", "Probably MySQL server is down", "or your machine can't reach MySQL Server."));
        }
    }
    
    private void toConnect() {
        if (this.connection == null) {
            this.connect();
        }
    }
    
    static {
        MySQL.instance = new MySQL();
    }
}
