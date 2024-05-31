package ro.kmagic.features.events.major;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import ro.kmagic.libapi.hologram.RefreshableHologram;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.Main;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.features.economy.renown.RenownEconomy;
import ro.kmagic.features.events.PitEvent;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.features.prestige.PlayerPrestige;

import java.util.*;

public class TeamDeathmatch extends PitEvent implements Listener {
    private static TeamDeathmatch instance;
    private ArrayList<RefreshableHologram> refreshableHolograms;
    private BukkitTask task;
    private final HashMap<Team, ArrayList<UUID>> teams;
    private final Map<UUID, Integer> playerKills;
    private final Map<Integer, Player> top;
    private boolean split;
    private int blueKills;
    private int redKills;
    private final int requiredPlayers;
    private final int duration;
    private final int top3Reward;
    private final int top4_20Reward;
    private final int topRestReward;
    private final int winnerReward;
    private final int looserReward;
    private final String eventName;
    private final List<Location> hologramLocations;
    private final List<String> preStart;
    private final List<String> start;
    private final long eventDuration;
    private final String blueTeam;
    private final String redTeam;
    
    private TeamDeathmatch() {
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        this.teams = new HashMap<Team, ArrayList<UUID>>();
        this.playerKills = new HashMap<UUID, Integer>();
        this.top = new HashMap<Integer, Player>();
        this.split = false;
        this.requiredPlayers = Main.getPitEventManager().getInt("Event.Major.TEAM_DEATHMATCH.RequiredPlayers");
        this.eventName = Main.getPitEventManager().getEventName(PitEventManager.PitEventType.TEAM_DEATHMATCH);
        this.hologramLocations = Main.getEventsHologram().getLocations();
        this.preStart = Main.getEventsHologram().getStringList("MajorEvent.PreStart");
        this.duration = Main.getPitEventManager().getMajorDuration(PitEventManager.PitEventType.TEAM_DEATHMATCH.name());
        this.start = Main.getEventsHologram().getStringList("MajorEvent.Start");
        this.eventDuration = Main.getPitEventManager().getMajorDuration(PitEventManager.PitEventType.TEAM_DEATHMATCH.name()) * 20L;
        this.blueTeam = Main.getPitEventManager().getString("Event.Major.TEAM_DEATHMATCH.Team.Blue");
        this.redTeam = Main.getPitEventManager().getString("Event.Major.TEAM_DEATHMATCH.Team.Red");
        this.top3Reward = Main.getPitEventManager().getReward(PitEventManager.PitEventType.TEAM_DEATHMATCH.name(), "3");
        this.top4_20Reward = Main.getPitEventManager().getReward(PitEventManager.PitEventType.TEAM_DEATHMATCH.name(), "4-20");
        this.topRestReward = Main.getPitEventManager().getReward(PitEventManager.PitEventType.TEAM_DEATHMATCH.name(), "4-20");
        this.winnerReward = Main.getPitEventManager().getEndReward(PitEventManager.PitEventType.TEAM_DEATHMATCH.name(), "Winner");
        this.looserReward = Main.getPitEventManager().getEndReward(PitEventManager.PitEventType.TEAM_DEATHMATCH.name(), "Looser");
    }
    
