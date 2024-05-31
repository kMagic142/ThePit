package ro.kmagic.features;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import ro.kmagic.Main;
import java.util.HashMap;
import java.util.List;

public class PlayerRankColor {
    private final List<String> playerRanks;
    private final String ownerColor;
    private final String defaultColor;
    private final HashMap<String, String> colorTags;
    
    public PlayerRankColor() {
        this.playerRanks = new ArrayList<String>(Main.getRankColors().getConfigurationSection("Rank").getKeys(false));
        final HashMap<String, String> colorTags = new HashMap<String, String>();
        for (final String s : this.playerRanks) {
            if (!s.equals("Default") && !s.equals("Owner")) {
                colorTags.put(s, Main.getRankColors().getUncoloredString("Rank." + s + ".Color"));
            }
        }
        this.colorTags = colorTags;
        this.ownerColor = Main.getRankColors().getUncoloredString("Rank.Owner.Color");
        this.defaultColor = Main.getRankColors().getUncoloredString("Rank.Default.Color");
    }
    
    public String getColorTag(final Player player) {
        if (player.isOp()) {
            return this.ownerColor;
        }
        for (final String key : this.playerRanks) {
            if (player.hasPermission("thepit.rankcolors." + key.toLowerCase())) {
                return this.colorTags.get(key);
            }
        }
        return this.defaultColor;
    }
}
