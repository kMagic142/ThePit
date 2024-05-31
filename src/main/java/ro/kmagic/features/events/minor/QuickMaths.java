package ro.kmagic.features.events.minor;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.chatoption.PlayerChatOption;
import org.bukkit.Bukkit;
import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.Main;
import org.bukkit.Location;
import java.util.List;
import java.util.UUID;
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.libapi.hologram.RefreshableHologram;
import java.util.ArrayList;
import org.bukkit.event.Listener;
import ro.kmagic.features.events.PitEvent;

public class QuickMaths extends PitEvent implements Listener {
    private static final QuickMaths instance;
    private ArrayList<RefreshableHologram> refreshableHolograms;
    private BukkitTask task;
    private BukkitTask task2;
    private String symbol;
    private int a;
    private int b;
    private int answer;
    private int top;
    private final ArrayList<UUID> answered;
    private final int requiredPlayers;
    private final int duration;
    private final int rewardGold;
    private final int rewardXP;
    private final String eventName;
    private final List<Location> hologramLocations;
    private final List<String> preStart;
    private final List<String> start;
    private final long eventDuration;
    
    private QuickMaths() {
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        this.answered = new ArrayList<UUID>();
        this.requiredPlayers = Main.getPitEventManager().getInt("Event.Minor.QUICK_MATHS.RequiredPlayers");
        this.eventName = Main.getPitEventManager().getEventName(PitEventManager.PitEventType.QUICK_MATHS);
        this.hologramLocations = Main.getEventsHologram().getLocations();
        this.preStart = Main.getEventsHologram().getStringList("MinorEvent.PreStart");
        this.duration = Main.getPitEventManager().getMinorDuration(PitEventManager.PitEventType.QUICK_MATHS.toString());
        this.start = Main.getEventsHologram().getStringList("MinorEvent.Start");
        this.eventDuration = Main.getPitEventManager().getMinorDuration(PitEventManager.PitEventType.QUICK_MATHS.name()) * 20L;
        this.rewardGold = Main.getPitEventManager().getInt("Event.Minor.QUICK_MATHS.Rewards.Gold");
        this.rewardXP = Main.getPitEventManager().getInt("Event.Minor.QUICK_MATHS.Rewards.XP");
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
                    return QuickMaths.this.getDescription();
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
                QuickMaths.this.task.cancel();
                for (RefreshableHologram refreshableHologram : list) {
                    refreshableHologram.remove();
                }
                Main.getPitEventManager().setStarting(false);
                QuickMaths.this.startEvent();
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
        this.prepareOperation();
        for (final UUID uuid : Main.getArena().getPlayers()) {
            for (String s : Main.getPitEventManager().getQMAnnounce(this.a, this.symbol, this.b)) {
                Bukkit.getPlayer(uuid).sendMessage(s);
            }
        }
        this.refreshableHolograms = new ArrayList<RefreshableHologram>();
        for (final Location location : this.hologramLocations) {
            this.refreshableHolograms.add(new RefreshableHologram(location.toString(), location, this.start, Arrays.asList(new RefreshablePlaceholder("%eventDescription%", 0) {
                @Override
                public String refresh() {
                    return QuickMaths.this.getDescription();
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
                QuickMaths.this.endEvent();
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
        this.answered.clear();
        this.top = 0;
        AsyncPlayerChatEvent.getHandlerList().unregister(this);
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
            Bukkit.getPlayer(uuid).sendMessage(Main.getPitEventManager().getQMOver(this.a, this.symbol, this.b, this.answer));
        }
        Main.getPitEventManager().setEventType(null);
        Main.getPitEventManager().startPitEvents(false);
    }
    
    public static QuickMaths getInstance() {
        return QuickMaths.instance;
    }
    
    @Override
    public String getDescription() {
        return Main.getPitEventManager().getMinorDescription(PitEventManager.PitEventType.QUICK_MATHS.toString());
    }
    
    private void prepareOperation() {
        final String[] split = Main.getPitEventManager().getOperation().split(" ");
        this.symbol = QuickMathsSymbols.valueOf(split[0].toUpperCase()).getSymbol();
        this.a = Integer.parseInt(split[1]);
        this.b = Integer.parseInt(split[2]);
        final String symbol = this.symbol;
        switch (symbol) {
            case "+": {
                this.answer = this.a + this.b;
                break;
            }
            case "-": {
                this.answer = this.a - this.b;
                break;
            }
            case "*": {
                this.answer = this.a * this.b;
                break;
            }
            case "/": {
                this.answer = this.a / this.b;
                break;
            }
        }
    }
    
    @EventHandler
    private void onPlayerChat(final AsyncPlayerChatEvent asyncPlayerChatEvent) {
        final Player player = asyncPlayerChatEvent.getPlayer();
        if (!asyncPlayerChatEvent.getMessage().equals(String.valueOf(this.answer))) {
            return;
        }
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (this.answered.contains(player.getUniqueId())) {
            return;
        }
        asyncPlayerChatEvent.setCancelled(true);
        this.answered.add(player.getUniqueId());
        PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, this.rewardGold);
        player.giveExp(this.rewardXP);
        ++this.top;
        for (UUID uuid : Main.getArena().getPlayers()) {
            Bukkit.getPlayer(uuid).sendMessage(Main.getPitEventManager().getQMAnswered(this.top, player));
        }
        if (this.top >= 5) {
            this.endEvent();
        }
    }
    
    static {
        instance = new QuickMaths();
    }
    
    public enum QuickMathsSymbols {
        ADD("+"), 
        SUBTRACT("-"), 
        MULTIPLY("*"), 
        DIVIDE("/");
        
        private final String symbol;
        
        QuickMathsSymbols(final String symbol) {
            this.symbol = symbol;
        }
        
        public String getSymbol() {
            return this.symbol;
        }
    }
}
