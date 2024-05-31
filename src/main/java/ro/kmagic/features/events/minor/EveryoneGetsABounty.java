package ro.kmagic.features.events.minor;

import java.util.Arrays;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import ro.kmagic.libapi.hologram.RefreshableHologram;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.chatoption.PlayerChatOption;
import org.bukkit.Bukkit;
import java.util.UUID;
import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.features.events.PitEventManager;

import java.util.ArrayList;
import ro.kmagic.Main;
import org.bukkit.Location;
import java.util.List;
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.features.events.PitEvent;

public class EveryoneGetsABounty extends PitEvent {
    private static final EveryoneGetsABounty instance;
    private BukkitTask task;
    private final List<Location> hologramLocations;
    private final List<String> preStart;
    private final List<String> end;
    private final int requiredPlayers;
    private final String eventName;
    
    private EveryoneGetsABounty() {
        this.requiredPlayers = Main.getPitEventManager().getInt("Event.Minor.EVERYONE_GETS_A_BOUNTY.RequiredPlayers");
        this.hologramLocations = new ArrayList<Location>(Main.getEventsHologram().getLocations());
        this.preStart = new ArrayList<String>(Main.getEventsHologram().getStringList("MinorEvent.PreStart"));
        this.eventName = Main.getPitEventManager().getEventName(PitEventManager.PitEventType.EVERYONE_GETS_A_BOUNTY);
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
                    return EveryoneGetsABounty.this.getDescription();
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
                EveryoneGetsABounty.this.task.cancel();
                for (RefreshableHologram refreshableHologram : list) {
                    refreshableHologram.remove();
                }
                Main.getPitEventManager().setStarting(false);
                EveryoneGetsABounty.this.startEvent();
            }
        }.runTaskLater(API.getPlugin(), Main.getPitEventManager().getSecondsBeforeStart());
    }
    
    private void startEvent() {
        Main.getPitEventManager().setStarted(true);
        for (final UUID uuid : Main.getArena().getPlayers()) {
            Main.getBounty().setBounty(Bukkit.getPlayer(uuid), Main.getBounty().getBounty(Bukkit.getPlayer(uuid)) + 100, false);
        }
        this.endEvent();
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
        return Main.getPitEventManager().getMinorDescription(PitEventManager.PitEventType.EVERYONE_GETS_A_BOUNTY.toString());
    }
    
    public static EveryoneGetsABounty getInstance() {
        return EveryoneGetsABounty.instance;
    }
    
    static {
        instance = new EveryoneGetsABounty();
    }
}
