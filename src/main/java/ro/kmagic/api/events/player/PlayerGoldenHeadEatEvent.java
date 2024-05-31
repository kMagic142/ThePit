package ro.kmagic.api.events.player;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PlayerGoldenHeadEatEvent extends Event {
    private final Player p;
    public static final HandlerList handlers;
    
    public PlayerGoldenHeadEatEvent(final Player p) {
        this.p = p;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public HandlerList getHandlers() {
        return PlayerGoldenHeadEatEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerGoldenHeadEatEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
