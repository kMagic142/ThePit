package ro.kmagic.features;

import org.bukkit.entity.Player;
import ro.kmagic.libapi.placeholder.Placeholder;
import ro.kmagic.libapi.placeholder.PlaceholderManager;
import ro.kmagic.Main;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.features.economy.renown.RenownEconomy;
import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.features.stats.Stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Placeholders {

    public Placeholders() {
        List<String> placeholdersPlayer = new ArrayList<String>(Arrays.asList("gold", "renown", "level", "level_prestige", "level_formated", "neededxp", "status", "streak", "bounty", "tag_prefix", "tag_suffix"));
        List<String> placeholdersStats = new ArrayList<String>(Arrays.asList("bow_damage_taken", "damage_taken", "deaths", "melee_damage_taken", "blocks_broken", "blocks_placed", "chat_messages", "fishing_rods_launched", "golden_apples_eaten", "jumps_into_pit", "lava_buckets_emptied", "left_clicks", "arrow_hits", "arrows_shot", "assists", "bow_damage_dealt", "diamond_items_purchased", "launches", "damage_dealt", "highest_streak", "kills", "melee_damage_dealt", "sword_hits", "gold_earned", "golden_heads_eaten"));
        List<String> placeholdersLeaderHeads = new ArrayList<String>(Arrays.asList("gold", "renown", "level", "prestige", "neededxp", "bounty"));
        PlaceholderManager.setIdentifier("thepit");
        for (String s : placeholdersPlayer) {
            PlaceholderManager.addPlaceholder(new Placeholder("player_" + s) {
                @Override
                public String request(final Player player) {
                    if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
                        return "";
                    }
                    final String replace = this.getName().replace("player_", "");
                    switch (replace) {
                        case "gold": {
                            return PlayerEconomy.format(PlayerEconomy.get(player).getBalance());
                        }
                        case "renown": {
                            return RenownEconomy.format(RenownEconomy.get(player).getRenown());
                        }
                        case "level": {
                            return Main.getXpTag().getScoreboard(player);
                        }
                        case "level_formated": {
                            return Main.getXpTag().getScoreboardFormated(player);
                        }
                        case "level_prestige": {
                            return Main.getXpTag().get(player);
                        }
                        case "neededxp": {
                            return Main.getArena().getRequiredXP(player, false);
                        }
                        case "streak": {
                            final double streak = Main.getStreakManager().getStreak(player);
                            return String.valueOf(String.valueOf(streak).contains(".0") ? String.valueOf(streak).replace(".0", "") : Double.valueOf(streak));
                        }
                        case "bounty": {
                            if (Main.getPitEventManager().isBeast(player)) {
                                return Main.getPitEventManager().getString("Event.Major.THE_BEAST.Tag");
                            }
                            if (Main.getBounty().getBounty(player) != 0) {
                                return String.valueOf(Main.getBounty().format(Main.getBounty().getBounty(player)));
                            }
                            break;
                        }
                        case "tag_prefix": {
                            return Main.getPlayerTag().getFormattedPrefix(player);
                        }
                        case "tag_suffix": {
                            return Main.getPlayerTag().getFormattedSuffix(player);
                        }
                    }
                    return "";
                }
            });
        }
        PlaceholderManager.addPlaceholder(new Placeholder("server_date") {
            @Override
            public String request(final Player player) {
                if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
                    return "";
                }
                return "&7" + (Calendar.getInstance().get(2) + 1) + "/" + Calendar.getInstance().get(5) + "/" + Calendar.getInstance().get(1);
            }
        });
        for (final String str : placeholdersStats) {
            PlaceholderManager.addPlaceholder(new Placeholder("stats_" + str) {
                @Override
                public String request(final Player player) {
                    if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
                        return "";
                    }
                    return String.valueOf(PlayerStats.get(player).get(Stats.StatsType.valueOf(str.toUpperCase())));
                }
            });
        }
        for (String placeholdersLeaderHead : placeholdersLeaderHeads) {
            PlaceholderManager.addPlaceholder(new Placeholder("leaderheads_" + placeholdersLeaderHead) {
                @Override
                public String request(final Player player) {
                    final String replace = this.getName().replace("leaderheads_", "");
                    switch (replace) {
                        case "gold": {
                            return String.valueOf((int) PlayerEconomy.get(player).getBalance());
                        }
                        case "renown": {
                            return String.valueOf(RenownEconomy.get(player).getRenown());
                        }
                        case "level": {
                            return String.valueOf(player.getLevel());
                        }
                        case "prestige": {
                            return String.valueOf(PlayerPrestige.get(player).getPrestige());
                        }
                        case "neededxp": {
                            return String.valueOf(Main.getArena().getRequiredXP(player, true));
                        }
                        case "bounty": {
                            return String.valueOf(Main.getBounty().getBounty(player));
                        }
                        default: {
                            return "";
                        }
                    }
                }
            });
        }
    }
}
