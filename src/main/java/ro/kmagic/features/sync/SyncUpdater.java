package ro.kmagic.features.sync;

import ro.kmagic.libapi.API;
import org.bukkit.Bukkit;
import java.util.UUID;
import ro.kmagic.Main;
import org.bukkit.scheduler.BukkitRunnable;

public class SyncUpdater {
    public SyncUpdater() {
        new BukkitRunnable() {
            public void run() {
                if (Main.getArena().getPlayers().size() != 0) {
                    for (final UUID uuid : Main.getArena().getPlayers()) {
                        if (PlayerSync.isCached(Bukkit.getPlayer(uuid))) {
                            PlayerSync.get(Bukkit.getPlayer(uuid)).save();
                        }
                    }
                }
            }
        }.runTaskTimer(API.getPlugin(), 20L, 18000L);
    }
}
