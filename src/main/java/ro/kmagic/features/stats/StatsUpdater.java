package ro.kmagic.features.stats;

import ro.kmagic.libapi.API;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class StatsUpdater {
    public StatsUpdater() {
        new BukkitRunnable() {
            public void run() {
                if (Bukkit.getOnlinePlayers().size() != 0) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (PlayerStats.isCached(player)) {
                            for (final Stats.StatsType statsType : Stats.StatsType.values()) {
                                if (PlayerStats.get(player).get(statsType) != 0) {
                                    API.getDatabase().setInt(player.getUniqueId(), PlayerStats.get(player).get(statsType), statsType.name(), Stats.getTableName());
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(API.getPlugin(), 0L, 20L * 60);
    }
}
