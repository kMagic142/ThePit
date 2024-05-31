package ro.kmagic.support;

import java.io.File;
import ro.kmagic.libapi.database.utils.ResultSet;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.libapi.utils.FileManager;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.Main;
import org.bukkit.entity.Player;

public class MySQLConverter {
    public static final String bounty = "Bounty";
    
    public void convert(final Player player) {
        if (!Main.getConverterDatabase().hasAccount(player.getUniqueId(), PlayerEconomy.getTableName())) {
            return;
        }
        final FileManager fileManager = new FileManager(player.getUniqueId().toString(), "Accounts");
        fileManager.set(PlayerEconomy.getTableName() + "." + "Balance", Main.getConverterDatabase().getInt(player.getUniqueId(), "Balance", PlayerEconomy.getTableName()));
        fileManager.set(PlayerEconomy.getTableName() + "." + "Bounty", Main.getConverterDatabase().getInt(player.getUniqueId(), "Bounty", PlayerEconomy.getTableName()));
        final ResultSet resultSet = Main.getConverterDatabase().getResultSet(player.getUniqueId(), Perk.getTableName());
        final ResultSet resultSet2 = Main.getConverterDatabase().getResultSet(player.getUniqueId(), Stats.getTableName());
        for (final Perk.PerkType perkType : Perk.PerkType.values()) {
            fileManager.set(Perk.getTableName() + "." + perkType + "_OWNED", resultSet.getString(perkType + "_OWNED"));
            fileManager.set(Perk.getTableName() + "." + perkType + "_SLOT", resultSet.getInt(perkType + "_SLOT"));
        }
        for (final Stats.StatsType obj : Stats.StatsType.values()) {
            fileManager.set(Stats.getTableName() + "." + obj, resultSet2.getInt(obj.name()));
        }
        Main.getConverterDatabase().deletePlayer(player.getUniqueId(), PlayerEconomy.getTableName());
        Main.getConverterDatabase().deletePlayer(player.getUniqueId(), Perk.getTableName());
        Main.getConverterDatabase().deletePlayer(player.getUniqueId(), Stats.getTableName());
    }
    
    public boolean hasAccount(final Player player) {
        return new File("plugins/ThePit/Accounts", player.getUniqueId() + ".yml").exists();
    }
}
