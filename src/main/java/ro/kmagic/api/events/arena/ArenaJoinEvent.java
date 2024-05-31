package ro.kmagic.api.events.arena;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class ArenaJoinEvent extends Event {
    private final Player p;
    public static final HandlerList handlers;
    
    public ArenaJoinEvent(final Player p) {
        this.p = p;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public HandlerList getHandlers() {
        return ArenaJoinEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return ArenaJoinEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
