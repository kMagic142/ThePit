package ro.kmagic.files.npcs.prestige;

import java.util.Arrays;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.utils.FileManager;

public class Prestige extends FileManager {
    public static final String title = "Title";
    public static final String prestigeItem = "Prestige.Item";
    public static final String prestigeName = "Prestige.Name";
    public static final String prestigeLore = "Prestige.Lore";
    public static final String prestigeTitle = "Prestige.Title";
    public static final String prestigeSubtitle = "Prestige.Subtitle";
    public static final String prestigeMessage = "Prestige.Message";
    public static final String renownShopItem = "Renown-Shop.Item";
    
    public Prestige() {
        super("Prestige", "Gui");
        this.addDefault("Title", "&7Prestige & Renown");
        this.addDefault("Prestige.Item", Materials.DIAMOND.name());
        this.addDefault("Prestige.Name", "&bPrestige");
        this.addDefault("Prestige.Lore", Arrays.asList("&7Required Level: %xpTag%", "", "&7Costs:", "&c&l\u25fe Resets &blevel &cto %resettedLevel%", "&c&l\u25fe Resets &6gold &cto 0", "&c&l\u25fe Resets &r&cALL &aperks and upgrades", "&c&l\u25fe Resets &r&cyour inventory", "&c&l\u25fe Grinded &6%grinded%&c/&6%required%g", "&7&oRenown upgrades are kept", "&7&oEnder chest is kept", "", "&7Reward: &e%renown% Renown", "", "&7New prestige: %nextPrestige%", "", "&eClick to purchase!"));
        this.addDefault("Prestige.Title", "&e&lPRESTIGE!");
        this.addDefault("Prestige.Subtitle", "&7You unlocked prestige &e%prestige%");
        this.addDefault("Prestige.Message", "&e&lPRESTIGE! &b%player% &7unlocked prestige &e%prestige%&7, gg!");
        this.addDefault("Renown-Shop.Item", Materials.BEACON.name());
        this.copyDefaults();
        this.save();
    }
}
