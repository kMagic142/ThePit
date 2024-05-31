package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class EndlessQuiver extends FileManager {
    public EndlessQuiver() {
        super("EndlessQuiver", "Perks");
        this.addDefault("Perk.RequiredLevel", 20);
        this.addDefault("Perk.Price", 2000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Endless Quiver");
        this.addDefault("Perk.Icon", Materials.BOW.name());
        this.addDefault("GUI.Activated.Name", "&aEndless Quiver");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Get 3 arrows on arrow hit.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aEndless Quiver");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Get 3 arrows on arrow hit.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aEndless Quiver");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Get 3 arrows on arrow hit.", "", "&7Cost: &62000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&920&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Endless Quiver", "&7Cost: &62000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aEndless Quiver", "", "&7Get 3 arrows on arrow hit.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
