package ro.kmagic.features.stats;

import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import ro.kmagic.api.events.player.PlayerGoldenHeadEatEvent;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.files.map.Region;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ro.kmagic.libapi.events.player.PlayerAssistEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import ro.kmagic.Main;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import ro.kmagic.libapi.API;
import java.util.UUID;
import java.util.ArrayList;
import org.bukkit.event.Listener;

public class StatsEvents implements Listener {
    private final ArrayList<UUID> pitJumpCooldown;
    
    public StatsEvents() {
        this.pitJumpCooldown = new ArrayList<UUID>();
        API.registerEvent(this);
    }
    
    @EventHandler
    private void onKill(final PlayerDeathEvent playerDeathEvent) {
        if (playerDeathEvent.getKiller() == null) {
            return;
        }
        final Player killed = playerDeathEvent.getKilled();
        final Player killer = playerDeathEvent.getKiller();
        if (!Main.getArena().getPlayers().contains(killer.getUniqueId())) {
            return;
        }
        final PlayerStats value = PlayerStats.get(killer);
        final PlayerStats value2 = PlayerStats.get(killed);
        value.add(Stats.StatsType.KILLS, 1);
        value2.add(Stats.StatsType.DEATHS, 1);
        final int n = (int)Main.getStreakManager().getStreak(killer);
        if (value.get(Stats.StatsType.HIGHEST_STREAK) < n) {
            value.add(Stats.StatsType.HIGHEST_STREAK, n);
        }
    }
    
    @EventHandler
    private void onAssist(final PlayerAssistEvent playerAssistEvent) {
        for (Player player : playerAssistEvent.getDamagers()) {
            PlayerStats.get(player).add(Stats.StatsType.ASSISTS, 1);
        }
    }
    
