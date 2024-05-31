package ro.kmagic.libapi.placeholder;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import ro.kmagic.libapi.exceptions.InitializationException;
import ro.kmagic.libapi.API;

import java.util.HashMap;

public class PlaceholderManager
{
    private static HashMap<String, Placeholder> placeholders;
    private static boolean papi;
    private static boolean mvdw;
    private static boolean mvdwReplacement;
    private static String identifier;
    
    public PlaceholderManager() {
        if (API.getPlugin() == null) {
            throw new InitializationException("Placeholder's plugin mustn't be null");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            PlaceholderManager.papi = true;
            new PAPI().register();
        }
        if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
            PlaceholderManager.mvdw = true;
        }
    }
    
    public static void setIdentifier(final String identifier) {
        PlaceholderManager.identifier = identifier;
    }
    
    public PlaceholderManager(final boolean mvdwReplacement) {
        PlaceholderManager.mvdwReplacement = mvdwReplacement;
        if (API.getPlugin() == null) {
            throw new InitializationException("Placeholder's plugin mustn't be null");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            PlaceholderManager.papi = true;
            new PAPI().register();
        }
        if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
            PlaceholderManager.mvdw = true;
        }
    }
    
    public static String getIdentifier() {
        return (PlaceholderManager.identifier == null) ? API.getPlugin().getDescription().getName().toLowerCase() : PlaceholderManager.identifier;
    }
    
    public static String craftBracketPlaceholder(final String str) {
        return "%" + getIdentifier() + "_" + str + "%";
    }
    
    public static void addPlaceholder(final Placeholder value) {
        PlaceholderManager.placeholders.put(value.getName(), value);
    }
    
    public static HashMap<String, Placeholder> getPlaceholders() {
        return PlaceholderManager.placeholders;
    }
    
    public static boolean isMvdwEnabled() {
        return PlaceholderManager.mvdw;
    }
    
    public static void disableMVdWReplacement() {
        PlaceholderManager.mvdwReplacement = false;
    }
    
    public static boolean isPapiEnabled() {
        return PlaceholderManager.papi;
    }
    
    public static String setPlaceholders(final Player player, String translateAlternateColorCodes) {
        translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', translateAlternateColorCodes);
        if (PlaceholderManager.papi) {
            return PAPI.setPlaceholders(player, translateAlternateColorCodes);
        }
        String replace = translateAlternateColorCodes;
        for (final String key : PlaceholderManager.placeholders.keySet()) {
            final Placeholder placeholder = PlaceholderManager.placeholders.get(key);
            String replacement = placeholder.request(player);
            if (replacement == null) {
                replacement = placeholder.request();
            }
            if (replacement == null) {
                continue;
            }
            replace = replace.replace(craftBracketPlaceholder(key), replacement);
        }
        return replace;
    }
    
    public static List<String> setPlaceholders(final Player player, final List<String> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(setPlaceholders(player, iterator.next()));
        }
        return list2;
    }
    
    public static ItemStack setPlaceholders(final Player player, final ItemStack itemStack) {
        if (itemStack.hasItemMeta()) {
            final ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta.hasDisplayName()) {
                itemMeta.setDisplayName(setPlaceholders(player, itemMeta.getDisplayName()));
            }
            if (itemMeta.hasLore()) {
                itemMeta.setLore(setPlaceholders(player, itemMeta.getLore()));
            }
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }
    
    public static boolean hasPlaceholders(final String s) {
        if (!PlaceholderManager.papi) {
            for (final String s2 : PlaceholderManager.placeholders.keySet()) {
                craftBracketPlaceholder(s2);
                if (s.contains(craftBracketPlaceholder(s2))) {
                    return true;
                }
            }
            return false;
        }
        return PAPI.hasPlaceholders(s);
    }
    
    public static boolean hasPlaceholders(final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (hasPlaceholders(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean hasPlaceholders(final ItemStack itemStack) {
        if (itemStack.hasItemMeta()) {
            final ItemMeta itemMeta = itemStack.getItemMeta();
            return (itemMeta.hasDisplayName() && hasPlaceholders(itemMeta.getDisplayName())) || (itemMeta.hasLore() && hasPlaceholders(itemMeta.getLore()));
        }
        return false;
    }
    
    static {
        PlaceholderManager.placeholders = new HashMap<String, Placeholder>();
        PlaceholderManager.papi = false;
        PlaceholderManager.mvdw = false;
        PlaceholderManager.mvdwReplacement = true;
    }
}
