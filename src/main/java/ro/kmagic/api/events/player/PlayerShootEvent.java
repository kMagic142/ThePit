package ro.kmagic.api.events.player;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PlayerShootEvent extends Event {
    private final Player p;
    public static final HandlerList handlers;
    
    public PlayerShootEvent(final Player p) {
        this.p = p;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public HandlerList getHandlers() {
        return PlayerShootEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerShootEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
