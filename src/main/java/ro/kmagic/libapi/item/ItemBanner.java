package ro.kmagic.libapi.item;

import org.bukkit.block.banner.Pattern;
import java.util.List;

public interface ItemBanner
{
    ItemBuilder setPatterns(final List<Pattern> p0);
    
    ItemBuilder setPattern(final int p0, final Pattern p1);
    
    ItemBuilder addPattern(final Pattern p0);
    
    ItemBuilder removePattern(final int p0);
    
    List<Pattern> getPatterns();
    
    Pattern getPattern(final int p0);
    
    int getNumberOfPatterns();
}
