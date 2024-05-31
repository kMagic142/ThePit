package ro.kmagic.features.chatoption;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.database.utils.Column;
import ro.kmagic.libapi.database.utils.ResultSet;
import ro.kmagic.libapi.gui.ClickAction;
import ro.kmagic.libapi.gui.GUI;
import ro.kmagic.libapi.gui.GuiItem;
import ro.kmagic.libapi.item.ItemBuilder;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ChatOption {
    private final HashMap<String, String> nameCache;
    private final HashMap<String, List<String>> loreCache;
    private final String onMsg;
    private final String offMsg;
    
    public ChatOption() {
        this.nameCache = new HashMap<>();
        this.loreCache = new HashMap<>();
        for (final String key : new ArrayList<>(Arrays.asList("Bounties.Name", "Streaks.Name", "Prestige.Name", "Minor-Events.Name", "Kill-Feed.Name", "Player-Chat.Name", "Miscellaneous.Name"))) {
            this.nameCache.put(key, Main.getChatOptionGui().getString(key));
        }
        for (final String key2 : new ArrayList<>(Arrays.asList("Bounties.Lore", "Streaks.Lore", "Prestige.Lore", "Minor-Events.Lore", "Kill-Feed.Lore", "Player-Chat.Lore", "Miscellaneous.Lore"))) {
            this.loreCache.put(key2, Main.getChatOptionGui().getStringList(key2));
        }
        this.onMsg = Main.getChatOptionGui().getString("Gui-Lore.On");
        this.offMsg = Main.getChatOptionGui().getString("Gui-Lore.Off");
    }
    
    public void addPlayer(final Player player) {
        final HashMap<ChatOptionType, String> hashMap = new HashMap<ChatOptionType, String>();
        final ResultSet resultSet = API.getDatabase().getResultSet(player.getUniqueId(), "ThePitChatOptions");
        for (final ChatOptionType key : ChatOptionType.values()) {
            hashMap.put(key, resultSet.getString(key.name()));
        }
        resultSet.close();
        new PlayerChatOption(player, hashMap);
    }
    
    public void openGui(final Player player) {
        if (!PlayerChatOption.isCached(player)) {
            return;
        }
        final GUI gui = new GUI(player, 27, Main.getChatOptionGui().getString("Title"));
        final ArrayList<String> lore = new ArrayList<String>();
        final ArrayList<String> lore2 = new ArrayList<String>();
        final ArrayList<String> lore3 = new ArrayList<String>();
        final ArrayList<String> lore4 = new ArrayList<String>();
        final ArrayList<String> lore5 = new ArrayList<String>();
        final ArrayList<String> lore6 = new ArrayList<String>();
        final ArrayList<String> lore7 = new ArrayList<String>();
        final PlayerChatOption value = PlayerChatOption.get(player);
        final ArrayList<ChatOptionType> enabledChatOptions = value.getEnabledChatOptions();
        for (String s : this.loreCache.get("Bounties.Lore")) {
            lore.add(s.replace("%enabled%", enabledChatOptions.contains(ChatOptionType.BOUNTIES) ? this.onMsg : this.offMsg));
        }
        for (String s : this.loreCache.get("Streaks.Lore")) {
            lore2.add(s.replace("%enabled%", enabledChatOptions.contains(ChatOptionType.STREAKS) ? this.onMsg : this.offMsg));
        }
        for (String s : this.loreCache.get("Prestige.Lore")) {
            lore3.add(s.replace("%enabled%", enabledChatOptions.contains(ChatOptionType.PRESTIGE) ? this.onMsg : this.offMsg));
        }
        for (String s : this.loreCache.get("Minor-Events.Lore")) {
            lore4.add(s.replace("%enabled%", enabledChatOptions.contains(ChatOptionType.MINOR_EVENTS) ? this.onMsg : this.offMsg));
        }
        for (String s : this.loreCache.get("Kill-Feed.Lore")) {
            lore5.add(s.replace("%enabled%", enabledChatOptions.contains(ChatOptionType.KILL_FEED) ? this.onMsg : this.offMsg));
        }
        for (String s : this.loreCache.get("Player-Chat.Lore")) {
            lore6.add(s.replace("%enabled%", enabledChatOptions.contains(ChatOptionType.PLAYER_CHAT) ? this.onMsg : this.offMsg));
        }
        for (String s : this.loreCache.get("Miscellaneous.Lore")) {
            lore7.add(s.replace("%enabled%", enabledChatOptions.contains(ChatOptionType.MISCELLANEOUS) ? this.onMsg : this.offMsg));
        }
        gui.addItem(new GuiItem(Materials.GOLD_NUGGET.getItem().setDisplayName(this.nameCache.get("Bounties.Name")).setLore(lore).build(), 10).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                value.toggle(ChatOptionType.BOUNTIES);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 2.0f);
                lore.clear();
                for (String s : ChatOption.this.loreCache.get("Bounties.Lore")) {
                    lore.add(s.replace("%enabled%", value.getEnabledChatOptions().contains(ChatOptionType.BOUNTIES) ? ChatOption.this.onMsg : ChatOption.this.offMsg));
                }
                guiItem.editItemStack(gui.getInventory(), new ItemBuilder(guiItem.getItemStack()).setLore(lore).build());
            }
        }));
        gui.addItem(new GuiItem(Materials.DIAMOND_LEGGINGS.getItem().setDisplayName(this.nameCache.get("Streaks.Name")).setLore(lore2).build(), 11).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                value.toggle(ChatOptionType.STREAKS);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 2.0f);
                lore2.clear();
                for (String s : ChatOption.this.loreCache.get("Streaks.Lore")) {
                    lore2.add(s.replace("%enabled%", value.getEnabledChatOptions().contains(ChatOptionType.STREAKS) ? ChatOption.this.onMsg : ChatOption.this.offMsg));
                }
                guiItem.editItemStack(gui.getInventory(), new ItemBuilder(guiItem.getItemStack()).setLore(lore2).build());
            }
        }));
        gui.addItem(new GuiItem(Materials.DIAMOND.getItem().setDisplayName(this.nameCache.get("Prestige.Name")).setLore(lore3).build(), 12).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                value.toggle(ChatOptionType.PRESTIGE);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 2.0f);
                lore3.clear();
                for (String s : ChatOption.this.loreCache.get("Prestige.Lore")) {
                    lore3.add(s.replace("%enabled%", value.getEnabledChatOptions().contains(ChatOptionType.PRESTIGE) ? ChatOption.this.onMsg : ChatOption.this.offMsg));
                }
                guiItem.editItemStack(gui.getInventory(), new ItemBuilder(guiItem.getItemStack()).setLore(lore3).build());
            }
        }));
        gui.addItem(new GuiItem(Materials.TNT.getItem().setDisplayName(this.nameCache.get("Minor-Events.Name")).setLore(lore4).build(), 13).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                value.toggle(ChatOptionType.MINOR_EVENTS);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 2.0f);
                lore4.clear();
                for (String s : ChatOption.this.loreCache.get("Minor-Events.Lore")) {
                    lore4.add(s.replace("%enabled%", value.getEnabledChatOptions().contains(ChatOptionType.MINOR_EVENTS) ? ChatOption.this.onMsg : ChatOption.this.offMsg));
                }
                guiItem.editItemStack(gui.getInventory(), new ItemBuilder(guiItem.getItemStack()).setLore(lore4).build());
            }
        }));
        gui.addItem(new GuiItem(Materials.IRON_SWORD.getItem().setDisplayName(this.nameCache.get("Kill-Feed.Name")).setLore(lore5).flag().add(ItemFlag.values()).build(), 14).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                value.toggle(ChatOptionType.KILL_FEED);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 2.0f);
                lore5.clear();
                for (String s : ChatOption.this.loreCache.get("Kill-Feed.Lore")) {
                    lore5.add(s.replace("%enabled%", value.getEnabledChatOptions().contains(ChatOptionType.KILL_FEED) ? ChatOption.this.onMsg : ChatOption.this.offMsg));
                }
                guiItem.editItemStack(gui.getInventory(), new ItemBuilder(guiItem.getItemStack()).setLore(lore5).build());
            }
        }));
        gui.addItem(new GuiItem(Materials.PAPER.getItem().setDisplayName(this.nameCache.get("Player-Chat.Name")).setLore(lore6).build(), 15).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                value.toggle(ChatOptionType.PLAYER_CHAT);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 2.0f);
                lore6.clear();
                for (String s : ChatOption.this.loreCache.get("Player-Chat.Lore")) {
                    lore6.add(s.replace("%enabled%", value.getEnabledChatOptions().contains(ChatOptionType.PLAYER_CHAT) ? ChatOption.this.onMsg : ChatOption.this.offMsg));
                }
                guiItem.editItemStack(gui.getInventory(), new ItemBuilder(guiItem.getItemStack()).setLore(lore6).build());
            }
        }));
        gui.addItem(new GuiItem(Materials.FLINT.getItem().setDisplayName(this.nameCache.get("Miscellaneous.Name")).setLore(lore7).build(), 16).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                value.toggle(ChatOptionType.MISCELLANEOUS);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 2.0f);
                lore7.clear();
                for (String s : ChatOption.this.loreCache.get("Miscellaneous.Lore")) {
                    lore7.add(s.replace("%enabled%", value.getEnabledChatOptions().contains(ChatOptionType.MISCELLANEOUS) ? ChatOption.this.onMsg : ChatOption.this.offMsg));
                }
                guiItem.editItemStack(gui.getInventory(), new ItemBuilder(guiItem.getItemStack()).setLore(lore7).build());
            }
        }));
        gui.open();
    }
    
    public static String getTableName() {
        return "ThePitChatOptions";
    }
    
    public static void createTable() {
        API.getDatabase().createTable("ThePitChatOptions", Arrays.asList(new Column("Player").setType(Column.ColumnType.VARCHAR).setLength(30), new Column("UUID").setType(Column.ColumnType.VARCHAR).setLength(50).setNotNull().setPrimaryKey()));
        final ChatOptionType[] values = ChatOptionType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            API.getDatabase().addColumn("ThePitChatOptions", new Column(values[i].name()).setType(Column.ColumnType.VARCHAR).setLength(5).setDefault("true"));
        }
    }
    
    public enum ChatOptionType {
        BOUNTIES, 
        STREAKS, 
        PRESTIGE, 
        MINOR_EVENTS, 
        KILL_FEED, 
        PLAYER_CHAT, 
        MISCELLANEOUS
    }
}
