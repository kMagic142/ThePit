package ro.kmagic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.database.Database;
import ro.kmagic.libapi.database.MySQL;
import ro.kmagic.libapi.placeholder.PlaceholderManager;
import ro.kmagic.api.ThePitAPI;
import ro.kmagic.commands.*;
import ro.kmagic.commands.arena.BuildCommand;
import ro.kmagic.commands.arena.EnableSetupCommand;
import ro.kmagic.commands.arena.JoinCommand;
import ro.kmagic.commands.arena.LeaveCommand;
import ro.kmagic.commands.player.GoldCommand;
import ro.kmagic.commands.setup.*;
import ro.kmagic.commands.setup.utils.SetupAssister;
import ro.kmagic.features.*;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.features.npcs.NPC;
import ro.kmagic.features.npcs.NPCsInteract;
import ro.kmagic.features.npcs.nonpermanentitems.NonPermanentItemsGUI;
import ro.kmagic.features.npcs.permanentupgrades.PerkGui;
import ro.kmagic.features.npcs.permanentupgrades.PerkSlotGui;
import ro.kmagic.features.npcs.prestige.PrestigeGui;
import ro.kmagic.features.npcs.stats.StatsGUI;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PerkEvents;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.features.stats.StatsEvents;
import ro.kmagic.features.stats.StatsUpdater;
import ro.kmagic.features.sync.Sync;
import ro.kmagic.files.RankColors;
import ro.kmagic.files.Settings;
import ro.kmagic.files.events.EventsHologram;
import ro.kmagic.files.events.EventsRegion;
import ro.kmagic.files.gui.ChatOptionGui;
import ro.kmagic.files.map.*;
import ro.kmagic.files.messages.Messages;
import ro.kmagic.files.npcs.permanentupgrades.PermanentUpgrades;
import ro.kmagic.files.npcs.prestige.Prestige;
import ro.kmagic.files.perk.PerkDescription;
import ro.kmagic.managers.*;
import ro.kmagic.support.MySQLConverter;
import ro.kmagic.support.legacy.SQLite;
import ro.kmagic.support.legacy.SQLiteConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Main extends JavaPlugin {
    private static Settings settings;
    private static Messages messages;
    private static WorldManager worldManager;
    private static Spawn spawn;
    private static NPC npc;
    private static Arena arena;
    private static RegionManager regionManager;
    private static RandomGold randomGold;
    private static Enderchest enderchest;
    private static EventsRegion eventsRegion;
    private static EventsHologram eventsHologram;
    private static Hologram hologram;
    private static SetupAssister setupAssister;
    private static XPTag xpTag;
    private static Bounty bounty;
    private static PlayerTag playerTag;
    private static StreakManager streakManager;
    private static PitEventManager pitEventManager;
    private static MapRecoverManager mapRecoverManager;
    private static NonPermanentItemsGUI nonPermanentItemsGUI;
    private static PerkDescription perkDescription;
    private static PermanentUpgrades permanentUpgrades;
    private static Perk perk;
    private static PerkSlotGui perkSlotGui;
    private static PerkGui perkGui;
    private static PerkEvents perkEvents;
    private static Stats stats;
    private static StatsGUI statsGUI;
    private static CombatManager combatManager;
    private static SQLiteConverter sqLiteConverter;
    private static MySQLConverter mySQLConverter;
    private static Sync sync;
    private static PlayerManager playerManager;
    private static EnderchestManager enderchestManager;
    private static ThePitAPI thePitAPI;
    private static ChatOptionGui chatOptionGui;
    private static ChatOption chatOption;
    private static GlobalManager globalManager;
    private static Prestige prestige;
    private static PrestigeGui prestigeGui;
    private static RankColors rankColors;
    private static PlayerRankColor playerRankColor;
    private static boolean fullStart;
    private static boolean isDisabling;
    private static boolean setupMode;
    private static boolean mySQLConverterEnabled;
    private static boolean syncEnabled;
    
    public void onEnable() {
        new API(this).initDB();
        Main.settings = new Settings();
        System.out.println(getSettings().getString("Plugin.SetupMode"));
        Main.messages = new Messages();
        Main.setupMode = getSettings().getBoolean("Plugin.SetupMode");
        Main.spawn = new Spawn();
        Main.worldManager = new WorldManager();
        Main.npc = new NPC();
        Main.hologram = new Hologram();
        Main.arena = new Arena();
        Main.regionManager = new RegionManager();
        Main.randomGold = new RandomGold();
        Main.enderchest = new Enderchest();
        Main.eventsRegion = new EventsRegion();
        Main.eventsHologram = new EventsHologram();
        Main.mySQLConverterEnabled = getSettings().getBoolean("Plugin.MySQLConvert");
        this.printConsole("&f&m--------------------------");
        this.printConsole("   &lThePit  " + this.getDescription().getVersion());
        this.printConsole(" ");
        this.printConsole("&lLoading ThePit...");
        this.printConsole(" ");
        this.printConsole("&lRunning on: " + API.getVersion());
        this.printConsole(" ");
        this.printConsole("&lSetup Mode: " + Main.setupMode);
        this.printConsole(" ");
        final ThePitCommand thePitCommand = new ThePitCommand();
        if (Main.setupMode) {
            this.printConsole("&lLoading setup...");
            this.printConsole(" ");

            getWorldManager().loadWorld();
            thePitCommand.addSubCommand(new SetupProgressCommand(), "&7&oShows your setup progression.");
            thePitCommand.addSubCommand(new SetMapCommand(), "&7&oTeleport to setup your thepit map.");
            thePitCommand.addSubCommand(new SetSpawnCommand(), "&7&oSets thepit's spawn point.");
            thePitCommand.addSubCommand(new SetLobbyCommand(), "&7&oSets thepit's lobby spawn point.");
            thePitCommand.addSubCommand(new SetSpawnRegionCommand(), "&7&oSets thepit's spawn region.");
            thePitCommand.addSubCommand(new SetPitHoleCommand(), "&7&oSets thepit's pit hole.");
            thePitCommand.addSubCommand(new SetUnlockedFeaturesHologram(), "&7&oSets thepit's unlocked features hologram.");
            thePitCommand.addSubCommand(new SetNpcCommand(), "&7&oSets thepit's npcs.");
            thePitCommand.addSubCommand(new SetRandomGoldCommand(), "&7&oSets thepit's arena random gold spawn points.");
            thePitCommand.addSubCommand(new SetEnderChestCommand(), "&7&oSets thepit's spawn enderchests.");
            thePitCommand.addSubCommand(new SetEventCommand(), "&7&oSets thepit's arena events.");
            thePitCommand.addSubCommand(new SetupGui(), "&7&oHelps beginners set thepit with explanations.");
            thePitCommand.addSubCommand(new DisableSetupCommand(), "&7&oToggles thepit's setup mode.");
            Main.setupAssister = new SetupAssister();
            API.print("");
            this.printConsole("&lSetup Loaded!");
            this.printConsole("&lBegin setup by joining the server and run /thepit");
            Main.fullStart = true;
        }
        else {
            new PlaceholderManager();
            Main.rankColors = new RankColors();
            Main.playerRankColor = new PlayerRankColor();
            new Placeholders();
            getWorldManager().loadWorld();
            new ro.kmagic.storage.Database();
            Main.globalManager = new GlobalManager();
            Main.xpTag = new XPTag();
            Main.streakManager = new StreakManager();
            Main.bounty = new Bounty();
            Main.mapRecoverManager = new MapRecoverManager();
            Main.pitEventManager = new PitEventManager();
            Main.playerTag = new PlayerTag();
            Main.nonPermanentItemsGUI = new NonPermanentItemsGUI();
            Main.perkDescription = new PerkDescription();
            Main.permanentUpgrades = new PermanentUpgrades();
            Main.perk = new Perk();
            Main.perkEvents = new PerkEvents();
            Main.perkSlotGui = new PerkSlotGui();
            Main.perkGui = new PerkGui();
            Main.stats = new Stats();
            new StatsUpdater();
            Main.statsGUI = new StatsGUI();
            Main.combatManager = new CombatManager();
            Main.playerManager = new PlayerManager();
            Main.sqLiteConverter = new SQLiteConverter();
            Main.chatOptionGui = new ChatOptionGui();
            Main.chatOption = new ChatOption();
            API.getVersionSupport().registerCommand(new PitChatCommand());
            Main.prestige = new Prestige();
            Main.prestigeGui = new PrestigeGui();
            API.getVersionSupport().registerCommand(new TradeCommand());
            API.getVersionSupport().registerCommand(new ViewCommand());
            if (isMySQLConverterEnabled()) {
                Main.mySQLConverter = new MySQLConverter();
            }
            Main.syncEnabled = getSettings().getBoolean("Plugin.Database.PlayerSync");
            API.getVersionSupport().registerCommand(new GoldCommand());
            thePitCommand.addSubCommand(new JoinCommand(), "&7&oJoin thepit's arena.");
            thePitCommand.addSubCommand(new LeaveCommand(), "&7&oLeave thepit's arena.");
            thePitCommand.addSubCommand(new LevelCommand(), "&7&oEdit player's level.");
            thePitCommand.addSubCommand(new PrestigeCommand(), "&7&oEdit player's prestige.");
            thePitCommand.addSubCommand(new GoldEconomyCommand(), "&7&oEdit player's balance.");
            thePitCommand.addSubCommand(new RenownEconomyCommand(), "&7&oEdit player's renown balance.");
            thePitCommand.addSubCommand(new BountyCommand(), "&7&oEdit player's bounty.");
            thePitCommand.addSubCommand(new BuildCommand(), "&7&oEnable self-building mode.");
            thePitCommand.addSubCommand(new ResetCommand(), "&7&oResets player's account");
            thePitCommand.addSubCommand(new EnableSetupCommand(), "&7&oToggles thepit's setup mode.");
            Main.thePitAPI = new ThePitAPI();
            new StatsEvents();
            new EntityManager();
            new RandomGoldManager();
            Main.enderchestManager = new EnderchestManager();
            new HologramManager();
            new BlockManager();
            new SoundManager();
            new NPCsInteract();
            if (Main.syncEnabled && Database.getMySQLCredentials().isEnabled()) {
                Main.sync = new Sync();
                getSync().run();
            }
            API.print(" ");
            if (PlaceholderManager.isPapiEnabled()) {
                API.print("&lPlaceholderAPI found, placeholders hooked!");
            } else {
                API.print("&lPlaceholderAPI not found!");
            }

            Main.fullStart = true;
            API.print("&lThePit loaded successfully!");
        }
        API.getVersionSupport().registerCommand(thePitCommand);
        API.print("&f&m--------------------------");
    }
    
    public void onDisable() {
        Main.isDisabling = true;
        API.print("&f&m--------------------------");
        API.print("   &lThePit " + this.getDescription().getVersion());
        API.print(" ");
        API.print("&lDisabling ThePit...");
        if (Main.fullStart) {

            for (final UUID uuid : new ArrayList<>(getArena().getPlayers())) {
                getArena().leaveArena(Bukkit.getPlayer(uuid));
                API.getVersionSupport().sendTablist(Bukkit.getPlayer(uuid), Collections.singletonList(""), Collections.singletonList(""));
            }

            if (getSettings().getBoolean("Bounty.Aura")) {
                API.getVersionSupport().removeAllPlayerAura();
            }
            API.getVersionSupport().removeALlHolograms();
            if (!isSetupMode()) {
                getCombatManager().clearCombatList();
            }
        }
        API.print(" ");
        if (Main.fullStart) {
            getWorldManager().unloadWorld();
            if (!isSetupMode()) {
                API.getDatabase().close();
            }
            if (!isSetupMode() && isMySQLConverterEnabled()) {
                getConverterDatabase().close();
            }
        }
        API.print("&lThePit unloaded successfully!");
        API.print("&f&m--------------------------");
    }
    
    public static Settings getSettings() {
        return Main.settings;
    }
    
    public static Messages getMessages() {
        return Main.messages;
    }
    
    public static WorldManager getWorldManager() {
        return Main.worldManager;
    }
    
    public static Spawn getSpawn() {
        return Main.spawn;
    }
    
    public static NPC getNpc() {
        return Main.npc;
    }
    
    public static boolean isSetupMode() {
        return Main.setupMode;
    }
    
    public static Arena getArena() {
        return Main.arena;
    }
    
    public static RegionManager getRegionManager() {
        return Main.regionManager;
    }

    
    public static RandomGold getRandomGold() {
        return Main.randomGold;
    }
    
    public static Enderchest getEnderchest() {
        return Main.enderchest;
    }
    
    public static EventsRegion getEventsRegion() {
        return Main.eventsRegion;
    }
    
    public static EventsHologram getEventsHologram() {
        return Main.eventsHologram;
    }
    
    public static Hologram getHologram() {
        return Main.hologram;
    }
    
    public static SetupAssister getSetupAssister() {
        return Main.setupAssister;
    }
    
    public static XPTag getXpTag() {
        return Main.xpTag;
    }
    
    public static Bounty getBounty() {
        return Main.bounty;
    }
    
    public static PlayerTag getPlayerTag() {
        return Main.playerTag;
    }
    
    public static StreakManager getStreakManager() {
        return Main.streakManager;
    }
    
    public static PitEventManager getPitEventManager() {
        return Main.pitEventManager;
    }
    
    public static MapRecoverManager getMapRecoverManager() {
        return Main.mapRecoverManager;
    }
    
    public static NonPermanentItemsGUI getNonPermanentItemsGUI() {
        return Main.nonPermanentItemsGUI;
    }
    
    public static PerkDescription getPerkDescription() {
        return Main.perkDescription;
    }
    
    public static PermanentUpgrades getPermanentUpgrades() {
        return Main.permanentUpgrades;
    }
    
    public static Perk getPerk() {
        return Main.perk;
    }
    
    public static PerkSlotGui getPerkSlotGui() {
        return Main.perkSlotGui;
    }
    
    public static PerkGui getPerkGui() {
        return Main.perkGui;
    }
    
    public static PerkEvents getPerkEvents() {
        return Main.perkEvents;
    }
    
    public static Stats getStats() {
        return Main.stats;
    }
    
    public static StatsGUI getStatsGUI() {
        return Main.statsGUI;
    }
    
    public static CombatManager getCombatManager() {
        return Main.combatManager;
    }
    
    public static SQLite getLegacyDatabase() {
        return SQLite.getInstance();
    }
    
    public static SQLiteConverter getSqLiteConverter() {
        return Main.sqLiteConverter;
    }
    
    public static boolean isMySQLConverterEnabled() {
        return Main.mySQLConverterEnabled;
    }
    
    public static MySQL getConverterDatabase() {
        return MySQL.getInstance();
    }
    
    public static MySQLConverter getMySQLConverter() {
        return Main.mySQLConverter;
    }
    
    public static Sync getSync() {
        return Main.sync;
    }
    
    public static boolean isSyncEnabled() {
        return Main.syncEnabled;
    }
    
    public static PlayerManager getPlayerManager() {
        return Main.playerManager;
    }
    
    public static EnderchestManager getEnderchestManager() {
        return Main.enderchestManager;
    }
    
    public static ThePitAPI getThePitAPI() {
        return Main.thePitAPI;
    }
    
    public static ChatOptionGui getChatOptionGui() {
        return Main.chatOptionGui;
    }
    
    public static ChatOption getChatOption() {
        return Main.chatOption;
    }
    
    public static GlobalManager getGlobalManager() {
        return Main.globalManager;
    }
    
    public static Prestige getPrestige() {
        return Main.prestige;
    }
    
    public static PrestigeGui getPrestigeGui() {
        return Main.prestigeGui;
    }
    
    public static RankColors getRankColors() {
        return Main.rankColors;
    }
    
    public static PlayerRankColor getPlayerRankColor() {
        return Main.playerRankColor;
    }
    
    public static boolean isDisabling() {
        return Main.isDisabling;
    }
    
    private void printConsole(final String s) {
        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }
    
    static {
        Main.fullStart = false;
        Main.isDisabling = false;
        Main.syncEnabled = false;
    }
}
