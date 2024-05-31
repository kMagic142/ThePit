package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.utils.FileManager;

public class Olympus extends FileManager {
    public Olympus() {
        super("Olympus", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 70);
        this.addDefault("Perk.Required-Prestige", 4);
        this.addDefault("Perk.Price", 6000);
        this.addDefault("Perk.Price-Renown", 20);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Olympus");
        this.addDefault("Perk.ItemName", "&bOlympus Potion");
        this.addDefault("Perk.ItemLore", Arrays.asList("&9Speed I (0:24)", "&9Regeneration III (0:10)", "&9Resistance II (0:04)"));
        this.addDefault("Item.Stack-Limit", 1);
        this.addDefault("Perk.Icon", Materials.POTION.name());
        this.addDefault("GUI.Activated.Name", "&aOlympus");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &bOlympus Potions&7.", "", "&bOlympus Potion", "&9Speed I (0:24)", "&9Regeneration III (0:10)", "&9Resistance II (0:04)", "&bGain +27 XP!", "&7Can only hold 1", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aOlympus");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &bOlympus Potions&7.", "", "&bOlympus Potion", "&9Speed I (0:24)", "&9Regeneration III (0:10)", "&9Resistance II (0:04)", "&bGain +27 XP!", "&7Can only hold 1", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aOlympus");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &bOlympus Potions&7.", "", "&bOlympus Potion", "&9Speed I (0:24)", "&9Regeneration III (0:10)", "&9Resistance II (0:04)", "&bGain +27 XP!", "&7Can only hold 1", "", "&7Cost: &66000g &7& &e20 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(70, 4)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Olympus", "&7Cost: &66000g &7& &e20 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aOlympus", "", "&7Golden apples you earn turn", "&7into &bOlympus Potions&7.", "", "&bOlympus Potion", "&9Speed I (0:24)", "&9Regeneration III (0:10)", "&9Resistance II (0:04)", "&bGain +27 XP!", "&7Can only hold 1", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
