package ro.kmagic.features.events.minor;

import java.util.Map;
import ro.kmagic.managers.MapRecoverManager;
import org.bukkit.World;
import org.bukkit.Material;

import java.util.Arrays;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.chatoption.PlayerChatOption;
import org.bukkit.Bukkit;
import java.util.UUID;
import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.Main;
import org.bukkit.Location;
import java.util.List;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import org.bukkit.block.Block;
import java.util.HashMap;
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.libapi.hologram.RefreshableHologram;
import java.util.ArrayList;
import ro.kmagic.features.events.PitEvent;

public class KingOfTheHill extends PitEvent {
    private static final KingOfTheHill instance;
    private ArrayList<RefreshableHologram> refreshableHolograms;
    private BukkitTask task;
    private BukkitTask task2;
    private final HashMap<Block, Materials> blocks;
    private final HashMap<Block, Byte> blockData;
    private final int requiredPlayers;
    private final int duration;
    private final List<Location> hologramLocations;
    private final List<String> preStart;
    private final List<String> start;
    private final List<String> end;
    private final String eventName;
    private final long eventDuration;
    
    private KingOfTheHill() {
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        this.blocks = new HashMap<Block, Materials>();
        this.blockData = new HashMap<Block, Byte>();
        this.requiredPlayers = Main.getPitEventManager().getInt("Event.Minor.KOTH.RequiredPlayers");
        this.hologramLocations = new ArrayList<Location>(Main.getEventsHologram().getLocations());
        this.preStart = new ArrayList<String>(Main.getEventsHologram().getStringList("MinorEvent.PreStart"));
        this.duration = Main.getPitEventManager().getMinorDuration(PitEventManager.PitEventType.KOTH.toString());
        this.start = new ArrayList<String>(Main.getEventsHologram().getStringList("MinorEvent.Start"));
        this.eventName = Main.getPitEventManager().getEventName(PitEventManager.PitEventType.KOTH);
        this.eventDuration = Main.getPitEventManager().getMinorDuration(PitEventManager.PitEventType.KOTH.name()) * 20L;
        this.end = new ArrayList<String>(Main.getPitEventManager().getMinorEndMessage(this.eventName));
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
            list.add(new RefreshableHologram(location.toString(), location, this.preStart, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return KingOfTheHill.this.getDescription();
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
                KingOfTheHill.this.task.cancel();
                for (RefreshableHologram refreshableHologram : list) {
                    refreshableHologram.remove();
                }
                Main.getPitEventManager().setStarting(false);
                KingOfTheHill.this.startEvent();
            }
        }.runTaskLater(API.getPlugin(), Main.getPitEventManager().getSecondsBeforeStart());
    }
    
