package ro.kmagic.features.stats;

import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public class PlayerStats {
    private static HashMap<UUID, PlayerStats> playerStats;
    private final HashMap<Stats.StatsType, Integer> stats;
    private final UUID uuid;
    
    public PlayerStats(final Player player, final HashMap<Stats.StatsType, Integer> stats) {
        this.stats = stats;
        this.uuid = player.getUniqueId();
        PlayerStats.playerStats.put(player.getUniqueId(), this);
    }
    
    public static boolean isCached(final Player player) {
        return PlayerStats.playerStats.containsKey(player.getUniqueId());
    }
    
    public static PlayerStats get(final Player player) {
        return PlayerStats.playerStats.get(player.getUniqueId());
    }
    
    public int get(final Stats.StatsType key) {
        return this.stats.get(key);
    }
    
    public static void removeAccount(final Player player) {
        PlayerStats.playerStats.remove(player.getUniqueId());
    }
    
    public void add(final Stats.StatsType statsType, final int n) {
        if (statsType == null) {
            return;
        }
        this.stats.put(statsType, this.stats.get(statsType) + n);
    }
    
    static {
        PlayerStats.playerStats = new HashMap<UUID, PlayerStats>();
    }
}
