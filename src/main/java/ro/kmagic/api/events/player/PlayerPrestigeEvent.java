package ro.kmagic.api.events.player;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PlayerPrestigeEvent extends Event {
    private final Player p;
    private final int oldPrestige;
    private final int newPrestige;
    public static final HandlerList handlers;
    
    public PlayerPrestigeEvent(final Player p3, final int oldPrestige, final int newPrestige) {
        this.p = p3;
        this.oldPrestige = oldPrestige;
        this.newPrestige = newPrestige;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public int getOldPrestige() {
        return this.oldPrestige;
    }
    
    public int getNewPrestige() {
        return this.newPrestige;
    }
    
    public HandlerList getHandlers() {
        return PlayerPrestigeEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerPrestigeEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
