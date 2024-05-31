package ro.kmagic.files.perk.perks;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.utils.FileManager;

public class GoldenHeads extends FileManager {
    private static final String cooldown = "Cooldown.";
    public static final String cooldownMessage = "Cooldown.Message";
    public static final String cooldownSeconds = "Cooldown.Seconds";
    public static final String itemStackLimit = "Item.Stack-Limit";
    public static final String perkItemLore = "Perk.ItemLore";
    
    public GoldenHeads() {
        super("GoldenHeads", "Perks");
        this.addDefault("Cooldown.Message", "&cGolden Heads can be used once %seconds% seconds!");
        this.addDefault("Cooldown.Seconds", 8);
        this.addDefault("Item.Stack-Limit", 2);
        this.addDefault("Perk.RequiredLevel", 10);
        this.addDefault("Perk.Price", 1000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Golden Heads");
        this.addDefault("Perk.ItemName", "&6Golden Heads");
        this.addDefault("Perk.ItemLore", Arrays.asList("&9Speed I (0:08)", "&9Regeneration II (0:05)"));
        this.addDefault("Perk.Icon", Materials.GOLD_BLOCK.name());
        this.addDefault("GUI.Activated.Name", "&aGolden Heads");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &6Golden Heads&7.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aGolden Heads");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &6Golden Heads&7.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aGolden Heads");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &6Golden Heads&7.", "", "&7Cost: &61000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&910&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Golden Heads", "&7Cost: &61000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aGolden Heads", "", "&7Golden apples you earn turn", "&7into &6Golden Heads&7.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
