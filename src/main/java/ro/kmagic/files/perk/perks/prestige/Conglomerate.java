package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Conglomerate extends FileManager {
    public Conglomerate() {
        super("Conglomerate", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 50);
        this.addDefault("Perk.Required-Prestige", 8);
        this.addDefault("Perk.Price", 20000);
        this.addDefault("Perk.Price-Renown", 40);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Conglomerate");
        this.addDefault("Perk.Icon", Materials.HAY_BLOCK.name());
        this.addDefault("GUI.Activated.Name", "&aConglomerate");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Don't earn &bXP &7from", "&7kills. The &bXP &7you would", "&7earn is converted to &6gold", "&7at a &e20 &7ratio.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aConglomerate");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Don't earn &bXP &7from", "&7kills. The &bXP &7you would", "&7earn is converted to &6gold", "&7at a &e20 &7ratio.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aConglomerate");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Don't earn &bXP &7from", "&7kills. The &bXP &7you would", "&7earn is converted to &6gold", "&7at a &e20 &7ratio.", "", "&7Cost: &620000g &7& &e40 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(50, 8)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Conglomerate", "&7Cost: &620000g &7& &e40 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aConglomerate", "", "&7Don't earn &bXP &7from", "&7kills. The &bXP &7you would", "&7earn is converted to &6gold", "&7at a &e20 &7ratio.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
