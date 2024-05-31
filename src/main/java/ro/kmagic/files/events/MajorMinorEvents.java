package ro.kmagic.files.events;

import ro.kmagic.features.events.PitEventManager;
import java.util.Iterator;
import java.util.ArrayList;
import ro.kmagic.Main;
import org.bukkit.ChatColor;
import java.util.List;
import org.bukkit.entity.Player;
import java.util.Arrays;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.utils.FileManager;

public class MajorMinorEvents extends FileManager {
    private static final String event = "Event.";
    private static final String minor = "Minor.";
    private static final String x2reward = "X2_REWARD.";
    private static final String koth = "KOTH.";
    private static final String everyone_gets_a_bounty = "EVERYONE_GETS_A_BOUNTY.";
    private static final String carePackage = "CARE_PACKAGE.";
    private static final String quickMaths = "QUICK_MATHS.";
    private static final String ragePit = "RAGE_PIT.";
    private static final String teamDeathmatch = "TEAM_DEATHMATCH.";
    private static final String theBeast = "THE_BEAST.";
    private static final String enabled = "Enabled";
    private static final String durationSeconds = "DurationSeconds";
    private static final String requiredPlayers = "RequiredPlayers";
    private static final String description = "Description";
    private static final String announce = "Announce";
    private static final String misc = "Misc.";
    private static final String major = "Major.";
    private static final String rewards = "Rewards.";
    private static final String gold = "Gold.";
    private static final String top = "Top.";
    private static final String killGoal = "KillGoal.";
    private static final String potato = "Potato.";
    private static final String cooldown = "Cooldown.";
    private static final String bonus = "Bonus.";
    private static final String winner = "Winner.";
    private static final String state = "State";
    private static final String looser = "Looser.";
    private static final String over = "Over";
    private static final String success = "Success.";
    private static final String bossBar = "Boss-Bar.";
    private static final String ending = "Ending-In";
    public static final String eventSecondsBeforeStart = "Event.SecondsBeforeStart";
    public static final String eventBossBarEnabled = "Event.Boss-Bar-Enabled";
    public static final String eventMinorX2RewardEnabled = "Event.Minor.X2_REWARD.Enabled";
    public static final String eventMinorX2RewardDurationSeconds = "Event.Minor.X2_REWARD.DurationSeconds";
    public static final String eventMinorX2RewardRequiredPlayers = "Event.Minor.X2_REWARD.RequiredPlayers";
    public static final String eventMinorX2RewardName = "Event.Minor.X2_REWARD.Name";
    public static final String eventMinorX2RewardDescription = "Event.Minor.X2_REWARD.Description";
    public static final String eventMinorKothEnabled = "Event.Minor.KOTH.Enabled";
    public static final String eventMinorKothDurationSeconds = "Event.Minor.KOTH.DurationSeconds";
    public static final String eventMinorKothRequiredPlayers = "Event.Minor.KOTH.RequiredPlayers";
    public static final String eventMinorKothName = "Event.Minor.KOTH.Name";
    public static final String eventMinorKothDescription = "Event.Minor.KOTH.Description";
    public static final String eventMinorEveryoneGetsABountyEnabled = "Event.Minor.EVERYONE_GETS_A_BOUNTY.Enabled";
    public static final String eventMinorEveryoneGetsABountyRequiredPlayers = "Event.Minor.EVERYONE_GETS_A_BOUNTY.RequiredPlayers";
    public static final String eventMinorEveryoneGetsABountyName = "Event.Minor.EVERYONE_GETS_A_BOUNTY.Name";
    public static final String eventMinorEveryoneGetsABountyDescription = "Event.Minor.EVERYONE_GETS_A_BOUNTY.Description";
    public static final String eventMinorCarePackageEnabled = "Event.Minor.CARE_PACKAGE.Enabled";
    public static final String eventMinorCarePackageDurationSeconds = "Event.Minor.CARE_PACKAGE.DurationSeconds";
    public static final String eventMinorCarePackageRequiredPlayers = "Event.Minor.CARE_PACKAGE.RequiredPlayers";
    public static final String eventMinorCarePackageChestName = "Event.Minor.CARE_PACKAGE.ChestName";
    public static final String eventMinorCarePackageClicksToBeOpened = "Event.Minor.CARE_PACKAGE.ClicksToBeOpened";
    public static final String eventMinorCarePackageName = "Event.Minor.CARE_PACKAGE.Name";
    public static final String eventMinorCarePackageDescription = "Event.Minor.CARE_PACKAGE.Description";
    public static final String eventMinorQuickMathsEnabled = "Event.Minor.QUICK_MATHS.Enabled";
    public static final String eventMinorQuickMathsDurationSeconds = "Event.Minor.QUICK_MATHS.DurationSeconds";
    public static final String eventMinorQuickMathsRequiredPlayers = "Event.Minor.QUICK_MATHS.RequiredPlayers";
    public static final String eventMinorQuickMathsRewardsGold = "Event.Minor.QUICK_MATHS.Rewards.Gold";
    public static final String eventMinorQuickMathsRewardsXP = "Event.Minor.QUICK_MATHS.Rewards.XP";
    public static final String eventMinorQuickMathsAnnounce = "Event.Minor.QUICK_MATHS.Announce";
    public static final String eventMinorQuickMathsAnswered = "Event.Minor.QUICK_MATHS.Answered";
    public static final String eventMinorQuickMathsOver = "Event.Minor.QUICK_MATHS.Over";
    public static final String eventMinorQuickMathsOperations = "Event.Minor.QUICK_MATHS.Operations";
    public static final String eventMinorQuickMathsName = "Event.Minor.QUICK_MATHS.Name";
    public static final String eventMinorQuickMathsDescription = "Event.Minor.QUICK_MATHS.Description";
    public static final String eventMinorMiscBossBarEnding = "Event.Minor.Misc.Boss-Bar.Ending-In";
    public static final String eventMinorMiscAnnounce = "Event.Minor.Misc.Announce";
    public static final String eventMinorMiscStarted = "Event.Minor.Misc.Started";
    public static final String eventMinorMiscEnded = "Event.Minor.Misc.Ended";
    public static final String eventMajorRagePitEnabled = "Event.Major.RAGE_PIT.Enabled";
    public static final String eventMajorRagePitDurationSeconds = "Event.Major.RAGE_PIT.DurationSeconds";
    public static final String eventMajorRagePitRequiredPlayers = "Event.Major.RAGE_PIT.RequiredPlayers";
    public static final String eventMajorRagePitName = "Event.Major.RAGE_PIT.Name";
    public static final String eventMajorRagePitDescription = "Event.Major.RAGE_PIT.Description";
    public static final String eventMajorRagePitRewardGoldTop3 = "Event.Major.RAGE_PIT.Rewards.Gold.Top.3";
    public static final String eventMajorRagePitRewardGoldTop4_20 = "Event.Major.RAGE_PIT.Rewards.Gold.Top.4-20";
    public static final String eventMajorRagePitRewardGoldTopRest = "Event.Major.RAGE_PIT.Rewards.Gold.Top.Rest";
    public static final String eventMajorRagePitKillGoalAmount = "Event.Major.RAGE_PIT.KillGoal.Amount";
    public static final String eventMajorRagePitKillGoalBonus = "Event.Major.RAGE_PIT.KillGoal.Bonus";
    public static final String eventMajorRagePitKillGoalSuccess = "Event.Major.RAGE_PIT.KillGoal.Success";
    public static final String eventMajorRagePitKillGoalFail = "Event.Major.RAGE_PIT.KillGoal.Fail";
    public static final String eventMajorRagePitPotatoName = "Event.Major.RAGE_PIT.Potato.Name";
    public static final String eventMajorRagePitPotatoCooldownSeconds = "Event.Major.RAGE_PIT.Potato.Cooldown.Seconds";
    public static final String eventMajorRagePitPotatoCooldownMessage = "Event.Major.RAGE_PIT.Potato.Cooldown.Message";
    public static final String eventMajorRagePitPotatoRegeneration = "Event.Major.RAGE_PIT.Potato.Regeneration";
    public static final String eventMajorRagePitPotatoAbsorption = "Event.Major.RAGE_PIT.Potato.Absorption";
    public static final String eventMajorRagePitOver = "Event.Major.RAGE_PIT.Over";
    public static final String eventMajorTeamDeathmatchEnabled = "Event.Major.TEAM_DEATHMATCH.Enabled";
    public static final String eventMajorTeamDeathmatchDurationSeconds = "Event.Major.TEAM_DEATHMATCH.DurationSeconds";
    public static final String eventMajorTeamDeathmatchRequiredPlayers = "Event.Major.TEAM_DEATHMATCH.RequiredPlayers";
    public static final String eventMajorTeamDeathmatchName = "Event.Major.TEAM_DEATHMATCH.Name";
    public static final String eventMajorTeamDeathmatchDescription = "Event.Major.TEAM_DEATHMATCH.Description";
    public static final String eventMajorTeamDeathmatchTeamBlue = "Event.Major.TEAM_DEATHMATCH.Team.Blue";
    public static final String eventMajorTeamDeathmatchTeamRed = "Event.Major.TEAM_DEATHMATCH.Team.Red";
    public static final String eventMajorTeamDeathmatchRewardsGoldTop3 = "Event.Major.TEAM_DEATHMATCH.Rewards.Gold.Top.3";
    public static final String eventMajorTeamDeathmatchRewardsGoldTop4_20 = "Event.Major.TEAM_DEATHMATCH.Rewards.Gold.Top.4-20";
    public static final String eventMajorTeamDeathmatchRewardsGoldRest = "Event.Major.TEAM_DEATHMATCH.Rewards.Gold.Top.Rest";
    public static final String eventMajorTeamDeathmatchBonusWinnerState = "Event.Major.TEAM_DEATHMATCH.Bonus.Winner.State";
    public static final String eventMajorTeamDeathmatchBonusWinnerGold = "Event.Major.TEAM_DEATHMATCH.Bonus.Winner.Gold";
    public static final String eventMajorTeamDeathmatchBonusLooserState = "Event.Major.TEAM_DEATHMATCH.Bonus.Looser.State";
    public static final String eventMajorTeamDeathmatchBonusLooserGold = "Event.Major.TEAM_DEATHMATCH.Bonus.Looser.Gold";
    public static final String eventMajorTeamDeathmatchOver = "Event.Major.TEAM_DEATHMATCH.Over";
    public static final String eventMajorTheBeastEnabled = "Event.Major.THE_BEAST.Enabled";
    public static final String eventMajorTheBeastDurationSeconds = "Event.Major.THE_BEAST.DurationSeconds";
    public static final String eventMajorTheBeastRequiredPlayers = "Event.Major.THE_BEAST.RequiredPlayers";
    public static final String eventMajorTheBeastName = "Event.Major.THE_BEAST.Name";
    public static final String eventMajorTheBeastDescription = "Event.Major.THE_BEAST.Description";
    public static final String eventMajorTheBeastAllPlayersInSpawn = "Event.Major.THE_BEAST.All-Players-In-Spawn";
    public static final String eventMajorTheBeastTag = "Event.Major.THE_BEAST.Tag";
    public static final String eventMajorTheBeastRewardsGoldTop3 = "Event.Major.THE_BEAST.Rewards.Gold.Top.3";
    public static final String eventMajorTheBeastRewardsGoldTop4_20 = "Event.Major.THE_BEAST.Rewards.Gold.Top.4-20";
    public static final String eventMajorTheBeastRewardsGoldTopRest = "Event.Major.THE_BEAST.Rewards.Gold.Top.Rest";
    public static final String eventMajorTheBeastBonusSuccessState = "Event.Major.THE_BEAST.Bonus.Success.State";
    public static final String eventMajorTheBeastBonusSuccessGold = "Event.Major.THE_BEAST.Bonus.Success.Gold";
    public static final String eventMajorTheBeastBonusFailState = "Event.Major.THE_BEAST.Bonus.Fail.State";
    public static final String eventMajorTheBeastOver = "Event.Major.THE_BEAST.Over";
    public static final String eventMajorMiscBossBarEnding = "Event.Major.Misc.Boss-Bar.Ending-In";
    public static final String eventMajorMiscAnnounce = "Event.Major.Misc.Announce";
    public static final String eventMajorMiscStarted = "Event.Major.Misc.Started";
    public static final String eventMajorMiscEnded = "Event.Major.Misc.Ended";
    
