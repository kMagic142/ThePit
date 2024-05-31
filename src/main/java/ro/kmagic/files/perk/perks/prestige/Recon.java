package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Recon extends FileManager {
    public Recon() {
        super("Recon", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 60);
        this.addDefault("Perk.Required-Prestige", 7);
        this.addDefault("Perk.Price", 8000);
        this.addDefault("Perk.Price-Renown", 20);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Recon");
        this.addDefault("Perk.Icon", Materials.ENDER_EYE.name());
        this.addDefault("GUI.Activated.Name", "&aRecon");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Each fourth arrow shot at", "&7someone rewards &b+40 XP", "&7and deals &c+50% damage&7.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aRecon");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Each fourth arrow shot at", "&7someone rewards &b+40 XP", "&7and deals &c+50% damage&7.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aRecon");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Each fourth arrow shot at", "&7someone rewards &b+40 XP", "&7and deals &c+50% damage&7.", "", "&7Cost: &68000g &7& &e20 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(60, 7)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Recon", "&7Cost: &68000g &7& &e20 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aRecon", "", "&7Each fourth arrow shot at", "&7someone rewards &b+40 XP", "&7and deals &c+50% damage&7.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
