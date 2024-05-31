package ro.kmagic.libapi.database.utils;

public class ColumnInfo
{
    String columnName;
    Object value;
    
    public ColumnInfo(final String columnName, final Object value) {
        this.columnName = columnName;
        this.value = value;
    }
    
    public String getColumnName() {
        return this.columnName;
    }
    
    public String getValue() {
        return "'" + this.value + "'";
    }
}
