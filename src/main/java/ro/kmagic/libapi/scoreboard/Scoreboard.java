package ro.kmagic.libapi.scoreboard;

import org.bukkit.Bukkit;
import ro.kmagic.libapi.placeholder.PlaceholderManager;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;
import ro.kmagic.libapi.API;
import java.util.Iterator;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import java.util.UUID;
import org.bukkit.scoreboard.ScoreboardManager;
import java.util.ArrayList;
import org.bukkit.scheduler.BukkitTask;
import java.util.HashMap;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import java.util.List;
import org.bukkit.scoreboard.Objective;
import org.bukkit.entity.Player;

public class Scoreboard
{
    private final Player p;
    private final Objective objective;
    private final String title;
    private final List<RefreshablePlaceholder> placeholders;
    private final HashMap<Integer, String> randomName;
    private final BukkitTask refreshTask;
    private final ArrayList<ScoreboardLine> refresh;
    private static ScoreboardManager scoreboardManager;
    private static HashMap<UUID, Scoreboard> customScoreboard;
    
    public static boolean has(final Player player) {
        return Scoreboard.customScoreboard.containsKey(player.getUniqueId());
    }
    
    public static Scoreboard get(final Player player) {
        return Scoreboard.customScoreboard.get(player.getUniqueId());
    }
    
    public Scoreboard(final Player p5, final String title, final List<String> lines, final List<RefreshablePlaceholder> placeholders, final int n) {
        this.randomName = new HashMap<Integer, String>();
        this.refresh = new ArrayList<ScoreboardLine>();
        if (Scoreboard.customScoreboard.containsKey(p5.getUniqueId())) {
            get(p5).remove();
        }
        this.p = p5;
        (this.objective = Scoreboard.scoreboardManager.getNewScoreboard().registerNewObjective("Scoreboard", "Custom")).setDisplaySlot(DisplaySlot.SIDEBAR);
        this.title = title;
        this.placeholders = placeholders;
        this.setLines(lines);
        p5.setScoreboard(this.objective.getScoreboard());
        this.refreshTask = new BukkitRunnable() {
            public void run() {
                final Iterator<ScoreboardLine> iterator = Scoreboard.this.refresh.iterator();
                while (iterator.hasNext()) {
                    Scoreboard.this.setLine(iterator.next());
                }
            }
        }.runTaskTimer(API.getPlugin(), 0L, n);
        Scoreboard.customScoreboard.put(p5.getUniqueId(), this);
    }
    
