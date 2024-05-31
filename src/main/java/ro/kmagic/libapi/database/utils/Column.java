package ro.kmagic.libapi.database.utils;

public class Column
{
    String columnName;
    ColumnType columnType;
    int length;
    boolean notNull;
    boolean primaryKey;
    Object def;
    
    public Column(final String columnName) {
        this.length = 0;
        this.notNull = false;
        this.primaryKey = false;
        this.def = null;
        this.columnName = columnName;
    }
    
    public String getName() {
        return this.columnName;
    }
    
    public Column setType(final ColumnType columnType) {
        this.columnType = columnType;
        return this;
    }
    
    public Column setLength(final int length) {
        this.length = length;
        return this;
    }
    
    public Column setNotNull() {
        this.notNull = true;
        return this;
    }
    
    public Column setPrimaryKey() {
        this.primaryKey = true;
        return this;
    }
    
    public Column setDefault(final Object def) {
        this.def = def;
        return this;
    }
    
    public String build() {
        final String s = " ";
        return this.columnName + s + this.columnType.name().replace("_", "") + ((this.length == 0) ? "" : ("(" + this.length + ")")) + (this.notNull ? (s + "NOT NULL") : "") + ((this.def == null) ? "" : (s + "default '" + this.def + "'")) + (this.primaryKey ? (s + ", PRIMARY KEY (" + this.columnName + ")") : "");
    }
    
    public enum ColumnType
    {
        VARCHAR, 
        LONG_TEXT, 
        INT, 
        LONG, 
        FLOAT, 
        DOUBLE
    }
}
