package ro.kmagic.libapi.events.player;

import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class PlayerAssistEvent extends Event
{
    public static final HandlerList handlers;
    private final Player victim;
    private final Player killer;
    private final List<Player> damagers;
    private final HashMap<Player, Integer> percentage;
    
    public PlayerAssistEvent(final Player victim, final Player killer, final List<Player> damagers, final HashMap<Player, Integer> percentage) {
        this.victim = victim;
        this.killer = killer;
        this.damagers = damagers;
        this.percentage = percentage;
    }
    
    public Player getVictim() {
        return this.victim;
    }
    
    public Player getKiller() {
        return this.killer;
    }
    
    public List<Player> getDamagers() {
        return this.damagers;
    }
    
    public HashMap<Player, Integer> getPercentage() {
        return this.percentage;
    }
    
    public HandlerList getHandlers() {
        return PlayerAssistEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerAssistEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
