package ro.kmagic.files.npcs.stats;

import java.util.Arrays;
import ro.kmagic.libapi.utils.FileManager;

public class Stats extends FileManager {
    private static final String offensive = "OffensiveStats.";
    private static final String defensive = "DefensiveStats.";
    private static final String performance = "PerformanceStats.";
    private static final String miscellaneous = "MiscellaneousStats.";
    public static final String guiName = "GuiName";
    public static final String offensiveName = "OffensiveStats.Name";
    public static final String offensiveLore = "OffensiveStats.Lore";
    public static final String defensiveName = "DefensiveStats.Name";
    public static final String defensiveLore = "DefensiveStats.Lore";
    public static final String performanceName = "PerformanceStats.Name";
    public static final String performanceLore = "PerformanceStats.Lore";
    public static final String miscellaneousName = "MiscellaneousStats.Name";
    public static final String miscellaneousLore = "MiscellaneousStats.Lore";
    
    public Stats() {
        super("Stats", "Gui");
        this.addDefault("GuiName", "&8My pit stats");
        this.addDefault("OffensiveStats.Name", "&cOffensive Stats");
        this.addDefault("OffensiveStats.Lore", Arrays.asList("&7Kills: &a%kills%", "&7Assists: &a%assists%", "&7Sword Hits: &a%sword_hits%", "&7Arrows Shoot: &a%arrows_shoot%", "&7Arrow Hits: &a%arrow_hits%", " ", "&7Damage Dealt: &a%damage_dealt%", "&7Melee Damage Dealt: &a%melee_damage_dealt%", "&7Bow Damage Dealt: &a%bow_damage_dealt%", " ", "&7Highest Streak: &a%highest_streak%"));
        this.addDefault("DefensiveStats.Name", "&9Defensive Stats");
        this.addDefault("DefensiveStats.Lore", Arrays.asList("&7Deaths: &a%deaths%", " ", "&7Damage Taken: &a%damage_taken%", "&7Melee Damage Taken: &a%melee_damage_taken%", "&7Bow Damage Taken: &a%bow_damage_taken%"));
        this.addDefault("PerformanceStats.Name", "&ePerformance Stats");
        this.addDefault("PerformanceStats.Lore", Arrays.asList("&7XP: &a%xp%", " ", "&7K/D: &a%kd%", "&7K+A/D: &a%kad%", " ", "&7Damage dealt/taken: &a%damage_dealt_taken%", "&7Arrows hit/shot: &a%arrows_hit_shot%"));
        this.addDefault("MiscellaneousStats.Name", "&dMiscellaneous Stats");
        this.addDefault("MiscellaneousStats.Lore", Arrays.asList("&7Left Clicks: &a%left_clicks%", "&7Gold Earned: &a%gold_earned%", "&7Diamond Items Purchased: &a%diamond_items_purchased%", "&7Chat Messages: &a%chat_messages%", " ", "&7Blocks Placed: &a%blocks_placed%", "&7Blocks Broken: &a%blocks_broken%", " ", "&7Jumps into Pit: &a%jumps_into_pit%", "&7Launcher Launches: &a%launcher_launches%", " ", "&7Golden Apples Eaten: &a%golden_apples_eaten%", "&7Golden Heads Eaten: &a%golden_heads_eaten%", " ", "&7Lava Buckets Emptied: &a%lava_buckets_emptied%", "&7Fishing Rods Launched: &a%fishing_rods_launched%", " ", "&7Contracts Completed: &a%contracts_completed%"));
        this.copyDefaults();
        this.save();
    }
}
