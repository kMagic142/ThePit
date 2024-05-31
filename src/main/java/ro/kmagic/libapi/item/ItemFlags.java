package ro.kmagic.libapi.item;

import org.bukkit.inventory.ItemFlag;

public interface ItemFlags
{
    ItemBuilder add(final ItemFlag... p0);
    
    ItemBuilder remove(final ItemFlag... p0);
    
    boolean has(final ItemFlag p0);
}
