package ro.kmagic.features.perk;

import org.bukkit.Color;
import ro.kmagic.libapi.database.Database;
import ro.kmagic.libapi.database.utils.Column;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import ro.kmagic.features.economy.renown.RenownEconomy;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.libapi.gui.ClickAction;
import ro.kmagic.libapi.gui.GuiItem;
import ro.kmagic.libapi.gui.GUI;

import java.util.Arrays;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import org.bukkit.inventory.PlayerInventory;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.libapi.database.utils.ResultSet;
import ro.kmagic.libapi.API;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import ro.kmagic.Main;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import ro.kmagic.libapi.item.ItemBuilder;

import java.util.UUID;
import java.util.HashMap;

public class Perk {
    private static final String table = "ThePitPerks";
    public static final String DBowned = "_OWNED";
    public static final String DBslot = "_SLOT";
    private final HashMap<UUID, PerkType> buying;
    private final ItemBuilder goldenHeads;
    private final List<String> itemLore;
    private final List<String> backButtonLore;
    private final List<String> deactivateButtonLore;
    private final List<String> olympusItemLore;
    private final List<String> soupItemLore;
    private final ItemStack fishingRod;
    private final ItemStack lavaBucket;
    private final ItemStack diamondPickaxeItem;
    private final ItemStack cobblestoneItem;
    private final ItemStack chainmailHelmet;
    private final ItemStack barbarianAxe;
    private final ItemStack air;
    private final ItemStack ironSwordItem;
    private final Material bucket;
    private final Material diamondPickaxe;
    private final Material cobblestone;
    private final Material fishingRodMaterial;
    private final Material lavaBucketMaterial;
    private final Material ironSword;
    private final Material ironAxe;
    private final String alreadySelected;
    private final String guiBuyTitle;
    private final String goldNotEnough;
    private final String renownNotEnough;
    private final String backButtonName;
    private final String deactivateButtonName;
    private final String olympusItemName;
    private final String soupItemName;
    
    public Perk() {
        this.buying = new HashMap<UUID, PerkType>();
        this.goldenHeads = Materials.PLAYER_HEAD.getItem().skull().setSkin(Main.getSettings().getString("GoldenHeadsTexture")).setDisplayName(Main.getPerkDescription().get(PerkType.GOLDEN_HEADS).getString("Perk.ItemName")).setLore(Main.getPerkDescription().get(PerkType.GOLDEN_HEADS).getStringList("Perk.ItemLore"));
        this.itemLore = Main.getPermanentUpgrades().getStringList("Misc.PerkItemLore");
        this.fishingRod = this.item(Materials.FISHING_ROD);
        this.bucket = Materials.BUCKET.getItem().getMaterial();
        this.lavaBucket = this.item(Materials.LAVA_BUCKET);
        this.diamondPickaxe = Materials.DIAMOND_PICKAXE.getItem().getMaterial();
        this.cobblestone = Materials.COBBLESTONE.getItem().getMaterial();
        this.diamondPickaxeItem = this.item(Materials.DIAMOND_PICKAXE);
        this.cobblestoneItem = Materials.COBBLESTONE.getItem().setAmount(24).build();
        this.chainmailHelmet = this.item(Materials.CHAINMAIL_HELMET);
        this.alreadySelected = Main.getPermanentUpgrades().getString("Misc.AlreadySelected");
        this.fishingRodMaterial = Materials.FISHING_ROD.getItem().getMaterial();
        this.lavaBucketMaterial = Materials.LAVA_BUCKET.getItem().getMaterial();
        this.ironSword = Materials.IRON_SWORD.getItem().getMaterial();
        this.ironAxe = Materials.IRON_AXE.getItem().getMaterial();
        this.barbarianAxe = this.getBarbarianAxe().build();
        this.air = Materials.AIR.getItem().build();
        this.ironSwordItem = Main.getArena().item(Materials.IRON_SWORD);
        this.guiBuyTitle = Main.getPermanentUpgrades().getString("GUI.BuyName");
        this.goldNotEnough = Main.getMessages().getString("Economy.NotEnoughGold");
        this.renownNotEnough = Main.getMessages().getString("Economy.Not-Enough-Renown");
        this.backButtonName = Main.getPermanentUpgrades().getString("GUI.BackButton.Name");
        this.backButtonLore = Main.getPermanentUpgrades().getStringList("GUI.BackButton.Lore");
        this.deactivateButtonName = Main.getPermanentUpgrades().getString("GUI.DeactivateButtonName");
        this.deactivateButtonLore = Main.getPermanentUpgrades().getStringList("GUI.DeactivateButtonLore");
        this.olympusItemName = Main.getPerkDescription().get(PerkType.OLYMPUS).getString("Perk.ItemName");
        this.olympusItemLore = Main.getPerkDescription().get(PerkType.OLYMPUS).getStringList("Perk.ItemLore");
        this.soupItemName = Main.getPerkDescription().get(PerkType.SOUP).getString("Perk.ItemName");
        this.soupItemLore = Main.getPerkDescription().get(PerkType.SOUP).getStringList("Perk.ItemLore");
    }
    
