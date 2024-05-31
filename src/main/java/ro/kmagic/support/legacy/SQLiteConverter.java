package ro.kmagic.support.legacy;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import ro.kmagic.libapi.utils.CustomError;
import java.util.Arrays;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.Main;
import ro.kmagic.libapi.utils.FileManager;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import org.bukkit.entity.Player;

public class SQLiteConverter {
    public static final String bounty = "Bounty";
    private final SQLite legacy;
    
    public SQLiteConverter() {
        this.legacy = SQLite.getInstance();
    }
    
    public void convert(final Player player) {
        if (!this.legacy.hasAccount(player, PlayerEconomy.getTableName())) {
            return;
        }
        final FileManager fileManager = new FileManager(player.getUniqueId().toString(), "Accounts");
        fileManager.set(PlayerEconomy.getTableName() + "." + "Balance", Main.getLegacyDatabase().getInt(player, "Balance", PlayerEconomy.getTableName()));
        fileManager.set(PlayerEconomy.getTableName() + "." + "Bounty", Main.getLegacyDatabase().getInt(player, "Bounty", PlayerEconomy.getTableName()));
        final ResultSet resultSet = Main.getLegacyDatabase().getResultSet(player, Perk.getTableName());
        final ResultSet resultSet2 = Main.getLegacyDatabase().getResultSet(player, Stats.getTableName());
        try {
            for (final Perk.PerkType perkType : Perk.PerkType.values()) {
                fileManager.set(Perk.getTableName() + "." + perkType + "_OWNED", resultSet.getString(perkType + "_OWNED"));
                fileManager.set(Perk.getTableName() + "." + perkType + "_SLOT", resultSet.getInt(perkType + "_SLOT"));
            }
            for (final Stats.StatsType obj : Stats.StatsType.values()) {
                fileManager.set(Stats.getTableName() + "." + obj, resultSet2.getInt(obj.name()));
            }
        }
        catch (SQLException ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't convert Legacy Database", "to the newer database system"));
        }
        Main.getLegacyDatabase().deletePlayer(player, PlayerEconomy.getTableName());
        Main.getLegacyDatabase().deletePlayer(player, Perk.getTableName());
        Main.getLegacyDatabase().deletePlayer(player, Stats.getTableName());
    }
    
    public boolean hasLegacyAccount(final Player player) {
        return new File("plugins/ThePit/Accounts", player.getUniqueId() + ".yml").exists();
    }
}
