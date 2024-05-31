package ro.kmagic.features.events.minor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.hologram.RefreshableHologram;
import ro.kmagic.libapi.item.ItemBuilder;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.Main;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.chatoption.PlayerChatOption;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.features.events.PitEvent;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.managers.MapRecoverManager;

import java.util.*;

public class CarePackage extends PitEvent implements Listener {
    private static final CarePackage instance;
    private ArrayList<RefreshableHologram> refreshableHolograms;
    private BukkitTask task;
    private BukkitTask task2;
    private final String carePackageName;
    private int clicksLeft;
    private Inventory carePackage;
    private RefreshableHologram carePackageHologram;
    private final List<Location> hologramLocations;
    private final List<String> preStartMessage;
    private final List<String> start;
    private final List<String> endMessage;
    private final List<String> carePackageHologramLines;
    private final int duration;
    private final int requiredPlayers;
    private final long eventDuration;
    private final String eventName;
    
    private CarePackage() {
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        this.clicksLeft = 0;
        this.carePackageName = Main.getPitEventManager().getString("Event.Minor.CARE_PACKAGE.ChestName");
        this.hologramLocations = new ArrayList<Location>(Main.getEventsHologram().getLocations());
        this.preStartMessage = new ArrayList<String>(Main.getEventsHologram().getStringList("MinorEvent.PreStart"));
        this.duration = Main.getPitEventManager().getMinorDuration(PitEventManager.PitEventType.CARE_PACKAGE.toString());
        this.requiredPlayers = Main.getPitEventManager().getInt("Event.Minor.CARE_PACKAGE.RequiredPlayers");
        this.eventName = Main.getPitEventManager().getEventName(PitEventManager.PitEventType.CARE_PACKAGE);
        this.endMessage = new ArrayList<String>(Main.getPitEventManager().getMinorEndMessage(this.eventName));
        this.carePackageHologramLines = new ArrayList<String>(Main.getEventsHologram().getStringList("CarePackage.Hologram"));
        this.eventDuration = Main.getPitEventManager().getMinorDuration(PitEventManager.PitEventType.CARE_PACKAGE.name()) * 20L;
        this.start = Main.getEventsHologram().getStringList("MinorEvent.Start");
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
        final List<String> minorAnnounceMessage = Main.getPitEventManager().getMinorAnnounceMessage(this.eventName, Main.getPitEventManager().getLocationCustomName(), Main.getPitEventManager().getTimeFormatted());
        for (final UUID uuid : Main.getArena().getPlayers()) {
            if (PlayerChatOption.get(Bukkit.getPlayer(uuid)).isEnabled(ChatOption.ChatOptionType.MINOR_EVENTS)) {
                for (String s : minorAnnounceMessage) {
                    Bukkit.getPlayer(uuid).sendMessage(s);
                }
            }
        }
        final ArrayList<RefreshableHologram> list = new ArrayList<RefreshableHologram>();
        for (final Location location : this.hologramLocations) {
            list.add(new RefreshableHologram(location.toString(), location, this.preStartMessage, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return CarePackage.this.getDescription();
                }
            }, new RefreshablePlaceholder("%eventTime%", 8) {
                @Override
                public String refresh() {
                    return Main.getPitEventManager().getTimeFormatted();
                }
            }, new RefreshablePlaceholder("%eventLocation%", 0) {
                @Override
                public String refresh() {
                    return Main.getPitEventManager().getLocationCustomName();
                }
            }), 20));
        }
        new BukkitRunnable() {
            public void run() {
                CarePackage.this.task.cancel();
                for (RefreshableHologram refreshableHologram : list) {
                    refreshableHologram.remove();
                }
                Main.getPitEventManager().setStarting(false);
                CarePackage.this.startEvent();
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
        for (final UUID uuid : Main.getArena().getPlayers()) {
            for (String s : Main.getPitEventManager().getMinorStartMessage(this.eventName, Main.getPitEventManager().getLocationCustomName(), Main.getPitEventManager().getTimeFormatted())) {
                Bukkit.getPlayer(uuid).sendMessage(s);
            }
        }
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        for (final Location location : this.hologramLocations) {
            this.refreshableHolograms.add(new RefreshableHologram(location.toString(), location, this.start, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return CarePackage.this.getDescription();
                }
            }, new RefreshablePlaceholder("%eventTime%", 8) {
                @Override
                public String refresh() {
                    return Main.getPitEventManager().getTimeFormatted();
                }
            }, new RefreshablePlaceholder("%eventLocation%", 0) {
                @Override
                public String refresh() {
                    return Main.getPitEventManager().getLocationCustomName();
                }
            }), 20));
        }
        this.spawnCarePackage(Main.getPitEventManager().getRandomLocation(PitEventManager.PitEventType.CARE_PACKAGE));
        this.task2 = new BukkitRunnable() {
            public void run() {
                CarePackage.this.endEvent();
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
        for (final UUID uuid : Main.getArena().getPlayers()) {
            if (Bukkit.getPlayer(uuid).getOpenInventory().getTitle().equals(this.carePackageName)) {
                Bukkit.getPlayer(uuid).getOpenInventory().close();
            }
        }
        Main.getPitEventManager().setStarted(false);
        if (this.task != null) {
            this.task.cancel();
        }
        if (this.task2 != null) {
            this.task2.cancel();
        }
        if (this.carePackageHologram != null) {
            this.carePackageHologram.remove();
        }
        Main.getMapRecoverManager().removeBlock(MapRecoverManager.RecoverType.REMOVE, Main.getPitEventManager().getRandomLocation(PitEventManager.PitEventType.CARE_PACKAGE).getBlock());
        Main.getPitEventManager().getRandomLocation(PitEventManager.PitEventType.CARE_PACKAGE).getBlock().setType(Material.AIR);
        PlayerInteractEvent.getHandlerList().unregister(this);
        InventoryClickEvent.getHandlerList().unregister(this);
        for (RefreshableHologram refreshableHologram : this.refreshableHolograms) {
            refreshableHologram.remove();
        }
        for (final UUID uuid2 : Main.getArena().getPlayers()) {
            for (String s : this.endMessage) {
                Bukkit.getPlayer(uuid2).sendMessage(s);
            }
        }
        Main.getPitEventManager().setEventType(null);
        Main.getPitEventManager().startPitEvents(false);
    }
    
    @EventHandler
    private void onCarePackageClick(final PlayerInteractEvent playerInteractEvent) {
        if (!playerInteractEvent.getAction().name().contains("CLICK_BLOCK") || !playerInteractEvent.getClickedBlock().getType().equals(Material.CHEST) || !playerInteractEvent.getClickedBlock().getLocation().equals(Main.getPitEventManager().getRandomLocation(PitEventManager.PitEventType.CARE_PACKAGE))) {
            return;
        }
        playerInteractEvent.setCancelled(true);
        if (this.clicksLeft == 0) {
            playerInteractEvent.getPlayer().openInventory(this.carePackage);
            return;
        }
        --this.clicksLeft;
    }
    
    @EventHandler
    private void onPlayerClick(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (inventoryClickEvent.getClickedInventory() == null || !inventoryClickEvent.getView().getTitle().equals(this.carePackageName)) {
            return;
        }
        inventoryClickEvent.setCancelled(true);
        if (inventoryClickEvent.getCurrentItem() == null) {
            return;
        }
        if (!inventoryClickEvent.getClickedInventory().getType().equals(InventoryType.CHEST)) {
            return;
        }
        final ItemStack currentItem = inventoryClickEvent.getCurrentItem();
        if (currentItem.getItemMeta() == null) {
            return;
        }
        final String displayName = currentItem.getItemMeta().getDisplayName();
        if (currentItem.getType().equals(Materials.GOLD_INGOT.getItem().getMaterial())) {
            int n = 0;
            final String s = displayName;
            switch (s) {
                case "20": {
                    n = 20;
                    break;
                }
                case "30": {
                    n = 30;
                    break;
                }
                case "50": {
                    n = 50;
                    break;
                }
            }
            PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, n);
        }
        else if (currentItem.getType().equals(Materials.EXPERIENCE_BOTTLE.getItem().getMaterial())) {
            int n3 = 0;
            if (displayName.contains("20")) {
                n3 = 20;
            }
            if (displayName.contains("30")) {
                n3 = 30;
            }
            if (displayName.contains("50")) {
                n3 = 50;
            }
            final String s2 = displayName;
            switch (s2) {
                case "20": {
                    n3 = 20;
                    break;
                }
                case "30": {
                    n3 = 30;
                    break;
                }
                case "50": {
                    n3 = 50;
                    break;
                }
            }
            player.giveExp(n3);
        }
        else if (currentItem.getType().name().contains("DIAMOND_")) {
            player.getInventory().addItem(currentItem);
        }
        this.carePackage.remove(currentItem);
        boolean b = true;
        final ItemStack[] contents = inventoryClickEvent.getInventory().getContents();
        for (int length = contents.length, i = 0; i < length; ++i) {
            if (contents[i] != null) {
                b = false;
                break;
            }
        }
        if (b) {
            this.endEvent();
        }
    }
    
    @Override
    public String getDescription() {
        return Main.getPitEventManager().getMinorDescription(PitEventManager.PitEventType.CARE_PACKAGE.toString());
    }
    
    public static CarePackage getInstance() {
        return CarePackage.instance;
    }
    
    private void spawnCarePackage(final Location location) {
        this.clicksLeft = Main.getPitEventManager().getInt("Event.Minor.CARE_PACKAGE.ClicksToBeOpened");
        location.getBlock().setType(Materials.CHEST.getItem().getMaterial());
        Main.getMapRecoverManager().addBlock(MapRecoverManager.RecoverType.REMOVE, location.getBlock());
        this.carePackage = Bukkit.createInventory(null, 27, this.carePackageName);
        final ArrayList<Integer> list = new ArrayList<Integer>();
        final HashMap<Integer, ItemStack> hashMap = new HashMap<Integer, ItemStack>();
        hashMap.put(1, this.carePackageItem(ItemType.GOLD, "20g"));
        hashMap.put(2, this.carePackageItem(ItemType.XP, "20XP"));
        hashMap.put(3, this.carePackageItem(ItemType.GOLD, "30g"));
        hashMap.put(4, this.carePackageItem(ItemType.ARMOR, ""));
        hashMap.put(5, this.carePackageItem(ItemType.GOLD, "50g"));
        hashMap.put(6, this.carePackageItem(ItemType.XP, "50XP"));
        hashMap.put(7, this.carePackageItem(ItemType.XP, "30XP"));
        hashMap.put(8, this.carePackageItem(ItemType.SWORD, ""));
        for (int i = 1; i <= 8; ++i) {
            int randomInt;
            do {
                randomInt = Main.getArena().getRandomInt(26);
            } while (list.contains(randomInt));
            list.add(randomInt);
            this.carePackage.setItem(randomInt, hashMap.get(i));
        }
        this.carePackageHologram = new RefreshableHologram(UUID.randomUUID().toString().substring(0, 6), new Location(location.getWorld(), location.getBlockX() + 0.5, location.getBlockY() + 1, location.getBlockZ() + 0.5), this.carePackageHologramLines, Collections.singletonList(new RefreshablePlaceholder("%clicksLeft%", 5) {
            @Override
            public String refresh() {
                return String.valueOf(CarePackage.this.clicksLeft);
            }
        }), 5);
    }
    
    private ItemStack carePackageItem(final ItemType itemType, final String s) {
        switch (itemType) {
            case GOLD: {
                return Materials.GOLD_INGOT.getItem().setDisplayName("&6&l" + s).build();
            }
            case XP: {
                return Materials.EXPERIENCE_BOTTLE.getItem().setDisplayName("&b&l" + s).build();
            }
            case ARMOR: {
                ItemBuilder itemBuilder = null;
                switch (Main.getArena().getRandomInt(2)) {
                    case 0: {
                        itemBuilder = Materials.DIAMOND_HELMET.getItem();
                        break;
                    }
                    case 1: {
                        itemBuilder = Materials.DIAMOND_CHESTPLATE.getItem();
                        break;
                    }
                    case 2: {
                        itemBuilder = Materials.DIAMOND_LEGGINGS.getItem();
                        break;
                    }
                    default: {
                        itemBuilder = Materials.DIAMOND_BOOTS.getItem();
                        break;
                    }
                }
                return itemBuilder.setUnbreakable(true).enchantment().add(Enchantment.THORNS, 1).build();
            }
            case SWORD: {
                return Materials.DIAMOND_SWORD.getItem().setUnbreakable(true).enchantment().add(Enchantment.DAMAGE_ALL, 1).build();
            }
            default: {
                return null;
            }
        }
    }
    
    static {
        instance = new CarePackage();
    }
    
    public enum ItemType
    {
        GOLD, 
        XP, 
        SWORD, 
        ARMOR
    }
}
