package ro.kmagic.libapi.events.misc;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class AnvilClickEvent extends Event
{
    public static final HandlerList handlers;
    private final Player p;
    private final String userInput;
    
    public AnvilClickEvent(final Player p2, final String userInput) {
        this.p = p2;
        this.userInput = userInput;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public String getUserInput() {
        return this.userInput;
    }
    
    public HandlerList getHandlers() {
        return AnvilClickEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return AnvilClickEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
