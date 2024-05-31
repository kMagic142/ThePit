package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class LuckyDiamond extends FileManager {
    public LuckyDiamond() {
        super("LuckyDiamond", "Perks");
        this.addDefault("Perk.RequiredLevel", 40);
        this.addDefault("Perk.Price", 3000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Lucky Diamond");
        this.addDefault("Perk.Icon", Materials.DIAMOND.name());
        this.addDefault("GUI.Activated.Name", "&aLucky Diamond");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&730% chance to upgrade", "&7dropped armor pieces from", "&7kills to &bdiamond&7.", "", "&7Upgraded pieces warp to", "&7your inventory.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aLucky Diamond");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&730% chance to upgrade", "&7dropped armor pieces from", "&7kills to &bdiamond&7.", "", "&7Upgraded pieces warp to", "&7your inventory.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aLucky Diamond");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&730% chance to upgrade", "&7dropped armor pieces from", "&7kills to &bdiamond&7.", "", "&7Upgraded pieces warp to", "&7your inventory.", "", "&7Cost: &63000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&940&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Lucky Diamond", "&7Cost: &63000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aLucky Diamond", "", "&730% chance to upgrade", "&7dropped armor pieces from", "&7kills to &bdiamond&7.", "", "&7Upgraded pieces warp to", "&7your inventory.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
