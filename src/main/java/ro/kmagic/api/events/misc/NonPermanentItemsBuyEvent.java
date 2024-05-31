package ro.kmagic.api.events.misc;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class NonPermanentItemsBuyEvent extends Event {
    private final Player p;
    private final int price;
    private final ItemStack itemStack;
    public static final HandlerList handlers;
    
    public NonPermanentItemsBuyEvent(final Player p3, final int price, final ItemStack itemStack) {
        this.p = p3;
        this.price = price;
        this.itemStack = itemStack;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public ItemStack getItem() {
        return this.itemStack;
    }
    
    public HandlerList getHandlers() {
        return NonPermanentItemsBuyEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return NonPermanentItemsBuyEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
