package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Mineman extends FileManager {
    public Mineman() {
        super("Mineman", "Perks");
        this.addDefault("Perk.RequiredLevel", 25);
        this.addDefault("Perk.Price", 3000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Mineman");
        this.addDefault("Perk.Icon", Materials.COBBLESTONE.name());
        this.addDefault("GUI.Activated.Name", "&aMineman");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Spawn with &f24 cobblestone", "&7and a diamond pickaxe.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aMineman");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Spawn with &f24 cobblestone", "&7and a diamond pickaxe.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aMineman");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Spawn with &f24 cobblestone", "&7and a diamond pickaxe.", "", "&7Cost: &63000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&925&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Mineman", "&7Cost: &63000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aMineman", "", "&7Spawn with &f24 cobblestone", "&7and a diamond pickaxe.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
