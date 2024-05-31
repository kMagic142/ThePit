package ro.kmagic.features.economy.renown;

import ro.kmagic.Main;
import ro.kmagic.libapi.API;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.entity.Player;

public class RenownEconomy {
    private int renown;
    private final Player p;
    private static HashMap<UUID, RenownEconomy> renownEconomy;
    
    public RenownEconomy(final Player p2, final int renown) {
        this.renown = renown;
        this.p = p2;
        RenownEconomy.renownEconomy.put(p2.getUniqueId(), this);
    }
    
    public static boolean isCached(final Player player) {
        return RenownEconomy.renownEconomy.containsKey(player.getUniqueId());
    }
    
    public static RenownEconomy get(final Player player) {
        return RenownEconomy.renownEconomy.get(player.getUniqueId());
    }
    
    public int getRenown() {
        return this.renown;
    }
    
    public boolean hasEnough(final int n) {
        return n <= this.getRenown();
    }
    
    public boolean setBalance(final RenownAction renownAction, final int renown) {
        if (!API.getDatabase().hasAccount(this.p.getUniqueId(), getTableName())) {
            this.p.sendMessage(Main.getMessages().getString("Economy.PlayerNotExist"));
            return false;
        }
        switch (renownAction) {
            case ADD: {
                this.renown = this.getRenown() + renown;
                break;
            }
            case SUBTRACT: {
                if (!this.hasEnough(renown)) {
                    this.p.sendMessage(Main.getMessages().getString("Economy.Not-Enough-Renown"));
                    return false;
                }
                this.renown = this.getRenown() - renown;
                break;
            }
            case SET: {
                this.renown = renown;
                break;
            }
        }
        API.getDatabase().setInt(this.p.getUniqueId(), this.renown, "Renown", getTableName());
        return true;
    }
    
    public static String getTableName() {
        return "ThePitPrestige";
    }
    
    public static void removeAccount(final Player player) {
        RenownEconomy.renownEconomy.remove(player.getUniqueId());
    }
    
    public static String format(final int i) {
        return i + " " + Main.getMessages().getString("Economy.Renown-Currency");
    }
    
    static {
        RenownEconomy.renownEconomy = new HashMap<>();
    }
    
    public enum RenownAction
    {
        ADD, 
        SUBTRACT, 
        SET
    }
}
