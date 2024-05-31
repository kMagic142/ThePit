package ro.kmagic.libapi.utils;

import ro.kmagic.libapi.events.player.PlayerAssistEvent;
import org.bukkit.Bukkit;
import java.util.Map;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.EventHandler;
import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import java.util.ArrayList;
import org.bukkit.scheduler.BukkitTask;
import java.util.UUID;
import java.util.List;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Assist implements Listener
{
    private final HashMap<Player, HashMap<Player, Double>> playerTable;
    private final List<UUID> ateGoldenApple;
    private final HashMap<UUID, BukkitTask> ateTask;
    
    public Assist() {
        this.playerTable = new HashMap<Player, HashMap<Player, Double>>();
        this.ateGoldenApple = new ArrayList<UUID>();
        this.ateTask = new HashMap<UUID, BukkitTask>();
    }
    
    @EventHandler
    private void onConsume(final PlayerItemConsumeEvent playerItemConsumeEvent) {
        if (!playerItemConsumeEvent.getItem().getType().equals(Material.GOLDEN_APPLE)) {
            return;
        }
        final Player player = playerItemConsumeEvent.getPlayer();
        if (this.ateTask.containsKey(player.getUniqueId())) {
            this.ateTask.get(player.getUniqueId()).cancel();
        }
        this.ateGoldenApple.add(player.getUniqueId());
        this.ateTask.put(player.getUniqueId(), new BukkitRunnable() {
            public void run() {
                Assist.this.ateGoldenApple.remove(player.getUniqueId());
            }
        }.runTaskLater(API.getPlugin(), 2400L));
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    private void onPvP(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }
        if (entityDamageByEntityEvent.isCancelled()) {
            return;
        }
        final Player player = (Player)entityDamageByEntityEvent.getEntity();
        Player key = null;
        if (entityDamageByEntityEvent.getDamager() instanceof Player) {
            key = (Player)entityDamageByEntityEvent.getDamager();
        }
        else if (entityDamageByEntityEvent.getDamager() instanceof Arrow && ((Arrow)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
            key = (Player)((Arrow)entityDamageByEntityEvent.getDamager()).getShooter();
        }
        if (key == null) {
            return;
        }
        if (player == null) {
            return;
        }
        final double n = (this.playerTable.containsKey(player) && this.playerTable.get(player).containsKey(key)) ? this.playerTable.get(player).get(key) : 0.0;
        if (!this.playerTable.containsKey(player)) {
            this.playerTable.put(player, new HashMap<Player, Double>());
        }
        this.playerTable.get(player).put(key, n + entityDamageByEntityEvent.getFinalDamage());
    }
    
    @EventHandler
    private void onPlayerDeath(final PlayerDeathEvent playerDeathEvent) {
        final Player killed = playerDeathEvent.getKilled();
        final Player killer = playerDeathEvent.getKiller();
        if (killed == null || killer == null) {
            return;
        }
        final HashMap<Player, Integer> hashMap = new HashMap<Player, Integer>();
        final ArrayList<Player> list = new ArrayList<Player>();
        if (this.playerTable.size() != 0 && this.playerTable.containsKey(killed)) {
            for (final Map.Entry<Player, Double> entry : this.playerTable.get(killed).entrySet()) {
                if (killer != entry.getKey()) {
                    hashMap.put(entry.getKey(), this.contribution(killed, entry.getValue()));
                    list.add(entry.getKey());
                }
            }
            this.playerTable.remove(killed);
            Bukkit.getPluginManager().callEvent(new PlayerAssistEvent(killed, killer, list, hashMap));
        }
    }
    
    private int contribution(final Player player, final double n) {
        return (int)(n / 20.0 + (this.ateGoldenApple.contains(player.getUniqueId()) ? 4 : 0)) * 100;
    }
}
