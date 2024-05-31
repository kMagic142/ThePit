package ro.kmagic.features.prestige;

import org.bukkit.ChatColor;
import ro.kmagic.features.economy.renown.RenownEconomy;
import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public class PlayerPrestige {
    private final UUID uuid;
    private int prestige;
    private int grindedGold;
    private static HashMap<UUID, PlayerPrestige> prestigeMap;
    
    public PlayerPrestige(final Player player, final int prestige, final int grindedGold) {
        this.uuid = player.getUniqueId();
        this.prestige = prestige;
        this.grindedGold = grindedGold;
        PlayerPrestige.prestigeMap.put(player.getUniqueId(), this);
    }
    
    public static boolean isCached(final Player player) {
        return PlayerPrestige.prestigeMap.containsKey(player.getUniqueId());
    }
    
    public static PlayerPrestige get(final Player player) {
        return PlayerPrestige.prestigeMap.get(player.getUniqueId());
    }
    
    public int getGrindedGold() {
        return this.grindedGold;
    }
    
    public int getPrestige() {
        return this.prestige;
    }
    
    public void addGrindedGold(final int n) {
        this.grindedGold += n;
        new BukkitRunnable() {
            public void run() {
                API.getDatabase().setInt(PlayerPrestige.this.uuid, PlayerPrestige.this.grindedGold, "Grinded", RenownEconomy.getTableName());
            }
        }.runTask(API.getPlugin());
    }
    
    public void resetGrindedGold() {
        this.grindedGold = 0;
        API.getDatabase().setInt(this.uuid, 0, "Grinded", RenownEconomy.getTableName());
    }
    
    public void setPrestige(final int prestige) {
        this.prestige = prestige;
        API.getDatabase().setInt(this.uuid, this.prestige, "Prestige", RenownEconomy.getTableName());
    }
    
    public void levelUpPrestige() {
        ++this.prestige;
        API.getDatabase().setInt(this.uuid, this.prestige, "Prestige", RenownEconomy.getTableName());
    }
    
    public static String format(final int n) {
        String string = "";
        int i;
        for (i = n; i >= 10; i -= 10) {
            string += formatString(10);
        }
        return ChatColor.translateAlternateColorCodes('&', "&e" + (string + formatString(i)));
    }
    
    private static String formatString(final int n) {
        String s = "";
        switch (n) {
            case 1: {
                s = "I";
                break;
            }
            case 2: {
                s = "II";
                break;
            }
            case 3: {
                s = "III";
                break;
            }
            case 4: {
                s = "IV";
                break;
            }
            case 5: {
                s = "V";
                break;
            }
            case 6: {
                s = "VI";
                break;
            }
            case 7: {
                s = "VII";
                break;
            }
            case 8: {
                s = "VIII";
                break;
            }
            case 9: {
                s = "IX";
                break;
            }
            case 10: {
                s = "X";
                break;
            }
        }
        return s;
    }
    
    public static int getRenownReward(final int n) {
        if (n <= 4) {
            return 10;
        }
        if (n <= 10) {
            return 20;
        }
        if (n <= 14) {
            return 30;
        }
        if (n <= 24) {
            return 40;
        }
        return 50;
    }
    
    public static int getRequiredGrindedGold(final int n) {
        switch (n) {
            case 1: {
                return 10000;
            }
            case 2: {
                return 22000;
            }
            case 3: {
                return 24000;
            }
            case 4: {
                return 26000;
            }
            case 5: {
                return 28000;
            }
            case 6: {
                return 30000;
            }
            case 7: {
                return 70000;
            }
            case 8: {
                return 80000;
            }
            case 9: {
                return 100000;
            }
            case 10: {
                return 120000;
            }
            case 11: {
                return 160000;
            }
            case 12: {
                return 200000;
            }
            case 13: {
                return 240000;
            }
            case 14: {
                return 280000;
            }
            case 15: {
                return 320000;
            }
            case 16: {
                return 360000;
            }
            case 17: {
                return 440000;
            }
            default: {
                return 480000;
            }
        }
    }
    
    static {
        PlayerPrestige.prestigeMap = new HashMap<UUID, PlayerPrestige>();
    }
}
