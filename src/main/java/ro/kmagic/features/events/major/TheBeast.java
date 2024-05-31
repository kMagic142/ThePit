package ro.kmagic.features.events.major;

import java.util.*;

import ro.kmagic.features.economy.renown.RenownEconomy;
import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import ro.kmagic.utils.ItemData;
import ro.kmagic.files.map.Region;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerQuitEvent;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import org.bukkit.Location;
import ro.kmagic.features.events.PitEventManager;
import org.bukkit.Bukkit;
import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.Main;
import ro.kmagic.libapi.hologram.RefreshableHologram;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.event.Listener;
import ro.kmagic.features.events.PitEvent;

public class TheBeast extends PitEvent implements Listener {
    private static TheBeast instace;
    private static BukkitTask task;
    private static BukkitTask task2;
    private static BukkitTask task3;
    private static BukkitTask beastTask;
    private int seconds;
    private int maxSeconds;
    private boolean over60;
    private Player beast;
    private ArrayList<RefreshableHologram> refreshableHolograms;
    private final HashMap<UUID, Integer> beastKills;
    private final HashMap<Integer, Player> top;
    
    private TheBeast() {
        this.beastKills = new HashMap<UUID, Integer>();
        this.top = new HashMap<Integer, Player>();
    }
    
    @Override
    public void preStartEvent() {
        if (Main.getPitEventManager().isStarting()) {
            return;
        }
        if (Main.getPitEventManager().isStarted()) {
            return;
        }
        this.maxSeconds = 0;
        if (Main.getArena().getPlayers().size() < Main.getPitEventManager().getInt("Event.Major.THE_BEAST.RequiredPlayers")) {
            this.endEvent(false);
            return;
        }
        Main.getPitEventManager().setStarting(true);
        Main.getPitEventManager().setEventTime(Main.getPitEventManager().getSecondsBeforeStart() / 20);
        TheBeast.task = new BukkitRunnable() {
            public void run() {
                Main.getPitEventManager().setEventTime(Main.getPitEventManager().getEventTime() - 1);
            }
        }.runTaskTimer(API.getPlugin(), 0L, 20L);
        for (final UUID key : Main.getArena().getPlayers()) {
            for (String s : Main.getPitEventManager().getMajorAnnounceMessage(Main.getPitEventManager().getEventName(PitEventManager.PitEventType.THE_BEAST), Main.getPitEventManager().getLocationName(), Main.getPitEventManager().getTimeFormatted())) {
                Bukkit.getPlayer(key).sendMessage(s);
            }
        }
        final ArrayList<RefreshableHologram> list = new ArrayList<RefreshableHologram>();
        for (final Location location : Main.getEventsHologram().getLocations()) {
            list.add(new RefreshableHologram(location.toString(), location, Main.getEventsHologram().getStringList("MajorEvent.PreStart"), Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 20) {
                @Override
                public String refresh() {
                    return TheBeast.this.getDescription();
                }
            }, new RefreshablePlaceholder("%eventTime%", 8) {
                @Override
                public String refresh() {
                    return Main.getPitEventManager().getTimeFormatted();
                }
            }), 20));
        }
        TheBeast.task3 = new BukkitRunnable() {
            public void run() {
                TheBeast.task.cancel();
                for (RefreshableHologram refreshableHologram : list) {
                    refreshableHologram.remove();
                }
                Main.getPitEventManager().setStarting(false);
                TheBeast.this.startEvent();
            }
        }.runTaskLater(API.getPlugin(), Main.getPitEventManager().getSecondsBeforeStart());
    }
    
    private void startEvent() {
        API.registerEvent(this);
        Main.getPitEventManager().setStarted(true);
        Main.getPitEventManager().setEventTime(Main.getPitEventManager().getMajorDuration(PitEventManager.PitEventType.THE_BEAST.toString()));
        TheBeast.task = new BukkitRunnable() {
            public void run() {
                Main.getPitEventManager().setEventTime(Main.getPitEventManager().getEventTime() - 1);
            }
        }.runTaskTimer(API.getPlugin(), 0L, 20L);
        this.selectRandomBeast();
        if (Main.getPitEventManager().isStarted()) {
            for (final UUID uuid : Main.getArena().getPlayers()) {
                for (String s : Main.getPitEventManager().getMajorStartMessage(Main.getPitEventManager().getEventName(PitEventManager.PitEventType.THE_BEAST), Main.getPitEventManager().getLocationName(), Main.getPitEventManager().getTimeFormatted())) {
                    Bukkit.getPlayer(uuid).sendMessage(s);
                }
            }
            this.refreshableHolograms = new ArrayList<RefreshableHologram>();
            for (final Location location : Main.getEventsHologram().getLocations()) {
                this.refreshableHolograms.add(new RefreshableHologram(location.toString(), location, Main.getEventsHologram().getStringList("MajorEvent.Start"), Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 20) {
                    @Override
                    public String refresh() {
                        return TheBeast.this.getDescription();
                    }
                }, new RefreshablePlaceholder("%eventTime%", 8) {
                    @Override
                    public String refresh() {
                        return Main.getPitEventManager().getTimeFormatted();
                    }
                }), 20));
            }
            TheBeast.task2 = new BukkitRunnable() {
                public void run() {
                    if (!Main.getPitEventManager().isStarting() && !Main.getPitEventManager().isSearching()) {
                        TheBeast.this.endEvent(true);
                    }
                }
            }.runTaskLater(API.getPlugin(), Main.getPitEventManager().getMajorDuration(PitEventManager.PitEventType.THE_BEAST.name()) * 20L);
        }
    }
    
    private void endEvent(final boolean b) {
        if (TheBeast.task != null) {
            TheBeast.task.cancel();
        }
        if (TheBeast.task2 != null) {
            TheBeast.task2.cancel();
        }
        if (TheBeast.task3 != null) {
            TheBeast.task3.cancel();
        }
        if (TheBeast.beastTask != null) {
            TheBeast.beastTask.cancel();
        }
        for (Player value : Bukkit.getOnlinePlayers()) {
            this.defaultPlayerHealth(value);
        }
        if (!Main.getPitEventManager().isStarted() && !Main.getPitEventManager().isSearching()) {
            Main.getPitEventManager().setPitEvent(null);
            Main.getPitEventManager().setEventType(null);
            if (!Main.getPitEventManager().isSearching()) {
                Main.getPitEventManager().startPitEvents(true);
            }
            return;
        }
        Main.getPitEventManager().setStarted(false);
        if (this.beast != null) {
            this.defaultPlayerHealth(this.beast);
            Main.getPlayerManager().restoreTheBeastCached(this.beast);
        }
        PlayerDeathEvent.getHandlerList().unregister(this);
        PlayerQuitEvent.getHandlerList().unregister(this);
        if (this.refreshableHolograms != null) {
            for (RefreshableHologram refreshableHologram : this.refreshableHolograms) {
                refreshableHologram.remove();
            }
        }
        if (b) {
            this.makePlayersTop();
            this.rewardPlayers();
        }
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
        this.beast = null;
        final String s = (player != null) ? String.valueOf(this.beastKills.get(player.getUniqueId())) : translateAlternateColorCodes;
        final String s2 = (player2 != null) ? String.valueOf(this.beastKills.get(player2.getUniqueId())) : translateAlternateColorCodes;
        final String s3 = (player3 != null) ? String.valueOf(this.beastKills.get(player3.getUniqueId())) : translateAlternateColorCodes;
        final int endReward = Main.getPitEventManager().getEndReward(PitEventManager.PitEventType.THE_BEAST.name(), "Success");
        for (final UUID uuid : Main.getArena().getPlayers()) {
            final Player player4 = Bukkit.getPlayer(uuid);
            if (b) {
                if (!this.over60) {
                    PlayerEconomy.get(player4).setBalance(PlayerEconomy.EconomyAction.ADD, endReward);
                }
                for (String value : Main.getPitEventManager().getTheBeastEndMessage(!this.over60, player, player2, player3, s, s2, s3)) {
                    player4.sendMessage(value);
                }
            }
        }
        this.over60 = false;
        this.maxSeconds = 0;
        this.seconds = 0;
        this.top.clear();
        this.beastKills.clear();
        Main.getPitEventManager().setEventType(null);
        this.beast = null;
        if (!Main.getPitEventManager().isSearching()) {
            Main.getPitEventManager().startPitEvents(false);
        }
    }
    
    @Override
    public String getDescription() {
        return Main.getPitEventManager().getMajorDescription(PitEventManager.PitEventType.THE_BEAST.toString());
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerKill(final PlayerDeathEvent playerDeathEvent) {
        final Player killed = playerDeathEvent.getKilled();
        final Player killer = playerDeathEvent.getKiller();
        if (killer == null) {
            return;
        }
        if (this.isBeast(killed)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                this.defaultPlayerHealth(player);
            }
            this.setBeast(killer);
            if (!Main.getArena().getPlayers().contains(killed.getUniqueId())) {
                Main.getPlayerManager().removeCacheTheBeast(killed);
            }
            this.beastKills.put(killer.getUniqueId(), this.beastKills.getOrDefault(killer.getUniqueId(), 0) + 1);
        }
    }
    
    @EventHandler
    private void onPlayerQuit(final PlayerQuitEvent playerQuitEvent) {
        this.playerQuit(playerQuitEvent.getPlayer());
    }
    
    @Override
    public void playerQuit(final Player player) {
        if (!this.isBeast(player)) {
            return;
        }
        for (UUID uuid : Main.getArena().getPlayers()) {
            this.defaultPlayerHealth(Bukkit.getPlayer(uuid));
        }
        this.selectRandomBeast();
    }
    
    public static TheBeast getInstace() {
        return TheBeast.instace;
    }
    
    @Override
    public void selectRandomBeast() {
        if (Main.getArena().getPlayers().size() != 0) {
            final ArrayList<Object> list = new ArrayList<Object>();
            for (final UUID uuid : Main.getArena().getPlayers()) {
                if (!Main.getRegionManager().inRegion(Bukkit.getPlayer(uuid).getLocation(), Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 1), Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 2))) {
                    list.add(uuid);
                }
            }
            if (list.size() == 0) {
                for (UUID uuid : Main.getArena().getPlayers()) {
                    Bukkit.getPlayer(uuid).sendMessage(Main.getPitEventManager().getString("Event.Major.THE_BEAST.All-Players-In-Spawn"));
                }
                this.endEvent(false);
            }
            else {
                this.setBeast(Bukkit.getPlayer((UUID)list.get(Main.getArena().getRandomInt(list.size() - 1))));
            }
        }
        else {
            this.endEvent(false);
        }
    }
    
    @Override
    public boolean isBeast(final Player player) {
        return this.beast != null && player.getUniqueId().equals(this.beast.getUniqueId());
    }
    
    private void setBeast(final Player player) {
        this.beast = player;
        this.seconds = 0;
        if (TheBeast.beastTask != null) {
            TheBeast.beastTask.cancel();
        }
        final PlayerInventory inventory = player.getInventory();
        final ArrayList<ItemData> list = new ArrayList<ItemData>();
        if (inventory.getHelmet() != null) {
            list.add(new ItemData(inventory.getHelmet(), ItemData.ItemType.HELMET));
        }
        else {
            list.add(new ItemData(new ItemStack(Material.AIR), ItemData.ItemType.HELMET));
        }
        if (inventory.getChestplate() != null) {
            list.add(new ItemData(inventory.getChestplate(), ItemData.ItemType.CHESTPLATE));
        }
        else {
            list.add(new ItemData(new ItemStack(Material.AIR), ItemData.ItemType.CHESTPLATE));
        }
        if (inventory.getLeggings() != null) {
            list.add(new ItemData(inventory.getLeggings(), ItemData.ItemType.LEGGINGS));
        }
        else {
            list.add(new ItemData(new ItemStack(Material.AIR), ItemData.ItemType.LEGGINGS));
        }
        if (inventory.getBoots() != null) {
            list.add(new ItemData(inventory.getBoots(), ItemData.ItemType.BOOTS));
        }
        else {
            list.add(new ItemData(new ItemStack(Material.AIR), ItemData.ItemType.BOOTS));
        }
        if (inventory.getItem(0) != null) {
            list.add(new ItemData(inventory.getItem(0), ItemData.ItemType.SWORD));
        }
        else {
            list.add(new ItemData(new ItemStack(Material.AIR), ItemData.ItemType.SWORD));
        }
        Main.getPlayerManager().cacheTheBeast(player, list);
        new BukkitRunnable() {
            public void run() {
                inventory.setItem(0, Materials.DIAMOND_SWORD.getItem().metadata().add("TheBeast").setUnbreakable(true).build());
                inventory.setHelmet(Materials.DIAMOND_HELMET.getItem().metadata().add("TheBeast").setUnbreakable(true).build());
                inventory.setChestplate(Materials.DIAMOND_CHESTPLATE.getItem().metadata().add("TheBeast").setUnbreakable(true).build());
                inventory.setLeggings(Materials.DIAMOND_LEGGINGS.getItem().metadata().add("TheBeast").setUnbreakable(true).build());
                if (!PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                    inventory.setBoots(Materials.DIAMOND_BOOTS.getItem().metadata().add("TheBeast").setUnbreakable(true).build());
                }
            }
        }.runTaskLater(API.getPlugin(), 4L);
        this.setPlayerHealth(player);
        if (this.over60) {
            return;
        }
        TheBeast.beastTask = new BukkitRunnable() {
            public void run() {
                ++TheBeast.this.seconds;
                if (TheBeast.this.seconds > TheBeast.this.maxSeconds) {
                    ++TheBeast.this.maxSeconds;
                }
                if (TheBeast.this.seconds >= 60) {
                    TheBeast.this.over60 = true;
                }
            }
        }.runTaskTimer(API.getPlugin(), 0L, 20L);
    }
    
    private void makePlayersTop() {
        final Map<UUID, Integer> sortByValue = sortByValue(this.beastKills);
        final ArrayList<UUID> list = new ArrayList<UUID>();
        for (final Map.Entry<UUID, Integer> entry : sortByValue.entrySet()) {
            if ((Bukkit.getPlayer(entry.getKey()) == null || !Bukkit.getPlayer(entry.getKey()).isOnline()) && !Main.getArena().getPlayers().contains(entry.getKey())) {
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
    
    private void setPlayerHealth(final Player player) {
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }

        player.setMaxHealth(40.0);
        player.setHealthScale(40.0);
        player.setHealthScaled(true);
        player.setHealth(40.0);
    }
    
    private void defaultPlayerHealth(final Player player) {
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        int n = 20;
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.RAMBO)) {
            n = 10;
        }
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.THICK)) {
            n += 4;
        }
        player.setHealth(n);
        player.setMaxHealth(n);
        player.setHealthScale(n);
        player.setHealthScaled(true);
    }
    
    private void rewardPlayers() {
        final int reward = Main.getPitEventManager().getReward(PitEventManager.PitEventType.THE_BEAST.name(), "3");
        final int reward2 = Main.getPitEventManager().getReward(PitEventManager.PitEventType.THE_BEAST.name(), "4-20");
        final int reward3 = Main.getPitEventManager().getReward(PitEventManager.PitEventType.THE_BEAST.name(), "Rest");
        for (int i = 1; i <= this.top.size(); ++i) {
            int n = 0;
            int n2;
            if (i <= 3) {
                n2 = reward;
                n = 2;
            }
            else if (i <= 20) {
                n2 = reward2;
                n = 1;
            }
            else {
                n2 = reward3;
            }
            final Player player = Bukkit.getPlayer(this.top.get(i).getUniqueId());
            PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, n2);
            if (n != 0 && PlayerPrestige.get(player).getPrestige() != 0) {
                RenownEconomy.get(player).setBalance(RenownEconomy.RenownAction.ADD, n);
            }
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
    public int getTheBeastKills(final Player player) {
        if (!this.beastKills.containsKey(player.getUniqueId())) {
            return 0;
        }
        return this.beastKills.get(player.getUniqueId());
    }
    
    @Override
    public int getTheBeastMaxLivingTime() {
        return this.maxSeconds;
    }
    
    static {
        TheBeast.instace = new TheBeast();
    }
}
