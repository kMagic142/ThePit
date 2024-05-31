package ro.kmagic.api.events.arena;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class ArenaLeaveEvent extends Event {
    private final Player p;
    public static final HandlerList handlers;
    
    public ArenaLeaveEvent(final Player p) {
        this.p = p;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public HandlerList getHandlers() {
        return ArenaLeaveEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return ArenaLeaveEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
