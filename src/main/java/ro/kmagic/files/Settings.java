package ro.kmagic.files;

import java.util.Arrays;

import ro.kmagic.libapi.utils.FileManager;

public class Settings extends FileManager {
    private static final String joinLeaveMessages = "JoinLeaveMessages.";
    private static final String economy = "Economy.";
    private static final String combatLog = "CombatLog.";
    private static final String world = "World.";
    private static final String temporaryBlocks = "TemporaryBlocks.";
    private static final String chatFormat = "ChatFormat.";
    private static final String player = "Player.";
    private static final String assist = "Assist.";
    private static final String mysql = "MySQL.";
    public static final String pluginLobbyServer = "Plugin.LobbyServer";
    public static final String pluginWorldName = "Plugin.WorldName";
    public static final String pluginLanguage = "Plugin.Language";
    public static final String pluginPluginType = "Plugin.PluginType";
    public static final String pluginAllowedCommandInWorld = "Plugin.Allowed-Command-In-World";
    public static final String pluginSetupMode = "Plugin.SetupMode";
    public static final String pluginDatabasePlayerSync = "Plugin.Database.PlayerSync";
    public static final String pluginMySQLConvert = "Plugin.MySQLConvert";
    public static final String joinLeaveMessagesEnabled = "JoinLeaveMessages.Enabled";
    public static final String joinLeaveMessagesJoin = "JoinLeaveMessages.Join";
    public static final String joinLeaveMessagesQuit = "JoinLeaveMessages.Quit";
    public static final String economyDefaultBalance = "Economy.DefaultBalance";
    public static final String economyGoldPickup = "Economy.GoldPickup";
    public static final String combatLogTime = "CombatLog.Time";
    public static final String combatLocBannedCommands = "CombatLog.BannedCommands";
    public static final String worldVoid = "World.Void";
    public static final String spawnTeleportOnBarrier = "Spawn.Teleport-On-Barrier";
    public static final String temporaryBlocksCobblestone = "TemporaryBlocks.Cobblestone";
    public static final String temporaryBlocksObsidian = "TemporaryBlocks.Obsidian";
    public static final String temporaryBlocksLiquid = "TemporaryBlocks.Liquid";
    public static final String temporaryBlocksForceRemoveCheck = "TemporaryBlocks.ForceRemoveCheck";
    public static final String playerPingFix = "Player.PingFix";
    public static final String bountyAura = "Bounty.Aura";
    public static final String assistGold = "Assist.GoldIncrease";
    public static final String assistXP = "Assist.XPIncrease";
    public static final String goldenHeadsTexture = "GoldenHeadsTexture";
    public static final String goldenAppleStackLimit = "Golden-Apple.Stack-Limit";
    public static final String mysqlEnabled = "MySQL.Enabled";
    public static final String mysqlHost = "MySQL.Host";
    public static final String mysqlPort = "MySQL.Port";
    public static final String mysqlDatabase = "MySQL.Database";
    public static final String mysqlSSL = "MySQL.SSL";
    public static final String mysqlUsername = "MySQL.Username";
    public static final String mysqlPassword = "MySQL.Password";
    public static final String mysqlPlayerSync = "MySQL.PlayerSync";
    
    public Settings() {
        super("Settings");
        this.addDefault("Plugin.LobbyServer", "lobby");
        this.addDefault("Plugin.WorldName", "ThePitWorld");
        this.addDefault("Plugin.Language", "EN");
        this.addDefault("Plugin.PluginType", "BUNGEE");
        this.addDefault("Plugin.Allowed-Command-In-World", Arrays.asList("world", "world_nether", "ThePitWorld"));
        this.addDefault("Plugin.SetupMode", true);
        this.addDefault("Plugin.MySQLConvert", false);
        this.addDefault("Plugin.Database.PlayerSync", false);
        this.addDefault("JoinLeaveMessages.Enabled", false);
        this.addDefault("JoinLeaveMessages.Join", "&7Player %player% joined!");
        this.addDefault("JoinLeaveMessages.Quit", "&7Player %player% left!");
        this.addDefault("Economy.DefaultBalance", 50);
        this.addDefault("Economy.GoldPickup", 2);
        this.addDefault("CombatLog.Time", 25);
        this.addDefault("CombatLog.BannedCommands", Arrays.asList("spawn", "tp", "respawn"));
        this.addDefault("World.Void", 0);
        this.addDefault("Spawn.Teleport-On-Barrier", true);
        this.addDefault("TemporaryBlocks.Cobblestone", 40);
        this.addDefault("TemporaryBlocks.Obsidian", 120);
        this.addDefault("TemporaryBlocks.Liquid", 20);
        this.addDefault("TemporaryBlocks.ForceRemoveCheck", false);
        this.addDefault("Golden-Apple.Stack-Limit", 2);
        this.addDefault("Player.PingFix", true);
        this.addDefault("Bounty.Aura", true);
        this.addDefault("Assist.GoldIncrease", 6);
        this.addDefault("Assist.XPIncrease", 4);
        this.addDefault("GoldenHeadsTexture", "http://textures.minecraft.net/texture/452dca68c8f8af533fb737faeeacbe717b968767fc18824dc2d37ac789fc77");
        this.addDefault("MySQL.PlayerSync", false);
        this.copyDefaults();
        this.save();
    }
}