    private void startEvent() {
        Main.getPitEventManager().setStarted(true);
        Main.getPitEventManager().setEventTime(this.duration);
        this.task = new BukkitRunnable() {
            public void run() {
                Main.getPitEventManager().setEventTime(Main.getPitEventManager().getEventTime() - 1);
            }
        }.runTaskTimer(API.getPlugin(), 0L, 20L);
        final List<String> minorStartMessage = Main.getPitEventManager().getMinorStartMessage(this.eventName, Main.getPitEventManager().getLocationCustomName(), Main.getPitEventManager().getTimeFormatted());
        for (final UUID uuid : Main.getArena().getPlayers()) {
            for (String s : minorStartMessage) {
                Bukkit.getPlayer(uuid).sendMessage(s);
            }
        }
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        for (final Location location : this.hologramLocations) {
            this.refreshableHolograms.add(new RefreshableHologram(location.toString(), location, this.start, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return KingOfTheHill.this.getDescription();
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
        this.spawnCircle(Main.getPitEventManager().getRandomLocation(PitEventManager.PitEventType.KOTH), Materials.DIAMOND_BLOCK.getItem().getMaterial());
        this.task2 = new BukkitRunnable() {
            public void run() {
                KingOfTheHill.this.endEvent();
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
        this.despawnCircle();
        Main.getPitEventManager().setStarted(false);
        if (this.task != null) {
            this.task.cancel();
        }
        if (this.task2 != null) {
            this.task2.cancel();
        }
        for (RefreshableHologram refreshableHologram : this.refreshableHolograms) {
            refreshableHologram.remove();
        }
        for (final UUID uuid : Main.getArena().getPlayers()) {
            for (String s : this.end) {
                Bukkit.getPlayer(uuid).sendMessage(s);
            }
        }
        Main.getPitEventManager().setEventType(null);
        Main.getPitEventManager().startPitEvents(false);
    }
    
    @Override
    public String getDescription() {
        return Main.getPitEventManager().getMinorDescription(PitEventManager.PitEventType.KOTH.toString());
    }
    
    public static KingOfTheHill getInstance() {
        return KingOfTheHill.instance;
    }
    
    private void spawnCircle(final Location location, final Material material) {
        new BukkitRunnable() {
            public void run() {
                int n = 7;
                final ArrayList<Block> list = new ArrayList<Block>();
                ++n;
                final int blockX = location.getBlockX();
                final int blockY = location.getBlockY();
                final int blockZ = location.getBlockZ();
                final int n2 = n * n;
                final World world = location.getWorld();
                for (int i = blockX - n; i <= blockX + n; ++i) {
                    for (int j = blockZ - n; j <= blockZ + n; ++j) {
                        if ((blockX - i) * (blockX - i) + (blockZ - j) * (blockZ - j) <= n2) {
                            final Location location = new Location(world, i, blockY, j);
                            if (!location.equals(new Location(world, blockX + n, blockY, blockZ)) && !location.equals(new Location(world, blockX - n, blockY, blockZ)) && !location.equals(new Location(world, blockX, blockY, blockZ + n)) && !location.equals(new Location(world, blockX, blockY, blockZ - n))) {
                                KingOfTheHill.this.blocks.put(world.getBlockAt(i, blockY, j), Materials.getBlockMaterial(world.getBlockAt(i, blockY, j)));
                                if (location.getBlockX() != world.getBlockAt(i, blockY, j).getX() || location.getBlockZ() != world.getBlockAt(i, blockY, j).getZ()) {
                                    list.add(world.getBlockAt(i, blockY, j));
                                    if (Materials.getBlockMaterial(world.getBlockAt(i, blockY, j)).hasData()) {
                                        KingOfTheHill.this.blockData.put(world.getBlockAt(i, blockY, j), world.getBlockAt(i, blockY, j).getData());
                                    }
                                    int finalI = i;
                                    int finalJ = j;
                                    new BukkitRunnable() {
                                        public void run() {
                                            world.getBlockAt(finalI, blockY, finalJ).setType(material);
                                        }
                                    }.runTaskLater(API.getPlugin(), 2L);
                                }
                            }
                        }
                    }
                }
                final Block block = world.getBlockAt(location);
                Main.getMapRecoverManager().addBlock(MapRecoverManager.RecoverType.REPLACE, block);
                if (API.getVersionSupport().contains(13, 14, 15, 16)) {
                    KingOfTheHill.this.blocks.put(block, Materials.getBlockMaterial(block));
                    block.setType(Materials.LIGHT_BLUE_STAINED_GLASS.getItem().getMaterial());
                }
                else {
                    KingOfTheHill.this.blocks.put(block, Materials.getBlockMaterial(block));
                    if (Materials.getBlockMaterial(block).hasData()) {
                        KingOfTheHill.this.blockData.put(block, block.getData());
                    }
                    block.setType(Material.STAINED_GLASS);
                    block.setData((byte)3);
                }
                final Block block2 = location.clone().add(0.0, -1.0, 0.0).getBlock();
                KingOfTheHill.this.blocks.put(block2, Materials.getBlockMaterial(block2));
                if (Materials.getBlockMaterial(block2).hasData()) {
                    KingOfTheHill.this.blockData.put(block2, block2.getData());
                }
                Main.getMapRecoverManager().addBlock(MapRecoverManager.RecoverType.REPLACE, block2);
                block2.setType(Materials.BEACON.getItem().getMaterial());
                for (int k = -1; k <= 1; ++k) {
                    for (int l = -1; l <= 1; ++l) {
                        final Block block3 = location.clone().add(k, -2.0, l).getBlock();
                        KingOfTheHill.this.blocks.put(block3, Materials.getBlockMaterial(block3));
                        if (Materials.getBlockMaterial(block3).hasData()) {
                            KingOfTheHill.this.blockData.put(block3, block3.getData());
                        }
                        block3.setType(material);
                    }
                }
                Main.getMapRecoverManager().addBlocks(MapRecoverManager.RecoverType.REPLACE, list);
            }
        }.runTask(API.getPlugin());
    }
    
    private void despawnCircle() {
        new BukkitRunnable() {
            public void run() {
                final ArrayList<Block> list = new ArrayList<Block>();
                for (final Map.Entry<Block, Materials> entry : KingOfTheHill.this.blocks.entrySet()) {
                    entry.getKey().setType((entry.getValue()).getItem().getMaterial());
                    if (!entry.getValue().hasData()) {
                        Byte data = KingOfTheHill.this.blockData.get(entry.getKey());
                        if(data != null) entry.getKey().setData(data);
                    }
                    list.add(entry.getKey());
                }
                Main.getMapRecoverManager().removeBlocks(MapRecoverManager.RecoverType.REPLACE, list);
            }
        }.runTask(API.getPlugin());
        new BukkitRunnable() {
            public void run() {
                KingOfTheHill.this.blocks.clear();
                KingOfTheHill.this.blockData.clear();
            }
        }.runTaskLater(API.getPlugin(), 8L);
    }
    
    static {
        instance = new KingOfTheHill();
    }
}
