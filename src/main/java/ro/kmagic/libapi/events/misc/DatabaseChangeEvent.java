package ro.kmagic.libapi.events.misc;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class DatabaseChangeEvent extends Event
{
    public static final HandlerList handlers;
    private final DatabaseAction databaseAction;
    private final String table;
    private final String column;
    
    public DatabaseChangeEvent(final DatabaseAction databaseAction, final String table, final String column) {
        this.databaseAction = databaseAction;
        this.table = table;
        this.column = column;
    }
    
    public DatabaseAction getDatabaseAction() {
        return this.databaseAction;
    }
    
    public String getTable() {
        return this.table;
    }
    
    public String getColumn() {
        return this.column;
    }
    
    public HandlerList getHandlers() {
        return DatabaseChangeEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return DatabaseChangeEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
    
    public enum DatabaseAction
    {
        SELECT, 
        UPDATE, 
        CREATE, 
        ALTER, 
        DELETE, 
        INSERT
    }
}
