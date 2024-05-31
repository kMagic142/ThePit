package ro.kmagic.files.perk.perks;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class BountyHunter extends FileManager {
    public BountyHunter() {
        super("BountyHunter", "Perks");
        this.addDefault("Perk.RequiredLevel", 45);
        this.addDefault("Perk.Price", 2000);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Bounty Hunter");
        this.addDefault("Perk.Icon", Materials.GOLDEN_LEGGINGS.name());
        this.addDefault("GUI.Activated.Name", "&aBounty Hunter");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&6+4g &7on all kills.", "&7Earn bounty assist shares.", "", "&c+1% damage&7/100g bounty", "&7on target.", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aBounty Hunter");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&6+4g &7on all kills.", "&7Earn bounty assist shares.", "", "&c+1% damage&7/100g bounty", "&7on target.", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aBounty Hunter");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&6+4g &7on all kills.", "&7Earn bounty assist shares.", "", "&c+1% damage&7/100g bounty", "&7on target.", "", "&7Cost: &62000g", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: [&945&7]"));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Bounty Hunter", "&7Cost: &62000g"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aBounty Hunter", "", "&6+4g &7on all kills.", "&7Earn bounty assist shares.", "", "&c+1% damage&7/100g bounty", "&7on target.", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
