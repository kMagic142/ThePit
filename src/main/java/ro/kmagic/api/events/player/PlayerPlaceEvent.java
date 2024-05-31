package ro.kmagic.api.events.player;

import org.bukkit.event.HandlerList;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PlayerPlaceEvent extends Event {
    private final Player p;
    private final Block block;
    public static final HandlerList handlers;
    
    public PlayerPlaceEvent(final Player p2, final Block block) {
        this.p = p2;
        this.block = block;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public HandlerList getHandlers() {
        return PlayerPlaceEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerPlaceEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
