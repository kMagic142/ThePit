package ro.kmagic.libapi.exceptions;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class InitializationException extends RuntimeException
{
    static final String line = "&7&m----------------------------------";
    static final String space = " ";
    static final String title = "    &c&oAn error occurred while initializing API";
    static final String description = "&eReason why the error occurred:";
    
    public InitializationException(final String str) {
        this.print("&7&m----------------------------------");
        this.print("    &c&oAn error occurred while initializing API");
        this.print(" ");
        this.print("&eReason why the error occurred:");
        this.print("&e" + str);
        this.print("&7&m----------------------------------");
    }
    
    private void print(final String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }
}
