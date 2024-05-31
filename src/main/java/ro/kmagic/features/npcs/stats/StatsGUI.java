package ro.kmagic.features.npcs.stats;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import ro.kmagic.libapi.gui.GUI;
import ro.kmagic.libapi.gui.GuiItem;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.files.npcs.stats.Stats;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StatsGUI extends Stats {
    private final DecimalFormat decimalFormat;
    private final String guiTitle;
    private final String offName;
    private final String defName;
    private final String perfName;
    private final String miscName;
    private final List<String> offLore;
    private final List<String> defLore;
    private final List<String> perfLore;
    private final List<String> miscLore;
    
    public StatsGUI() {
        this.decimalFormat = new DecimalFormat("0.00");
        this.guiTitle = this.getString("GuiName");
        this.offName = this.getString("OffensiveStats.Name");
        this.offLore = this.getStringList("OffensiveStats.Lore");
        this.defName = this.getString("DefensiveStats.Name");
        this.defLore = this.getStringList("DefensiveStats.Lore");
        this.perfName = this.getString("PerformanceStats.Name");
        this.perfLore = this.getStringList("PerformanceStats.Lore");
        this.miscName = this.getString("MiscellaneousStats.Name");
        this.miscLore = this.getStringList("MiscellaneousStats.Lore");
    }
    
    public void open(final Player player) {
        final GUI gui = new GUI(player, 27, this.guiTitle);
        gui.addItem(new GuiItem(this.offensive(player), 10));
        gui.addItem(new GuiItem(this.defensive(player), 12));
        gui.addItem(new GuiItem(this.performance(player), 14));
        gui.addItem(new GuiItem(this.miscellaneous(player), 16));
        gui.open();
    }
    
    private ItemStack offensive(final Player player) {
        final ArrayList<String> lore = new ArrayList<String>();
        for (String s : this.offLore) {
            lore.add(s.replace("%kills%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.KILLS))).replace("%assists%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.ASSISTS))).replace("%sword_hits%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.SWORD_HITS))).replace("%arrows_shoot%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.ARROWS_SHOT))).replace("%arrow_hits%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.ARROW_HITS))).replace("%damage_dealt%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.MELEE_DAMAGE_DEALT) + PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.BOW_DAMAGE_DEALT))).replace("%melee_damage_dealt%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.MELEE_DAMAGE_DEALT))).replace("%bow_damage_dealt%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.BOW_DAMAGE_DEALT))).replace("%highest_streak%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.HIGHEST_STREAK))));
        }
        return Materials.IRON_SWORD.getItem().setDisplayName(this.offName).setLore(lore).flag().add(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON).build();
    }
    
    private ItemStack defensive(final Player player) {
        final ArrayList<String> lore = new ArrayList<String>();
        for (String s : this.defLore) {
            lore.add(s.replace("%deaths%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.DEATHS))).replace("%damage_taken%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.MELEE_DAMAGE_TAKEN) + PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.BOW_DAMAGE_TAKEN))).replace("%melee_damage_taken%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.MELEE_DAMAGE_TAKEN))).replace("%bow_damage_taken%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.BOW_DAMAGE_TAKEN))));
        }
        return Materials.IRON_CHESTPLATE.getItem().setDisplayName(this.defName).setLore(lore).flag().add(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON).build();
    }
    
    private ItemStack performance(final Player player) {
        final ArrayList<String> lore = new ArrayList<String>();
        for (String s : this.perfLore) {
            lore.add(s.replace("%xp%", String.valueOf(player.getTotalExperience())).replace("%kd%", this.getKillDeathRatio(player)).replace("%kad%", this.getKillAssistDeathRatio(player)).replace("%damage_dealt_taken%", this.getDamageDealtTakenRatio(player)).replace("%arrows_hit_shot%", this.getArrowShotHitRatio(player)));
        }
        return Materials.WHEAT.getItem().setDisplayName(this.perfName).setLore(lore).flag().add(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON).build();
    }
    
    private ItemStack miscellaneous(final Player player) {
        final ArrayList<String> lore = new ArrayList<String>();
        for (String s : this.miscLore) {
            lore.add(s.replace("%left_clicks%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.LEFT_CLICKS))).replace("%gold_earned%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.GOLD_EARNED))).replace("%diamond_items_purchased%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.DIAMOND_ITEMS_PURCHASED))).replace("%chat_messages%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.CHAT_MESSAGES))).replace("%blocks_placed%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.BLOCKS_PLACED))).replace("%blocks_broken%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.BLOCKS_BROKEN))).replace("%jumps_into_pit%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.JUMPS_INTO_PIT))).replace("%launcher_launches%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.LAUNCHES))).replace("%golden_apples_eaten%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.GOLDEN_APPLES_EATEN))).replace("%golden_heads_eaten%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.GOLDEN_HEADS_EATEN))).replace("%lava_buckets_emptied%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.LAVA_BUCKETS_EMPTIED))).replace("%fishing_rods_launched%", String.valueOf(PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.FISHING_RODS_LAUNCHED) / 2)));
        }
        return Materials.OBSIDIAN.getItem().setDisplayName(this.miscName).setLore(lore).flag().add(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON).build();
    }
    
    private String getKillDeathRatio(final Player player) {
        final int value = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.KILLS);
        final int value2 = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.DEATHS);
        if (value >= 1 && value2 >= 1) {
            return this.decimalFormat.format(value / value2);
        }
        return "0";
    }
    
    private String getKillAssistDeathRatio(final Player player) {
        final double n = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.KILLS);
        final double n2 = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.ASSISTS);
        final double n3 = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.DEATHS);
        if (n >= 1.0 && n3 >= 1.0) {
            return this.decimalFormat.format((n + n2) / n3);
        }
        return "0";
    }
    
    private String getDamageDealtTakenRatio(final Player player) {
        final double n = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.BOW_DAMAGE_DEALT) + PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.MELEE_DAMAGE_DEALT);
        final double n2 = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.BOW_DAMAGE_TAKEN) + PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.MELEE_DAMAGE_TAKEN);
        if (n >= 1.0 && n2 >= 1.0) {
            return this.decimalFormat.format(n / n2);
        }
        return "0";
    }
    
    private String getArrowShotHitRatio(final Player player) {
        final double n = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.ARROWS_SHOT);
        final double n2 = PlayerStats.get(player).get(ro.kmagic.features.stats.Stats.StatsType.ARROW_HITS);
        if (n >= 1.0 && n2 >= 1.0) {
            return this.decimalFormat.format(n / n2);
        }
        return "0";
    }
}
