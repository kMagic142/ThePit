package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Rambo extends FileManager {
    public Rambo() {
        super("Rambo", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 70);
        this.addDefault("Perk.Required-Prestige", 3);
        this.addDefault("Perk.Price", 6000);
        this.addDefault("Perk.Price-Renown", 10);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Rambo");
        this.addDefault("Perk.Icon", Materials.STICK.name());
        this.addDefault("GUI.Activated.Name", "&aRambo");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Don't earn golden apples.", "&7Max health: &c5\u2764", "&7Refill all health on kill.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aRambo");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Don't earn golden apples.", "&7Max health: &c5\u2764", "&7Refill all health on kill.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aRambo");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Don't earn golden apples.", "&7Max health: &c5\u2764", "&7Refill all health on kill.", "", "&7Cost: &66000g &7& &e10 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(70, 3)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Rambo", "&7Cost: &66000g &7& &e10 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aRambo", "", "&7Don't earn golden apples.", "&7Max health: &c5\u2764", "&7Refill all health on kill.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
