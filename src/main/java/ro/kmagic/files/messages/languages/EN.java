package ro.kmagic.files.messages.languages;

import ro.kmagic.libapi.utils.FileManager;

public class EN extends FileManager {
    public EN() {
        super("Messages_EN", "Language");
        this.addDefault("Plugin.NotAvailableInConsole", "&cThis command isn't available in console.");
        this.addDefault("Plugin.NoPermission", "&cYou don't have permission to use this command.");
        this.addDefault("Plugin.Command-Not-Available-In-World", "&7This command cannot be executed in this world!");
        this.addDefault("Plugin.Build.On", "&7Now only you can build!");
        this.addDefault("Plugin.Build.Off", "&7Now you can't build anymore!");
        this.addDefault("Plugin.AlreadyInArena", "&7You're already in ThePit Arena!");
        this.addDefault("Plugin.NotInArena", "&7You're not in ThePit Arena!");
        this.addDefault("Plugin.Reload", "&a&lThePit &7was reloaded!");
        this.addDefault("Plugin.ServerReload", "&7Server was reloaded and you have been kicked by ThePit");
        this.addDefault("Plugin.Block-Interact", "&cYou can't interact with this block!");
        this.addDefault("Economy.PlayerNotExist", "&7This player doesn't exist.");
        this.addDefault("Economy.Currency", "Gold");
        this.addDefault("Economy.Renown-Currency", "&cRenown");
        this.addDefault("Economy.NotEnoughGold", "&7You don't have enough gold.");
        this.addDefault("Economy.Not-Enough-Renown", "&7You don't have enough renown.");
        this.addDefault("Economy.GiveExecuted", "&7Player &f%target% &7has received &f%gold%");
        this.addDefault("Economy.TakeExectuted", "&7You took &f%gold% &7from &f%target%");
        this.addDefault("Economy.SetExected", "&7Player &f%target% &7has now &f%gold%");
        this.addDefault("Economy.Balance", "&7Balance: &f%gold%");
        this.addDefault("Economy.KillReward", "&a&lKILL! &7on %killed% &3+%xp%XP &6%coins%g");
        this.addDefault("Economy.Assist", "&a&lASSIST! &7on %victim% &b+%xp%XP &6+%gold%g");
        this.addDefault("Economy.GoldPickup", "&6&lGOLD PICKUP! &7from the ground &6%coins%g");
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
        this.addDefault("Death.ByPlayer", "&c&lDEATH! &7by %killer%");
        this.addDefault("Death.ByUnknown", "&c&lDEATH!");
        this.addDefault("Death.Title", "&c&lYOU DIED");
        this.addDefault("Death.SubTitle", "");
        this.addDefault("Death.ActionBar", "&7%victim% &a&lKILL!");
        this.addDefault("XP.RankupTitleLine1", "&b&lLEVEL UP!");
        this.addDefault("XP.RankupTitleLine2", "&7[%oldlvl%&7] \u27a0 &7[%newlvl%&7]");
        this.addDefault("XP.Rankup-Message", "&b&lPIT LEVEL UP! %oldlvl% \u27a0 %newlvl%");
        this.addDefault("XP.Level.Max", "&bMAXED");
        this.addDefault("Level.Not-Enough", "&cYou don't have the required level to use this feature!");
        this.addDefault("Level.Set", "&7Player &f%target% &7has now &fLevel %level%");
        this.addDefault("Level.More-Than-Allowed", "&cYou can't set level greater than 120!");
        this.addDefault("Respawn.NotAvailable", "&cYou cannot /respawn here!");
        this.addDefault("Respawn.InCombat", "&c&lHOLD UP! &7Can't /respawn while fighting (&c%seconds%s &7left)");
        this.addDefault("Respawn.Cooldown", "&cYou may only /respawn every 10 seconds!");
        this.addDefault("Respawn.Not-Available-As-Beast", "&cYou cannot use /respawn while you're a beast!");
        this.addDefault("Reset.Executed", "&f%target%'s account was removed!");
        this.addDefault("Reset.Deleted", "&fYour account was deleted! You must rejoin!");
        this.addDefault("Bounty.Tag", "&6&l%gold%g");
        this.addDefault("Bounty.Announce", "&6&lBOUNTY! &7of &6&l%bounty%g &7on %tag% &7for high streak");
        this.addDefault("Bounty.Claimed", "&6&lBOUNTY CLAIMED! %killer_tag% &7killed %killed_tag% &7for &6&l%gold%g");
        this.addDefault("CombatLog.NoAccess", "&7You can't use this command while in pvp.");
        this.addDefault("KillStreak.Message", "&c&lSTREAK! &7of &c%kills% &7kills by %player%");
        this.addDefault("SpawnNotSet", "&7Spawn isn't set!");
        this.addDefault("Soon", "&c&lSOON");
        this.copyDefaults();
        this.save();
    }
}
