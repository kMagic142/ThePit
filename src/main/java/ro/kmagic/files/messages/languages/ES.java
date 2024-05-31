package ro.kmagic.files.messages.languages;

import ro.kmagic.libapi.utils.FileManager;

public class ES extends FileManager {
    public ES() {
        super("Messages_ES", "Language");
        this.addDefault("Plugin.NotAvailableInConsole", "&cLos comandos no se pueden usar desde la consola.");
        this.addDefault("Plugin.NoPermission", "&cNo tienes permisos para usar este comando.");
        this.addDefault("Plugin.Command-Not-Available-In-World", "&7This command cannot be executed in this world!");
        this.addDefault("Plugin.Build.On", "&7Ahora solo t\u00fa puedes construir.");
        this.addDefault("Plugin.Build.Off", "&7Ahora ya no puedes construir m\u00e1s.");
        this.addDefault("Plugin.AlreadyInArena", "&7You're already in ThePit Arena!");
        this.addDefault("Plugin.NotInArena", "&7You're not in ThePit Arena!");
        this.addDefault("Plugin.Reload", "&a&lThePit por MrDarkness462 &7ha sido reiniciado!");
        this.addDefault("Plugin.ServerReload", "&7El server se ha reiniciado y has sido pateado por ThePit");
        this.addDefault("Plugin.Block-Interact", "&cYou can't interact with this block!");
        this.addDefault("Economy.PlayerNotExist", "&7Ese jugador no existe.");
        this.addDefault("Economy.Currency", "Oro");
        this.addDefault("Economy.Renown-Currency", "&cRenown");
        this.addDefault("Economy.NotEnoughGold", "&7No tienes el oro suficiente.");
        this.addDefault("Economy.Not-Enough-Renown", "&7You don't have enough renown.");
        this.addDefault("Economy.GiveExecuted", "&7Player &f%target% &7has received &f%gold%");
        this.addDefault("Economy.TakeExectuted", "&7You took &f%gold% &7from &f%target%");
        this.addDefault("Economy.SetExected", "&7Player &f%target% &7has now &f%gold%");
        this.addDefault("Economy.Balance", "&7Balance: &f%gold%");
        this.addDefault("Economy.KillReward", "&a&lASESINATO! &7en %killed% &3+%xp%XP &6%coins%g");
        this.addDefault("Economy.Assist", "&a&lASISTENCIA! &7en %victim% &b+%xp%XP &6+%gold%g");
        this.addDefault("Economy.GoldPickup", "&6&lORO RECOGIDO! &7del suelo &6%coins%g");
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
        this.addDefault("Death.ByPlayer", "&c&lMUERTE! &7por %killer%");
        this.addDefault("Death.ByUnknown", "&c&lMUERTE!");
        this.addDefault("Death.Title", "&c&lHAS MUERTO!");
        this.addDefault("Death.SubTitle", "");
        this.addDefault("Death.ActionBar", "&a&lASESINATO de &7%victim%!");
        this.addDefault("XP.RankupTitleLine1", "&b&lSUBIDA DE NIVEL!");
        this.addDefault("XP.RankupTitleLine2", "&7[%oldlvl%&7] \u27a0 &7[%newlvl%&7]");
        this.addDefault("XP.Rankup-Message", "&b&lPIT LEVEL UP! %oldlvl% \u27a0 %newlvl%");
        this.addDefault("XP.Level.Max", "&bMAXED");
        this.addDefault("Level.Not-Enough", "&cYou don't have the required level to use this feature!");
        this.addDefault("Level.Set", "&7Player &f%target% &7has now &fLevel %level%");
        this.addDefault("Level.More-Than-Allowed", "&cYou can't set level greater than 120!");
        this.addDefault("Respawn.NotAvailable", "&cNo puedes usar /respawn aqu\u00ed!");
        this.addDefault("Respawn.InCombat", "&c&lESPERE! &7No puedes usar /respawn cuando estas luchando (&c%seconds%s &7restantes)");
        this.addDefault("Respawn.Cooldown", "&cSolo puedes usar el comando /respawn cada 10 segundos!");
        this.addDefault("Respawn.Not-Available-As-Beast", "&cYou cannot use /respawn while you're a beast!");
        this.addDefault("Reset.Executed", "&fLa cuenta de %target% ha sido eliminada!");
        this.addDefault("Reset.Deleted", "&fTu cuenta se ha eliminado! Vuelve a entrar al servidor");
        this.addDefault("Bounty.Tag", "&6&l%gold%g");
        this.addDefault("Bounty.Announce", "&6&lRECOMPENSA! &7de &6&l%bounty%g &7en %tag% &7por una racha alta");
        this.addDefault("Bounty.Claimed", "&6&lRECOMPENSA RECOGIDA! %killer_tag% &7ha asesinado a %killed_tag% &7por &6&l%gold%g");
        this.addDefault("CombatLog.NoAccess", "&7No puedes usar comandos mientras est\u00e1s en PvP.");
        this.addDefault("KillStreak.Message", "&c&lRACHA! &7de &c%kills% &7Asesinatos por %player%");
        this.addDefault("SpawnNotSet", "&7El punto de reaparici\u00f3n no est\u00e1 colocado!");
        this.addDefault("Soon", "&c&lPROXIMAMENTE");
        this.copyDefaults();
        this.save();
    }
}
