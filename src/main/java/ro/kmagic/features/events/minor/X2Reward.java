package ro.kmagic.features.events.minor;

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
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.libapi.hologram.RefreshableHologram;
import java.util.ArrayList;
import ro.kmagic.features.events.PitEvent;

public class X2Reward extends PitEvent {
    private static final X2Reward instance;
    private ArrayList<RefreshableHologram> refreshableHolograms;
    private BukkitTask task;
    private BukkitTask task2;
    private final int requiredPlayers;
    private final int duration;
    private final String eventName;
    private final List<Location> hologramLocations;
    private final List<String> preStart;
    private final List<String> start;
    private final List<String> end;
    private final long eventDuration;
    
    private X2Reward() {
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        this.requiredPlayers = Main.getPitEventManager().getInt("Event.Minor.X2_REWARD.RequiredPlayers");
        this.eventName = Main.getPitEventManager().getEventName(PitEventManager.PitEventType.X2_REWARD);
        this.hologramLocations = Main.getEventsHologram().getLocations();
        this.preStart = Main.getEventsHologram().getStringList("MinorEvent.PreStart");
        this.duration = Main.getPitEventManager().getMinorDuration(PitEventManager.PitEventType.X2_REWARD.toString());
        this.start = Main.getEventsHologram().getStringList("MinorEvent.Start");
        this.eventDuration = Main.getPitEventManager().getMinorDuration(PitEventManager.PitEventType.X2_REWARD.name()) * 20L;
        this.end = Main.getPitEventManager().getMinorEndMessage(this.eventName);
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
        }.runTaskTimerAsynchronously(API.getPlugin(), 0L, 20L);
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
                    return X2Reward.this.getDescription();
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
                X2Reward.this.task.cancel();
                for (RefreshableHologram refreshableHologram : list) {
                    refreshableHologram.remove();
                }
                Main.getPitEventManager().setStarting(false);
                X2Reward.this.startEvent();
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
        }.runTaskTimerAsynchronously(API.getPlugin(), 0L, 20L);
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
                    return X2Reward.this.getDescription();
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
        this.task2 = new BukkitRunnable() {
            public void run() {
                X2Reward.this.endEvent();
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
        return Main.getPitEventManager().getMinorDescription(PitEventManager.PitEventType.X2_REWARD.toString());
    }
    
    public static X2Reward getInstance() {
        return X2Reward.instance;
    }
    
    static {
        instance = new X2Reward();
    }
}
