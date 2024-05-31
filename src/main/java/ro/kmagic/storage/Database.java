package ro.kmagic.storage;

import ro.kmagic.libapi.database.utils.MySQLCredentials;
import ro.kmagic.features.economy.renown.RenownEconomy;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.sync.Sync;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.stats.Stats;
import java.util.Arrays;
import ro.kmagic.libapi.database.utils.Column;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.libapi.API;
import ro.kmagic.Main;

public class Database {
    public Database() {
        if (Main.getSettings().getBoolean("MySQL.Enabled")) {
            final MySQLCredentials mySQLCredentials = ro.kmagic.libapi.database.Database.getMySQLCredentials();
            if (Main.getSettings().get("MySQL.Enabled") != null) {
                mySQLCredentials.setBoolean("Enabled", Main.getSettings().getBoolean("MySQL.Enabled"));
            }
            if (Main.getSettings().get("MySQL.Host") != null) {
                mySQLCredentials.setString("Host", Main.getSettings().getString("MySQL.Host"));
            }
            if (Main.getSettings().get("MySQL.Port") != null) {
                mySQLCredentials.setInt("Port", Main.getSettings().getInt("MySQL.Port"));
            }
            if (Main.getSettings().get("MySQL.Database") != null) {
                mySQLCredentials.setString("Database", Main.getSettings().getString("MySQL.Database"));
            }
            if (Main.getSettings().get("MySQL.SSL") != null) {
                mySQLCredentials.setBoolean("SSL", Main.getSettings().getBoolean("MySQL.SSL"));
            }
            if (Main.getSettings().get("MySQL.Username") != null) {
                mySQLCredentials.setString("Username", Main.getSettings().getString("MySQL.Username"));
            }
            if (Main.getSettings().get("MySQL.Password") != null) {
                mySQLCredentials.setString("Password", Main.getSettings().getString("MySQL.Password"));
            }
            Main.getSettings().set("MySQL.Enabled", null);
            Main.getSettings().set("MySQL.Host", null);
            Main.getSettings().set("MySQL.Port", null);
            Main.getSettings().set("MySQL.Database", null);
            Main.getSettings().set("MySQL.SSL", null);
            Main.getSettings().set("MySQL.Username", null);
            Main.getSettings().set("MySQL.Password", null);
        }
        if (!ro.kmagic.libapi.database.Database.getMySQLCredentials().isEnabled()) {
            return;
        }
        API.getDatabase().createTable(PlayerEconomy.getTableName(), Arrays.asList(new Column("Player").setType(Column.ColumnType.VARCHAR).setLength(25).setNotNull(), new Column("UUID").setType(Column.ColumnType.VARCHAR).setLength(40).setNotNull().setPrimaryKey()));
        API.getDatabase().addColumn(PlayerEconomy.getTableName(), new Column("Balance").setType(Column.ColumnType.INT).setLength(11).setDefault(0));
        API.getDatabase().addColumn(PlayerEconomy.getTableName(), new Column("Bounty").setType(Column.ColumnType.INT).setLength(11).setDefault(0));
        API.getDatabase().addColumn(PlayerEconomy.getTableName(), new Column("Level").setType(Column.ColumnType.INT).setLength(3).setDefault(1));
        Stats.createTable();
        Perk.createTable();
        if (Main.isSyncEnabled()) {
            Sync.createTable();
        }
        ChatOption.createTable();
        API.getDatabase().createTable(RenownEconomy.getTableName(), Arrays.asList(new Column("Player").setType(Column.ColumnType.VARCHAR).setLength(25).setNotNull(), new Column("UUID").setType(Column.ColumnType.VARCHAR).setLength(40).setNotNull().setPrimaryKey()));
        API.getDatabase().addColumn(RenownEconomy.getTableName(), new Column("Prestige").setType(Column.ColumnType.INT).setLength(11).setDefault(0));
        API.getDatabase().addColumn(RenownEconomy.getTableName(), new Column("Renown").setType(Column.ColumnType.INT).setLength(11).setDefault(0));
        API.getDatabase().addColumn(RenownEconomy.getTableName(), new Column("Grinded").setType(Column.ColumnType.INT).setLength(11).setDefault(0));
    }
}
