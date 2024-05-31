package ro.kmagic.libapi.database.utils;

import java.sql.SQLException;
import ro.kmagic.libapi.API;
import java.util.HashMap;
import ro.kmagic.libapi.database.Database;

public class ResultSet
{
    Database.DatabaseType dbType;
    java.sql.ResultSet mysqlResultSet;
    HashMap<Object, Object> flatfileResultSet;
    
    public ResultSet(final java.sql.ResultSet mysqlResultSet) {
        this.dbType = API.getDatabase().getDatabaseType();
        this.mysqlResultSet = mysqlResultSet;
    }
    
    public ResultSet(final HashMap<Object, Object> flatfileResultSet) {
        this.dbType = API.getDatabase().getDatabaseType();
        this.flatfileResultSet = flatfileResultSet;
    }
    
    public boolean next() {
        switch (this.dbType) {
            case MYSQL: {
                try {
                    return this.mysqlResultSet.next();
                }
                catch (SQLException ex) {
                    throw new ResultSetException();
                }
            }
            case FLAT_FILE: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void close() {
        if (!this.dbType.equals(Database.DatabaseType.MYSQL)) {
            return;
        }
        try {
            this.mysqlResultSet.close();
        }
        catch (SQLException ex) {
            throw new ResultSetException();
        }
    }
    
    public String getString(final String key) {
        switch (this.dbType) {
            case MYSQL: {
                try {
                    return this.mysqlResultSet.getString(key);
                }
                catch (SQLException ex) {
                    throw new ResultSetException();
                }
            }
            case FLAT_FILE: {
                return String.valueOf(this.flatfileResultSet.get(key));
            }
            default: {
                return "";
            }
        }
    }
    
    public boolean getBoolean(final String s) {
        return Boolean.parseBoolean(this.getString(s));
    }
    
    public int getInt(final String key) {
        switch (this.dbType) {
            case MYSQL: {
                try {
                    return this.mysqlResultSet.getInt(key);
                }
                catch (SQLException ex) {
                    throw new ResultSetException();
                }
            }
            case FLAT_FILE: {
                return Integer.parseInt(String.valueOf(this.flatfileResultSet.get(key)));
            }
            default: {
                return 0;
            }
        }
    }
    
    public long getLong(final String key) {
        switch (this.dbType) {
            case MYSQL: {
                try {
                    return this.mysqlResultSet.getLong(key);
                }
                catch (SQLException ex) {
                    throw new ResultSetException();
                }
            }
            case FLAT_FILE: {
                return Long.parseLong(String.valueOf(this.flatfileResultSet.get(key)));
            }
            default: {
                return 0L;
            }
        }
    }
    
    public float getFloat(final String key) {
        switch (this.dbType) {
            case MYSQL: {
                try {
                    return this.mysqlResultSet.getFloat(key);
                }
                catch (SQLException ex) {
                    throw new ResultSetException();
                }
            }
            case FLAT_FILE: {
                return Float.parseFloat(String.valueOf(this.flatfileResultSet.get(key)));
            }
            default: {
                return 0.0f;
            }
        }
    }
    
    public double getDouble(final String key) {
        switch (this.dbType) {
            case MYSQL: {
                try {
                    return this.mysqlResultSet.getDouble(key);
                }
                catch (SQLException ex) {
                    throw new ResultSetException();
                }
            }
            case FLAT_FILE: {
                return Double.parseDouble(String.valueOf(this.flatfileResultSet.get(key)));
            }
            default: {
                return 0.0;
            }
        }
    }
}
