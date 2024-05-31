package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class SafetyFirst extends FileManager {
    public SafetyFirst() {
        super("SafetyFirst", "Perks");
        this.addDefault("Perk.RequiredLevel", 30);
        this.addDefault("Perk.Price", 3000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Safety First");
        this.addDefault("Perk.Icon", Materials.CHAINMAIL_HELMET.name());
        this.addDefault("GUI.Activated.Name", "&aSafety First");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Spawn with a helmet.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aSafety First");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Spawn with a helmet.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aSafety First");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Spawn with a helmet.", "", "&7Cost: &63000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&930&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Safety First", "&7Cost: &63000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aSafety First", "", "&7Spawn with a helmet.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