    public void addPlayer(final Player player) {
        final ArrayList<PerkType> list = new ArrayList<PerkType>();
        PerkType perkType = null;
        PerkType perkType2 = null;
        PerkType perkType3 = null;
        PerkType perkType4 = null;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final ResultSet resultSet = API.getDatabase().getResultSet(player.getUniqueId(), "ThePitPerks");
        for (final PerkType perkType5 : PerkType.values()) {
            hashMap.put(perkType5 + "_OWNED", resultSet.getString(perkType5 + "_OWNED"));
            hashMap.put(perkType5 + "_SLOT", String.valueOf(resultSet.getInt(perkType5 + "_SLOT")));
        }
        for (final PerkType perkType6 : PerkType.values()) {
            if (String.valueOf(hashMap.get(perkType6 + "_OWNED")).equalsIgnoreCase("true")) {
                list.add(perkType6);
            }
            if (list.contains(perkType6)) {
                final int int1 = Integer.parseInt(String.valueOf(hashMap.get(perkType6 + "_SLOT")));
                if (int1 != 0) {
                    switch (int1) {
                        case 1: {
                            perkType = perkType6;
                            break;
                        }
                        case 2: {
                            perkType2 = perkType6;
                            break;
                        }
                        case 3: {
                            perkType3 = perkType6;
                            break;
                        }
                        case 4: {
                            perkType4 = perkType6;
                            break;
                        }
                    }
                }
            }
        }
        new PlayerPerk(player, perkType, perkType2, perkType3, perkType4, list);
    }
    
