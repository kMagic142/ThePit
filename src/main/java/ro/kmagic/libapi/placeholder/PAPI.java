package ro.kmagic.libapi.placeholder;

import ro.kmagic.libapi.API;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PAPI extends PlaceholderExpansion
{
    public static String setPlaceholders(final Player player, final String s) {
        return PlaceholderAPI.setPlaceholders(player, s);
    }
    
    public static boolean hasPlaceholders(final String s) {
        return PlaceholderAPI.containsPlaceholders(s);
    }
    
    public String getIdentifier() {
        return PlaceholderManager.getIdentifier();
    }
    
    public boolean canRegister() {
        return true;
    }
    
    public String getAuthor() {
        return "kMagic";
    }
    
    public String getVersion() {
        return API.getPlugin().getDescription().getVersion();
    }
    
    public String onPlaceholderRequest(final Player player, final String s) {
        if (PlaceholderManager.getPlaceholders().containsKey(s.toLowerCase())) {
            final Placeholder placeholder = PlaceholderManager.getPlaceholders().get(s.toLowerCase());
            String s2 = placeholder.request(player);
            if (s2 == null) {
                s2 = placeholder.request();
            }
            return s2;
        }
        return null;
    }
}
