package ro.kmagic.managers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.utils.FileManager;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import ro.kmagic.Main;
import ro.kmagic.utils.EnderchestItemData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class EnderchestManager extends FileManager implements Listener {
    private final ArrayList<UUID> cooldown;
    private final ArrayList<UUID> inEnderchest;
    private final Material enderChest;
    private final String notEnoughLevel;
    private final String cooldownMsg;
    private final String invalidItemMessage;
    private final long cooldownTick;
    private final boolean isCacheEnderchestEnabled;
    private final ArrayList<Material> allowedItems;
    
    public EnderchestManager() {
        super("EnderchestCache", "Cache");
        this.cooldown = new ArrayList<UUID>();
        this.inEnderchest = new ArrayList<UUID>();
        this.allowedItems = new ArrayList<Material>(Arrays.asList(Materials.AIR.getItem().getMaterial(), Materials.DIAMOND_HELMET.getItem().getMaterial(), Materials.DIAMOND_CHESTPLATE.getItem().getMaterial(), Materials.DIAMOND_LEGGINGS.getItem().getMaterial(), Materials.DIAMOND_BOOTS.getItem().getMaterial(), Materials.DIAMOND_SWORD.getItem().getMaterial(), Materials.IRON_HELMET.getItem().getMaterial(), Materials.IRON_CHESTPLATE.getItem().getMaterial(), Materials.IRON_LEGGINGS.getItem().getMaterial(), Materials.IRON_BOOTS.getItem().getMaterial(), Materials.OBSIDIAN.getItem().getMaterial()));
        API.registerEvent(this);
        this.enderChest = Materials.ENDER_CHEST.getItem().getMaterial();
        this.notEnoughLevel = Main.getMessages().getString("Level.Not-Enough");
        this.cooldownMsg = Main.getEnderchest().getString("Cooldown.Message");
        this.cooldownTick = Main.getEnderchest().getInt("Cooldown.Seconds") * 20L;
        this.isCacheEnderchestEnabled = Main.getEnderchest().getBoolean("Cache-Enderchests");
        this.invalidItemMessage = Main.getEnderchest().getString("Invalid-Item");
    }
    
    @EventHandler
    private void onPlayerInteract(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) || !playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK) || !playerInteractEvent.getClickedBlock().getType().equals(this.enderChest)) {
            return;
        }
        if (!Main.getArena().displayFeature(player, 15)) {
            player.sendMessage(this.notEnoughLevel);
            playerInteractEvent.setCancelled(true);
            Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
            return;
        }
        if (this.cooldown.contains(player.getUniqueId())) {
            playerInteractEvent.setCancelled(true);
            player.sendMessage(this.cooldownMsg);
            Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
            return;
        }
        this.cooldown.add(player.getUniqueId());
        this.inEnderchest.add(player.getUniqueId());
        new BukkitRunnable() {
            public void run() {
                EnderchestManager.this.cooldown.remove(player.getUniqueId());
            }
        }.runTaskLater(API.getPlugin(), this.cooldownTick);
    }
    
    @EventHandler
    private void inventoryClose(final InventoryCloseEvent inventoryCloseEvent) {
        final Player player = (Player)inventoryCloseEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        this.inEnderchest.remove(player.getUniqueId());
    }
    
    public void onJoin(final Player player) {
        if (!this.isCacheEnderchestEnabled) {
            return;
        }
        if (!this.contains("Enderchest." + player.getUniqueId())) {
            return;
        }
        if (this.getString("Enderchest." + player.getUniqueId()).isEmpty()) {
            return;
        }
        player.getEnderChest().clear();
        new BukkitRunnable() {
            final /* synthetic */ Inventory val$enderchest = player.getEnderChest();
            
            public void run() {
                for (final EnderchestItemData enderchestItemData : EnderchestManager.this.getItemsFromFile(EnderchestManager.this.getString("Enderchest." + player.getUniqueId()))) {
                    this.val$enderchest.setItem(enderchestItemData.getSlot(), enderchestItemData.getItemStack());
                }
            }
        }.runTaskLater(API.getPlugin(), 10L);
    }
    
    public void onQuit(final Player player) {
        if (!this.isCacheEnderchestEnabled) {
            return;
        }
        final ArrayList<EnderchestItemData> list = new ArrayList<EnderchestItemData>();
        final ArrayList<ItemStack> list2 = new ArrayList<ItemStack>(Arrays.asList(player.getEnderChest().getContents()));
        for (int i = 0; i <= list2.size() - 1; ++i) {
            if (list2.get(i) != null) {
                list.add(new EnderchestItemData(list2.get(i), i, Materials.getItemStackMaterial(list2.get(i))));
            }
        }
        this.set("Enderchest." + player.getUniqueId(), this.saveToFile(list));
    }
    
    public ArrayList<Material> getAllowedItems() {
        return this.allowedItems;
    }
    
    @EventHandler
    private void onInventoryInteract(final InventoryClickEvent inventoryClickEvent) {
        if (!(inventoryClickEvent.getWhoClicked() instanceof Player)) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null) {
            return;
        }
        if (!inventoryClickEvent.getClick().isShiftClick() && !inventoryClickEvent.getClickedInventory().getType().equals(InventoryType.ENDER_CHEST)) {
            return;
        }
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (!this.inEnderchest.contains(player.getUniqueId())) {
            return;
        }
        boolean b = false;
        boolean b2 = false;
        final ClickType click = inventoryClickEvent.getClick();
        if (click.equals(ClickType.NUMBER_KEY)) {
            switch (inventoryClickEvent.getClickedInventory().getType()) {
                case PLAYER: {
                    final ItemStack item = player.getInventory().getItem(inventoryClickEvent.getHotbarButton());
                    if (item != null && this.allowedItems.contains(item.getType())) {
                        b = true;
                        break;
                    }
                    break;
                }
                case ENDER_CHEST: {
                    final ItemStack item2 = inventoryClickEvent.getClickedInventory().getItem(inventoryClickEvent.getSlot());
                    if (item2 != null && this.allowedItems.contains(item2.getType())) {
                        b = true;
                        break;
                    }
                    break;
                }
            }
            b2 = true;
        }
        else if (click.isShiftClick()) {
            if (inventoryClickEvent.getCurrentItem() != null && this.allowedItems.contains(inventoryClickEvent.getCurrentItem().getType())) {
                b = true;
            }
        }
        else if (inventoryClickEvent.getCursor() != null && this.allowedItems.contains(inventoryClickEvent.getCursor().getType()) && inventoryClickEvent.getCurrentItem() != null && this.allowedItems.contains(inventoryClickEvent.getCurrentItem().getType())) {
            b = true;
        }
        if (!b) {
            inventoryClickEvent.setCancelled(true);
            boolean b3 = false;
            if (b2) {
                final ItemStack item3 = player.getInventory().getItem(inventoryClickEvent.getHotbarButton());
                if (item3 != null && !item3.getType().equals(Material.AIR)) {
                    b3 = true;
                }
            }
            else if (click.isShiftClick()) {
                final ItemStack currentItem = inventoryClickEvent.getCurrentItem();
                if (currentItem != null && !currentItem.getType().equals(Material.AIR)) {
                    b3 = true;
                }
            }
            else if (inventoryClickEvent.getCursor() != null && !inventoryClickEvent.getCursor().getType().equals(Material.AIR)) {
                b3 = true;
            }
            if (b3) {
                Sounds.ENDERMAN_TELEPORT.getSound().play(player, 3.0f, 0.0f);
                player.sendMessage(this.invalidItemMessage);
            }
        }
    }
    
    @EventHandler
    private void onInventoryDrag(final InventoryDragEvent inventoryDragEvent) {
        if (!Main.getArena().getPlayers().contains(inventoryDragEvent.getWhoClicked().getUniqueId())) {
            return;
        }
        if (!inventoryDragEvent.getInventory().getType().equals(InventoryType.ENDER_CHEST)) {
            return;
        }
        boolean b = false;
        for (final Material material : this.allowedItems) {
            if (inventoryDragEvent.getCursor() != null && inventoryDragEvent.getCursor().getType().equals(material)) {
                b = true;
            }
            if (inventoryDragEvent.getOldCursor() != null && inventoryDragEvent.getOldCursor().getType().equals(material)) {
                b = true;
            }
        }
        if (!b) {
            inventoryDragEvent.setCancelled(true);
        }
    }
    
    private String saveToFile(final List<EnderchestItemData> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final EnderchestItemData enderchestItemData : list) {
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
                bukkitObjectOutputStream.writeInt(1);
                bukkitObjectOutputStream.writeObject(enderchestItemData.getItemStack());
                bukkitObjectOutputStream.close();
                list2.add("Slot=" + enderchestItemData.getSlot() + "/;;;/Material=" + enderchestItemData.getMaterial() + "/;;;/" + Base64Coder.encodeLines(byteArrayOutputStream.toByteArray()));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        String s = "";
        for (int i = 0; i < list2.size(); ++i) {
            if (i != list2.size() - 1) {
                s = s + list2.get(i) + "/////";
            }
            else {
                s += list2.get(i);
            }
        }
        return s;
    }
    
    private List<EnderchestItemData> getItemsFromFile(final String s) {
        if (s == null) {
            return new ArrayList<EnderchestItemData>();
        }
        if (s.isEmpty()) {
            return new ArrayList<EnderchestItemData>();
        }
        final ArrayList<EnderchestItemData> list = new ArrayList<EnderchestItemData>();
        for (final String s2 : new ArrayList<String>(Arrays.asList(s.split("/////")))) {
            ItemStack itemStack = null;
            try {
                final BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(new ByteArrayInputStream(Base64Coder.decodeLines(s2.split("/;;;/")[2])));
                final ItemStack[] array = new ItemStack[bukkitObjectInputStream.readInt()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = (ItemStack)bukkitObjectInputStream.readObject();
                }
                bukkitObjectInputStream.close();
                itemStack = array[0];
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            list.add(new EnderchestItemData(itemStack, Integer.parseInt(s2.split("/;;;/")[0].replace("Slot=", "")), Materials.valueOf(s2.split("/;;;/")[1].replace("Material=", ""))));
        }
        return list;
    }
}
