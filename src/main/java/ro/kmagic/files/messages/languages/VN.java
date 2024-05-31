package ro.kmagic.files.messages.languages;

import ro.kmagic.libapi.utils.FileManager;

public class VN extends FileManager {
    public VN() {
        super("Messages_VN", "Language");
        this.addDefault("Plugin.NotAvailableInConsole", "&cC\u00e1c l\u1ec7nh kh\u00f4ng c\u00f3 s\u1eb5n trong c\u1ed1nle.");
        this.addDefault("Plugin.NoPermission", "&cB\u1ea1n kh\u00f4ng c\u00f3 quy\u1ec1n \u0111\u1ec3 d\u00f9ng c\u00e2u l\u1ec7nh n\u00e0y.");
        this.addDefault("Plugin.Command-Not-Available-In-World", "&7This command cannot be executed in this world!");
        this.addDefault("Plugin.Build.On", "&7B\u00e2y gi\u1edd m\u1ed7i m\u00ecnh b\u1ea1n c\u00f3 th\u1ec3 x\u00e2y d\u1ef1ng");
        this.addDefault("Plugin.Build.Off", "&7B\u00e2y gi\u1edd b\u1ea1n kh\u00f4ng th\u1ec3 x\u00e2y d\u1ef1ng n\u1eefa");
        this.addDefault("Plugin.AlreadyInArena", "&7You're already in ThePit Arena!");
        this.addDefault("Plugin.NotInArena", "&7You're not in ThePit Arena!");
        this.addDefault("Plugin.Reload", "&a&lThePit &7b\u1edfi MrDarkness462 &7\u0111\u00e3 \u0111\u01b0\u1ee3c reload!");
        this.addDefault("Plugin.ServerReload", "&7Server \u0111\u00e3 reload v\u00e0 b\u1ea1n b\u1ecb kick b\u1edfi ThePit.");
        this.addDefault("Plugin.Block-Interact", "&cYou can't interact with this block!");
        this.addDefault("Economy.PlayerNotExist", "&7Ng\u01b0\u1eddi ch\u01a1i n\u00e0y kh\u00f4ng t\u1ed3n t\u1ea1i.");
        this.addDefault("Economy.Currency", "Gold");
        this.addDefault("Economy.Renown-Currency", "&cRenown");
        this.addDefault("Economy.NotEnoughGold", "&7B\u1ea1n kh\u00f4ng c\u00f3 \u0111\u1ee7 gold.");
        this.addDefault("Economy.Not-Enough-Renown", "&7You don't have enough renown.");
        this.addDefault("Economy.GiveExecuted", "&7Player &f%target% &7has received &f%gold%");
        this.addDefault("Economy.TakeExectuted", "&7You took &f%gold% &7from &f%target%");
        this.addDefault("Economy.SetExected", "&7Player &f%target% &7has now &f%gold%");
        this.addDefault("Economy.Balance", "&7T\u00e0i kho\u1ea3n: &f%gold%");
        this.addDefault("Economy.KillReward", "&a&lGI\u1ebeT!&7 %killed% &3+%xp%XP &6%coins%g");
        this.addDefault("Economy.Assist", "&a&lH\u1ed6 TR\u1ee2!&7 %victim% &b+%xp%XP &6+%gold%g");
        this.addDefault("Economy.GoldPickup", "&6&lNH\u1eb6T \u0110\u01af\u1ee2C GOLD! &7t\u1eeb m\u1eb7t \u0111\u1ea5t \u0111\u01b0\u1ee3c &6%coins%g");
        this.addDefault("Prestige.Set", "&7Player &f%target% &7has now &fPrestige %prestige%");
        this.addDefault("Prestige.Not.Enough.Grinded", "&cYou did not grind enough! You grinded %grindedGold% out of %totalToGrindedGold%");
        this.addDefault("Prestige.Not.Available-During-Major-Events", "&cYou can't prestige while major events are started!");
        this.addDefault("Prestige.Not.Enough.Level", "&cYou need level 120 in order to prestige!");
        this.addDefault("Prestige.Max-Prestige", "&cYou've reached maximum prestige!");
        this.addDefault("Prestige.More-Than-Allowed", "&cYou can't set prestige greater than 35!");
        this.addDefault("Bounty.Give-Executed", "&7Player &f%target%&7's bounty increased to &f%bounty%");
        this.addDefault("Bounty.Take-Executed", "&7You decreased &f%bounty% &7from &f%target%&7's bounty");
        this.addDefault("Bounty.Set-Executed", "&7Player &f%target%&7's bounty was set to &f%bounty%");
        this.addDefault("Bounty.Not-Enough-Bounty", "&7You don't have enough bounty.");
        this.addDefault("Death.ByPlayer", "&c&lB\u1ea0N \u0110\u00c3 CH\u1ebeT! &7b\u1edfi %killer%");
        this.addDefault("Death.ByUnknown", "&c&lB\u1ea0N \u0110\u00c3 CH\u1ebeT!");
        this.addDefault("Death.Title", "&c&lB\u1ea0N \u0110\u00c3 CH\u1ebeT!");
        this.addDefault("Death.SubTitle", "");
        this.addDefault("Death.ActionBar", "&a&lGi\u1ebft &7%victim% &a&l!");
        this.addDefault("XP.RankupTitleLine1", "&b&lL\u00caN LEVEL!");
        this.addDefault("XP.RankupTitleLine2", "&7[%oldlvl%&7] \u27a0 &7[%newlvl%&7]");
        this.addDefault("XP.Rankup-Message", "&b&lPIT LEVEL UP! %oldlvl% \u27a0 %newlvl%");
        this.addDefault("XP.Level.Max", "&bMAXED");
        this.addDefault("Level.Not-Enough", "&cYou don't have the required level to use this feature!");
        this.addDefault("Level.Set", "&7Player &f%target% &7has now &fLevel %level%");
        this.addDefault("Level.More-Than-Allowed", "&cYou can't set level greater than 120!");
        this.addDefault("Respawn.NotAvailable", "&cB\u1ea1n kh\u00f4ng th\u1ec3 /respawn t\u1ea1i \u0111\u00e2y!");
        this.addDefault("Respawn.InCombat", "&c&lC\u1ed0 L\u00caN! &7B\u1ea1n kh\u00f4ng th\u1ec3 /respawn khi \u0111ang pvp (&c%seconds%s &7c\u00f2n l\u1ea1i \u0111\u1ec3 \u0111\u01b0\u1ee3c /respawn)");
        this.addDefault("Respawn.Cooldown", "&cB\u1ea1n ch\u1ec9 c\u00f3 th\u1ec3 /respawn c\u1ee9 sau 10 gi\u00e2y");
        this.addDefault("Respawn.Not-Available-As-Beast", "&cYou cannot use /respawn while you're a beast!");
        this.addDefault("Reset.Executed", "&7T\u00e0i kho\u1ea3n &f%target%''s &7\u0111\u00e3 b\u1ecb x\u00f3a!");
        this.addDefault("Reset.Deleted", "&fT\u00e0i kho\u1ea3n c\u1ee7a b\u1ea1n ! B\u1ea1n ph\u1ea3i join l\u1ea1i!");
        this.addDefault("Bounty.Tag", "&6&l%gold%g");
        this.addDefault("Bounty.Announce", "&6&lTRY N\u00c3! &7v\u1edbi ti\u1ec1n th\u01b0\u1edfng &6&l%bounty%g &7cho %tag% &7v\u00ec c\u00f3 s\u1ed1 l\u1ea7n li\u00ean tr\u1ea3m cao");
        this.addDefault("Bounty.Claimed", "&6&lNH\u1eacN TI\u1ec0N TRUY N\u00c3! &7cho &c%killer_tag% &7v\u00ec \u0111\u00e3 gi\u1ebft &a%killed_tag% &7s\u1ed1 ti\u1ec1n l\u00e0 &6&l%gold%g");
        this.addDefault("CombatLog.NoAccess", "&7B\u1ea1n kh\u00f4ng th\u1ec3 d\u00f9ng c\u00e2u l\u1ec7nh n\u00e0y khi \u0111ang pvp.");
        this.addDefault("KillStreak.Message", "&c&lLI\u00caN TR\u1ea2M! &c%kills% &7ng\u01b0\u1eddi b\u1edfi %player%");
        this.addDefault("SpawnNotSet", "&7Spawn ch\u01b0a \u0111\u01b0\u1ee3c set!");
        this.addDefault("Soon", "&c&lS\u1edbm");
        this.copyDefaults();
        this.save();
    }
}
