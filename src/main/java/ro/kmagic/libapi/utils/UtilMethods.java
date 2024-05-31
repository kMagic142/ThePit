package ro.kmagic.libapi.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.ChatColor;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class UtilMethods
{
    public static String decolorize(final String s) {
        return s.replace(String.valueOf('§'), "&");
    }
    
    public static List<String> decolorize(final List<String> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(decolorize(iterator.next()));
        }
        return list2;
    }
    
    public static String colorize(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static List<String> colorize(final List<String> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(ChatColor.translateAlternateColorCodes('&', iterator.next()));
        }
        return list2;
    }
    
    public static void replaceInLore(final ItemStack itemStack, final String target, final String replacement) {
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
            final ItemMeta itemMeta = itemStack.getItemMeta();
            final ArrayList<String> lore = new ArrayList<String>();
            for (String s : itemMeta.getLore()) {
                lore.add(s.replace(target, replacement));
            }
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
    }
    
    public static boolean inventoryFull(final Player player) {
        for (int i = 0; i < 36; ++i) {
            if (player.getInventory().getItem(i) == null) {
                return false;
            }
        }
        return true;
    }
}
