package ro.kmagic.files.gui;

import java.util.Arrays;
import ro.kmagic.libapi.utils.FileManager;

public class ChatOptionGui extends FileManager {
    public static final String title = "Title";
    public static final String bountiesName = "Bounties.Name";
    public static final String bountiesLore = "Bounties.Lore";
    public static final String streaksName = "Streaks.Name";
    public static final String streaksLore = "Streaks.Lore";
    public static final String prestigeName = "Prestige.Name";
    public static final String prestigeLore = "Prestige.Lore";
    public static final String minorEventsName = "Minor-Events.Name";
    public static final String minorEventsLore = "Minor-Events.Lore";
    public static final String killFeedName = "Kill-Feed.Name";
    public static final String killFeedLore = "Kill-Feed.Lore";
    public static final String playerChatName = "Player-Chat.Name";
    public static final String playerChatLore = "Player-Chat.Lore";
    public static final String miscellaneousName = "Miscellaneous.Name";
    public static final String miscellaneousLore = "Miscellaneous.Lore";
    public static final String on = "Gui-Lore.On";
    public static final String off = "Gui-Lore.Off";
    
    public ChatOptionGui() {
        super("ChatOption", "Gui");
        this.addDefault("Title", "&8Chat Options");
        this.addDefault("Bounties.Name", "&aChat Option: Bounties");
        this.addDefault("Bounties.Lore", Arrays.asList("&7Bounties announcements and", "&7claims, except yours.", "", "&7Enabled: %enabled%", "&eClick to toggle!"));
        this.addDefault("Streaks.Name", "&aChat Option: Streaks");
        this.addDefault("Streaks.Lore", Arrays.asList("&7Streak broadcasts.", "", "&7Enabled: %enabled%", "&eClick to toggle!"));
        this.addDefault("Prestige.Name", "&aChat Option: Prestige");
        this.addDefault("Prestige.Lore", Arrays.asList("&7Player prestige", "&7announcements.", "", "&7Enabled: %enabled%", "&eClick to toggle!"));
        this.addDefault("Minor-Events.Name", "&aChat Option: Minor Events");
        this.addDefault("Minor-Events.Lore", Arrays.asList("&7Minor event announcements.", "", "&7Enabled: %enabled%", "&eClick to toggle!"));
        this.addDefault("Kill-Feed.Name", "&aChat Option: Kill Feed");
        this.addDefault("Kill-Feed.Lore", Arrays.asList("&7Kills, assists and deaths.", "", "&7Enabled: %enabled%", "&eClick to toggle!"));
        this.addDefault("Player-Chat.Name", "&aChat Option: Player Chat");
        this.addDefault("Player-Chat.Lore", Arrays.asList("&7Chat messages sent from players.", "", "&7Enabled: %enabled%", "&eClick to toggle!"));
        this.addDefault("Miscellaneous.Name", "&aChat Option: Miscellaneous");
        this.addDefault("Miscellaneous.Lore", Arrays.asList("&7Tips, notes, picked up gold", "&7and passive xp.", "", "&7Enabled: %enabled%", "&eClick to toggle!"));
        this.addDefault("Gui-Lore.On", "&aON");
        this.addDefault("Gui-Lore.Off", "&cOFF");
        this.copyDefaults();
        this.save();
    }
}
