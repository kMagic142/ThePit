package ro.kmagic.api.events.player;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PlayerArrowHitEvent extends Event {
    private final Player p;
    public static final HandlerList handlers;
    
    public PlayerArrowHitEvent(final Player p) {
        this.p = p;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public HandlerList getHandlers() {
        return PlayerArrowHitEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerArrowHitEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
