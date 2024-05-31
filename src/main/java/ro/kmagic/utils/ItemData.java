package ro.kmagic.utils;

import org.bukkit.inventory.ItemStack;

public class ItemData {
    private final ItemStack itemStack;
    private final ItemType itemType;
    
    public ItemData(final ItemStack itemStack, final ItemType itemType) {
        this.itemStack = itemStack;
        this.itemType = itemType;
    }
    
    public ItemType getItemType() {
        return this.itemType;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    public enum ItemType {
        HELMET, 
        CHESTPLATE, 
        LEGGINGS, 
        BOOTS, 
        SWORD
    }
}
