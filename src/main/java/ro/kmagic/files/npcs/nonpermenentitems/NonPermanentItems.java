package ro.kmagic.files.npcs.nonpermenentitems;

import java.util.Arrays;
import ro.kmagic.libapi.utils.FileManager;

public class NonPermanentItems extends FileManager {
    private static final String diamondSword = "DiamondSword.";
    private static final String obsidian = "Obsidian.";
    private static final String diamondChestplate = "DiamondChestplate.";
    private static final String diamondBoots = "DiamondBoots.";
    public static final String guiName = "GuiName";
    public static final String diamondSwordName = "DiamondSword.Name";
    public static final String diamondSwordLore = "DiamondSword.Lore";
    public static final String diamondSwordSuccess = "DiamondSword.Success";
    public static final String diamondSwordPrice = "DiamondSword.Price";
    public static final String obsidianName = "Obsidian.Name";
    public static final String obsidianLore = "Obsidian.Lore";
    public static final String obsidianSuccess = "Obsidian.Success";
    public static final String obsidianPrice = "Obsidian.Price";
    public static final String diamondChestplateName = "DiamondChestplate.Name";
    public static final String diamondChestplateLore = "DiamondChestplate.Lore";
    public static final String diamondChestplateSuccess = "DiamondChestplate.Success";
    public static final String diamondChestplatePrice = "DiamondChestplate.Price";
    public static final String diamondBootsName = "DiamondBoots.Name";
    public static final String diamondBootsLore = "DiamondBoots.Lore";
    public static final String diamondBootsSuccess = "DiamondBoots.Success";
    public static final String diamondBootsPrice = "DiamondBoots.Price";
    
    public NonPermanentItems() {
        super("NonPermanentItems", "Gui");
        this.addDefault("GuiName", "&8Non-permanent items");
        this.addDefault("DiamondSword.Name", "&eDiamond Sword");
        this.addDefault("DiamondSword.Lore", Arrays.asList("&9+20% damage vs bountied", " ", "&7&oLost on death.", "&7Cost: &6100g", "&eClick to purchase!"));
        this.addDefault("DiamondSword.Success", "&a&lBOUGHT! &6Diamond Sword");
        this.addDefault("DiamondSword.Price", 100);
        this.addDefault("Obsidian.Name", "&eObsidian");
        this.addDefault("Obsidian.Lore", Arrays.asList("&7Remains for 120 seconds.", " ", "&7&oLost on death.", "&7Cost: &640g", "&eClick to purchase!"));
        this.addDefault("Obsidian.Success", "&a&lBOUGHT! &6Obsidian");
        this.addDefault("Obsidian.Price", 40);
        this.addDefault("DiamondChestplate.Name", "&eDiamond Chestplate");
        this.addDefault("DiamondChestplate.Lore", Arrays.asList("&7Auto-equips on buy!", " ", "&7&oLost on death.", "&7Cost: &6250g", "&eClick to purchase!"));
        this.addDefault("DiamondChestplate.Success", "&a&lBOUGHT! &6Diamond Chestplate");
        this.addDefault("DiamondChestplate.Price", 250);
        this.addDefault("DiamondBoots.Name", "&eDiamond Boots");
        this.addDefault("DiamondBoots.Lore", Arrays.asList("&7Auto-equips on buy!", " ", "&7&oLost on death.", "&7Cost: &6150g", "&eClick to purchase!"));
        this.addDefault("DiamondBoots.Success", "&a&lBOUGHT! &6Diamond Boots");
        this.addDefault("DiamondBoots.Price", 150);
        this.copyDefaults();
        this.save();
        this.reload();
    }
}
