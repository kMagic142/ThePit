package ro.kmagic.features;

import org.bukkit.ChatColor;
import ro.kmagic.Main;
import ro.kmagic.features.prestige.PlayerPrestige;
import org.bukkit.entity.Player;

public class XPTag {
    public String get(final Player player) {
        final String s = "&?[";
        final String s2 = "&?]";
        final String levelColor = this.getLevelColor(player.getLevel());
        final String s3 = "7";
        final int prestige = PlayerPrestige.get(player).getPrestige();
        if (prestige != 0) {
            final String prestigeColor = this.getPrestigeColor(prestige);
            return ChatColor.translateAlternateColorCodes('&', s.replace("?", prestigeColor) + PlayerPrestige.format(prestige) + "&?-".replace("?", prestigeColor) + levelColor + player.getLevel() + s2.replace("?", prestigeColor) + Main.getArena().getNameColorCode(player));
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace("?", s3) + levelColor + player.getLevel() + s2.replace("?", s3) + Main.getArena().getNameColorCode(player));
    }
    
    public String get(final int n, final int n2) {
        final String s = "&?[";
        final String s2 = "&?]";
        final String levelColor = this.getLevelColor(n);
        final String s3 = "7";
        if (n2 != 0) {
            final String prestigeColor = this.getPrestigeColor(n2);
            return ChatColor.translateAlternateColorCodes('&', s.replace("?", prestigeColor) + PlayerPrestige.format(n2) + "&?-".replace("?", prestigeColor) + levelColor + n + s2.replace("?", prestigeColor) + "&7");
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace("?", s3) + levelColor + n + s2.replace("?", s3) + "&7");
    }

    public String getScoreboardFormated(final Player player) {
        final String s = "&?[";
        final String s2 = "&?]";
        final String levelColor = this.getLevelColor(player.getLevel()).replace("&l", "");
        String prestigeColor = "7";
        final int prestige = PlayerPrestige.get(player).getPrestige();
        if (prestige != 0) {
            prestigeColor = this.getPrestigeColor(prestige);
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace("?", prestigeColor) + levelColor + player.getLevel() + s2.replace("?", prestigeColor) + Main.getArena().getNameColorCode(player)).replace("null", "");
    }
    
    public String getScoreboard(final Player player) {
        final String s = "&?[";
        final String s2 = "&?]";
        final String levelColor = this.getLevelColor(player.getLevel());
        String prestigeColor = "7";
        final int prestige = PlayerPrestige.get(player).getPrestige();
        if (prestige != 0) {
            prestigeColor = this.getPrestigeColor(prestige);
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace("?", prestigeColor) + levelColor + player.getLevel() + s2.replace("?", prestigeColor) + Main.getArena().getNameColorCode(player)).replace("null", "");
    }
    
    private String getLevelColor(final int n) {
        if (n < 10) {
            return "&7";
        }
        if (n < 20) {
            return "&9";
        }
        if (n < 30) {
            return "&3";
        }
        if (n < 40) {
            return "&2";
        }
        if (n < 50) {
            return "&a";
        }
        if (n < 60) {
            return "&e";
        }
        if (n < 70) {
            return "&6&l";
        }
        if (n < 80) {
            return "&c&l";
        }
        if (n < 90) {
            return "&4&l";
        }
        if (n < 100) {
            return "&5&l";
        }
        if (n < 110) {
            return "&d&l";
        }
        if (n < 120) {
            return "&f&l";
        }
        return "&b&l";
    }
    
    private String getPrestigeColor(final int n) {
        if (n < 5) {
            return "b";
        }
        if (n < 10) {
            return "e";
        }
        if (n < 15) {
            return "6";
        }
        if (n < 20) {
            return "c";
        }
        if (n < 25) {
            return "5";
        }
        return "d";
    }
}
