package ro.kmagic.api.events.player;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PlayerRankupEvent extends Event {
    private final Player p;
    private final int oldLevel;
    private final int newLevel;
    public static final HandlerList handlers;
    
    public PlayerRankupEvent(final Player p3, final int oldLevel, final int newLevel) {
        this.p = p3;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public int getOldLevel() {
        return this.oldLevel;
    }
    
    public int getNewLeel() {
        return this.newLevel;
    }
    
    public HandlerList getHandlers() {
        return PlayerRankupEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerRankupEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
