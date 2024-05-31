package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Streaker extends FileManager {
    public Streaker() {
        super("Streaker", "Perks");
        this.addDefault("Perk.RequiredLevel", 50);
        this.addDefault("Perk.Price", 8000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Streaker");
        this.addDefault("Perk.Icon", Materials.WHEAT.name());
        this.addDefault("GUI.Activated.Name", "&aStreaker");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Triple streak kill &bXP", "&7bonus.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aStreaker");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Triple streak kill &bXP", "&7bonus.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aStreaker");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Triple streak kill &bXP", "&7bonus.", "", "&7Cost: &68000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&950&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Streaker", "&7Cost: &68000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aStreaker", "", "&7Triple streak kill &bXP", "&7bonus.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