    public MajorMinorEvents() {
        super("Events", "Events");
        this.addDefault("Event.SecondsBeforeStart", 120);
        this.addDefault("Event.Boss-Bar-Enabled", !API.getVersionSupport().contains(8));
        this.addDefault("Event.Minor.X2_REWARD.Enabled", true);
        this.addDefault("Event.Minor.X2_REWARD.DurationSeconds", 240);
        this.addDefault("Event.Minor.X2_REWARD.RequiredPlayers", 2);
        this.addDefault("Event.Minor.X2_REWARD.Name", "&e&lX2 Reward");
        this.addDefault("Event.Minor.X2_REWARD.Description", "&e2X &bXP&d/&6gold&e!");
        this.addDefault("Event.Minor.KOTH.Enabled", true);
        this.addDefault("Event.Minor.KOTH.DurationSeconds", 240);
        this.addDefault("Event.Minor.KOTH.RequiredPlayers", 2);
        this.addDefault("Event.Minor.KOTH.Name", "&e&lKing of The Hill");
        this.addDefault("Event.Minor.KOTH.Description", "&bKing of the Hill");
        this.addDefault("Event.Minor.EVERYONE_GETS_A_BOUNTY.Enabled", true);
        this.addDefault("Event.Minor.EVERYONE_GETS_A_BOUNTY.RequiredPlayers", 2);
        this.addDefault("Event.Minor.EVERYONE_GETS_A_BOUNTY.Name", "&e&lEveryone gets a Bounty");
        this.addDefault("Event.Minor.EVERYONE_GETS_A_BOUNTY.Description", "&6Everyone gets a Bounty");
        this.addDefault("Event.Minor.CARE_PACKAGE.Enabled", true);
        this.addDefault("Event.Minor.CARE_PACKAGE.DurationSeconds", 120);
        this.addDefault("Event.Minor.CARE_PACKAGE.RequiredPlayers", 2);
        this.addDefault("Event.Minor.CARE_PACKAGE.ChestName", "&7Care Package");
        this.addDefault("Event.Minor.CARE_PACKAGE.ClicksToBeOpened", 200);
        this.addDefault("Event.Minor.CARE_PACKAGE.Name", "&e&lCare Package");
        this.addDefault("Event.Minor.CARE_PACKAGE.Description", "&2Care Package");
        this.addDefault("Event.Minor.QUICK_MATHS.Enabled", true);
        this.addDefault("Event.Minor.QUICK_MATHS.DurationSeconds", 30);
        this.addDefault("Event.Minor.QUICK_MATHS.RequiredPlayers", 2);
        this.addDefault("Event.Minor.QUICK_MATHS.Rewards.Gold", 500);
        this.addDefault("Event.Minor.QUICK_MATHS.Rewards.XP", 250);
        this.addDefault("Event.Minor.QUICK_MATHS.Announce", Arrays.asList("&d&lQUICK MATHS! &7First 5 players to answer gain &b+250XP &6+500g", "&d&lQUICK MATHS! &7Solve: &e%number1%%symbol%%number2%"));
        this.addDefault("Event.Minor.QUICK_MATHS.Answered", "&d&lQUICK MATHS! &e#%top% %player_tag% &7answered");
        this.addDefault("Event.Minor.QUICK_MATHS.Over", "&d&lQUICK MATHS OVER! &e%number1%%symbol%%number2% &7= &e%answer%");
        this.addDefault("Event.Minor.QUICK_MATHS.Operations", Arrays.asList("ADD 5 9", "ADD 104 850", "SUBTRACT 100 89", "SUBTRACT 982 156", "MULTIPLY 5 10", "MULTIPLY 9 5", "DIVIDE 10 2", "DIVIDE 49 7"));
        this.addDefault("Event.Minor.QUICK_MATHS.Name", "&e&lQuick Maths");
        this.addDefault("Event.Minor.QUICK_MATHS.Description", "&eQuick Maths");
        this.addDefault("Event.Minor.Misc.Boss-Bar.Ending-In", "&5&lMINOR EVENT! %eventName%! &7Ending in &a%time%");
        this.addDefault("Event.Minor.Misc.Announce", Arrays.asList("&7&m---------------------------", "&d&lMINOR EVENT! &e&l%eventName%", "&7Location: &6%eventLocation%", "&7Starting in: &2%minutes% Minutes", "&7&m---------------------------"));
        this.addDefault("Event.Minor.Misc.Started", Arrays.asList("&7&m---------------------------", "&a&lMINOR EVENT STARTED! &e&l%eventName%", "&7Location: &6%eventLocation%", "&7Duration: &2%minutes% Minutes", "&7&m---------------------------"));
        this.addDefault("Event.Minor.Misc.Ended", Arrays.asList("&7&m---------------------------", "&c&lMINOR EVENT ENDED! &e&l%eventName%", "&7&m---------------------------"));
        this.addDefault("Event.Major.RAGE_PIT.Enabled", true);
        this.addDefault("Event.Major.RAGE_PIT.DurationSeconds", 300);
        this.addDefault("Event.Major.RAGE_PIT.RequiredPlayers", 2);
        this.addDefault("Event.Major.RAGE_PIT.Name", "&c&lRAGE PIT");
        this.addDefault("Event.Major.RAGE_PIT.Description", "&c&lRAGE PIT");
        this.addDefault("Event.Major.RAGE_PIT.Rewards.Gold.Top.3", 2000);
        this.addDefault("Event.Major.RAGE_PIT.Rewards.Gold.Top.4-20", 500);
        this.addDefault("Event.Major.RAGE_PIT.Rewards.Gold.Top.Rest", 100);
        this.addDefault("Event.Major.RAGE_PIT.KillGoal.Amount", 600);
        this.addDefault("Event.Major.RAGE_PIT.KillGoal.Bonus", 500);
        this.addDefault("Event.Major.RAGE_PIT.KillGoal.Success", "&a&lSuccess");
        this.addDefault("Event.Major.RAGE_PIT.KillGoal.Fail", "&c&lFail");
        this.addDefault("Event.Major.RAGE_PIT.Potato.Name", "&ePotato");
        this.addDefault("Event.Major.RAGE_PIT.Potato.Cooldown.Seconds", 4);
        this.addDefault("Event.Major.RAGE_PIT.Potato.Cooldown.Message", "&7You can't use &ePotato &7now");
        this.addDefault("Event.Major.RAGE_PIT.Potato.Regeneration", 4);
        this.addDefault("Event.Major.RAGE_PIT.Potato.Absorption", 3);
        this.addDefault("Event.Major.RAGE_PIT.Over", Arrays.asList("&6&m---------------------------", "&6&lPIT EVENT ENDED: &c&lRAGE PIT&6&l!", "&6&lBonus for all: %state%", "&6&lTop players:", " &e&l#1 %top1_player% &ewith &c%top1_dealt%\u2764 dealt", " &e&l#2 %top2_player% &ewith &c%top2_dealt%\u2764 dealt", " &e&l#3 %top3_player% &ewith &c%top3_dealt%\u2764 dealt", "&6&m---------------------------"));
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Enabled", true);
        this.addDefault("Event.Major.TEAM_DEATHMATCH.DurationSeconds", 300);
        this.addDefault("Event.Major.TEAM_DEATHMATCH.RequiredPlayers", 2);
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Name", "&9&lTEAM DEATHMATCH");
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Description", "&9&lTEAM DEATHMATCH");
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Team.Blue", "&9&lBLUE");
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Team.Red", "&c&lRED");
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Rewards.Gold.Top.3", 2000);
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Rewards.Gold.Top.4-20", 500);
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Rewards.Gold.Top.Rest", 100);
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Bonus.Winner.State", "&a&lSUCCESS! &7you were on the winning team");
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Bonus.Winner.Gold", 1000);
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Bonus.Looser.State", "&c&lFAIL! &7you were on the losing team");
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Bonus.Looser.Gold", 150);
        this.addDefault("Event.Major.TEAM_DEATHMATCH.Over", Arrays.asList("&6&m---------------------------", "&6&lPIT EVENT ENDED: &5&lTEAM DEATHMATCH&6&l!", "&6&lWinning team: %team% &ewith %kills% kills", "&6&lTeam Bonus: %state%", "&6&lTop players:", " &e&l#1 %top1_player% &ewith &c%top1_ka% &cKills + Assists", " &e&l#2 %top2_player% &ewith &c%top2_ka% &cKills + Assists", " &e&l#3 %top3_player% &ewith &c%top3_ka% &cKills + Assists", "&6&m---------------------------"));
        this.addDefault("Event.Major.THE_BEAST.Enabled", true);
        this.addDefault("Event.Major.THE_BEAST.DurationSeconds", 300);
        this.addDefault("Event.Major.THE_BEAST.RequiredPlayers", 2);
        this.addDefault("Event.Major.THE_BEAST.Name", "&a&lTHE BEAST");
        this.addDefault("Event.Major.THE_BEAST.Description", "&a&lTHE BEAST");
        this.addDefault("Event.Major.THE_BEAST.All-Players-In-Spawn", "&cAll players were in spawn! Event ended!");
        this.addDefault("Event.Major.THE_BEAST.Tag", "&a&lBEAST");
        this.addDefault("Event.Major.THE_BEAST.Rewards.Gold.Top.3", 2000);
        this.addDefault("Event.Major.THE_BEAST.Rewards.Gold.Top.4-20", 500);
        this.addDefault("Event.Major.THE_BEAST.Rewards.Gold.Top.Rest", 100);
        this.addDefault("Event.Major.THE_BEAST.Bonus.Success.State", "&a&lSUCCESS! &7the beast didn't survive over 60 seconds");
        this.addDefault("Event.Major.THE_BEAST.Bonus.Success.Gold", 1000);
        this.addDefault("Event.Major.THE_BEAST.Bonus.Fail.State", "&c&lFAIL! &7the beast survived over 60 seconds");
        this.addDefault("Event.Major.THE_BEAST.Over", Arrays.asList("&6&m---------------------------", "&6&lPIT EVENT ENDED: &a&lBEAST&6&l!", "&6&lBonus for all: %state%", "&6&lTop players:", " &e&l#1 %top1_player% &ewith &a%top1_bk% &aBeast Kills", " &e&l#2 %top2_player% &ewith &a%top2_bk% &aBeast Kills", " &e&l#3 %top3_player% &ewith &a%top3_bk% &aBeast Kills", "&6&m---------------------------"));
        this.addDefault("Event.Major.Misc.Boss-Bar.Ending-In", "&5&lMAJOR EVENT! %eventName%! &7Ending in &a%time%");
        this.addDefault("Event.Major.Misc.Announce", Arrays.asList("&6&m---------------------------", "&6&lMAJOR EVENT! &e&l%eventName%", "&7Starting in: &2%minutes% Minutes", "&6&m---------------------------"));
        this.addDefault("Event.Major.Misc.Started", Arrays.asList("&6&m---------------------------", "&6&lMAJOR EVENT &a&lSTARTED! &e&l%eventName%", "&7Duration: &2%minutes% Minutes", "&6&m---------------------------"));
        this.addDefault("Event.Major.Misc.Ended", Arrays.asList("&6&m---------------------------", "&6&lMAJOR EVENT &c&lENDED! &e&l%eventName%", "&6&m---------------------------"));
        this.copyDefaults();
        this.save();
    }
    
