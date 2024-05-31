package ro.kmagic.libapi.database.utils;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class ResultSetException extends RuntimeException
{
    static final String line = "&7&m----------------------------------";
    static final String space = " ";
    static final String title = "    &c&oAn error occurred while gathering ResultSet from Database";
    static final String description = "&eReason why the error occurred:";
    
    public ResultSetException() {
        this.print("&7&m----------------------------------");
        this.print("    &c&oAn error occurred while gathering ResultSet from Database");
        this.print(" ");
        this.print("&eReason why the error occurred:");
        this.print("&eResultSet is null or can't reach MySQL.");
        this.print("&7&m----------------------------------");
    }
    
    private void print(final String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }
}
