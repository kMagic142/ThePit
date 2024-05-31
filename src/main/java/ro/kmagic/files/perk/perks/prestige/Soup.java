package ro.kmagic.files.perk.perks.prestige;

import ro.kmagic.Main;
import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Soup extends FileManager {
    public Soup() {
        super("Soup", "Perks/Prestige");
        this.addDefault("Perk.RequiredLevel", 90);
        this.addDefault("Perk.Required-Prestige", 7);
        this.addDefault("Perk.Price", 8000);
        this.addDefault("Perk.Price-Renown", 30);
        this.addDefault("Perk.Bought", "&a&lBOUGHT! &6Soup");
        this.addDefault("Perk.Icon", Materials.MUSHROOM_STEW.name());
        this.addDefault("Perk.ItemName", "&aTasty Soup");
        this.addDefault("Perk.ItemLore", Arrays.asList("&9Speed I (0:07)", "&9Regeneration I (0:07)", "&a1.5\u2764 Heal &7+ &61\u2764 Absorption", "&cNext melee hit +15% damage"));
        this.addDefault("Item.Stack-Limit", 1);
        this.addDefault("GUI.Activated.Name", "&aSoup");
        this.addDefault("GUI.Activated.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &aTasty Soup&7. You", "&7also earn soup on assists.", "", "&aTasty Soup", "&9Speed I (0:07)", "&9Regeneration I (0:07)", "&a1.5\u2764 Heal &7+ &61\u2764 Absorption", "&cNext melee hit +15% damage", "", "&aAlready selected!"));
        this.addDefault("GUI.Deactivated.Name", "&aSoup");
        this.addDefault("GUI.Deactivated.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &aTasty Soup&7. You", "&7also earn soup on assists.", "", "&aTasty Soup", "&9Speed I (0:07)", "&9Regeneration I (0:07)", "&a1.5\u2764 Heal &7+ &61\u2764 Absorption", "&cNext melee hit +15% damage", "", "&eClick to select!"));
        this.addDefault("GUI.NotOwned.Name", "&aSoup");
        this.addDefault("GUI.NotOwned.Lore", Arrays.asList("&7Golden apples you earn turn", "&7into &aTasty Soup&7. You", "&7also earn soup on assists.", "", "&aTasty Soup", "&9Speed I (0:07)", "&9Regeneration I (0:07)", "&a1.5\u2764 Heal &7+ &61\u2764 Absorption", "&cNext melee hit +15% damage", "", "&7Cost: &68000g &7& &e30 Renown", "&7You have: &e%renown%", "&eClick to purchase!"));
        this.addDefault("GUI.Locked.Name", "&cUnknown perk");
        this.addDefault("GUI.Locked.Lore", Collections.singletonList("&7Required level: " + Main.getXpTag().get(90, 7)));
        this.addDefault("GUI.Confirm.Name", "&aConfirm");
        this.addDefault("GUI.Confirm.Lore", Arrays.asList("&7Purchasing: &6Soup", "&7Cost: &68000g &7& &e30 Renown", "&7You have: &e%renown%"));
        this.addDefault("GUI.Deny.Name", "&cClose");
        this.addDefault("GUI.Deny.Lore", Collections.singletonList("&7Back to main menu"));
        this.addDefault("GUI.Selected.Lore", Arrays.asList("&7Selected: &aSoup", "", "&7Golden apples you earn turn", "&7into &aTasty Soup&7. You", "&7also earn soup on assists.", "", "&aTasty Soup", "&9Speed I (0:07)", "&9Regeneration I (0:07)", "&a1.5\u2764 Heal &7+ &61\u2764 Absorption", "&cNext melee hit +15% damage", "", "&eClick to choose perk!"));
        this.copyDefaults();
        this.save();
    }
}
