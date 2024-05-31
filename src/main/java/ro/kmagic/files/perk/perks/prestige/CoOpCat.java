package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class CoOpCat extends FileManager {
    public CoOpCat() {
        super("CoOpCat", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 50);
        this.addDefault("Perk.Required-Prestige", 9);
        this.addDefault("Perk.Price", 8000);
        this.addDefault("Perk.Price-Renown", 20);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Co-op Cat");
        this.addDefault("Perk.Icon", Materials.OCELOT_SPAWN_EGG.name());
        this.addDefault("GUI.Activated.Name", "&aCo-op Cat");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Earn &b+50% XP &7and", "&6+50%g &7on all assists.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aCo-op Cat");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Earn &b+50% XP &7and", "&6+50%g &7on all assists.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aCo-op Cat");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Earn &b+50% XP &7and", "&6+50%g &7on all assists.", "", "&7Cost: &68000g &7& &e20 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(50, 9)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Co-op Cat", "&7Cost: &68000g &7& &e20 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aCo-op Cat", "", "&7Earn &b+50% XP &7and", "&6+50%g &7on all assists.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