    @Override
    public void preStartEvent() {
        if (Main.getPitEventManager().isStarting()) {
            return;
        }
        if (Main.getPitEventManager().isStarted()) {
            return;
        }
        if (Main.getArena().getPlayers().size() < this.requiredPlayers) {
            this.endEvent();
            return;
        }
        Main.getPitEventManager().setStarting(true);
        Main.getPitEventManager().setEventTime(Main.getPitEventManager().getSecondsBeforeStart() / 20);
        this.task = new BukkitRunnable() {
            public void run() {
                Main.getPitEventManager().setEventTime(Main.getPitEventManager().getEventTime() - 1);
            }
        }.runTaskTimer(API.getPlugin(), 0L, 20L);
        final List<String> majorAnnounceMessage = Main.getPitEventManager().getMajorAnnounceMessage(this.eventName, Main.getPitEventManager().getLocationName(), Main.getPitEventManager().getTimeFormatted());
        for (final UUID uuid : Main.getArena().getPlayers()) {
            for (String s : majorAnnounceMessage) {
                Bukkit.getPlayer(uuid).sendMessage(s);
            }
        }
        final ArrayList<RefreshableHologram> list = new ArrayList<RefreshableHologram>();
        for (final Location location : this.hologramLocations) {
            list.add(new RefreshableHologram(location.toString(), location, this.preStart, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return TeamDeathmatch.this.getDescription();
                }
            }, new RefreshablePlaceholder("%eventTime%", 8) {
                @Override
                public String refresh() {
                    return Main.getPitEventManager().getTimeFormatted();
                }
            }), 20));
        }
        new BukkitRunnable() {
            public void run() {
                TeamDeathmatch.this.task.cancel();
                for (RefreshableHologram refreshableHologram : list) {
                    refreshableHologram.remove();
                }
                Main.getPitEventManager().setStarting(false);
                TeamDeathmatch.this.startEvent();
            }
        }.runTaskLater(API.getPlugin(), Main.getPitEventManager().getSecondsBeforeStart());
    }
    
    private void startEvent() {
        API.registerEvent(this);
        Main.getPitEventManager().setStarted(true);
        Main.getPitEventManager().setEventTime(this.duration);
        this.task = new BukkitRunnable() {
            public void run() {
                Main.getPitEventManager().setEventTime(Main.getPitEventManager().getEventTime() - 1);
            }
        }.runTaskTimer(API.getPlugin(), 0L, 20L);
        final List<String> majorStartMessage = Main.getPitEventManager().getMajorStartMessage(this.eventName, Main.getPitEventManager().getLocationName(), Main.getPitEventManager().getTimeFormatted());
        for (final UUID uuid : Main.getArena().getPlayers()) {
            for (String s : majorStartMessage) {
                Bukkit.getPlayer(uuid).sendMessage(s);
            }
        }
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        for (final Location location : this.hologramLocations) {
            this.refreshableHolograms.add(new RefreshableHologram(location.toString(), location, this.start, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return TeamDeathmatch.this.getDescription();
                }
            }, new RefreshablePlaceholder("%eventTime%", 8) {
                @Override
                public String refresh() {
                    return Main.getPitEventManager().getTimeFormatted();
                }
            }), 20));
        }
        this.teams.put(Team.BLUE, new ArrayList<UUID>());
        this.teams.put(Team.RED, new ArrayList<UUID>());
        for (UUID uuid : Main.getArena().getPlayers()) {
            this.addTeam(Bukkit.getPlayer(uuid));
        }
        new BukkitRunnable() {
            public void run() {
                TeamDeathmatch.this.task.cancel();
                for (RefreshableHologram refreshableHologram : TeamDeathmatch.this.refreshableHolograms) {
                    refreshableHologram.remove();
                }
                TeamDeathmatch.this.endEvent();
            }
        }.runTaskLater(API.getPlugin(), this.eventDuration);
    }
    
    private void endEvent() {
        if (!Main.getPitEventManager().isStarted()) {
            Main.getPitEventManager().setPitEvent(null);
            Main.getPitEventManager().setStarted(false);
            Main.getPitEventManager().setEventType(null);
            Main.getPitEventManager().startPitEvents(true);
            return;
        }
        Main.getPitEventManager().setStarted(false);
        if (this.task != null) {
            this.task.cancel();
        }
        EntityDamageByEntityEvent.getHandlerList().unregister(this);
        PlayerDeathEvent.getHandlerList().unregister(this);
        this.makePlayersTop();
        this.rewardPlayers();
        Player player = null;
        Player player2 = null;
        Player player3 = null;
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', "&c-");
        if (this.top.containsKey(1)) {
            player = this.top.get(1);
            if (this.top.containsKey(2)) {
                player2 = this.top.get(2);
                if (this.top.containsKey(3)) {
                    player3 = this.top.get(3);
                }
            }
        }
        final String s = (player != null) ? String.valueOf(this.playerKills.get(player.getUniqueId())) : translateAlternateColorCodes;
        final String s2 = (player2 != null) ? String.valueOf(this.playerKills.get(player2.getUniqueId())) : translateAlternateColorCodes;
        final String s3 = (player3 != null) ? String.valueOf(this.playerKills.get(player3.getUniqueId())) : translateAlternateColorCodes;
        final Team winningTeam = this.getWinningTeam();
        final String s4 = winningTeam.equals(Team.BLUE) ? this.blueTeam : this.redTeam;
        final int winningTeamKills = this.getWinningTeamKills();
        final Team[] values = Team.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            for (final UUID uuid : this.teams.get(values[i])) {
                if (Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline()) {
                    Bukkit.getPlayer(uuid).getInventory().setHelmet(null);
                }
            }
        }
        for (final UUID uuid2 : Main.getArena().getPlayers()) {
            for (String value : Main.getPitEventManager().getTeamDeathmatchEndMessage(s4, String.valueOf(winningTeamKills), this.isWinner(winningTeam, Bukkit.getPlayer(uuid2)), player, player2, player3, s, s2, s3)) {
                Bukkit.getPlayer(uuid2).sendMessage(value);
            }
            Main.getPlayerManager().restoreCachedHelmet(Bukkit.getPlayer(uuid2));
        }
        this.redKills = 0;
        this.blueKills = 0;
        this.top.clear();
        this.playerKills.clear();
        this.teams.clear();
        this.split = false;
        this.blueKills = 0;
        this.redKills = 0;
        Main.getPitEventManager().setEventType(null);
        Main.getPitEventManager().startPitEvents(false);
    }
    
    @Override
    public String getDescription() {
        return Main.getPitEventManager().getMajorDescription(PitEventManager.PitEventType.TEAM_DEATHMATCH.toString());
    }
    
    public static TeamDeathmatch getInstance() {
        return TeamDeathmatch.instance;
    }
    
    @Override
    public void addTeam(final Player player) {
        this.teams.get(this.split ? Team.BLUE : Team.RED).add(player.getUniqueId());
        Main.getPlayerManager().cacheHelmet(player);
        player.getInventory().setHelmet(this.split ? Materials.BLUE_WOOL.getItem().build() : Materials.RED_WOOL.getItem().build());
        this.split = !this.split;
    }
    
    @Override
    public void removeTeam(final Player player) {
        this.teams.get(Team.RED).remove(player.getUniqueId());
        this.teams.get(Team.BLUE).remove(player.getUniqueId());
    }
    
    private void makePlayersTop() {
        final Map<UUID, Integer> sortByValue = sortByValue(this.playerKills);
        final ArrayList<UUID> list = new ArrayList<UUID>();
        for (final Map.Entry<UUID, Integer> entry : sortByValue.entrySet()) {
            if (Bukkit.getPlayer(entry.getKey()) == null || !Bukkit.getPlayer(entry.getKey()).isOnline()) {
                list.add(entry.getKey());
            }
        }
        for (UUID uuid : list) {
            sortByValue.remove(uuid);
        }
        int i = sortByValue.size();
        while (i > 0) {
            final Iterator<Map.Entry<UUID, Integer>> iterator3 = sortByValue.entrySet().iterator();
            if (iterator3.hasNext()) {
                final Map.Entry<UUID, Integer> entry2 = iterator3.next();
                this.top.put(i, Bukkit.getPlayer(entry2.getKey()));
                --i;
                sortByValue.remove(entry2.getKey());
            }
        }
    }
    
    private void rewardPlayers() {
        for (int i = 1; i <= this.top.size(); ++i) {
            int n = 0;
            int n2;
            if (i <= 3) {
                n2 = this.top3Reward;
                n = 2;
            }
            else if (i <= 20) {
                n2 = this.top4_20Reward;
                n = 1;
            }
            else {
                n2 = this.topRestReward;
            }
            if (this.top.get(i) != null && this.top.get(i).getUniqueId() != null && Bukkit.getPlayer(this.top.get(i).getUniqueId()) != null) {
                final Player player = Bukkit.getPlayer(this.top.get(i).getUniqueId());
                PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, n2);
                if (n != 0 && PlayerPrestige.get(player).getPrestige() != 0) {
                    RenownEconomy.get(player).setBalance(RenownEconomy.RenownAction.ADD, n);
                }
            }
        }
    }
    
    private Team getWinningTeam() {
        if (this.blueKills >= this.redKills) {
            return Team.BLUE;
        }
        return Team.RED;
    }
    
    private int getWinningTeamKills() {
        return Math.max(this.blueKills, this.redKills);
    }
    
    private boolean isWinner(final Team key, final Player player) {
        boolean b = false;
        int n;
        if (this.teams.get(key).contains(player.getUniqueId())) {
            n = this.winnerReward;
            b = true;
        }
        else {
            n = this.looserReward;
        }
        if (key.equals(Team.RED)) {
            if (this.redKills == 0) {
                return b;
            }
        }
        else if (this.blueKills == 0) {
            return b;
        }
        PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, n);
        return b;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)entityDamageByEntityEvent.getEntity();
        Player player2 = null;
        if (entityDamageByEntityEvent.getDamager() instanceof Player) {
            player2 = (Player)entityDamageByEntityEvent.getDamager();
        }
        else if (entityDamageByEntityEvent.getDamager() instanceof Arrow && ((Arrow)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
            player2 = (Player)((Arrow)entityDamageByEntityEvent.getDamager()).getShooter();
        }
        else if (entityDamageByEntityEvent.getDamager() instanceof FishHook && ((FishHook)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
            player2 = (Player)((FishHook)entityDamageByEntityEvent.getDamager()).getShooter();
        }
        if (player2 == null) {
            return;
        }
        for (final Team team : Team.values()) {
            if (this.teams.get(team).contains(player2.getUniqueId()) && this.teams.get(team).contains(player.getUniqueId())) {
                entityDamageByEntityEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    private void onPlayerKill(final PlayerDeathEvent playerDeathEvent) {
        if (playerDeathEvent.getKiller() == null) {
            return;
        }
        final Player killer = playerDeathEvent.getKiller();
        this.playerKills.put(killer.getUniqueId(), this.playerKills.getOrDefault(killer.getUniqueId(), 0) + 1);
        if (this.teams.get(Team.BLUE).contains(killer.getUniqueId())) {
            ++this.blueKills;
        }
        if (this.teams.get(Team.RED).contains(killer.getUniqueId())) {
            ++this.redKills;
        }
    }
    
    @Override
    public void giveHead(final Player player) {
        if (this.teams.get(Team.BLUE).contains(player.getUniqueId())) {
            player.getInventory().setHelmet(Materials.BLUE_WOOL.getItem().build());
        }
        if (this.teams.get(Team.RED).contains(player.getUniqueId())) {
            player.getInventory().setHelmet(Materials.RED_WOOL.getItem().build());
        }
    }
    
    private static Map<UUID, Integer> sortByValue(final Map<UUID, Integer> map) {
        final List<Map.Entry<UUID, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        final Map<UUID, Integer> linkedHashMap = new HashMap<>();
        for (final Map.Entry<UUID, Integer> entry : list) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }
    
    @Override
    public int getTeamDeathmatchKAndA(final Player player) {
        if (!this.playerKills.containsKey(player.getUniqueId())) {
            return 0;
        }
        return this.playerKills.get(player.getUniqueId());
    }
    
    @Override
    public int getTeamDeathmatchBlueKills() {
        return this.blueKills;
    }
    
    @Override
    public int getTeamDeathmatchRedKills() {
        return this.redKills;
    }
    
    @Override
    public String getTeamColorCode(final Player player) {
        if (this.teams.getOrDefault(Team.BLUE, new ArrayList<UUID>()).contains(player.getUniqueId())) {
            return "&9";
        }
        if (this.teams.getOrDefault(Team.RED, new ArrayList<UUID>()).contains(player.getUniqueId())) {
            return "&c";
        }
        return "&7";
    }
    
    static {
        TeamDeathmatch.instance = new TeamDeathmatch();
    }
    
    public enum Team {
        RED, 
        BLUE
    }
}
