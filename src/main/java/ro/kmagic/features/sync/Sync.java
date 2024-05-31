package ro.kmagic.features.sync;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.database.utils.Column;
import ro.kmagic.libapi.database.utils.ResultSet;
import ro.kmagic.libapi.utils.CustomError;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import ro.kmagic.Main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class Sync {
    private static final String table = "ThePitPlayerSync";
    
    public void run() {
        new BukkitRunnable() {
            public void run() {
                if (Main.getArena().getPlayers().size() == 0) {
                    return;
                }
                for (UUID uuid : PlayerSync.getUUIDs()) {
                    PlayerSync.get(Bukkit.getPlayer(uuid)).save();
                }
            }
        }.runTaskTimer(API.getPlugin(), 20L, 36000L);
    }
    
    public void addPlayer(final Player player) {
        if (!API.getDatabase().hasAccount(player.getUniqueId(), "ThePitPlayerSync")) {
            Main.getArena().giveKit(player, true, 45);
            Main.getPerk().giveItems(player);
            new BukkitRunnable() {
                public void run() {
                    new PlayerSync(player.getUniqueId(), player.getInventory(), player.getEnderChest(), player.getTotalExperience());
                }
            }.runTaskLater(API.getPlugin(), 10L);
            return;
        }
        final ResultSet resultSet = API.getDatabase().getResultSet(player.getUniqueId(), "ThePitPlayerSync");
        final String string = resultSet.getString(SyncType.PLAYER_INVENTORY.name());
        final String string2 = resultSet.getString(SyncType.PLAYER_ENDERCHEST.name());
        final int int1 = resultSet.getInt(SyncType.PLAYER_XP.name());
        final Inventory inventory = this.getInventory(string);
        final Inventory inventory2 = this.getInventory(string2);
        if (inventory == null || inventory2 == null || int1 == 0) {
            return;
        }
        player.getInventory().setContents(inventory.getContents());
        player.getEnderChest().setContents(inventory2.getContents());
        player.setTotalExperience(int1);
        Main.getArena().giveKit(player, false, 45);
        Main.getPerk().giveItems(player);
        new PlayerSync(player.getUniqueId(), player.getInventory(), player.getEnderChest(), player.getTotalExperience());
    }
    
    public void removePlayer(final Player player) {
        if (!PlayerSync.isCached(player)) {
            return;
        }
        PlayerSync.get(player).save();
        player.getInventory().clear();
        player.getEnderChest().clear();
    }
    
    public static void createTable() {
        API.getDatabase().createTable("ThePitPlayerSync", Arrays.asList(new Column("Player").setType(Column.ColumnType.VARCHAR).setLength(30), new Column("UUID").setType(Column.ColumnType.VARCHAR).setLength(50).setNotNull().setPrimaryKey()));
        API.getDatabase().addColumn("ThePitPlayerSync", new Column(SyncType.PLAYER_INVENTORY.name()).setType(Column.ColumnType.LONG_TEXT));
        API.getDatabase().addColumn("ThePitPlayerSync", new Column(SyncType.PLAYER_ENDERCHEST.name()).setType(Column.ColumnType.LONG_TEXT));
        API.getDatabase().addColumn("ThePitPlayerSync", new Column(SyncType.PLAYER_XP.name()).setType(Column.ColumnType.INT).setLength(11).setDefault(0));
    }
    
    public String getString(final Inventory inventory) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeInt(inventory.getSize());
            for (int i = 0; i < inventory.getSize(); ++i) {
                bukkitObjectOutputStream.writeObject(inventory.getItem(i));
            }
            bukkitObjectOutputStream.close();
            return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't convert Player's Inventory to String!", "Probably the inventory is null."));
            return null;
        }
    }
    
    public Inventory getInventory(final String s) {
        if (s == null) {
            return null;
        }
        try {
            final BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(new ByteArrayInputStream(Base64Coder.decodeLines(s)));
            final int int1 = bukkitObjectInputStream.readInt();
            if (int1 != 9 && int1 != 18 && int1 != 27 && int1 != 36 && int1 != 45 && int1 != 54) {
                return null;
            }
            final Inventory inventory = Bukkit.createInventory(null, int1);
            for (int i = 0; i < inventory.getSize(); ++i) {
                inventory.setItem(i, (ItemStack)bukkitObjectInputStream.readObject());
            }
            bukkitObjectInputStream.close();
            return inventory;
        }
        catch (ClassNotFoundException | IOException ex2) {
            CustomError.print(ex2, this.getClass(), Arrays.asList("Can't convert Player's Inventory to String!", "Probably the inventory is null."));
            return null;
        }
    }
    
    public static String getTableName() {
        return "ThePitPlayerSync";
    }
    
    public enum SyncType
    {
        PLAYER_INVENTORY, 
        PLAYER_ENDERCHEST, 
        PLAYER_XP
    }
}
