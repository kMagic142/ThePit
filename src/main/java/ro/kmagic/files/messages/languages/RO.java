package ro.kmagic.files.messages.languages;

import ro.kmagic.libapi.utils.FileManager;

public class RO extends FileManager {
    public RO() {
        super("Messages_RO", "Language");
        this.addDefault("Plugin.NotAvailableInConsole", "&cAceasta comanda nu este valabila in consola.");
        this.addDefault("Plugin.NoPermission", "&cNu ai permisiune pentru a folosi aceasta comanda.");
        this.addDefault("Plugin.Command-Not-Available-In-World", "&7Aceasta comanda nu poate fi executata in aceasta lume!");
        this.addDefault("Plugin.Build.On", "&7Acum poti construi!");
        this.addDefault("Plugin.Build.Off", "&7Acum nu mai poti construi!");
        this.addDefault("Plugin.AlreadyInArena", "&7Esti deja in arena de ThePit!");
        this.addDefault("Plugin.NotInArena", "&7Nu esti in arena de ThePit!");
        this.addDefault("Plugin.Reload", "&a&lThePit &7a fost reincarcat!");
        this.addDefault("Plugin.ServerReload", "&7Server-ul a fost reincarcat si ai fost dat afara.");
        this.addDefault("Plugin.Block-Interact", "&cNu poti interactiona cu acest block!");
        this.addDefault("Economy.PlayerNotExist", "&7Acest jucator nu exista.");
        this.addDefault("Economy.Currency", "Aur");
        this.addDefault("Economy.Renown-Currency", "&cRenown");
        this.addDefault("Economy.NotEnoughGold", "&7Nu ai suficient aur.");
        this.addDefault("Economy.Not-Enough-Renown", "&7Nu ai suficient renown.");
        this.addDefault("Economy.GiveExecuted", "&7Jucatorul &f%target% &7a primit &f%gold%");
        this.addDefault("Economy.TakeExectuted", "&7I-ai luat &f%gold% &7lui &f%target%");
        this.addDefault("Economy.SetExected", "&7Jucatorul &f%target% &7are acum &f%gold%");
        this.addDefault("Economy.Balance", "&7Balanta: &f%gold%");
        this.addDefault("Economy.KillReward", "&a&lKILL! &7pe %killed% &3+%xp%XP &6%coins%g");
        this.addDefault("Economy.Assist", "&a&lASSIST! &7pe %victim% &b+%xp%XP &6+%gold%g");
        this.addDefault("Economy.GoldPickup", "&6&lGOLD RIDICAT! &7de pe jos &6%coins%g");
        this.addDefault("Prestige.Set", "&7Jucatorul &f%target% &7are acum &fPrestige %prestige%");
        this.addDefault("Prestige.Not.Enough.Grinded", "&cNu ai colectat gold suficient! Ai colectat &6%grindedGold%g &cdin &6%totalToGrindedGold%g");
        this.addDefault("Prestige.Not.Available-During-Major-Events", "&cNu poti folosi functia de prestige cat timp evenimentele majore ruleaza!");
        this.addDefault("Prestige.Not.Enough.Level", "&cAi nevoie de nivel 120 pentru a folosi functia de prestige!");
        this.addDefault("Prestige.Max-Prestige", "&cAi ajuns la nivelul maxim de prestige!");
        this.addDefault("Prestige.More-Than-Allowed", "&cNu poti seta prestige-ul mai mare de 35!");
        this.addDefault("Bounty.Give-Executed", "&7Jucatorul &f%target%&7 are bounty-ul ridicat la &f%bounty%");
        this.addDefault("Bounty.Take-Executed", "&7Ai scazut bounty-ul jucatorului &f%target% &7la &f%bounty%");
        this.addDefault("Bounty.Set-Executed", "&7Bounty-ul lui &f%target% &7a fost setat la &f%bounty%");
        this.addDefault("Bounty.Not-Enough-Bounty", "&7Nu ai destul bounty.");
        this.addDefault("Death.ByPlayer", "&c&lMOARTE! &7de catre %killer%");
        this.addDefault("Death.ByUnknown", "&c&lMOARTE!");
        this.addDefault("Death.Title", "&c&lAI MURIT");
        this.addDefault("Death.SubTitle", "");
        this.addDefault("Death.ActionBar", "&7%victim% &a&lOMORAT!");
        this.addDefault("XP.RankupTitleLine1", "&b&lNivel avansat!");
        this.addDefault("XP.RankupTitleLine2", "&7[%oldlvl%&7] \u27a0 &7[%newlvl%&7]");
        this.addDefault("XP.Rankup-Message", "&b&lPIT LEVEL UP! %oldlvl% \u27a0 %newlvl%");
        this.addDefault("XP.Level.Max", "&bMAXIM");
        this.addDefault("Level.Not-Enough", "&cNu ai nivel suficient pentru a folosi acesta optiune!");
        this.addDefault("Level.Set", "&7Jucatorul &f%target% &7are acum &fNivel %level%");
        this.addDefault("Level.More-Than-Allowed", "&cNu poti seta nivelul mai mare de 120!");
        this.addDefault("Respawn.NotAvailable", "&cNu poti folosi /respawn aici!");
        this.addDefault("Respawn.InCombat", "&c&lASTEAPTA! &7Nu poti folosi /respawn cand te bati (&c%seconds%s &7ramase)");
        this.addDefault("Respawn.Cooldown", "&cPoti folosi /respawn odata la 10 secunde!");
        this.addDefault("Respawn.Not-Available-As-Beast", "&cYou cannot use /respawn while you're a beast!");
        this.addDefault("Reset.Executed", "&fContul lui %target% a fost sters!");
        this.addDefault("Reset.Deleted", "&fContul tau a fost sters. Reintra!");
        this.addDefault("Bounty.Tag", "&6&l%gold%g");
        this.addDefault("Bounty.Announce", "&6&lBOUNTY! &7de &6&l%bounty%g &7pe %tag% &7pentru kill streak mare");
        this.addDefault("Bounty.Claimed", "&6&lBOUNTY PRIMIT! %killer_tag% &7l-a omorat pe %killed_tag% &7pentru &6&l%gold%g");
        this.addDefault("CombatLog.NoAccess", "&7Nu poti folosi aceasta comanda in pvp.");
        this.addDefault("KillStreak.Message", "&c&lSTREAK! &7de &c%kills% &7ucideri facut de %player%");
        this.addDefault("SpawnNotSet", "&7Spawn-ul nu este setat!");
        this.addDefault("Soon", "&c&lIn curand");
        this.copyDefaults();
        this.save();
    }
}
