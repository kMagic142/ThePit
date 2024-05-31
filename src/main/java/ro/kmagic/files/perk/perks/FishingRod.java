package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class FishingRod extends FileManager {
    public FishingRod() {
        super("FishingRod", "Perks");
        this.addDefault("Perk.RequiredLevel", 15);
        this.addDefault("Perk.Price", 1000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Fishing Rod");
        this.addDefault("Perk.Icon", Materials.FISHING_ROD.name());
        this.addDefault("GUI.Activated.Name", "&aFishing Rod");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Spawn with a fishing rod.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aFishing Rod");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Spawn with a fishing rod.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aFishing Rod");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Spawn with a fishing rod.", "", "&7Cost: &61000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&915&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Fishing Rod", "&7Cost: &61000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aFishing Rod", "", "&7Spawn with a fishing rod.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
