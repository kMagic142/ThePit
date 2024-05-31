package ro.kmagic.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CombatManager implements Listener {
    private final HashMap<UUID, Integer> combatLog;
    private final HashMap<UUID, UUID> combatingPlayers;
    private final ArrayList<UUID> playerDisconnected;
    private final int combatTime;
    private final List<String> bannedCommands;
    private final String noAccess;
    
    public CombatManager() {
        this.combatLog = new HashMap<UUID, Integer>();
        this.combatingPlayers = new HashMap<UUID, UUID>();
        this.playerDisconnected = new ArrayList<UUID>();
        API.registerEvent(this);
        this.combatTime = Main.getSettings().getInt("CombatLog.Time");
        this.bannedCommands = Main.getSettings().getStringList("CombatLog.BannedCommands");
        this.noAccess = Main.getMessages().getString("CombatLog.NoAccess");
        new BukkitRunnable() {
            public void run() {
                final ArrayList<UUID> list = new ArrayList<UUID>();
                for (final UUID key : CombatManager.this.combatLog.keySet()) {
                    if (CombatManager.this.combatLog.get(key) >= CombatManager.this.combatTime) {
                        list.add(key);
                    }
                    else {
                        CombatManager.this.combatLog.put(key, CombatManager.this.combatLog.get(key) + 1);
                    }
                }
                for (final UUID uuid : list) {
                    CombatManager.this.combatLog.remove(uuid);
                    CombatManager.this.combatingPlayers.remove(uuid);
                }
            }
        }.runTaskTimer(API.getPlugin(), 0L, 20L);
    }
    
    @EventHandler(ignoreCancelled = true)
    private void onPvP(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)entityDamageByEntityEvent.getEntity();
        Player player2 = null;
        if (entityDamageByEntityEvent.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            if (((Projectile)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
                player2 = (Player)((Projectile)entityDamageByEntityEvent.getDamager()).getShooter();
            }
        }
        else if (entityDamageByEntityEvent.getDamager() instanceof Player) {
            player2 = (Player)entityDamageByEntityEvent.getDamager();
        }
        if (player2 == null || player2 == player) {
            return;
        }
        this.addCombat(player2, player);
        this.addCombat(player, player2);
    }
    
    @EventHandler
    private void onPlayerDeath(final PlayerDeathEvent playerDeathEvent) {
        if (Main.getArena().getPlayers().contains(playerDeathEvent.getKilled().getUniqueId())) {
            this.combatLog.remove(playerDeathEvent.getKilled().getUniqueId());
        }
    }
    
    @EventHandler
    private void onPlayerQuit(final PlayerQuitEvent playerQuitEvent) {
        this.quitPlayer(playerQuitEvent.getPlayer());
    }
    
    public void quitPlayer(final Player player) {
        if (!this.combatLog.containsKey(player.getUniqueId())) {
            return;
        }
        this.playerDisconnected.add(player.getUniqueId());
        final Player player2 = Bukkit.getPlayer(this.combatingPlayers.get(player.getUniqueId()));
        Bukkit.getPluginManager().callEvent(new PlayerDeathEvent(player2, player));
        player.getInventory().clear();
        final ItemStack build = Materials.AIR.getItem().build();
        player.getInventory().setHelmet(build);
        player.getInventory().setChestplate(build);
        player.getInventory().setLeggings(build);
        player.getInventory().setBoots(build);
        player.setHealth(0.0);
        Main.getBounty().executeBounty(player2, player);
        player.spigot().respawn();
        this.combatLog.remove(player.getUniqueId());
        this.playerDisconnected.remove(player.getUniqueId());
    }
    
    @EventHandler
    private void onPlayerDeath(final org.bukkit.event.entity.PlayerDeathEvent playerDeathEvent) {
        if (!this.combatLog.containsKey(playerDeathEvent.getEntity().getUniqueId())) {
            return;
        }
        playerDeathEvent.setKeepLevel(true);
        playerDeathEvent.setDeathMessage(null);
    }
    
    @EventHandler
    private void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        final Player player = playerJoinEvent.getPlayer();
        if (!player.isDead()) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                player.spigot().respawn();
            }
        }.runTaskLater(API.getPlugin(), 1L);
    }
    
    @EventHandler
    private void onPlayerCommand(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final Player player = playerCommandPreprocessEvent.getPlayer();
        if (player.isOp() || !this.combatLog.containsKey(player.getUniqueId())) {
            return;
        }
        for (String bannedCommand : this.bannedCommands) {
            if (playerCommandPreprocessEvent.getMessage().toLowerCase().startsWith("/" + bannedCommand.toLowerCase())) {
                playerCommandPreprocessEvent.setCancelled(true);
                player.sendMessage(this.noAccess);
            }
        }
    }
    
    public boolean inCombat(final Player player) {
        return this.combatLog.containsKey(player.getUniqueId());
    }
    
    public int getSeconds(final Player player) {
        if (this.inCombat(player)) {
            return this.combatLog.get(player.getUniqueId());
        }
        return 0;
    }
    
    private void addCombat(final Player player, final Player player2) {
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        player.setFlying(false);
        player.setAllowFlight(false);
        this.combatLog.put(player.getUniqueId(), 0);
        this.combatingPlayers.put(player.getUniqueId(), player2.getUniqueId());
    }
    
    public ArrayList<UUID> getPlayerDisconnected() {
        return this.playerDisconnected;
    }
    
    public void clearCombatList() {
        this.combatLog.clear();
    }
}
