package ro.kmagic.files.messages;

import ro.kmagic.libapi.utils.FileManager;
import ro.kmagic.Main;
import ro.kmagic.files.messages.languages.*;

public class Messages extends FileManager {

    private static final String plugin = "Plugin.";
    private static final String economy = "Economy.";
    private static final String death = "Death.";
    private static final String xp = "XP.";
    private static final String respawn = "Respawn.";
    private static final String reset = "Reset.";
    private static final String bounty = "Bounty.";
    private static final String combatLog = "CombatLog.";
    private static final String killStreak = "KillStreak.";
    public static final String pluginNotAvailableInConsole = "Plugin.NotAvailableInConsole";
    public static final String pluginNoPermission = "Plugin.NoPermission";
    public static final String pluginCommandNotAvailableInWorld = "Plugin.Command-Not-Available-In-World";
    public static final String pluginBuildOn = "Plugin.Build.On";
    public static final String pluginBuildOff = "Plugin.Build.Off";
    public static final String pluginAlreadyInArena = "Plugin.AlreadyInArena";
    public static final String pluginNotInArena = "Plugin.NotInArena";
    public static final String pluginReload = "Plugin.Reload";
    public static final String pluginServerReload = "Plugin.ServerReload";
    public static final String pluginBlockInteract = "Plugin.Block-Interact";
    public static final String economyPlayerNotExist = "Economy.PlayerNotExist";
    public static final String economyCurrency = "Economy.Currency";
    public static final String economyRenownCurrency = "Economy.Renown-Currency";
    public static final String economyNotEnoughGold = "Economy.NotEnoughGold";
    public static final String economyNotEnoughRenown = "Economy.Not-Enough-Renown";
    public static final String economyGive = "Economy.GiveExecuted";
    public static final String economyTake = "Economy.TakeExectuted";
    public static final String economySet = "Economy.SetExected";
    public static final String economyBalance = "Economy.Balance";
    public static final String economyKillReward = "Economy.KillReward";
    public static final String economyAssist = "Economy.Assist";
    public static final String economyGoldPickup = "Economy.GoldPickup";
    public static final String prestigeSet = "Prestige.Set";
    public static final String prestigeNotEnoughGrinded = "Prestige.Not.Enough.Grinded";

    public Messages() {
        super("Messages_" + Main.getSettings().getString("Plugin.Language").toUpperCase(), "Language");
        new EN();
        new IT();
        new CN();
        new RO();
        new ES();
        new VN();
        new PT();
        reload();
    }

    public static final String prestigeNotEnoughLevel = "Prestige.Not.Enough.Level";
    public static final String prestigeNotAvailableDuringMajorEvents = "Prestige.Not.Available-During-Major-Events";
    public static final String prestigeMaxPrestige = "Prestige.Max-Prestige";
    public static final String prestigeMoreThanAllowed = "Prestige.More-Than-Allowed";
    public static final String bountyGive = "Bounty.Give-Executed";
    public static final String bountyTake = "Bounty.Take-Executed";
    public static final String bountySet = "Bounty.Set-Executed";
    public static final String bountyNotEnoughBounty = "Bounty.Not-Enough-Bounty";
    public static final String deathByPlayer = "Death.ByPlayer";
    public static final String deathByUnknown = "Death.ByUnknown";
    public static final String deathTitle = "Death.Title";
    public static final String deathSubtitle = "Death.SubTitle";
    public static final String deathActionBar = "Death.ActionBar";
    public static final String xpRankupTitleLine1 = "XP.RankupTitleLine1";
    public static final String xpRankupTitleLine2 = "XP.RankupTitleLine2";
    public static final String xpRankupMessage = "XP.Rankup-Message";
    public static final String xpLevelMax = "XP.Level.Max";
    public static final String levelNotEnough = "Level.Not-Enough";
    public static final String levelSet = "Level.Set";
    public static final String levelMoreThanAllowed = "Level.More-Than-Allowed";
    public static final String respawnNotAvailable = "Respawn.NotAvailable";
    public static final String respawnNotAvailableAsBeast = "Respawn.Not-Available-As-Beast";
    public static final String respawnInCombat = "Respawn.InCombat";
    public static final String respawnCooldown = "Respawn.Cooldown";
    public static final String resetExecuted = "Reset.Executed";
    public static final String resetDeleted = "Reset.Deleted";
    public static final String bountyTag = "Bounty.Tag";
    public static final String bountyAnnounce = "Bounty.Announce";
    public static final String bountyClaimed = "Bounty.Claimed";
    public static final String combatLogNoAccess = "CombatLog.NoAccess";
    public static final String killStreakMessage = "KillStreak.Message";
    public static final String spawnNotSet = "SpawnNotSet";
    public static final String soon = "Soon";
}
