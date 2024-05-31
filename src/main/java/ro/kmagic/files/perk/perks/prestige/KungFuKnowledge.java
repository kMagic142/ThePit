package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class KungFuKnowledge extends FileManager {
    public KungFuKnowledge() {
        super("KungFuKnowledge", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 100);
        this.addDefault("Perk.Required-Prestige", 9);
        this.addDefault("Perk.Price", 10000);
        this.addDefault("Perk.Price-Renown", 40);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Kung Fu Knowledge");
        this.addDefault("Perk.Icon", Materials.BEEF.name());
        this.addDefault("GUI.Activated.Name", "&aKung Fu Knowledge");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7No sword damage.", "&7Fists hit like a truck.", "&7Gain speed II (0:05) every", "&7fourth strike on a player.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aKung Fu Knowledge");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7No sword damage.", "&7Fists hit like a truck.", "&7Gain speed II (0:05) every", "&7fourth strike on a player.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aKung Fu Knowledge");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7No sword damage.", "&7Fists hit like a truck.", "&7Gain speed II (0:05) every", "&7fourth strike on a player.", "", "&7Cost: &610000g &7& &e40 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(100, 9)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Kung Fu Knowledge", "&7Cost: &610000g &7& &e40 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aKung Fu Knowledge", "", "&7No sword damage.", "&7Fists hit like a truck.", "&7Gain speed II (0:05) every", "&7fourth strike on a player.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
