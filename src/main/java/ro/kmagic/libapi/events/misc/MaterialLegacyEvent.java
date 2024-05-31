package ro.kmagic.libapi.events.misc;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class MaterialLegacyEvent extends Event
{
    public static final HandlerList handlers;
    String calledMaterial;
    String placeholderMaterial;
    
    public MaterialLegacyEvent(final String calledMaterial, final String placeholderMaterial) {
        this.calledMaterial = calledMaterial;
        this.placeholderMaterial = placeholderMaterial;
    }
    
    public String getCalledMaterial() {
        return this.calledMaterial;
    }
    
    public String getPlaceholderMaterial() {
        return this.placeholderMaterial;
    }
    
    public HandlerList getHandlers() {
        return MaterialLegacyEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return MaterialLegacyEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
