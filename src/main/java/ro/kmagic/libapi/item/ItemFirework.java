package ro.kmagic.libapi.item;

import java.util.List;
import org.bukkit.FireworkEffect;

public interface ItemFirework
{
    ItemBuilder addEffect(final FireworkEffect p0);
    
    ItemBuilder addEffects(final FireworkEffect... p0);
    
    List<FireworkEffect> getEffects();
    
    int getEffectsSize();
    
    ItemBuilder removeEffect(final int p0);
    
    ItemBuilder clearEffects();
    
    boolean hasEffects();
    
    int getPower();
    
    ItemBuilder setPower(final int p0);
    
    ItemBuilder setEffect(final FireworkEffect p0);
    
    boolean hasEffect();
    
    FireworkEffect getEffect();
}
