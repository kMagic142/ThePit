package ro.kmagic.features.npcs.nonpermanentitems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import ro.kmagic.libapi.gui.ClickAction;
import ro.kmagic.libapi.gui.GUI;
import ro.kmagic.libapi.gui.GuiItem;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.api.events.misc.NonPermanentItemsBuyEvent;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.files.npcs.nonpermenentitems.NonPermanentItems;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class NonPermanentItemsGUI extends NonPermanentItems {
    private final HashMap<UUID, GUI> shop;
    private final int diamondSwordCost;
    private final int obsidianCost;
    private final int diamondChestplateCost;
    private final int diamondBootsCost;
    private final String diamondSwordMessage;
    private final String obsidianMessage;
    private final String diamondChestplateMessage;
    private final String diamondBootsMessage;
    private final String diamondSwordItemName;
    private final String obsidianItemName;
    private final String diamondChestplateItemName;
    private final String diamondBootsItemName;
    private final List<String> diamondSwordItemLore;
    private final List<String> obsidianItemLore;
    private final List<String> diamondChestplateItemLore;
    private final List<String> diamondBootsItemLore;
    
    public NonPermanentItemsGUI() {
        this.shop = new HashMap<UUID, GUI>();
        this.diamondSwordCost = this.getInt("DiamondSword.Price");
        this.diamondSwordMessage = this.getString("DiamondSword.Success");
        this.obsidianCost = this.getInt("Obsidian.Price");
        this.obsidianMessage = this.getString("Obsidian.Success");
        this.diamondChestplateCost = this.getInt("DiamondChestplate.Price");
        this.diamondChestplateMessage = this.getString("DiamondChestplate.Success");
        this.diamondBootsCost = this.getInt("DiamondBoots.Price");
        this.diamondBootsMessage = this.getString("DiamondBoots.Success");
        this.diamondSwordItemName = this.getString("DiamondSword.Name");
        this.diamondSwordItemLore = this.getStringList("DiamondSword.Lore");
        this.obsidianItemName = this.getString("Obsidian.Name");
        this.obsidianItemLore = this.getStringList("Obsidian.Lore");
        this.diamondChestplateItemName = this.getString("DiamondChestplate.Name");
        this.diamondChestplateItemLore = this.getStringList("DiamondChestplate.Lore");
        this.diamondBootsItemName = this.getString("DiamondBoots.Name");
        this.diamondBootsItemLore = this.getStringList("DiamondBoots.Lore");
    }
    
    public void open(final Player player) {
        if (this.shop.containsKey(player.getUniqueId())) {
            this.shop.get(player.getUniqueId()).open();
            return;
        }
        final GUI gui = new GUI(player, 27, this.getString("GuiName"));
        gui.addItem(new GuiItem(this.shopItem(Materials.DIAMOND_SWORD, 1, this.diamondSwordItemName, this.diamondSwordItemLore), 10, new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                if (!PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.SUBTRACT, NonPermanentItemsGUI.this.diamondSwordCost)) {
                    return;
                }
                player.sendMessage(NonPermanentItemsGUI.this.diamondSwordMessage);
                final PlayerInventory inventory = player.getInventory();
                final ItemStack build = Materials.DIAMOND_SWORD.getItem().setUnbreakable(true).build();
                if (inventory.getItem(0) == null) {
                    ((Inventory)inventory).setItem(0, build);
                }
                else if (inventory.getItem(0).getType().equals(build.getType())) {
                    inventory.addItem(build);
                }
                else {
                    ((Inventory)inventory).setItem(0, build);
                }
                Sounds.PLAYER_LEVELUP.getSound().play(player, 1.0f, 3.0f);
                player.getInventory().remove(Material.IRON_SWORD);
                PlayerStats.get(player).add(Stats.StatsType.DIAMOND_ITEMS_PURCHASED, 1);
                Bukkit.getPluginManager().callEvent(new NonPermanentItemsBuyEvent(player, NonPermanentItemsGUI.this.diamondSwordCost, build));
            }
        }));
        gui.addItem(new GuiItem(this.shopItem(Materials.OBSIDIAN, 8, this.obsidianItemName, this.obsidianItemLore), 11).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                if (!PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.SUBTRACT, NonPermanentItemsGUI.this.obsidianCost)) {
                    return;
                }
                player.sendMessage(NonPermanentItemsGUI.this.obsidianMessage);
                final ItemStack build = Materials.OBSIDIAN.getItem().setAmount(8).build();
                player.getInventory().addItem(build);
                Sounds.PLAYER_LEVELUP.getSound().play(player, 1.0f, 3.0f);
                Bukkit.getPluginManager().callEvent(new NonPermanentItemsBuyEvent(player, NonPermanentItemsGUI.this.obsidianCost, build));
            }
        }));
        gui.addItem(new GuiItem(this.shopItem(Materials.DIAMOND_CHESTPLATE, 1, this.diamondChestplateItemName, this.diamondChestplateItemLore), 15).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                if (!PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.SUBTRACT, NonPermanentItemsGUI.this.diamondChestplateCost)) {
                    return;
                }
                player.sendMessage(NonPermanentItemsGUI.this.diamondChestplateMessage);
                final PlayerInventory inventory = player.getInventory();
                final ItemStack build = Materials.DIAMOND_CHESTPLATE.getItem().setUnbreakable(true).build();
                if (inventory.getChestplate() == null) {
                    inventory.setChestplate(build);
                    Sounds.EQUIP_ARMOR.getSound().play(player, 1.0f, 1.0f);
                }
                else if (inventory.getChestplate().equals(build)) {
                    Sounds.PLAYER_LEVELUP.getSound().play(player, 1.0f, 3.0f);
                    inventory.addItem(build);
                }
                else {
                    Sounds.EQUIP_ARMOR.getSound().play(player, 1.0f, 1.0f);
                    inventory.setChestplate(build);
                }
                PlayerStats.get(player).add(Stats.StatsType.DIAMOND_ITEMS_PURCHASED, 1);
                Bukkit.getPluginManager().callEvent(new NonPermanentItemsBuyEvent(player, NonPermanentItemsGUI.this.diamondChestplateCost, build));
            }
        }));
        gui.addItem(new GuiItem(this.shopItem(Materials.DIAMOND_BOOTS, 1, this.diamondBootsItemName, this.diamondBootsItemLore), 16).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                if (!PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.SUBTRACT, NonPermanentItemsGUI.this.diamondBootsCost)) {
                    return;
                }
                player.sendMessage(NonPermanentItemsGUI.this.diamondBootsMessage);
                final PlayerInventory inventory = player.getInventory();
                final ItemStack build = Materials.DIAMOND_BOOTS.getItem().setUnbreakable(true).build();
                if (inventory.getBoots() == null) {
                    if (PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                        inventory.addItem(build);
                    }
                    else {
                        inventory.setBoots(build);
                    }
                    Sounds.EQUIP_ARMOR.getSound().play(player, 1.0f, 1.0f);
                }
                else if (inventory.getBoots().equals(build)) {
                    Sounds.PLAYER_LEVELUP.getSound().play(player, 1.0f, 3.0f);
                    inventory.addItem(build);
                }
                else {
                    Sounds.EQUIP_ARMOR.getSound().play(player, 1.0f, 1.0f);
                    if (PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                        inventory.addItem(build);
                    }
                    else {
                        inventory.setBoots(build);
                    }
                }
                PlayerStats.get(player).add(Stats.StatsType.DIAMOND_ITEMS_PURCHASED, 1);
                Bukkit.getPluginManager().callEvent(new NonPermanentItemsBuyEvent(player, NonPermanentItemsGUI.this.diamondBootsCost, build));
            }
        }));
        gui.open();
    }
    
    private ItemStack shopItem(final Materials materials, final int amount, final String displayName, final List<String> lore) {
        return materials.getItem().setAmount(amount).setDisplayName(displayName).setLore(lore).flag().add(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE).build();
    }
}
