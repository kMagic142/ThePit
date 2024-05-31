package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Thick extends FileManager {
    public Thick() {
        super("Thick", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 100);
        this.addDefault("Perk.Required-Prestige", 13);
        this.addDefault("Perk.Price", 10000);
        this.addDefault("Perk.Price-Renown", 45);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Thick");
        this.addDefault("Perk.Icon", Materials.APPLE.name());
        this.addDefault("GUI.Activated.Name", "&aThick");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7You have &c+2 Max \u2764&7.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aThick");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7You have &c+2 Max \u2764&7.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aThick");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7You have &c+2 Max \u2764&7.", "", "&7Cost: &610000g &7& &e45 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(100, 13)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Thick", "&7Cost: &610000g &7& &e45 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aThick", "", "&7You have &c+2 Max \u2764&7.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
