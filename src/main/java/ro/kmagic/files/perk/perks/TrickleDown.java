package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class TrickleDown extends FileManager {
    public TrickleDown() {
        super("Trickle-down", "Perks");
        this.addDefault("Perk.RequiredLevel", 40);
        this.addDefault("Perk.Price", 3000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Trickle-down");
        this.addDefault("Perk.Icon", Materials.GOLD_INGOT.name());
        this.addDefault("GUI.Activated.Name", "&aTrickle-down");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Gold ingots reward", "&6+10g &7and heal &c2\u2764&7.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aTrickle-down");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Gold ingots reward", "&6+10g &7and heal &c2\u2764&7.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aTrickle-down");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Gold ingots reward", "&6+10g &7and heal &c2\u2764&7.", "", "&7Cost: &63000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&940&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Trickle-down", "&7Cost: &63000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aTrickle-down", "", "&7Gold ingots reward", "&6+10g &7and heal &c2\u2764&7.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
