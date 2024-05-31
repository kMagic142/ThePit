package ro.kmagic.libapi.events.player;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class PlayerPickupItemEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private final Player player;
    private final Item item;
    private final int remaining;
    private boolean cancel;
    
    public PlayerPickupItemEvent(final Player player, final Item item, final int remaining) {
        this.cancel = false;
        this.player = player;
        this.item = item;
        this.remaining = remaining;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Item getItem() {
        return this.item;
    }
    
    public int getRemaining() {
        return this.remaining;
    }
    
    public boolean isCancelled() {
        return this.cancel;
    }
    
    public void setCancelled(final boolean cancel) {
        this.cancel = cancel;
    }
    
    public HandlerList getHandlers() {
        return PlayerPickupItemEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerPickupItemEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
