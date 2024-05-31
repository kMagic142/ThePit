package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Vampire extends FileManager {
    public Vampire() {
        super("Vampire", "Perks");
        this.addDefault("Perk.RequiredLevel", 60);
        this.addDefault("Perk.Price", 4000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Vampire");
        this.addDefault("Perk.Icon", Materials.FERMENTED_SPIDER_EYE.name());
        this.addDefault("GUI.Activated.Name", "&aVampire");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Don't earn golden apples.", "&7Heal &c0.5\u2764 &7on hit.", "&7Tripled on arrow crit.", "&cRegen I &7(8s) on kill.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aVampire");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Don't earn golden apples.", "&7Heal &c0.5\u2764 &7on hit.", "&7Tripled on arrow crit.", "&cRegen I &7(8s) on kill.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aVampire");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Don't earn golden apples.", "&7Heal &c0.5\u2764 &7on hit.", "&7Tripled on arrow crit.", "&cRegen I &7(8s) on kill.", "", "&7Cost: &64000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&960&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Vampire", "&7Cost: &64000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aVampire", "", "&7Don't earn golden apples.", "&7Heal &c0.5\u2764 &7on hit.", "&7Tripled on arrow crit.", "&cRegen I &7(8s) on kill.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
