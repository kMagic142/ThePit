package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Gladiator extends FileManager {
    public Gladiator() {
        super("Gladiator", "Perks");
        this.addDefault("Perk.RequiredLevel", 55);
        this.addDefault("Perk.Price", 4000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Gladiator");
        this.addDefault("Perk.Icon", Materials.BONE.name());
        this.addDefault("GUI.Activated.Name", "&aGladiator");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Receive &9-3% &7damage per", "&7nearby player.", "", "&712 blocks range.", "&7Minimum 3, max 10 players", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aGladiator");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Receive &9-3% &7damage per", "&7nearby player.", "", "&712 blocks range.", "&7Minimum 3, max 10 players", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aGladiator");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Receive &9-3% &7damage per", "&7nearby player.", "", "&712 blocks range.", "&7Minimum 3, max 10 players", "", "&7Cost: &64000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&955&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Gladiator", "&7Cost: &64000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aGladiator", "", "&7Receive &9-3% &7damage per", "&7nearby player.", "", "&712 blocks range.", "&7Minimum 3, max 10 players", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
