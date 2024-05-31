package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Barbarian extends FileManager {
    public Barbarian() {
        super("Barbarian", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 35);
        this.addDefault("Perk.Required-Prestige", 2);
        this.addDefault("Perk.Price", 3000);
        this.addDefault("Perk.Price-Renown", 10);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Barbarian");
        this.addDefault("Perk.Icon", Materials.IRON_AXE.name());
        this.addDefault("GUI.Activated.Name", "&aBarbarian");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Replaces your sword with a", "&7stronger axe.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aBarbarian");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Replaces your sword with a", "&7stronger axe.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aBarbarian");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Replaces your sword with a", "&7stronger axe.", "", "&7Cost: &63000g &7& &e10 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(35, 2)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Barbarian", "&7Cost: &63000g &7& &e10 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aBarbarian", "", "&7Replaces your sword with a", "&7stronger axe.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
