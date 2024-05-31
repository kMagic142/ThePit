package ro.kmagic.api.events.misc;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Item;
import org.bukkit.event.Event;

public class GoldSpawnEvent extends Event {
    private final Item item;
    public static final HandlerList handlers;
    
    public GoldSpawnEvent(final Item item) {
        this.item = item;
    }
    
    public Item getItem() {
        return this.item;
    }
    
    public HandlerList getHandlers() {
        return GoldSpawnEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return GoldSpawnEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
