package ro.kmagic.libapi.utils;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import java.util.Iterator;
import java.util.List;

public class CustomError
{
    public static void print(final Exception ex, final Class clazz, final List<String> list) {
        final String s = "&7&m----------------------------------";
        final String s2 = " ";
        final String string = "    &c&oAn error occurred in class " + clazz.getSimpleName();
        final String string2 = "&fClass Path: &c" + clazz.getName();
        final String s3 = "&eA small description:";
        final String s4 = "&cComplete Error:";
        print(s);
        print(string);
        print(s2);
        print(string2);
        print(s2);
        print(s3);
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            print("&e" + iterator.next());
        }
        print(s2);
        print(s4);
        ex.printStackTrace();
        print(s);
    }
    
    private static void print(final String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }
}
