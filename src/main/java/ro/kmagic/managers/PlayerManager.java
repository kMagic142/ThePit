package ro.kmagic.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import ro.kmagic.libapi.item.ItemBuilder;
import ro.kmagic.libapi.utils.FileManager;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.utils.ItemData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerManager extends FileManager {
    private final HashMap<UUID, ItemStack> cachedHelmet;
    private final HashMap<UUID, ArrayList<ItemData>> cachedTheBeast;
    
    public PlayerManager() {
        super("PlayerCache", "Cache");
        this.cachedHelmet = new HashMap<>();
        this.cachedTheBeast = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.restoreCachedHelmet(player);
        }
    }
    
    public void cacheHelmet(final Player player) {
        if (player.getInventory().getHelmet() != null) {
            this.cachedHelmet.put(player.getUniqueId(), player.getInventory().getHelmet());
            this.setItemStack(player.getName() + ".Helmet", player.getInventory().getHelmet());
        }
    }
    
    public void cacheTheBeast(final Player player, final ArrayList<ItemData> value) {
        this.cachedTheBeast.put(player.getUniqueId(), value);
        for (final ItemData itemData : value) {
            if (itemData != null && itemData.getItemStack() != null) {
                this.setItemStack(player.getName() + ".TheBeast." + itemData.getItemType(), itemData.getItemStack());
            }
        }
    }
    
    public void removeCachedHelmet(final Player player) {
        this.set(player.getName() + ".Helmet", null);
        this.cachedHelmet.remove(player.getUniqueId());
    }
    
    public void removeCacheTheBeast(final Player player) {
        this.set(player.getName() + ".TheBeast", null);
        this.cachedTheBeast.remove(player.getUniqueId());
    }
    
    public boolean isHelmetCached(final Player player) {
        return this.get(player.getName() + ".Helmet") != null && (this.contains(player.getName() + ".Helmet") || this.cachedHelmet.containsKey(player.getUniqueId()));
    }
    
    public boolean isTheBeastCached(final Player player) {
        return this.get(player.getName() + ".TheBeast") != null || this.cachedTheBeast.containsKey(player.getUniqueId());
    }
    
    public ItemStack getCachedHelmet(final Player player) {
        if (this.cachedHelmet.containsKey(player.getUniqueId())) {
            return this.cachedHelmet.get(player.getUniqueId());
        }
        return this.getItemStack(player.getName() + ".Helmet");
    }
    
    public ArrayList<ItemData> getCachedTheBeast(final Player player) {
        if (this.cachedTheBeast.containsKey(player.getUniqueId())) {
            return this.cachedTheBeast.get(player.getUniqueId());
        }
        final ArrayList<ItemData> list = new ArrayList<>();
        for (final ItemData.ItemType obj : ItemData.ItemType.values()) {
            if (this.get(player.getName() + ".TheBeast." + obj) != null && !this.getItemStack(player.getName() + ".TheBeast." + obj).getType().equals(Material.AIR)) {
                list.add(new ItemData(new ItemBuilder(this.getItemStack(player.getName() + ".TheBeast." + obj)).setUnbreakable(true).build(), obj));
            }
        }
        return list;
    }
    
    public void restoreCachedHelmet(final Player player) {
        if (!this.isHelmetCached(player)) {
            return;
        }
        player.getInventory().setHelmet(new ItemBuilder(this.getCachedHelmet(player)).setUnbreakable(true).build());
        this.removeCachedHelmet(player);
    }
    
    public void restoreTheBeastCached(final Player player) {
        if (!this.isTheBeastCached(player)) {
            return;
        }
        final PlayerInventory inventory = player.getInventory();
        for (final ItemData itemData : this.getCachedTheBeast(player)) {
            switch (itemData.getItemType()) {
                case HELMET: {
                    inventory.setHelmet(itemData.getItemStack());
                    continue;
                }
                case CHESTPLATE: {
                    inventory.setChestplate(itemData.getItemStack());
                    continue;
                }
                case LEGGINGS: {
                    inventory.setLeggings(itemData.getItemStack());
                    continue;
                }
                case BOOTS: {
                    if (!PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                        inventory.setBoots(itemData.getItemStack());
                        continue;
                    }
                    inventory.addItem(itemData.getItemStack());
                    continue;
                }
                case SWORD: {
                    inventory.setItem(0, itemData.getItemStack());
                }
            }
        }
        this.removeCacheTheBeast(player);
    }
}
