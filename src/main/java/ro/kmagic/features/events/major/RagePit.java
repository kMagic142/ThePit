package ro.kmagic.features.events.major;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.managers.MapRecoverManager;

import java.util.*;

public class RagePit extends PitEvent implements Listener {
    private static final RagePit instance;
    private final ArrayList<Location> locations;
    private ArrayList<RefreshableHologram> refreshableHolograms;
    private final HashMap<UUID, Integer> playerDamage;
    private final HashMap<Integer, Player> top;
    private BukkitTask task;
    private BukkitTask task2;
    private int kills;
    private boolean goalFinished;
    private final int requiredPlayers;
    private final int duration;
    private final int killGoalAmount;
    private final int killGoalBonus;
    private final int top3Reward;
    private final int top4_20Reward;
    private final int topRestReward;
    private final String eventName;
    private final List<Location> hologramLocations;
    private final List<String> preStart;
    private final List<String> start;
    private final long eventDuration;
    
    private RagePit() {
        this.locations = new ArrayList<Location>();
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        this.playerDamage = new HashMap<UUID, Integer>();
        this.top = new HashMap<Integer, Player>();
        for (String s : Main.getEventsRegion().getRagePitBlocks()) {
            final String[] split = s.split(" ");
            this.locations.add(new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3])));
        }
        this.requiredPlayers = Main.getPitEventManager().getInt("Event.Major.RAGE_PIT.RequiredPlayers");
        this.eventName = Main.getPitEventManager().getEventName(PitEventManager.PitEventType.RAGE_PIT);
        this.hologramLocations = Main.getEventsHologram().getLocations();
        this.preStart = Main.getEventsHologram().getStringList("MajorEvent.PreStart");
        this.duration = Main.getPitEventManager().getMajorDuration(PitEventManager.PitEventType.RAGE_PIT.toString());
        this.start = Main.getEventsHologram().getStringList("MajorEvent.Start");
        this.eventDuration = Main.getPitEventManager().getMajorDuration(PitEventManager.PitEventType.RAGE_PIT.name()) * 20L;
        this.killGoalAmount = Main.getPitEventManager().getRPKillGoal("Amount");
        this.killGoalBonus = Main.getPitEventManager().getRPKillGoal("Bonus");
        this.top3Reward = Main.getPitEventManager().getReward(PitEventManager.PitEventType.RAGE_PIT.name(), "3");
        this.top4_20Reward = Main.getPitEventManager().getReward(PitEventManager.PitEventType.RAGE_PIT.name(), "4-20");
        this.topRestReward = Main.getPitEventManager().getReward(PitEventManager.PitEventType.RAGE_PIT.name(), "Rest");
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
                Player p = Bukkit.getPlayer(uuid);
                if(p != null) p.sendMessage(s);
            }
        }
        final ArrayList<RefreshableHologram> list = new ArrayList<RefreshableHologram>();
        for (final Location location : this.hologramLocations) {
            list.add(new RefreshableHologram(location.toString(), location, this.preStart, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return RagePit.this.getDescription();
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
                RagePit.this.task.cancel();
                for (RefreshableHologram refreshableHologram : list) {
                    refreshableHologram.remove();
                }
                Main.getPitEventManager().setStarting(false);
                RagePit.this.startEvent();
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
        this.setPlayerHealth();
        final List<String> majorStartMessage = Main.getPitEventManager().getMajorStartMessage(this.eventName, Main.getPitEventManager().getLocationName(), Main.getPitEventManager().getTimeFormatted());
        for (final UUID uuid : Main.getArena().getPlayers()) {
            for (String s : majorStartMessage) {
                Bukkit.getPlayer(uuid).sendMessage(s);
            }
        }
        Main.getArena().sendPlayersToSpawn();
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        for (final Location location : this.hologramLocations) {
            this.refreshableHolograms.add(new RefreshableHologram(location.toString(), location, this.start, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return RagePit.this.getDescription();
                }
            }, new RefreshablePlaceholder("%eventTime%", 8) {
                @Override
                public String refresh() {
                    return Main.getPitEventManager().getTimeFormatted();
                }
            }), 20));
        }
        this.spawnCage();
        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().remove(Main.getPerk().getGoldenHeads().getMaterial());
            player.getInventory().remove(Materials.GOLDEN_APPLE.getItem().getMaterial());
            player.getInventory().remove(Materials.MUSHROOM_STEW.getItem().getMaterial());
            player.getInventory().remove(Materials.POTION.getItem().getMaterial());
        }
        this.task2 = new BukkitRunnable() {
            public void run() {
                RagePit.this.endEvent();
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
        if (this.task2 != null) {
            this.task2.cancel();
        }
        PlayerDeathEvent.getHandlerList().unregister(this);
        for (RefreshableHologram refreshableHologram : this.refreshableHolograms) {
            refreshableHologram.remove();
        }
        this.makePlayersTop();
        this.rewardPlayers();
        this.despawnCage();
        this.defaultPlayerHealth();
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
        final String s = (player != null) ? String.valueOf(this.playerDamage.get(player.getUniqueId())) : translateAlternateColorCodes;
        final String s2 = (player2 != null) ? String.valueOf(this.playerDamage.get(player2.getUniqueId())) : translateAlternateColorCodes;
        final String s3 = (player3 != null) ? String.valueOf(this.playerDamage.get(player3.getUniqueId())) : translateAlternateColorCodes;
        if (this.kills >= this.killGoalAmount) {
            this.goalFinished = true;
        }
        for (final UUID uuid : Main.getArena().getPlayers()) {
            Bukkit.getPlayer(uuid).getInventory().remove(Materials.POTATO.getItem().getMaterial());
            if (this.goalFinished) {
                PlayerEconomy.get(Bukkit.getPlayer(uuid)).setBalance(PlayerEconomy.EconomyAction.ADD, this.killGoalBonus);
            }
            for (String value : Main.getPitEventManager().getRagePitEndMessage(this.goalFinished, player, player2, player3, s, s2, s3)) {
                Bukkit.getPlayer(uuid).sendMessage(value);
            }
        }
        this.goalFinished = false;
        this.kills = 0;
        this.top.clear();
        this.playerDamage.clear();
        Main.getPitEventManager().setEventType(null);
        Main.getPitEventManager().startPitEvents(false);
    }
    
    public static RagePit getInstance() {
        return RagePit.instance;
    }
    
    @Override
    public String getDescription() {
        return Main.getPitEventManager().getMajorDescription(PitEventManager.PitEventType.RAGE_PIT.toString());
    }
    
    private void spawnCage() {
        new BukkitRunnable() {
            public void run() {
                final ArrayList<Block> list = new ArrayList<Block>();
                for (final Location location : RagePit.this.locations) {
                    location.getBlock().setType(Material.GLASS);
                    list.add(location.getBlock());
                }
                Main.getMapRecoverManager().addBlocks(MapRecoverManager.RecoverType.REMOVE, list);
            }
        }.runTask(API.getPlugin());
    }
    
    private void despawnCage() {
        new BukkitRunnable() {
            public void run() {
                final ArrayList<Block> list = new ArrayList<Block>();
                for (final Location location : RagePit.this.locations) {
                    location.getBlock().setType(Material.AIR);
                    list.add(location.getBlock());
                }
                Main.getMapRecoverManager().removeBlocks(MapRecoverManager.RecoverType.REMOVE, list);
            }
        }.runTask(API.getPlugin());
    }
    
    private void setPlayerHealth() {
        for (final UUID uuid : Main.getArena().getPlayers()) {
            Bukkit.getPlayer(uuid).setMaxHealth(40.0);
            Bukkit.getPlayer(uuid).setHealthScale(40.0);
            Bukkit.getPlayer(uuid).setHealthScaled(true);
            Bukkit.getPlayer(uuid).setHealth(40.0);
        }
    }
    
    private void defaultPlayerHealth() {
        for (final UUID uuid : Main.getArena().getPlayers()) {
            int n = 20;
            if (PlayerPerk.get(Bukkit.getPlayer(uuid)).isActivated(Perk.PerkType.RAMBO)) {
                n = 10;
            }
            if (PlayerPerk.get(Bukkit.getPlayer(uuid)).isActivated(Perk.PerkType.THICK)) {
                n += 4;
            }
            Bukkit.getPlayer(uuid).setHealth(n);
            Bukkit.getPlayer(uuid).setMaxHealth(n);
            Bukkit.getPlayer(uuid).setHealthScale(n);
            Bukkit.getPlayer(uuid).setHealthScaled(true);
        }
    }
    
    private void makePlayersTop() {
        final Map<UUID, Integer> sortByValue = sortByValue(this.playerDamage);
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
    
    private static Map<UUID, Integer> sortByValue(final Map<UUID, Integer> map) {
        final List<Map.Entry<UUID, Integer>> list = new LinkedList<>(map.entrySet());

        Comparator<Map.Entry<UUID, Integer>> valueComparator = Map.Entry.comparingByValue();

        list.sort(valueComparator);

        final Map<UUID, Integer> linkedHashMap = new LinkedHashMap<>();
        for (final Map.Entry<UUID, Integer> entry : list) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }

        return linkedHashMap;
    }
    
    @EventHandler
    private void onPlayerDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.isCancelled() || !(entityDamageByEntityEvent.getEntity() instanceof Player) || !Main.getArena().getPlayers().contains(entityDamageByEntityEvent.getEntity().getUniqueId())) {
            return;
        }
        final Player player = (Player)entityDamageByEntityEvent.getEntity();
        Player player2 = null;
        if (entityDamageByEntityEvent.getCause() != EntityDamageEvent.DamageCause.PROJECTILE && entityDamageByEntityEvent.getDamager() instanceof Player && !player.equals(entityDamageByEntityEvent.getDamager())) {
            player2 = (Player)entityDamageByEntityEvent.getDamager();
        }
        else if (entityDamageByEntityEvent.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            player2 = (Player)((Projectile)entityDamageByEntityEvent.getDamager()).getShooter();
        }
        if (player2 != null) {
            this.playerDamage.put(player2.getUniqueId(), this.playerDamage.getOrDefault(player2.getUniqueId(), 0) + (int)entityDamageByEntityEvent.getDamage());
        }
    }
    
    @Override
    public int getRagePitDamageDealt(final Player player) {
        if (!this.playerDamage.containsKey(player.getUniqueId())) {
            return 0;
        }
        return this.playerDamage.get(player.getUniqueId());
    }
    
    @Override
    public void addRagePitKill() {
        ++this.kills;
    }
    
    @Override
    public int getRagePitKills() {
        return this.kills;
    }
    
    static {
        instance = new RagePit();
    }
}
