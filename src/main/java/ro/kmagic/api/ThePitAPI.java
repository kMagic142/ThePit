package ro.kmagic.api;

import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.Main;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import org.bukkit.entity.Player;

public class ThePitAPI {
    private final int api;
    private int xpAmplifier;
    private int goldAmplifier;
    
    public ThePitAPI() {
        this.api = 3;
        this.xpAmplifier = 1;
        this.goldAmplifier = 1;
    }
    
    public int getAPIVersion() {
        return this.api;
    }
    
    public PlayerEconomy getPlayerEconomy(final Player player) {
        return PlayerEconomy.get(player);
    }
    
    public String formatCoins(final double n) {
        return PlayerEconomy.format(n);
    }
    
    public boolean isInCombat(final Player player) {
        return Main.getCombatManager().inCombat(player);
    }
    
    public int getPlayerStats(final Player player, final Stats.StatsType statsType) {
        return PlayerStats.get(player).get(statsType);
    }
    
    public int getPlayerLevel(final Player player) {
        return player.getLevel();
    }
    
    public int getPlayerExp(final Player player) {
        return player.getTotalExperience();
    }
    
    public int getXPAmplifier() {
        return this.xpAmplifier;
    }
    
    public void setXpAmplifier(final int xpAmplifier) {
        this.xpAmplifier = xpAmplifier;
    }
    
    public int getGoldAmplifier() {
        return this.goldAmplifier;
    }
    
    public void setGoldAmplifier(final int goldAmplifier) {
        this.goldAmplifier = goldAmplifier;
    }
}
