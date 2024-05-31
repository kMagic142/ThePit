package ro.kmagic.features.perk;

import ro.kmagic.libapi.API;
import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;

public class PlayerPerk {
    private final Player player;
    private Perk.PerkType slot1;
    private Perk.PerkType slot2;
    private Perk.PerkType slot3;
    private Perk.PerkType slot4;
    private final List<Perk.PerkType> ownedPerks;
    private static HashMap<UUID, PlayerPerk> playerPerk;
    
    public PlayerPerk(final Player player, final Perk.PerkType slot1, final Perk.PerkType slot2, final Perk.PerkType slot3, final Perk.PerkType slot4, final List<Perk.PerkType> ownedPerks) {
        this.player = player;
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.slot4 = slot4;
        this.ownedPerks = ownedPerks;
        PlayerPerk.playerPerk.put(player.getUniqueId(), this);
    }
    
    public static boolean isCached(final Player player) {
        return PlayerPerk.playerPerk.containsKey(player.getUniqueId());
    }
    
    public static PlayerPerk get(final Player player) {
        return PlayerPerk.playerPerk.get(player.getUniqueId());
    }
    
    public Perk.PerkType getSlot(final int n) {
        switch (n) {
            case 1: {
                return this.slot1;
            }
            case 2: {
                return this.slot2;
            }
            case 3: {
                return this.slot3;
            }
            case 4: {
                return this.slot4;
            }
            default: {
                return null;
            }
        }
    }
    
    public void setSlot(final Perk.PerkType perkType, final int n) {
        switch (n) {
            case 1: {
                this.slot1 = perkType;
                break;
            }
            case 2: {
                this.slot2 = perkType;
                break;
            }
            case 3: {
                this.slot3 = perkType;
                break;
            }
            case 4: {
                this.slot4 = perkType;
                break;
            }
        }
    }
    
    public boolean isActivated(final Perk.PerkType perkType) {
        final ArrayList<Perk.PerkType> list = new ArrayList<Perk.PerkType>();
        for (int i = 1; i <= 4; ++i) {
            final Perk.PerkType slot = this.getSlot(i);
            if (slot != null) {
                list.add(slot);
            }
        }
        return list.contains(perkType);
    }
    
    public boolean isActivated(final Perk.PerkType other, final int n) {
        final Perk.PerkType slot = this.getSlot(n);
        return slot != null && slot.equals(other);
    }
    
    public boolean isBought(final Perk.PerkType perkType) {
        return this.ownedPerks.contains(perkType);
    }
    
    public static void removeAccount(final Player player) {
        PlayerPerk.playerPerk.remove(player.getUniqueId());
    }
    
    public void addBought(final Perk.PerkType obj) {
        this.ownedPerks.add(obj);
        API.getDatabase().setString(this.player.getUniqueId(), "true", obj + "_OWNED", Perk.getTableName());
    }
    
    public void removePerk(final Perk.PerkType perkType) {
        this.ownedPerks.remove(perkType);
    }
    
    public void deactivatePerk(final Perk.PerkType perkType) {
        for (int i = 1; i <= 4; ++i) {
            if (this.isActivated(perkType, i)) {
                this.setSlot(null, i);
            }
        }
    }
    
    static {
        PlayerPerk.playerPerk = new HashMap<UUID, PlayerPerk>();
    }
}
