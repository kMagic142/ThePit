package ro.kmagic.files.messages.languages;

import ro.kmagic.libapi.utils.FileManager;

public class CN extends FileManager {
    public CN() {
        super("Messages_CN", "Language");
        this.addDefault("Plugin.NotAvailableInConsole", "&c\u6307\u4ee4\u5728\u63a7\u5236\u53f0\u4e2d\u4e0d\u53ef\u4f7f\u7528.");
        this.addDefault("Plugin.NoPermission", "&c\u4f60\u6ca1\u6709\u6743\u9650\u4f7f\u7528\u8fd9\u4e2a\u6307\u4ee4!");
        this.addDefault("Plugin.Command-Not-Available-In-World", "&7This command cannot be executed in this world!");
        this.addDefault("Plugin.Build.On", "&7\u4f60\u73b0\u5728\u53ef\u4ee5\u8fdb\u884c\u5efa\u7b51!");
        this.addDefault("Plugin.Build.Off", "&7\u4f60\u73b0\u5728\u4e0d\u80fd\u8fdb\u884c\u5efa\u7b51\u4e86!");
        this.addDefault("Plugin.AlreadyInArena", "&7You're already in ThePit Arena!");
        this.addDefault("Plugin.NotInArena", "&7You're not in ThePit Arena!");
        this.addDefault("Plugin.Reload", "&a&lThePit &7\u91cd\u8f7d\u6210\u529f!");
        this.addDefault("Plugin.ServerReload", "&7Server was reloaded and you have been kicked by ThePit");
        this.addDefault("Plugin.Block-Interact", "&cYou can't interact with this block!");
        this.addDefault("Economy.PlayerNotExist", "&7\u8be5\u73a9\u5bb6\u4e0d\u5b58\u5728.");
        this.addDefault("Economy.Currency", "\u91d1\u952d");
        this.addDefault("Economy.Renown-Currency", "&cRenown");
        this.addDefault("Economy.NotEnoughGold", "&7\u4f60\u6ca1\u6709\u8db3\u591f\u7684\u91d1\u952d.");
        this.addDefault("Economy.Not-Enough-Renown", "&7You don't have enough renown.");
        this.addDefault("Economy.GiveExecuted", "&7\u73a9\u5bb6 &f%target% &7\u83b7\u5f97\u4e86 &f%gold%\u91d1\u952d");
        this.addDefault("Economy.TakeExectuted", "&7\u4f60\u4ece &f%target%&7\u62ff\u8d70\u4e86 &f%gold%\u91d1\u952d");
        this.addDefault("Economy.SetExected", "&7\u73a9\u5bb6 &f%target% &7\u73b0\u5728\u62e5\u6709 &f%gold%\u91d1\u952d");
        this.addDefault("Economy.Balance", "&7\u91d1\u952d: &f%gold%");
        this.addDefault("Economy.KillReward", "&a&l\u51fb\u6740! &7\u4f60\u51fb\u6740\u4e86 %killed% &3+%xp%\u7ecf\u9a8c &6%coins%\u91d1\u952d");
        this.addDefault("Economy.Assist", "&a&l\u52a9\u653b! &7\u6765\u81ea\u51fb\u6740 %victim% &b+%xp%\u7ecf\u9a8c &6+%gold%\u91d1\u952d");
        this.addDefault("Economy.GoldPickup", "&6&l\u62fe\u53d6\u91d1\u952d! &7\u6765\u81ea\u5730\u56fe\u62fe\u53d6 &6+%coins%\u91d1\u952d");
        this.addDefault("Prestige.Set", "&7Player &f%target% &7has now &fPrestige %prestige%");
        this.addDefault("Prestige.Not.Enough.Grinded", "&cYou did not grind enough! You grinded &6%grindedGold%g &cout of &6%totalToGrindedGold%g");
        this.addDefault("Prestige.Not.Available-During-Major-Events", "&cYou can't prestige while major events are started!");
        this.addDefault("Prestige.Not.Enough.Level", "&cYou need level 120 in order to prestige!");
        this.addDefault("Prestige.Max-Prestige", "&cYou've reached maximum prestige!");
        this.addDefault("Prestige.More-Than-Allowed", "&cYou can't set prestige greater than 35!");
        this.addDefault("Bounty.Give-Executed", "&7Player &f%target%&7's bounty increased to &f%bounty%");
        this.addDefault("Bounty.Take-Executed", "&7You decreased &f%bounty% &7from &f%target%&7's bounty");
        this.addDefault("Bounty.Set-Executed", "&7Player &f%target%&7's bounty was set to &f%bounty%");
        this.addDefault("Bounty.Not-Enough-Bounty", "&7You don't have enough bounty.");
        this.addDefault("Death.ByPlayer", "&c&l\u6b7b\u4ea1! &7\u88ab %killer% \u51fb\u6740");
        this.addDefault("Death.ByUnknown", "&c&l\u6b7b\u4ea1!");
        this.addDefault("Death.Title", "&c&l\u4f60\u6b7b\u4e86");
        this.addDefault("Death.SubTitle", "");
        this.addDefault("Death.ActionBar", "&a&l\u51fb\u6740! &7%victim%");
        this.addDefault("XP.RankupTitleLine1", "&b&l\u5347\u7ea7!");
        this.addDefault("XP.RankupTitleLine2", "&7[%oldlvl%&7] \u27a0 &7[%newlvl%&7]");
        this.addDefault("XP.Rankup-Message", "&b&lPIT LEVEL UP! %oldlvl% \u27a0 %newlvl%");
        this.addDefault("XP.Level.Max", "&bMAXED");
        this.addDefault("Level.Not-Enough", "&cYou don't have the required level to use this feature!");
        this.addDefault("Level.Set", "&7Player &f%target% &7has now &fLevel %level%");
        this.addDefault("Level.More-Than-Allowed", "&cYou can't set level greater than 120!");
        this.addDefault("Respawn.NotAvailable", "&c\u4f60\u4e0d\u80fd\u5728\u8fd9\u91cd\u751f!");
        this.addDefault("Respawn.InCombat", "&c&l\u8b66\u544a! &7\u4f60\u4e0d\u80fd\u5728\u6218\u6597\u72b6\u6001\u4f7f\u7528 /respawn (&7\u5269\u4f59 &c%seconds%\u79d2)");
        this.addDefault("Respawn.Cooldown", "&c\u8bf7\u7b49\u5f8510\u79d2\u540e\u624d\u80fd\u518d\u6b21\u4f7f\u7528!");
        this.addDefault("Respawn.Not-Available-As-Beast", "&cYou cannot use /respawn while you're a beast!");
        this.addDefault("Reset.Executed", "&f%target%'\u5df2\u88ab\u91cd\u7f6e!");
        this.addDefault("Reset.Deleted", "&f\u4f60\u7684\u6570\u636e\u5df2\u88ab\u91cd\u7f6e!\u4f60\u5fc5\u987b\u91cd\u65b0\u8fdb\u5165\u670d\u52a1\u5668");
        this.addDefault("Bounty.Tag", "&6&l%gold%g");
        this.addDefault("Bounty.Announce", "&6&lBOUNTY! &7of &6&l%bounty%g &7on %tag% &7for high streak");
        this.addDefault("Bounty.Claimed", "&6&lBOUNTY CLAIMED! %killer_tag% &7killed %killed_tag% &7for &6&l%gold%g");
        this.addDefault("CombatLog.NoAccess", "&7\u4f60\u4e0d\u80fd\u5728\u6218\u6597\u72b6\u6001\u4f7f\u7528\u6307\u4ee4.");
        this.addDefault("KillStreak.Message", "&c&l\u8fde\u6740! &7%player% \u5b8c\u6210\u4e86 &c%kills% &7\u8fde\u6740\uff01");
        this.addDefault("SpawnNotSet", "&7\u51fa\u751f\u70b9\u8fd8\u672a\u8bbe\u7f6e!");
        this.addDefault("Soon", "&c&l\u4e0d\u4e45");
        this.copyDefaults();
        this.save();
    }
}
