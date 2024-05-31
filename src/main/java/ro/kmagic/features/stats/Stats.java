package ro.kmagic.features.stats;

import java.util.Arrays;
import ro.kmagic.libapi.database.utils.Column;
import ro.kmagic.libapi.database.utils.ResultSet;
import ro.kmagic.libapi.API;
import java.util.HashMap;
import org.bukkit.entity.Player;

public class Stats {
    private static final String table = "ThePitStats";
    
    public void addPlayer(final Player player) {
        final HashMap<StatsType, Integer> hashMap = new HashMap<StatsType, Integer>();
        final ResultSet resultSet = API.getDatabase().getResultSet(player.getUniqueId(), "ThePitStats");
        for (final StatsType key : StatsType.values()) {
            hashMap.put(key, resultSet.getInt(key.name()));
        }
        resultSet.close();
        new PlayerStats(player, hashMap);
    }
    
    public static String getTableName() {
        return "ThePitStats";
    }
    
    public static void createTable() {
        API.getDatabase().createTable("ThePitStats", Arrays.asList(new Column("Player").setType(Column.ColumnType.VARCHAR).setLength(30), new Column("UUID").setType(Column.ColumnType.VARCHAR).setLength(50).setPrimaryKey()));
        final StatsType[] values = StatsType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            API.getDatabase().addColumn("ThePitStats", new Column(values[i].name()).setType(Column.ColumnType.INT).setLength(11).setDefault(0));
        }
    }
    
    public static void createFlatFileAccount(final Player player) {
        final StatsType[] values = StatsType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            API.getDatabase().setInt(player.getUniqueId(), 0, values[i].name(), "ThePitStats");
        }
    }
    
    public enum StatsType
    {
        BOW_DAMAGE_TAKEN, 
        DAMAGE_TAKEN, 
        DEATHS, 
        MELEE_DAMAGE_TAKEN, 
        BLOCKS_BROKEN, 
        BLOCKS_PLACED, 
        CHAT_MESSAGES, 
        FISHING_RODS_LAUNCHED, 
        GOLDEN_APPLES_EATEN, 
        JUMPS_INTO_PIT, 
        LAVA_BUCKETS_EMPTIED, 
        LEFT_CLICKS, 
        ARROW_HITS, 
        ARROWS_SHOT, 
        ASSISTS, 
        BOW_DAMAGE_DEALT, 
        DIAMOND_ITEMS_PURCHASED, 
        LAUNCHES, 
        DAMAGE_DEALT, 
        HIGHEST_STREAK, 
        KILLS, 
        MELEE_DAMAGE_DEALT, 
        SWORD_HITS, 
        GOLD_EARNED, 
        GOLDEN_HEADS_EATEN
    }
}
