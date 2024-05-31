package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class AssistStreaker extends FileManager {
    public AssistStreaker() {
        super("AssistStreaker", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 50);
        this.addDefault("Perk.Required-Prestige", 5);
        this.addDefault("Perk.Price", 8000);
        this.addDefault("Perk.Price-Renown", 15);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Assist Streaker");
        this.addDefault("Perk.Icon", Materials.DARK_OAK_FENCE.name());
        this.addDefault("GUI.Activated.Name", "&aAssist Streaker");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Assists count their", "&aparticipation &7towards", "&7killstreaks.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aAssist Streaker");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Assists count their", "&aparticipation &7towards", "&7killstreaks.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aAssist Streaker");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Assists count their", "&aparticipation &7towards", "&7killstreaks.", "", "&7Cost: &68000g &7& &e15 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(50, 5)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Assist Streaker", "&7Cost: &68000g &7& &e15 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aAssist Streaker", "", "&7Assists count their", "&aparticipation &7towards", "&7killstreaks.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