    public void giveItems(final Player player) {
        if (!PlayerPerk.isCached(player)) {
            return;
        }
        final PlayerPerk value = PlayerPerk.get(player);
        final PlayerInventory inventory = player.getInventory();
        if (value.isActivated(PerkType.FISHING_ROD) && !inventory.contains(Materials.FISHING_ROD.getItem().getMaterial())) {
            inventory.remove(this.fishingRod);
            inventory.addItem(this.fishingRod);
        }
        if (value.isActivated(PerkType.LAVA_BUCKET) && !inventory.contains(Materials.LAVA_BUCKET.getItem().getMaterial())) {
            inventory.remove(this.bucket);
            inventory.addItem(this.lavaBucket);
        }
        if (value.isActivated(PerkType.MINEMAN)) {
            inventory.remove(this.diamondPickaxe);
            inventory.remove(this.cobblestone);
            inventory.addItem(this.diamondPickaxeItem);
            inventory.addItem(this.cobblestoneItem);
        }
        if (value.isActivated(PerkType.SAFETY_FIRST) && (Main.getPitEventManager().getEventType() == null || !Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH) || !Main.getPitEventManager().isStarted())) {
            inventory.setHelmet(this.chainmailHelmet);
        }
    }
    
    public ItemStack item(final Materials materials) {
        return materials.getItem().setUnbreakable(true).setLore(this.itemLore).build();
    }
    
    public void activate(final Player player, final PerkType other, final int n) {
        if (!PlayerPerk.isCached(player)) {
            return;
        }
        final PlayerPerk value = PlayerPerk.get(player);
        if (value.isActivated(other, n)) {
            player.sendMessage(this.alreadySelected);
            Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
            return;
        }
        final PlayerInventory inventory = player.getInventory();
        this.deactivate(player, n);
        API.getDatabase().setInt(player.getUniqueId(), n, other + "_SLOT", "ThePitPerks");
        final PerkType slot = value.getSlot(1);
        final PerkType slot2 = value.getSlot(2);
        final PerkType slot3 = value.getSlot(3);
        final PerkType slot4 = value.getSlot(4);
        if (slot != null && slot.equals(other)) {
            value.setSlot(null, 1);
        }
        else if (slot2 != null && slot2.equals(other)) {
            value.setSlot(null, 2);
        }
        else if (slot3 != null && slot3.equals(other)) {
            value.setSlot(null, 3);
        }
        else if (slot4 != null && slot4.equals(other)) {
            value.setSlot(null, 4);
        }
        value.setSlot(other, n);
        switch (other) {
            case OLYMPUS: {
                for (final PerkType perkType : new ArrayList<PerkType>(Arrays.asList(PerkType.VAMPIRE, PerkType.GOLDEN_HEADS))) {
                    if (slot != null && slot.equals(perkType)) {
                        this.deactivate(player, 1);
                    }
                    else if (slot2 != null && slot2.equals(perkType)) {
                        this.deactivate(player, 2);
                    }
                    else if (slot3 != null && slot3.equals(perkType)) {
                        this.deactivate(player, 3);
                    }
                    else {
                        if (slot4 == null || !slot4.equals(perkType)) {
                            continue;
                        }
                        this.deactivate(player, 4);
                    }
                }
                break;
            }
            case GOLDEN_HEADS: {
                for (final PerkType perkType2 : new ArrayList<PerkType>(Arrays.asList(PerkType.VAMPIRE, PerkType.SOUP))) {
                    if (slot != null && slot.equals(perkType2)) {
                        this.deactivate(player, 1);
                    }
                    else if (slot2 != null && slot2.equals(perkType2)) {
                        this.deactivate(player, 2);
                    }
                    else if (slot3 != null && slot3.equals(perkType2)) {
                        this.deactivate(player, 3);
                    }
                    else {
                        if (slot4 == null || !slot4.equals(perkType2)) {
                            continue;
                        }
                        this.deactivate(player, 4);
                    }
                }
                break;
            }
            case VAMPIRE: {
                for (final PerkType perkType3 : new ArrayList<PerkType>(Arrays.asList(PerkType.GOLDEN_HEADS, PerkType.OLYMPUS, PerkType.SOUP))) {
                    if (slot != null && slot.equals(perkType3)) {
                        this.deactivate(player, 1);
                    }
                    else if (slot2 != null && slot2.equals(perkType3)) {
                        this.deactivate(player, 2);
                    }
                    else if (slot3 != null && slot3.equals(perkType3)) {
                        this.deactivate(player, 3);
                    }
                    else {
                        if (slot4 == null || !slot4.equals(perkType3)) {
                            continue;
                        }
                        this.deactivate(player, 4);
                    }
                }
                break;
            }
            case SOUP: {
                for (final PerkType perkType4 : new ArrayList<PerkType>(Arrays.asList(PerkType.GOLDEN_HEADS, PerkType.VAMPIRE))) {
                    if (slot != null && slot.equals(perkType4)) {
                        this.deactivate(player, 1);
                    }
                    else if (slot2 != null && slot2.equals(perkType4)) {
                        this.deactivate(player, 2);
                    }
                    else if (slot3 != null && slot3.equals(perkType4)) {
                        this.deactivate(player, 3);
                    }
                    else {
                        if (slot4 == null || !slot4.equals(perkType4)) {
                            continue;
                        }
                        this.deactivate(player, 4);
                    }
                }
                break;
            }
            case FISHING_ROD: {
                inventory.remove(this.fishingRodMaterial);
                inventory.addItem(this.fishingRod);
                break;
            }
            case LAVA_BUCKET: {
                inventory.remove(this.lavaBucketMaterial);
                inventory.remove(this.bucket);
                inventory.addItem(this.lavaBucket);
                break;
            }
            case MINEMAN: {
                inventory.remove(this.diamondPickaxe);
                inventory.remove(this.cobblestone);
                inventory.addItem(this.diamondPickaxeItem);
                inventory.addItem(this.cobblestoneItem);
                break;
            }
            case SAFETY_FIRST: {
                if (Main.getPitEventManager().getEventType() == null || !Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH) || !Main.getPitEventManager().isStarted()) {
                    if (inventory.getHelmet() != null) {
                        final ItemStack build = new ItemBuilder(inventory.getHelmet()).metadata().add("SafetyFirst").build();
                        inventory.setHelmet(null);
                        inventory.addItem(build);
                    }
                    inventory.setHelmet(this.chainmailHelmet);
                    break;
                }
                break;
            }
            case BARBARIAN: {
                inventory.remove(this.ironSword);
                player.getInventory().remove(this.ironAxe);
                player.getInventory().addItem(this.barbarianAxe);
                break;
            }
            case RAMBO: {
                int n2 = 10;
                if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT)) {
                    n2 = 40;
                }
                if (PlayerPerk.get(player).isActivated(PerkType.THICK)) {
                    n2 += 4;
                }

                player.setMaxHealth(n2);
                player.setHealthScale(n2);
                player.setHealthScaled(true);
                player.setHealth(n2);
                break;
            }
            case FIRST_STRIKE: {
                Main.getGlobalManager().firstHitMap.remove(player.getUniqueId());
                Main.getGlobalManager().firstHitMap.put(player.getUniqueId(), new ArrayList<UUID>());
                break;
            }
            case MARATHON: {
                if (inventory.getBoots() != null) {
                    inventory.addItem(new ItemBuilder(inventory.getBoots()).metadata().add("MARATHON").build());
                    inventory.setBoots(this.air);
                    break;
                }
                break;
            }
            case RECON: {
                Main.getGlobalManager().reconBuff.remove(player.getUniqueId());
                Main.getGlobalManager().reconBuff.put(player.getUniqueId(), 0);
                break;
            }
            case KUNG_FU_KNOWLEDGE: {
                Main.getGlobalManager().kungFuKnowledgeBuff.remove(player.getUniqueId());
                Main.getGlobalManager().kungFuKnowledgeBuff.put(player.getUniqueId(), 0);
                break;
            }
            case THICK: {
                int n3 = 20;
                if (PlayerPerk.get(player).isActivated(PerkType.RAMBO)) {
                    n3 = 10;
                }
                if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT)) {
                    n3 = 40;
                }
                n3 += 4;
                player.setMaxHealth(n3);
                player.setHealthScale(n3);
                player.setHealthScaled(true);
                player.setHealth(n3);
                break;
            }
        }
    }
    
    public void addBought(final Player player, final PerkType perkType) {
        if (!PlayerPerk.isCached(player)) {
            return;
        }
        PlayerPerk.get(player).addBought(perkType);
        Sounds.PLAYER_LEVELUP.getSound().play(player, 1.0f, 3.0f);
        player.sendMessage(Main.getPerkDescription().getBoughtMessage(perkType));
    }
    
    public void deactivate(final Player player, final int n) {
        if (!PlayerPerk.isCached(player)) {
            return;
        }
        final PlayerPerk value = PlayerPerk.get(player);
        final PerkType slot = value.getSlot(n);
        if (slot == null || !value.isActivated(slot, n)) {
            return;
        }
        value.setSlot(null, n);
        final PlayerInventory inventory = player.getInventory();
        switch (slot) {
            case FISHING_ROD: {
                inventory.remove(this.fishingRodMaterial);
                break;
            }
            case LAVA_BUCKET: {
                inventory.remove(this.lavaBucketMaterial);
                inventory.remove(this.bucket);
                break;
            }
            case MINEMAN: {
                inventory.remove(this.diamondPickaxe);
                inventory.remove(this.cobblestone);
                break;
            }
            case SAFETY_FIRST: {
                inventory.setHelmet(null);
                inventory.remove(Material.CHAINMAIL_HELMET);
                ItemStack helmet = null;
                for (final ItemStack itemStack : inventory.getContents()) {
                    if (itemStack != null) {
                        final ItemBuilder itemBuilder = new ItemBuilder(itemStack);
                        if (itemBuilder.metadata().has() && itemBuilder.metadata().get().equals("SafetyFirst")) {
                            helmet = itemStack;
                            inventory.remove(itemStack);
                            break;
                        }
                    }
                }
                if (helmet != null) {
                    inventory.setHelmet(helmet);
                    break;
                }
                break;
            }
            case BARBARIAN: {
                inventory.remove(this.ironAxe);
                inventory.addItem(this.ironSwordItem);
                break;
            }
            case RAMBO:
            case THICK: {
                int n2 = 20;
                if (PlayerPerk.get(player).isActivated(PerkType.RAMBO)) {
                    n2 = 10;
                }
                if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT)) {
                    n2 = 40;
                }
                if (PlayerPerk.get(player).isActivated(PerkType.THICK)) {
                    n2 += 4;
                }
                player.setMaxHealth(n2);
                player.setHealthScale(n2);
                player.setHealthScaled(true);
                player.setHealth(n2);
                break;
            }
            case FIRST_STRIKE: {
                Main.getGlobalManager().firstHitMap.remove(player.getUniqueId());
                break;
            }
            case MARATHON: {
                ItemStack boots = Materials.AIR.getItem().build();
                for (final ItemStack itemStack2 : inventory.getContents()) {
                    if (itemStack2 != null) {
                        final ItemBuilder itemBuilder2 = new ItemBuilder(itemStack2);
                        if (itemBuilder2.metadata().has() && itemBuilder2.metadata().get().equals("MARATHON")) {
                            boots = itemBuilder2.metadata().remove().build();
                            inventory.remove(itemStack2);
                            break;
                        }
                    }
                }
                inventory.setBoots(boots);
                break;
            }
            case RECON: {
                Main.getGlobalManager().reconBuff.remove(player.getUniqueId());
                break;
            }
            case KUNG_FU_KNOWLEDGE: {
                Main.getGlobalManager().kungFuKnowledgeBuff.remove(player.getUniqueId());
                break;
            }
        }
        API.getDatabase().setInt(player.getUniqueId(), 0, slot + "_SLOT", "ThePitPerks");
    }
    
    public boolean hasLevel(final Player player, final int i) {
        return player.getLevel() >= Main.getPermanentUpgrades().getInt("Player.PerkSlot." + i + ".Level.Required");
    }
    
    public void buy(final Player player, final PerkType value) {
        this.buying.put(player.getUniqueId(), value);
        final GUI gui = new GUI(player, 27, this.guiBuyTitle);
        gui.addItem(new GuiItem(this.buying(value, BuyState.CONFIRM), 11).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                boolean b = PlayerEconomy.get(player).hasEnough(Main.getPerkDescription().getPrice(value));
                PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.SUBTRACT, Main.getPerkDescription().getPrice(value));
                if (b) {
                    Perk.this.addBought(player, value);
                    Perk.this.activate(player, value, Main.getPerkSlotGui().playerSlot.get(player.getUniqueId()));
                }
                else {
                    Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                }
                gui.close();
                new BukkitRunnable() {
                    public void run() {
                        Main.getPerkGui().open(player);
                    }
                }.runTaskLater(API.getPlugin(), 10L);
            }
        }));
        gui.addItem(new GuiItem(this.buying(value, BuyState.DENY), 15).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                gui.close();
                new BukkitRunnable() {
                    public void run() {
                        Main.getPerkGui().open(player);
                    }
                }.runTaskLater(API.getPlugin(), 10L);
            }
        }));
        gui.open();
    }
    
    public void buyPrestige(final Player player, final PerkType value) {
        this.buying.put(player.getUniqueId(), value);
        final GUI gui = new GUI(player, 27, this.guiBuyTitle);
        gui.addItem(new GuiItem(this.buyingPrestige(player, value, BuyState.CONFIRM), 11).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                if (PlayerEconomy.get(player).hasEnough(Main.getPerkDescription().getPrice(value)) && RenownEconomy.get(player).hasEnough(Main.getPerkDescription().getPriceRenown(value))) {
                    PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.SUBTRACT, Main.getPerkDescription().getPrice(value));
                    RenownEconomy.get(player).setBalance(RenownEconomy.RenownAction.SUBTRACT, Main.getPerkDescription().getPriceRenown(value));
                    Perk.this.addBought(player, value);
                    Perk.this.activate(player, value, Main.getPerkSlotGui().playerSlot.get(player.getUniqueId()));
                }
                else {
                    Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                    if (!PlayerEconomy.get(player).hasEnough(Main.getPerkDescription().getPrice(value))) {
                        player.sendMessage(Perk.this.goldNotEnough);
                    }
                    else if (!RenownEconomy.get(player).hasEnough(Main.getPerkDescription().getPriceRenown(value))) {
                        player.sendMessage(Perk.this.renownNotEnough);
                    }
                }
                gui.close();
                new BukkitRunnable() {
                    public void run() {
                        Main.getPerkGui().open(player);
                    }
                }.runTaskLater(API.getPlugin(), 10L);
            }
        }));
        gui.addItem(new GuiItem(this.buyingPrestige(player, value, BuyState.DENY), 15).addClickAction(new ClickAction() {
            @Override
            public void onClick(final GuiItem guiItem, final GUI gui) {
                gui.close();
                new BukkitRunnable() {
                    public void run() {
                        Main.getPerkGui().open(player);
                    }
                }.runTaskLater(API.getPlugin(), 10L);
            }
        }));
        gui.open();
    }
    
    private ItemStack buying(final PerkType perkType, final BuyState buyState) {
        ItemBuilder itemBuilder = null;
        switch (buyState) {
            case CONFIRM: {
                itemBuilder = Materials.LIME_GLAZED_TERRACOTTA.getItem().setDisplayName(Main.getPerkDescription().getConfirmName(perkType)).setLore(Main.getPerkDescription().getConfirmLore(perkType));
                break;
            }
            case DENY: {
                itemBuilder = Materials.RED_GLAZED_TERRACOTTA.getItem().setDisplayName(Main.getPerkDescription().getDenyName(perkType)).setLore(Main.getPerkDescription().getDenyLore(perkType));
                break;
            }
        }
        return itemBuilder.flag().add(ItemFlag.values()).build();
    }
    
    private ItemStack buyingPrestige(final Player player, final PerkType perkType, final BuyState buyState) {
        ItemBuilder itemBuilder = null;
        switch (buyState) {
            case CONFIRM: {
                final ArrayList<String> lore = new ArrayList<String>();
                for (String s : Main.getPerkDescription().getConfirmLore(perkType)) {
                    lore.add(s.replace("%renown%", RenownEconomy.format(RenownEconomy.get(player).getRenown())));
                }
                itemBuilder = Materials.LIME_GLAZED_TERRACOTTA.getItem().setDisplayName(Main.getPerkDescription().getConfirmName(perkType)).setLore(lore);
                break;
            }
            case DENY: {
                itemBuilder = Materials.RED_GLAZED_TERRACOTTA.getItem().setDisplayName(Main.getPerkDescription().getDenyName(perkType)).setLore(Main.getPerkDescription().getDenyLore(perkType));
                break;
            }
        }
        return itemBuilder.flag().add(ItemFlag.values()).build();
    }
    
    public ItemStack getDescription(final PerkType perkType, final DescriptionType descriptionType, final int i, final Materials materials) {
        ItemBuilder itemBuilder = null;
        if (perkType == null) {
            itemBuilder = materials.getItem();
        }
        else {
            switch (perkType) {
                case GOLDEN_HEADS: {
                    itemBuilder = new ItemBuilder(this.goldenHeads.build());
                    break;
                }
                case OLYMPUS: {
                    itemBuilder = this.getOlympusPotion();
                    break;
                }
                case MARATHON: {
                    itemBuilder = this.getMarathonBoots();
                    break;
                }
                default: {
                    itemBuilder = Main.getPerkDescription().getIcon(perkType).getItem();
                    break;
                }
            }
        }
        switch (descriptionType) {
            case LOCKED: {
                itemBuilder = Materials.BEDROCK.getItem().setDisplayName(Main.getPerkDescription().getLockedName(perkType)).setLore(Main.getPerkDescription().getLockedLore(perkType));
                break;
            }
            case NOT_OWNED: {
                itemBuilder.setDisplayName(Main.getPerkDescription().getNotOwnedName(perkType)).setLore(Main.getPerkDescription().getNotOwnedLore(perkType));
                break;
            }
            case DEACTIVATED: {
                itemBuilder.setDisplayName(Main.getPerkDescription().getDeactivatedName(perkType)).setLore(Main.getPerkDescription().getDeactivatedLore(perkType));
                break;
            }
            case ACTIVATED: {
                itemBuilder.setDisplayName(Main.getPerkDescription().getActivatedName(perkType)).setLore(Main.getPerkDescription().getActivatedLore(perkType)).enchantment().addUnsafe(Enchantment.DURABILITY, 1);
                break;
            }
            case SELECTED: {
                itemBuilder.setDisplayName(Main.getPermanentUpgrades().getString("GUI.PerkSlot." + i + ".Name")).setLore(Main.getPerkDescription().getSelectedLore(perkType));
                break;
            }
            case PERK_SLOT: {
                itemBuilder.setDisplayName(Main.getPermanentUpgrades().getString("GUI.PerkSlot." + i + ".Name")).setLore(Main.getPermanentUpgrades().getStringList("GUI.PerkSlot." + i + ".Lore"));
                break;
            }
            case PERK_SLOT_NOT_OWNED: {
                itemBuilder.setDisplayName(Main.getPermanentUpgrades().getString("GUI.PerkSlot." + i + ".NotOwned.Name")).setLore(Main.getPermanentUpgrades().getStringList("GUI.PerkSlot." + i + ".NotOwned.Lore"));
                break;
            }
        }
        return itemBuilder.flag().add(ItemFlag.values()).build();
    }
    
    public ItemStack getDescriptionPrestige(final Player player, final PerkType perkType, final DescriptionType descriptionType, final Materials materials) {
        if (perkType == null) {
            return this.air;
        }
        ItemBuilder itemBuilder = Main.getPerkDescription().getIcon(perkType).getItem();
        if (perkType.equals(PerkType.OLYMPUS)) {
            itemBuilder = this.getOlympusPotion();
        }
        else if (perkType.equals(PerkType.MARATHON)) {
            itemBuilder = this.getMarathonBoots();
        }
        if (descriptionType == DescriptionType.NOT_OWNED) {
            final ArrayList<String> lore = new ArrayList<String>();
            for (String s : Main.getPerkDescription().getNotOwnedLore(perkType)) {
                lore.add(s.replace("%renown%", RenownEconomy.format(RenownEconomy.get(player).getRenown())));
            }
            itemBuilder.setDisplayName(Main.getPerkDescription().getNotOwnedName(perkType)).setLore(lore);
        }
        return itemBuilder.flag().add(ItemFlag.values()).build();
    }
    
    public ItemStack getButton(final ButtonType buttonType) {
        ItemBuilder itemBuilder = null;
        switch (buttonType) {
            case BACK: {
                itemBuilder = Materials.ARROW.getItem().setDisplayName(this.backButtonName).setLore(this.backButtonLore);
                break;
            }
            case DEACTIVATE: {
                itemBuilder = Materials.DIAMOND_BLOCK.getItem().setDisplayName(this.deactivateButtonName).setLore(this.deactivateButtonLore);
                break;
            }
        }
        return itemBuilder.flag().add(ItemFlag.values()).build();
    }
    
    public ItemBuilder getGoldenHeads() {
        return this.goldenHeads;
    }
    
    public static String getTableName() {
        return "ThePitPerks";
    }
    
    public static void createTable() {
        API.getDatabase().createTable("ThePitPerks", Arrays.asList(new Column("Player").setType(Column.ColumnType.VARCHAR).setLength(30), new Column("UUID").setType(Column.ColumnType.VARCHAR).setLength(50).setPrimaryKey()));
        for (final PerkType perkType : PerkType.values()) {
            API.getDatabase().addColumn("ThePitPerks", new Column(perkType + "_OWNED").setType(Column.ColumnType.VARCHAR).setLength(5).setDefault("false"));
            API.getDatabase().addColumn("ThePitPerks", new Column(perkType + "_SLOT").setType(Column.ColumnType.INT).setLength(1).setDefault(0));
        }
    }
    
    public static void createFlatFileAccount(final Player player) {
        final Database database = API.getDatabase();
        for (final PerkType perkType : PerkType.values()) {
            database.setString(player.getUniqueId(), "false", perkType + "_OWNED", "ThePitPerks");
            database.setInt(player.getUniqueId(), 0, perkType + "_SLOT", "ThePitPerks");
        }
    }
    
    public ItemBuilder getBarbarianAxe() {
        return Materials.IRON_AXE.getItem().modifyDamage(7).setUnbreakable(true).setLore(Main.getPermanentUpgrades().getStringList("Misc.PerkItemLore"));
    }
    
    public ItemBuilder getOlympusPotion() {
        final ItemBuilder setLore = Materials.POTION.getItem().setData(8203).flag().add(ItemFlag.values()).setDisplayName(this.olympusItemName).setLore(this.olympusItemLore);
        if (API.getVersionSupport().contains(11, 12, 13, 14, 15, 16)) {
            setLore.potion().setColor(Color.AQUA);
        }
        return setLore;
    }
    
    public ItemBuilder getMarathonBoots() {
        return Materials.LEATHER_BOOTS.getItem().leatherarmor().setColor(Color.WHITE).flag().add(ItemFlag.values());
    }
    
    public ItemBuilder getSoup() {
        return Materials.MUSHROOM_STEW.getItem().setDisplayName(this.soupItemName).setLore(this.soupItemLore);
    }
    
    public enum PerkType
    {
        GOLDEN_HEADS(false), 
        FISHING_ROD(false), 
        LAVA_BUCKET(false), 
        STRENGTH_CHAINING(false), 
        ENDLESS_QUIVER(false), 
        MINEMAN(false), 
        SAFETY_FIRST(false), 
        TRICKLE_DOWN(false), 
        LUCKY_DIAMOND(false), 
        SPAMMER(false), 
        BOUNTY_HUNTER(false), 
        STREAKER(false), 
        GLADIATOR(false), 
        VAMPIRE(false), 
        OVERHEAL(true), 
        BARBARIAN(true), 
        DIRTY(true), 
        RAMBO(true), 
        OLYMPUS(true), 
        FIRST_STRIKE(true), 
        ASSIST_STREAKER(true), 
        MARATHON(true), 
        SOUP(true), 
        RECON(true), 
        CONGLOMERATE(true), 
        KUNG_FU_KNOWLEDGE(true), 
        CO_OP_CAT(true), 
        THICK(true);
        
        private final boolean isPrestige;
        
        PerkType(final boolean isPrestige) {
            this.isPrestige = isPrestige;
        }
        
        public boolean isPrestige() {
            return this.isPrestige;
        }
    }
    
    public enum DescriptionType
    {
        LOCKED, 
        NOT_OWNED, 
        DEACTIVATED, 
        ACTIVATED, 
        SELECTED, 
        PERK_SLOT, 
        PERK_SLOT_NOT_OWNED
    }
    
    public enum BuyState
    {
        CONFIRM, 
        DENY
    }
    
    public enum ButtonType
    {
        BACK, 
        DEACTIVATE
    }
}
