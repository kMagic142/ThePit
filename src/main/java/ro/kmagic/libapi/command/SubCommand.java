package ro.kmagic.libapi.command;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;

public abstract class SubCommand
{
    private final String name;
    private String description;
    private String[] permissions;
    private ClickEvent.Action clickAction;
    
    public SubCommand(final String name, final String[] permissions) {
        this.permissions = null;
        this.clickAction = ClickEvent.Action.SUGGEST_COMMAND;
        this.name = name;
        this.permissions = permissions;
    }
    
    public SubCommand(final String name) {
        this.permissions = null;
        this.clickAction = ClickEvent.Action.SUGGEST_COMMAND;
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String[] getPermissions() {
        return this.permissions;
    }
    
    public boolean hasPermission(final CommandSender commandSender) {
        if (this.permissions == null) {
            return true;
        }
        final String[] permissions = this.permissions;
        for (int length = permissions.length, i = 0; i < length; ++i) {
            if (commandSender.hasPermission(permissions[i])) {
                return true;
            }
        }
        return false;
    }
    
    public List<String> tabComplete(final CommandSender commandSender, final String s, final String[] array, final Location location) {
        return null;
    }
    
    public void setClickAction(final ClickEvent.Action clickAction) {
        this.clickAction = clickAction;
    }
    
    public ClickEvent.Action getClickAction() {
        return this.clickAction;
    }
    
    public abstract void execute(final CommandSender p0, final String[] p1);
    
    public abstract boolean canSee(final CommandSender p0);
}
