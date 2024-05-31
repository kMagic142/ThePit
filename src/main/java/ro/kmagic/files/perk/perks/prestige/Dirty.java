package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Dirty extends FileManager {
    public Dirty() {
        super("Dirty", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 80);
        this.addDefault("Perk.Required-Prestige", 2);
        this.addDefault("Perk.Price", 8000);
        this.addDefault("Perk.Price-Renown", 15);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Dirty");
        this.addDefault("Perk.Icon", Materials.PODZOL.name());
        this.addDefault("GUI.Activated.Name", "&aDirty");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Gain Resistance II (5s) on", "&7kill.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aDirty");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Gain Resistance II (5s) on", "&7kill.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aDirty");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Gain Resistance II (5s) on", "&7kill.", "", "&7Cost: &68000g &7& &e15 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(80, 2)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Dirty", "&7Cost: &68000g &7& &e15 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aDirty", "", "&7Gain Resistance II (5s) on", "&7kill.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
