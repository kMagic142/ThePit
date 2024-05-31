package ro.kmagic.libapi.item;

import org.bukkit.potion.PotionType;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import java.util.List;

public interface ItemPotion
{
    boolean hasCustomEffects();
    
    List<PotionEffect> getCustomEffects();
    
    ItemBuilder removeCustomEffect(final PotionEffectType p0);
    
    boolean hasColor();
    
    Color getColor();
    
    ItemBuilder setColor(final Color p0);
    
    ItemBuilder setSplash(final PotionType p0, final boolean p1);
    
    ItemBuilder addCustomEffect(final PotionEffect p0);
}
