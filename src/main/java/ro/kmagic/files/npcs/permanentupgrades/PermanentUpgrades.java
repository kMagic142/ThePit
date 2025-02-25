package ro.kmagic.files.npcs.permanentupgrades;

import java.util.Arrays;
import java.util.Collections;

import ro.kmagic.libapi.utils.FileManager;

public class PermanentUpgrades extends FileManager {
    private static final String player = "Player.";
    private static final String perkSlot = "PerkSlot.";
    private static final String level = "Level.";
    private static final String required = "Required";
    private static final String notEnough = "NotEnough";
    private static final String gui = "GUI.";
    private static final String name = "Name";
    private static final String lore = "Lore";
    private static final String backButton = "BackButton.";
    private static final String deactivateButton = "DeactivateButton";
    private static final String notOwned = "NotOwned.";
    private static final String misc = "Misc.";
    public static final String playerPerkSlot1LevelRequired = "Player.PerkSlot.1.Level.Required";
    public static final String playerPerkSlot1LevelNotEnough = "Player.PerkSlot.1.Level.NotEnough";
    public static final String playerPerkSlot2LevelRequired = "Player.PerkSlot.2.Level.Required";
    public static final String playerPerkSlot2LevelNotEnough = "Player.PerkSlot.2.Level.NotEnough";
    public static final String playerPerkSlot3LevelRequired = "Player.PerkSlot.3.Level.Required";
    public static final String playerPerkSlot3LevelNotEnough = "Player.PerkSlot.3.Level.NotEnough";
    public static final String guiName = "GUI.Name";
    public static final String guiPerkName = "GUI.Perk.Name";
    public static final String guiBuyName = "GUI.BuyName";
    public static final String guiConfirmName = "GUI.ConfirmName";
    public static final String guiBackButtonName = "GUI.BackButton.Name";
    public static final String guiBackButtonLore = "GUI.BackButton.Lore";
    public static final String guiDeactivateButtonName = "GUI.DeactivateButtonName";
    public static final String guiDeactivateButtonLore = "GUI.DeactivateButtonLore";
    public static final String guiPerkSlot1Name = "GUI.PerkSlot.1.Name";
    public static final String guiPerkSlot1Lore = "GUI.PerkSlot.1.Lore";
    public static final String guiPerkSlot2Name = "GUI.PerkSlot.2.Name";
    public static final String guiPerkSlot2Lore = "GUI.PerkSlot.2.Lore";
    public static final String guiPerkSlot2NotOwnedName = "GUI.PerkSlot.2.NotOwned.Name";
    public static final String guiPerkSlot2NotOwnedLore = "GUI.PerkSlot.2.NotOwned.Lore";
    public static final String guiPerkSlot3Name = "GUI.PerkSlot.3.Name";
    public static final String guiPerkSlot3Lore = "GUI.PerkSlot.3.Lore";
    public static final String guiPerkSlot3NotOwnedName = "GUI.PerkSlot.3.NotOwned.Name";
    public static final String guiPerkSlot3NotOwnedLore = "GUI.PerkSlot.3.NotOwned.Lore";
    public static final String guiPerkSlot4Name = "GUI.PerkSlot.4.Name";
    public static final String guiPerkSlot4Lore = "GUI.PerkSlot.4.Lore";
    public static final String miscPerkItemLore = "Misc.PerkItemLore";
    public static final String miscNotEnoughLevel = "Misc.NotEnoughLevel";
    public static final String miscNotEnoughPrestige = "Misc.Not-Enough-Prestige";
    public static final String miscNotUnlocked = "Misc.NotUnlocked";
    public static final String miscLocked = "Misc.Locked";
    public static final String miscAlreadySelected = "Misc.AlreadySelected";
    
    public PermanentUpgrades() {
        super("PermanentUpgrades", "Gui");
        this.addDefault("Player.PerkSlot.1.Level.Required", 10);
        this.addDefault("Player.PerkSlot.1.Level.NotEnough", "&cThis option unlocks at level 10");
        this.addDefault("Player.PerkSlot.2.Level.Required", 35);
        this.addDefault("Player.PerkSlot.2.Level.NotEnough", "&cSlot not unlocked yet!");
        this.addDefault("Player.PerkSlot.3.Level.Required", 70);
        this.addDefault("Player.PerkSlot.3.Level.NotEnough", "&cSlot not unlocked yet!");
        this.addDefault("GUI.Name", "Permanent Upgrades");
        this.addDefault("GUI.Perk.Name", "Choose a perk");
        this.addDefault("GUI.BuyName", "Are you sure?");
        this.addDefault("GUI.ConfirmName", "Are you sure?");
        this.addDefault("GUI.BackButton.Name", "&aGo Back");
        this.addDefault("GUI.BackButton.Lore", Collections.singletonList("&7To permanent upgrades"));
        this.addDefault("GUI.DeactivateButtonName", "&cNo Perk");
        this.addDefault("GUI.DeactivateButtonLore", Arrays.asList("&7Are you hardcore enough", "&7that you don't need any", "&7perk for this slot?", " ", "&eClick to remove perk!"));
        final String s = "&7Select a perk to fill this";
        final String s2 = "&7slot.";
        final String s3 = " ";
        final String s4 = "&eClick to choose perk!";
        this.addDefault("GUI.PerkSlot.1.Name", "&aPerk Slot #1");
        this.addDefault("GUI.PerkSlot.1.Lore", Arrays.asList(s, s2, s3, s4));
        this.addDefault("GUI.PerkSlot.2.Name", "&aPerk Slot #2");
        this.addDefault("GUI.PerkSlot.2.Lore", Arrays.asList(s, s2, s3, s4));
        this.addDefault("GUI.PerkSlot.2.NotOwned.Name", "&cPerk Slot #2");
        this.addDefault("GUI.PerkSlot.2.NotOwned.Lore", Collections.singletonList("&7Required level: &7[&c35&7]"));
        this.addDefault("GUI.PerkSlot.3.Name", "&aPerk Slot #3");
        this.addDefault("GUI.PerkSlot.3.Lore", Arrays.asList(s, s2, s3, s4));
        this.addDefault("GUI.PerkSlot.3.NotOwned.Name", "&cPerk Slot #3");
        this.addDefault("GUI.PerkSlot.3.NotOwned.Lore", Collections.singletonList("&7Required level: &7[&c70&7]"));
        this.addDefault("GUI.PerkSlot.4.Name", "&aPerk Slot #4");
        this.addDefault("GUI.PerkSlot.4.Lore", Arrays.asList(s, s2, s3, s4));
        this.addDefault("Misc.PerkItemLore", Collections.singletonList("&7Perk item"));
        this.addDefault("Misc.NotEnoughLevel", "&cYou don't have enough level!");
        this.addDefault("Misc.Not-Enough-Prestige", "&cYou don't have enough prestige!");
        this.addDefault("Misc.NotUnlocked", "&cSlot not unlocked yet!");
        this.addDefault("Misc.Locked", "&cYou don't have enough level to buy this perk!");
        this.addDefault("Misc.AlreadySelected", "&cThis perk is already selected!");
        this.copyDefaults();
        this.save();
    }
}
