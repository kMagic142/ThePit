package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class LavaBucket extends FileManager {
    public LavaBucket() {
        super("LavaBucket", "Perks");
        this.addDefault("Perk.RequiredLevel", 15);
        this.addDefault("Perk.Price", 1000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Lava Bucket");
        this.addDefault("Perk.Icon", Materials.LAVA_BUCKET.name());
        this.addDefault("GUI.Activated.Name", "&aLava Bucket");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Spawn with a lava bucket.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aLava Bucket");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Spawn with a lava bucket.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aLava Bucket");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Spawn with a lava bucket.", "", "&7Cost: &61000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&915&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Lava Bucket", "&7Cost: &61000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aLava Bucket", "", "&7Spawn with a lava bucket.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
