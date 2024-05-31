package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class StrengthChaining extends FileManager {
    public StrengthChaining() {
        super("Strength-Chaining", "Perks");
        this.addDefault("Perk.RequiredLevel", 20);
        this.addDefault("Perk.Price", 2000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Strength-Chaining");
        this.addDefault("Perk.Icon", Materials.REDSTONE.name());
        this.addDefault("GUI.Activated.Name", "&aStrength-Chaining");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&c+8% damage &7for 7s", "&7stacking on kill.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aStrength-Chaining");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&c+8% damage &7for 7s", "&7stacking on kill.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aStrength-Chaining");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&c+8% damage &7for 7s", "&7stacking on kill.", "", "&7Cost: &62000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&920&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Strength-Chaining", "&7Cost: &62000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aStrength-Chaining", "", "&c+8% damage &7for 7s", "&7stacking on kill.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