    @EventHandler
    private void onSwordHits(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.isCancelled()) {
            return;
        }
        if (!(entityDamageByEntityEvent.getDamager() instanceof Player) || !(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)entityDamageByEntityEvent.getDamager();
        final Player player2 = (Player)entityDamageByEntityEvent.getEntity();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) || !Main.getArena().getPlayers().contains(player2.getUniqueId())) {
            return;
        }
        if (!player.getItemInHand().getType().name().contains("SWORD")) {
            return;
        }
        PlayerStats.get(player).add(Stats.StatsType.SWORD_HITS, 1);
    }
    
    @EventHandler
    private void onArrowShot(final EntityShootBowEvent entityShootBowEvent) {
        if (!(entityShootBowEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)entityShootBowEvent.getEntity();
        if (Main.getArena().getPlayers().contains(player.getUniqueId())) {
            PlayerStats.get(player).add(Stats.StatsType.ARROWS_SHOT, 1);
        }
    }
    
    @EventHandler
    private void onArrowHit(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!(entityDamageByEntityEvent.getDamager() instanceof Arrow) || !(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)((Projectile)entityDamageByEntityEvent.getDamager()).getShooter();
        final Player obj = (Player)entityDamageByEntityEvent.getEntity();
        if (Main.getArena().getPlayers().contains(player.getUniqueId()) && !player.equals(obj)) {
            PlayerStats.get(player).add(Stats.StatsType.ARROW_HITS, 1);
        }
    }
    
    @EventHandler
    private void onEntDmgByEnt(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.isCancelled() || !(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player obj = (Player)entityDamageByEntityEvent.getEntity();
        Player player = null;
        if (!Main.getArena().getPlayers().contains(obj.getUniqueId())) {
            return;
        }
        final int n = (int)entityDamageByEntityEvent.getFinalDamage();
        final PlayerStats value = PlayerStats.get(obj);
        if (entityDamageByEntityEvent.getDamager() instanceof Arrow && ((Arrow)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
            player = (Player)((Arrow)entityDamageByEntityEvent.getDamager()).getShooter();
            PlayerStats.get(player).add(Stats.StatsType.BOW_DAMAGE_DEALT, n);
            value.add(Stats.StatsType.BOW_DAMAGE_TAKEN, n);
        }
        else if (entityDamageByEntityEvent.getDamager() instanceof Player) {
            player = (Player)entityDamageByEntityEvent.getDamager();
            PlayerStats.get(player).add(Stats.StatsType.MELEE_DAMAGE_DEALT, n);
            value.add(Stats.StatsType.MELEE_DAMAGE_TAKEN, n);
        }
        if (player == null || !player.equals(obj)) {
            return;
        }
        PlayerStats.get(player).add(Stats.StatsType.DAMAGE_DEALT, n);
        value.add(Stats.StatsType.DAMAGE_TAKEN, n);
    }
    
    @EventHandler
    private void onLeftClicks(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        if (playerInteractEvent.getAction().name().contains("LEFT") && Main.getArena().getPlayers().contains(player.getUniqueId())) {
            PlayerStats.get(player).add(Stats.StatsType.LEFT_CLICKS, 1);
        }
    }
    
    @EventHandler
    private void onChatMessages(final AsyncPlayerChatEvent asyncPlayerChatEvent) {
        if (Main.getArena().getPlayers().contains(asyncPlayerChatEvent.getPlayer().getUniqueId())) {
            PlayerStats.get(asyncPlayerChatEvent.getPlayer()).add(Stats.StatsType.CHAT_MESSAGES, 1);
        }
    }
    
    @EventHandler
    private void onBlocksPlaced(final BlockPlaceEvent blockPlaceEvent) {
        if (blockPlaceEvent.isCancelled()) {
            return;
        }
        if (Main.getArena().getPlayers().contains(blockPlaceEvent.getPlayer().getUniqueId())) {
            PlayerStats.get(blockPlaceEvent.getPlayer()).add(Stats.StatsType.BLOCKS_PLACED, 1);
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    private void onBlocksBroken(final BlockBreakEvent blockBreakEvent) {
        if (blockBreakEvent.isCancelled()) {
            return;
        }
        if (Main.getArena().getPlayers().contains(blockBreakEvent.getPlayer().getUniqueId())) {
            PlayerStats.get(blockBreakEvent.getPlayer()).add(Stats.StatsType.BLOCKS_BROKEN, 1);
        }
    }
    
    @EventHandler
    private void onPitJump(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        if (this.pitJumpCooldown.contains(player.getUniqueId()) || !Main.getRegionManager().isSet(Region.RegionType.PIT_HOLE_REGION) || !Main.getArena().getPlayers().contains(player.getUniqueId()) || !Main.getRegionManager().inRegion(player.getLocation(), Main.getRegionManager().getPos(Region.RegionType.PIT_HOLE_REGION, 1), Main.getRegionManager().getPos(Region.RegionType.PIT_HOLE_REGION, 2))) {
            return;
        }
        this.pitJumpCooldown.add(player.getUniqueId());
        PlayerStats.get(player).add(Stats.StatsType.JUMPS_INTO_PIT, 1);
        new BukkitRunnable() {
            public void run() {
                StatsEvents.this.pitJumpCooldown.remove(player.getUniqueId());
            }
        }.runTaskLater(API.getPlugin(), 60L);
    }
    
    @EventHandler
    private void onGoldenApplesEaten(final PlayerItemConsumeEvent playerItemConsumeEvent) {
        final Player player = playerItemConsumeEvent.getPlayer();
        if (Main.getArena().getPlayers().contains(player.getUniqueId()) && playerItemConsumeEvent.getItem().getType().equals(Material.GOLDEN_APPLE)) {
            PlayerStats.get(player).add(Stats.StatsType.GOLDEN_APPLES_EATEN, 1);
        }
    }
    
    @EventHandler
    private void onGoldenHeadEat(final PlayerGoldenHeadEatEvent playerGoldenHeadEatEvent) {
        PlayerStats.get(playerGoldenHeadEatEvent.getPlayer()).add(Stats.StatsType.GOLDEN_HEADS_EATEN, 1);
    }
    
    @EventHandler
    private void onLavaBucketsEmptied(final PlayerBucketEmptyEvent playerBucketEmptyEvent) {
        final Player player = playerBucketEmptyEvent.getPlayer();
        if (!playerBucketEmptyEvent.isCancelled() && Main.getArena().getPlayers().contains(player.getUniqueId())) {
            PlayerStats.get(player).add(Stats.StatsType.LAVA_BUCKETS_EMPTIED, 1);
        }
    }
    
    @EventHandler
    private void onFishingRodsLaunched(final PlayerFishEvent playerFishEvent) {
        if (Main.getArena().getPlayers().contains(playerFishEvent.getPlayer().getUniqueId())) {
            PlayerStats.get(playerFishEvent.getPlayer()).add(Stats.StatsType.FISHING_RODS_LAUNCHED, 1);
        }
    }
}
