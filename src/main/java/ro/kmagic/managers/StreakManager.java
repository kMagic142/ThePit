package ro.kmagic.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.events.player.PlayerAssistEvent;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import ro.kmagic.Main;
import ro.kmagic.api.events.player.PlayerStreakEvent;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.chatoption.PlayerChatOption;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PlayerPerk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class StreakManager implements Listener {
    private final HashMap<UUID, Double> playerStreak;
    private final ArrayList<Integer> killAnnounce;
    private final String streakAnnounceMessage;

    public StreakManager() {
        this.playerStreak = new HashMap<>();
        this.killAnnounce = new ArrayList<>(Arrays.asList(5, 10, 15, 20, 25, 30, 40, 50, 65, 80, 100));
        API.registerEvent(this);
        this.streakAnnounceMessage = Main.getMessages().getString("KillStreak.Message");
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerKill(final PlayerDeathEvent playerDeathEvent) {
        final Player killed = playerDeathEvent.getKilled();
        if (!Main.getArena().getPlayers().contains(killed.getUniqueId())) {
            return;
        }
        this.playerStreak.remove(killed.getUniqueId());
        if (playerDeathEvent.getKiller() == null) {
            return;
        }
        final Player killer = playerDeathEvent.getKiller();
        this.playerStreak.put(killer.getUniqueId(), this.playerStreak.getOrDefault(killer.getUniqueId(), 0.0) + 1.0);
        new BukkitRunnable() {
            public void run() {
                if (!killer.isOnline()) {
                    return;
                }
                if (!StreakManager.this.playerStreak.containsKey(killer.getUniqueId())) {
                    return;
                }
                final double doubleValue = StreakManager.this.playerStreak.get(killer.getUniqueId());
                final String replace = String.valueOf(doubleValue).replace(".0", "");
                if (StreakManager.this.killAnnounce.contains((int)doubleValue)) {
                    for (final UUID uuid : Main.getArena().getPlayers()) {
                        if (PlayerChatOption.get(Bukkit.getPlayer(uuid)).isEnabled(ChatOption.ChatOptionType.STREAKS)) {
                            Bukkit.getPlayer(uuid).sendMessage(StreakManager.this.streakAnnounceMessage.replace("%kills%", replace).replace("%player%", Main.getXpTag().get(killer) + " " + killer.getName()));
                        }
                    }
                    Bukkit.getPluginManager().callEvent(new PlayerStreakEvent(killer, doubleValue));
                }
            }
        }.runTaskLater(API.getPlugin(), 5L);
    }
    
    @EventHandler
    private void onPlayerAssist(final PlayerAssistEvent playerAssistEvent) {
        if (!Main.getArena().getPlayers().contains(playerAssistEvent.getVictim().getUniqueId())) {
            return;
        }
        if (playerAssistEvent.getKiller() == null) {
            return;
        }
        for (final Player player : playerAssistEvent.getDamagers()) {
            if (Main.getArena().getPlayers().contains(player.getUniqueId())) {
                if (!PlayerPerk.get(player).isActivated(Perk.PerkType.ASSIST_STREAKER)) {
                    return;
                }
                this.playerStreak.put(player.getUniqueId(), this.playerStreak.getOrDefault(player.getUniqueId(), 0.0) + 0.1);
                new BukkitRunnable() {
                    public void run() {
                        final double doubleValue = StreakManager.this.playerStreak.get(player.getUniqueId());
                        final String replace = String.valueOf(doubleValue).replace(".0", "");
                        if (StreakManager.this.killAnnounce.contains((int)doubleValue)) {
                            for (final UUID uuid : Main.getArena().getPlayers()) {
                                if (PlayerChatOption.get(Bukkit.getPlayer(uuid)).isEnabled(ChatOption.ChatOptionType.STREAKS)) {
                                    Bukkit.getPlayer(uuid).sendMessage(StreakManager.this.streakAnnounceMessage.replace("%kills%", replace).replace("%player%", Main.getXpTag().get(player) + " " + player.getName()));
                                }
                            }
                            Bukkit.getPluginManager().callEvent(new PlayerStreakEvent(player, doubleValue));
                        }
                    }
                }.runTaskLater(API.getPlugin(), 5L);
            }
        }
    }
    
    @EventHandler
    private void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        this.playerStreak.put(playerJoinEvent.getPlayer().getUniqueId(), 0.0);
    }
    
    @EventHandler
    private void onPlayerCommand(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final String lowerCase = playerCommandPreprocessEvent.getMessage().toLowerCase();
        if (!lowerCase.contains("spawn") && !lowerCase.contains("respawn")) {
            return;
        }
        if (!Main.getCombatManager().inCombat(playerCommandPreprocessEvent.getPlayer())) {
            this.playerStreak.put(playerCommandPreprocessEvent.getPlayer().getUniqueId(), 0.0);
        }
    }
    
    public boolean hasStreak(final Player player) {
        return player != null && this.playerStreak.containsKey(player.getUniqueId());
    }
    
    public double getStreak(final Player player) {
        if (!this.hasStreak(player)) {
            return 0.0;
        }
        return this.playerStreak.get(player.getUniqueId());
    }
}
