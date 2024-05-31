package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class FirstStrike extends FileManager {
    public FirstStrike() {
        super("FirstStrike", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 80);
        this.addDefault("Perk.Required-Prestige", 5);
        this.addDefault("Perk.Price", 8000);
        this.addDefault("Perk.Price-Renown", 25);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6First Strike");
        this.addDefault("Perk.Icon", Materials.COOKED_CHICKEN.name());
        this.addDefault("GUI.Activated.Name", "&aFirst Strike");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7First hit on a player deals", "&c+40% damage &7and grants", "&7speed I (5s).", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aFirst Strike");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7First hit on a player deals", "&c+40% damage &7and grants", "&7speed I (5s).", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aFirst Strike");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7First hit on a player deals", "&c+40% damage &7and grants", "&7speed I (5s).", "", "&7Cost: &68000g &7& &e25 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(80, 5)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6First Strike", "&7Cost: &68000g &7& &e25 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aFirst Strike", "", "&7First hit on a player deals", "&c+40% damage &7and grants", "&7speed I (5s).", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
