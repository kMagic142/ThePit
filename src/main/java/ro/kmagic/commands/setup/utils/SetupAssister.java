package ro.kmagic.commands.setup.utils;

import org.bukkit.ChatColor;
import ro.kmagic.files.events.EventsRegion;
import ro.kmagic.files.map.Region;
import ro.kmagic.files.map.Spawn;
import ro.kmagic.features.npcs.NPC;
import ro.kmagic.Main;
import org.bukkit.event.EventHandler;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.api.events.misc.SetupSetEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class SetupAssister implements Listener {
    private boolean isDone;
    private SetupUtils.SetupType step;
    private final HashMap<SetupUtils.SetupType, String> scoreboardStatus;
    private final String set = "&a\u2714 &f";
    private final String notSet = "&c\u2716 &f";
    private final String clickHere = " &e&l[CLICK HERE]";
    private final List<SetupUtils.SetupType> helperExceptions;
    
    public SetupAssister() {
        this.isDone = false;
        this.step = null;
        this.scoreboardStatus = new HashMap<SetupUtils.SetupType, String>();
        this.helperExceptions = new ArrayList<SetupUtils.SetupType>(Arrays.asList(SetupUtils.SetupType.NPC_STATS, SetupUtils.SetupType.NPC_NON_PERMANENT_ITEMS, SetupUtils.SetupType.NPC_PERMANENT_UPGRADES, SetupUtils.SetupType.NPC_THE_KEEPER, SetupUtils.SetupType.NPC_PRESTIGE, SetupUtils.SetupType.NPC_QUEST_MASTER, SetupUtils.SetupType.EVENTS_LOCATIONS, SetupUtils.SetupType.EVENTS_HOLOGRAMS));
        API.registerEvent(this);
        new BukkitRunnable() {
            public void run() {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("thepit.setup") || player.isOp()) {
                        SetupAssister.this.sendHelper(player);
                    }
                }
            }
        }.runTaskTimer(API.getPlugin(), 20L, 20L);
    }
    
    @EventHandler
    private void onSetupSet(final SetupSetEvent setupSetEvent) {
        Sounds.PLAYER_LEVELUP.getSound().play(setupSetEvent.getPlayer(), 1.0f, 3.0f);
        for (final SetupUtils.SetupType step : SetupUtils.SetupType.values()) {
            if (this.getStatus(step, "", false).contains("\u2716")) {
                this.step = step;
                break;
            }
        }
        this.updateHelper();
    }
    
    public void updateHelper() {
        for (final SetupUtils.SetupType key : SetupUtils.SetupType.values()) {
            if (key.isScoreboard()) {
                this.scoreboardStatus.put(key, this.getStatus(key, key.getName(), false));
            }
        }
    }
    
    public void sendHelper(final Player player) {
        if (!this.isDone) {
            for (final SetupUtils.SetupType step : SetupUtils.SetupType.values()) {
                if (!this.helperExceptions.contains(step) && this.getStatus(step, "", false).contains("\u2716")) {
                    this.step = step;
                    break;
                }
            }
        }
        if (this.step == null) {
            this.isDone = true;
        }
        if (this.isDone) {
            API.getVersionSupport().sendActionBar(player, "&e&lThePit Setup is done! &a&lUse: &f&l/ThePit disableSetup");
            return;
        }
        if (!this.getStatus(SetupUtils.SetupType.LOBBY, "", false).contains("\u2714")) {
            API.getVersionSupport().sendActionBar(player, "&c&lSet the arena Lobby! &a&lUse: &f&l/ThePit SetLobby &7(&cMUST SET IN LOBBY/HUB world&7)");
            return;
        }
        if (!Main.getWorldManager().isOnMap(player)) {
            API.getVersionSupport().sendActionBar(player, "&c&lYou must go in ThePit world! &a&lUse: &f&l/ThePit SetMap");
            return;
        }
        String s = "";
        switch (this.step) {
            case SPAWN: {
                s = "&e&lSet pit Spawn! &a&lUse: &f&l/ThePit SetSpawn";
                break;
            }
            case LOBBY: {
                s = "&e&lSet sever Lobby! &a&lUse: &f&l/ThePit SetLobby &4(&cMUST SET IN LOBBY/HUB world&4)";
                break;
            }
            case SPAWN_REGION: {
                s = "&e&lSet pit spawn's region! &a&lUse: &f&l/ThePit SetSpawnRegion";
                break;
            }
            case PIT_HOLE: {
                s = "&e&lSet pit hole! &a&lUse: &f&l/ThePit SetPitHole Wand";
                break;
            }
            case NPCS: {
                s = "&e&lSet pit NPCs! &a&lUse: &f&l/ThePit SetNPC";
                break;
            }
            case RANDOM_GOLD: {
                s = "&e&lSet pit random gold! &a&lUse: &f&l/ThePit SetRandomGold";
                break;
            }
            case ENDERCHEST: {
                s = "&8&lOptional: &e&lSet pit ender chests! &a&lUse: &f&l/ThePit SetEnderchest";
                break;
            }
            case EVENTS_LOCATIONS_HOLOGRAMS: {
                s = "&8&lOptional: &e&lSet pit event locations and holograms! &a&lUse: &f&l/ThePit SetEvent";
                break;
            }
            case KOTH: {
                s = "&8&lOptional: &e&lSet pit koth locations! &a&lUse: &f&l/ThePit SetEvent Koth";
                break;
            }
            case CARE_PACKAGE: {
                s = "&8&lOptional: &e&lSet pit care package locations! &a&lUse: &f&l/ThePit SetEvent CarePackage";
                break;
            }
            case RAGE_PIT: {
                s = "&8&lOptional: &e&lSet pit rage pit blocks! &a&lUse: &f&l/ThePit SetEvent RagePit";
                break;
            }
            case PIT_HOLE_HOLOGRAM: {
                s = "&8&lOptional: &e&lSet pit pit hole hologram! &a&lUse: &f&l/ThePit SetPitHole Hologram";
                break;
            }
            case UNLOCKED_FEATURES_HOLOGRAM: {
                s = "&8&lOptional: &e&lSet pit unlocked features hologram! &a&lUse: &f&l/ThePit SetUnlockedFeaturesHologram";
                break;
            }
        }
        API.getVersionSupport().sendActionBar(player, s);
    }
    
    public String getStatus(final SetupUtils.SetupType obj, final String s, final boolean b) {
        String s2 = "&c\u2716 &f" + s + (b ? " &e&l[CLICK HERE]" : "");
        int n = 1;
        switch (obj) {
            case NPCS: {
                final NPC.NPCType[] values = NPC.NPCType.values();
                for (int length = values.length, i = 0; i < length; ++i) {
                    if (!Main.getNpc().isSet(values[i])) {
                        n = 0;
                        break;
                    }
                }
                break;
            }
            case NPC_STATS: {
                n = (Main.getNpc().isSet(NPC.NPCType.Stats) ? 1 : 0);
                break;
            }
            case NPC_NON_PERMANENT_ITEMS: {
                n = (Main.getNpc().isSet(NPC.NPCType.NonPermanentItems) ? 1 : 0);
                break;
            }
            case NPC_PERMANENT_UPGRADES: {
                n = (Main.getNpc().isSet(NPC.NPCType.PermanentUpgrades) ? 1 : 0);
                break;
            }
            case NPC_THE_KEEPER: {
                n = (Main.getNpc().isSet(NPC.NPCType.TheKeeper) ? 1 : 0);
                break;
            }
            case NPC_PRESTIGE: {
                n = (Main.getNpc().isSet(NPC.NPCType.Prestige) ? 1 : 0);
                break;
            }
            case NPC_QUEST_MASTER: {
                n = (Main.getNpc().isSet(NPC.NPCType.QuestMaster) ? 1 : 0);
                break;
            }
            case SPAWN: {
                n = (Main.getSpawn().isSet(Spawn.SpawnType.Spawn) ? 1 : 0);
                break;
            }
            case LOBBY: {
                n = (Main.getSpawn().isSet(Spawn.SpawnType.Lobby) ? 1 : 0);
                break;
            }
            case SPAWN_REGION: {
                n = (Main.getRegionManager().isSet(Region.RegionType.SPAWN_REGION) ? 1 : 0);
                break;
            }
            case PIT_HOLE: {
                n = (Main.getRegionManager().isSet(Region.RegionType.PIT_HOLE_REGION) ? 1 : 0);
                break;
            }
            case RANDOM_GOLD: {
                n = (Main.getRandomGold().isSet() ? 1 : 0);
                break;
            }
            case ENDERCHEST: {
                n = (Main.getEnderchest().isSet() ? 1 : 0);
                break;
            }
            case EVENTS_LOCATIONS: {
                n = (Main.getEventsRegion().isSet() ? 1 : 0);
                break;
            }
            case EVENTS_HOLOGRAMS: {
                n = (Main.getEventsHologram().isSet() ? 1 : 0);
                break;
            }
            case EVENTS_LOCATIONS_HOLOGRAMS: {
                n = ((Main.getEventsRegion().isSet() && Main.getEventsHologram().isSet()) ? 1 : 0);
                break;
            }
            case KOTH:
            case CARE_PACKAGE: {
                n = (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.valueOf(obj + "_REGION")) ? 1 : 0);
                break;
            }
            case PIT_HOLE_HOLOGRAM: {
                n = (Main.getHologram().isPitHoleHologramSet() ? 1 : 0);
                break;
            }
            case UNLOCKED_FEATURES_HOLOGRAM: {
                n = (Main.getHologram().isUnlockedFeaturesSet() ? 1 : 0);
                break;
            }
            case RAGE_PIT: {
                n = (Main.getEventsRegion().isRagePitSet() ? 1 : 0);
                break;
            }
        }
        if (n != 0) {
            s2 = "&a\u2714 &f" + s;
        }
        return ChatColor.translateAlternateColorCodes('&', s2);
    }
    
    public String getNPCStatus(final NPC.NPCType npcType) {
        String s = "&c\u2716 &f" + npcType.getName() + " &e&l[CLICK HERE]";
        if (Main.getNpc().isSet(npcType)) {
            s = "&a\u2714 &f" + npcType.getName();
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public String getSpawnRegionStatus(final int n, final String s) {
        String s2 = "&c\u2716 &f" + s + " &e&l[CLICK HERE]";
        if (Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, n) != null) {
            s2 = "&a\u2714 &f" + s;
        }
        return ChatColor.translateAlternateColorCodes('&', s2);
    }
    
    public String getEventLocationStatus(final int n, final String s) {
        String s2 = "&c\u2716 &f" + s + " &e&l[CLICK HERE]";
        if (Main.getEventsRegion().getNodes() != null) {
            for (String value : Main.getEventsRegion().getNodes()) {
                if (Main.getEventsRegion().getPos(value, n) != null) {
                    s2 = "&a\u2714 &f" + s;
                    break;
                }
            }
        }
        return ChatColor.translateAlternateColorCodes('&', s2);
    }
    
    public String getRagePitStatus(final String s) {
        String s2 = "&c\u2716 &f" + s + " &e&l[CLICK HERE]";
        if (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.RAGE_PIT_REGION)) {
            s2 = "&a\u2714 &f" + s;
        }
        return ChatColor.translateAlternateColorCodes('&', s2);
    }
}
