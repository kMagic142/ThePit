package ro.kmagic.libapi.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class PlayerDeathEvent extends Event
{
    private static final HandlerList handlers;
    private final Player killed;
    private final Player killer;
    
    public PlayerDeathEvent(final Player killer, final Player killed) {
        this.killed = killed;
        this.killer = killer;
    }
    
    public Player getKiller() {
        return this.killer;
    }
    
    public Player getKilled() {
        return this.killed;
    }
    
    public HandlerList getHandlers() {
        return PlayerDeathEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerDeathEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
