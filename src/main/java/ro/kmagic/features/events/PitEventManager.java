package ro.kmagic.features.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;
import ro.kmagic.Main;
import ro.kmagic.features.events.major.RagePit;
import ro.kmagic.features.events.major.TeamDeathmatch;
import ro.kmagic.features.events.major.TheBeast;
import ro.kmagic.features.events.minor.*;
import ro.kmagic.files.events.EventsRegion;
import ro.kmagic.files.events.MajorMinorEvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PitEventManager extends MajorMinorEvents {
    private final ArrayList<PitEventType> pitEventTypes;
    private PitEvent pitEvent;
    private PitEventType eventType;
    private String locationName;
    private String operation;
    private Location carePackageLocation;
    private Location kothLocation;
    private final int secondsBeforeStart;
    private int eventTime;
    private boolean started;
    private boolean isSearching;
    private boolean starting;
    private final List<String> eventsRegion;
    private final List<String> quickMathOperations;
    
    public PitEventManager() {
        this.pitEventTypes = new ArrayList<PitEventType>();
        this.secondsBeforeStart = this.getInt("Event.SecondsBeforeStart") * 20;
        this.eventTime = 0;
        this.started = false;
        this.isSearching = false;
        this.starting = false;
        this.eventsRegion = Main.getEventsRegion().getNodes();
        this.quickMathOperations = this.getStringList("Event.Minor.QUICK_MATHS.Operations");
        String bossBarMajorEnding = this.getString("Event.Major.Misc.Boss-Bar.Ending-In");
        String bossBarMinorEnding = this.getString("Event.Minor.Misc.Boss-Bar.Ending-In");
        if (Main.getEventsRegion().getNodes() == null) {
            return;
        }
        for (final PitEventType e : PitEventType.values()) {
            if (this.getMinorEventEnabled(e.name()) || this.getMajorEventEnabled(e.name())) {
                switch (e) {
                    case QUICK_MATHS: {
                        if (this.isOperationSet()) {
                            this.pitEventTypes.add(e);
                            break;
                        }
                        break;
                    }
                    case RAGE_PIT: {
                        if (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.RAGE_PIT_REGION)) {
                            this.pitEventTypes.add(e);
                            break;
                        }
                        break;
                    }
                    case KOTH: {
                        if (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.KOTH_REGION)) {
                            this.pitEventTypes.add(e);
                            break;
                        }
                        break;
                    }
                    case CARE_PACKAGE: {
                        if (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.CARE_PACKAGE_REGION)) {
                            this.pitEventTypes.add(e);
                            break;
                        }
                        break;
                    }
                    default: {
                        this.pitEventTypes.add(e);
                        break;
                    }
                }
            }
        }
        if (this.pitEventTypes.isEmpty()) {
            return;
        }
        this.startPitEvents(false);
    }
    
    public void startPitEvents(final boolean b) {
        if (Main.isDisabling()) {
            return;
        }
        int secondsBeforeStart = this.secondsBeforeStart;
        if (b) {
            secondsBeforeStart = 20;
        }
        this.isSearching = true;
        new BukkitRunnable() {
            public void run() {
                if (PitEventManager.this.eventType != null) {
                    API.print("&cA new ThePit event couldn't start because there is another event running.");
                    this.cancel();
                }
                else {
                    PitEventManager.this.eventType = PitEventManager.this.chooseRandomPitEvent();
                    if (PitEventManager.this.eventType != null) {
                        switch (PitEventManager.this.eventType) {
                            case X2_REWARD: {
                                PitEventManager.this.pitEvent = X2Reward.getInstance();
                                break;
                            }
                            case KOTH: {
                                PitEventManager.this.pitEvent = KingOfTheHill.getInstance();
                                break;
                            }
                            case EVERYONE_GETS_A_BOUNTY: {
                                PitEventManager.this.pitEvent = EveryoneGetsABounty.getInstance();
                                break;
                            }
                            case CARE_PACKAGE: {
                                PitEventManager.this.pitEvent = CarePackage.getInstance();
                                break;
                            }
                            case QUICK_MATHS: {
                                PitEventManager.this.pitEvent = QuickMaths.getInstance();
                                break;
                            }
                            case RAGE_PIT: {
                                PitEventManager.this.pitEvent = RagePit.getInstance();
                                break;
                            }
                            case TEAM_DEATHMATCH: {
                                PitEventManager.this.pitEvent = TeamDeathmatch.getInstance();
                                break;
                            }
                            case THE_BEAST: {
                                PitEventManager.this.pitEvent = TheBeast.getInstace();
                                break;
                            }
                        }
                        PitEventManager.this.isSearching = false;
                        PitEventManager.this.getPitEvent().preStartEvent();
                    }
                    else {
                        PitEventManager.this.startPitEvents(true);
                    }
                }
            }
        }.runTaskLater(API.getPlugin(), secondsBeforeStart);
    }
    
    public PitEventType chooseRandomPitEvent() {
        int i = 0;
        final HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        for (String s : this.eventsRegion) {
            hashMap.put(i, s);
            ++i;
        }
        int randomInt = Main.getArena().getRandomInt(i - 1);
        if (randomInt > 3 || randomInt < 0) {
            randomInt = 1;
        }
        this.locationName = hashMap.getOrDefault(randomInt, "");
        if (this.locationName.equals("")) {
            return null;
        }
        int n = 0;
        final HashMap<Integer, PitEventType> hashMap2 = new HashMap<Integer, PitEventType>();
        for (final PitEventType pitEventType : this.pitEventTypes) {
            if (pitEventType.equals(PitEventType.KOTH)) {
                if (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.KOTH_REGION, this.getLocationName())) {
                    final List<String> locations = Main.getEventsRegion().getLocations(this.locationName, "Koth");
                    final String[] split = locations.get(Main.getArena().getRandomInt(locations.size() - 1)).split(" ");
                    this.kothLocation = new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
                    hashMap2.put(n, pitEventType);
                }
            }
            else if (pitEventType.equals(PitEventType.CARE_PACKAGE)) {
                if (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.CARE_PACKAGE_REGION, this.getLocationName())) {
                    final List<String> locations2 = Main.getEventsRegion().getLocations(this.locationName, "CarePackage");
                    final String[] split2 = locations2.get(Main.getArena().getRandomInt(locations2.size() - 1)).split(" ");
                    this.carePackageLocation = new Location(Bukkit.getWorld(split2[0]), Double.parseDouble(split2[1]), Double.parseDouble(split2[2]), Double.parseDouble(split2[3]));
                    hashMap2.put(n, pitEventType);
                }
            }
            else if (pitEventType.equals(PitEventType.QUICK_MATHS)) {
                this.operation = this.quickMathOperations.get(Main.getArena().getRandomInt(this.quickMathOperations.size() - 1));
                hashMap2.put(n, pitEventType);
            }
            else {
                hashMap2.put(n, pitEventType);
            }
            ++n;
        }
        return hashMap2.getOrDefault(Main.getArena().getRandomInt(n - 1), null);
    }
    
    public void setPitEvent(final PitEvent pitEvent) {
        this.pitEvent = pitEvent;
    }
    
    public PitEvent getPitEvent() {
        return this.pitEvent;
    }
    
    public PitEventType getEventType() {
        return this.eventType;
    }
    
    public void setEventType(final PitEventType eventType) {
        this.eventType = eventType;
    }
    
    public String getLocationName() {
        return this.locationName;
    }
    
    public String getLocationCustomName() {
        if (this.getLocationName() == null || this.getLocationName().trim().isEmpty()) {
            return "";
        }
        return Main.getEventsRegion().getName(this.getLocationName());
    }
    
    public String getOperation() {
        return this.operation;
    }
    
    public Location getRandomLocation(final PitEventType pitEventType) {
        switch (pitEventType) {
            case KOTH: {
                return this.kothLocation;
            }
            case CARE_PACKAGE: {
                return this.carePackageLocation;
            }
            default: {
                return new Location(Main.getSpawn().getLocation("Spawn").getWorld(), 0.0, 0.0, 0.0);
            }
        }
    }
    
    public int getSecondsBeforeStart() {
        return this.secondsBeforeStart;
    }
    
    public void setEventTime(final int eventTime) {
        this.eventTime = eventTime;
    }
    
    public int getEventTime() {
        return this.eventTime;
    }
    
    public String getTimeFormatted() {
        return String.format("%02d:%02d", this.eventTime / 60, this.eventTime % 60);
    }
    
    public void setStarted(final boolean started) {
        this.started = started;
    }
    
    public void assignTeamForPlayer(final Player player) {
        this.pitEvent.addTeam(player);
    }
    
    public void unAssignTeamForPlayer(final Player player) {
        this.pitEvent.removeTeam(player);
    }
    
    public boolean isStarted() {
        return this.started;
    }
    
    public boolean isSearching() {
        return this.isSearching;
    }
    
    public boolean isStarting() {
        return this.starting;
    }
    
    public void setStarting(final boolean starting) {
        this.starting = starting;
    }
    
    public void giveHead(final Player player) {
        this.pitEvent.giveHead(player);
    }
    
    public boolean isBeast(final Player player) {
        return this.isStarted() && this.getEventType().equals(PitEventType.THE_BEAST) && this.pitEvent.isBeast(player);
    }
    
    public void selectRandomBeast() {
        if (!this.isStarted() || !this.getEventType().equals(PitEventType.THE_BEAST)) {
            return;
        }
        this.pitEvent.selectRandomBeast();
    }
    
    public int getRagePitDamageDealt(final Player player) {
        return this.pitEvent.getRagePitDamageDealt(player);
    }
    
    public void addRagePitKill() {
        this.pitEvent.addRagePitKill();
    }
    
    public int getRagePitKills() {
        return this.pitEvent.getRagePitKills();
    }
    
    public int getTeamDeathmatchKAndA(final Player player) {
        return this.pitEvent.getTeamDeathmatchKAndA(player);
    }
    
    public int getTeamDeathmatchBlueKills() {
        return this.pitEvent.getTeamDeathmatchBlueKills();
    }
    
    public int getTeamDeathmatchRedKills() {
        return this.pitEvent.getTeamDeathmatchRedKills();
    }
    
    public int getTheBeastKills(final Player player) {
        return this.pitEvent.getTheBeastKills(player);
    }
    
    public int getTheBeastMaxLivingTime() {
        return this.pitEvent.getTheBeastMaxLivingTime();
    }
    
    public String getTeamColorCode(final Player player) {
        if (this.pitEvent == null) {
            return null;
        }
        return this.pitEvent.getTeamColorCode(player);
    }
    
    public void quitPlayer(final Player player) {
        if (this.pitEvent == null) {
            return;
        }
        this.pitEvent.playerQuit(player);
    }
    
    public enum PitEventType {
        X2_REWARD(false), 
        KOTH(false), 
        EVERYONE_GETS_A_BOUNTY(false), 
        CARE_PACKAGE(false), 
        QUICK_MATHS(false), 
        RAGE_PIT(true), 
        TEAM_DEATHMATCH(true), 
        THE_BEAST(true);
        
        private final boolean isMajor;
        
        PitEventType(final boolean isMajor) {
            this.isMajor = isMajor;
        }
        
        public boolean isMajor() {
            return this.isMajor;
        }
    }
}