    private void setLines(final List<String> list) {
        final Iterator<Team> iterator = this.objective.getScoreboard().getTeams().iterator();
        while (iterator.hasNext()) {
            iterator.next().unregister();
        }
        this.refresh.clear();
        this.objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.title));
        int n = 1;
        for (int i = list.size(); i > 0; --i) {
            final Team registerNewTeam = this.objective.getScoreboard().registerNewTeam("Line-" + n);
            final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', list.get(i - 1));
            final ScoreboardLine e = new ScoreboardLine(registerNewTeam, translateAlternateColorCodes, n);
            this.randomName.put(n, ChatColor.values()[i - 1].toString());
            this.setLine(e);
            final Iterator<RefreshablePlaceholder> iterator2 = this.placeholders.iterator();
            while (iterator2.hasNext()) {
                if (translateAlternateColorCodes.contains(iterator2.next().getPlaceholder())) {
                    this.refresh.add(e);
                }
            }
            if (PlaceholderManager.isPapiEnabled()) {
                this.refresh.add(e);
            }
            ++n;
        }
    }
    
    private void setLine(final ScoreboardLine scoreboardLine) {
        final String replacePlaceholders = this.replacePlaceholders(scoreboardLine.getLine());
        final Team team = scoreboardLine.getTeam();
        final String entry = scoreboardLine.getEntry();
        int n = 0;
        String s;
        if (replacePlaceholders.length() <= 16) {
            team.setPrefix(replacePlaceholders);
            s = this.randomName.get(scoreboardLine.getRow());
            team.addEntry(s);
            team.setSuffix("");
        }
        else if (API.getVersionSupport().contains(13, 14, 15, 16)) {
            n = 80;
            if (replacePlaceholders.length() <= 32) {
                s = this.randomName.get(scoreboardLine.getRow());
                String prefix = replacePlaceholders.substring(0, 16);
                String suffix;
                if (prefix.endsWith("&") || prefix.endsWith("§")) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    suffix = replacePlaceholders.substring(prefix.length());
                }
                else if (prefix.substring(0, 15).endsWith("&") || prefix.substring(0, 15).endsWith("§")) {
                    prefix = prefix.substring(0, prefix.length() - 2);
                    suffix = replacePlaceholders.substring(prefix.length());
                }
                else {
                    suffix = ChatColor.getLastColors(prefix) + replacePlaceholders.substring(prefix.length());
                }
                team.setPrefix(prefix);
                team.addEntry(s);
                team.setSuffix(suffix);
            }
            else if (replacePlaceholders.length() <= 80) {
                s = this.randomName.get(scoreboardLine.getRow());
                String prefix2 = replacePlaceholders.substring(0, 64);
                String suffix2;
                if (prefix2.endsWith("&") || prefix2.endsWith("§")) {
                    prefix2 = prefix2.substring(0, prefix2.length() - 1);
                    suffix2 = replacePlaceholders.substring(prefix2.length());
                }
                else if (prefix2.substring(0, 63).endsWith("&") || prefix2.substring(0, 63).endsWith("§")) {
                    prefix2 = prefix2.substring(0, prefix2.length() - 2);
                    suffix2 = replacePlaceholders.substring(prefix2.length());
                }
                else {
                    suffix2 = ChatColor.getLastColors(prefix2) + replacePlaceholders.substring(prefix2.length());
                }
                team.setPrefix(prefix2);
                team.addEntry(s);
                team.setSuffix(suffix2);
            }
            else {
                team.setPrefix(replacePlaceholders.substring(0, 64));
                s = replacePlaceholders.substring(64, 80);
                team.addEntry(s);
                int length = 144;
                if (replacePlaceholders.length() < 144) {
                    length = replacePlaceholders.length();
                }
                team.setSuffix(replacePlaceholders.substring(80, length));
            }
        }
        else {
            n = 32;
            if (replacePlaceholders.length() <= 32) {
                s = this.randomName.get(scoreboardLine.getRow());
                String prefix3 = replacePlaceholders.substring(0, 16);
                String suffix3;
                if (prefix3.endsWith("&") || prefix3.endsWith("§")) {
                    prefix3 = prefix3.substring(0, prefix3.length() - 1);
                    suffix3 = replacePlaceholders.substring(prefix3.length());
                }
                else if (prefix3.substring(0, 15).endsWith("&") || prefix3.substring(0, 15).endsWith("§")) {
                    prefix3 = prefix3.substring(0, prefix3.length() - 2);
                    suffix3 = replacePlaceholders.substring(prefix3.length());
                }
                else {
                    suffix3 = ChatColor.getLastColors(prefix3) + replacePlaceholders.substring(prefix3.length());
                }
                team.setPrefix(prefix3);
                team.addEntry(s);
                team.setSuffix(suffix3);
            }
            else {
                team.setPrefix(replacePlaceholders.substring(0, 16));
                s = replacePlaceholders.substring(16, 32);
                team.addEntry(s);
                int length2 = 48;
                if (replacePlaceholders.length() < 48) {
                    length2 = replacePlaceholders.length();
                }
                team.setSuffix(replacePlaceholders.substring(32, length2));
            }
        }
        if (n > replacePlaceholders.length() && entry != null && !entry.equals(s)) {
            this.objective.getScoreboard().resetScores(entry);
        }
        this.objective.getScore(s).setScore(scoreboardLine.getRow());
        scoreboardLine.setEntry(s);
    }
    
    private String replacePlaceholders(String s) {
        if (PlaceholderManager.isPapiEnabled()) {
            s = PlaceholderManager.setPlaceholders(this.p, s);
        }
        for (final RefreshablePlaceholder refreshablePlaceholder : this.placeholders) {
            s = s.replace(refreshablePlaceholder.getPlaceholder(), refreshablePlaceholder.getValue());
        }
        return s;
    }
    
    public void remove() {
        this.refreshTask.cancel();
        this.p.setScoreboard(Scoreboard.scoreboardManager.getNewScoreboard());
        Scoreboard.customScoreboard.remove(this.p.getUniqueId());
        final Iterator<RefreshablePlaceholder> iterator = this.placeholders.iterator();
        while (iterator.hasNext()) {
            iterator.next().closeRefresh();
        }
    }
    
    static {
        Scoreboard.scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard.customScoreboard = new HashMap<UUID, Scoreboard>();
    }
}
