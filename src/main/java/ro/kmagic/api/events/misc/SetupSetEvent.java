package ro.kmagic.api.events.misc;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import ro.kmagic.commands.setup.utils.SetupUtils;
import org.bukkit.event.Event;

public class SetupSetEvent extends Event {
    private final SetupUtils.SetupType type;
    private final Player p;
    public static final HandlerList handlers;
    
    public SetupSetEvent(final SetupUtils.SetupType type, final Player p2) {
        this.type = type;
        this.p = p2;
    }
    
    public SetupUtils.SetupType getSetupSetType() {
        return this.type;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public HandlerList getHandlers() {
        return SetupSetEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return SetupSetEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
