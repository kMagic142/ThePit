package ro.kmagic.files.messages.languages;

import ro.kmagic.libapi.utils.FileManager;

public class IT extends FileManager {
    public IT() {
        super("Messages_IT", "Language");
        this.addDefault("Plugin.NotAvailableInConsole", "&cComandi non disponibili in console.");
        this.addDefault("Plugin.NoPermission", "&cNon hai il permesso per usare questo comando.");
        this.addDefault("Plugin.Command-Not-Available-In-World", "&7This command cannot be executed in this world!");
        this.addDefault("Plugin.Build.On", "&7Ora puoi costruire!");
        this.addDefault("Plugin.Build.Off", "&7Non puoi pi\u00f9 costruire!");
        this.addDefault("Plugin.AlreadyInArena", "&7Sei gi\u00e0 nell'arena ThePit!");
        this.addDefault("Plugin.NotInArena", "&7Non sei nell'arena ThePit!");
        this.addDefault("Plugin.Reload", "&a&lThePit &7\u00e9 stato ricaricato!");
        this.addDefault("Plugin.ServerReload", "&7Il Server \u00e8 stato riavviato e sei stato espulso da ThePit");
        this.addDefault("Plugin.Block-Interact", "&cYou can't interact with this block!");
        this.addDefault("Economy.PlayerNotExist", "&7Questo player non esiste.");
        this.addDefault("Economy.Currency", "Oro");
        this.addDefault("Economy.Renown-Currency", "&cRenown");
        this.addDefault("Economy.NotEnoughGold", "&7Non hai abbastanza monete.");
        this.addDefault("Economy.Not-Enough-Renown", "&7You don't have enough renown.");
        this.addDefault("Economy.GiveExecuted", "&7Giocatore &f%target% &7ha ricevuto &f%gold%");
        this.addDefault("Economy.TakeExectuted", "&7Hai preso &f%gold% &7da &f%target%");
        this.addDefault("Economy.SetExected", "&7Giocatore &f%target% &7ha ora &f%gold%");
        this.addDefault("Economy.Balance", "&7Bilancio: &f%gold%");
        this.addDefault("Economy.KillReward", "&a&lUCCISIONE! &7su %killed% &3+%xp%XP &6%coins%g");
        this.addDefault("Economy.Assist", "&a&lASSIST! &7su %victim% &b+%xp%XP &6+%gold%g");
        this.addDefault("Economy.GoldPickup", "&6&lORO RACCOLTO! &7dal suolo &6%coins%g");
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
        this.addDefault("Death.ByPlayer", "&c&lMORTE! &7da %killer%");
        this.addDefault("Death.ByUnknown", "&c&lMORTE!");
        this.addDefault("Death.Title", "&c&lSEI MORTO");
        this.addDefault("Death.SubTitle", "");
        this.addDefault("Death.ActionBar", "&7%victim% &a&lUCCISIONE!");
        this.addDefault("XP.RankupTitleLine1", "&b&lAUMENTO LIVELLO!");
        this.addDefault("XP.RankupTitleLine2", "&7[%oldlvl%&7] \u27a0 &7[&9%newlvl%&7]");
        this.addDefault("XP.Rankup-Message", "&b&lPIT LEVEL UP! %oldlvl% \u27a0 %newlvl%");
        this.addDefault("XP.Level.Max", "&bMAXED");
        this.addDefault("Level.Not-Enough", "&cYou don't have the required level to use this feature!");
        this.addDefault("Level.Set", "&7Player &f%target% &7has now &fLevel %level%");
        this.addDefault("Level.More-Than-Allowed", "&cYou can't set level greater than 120!");
        this.addDefault("Respawn.NotAvailable", "&cNon puoi usare /respawn qui!");
        this.addDefault("Respawn.InCombat", "&c&lASPETTA! &7Non puoi fare /respawn in combattimento (&c%seconds%s &7rimasti)");
        this.addDefault("Respawn.Cooldown", "&cPuoi usare /respawn solo ogni 10 secondi!");
        this.addDefault("Respawn.Not-Available-As-Beast", "&cYou cannot use /respawn while you're a beast!");
        this.addDefault("Reset.Executed", "&fAccount di %target% rimosso!");
        this.addDefault("Reset.Deleted", "&7Il tuo account \u00e9 stato rimossi! Devi rientrare!");
        this.addDefault("Bounty.Tag", "&6&l%gold%g");
        this.addDefault("Bounty.Announce", "&6&lBOUNTY! &7di &6&l%bounty%g &7su %tag% &7per streak alta");
        this.addDefault("Bounty.Claimed", "&6&lBOUNTY PRESO! %killer_tag% ha ucciso &7 %killed_tag%  &7per &6&l%gold%g");
        this.addDefault("KillStreak.Message", "&c&lSTREAK! &7di &c%kills% &7uccisioni da %player%");
        this.addDefault("CombatLog.NoAccess", "&7Non puoi usare questo comando in pvp.");
        this.addDefault("SpawnNotSet", "&7Spawn non impostato!");
        this.addDefault("Soon", "&c&PRESTO");
        this.copyDefaults();
        this.save();
    }
}
