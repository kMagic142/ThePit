package ro.kmagic.features;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import ro.kmagic.Main;
import ro.kmagic.commands.BountyCommand;
import ro.kmagic.features.economy.gold.PlayerEconomy;

import java.util.HashMap;
import java.util.UUID;

public class Bounty implements Listener {
    private static final String columnName = "Bounty";
    private final HashMap<UUID, Integer> playerBounty;
    private final String bountyClaimedMessage;
    private final String bountyAnnounceMessage;
    private final String playerNotExist;
    private final String notEnoughBounty;
    private final boolean isBountyAuraEnabled;
    
    public Bounty() {
        this.playerBounty = new HashMap<UUID, Integer>();
        API.registerEvent(this);
        this.bountyClaimedMessage = Main.getMessages().getString("Bounty.Claimed");
        this.isBountyAuraEnabled = Main.getSettings().getBoolean("Bounty.Aura");
        this.bountyAnnounceMessage = Main.getMessages().getString("Bounty.Announce");
        this.playerNotExist = Main.getMessages().getString("Economy.PlayerNotExist");
        this.notEnoughBounty = Main.getMessages().getString("Bounty.Not-Enough-Bounty");
    }
    
    @EventHandler
    private void onPlayerDeath(final PlayerDeathEvent playerDeathEvent) {
        final Player killed = playerDeathEvent.getKilled();
        if (killed.getKiller() == null) {
            return;
        }
        if (!Main.getArena().getPlayers().contains(killed.getUniqueId())) {
            return;
        }
        this.executeBounty(killed.getKiller(), killed);
    }
    
    public void executeBounty(final Player player, final Player player2) {
        if (this.playerBounty.containsKey(player2.getUniqueId())) {
            final int intValue = this.playerBounty.get(player2.getUniqueId());
            this.setBounty(player2, 0, false);
            if (player == null) {
                return;
            }
            if (!player2.isOnline()) {
                return;
            }
            PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, intValue);
        }
        if (!Main.getStreakManager().hasStreak(player)) {
            return;
        }
        final int bounty = this.getBounty(player);
        final int n = (int)Main.getStreakManager().getStreak(player);
        int n2 = 0;
        if (bounty >= 5000) {
            return;
        }
        switch (n) {
            case 5:
            case 15: {
                n2 = 50;
                break;
            }
            case 20: {
                n2 = 150;
                break;
            }
            case 30: {
                n2 = 250;
                break;
            }
            case 40: {
                n2 = 500;
                break;
            }
            case 50: {
                n2 = 1250;
                break;
            }
            case 60: {
                n2 = 2500;
                break;
            }
        }
        if (n2 != 0) {
            this.setBounty(player, bounty + n2, true);
        }
    }
    
    public void setBounty(final Player player, final int n, final boolean b) {
        if (n == 0) {
            this.playerBounty.remove(player.getUniqueId());
        }
        else {
            this.playerBounty.put(player.getUniqueId(), n);
        }
        new BukkitRunnable() {
            public void run() {
                API.getDatabase().setInt(player.getUniqueId(), n, Bounty.getColumnName(), PlayerEconomy.getTableName());
            }
        }.runTaskAsynchronously(API.getPlugin());
    }
    
    public boolean setPlayerBounty(final Player player, final BountyCommand.BountyAction bountyAction, final int n) {
        int n2 = 0;
        if (!API.getDatabase().hasAccount(player.getUniqueId(), PlayerEconomy.getTableName())) {
            player.sendMessage(this.playerNotExist);
            return false;
        }
        switch (bountyAction) {
            case ADD: {
                n2 = this.getBounty(player) + n;
                break;
            }
            case SUBTRACT: {
                if (!this.hasEnough(player, n)) {
                    player.sendMessage(this.notEnoughBounty);
                    return false;
                }
                n2 = this.getBounty(player) - n;
                break;
            }
            case SET: {
                n2 = n;
                break;
            }
        }
        this.setBounty(player, n2, false);
        return true;
    }
    
    public boolean hasBounty(final Player player) {
        return this.playerBounty.containsKey(player.getUniqueId());
    }
    
    public boolean hasEnough(final Player player, final int n) {
        return n <= this.getBounty(player);
    }
    
    public int getBounty(final Player player) {
        if (!this.hasBounty(player)) {
            return 0;
        }
        return this.playerBounty.get(player.getUniqueId());
    }
    
    public String format(final int i) {
        return ChatColor.translateAlternateColorCodes('&', "&6&l" + i + "g");
    }
    
    public static String getColumnName() {
        return "Bounty";
    }
}