    public List<String> getRagePitEndMessage(final boolean b, final Player player, final Player player2, final Player player3, final String replacement, final String replacement2, final String replacement3) {
        String replacement4 = this.getString("Event.Major.RAGE_PIT.KillGoal.Fail");
        if (b) {
            replacement4 = this.getString("Event.Major.RAGE_PIT.KillGoal.Success");
        }
        String replacement5;
        String string2;
        String string = string2 = (replacement5 = ChatColor.translateAlternateColorCodes((char)38, "&c&l"));
        if (player != null) {
            replacement5 = Main.getXpTag().get(player) + " " + player.getDisplayName();
        }
        if (player2 != null) {
            string = Main.getXpTag().get(player2) + " " + player2.getDisplayName();
        }
        if (player3 != null) {
            string2 = Main.getXpTag().get(player3) + " " + player3.getDisplayName();
        }
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Major.RAGE_PIT.Over").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%state%", replacement4).replace("%top1_player%", replacement5).replace("%top2_player%", string).replace("%top3_player%", string2).replace("%top1_dealt%", replacement).replace("%top2_dealt%", replacement2).replace("%top3_dealt%", replacement3));
        }
        return list;
    }
    
    public List<String> getTeamDeathmatchEndMessage(final String replacement, final String replacement2, final boolean b, final Player player, final Player player2, final Player player3, final String replacement3, final String replacement4, final String replacement5) {
        String replacement6 = this.getString("Event.Major.TEAM_DEATHMATCH.Bonus.Looser.State");
        if (b) {
            replacement6 = this.getString("Event.Major.TEAM_DEATHMATCH.Bonus.Winner.State");
        }
        String replacement7;
        String string2;
        String string = string2 = (replacement7 = ChatColor.translateAlternateColorCodes((char)38, "&c&l"));
        if (player != null) {
            replacement7 = Main.getXpTag().get(player) + " " + player.getDisplayName();
        }
        if (player2 != null) {
            string = Main.getXpTag().get(player2) + " " + player2.getDisplayName();
        }
        if (player3 != null) {
            string2 = Main.getXpTag().get(player3) + " " + player3.getDisplayName();
        }
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Major.TEAM_DEATHMATCH.Over").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%team%", replacement).replace("%kills%", replacement2).replace("%state%", replacement6).replace("%top1_player%", replacement7).replace("%top2_player%", string).replace("%top3_player%", string2).replace("%top1_ka%", replacement3).replace("%top2_ka%", replacement4).replace("%top3_ka%", replacement5));
        }
        return list;
    }
    
    public List<String> getTheBeastEndMessage(final boolean b, final Player player, final Player player2, final Player player3, final String replacement, final String replacement2, final String replacement3) {
        String replacement4 = this.getString("Event.Major.THE_BEAST.Bonus.Fail.State");
        if (b) {
            replacement4 = this.getString("Event.Major.THE_BEAST.Bonus.Success.State");
        }
        String replacement5;
        String string2;
        String string = string2 = (replacement5 = ChatColor.translateAlternateColorCodes((char)38, "&c&l"));
        if (player != null) {
            replacement5 = Main.getXpTag().get(player) + " " + player.getDisplayName();
        }
        if (player2 != null) {
            string = Main.getXpTag().get(player2) + " " + player2.getDisplayName();
        }
        if (player3 != null) {
            string2 = Main.getXpTag().get(player3) + " " + player3.getDisplayName();
        }
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Major.THE_BEAST.Over").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%state%", replacement4).replace("%top1_player%", replacement5).replace("%top2_player%", string).replace("%top3_player%", string2).replace("%top1_bk%", replacement).replace("%top2_bk%", replacement2).replace("%top3_bk%", replacement3));
        }
        return list;
    }
    
    public int getEndReward(final String str, final String str2) {
        return this.getInt("Event.Major." + str + "." + "Bonus." + str2 + ".Gold");
    }
    
    public int getTDMBonus(final String str) {
        return this.getInt("Event.Major.TEAM_DEATHMATCH.Bonus." + str);
    }
    
    public int getReward(final String str, final String str2) {
        return this.getInt("Event.Major." + str + "." + "Rewards." + "Gold." + "Top." + str2);
    }
    
    public int getRPKillGoal(final String str) {
        return this.getInt("Event.Major.RAGE_PIT.KillGoal." + str);
    }
    
    public int getPotatoEnchantmentLevel(final String str) {
        return this.getInt("Event.Major.RAGE_PIT.Potato." + str);
    }
    
    public List<String> getQMAnnounce(final int i, final String replacement, final int j) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Minor.QUICK_MATHS.Announce").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%number1%", String.valueOf(i)).replace("%symbol%", replacement).replace("%number2%", String.valueOf(j)));
        }
        return list;
    }
    
    public String getQMAnswered(final int i, final Player player) {
        return this.getString("Event.Minor.QUICK_MATHS.Answered").replace("%top%", String.valueOf(i)).replace("%player_tag%", Main.getXpTag().get(player) + " " + player.getDisplayName());
    }
    
    public String getQMOver(final int i, final String replacement, final int j, final int k) {
        return this.getString("Event.Minor.QUICK_MATHS.Over").replace("%number1%", String.valueOf(i)).replace("%symbol%", replacement).replace("%number2%", String.valueOf(j)).replace("%answer%", String.valueOf(k));
    }
    
    public int getMinorDuration(final String str) {
        return this.getInt("Event.Minor." + str + "." + "DurationSeconds");
    }
    
    public int getMinorRequiredPlayers(final String str) {
        return this.getInt("Event.Minor." + str + "." + "RequiredPlayers");
    }
    
    public String getMinorDescription(final String str) {
        return this.getString("Event.Minor." + str + "." + "Description");
    }
    
    public boolean getMinorEventEnabled(final String str) {
        return this.getBoolean("Event.Minor." + str + "." + "Enabled");
    }
    
    public int getMajorDuration(final String str) {
        return this.getInt("Event.Major." + str + "." + "DurationSeconds");
    }
    
    public int getMajorRequiredPlayers(final String str) {
        return this.getInt("Event.Major." + str + "." + "RequiredPlayers");
    }
    
    public String getMajorDescription(final String str) {
        return this.getString("Event.Major." + str + "." + "Description");
    }
    
    public boolean getMajorEventEnabled(final String str) {
        return this.getBoolean("Event.Major." + str + "." + "Enabled");
    }
    
    public List<String> getMinorAnnounceMessage(final String replacement, final String replacement2, final String replacement3) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Minor.Misc.Announce").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%eventName%", replacement).replace("%eventLocation%", replacement2).replace("%minutes%", replacement3));
        }
        return list;
    }
    
    public List<String> getMinorStartMessage(final String replacement, final String replacement2, final String replacement3) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Minor.Misc.Started").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%eventName%", replacement).replace("%eventLocation%", replacement2).replace("%minutes%", replacement3));
        }
        return list;
    }
    
    public List<String> getMinorEndMessage(final String replacement) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Minor.Misc.Ended").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%eventName%", replacement));
        }
        return list;
    }
    
    public List<String> getMajorAnnounceMessage(final String replacement, final String replacement2, final String replacement3) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Major.Misc.Announce").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%eventName%", replacement).replace("%eventLocation%", replacement2).replace("%minutes%", replacement3));
        }
        return list;
    }
    
    public List<String> getMajorStartMessage(final String replacement, final String replacement2, final String replacement3) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Major.Misc.Started").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%eventName%", replacement).replace("%eventLocation%", replacement2).replace("%minutes%", replacement3));
        }
        return list;
    }
    
    public List<String> getMajorEndMessage(final String replacement) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.getStringList("Event.Major.Misc.Ended").iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().replace("%eventName%", replacement));
        }
        return list;
    }
    
    public String getEventName(final PitEventManager.PitEventType pitEventType) {
        switch (pitEventType) {
            case X2_REWARD: {
                return this.getString("Event.Minor.X2_REWARD.Name");
            }
            case KOTH: {
                return this.getString("Event.Minor.KOTH.Name");
            }
            case QUICK_MATHS: {
                return this.getString("Event.Minor.QUICK_MATHS.Name");
            }
            case CARE_PACKAGE: {
                return this.getString("Event.Minor.CARE_PACKAGE.Name");
            }
            case EVERYONE_GETS_A_BOUNTY: {
                return this.getString("Event.Minor.EVERYONE_GETS_A_BOUNTY.Name");
            }
            case RAGE_PIT: {
                return this.getString("Event.Major.RAGE_PIT.Name");
            }
            case THE_BEAST: {
                return this.getString("Event.Major.THE_BEAST.Name");
            }
            case TEAM_DEATHMATCH: {
                return this.getString("Event.Major.TEAM_DEATHMATCH.Name");
            }
            default: {
                return null;
            }
        }
    }
    
    public boolean isOperationSet() {
        return this.contains("Event.Minor.QUICK_MATHS.Operations");
    }
}
