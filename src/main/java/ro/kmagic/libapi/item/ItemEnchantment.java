package ro.kmagic.libapi.item;

import java.util.Map;
import org.bukkit.enchantments.Enchantment;

public interface ItemEnchantment
{
    int getLevel(final Enchantment p0);
    
    ItemBuilder add(final Enchantment p0, final int p1);
    
    ItemBuilder addUnsafe(final Enchantment p0, final int p1);
    
    ItemBuilder remove(final Enchantment p0);
    
    Map<Enchantment, Integer> getList();
    
    boolean has(final Enchantment p0);
}
