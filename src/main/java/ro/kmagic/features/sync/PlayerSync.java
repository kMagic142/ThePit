package ro.kmagic.features.sync;

import ro.kmagic.Main;
import ro.kmagic.libapi.API;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.inventory.Inventory;
import java.util.UUID;

public class PlayerSync {
    private final UUID uuid;
    private Inventory inventory;
    private Inventory enderchest;
    private int xp;
    private static HashMap<UUID, PlayerSync> playerSync;
    
    public PlayerSync(final UUID uuid, final Inventory inventory, final Inventory enderchest, final int xp) {
        this.uuid = uuid;
        this.inventory = inventory;
        this.enderchest = enderchest;
        this.xp = xp;
        PlayerSync.playerSync.put(uuid, this);
    }
    
    public static boolean isCached(final Player player) {
        return PlayerSync.playerSync.containsKey(player.getUniqueId());
    }
    
    public static PlayerSync get(final Player player) {
        return PlayerSync.playerSync.get(player.getUniqueId());
    }
    
    public void restore() {
        final Player player = Bukkit.getPlayer(this.uuid);
        player.getInventory().setContents(this.inventory.getContents());
        player.getEnderChest().setContents(this.enderchest.getContents());
        player.setTotalExperience(this.xp);
    }
    
    public static Set<UUID> getUUIDs() {
        return PlayerSync.playerSync.keySet();
    }
    
    public void save() {
        final Player player = Bukkit.getPlayer(this.uuid);
        this.inventory = player.getInventory();
        this.enderchest = player.getEnderChest();
        this.xp = player.getTotalExperience();
        API.getDatabase().prepareStatement("UPDATE ThePitPlayerSync SET " + (Sync.SyncType.PLAYER_INVENTORY.name() + " = '" + Main.getSync().getString(player.getInventory()) + "', " + (Sync.SyncType.PLAYER_ENDERCHEST.name() + " = '" + Main.getSync().getString(player.getEnderChest()) + "', ") + (Sync.SyncType.PLAYER_XP.name() + " = " + player.getTotalExperience() + " ")) + "WHERE UUID = '" + player.getUniqueId() + "';");
    }
    
    static {
        PlayerSync.playerSync = new HashMap<UUID, PlayerSync>();
    }
}
