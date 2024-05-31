package ro.kmagic.managers;

import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ro.kmagic.libapi.API;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import ro.kmagic.files.map.Region;

public class RegionManager extends Region implements Listener {
    private final boolean isSpawnRegionSetBoolean;
    private final Location spawnPos1;
    private final Location spawnPos2;
    
    public RegionManager() {
        API.registerEvent(this);
        this.isSpawnRegionSetBoolean = this.isSet(RegionType.SPAWN_REGION);
        this.spawnPos1 = this.getPos(RegionType.SPAWN_REGION, 1);
        this.spawnPos2 = this.getPos(RegionType.SPAWN_REGION, 2);
    }
    
    public boolean inRegion(final Location location, final Location location2, final Location location3) {
        final int min = Math.min(location2.getBlockX(), location3.getBlockX());
        final int min2 = Math.min(location2.getBlockY(), location3.getBlockY());
        final int min3 = Math.min(location2.getBlockZ(), location3.getBlockZ());
        final int max = Math.max(location2.getBlockX(), location3.getBlockX());
        final int max2 = Math.max(location2.getBlockY(), location3.getBlockY());
        final int max3 = Math.max(location2.getBlockZ(), location3.getBlockZ());
        return location != null && location.getWorld().equals(location2.getWorld()) && location2.getWorld().equals(location3.getWorld()) && min <= location.getBlockX() && max >= location.getBlockX() && min3 <= location.getBlockZ() && max3 >= location.getBlockZ() && min2 <= location.getBlockY() && max2 >= location.getBlockY();
    }
    
    @EventHandler
    private void onPlayerVsPlayer(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!this.isSpawnRegionSetBoolean) {
            return;
        }
        if (!(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)entityDamageByEntityEvent.getEntity();
        Player player2 = null;
        if (Main.getArena().getPlayers().contains(player.getUniqueId())) {
            if (entityDamageByEntityEvent.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                if (((Projectile)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
                    player2 = (Player)((Projectile)entityDamageByEntityEvent.getDamager()).getShooter();
                }
            }
            else if (entityDamageByEntityEvent.getDamager() instanceof Player) {
                player2 = (Player)entityDamageByEntityEvent.getDamager();
            }
        }
        if (player2 == null) {
            return;
        }
        if (!this.inRegion(player.getLocation(), this.spawnPos1, this.spawnPos2) && !this.inRegion(player2.getLocation(), this.spawnPos1, this.spawnPos2)) {
            return;
        }
        entityDamageByEntityEvent.setCancelled(true);
    }
    
    @EventHandler
    private void onPlayerPlace(final BlockPlaceEvent blockPlaceEvent) {
        final Player player = blockPlaceEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (!this.inRegion(player.getLocation(), this.spawnPos1, this.spawnPos2)) {
            return;
        }
        if (Main.getArena().getBuilders().contains(player.getUniqueId())) {
            return;
        }
        blockPlaceEvent.setCancelled(true);
    }
    
    @EventHandler
    private void onLiquidPlace(final PlayerBucketEmptyEvent playerBucketEmptyEvent) {
        final Player player = playerBucketEmptyEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (!this.inRegion(player.getLocation(), this.spawnPos1, this.spawnPos2)) {
            return;
        }
        playerBucketEmptyEvent.setCancelled(true);
    }
}
