package ro.kmagic.files.messages.languages;

import ro.kmagic.libapi.utils.FileManager;

public class PT extends FileManager {
    public PT() {
        super("Messages_PT", "Language");
        this.addDefault("Plugin.NotAvailableInConsole", "&cEste comando n\u00e3o est\u00e1 dispon\u00edvel no console.");
        this.addDefault("Plugin.NoPermission", "&cVoc\u00ea n\u00e3o tem permiss\u00e3o para usar este comando.");
        this.addDefault("Plugin.Command-Not-Available-In-World", "&7This command cannot be executed in this world!");
        this.addDefault("Plugin.Build.On", "&7Agora s\u00f3 voc\u00ea pode construir");
        this.addDefault("Plugin.Build.Off", "&7Agora voc\u00ea n\u00e3o pode mais construir");
        this.addDefault("Plugin.AlreadyInArena", "&7Voc\u00ea j\u00e1 est\u00e1 na Arena ThePit!");
        this.addDefault("Plugin.NotInArena", "&7Voc\u00ea n\u00e3o est\u00e1 na Arena ThePit!");
        this.addDefault("Plugin.Reload", "&a&lThePit &7foi recarregado!");
        this.addDefault("Plugin.ServerReload", "&7Servidor foi kickado e voc\u00ea foi chutado por ThePit.");
        this.addDefault("Plugin.Block-Interact", "&cYou can't interact with this block!");
        this.addDefault("Economy.PlayerNotExist", "&7Este jogador n\u00e3o existe.");
        this.addDefault("Economy.Currency", "Ouro");
        this.addDefault("Economy.Renown-Currency", "&cRenown");
        this.addDefault("Economy.NotEnoughGold", "&7Voc\u00ea n\u00e3o tem ouro suficiente.");
        this.addDefault("Economy.Not-Enough-Renown", "&7You don't have enough renown.");
        this.addDefault("Economy.GiveExecuted", "&7Jogador &f%target% &7recebeu &f%gold%");
        this.addDefault("Economy.TakeExectuted", "&7Voc\u00ea retirou &f%gold% &7de &f%target%");
        this.addDefault("Economy.SetExected", "&7Jogador &f%target% &7agora tem &f%gold%");
        this.addDefault("Economy.Balance", "&7Ouros: &f%gold%");
        this.addDefault("Economy.KillReward", "&a&lMATOU! &7o(a) %killed% &3+%xp%XP &6%coins%g");
        this.addDefault("Economy.Assist", "&a&lASSIST! &7o(a) %victim% &b+%xp%XP &6+%gold%g");
        this.addDefault("Economy.GoldPickup", "&6&lCOLETOU OURO! &7do ch\u00e3o &6%coins%g");
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
        this.addDefault("Death.ByPlayer", "&c&lMORREU! &7por %killer%");
        this.addDefault("Death.ByUnknown", "&c&lMORTE!");
        this.addDefault("Death.Title", "&c&lVOC\u00ca MORREU");
        this.addDefault("Death.SubTitle", "");
        this.addDefault("Death.ActionBar", "&7%victim% &a&lMATOU!");
        this.addDefault("XP.RankupTitleLine1", "&b&lLEVEL UP!");
        this.addDefault("XP.RankupTitleLine2", "&7[%oldlvl%&7] \u27a0 &7[%newlvl%&7]");
        this.addDefault("XP.Rankup-Message", "&b&lPIT LEVEL UP! %oldlvl% \u27a0 %newlvl%");
        this.addDefault("XP.Level.Max", "&bMAXED");
        this.addDefault("Level.Not-Enough", "&cYou don't have the required level to use this feature!");
        this.addDefault("Level.Set", "&7Player &f%target% &7has now &fLevel %level%");
        this.addDefault("Level.More-Than-Allowed", "&cYou can't set level greater than 120!");
        this.addDefault("Respawn.NotAvailable", "&cVoc\u00ea n\u00e3o pode usar /respawn aqui!");
        this.addDefault("Respawn.InCombat", "&c&lAGUARDE! &7N\u00e3o pode usar /respawn enquanto luta (&c%seconds%s &7para acabar)");
        this.addDefault("Respawn.Cooldown", "&cVoc\u00ea s\u00f3 pode usar /respawn a cada 10 segundos!");
        this.addDefault("Respawn.Not-Available-As-Beast", "&cYou cannot use /respawn while you're a beast!");
        this.addDefault("Reset.Executed", "&f%target% teve sua conta foi removida!");
        this.addDefault("Reset.Deleted", "&fSua conta foi exclu\u00edda! Voc\u00ea deve se juntar novamente!");
        this.addDefault("Bounty.Tag", "&6&l%gold%g");
        this.addDefault("Bounty.Announce", "&6&lRECOMPENSA! &7de &6&l%bounty%g &7por %tag% &7por alta sequ\u00eancia");
        this.addDefault("Bounty.Claimed", "&6&lRECOMPENSA REIVINDICADA! %killer_tag% &7matou %killed_tag% &7por &6&l%gold%g");
        this.addDefault("CombatLog.NoAccess", "&7Voc\u00ea n\u00e3o pode usar este comando enquanto estiver no pvp.");
        this.addDefault("KillStreak.Message", "&c&lSEQU\u00caNCIA! &7de &c%kills% &7do Jogador %player%");
        this.addDefault("SpawnNotSet", "&7Spawn n\u00e3o est\u00e1 definido!");
        this.addDefault("Soon", "&c&lEM BREVE");
        this.copyDefaults();
        this.save();
    }
}
