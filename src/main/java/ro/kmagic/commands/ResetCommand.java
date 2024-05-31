package ro.kmagic.commands;

import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.ChatColor;
import ro.kmagic.libapi.utils.FileManager;
import org.bukkit.entity.Player;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.libapi.scoreboard.Scoreboard;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.economy.renown.RenownEconomy;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.database.Database;
import ro.kmagic.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.SubCommand;

public class ResetCommand extends SubCommand {
    public ResetCommand() {
        super("reset", new String[] { "thepit.*", "thepit.reset" });
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (array.length != 1) {
            this.help(commandSender);
            return;
        }
        final Player player = Bukkit.getPlayer(array[0]);
        if (player == null) {
            commandSender.sendMessage(Main.getMessages().getString("Economy.PlayerNotExist"));
            return;
        }
        if (Database.getMySQLCredentials().isEnabled()) {
            API.getDatabase().deletePlayer(player.getUniqueId(), "ThePitProfiles");
            API.getDatabase().deletePlayer(player.getUniqueId(), "ThePitPerks");
            API.getDatabase().deletePlayer(player.getUniqueId(), "ThePitStats");
            API.getDatabase().deletePlayer(player.getUniqueId(), "ThePitPrestige");
            if (Main.isSyncEnabled()) {
                API.getDatabase().deletePlayer(player.getUniqueId(), "ThePitPlayerSync");
            }
        }
        else {
            final FileManager profile = API.getDatabase().getProfile(player.getUniqueId());
            profile.set(PlayerEconomy.getTableName() + ".Balance", Main.getSettings().getInt("Economy.DefaultBalance"));
            profile.set(PlayerEconomy.getTableName() + ".Bounty", 0);
            profile.set(PlayerEconomy.getTableName() + ".Level", 1);
            profile.set(RenownEconomy.getTableName() + ".Prestige", 0);
            profile.set(RenownEconomy.getTableName() + ".Renown", 0);
            profile.set(RenownEconomy.getTableName() + ".Grinded", 0);
            for (final Perk.PerkType perkType : Perk.PerkType.values()) {
                profile.set(Perk.getTableName() + "." + perkType + "_OWNED", "false");
                profile.set(Perk.getTableName() + "." + perkType + "_SLOT", 0);
            }
            if (Scoreboard.has(player)) {
                Scoreboard.get(player).remove();
            }
            PlayerEconomy.removeAccount(player);
            PlayerPerk.removeAccount(player);
            PlayerStats.removeAccount(player);
            RenownEconomy.removeAccount(player);
            final Stats.StatsType[] values2 = Stats.StatsType.values();
            for (int length2 = values2.length, j = 0; j < length2; ++j) {
                profile.set(Stats.getTableName() + "." + values2[j], 0);
            }
            profile.reload();
        }
        player.setExp(0.0f);
        player.setLevel(1);
        player.getEnderChest().clear();
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.kickPlayer(Main.getMessages().getString("Reset.Deleted"));
        commandSender.sendMessage(Main.getMessages().getString("Reset.Executed").replace("%target%", player.getName()));
    }
    
    private void help(final CommandSender commandSender) {
        commandSender.sendMessage("");
        commandSender.sendMessage("");
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Command usage:"));
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit Reset <&aplayer&f>");
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(translateAlternateColorCodes);
            return;
        }
        TextComponentUtil.sendTextComponent((Player)commandSender, translateAlternateColorCodes, "/thepit reset ");
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        if (commandSender instanceof Player) {
            return this.hasPermission(commandSender) && Main.getArena().getPlayers().contains(((Player)commandSender).getUniqueId());
        }
        return this.hasPermission(commandSender);
    }
}
