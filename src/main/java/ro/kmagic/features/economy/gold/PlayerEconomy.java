package ro.kmagic.features.economy.gold;

import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.Main;
import ro.kmagic.libapi.API;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.entity.Player;

public class PlayerEconomy {
    private double balance;
    private final Player p;
    private static HashMap<UUID, PlayerEconomy> playerEconomy;
    
    public PlayerEconomy(final Player p2, final double balance) {
        this.balance = balance;
        this.p = p2;
        PlayerEconomy.playerEconomy.put(p2.getUniqueId(), this);
    }
    
    public static boolean isCached(final Player player) {
        return PlayerEconomy.playerEconomy.containsKey(player.getUniqueId());
    }
    
    public static PlayerEconomy get(final Player player) {
        return PlayerEconomy.playerEconomy.get(player.getUniqueId());
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public boolean hasEnough(final double n) {
        return n <= this.getBalance();
    }
    
    public boolean setBalance(final EconomyAction economyAction, final double balance) {
        if (!API.getDatabase().hasAccount(this.p.getUniqueId(), getTableName())) {
            this.p.sendMessage(Main.getMessages().getString("Economy.PlayerNotExist"));
            return false;
        }
        switch (economyAction) {
            case ADD: {
                this.balance = this.getBalance() + balance;
                PlayerStats.get(this.p).add(Stats.StatsType.GOLD_EARNED, (int)balance);
                PlayerPrestige.get(this.p).addGrindedGold((int)balance);
                break;
            }
            case SUBTRACT: {
                if (!this.hasEnough(balance)) {
                    this.p.sendMessage(Main.getMessages().getString("Economy.NotEnoughGold"));
                    return false;
                }
                this.balance = this.getBalance() - balance;
                break;
            }
            case SET: {
                this.balance = balance;
                break;
            }
        }
        API.getDatabase().setDouble(this.p.getUniqueId(), this.balance, "Balance", getTableName());
        return true;
    }
    
    public static String getTableName() {
        return "ThePitProfiles";
    }
    
    public static void removeAccount(final Player player) {
        PlayerEconomy.playerEconomy.remove(player.getUniqueId());
    }
    
    public static String format(final double d) {
        return d + " " + Main.getMessages().getString("Economy.Currency");
    }
    
    static {
        PlayerEconomy.playerEconomy = new HashMap<>();
    }
    
    public enum EconomyAction
    {
        ADD, 
        SUBTRACT, 
        SET
    }
}
