package ro.kmagic.api.events.player;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PlayerStreakEvent extends Event {
    private final Player p;
    private final double kills;
    public static final HandlerList handlers;
    
    public PlayerStreakEvent(final Player p2, final double kills) {
        this.p = p2;
        this.kills = kills;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public double getKills() {
        return this.kills;
    }
    
    public HandlerList getHandlers() {
        return PlayerStreakEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerStreakEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
