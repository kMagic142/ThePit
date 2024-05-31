package ro.kmagic.libapi.placeholder;

import org.bukkit.entity.Player;

public abstract class Placeholder
{
    private final String name;
    
    public Placeholder(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String request(final Player player) {
        return null;
    }
    
    public String request() {
        return null;
    }
}
