package ro.kmagic.utils;

import ro.kmagic.libapi.item.ItemBuilder;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import org.bukkit.inventory.ItemStack;

public class EnderchestItemData {
    private final int slot;
    private final ItemStack itemStack;
    private final Materials material;
    
    public EnderchestItemData(final ItemStack itemStack, final int slot, final Materials material) {
        this.slot = slot;
        this.itemStack = new ItemBuilder(itemStack).setMaterial(material.getItem().getMaterial()).setData(material.getData()).build();
        this.material = material;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    public Materials getMaterial() {
        return this.material;
    }
}
