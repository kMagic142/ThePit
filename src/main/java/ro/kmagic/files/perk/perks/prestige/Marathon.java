package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Marathon extends FileManager {
    public Marathon() {
        super("Marathon", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 90);
        this.addDefault("Perk.Required-Prestige", 6);
        this.addDefault("Perk.Price", 8000);
        this.addDefault("Perk.Price-Renown", 20);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Marathon");
        this.addDefault("Perk.Icon", Materials.LEATHER_BOOTS.name());
        this.addDefault("GUI.Activated.Name", "&aMarathon");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Cannot wear boots.", "&7While you have speed:", "&7\u25fe Deal &c+15% &7damage", "&7\u25fe take &9-15% &7damage.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aMarathon");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Cannot wear boots.", "&7While you have speed:", "&7\u25fe Deal &c+15% &7damage", "&7\u25fe take &9-15% &7damage.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aMarathon");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Cannot wear boots.", "&7While you have speed:", "&7\u25fe Deal &c+15% &7damage", "&7\u25fe take &9-15% &7damage.", "", "&7Cost: &68000g &7& &e20 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(90, 6)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Marathon", "&7Cost: &68000g &7& &e20 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aMarathon", "", "&7Cannot wear boots.", "&7While you have speed:", "&7\u25fe Deal &c+15% &7damage", "&7\u25fe take &9-15% &7damage.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
