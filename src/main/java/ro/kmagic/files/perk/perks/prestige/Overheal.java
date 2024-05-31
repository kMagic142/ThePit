package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Overheal extends FileManager {
    public Overheal() {
        super("Overheal", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 70);
        this.addDefault("Perk.Required-Prestige", 1);
        this.addDefault("Perk.Price", 6000);
        this.addDefault("Perk.Price-Renown", 10);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Overheal");
        this.addDefault("Perk.Icon", Materials.BREAD.name());
        this.addDefault("GUI.Activated.Name", "&aOverheal");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Double healing item limits.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aOverheal");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Double healing item limits.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aOverheal");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Double healing item limits.", "", "&7Cost: &66000g &7& &e10 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(70, 1)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Overheal", "&7Cost: &66000g &7& &e10 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aOverheal", "", "&7Double healing item limits.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
