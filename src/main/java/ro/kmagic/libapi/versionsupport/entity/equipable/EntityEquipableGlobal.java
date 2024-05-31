package ro.kmagic.libapi.versionsupport.entity.equipable;

import ro.kmagic.libapi.utils.CustomError;

import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.item.ItemBuilder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EntityEquipment;

public class EntityEquipableGlobal
{
    private final EntityEquipment entityEquipment;
    
    public EntityEquipableGlobal(final Entity entity) {
        this.entityEquipment = ((LivingEntity)entity).getEquipment();
    }
    
    public void set(final EquipmentSlot equipmentSlot, final ItemBuilder itemBuilder) {
        final ItemStack build = itemBuilder.build();
        String name = "set" + equipmentSlot.getName();
        if (API.getVersion().equals("v1_8_R3")) {
            switch (equipmentSlot) {
                case RIGHT_HAND: {
                    name = equipmentSlot.getLegacy();
                    break;
                }
                case LEFT_HAND: {
                    return;
                }
            }
        }
        try {
            EntityEquipment.class.getMethod(name, ItemStack.class).invoke(this.entityEquipment, build);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public ItemStack get(final EquipmentSlot equipmentSlot) {
        String name = "get" + equipmentSlot.getName();
        if (API.getVersion().equals("v1_8_R3")) {
            switch (equipmentSlot) {
                case RIGHT_HAND: {
                    name = equipmentSlot.getLegacy();
                    break;
                }
                case LEFT_HAND: {
                    return new ItemStack(Material.AIR);
                }
            }
        }
        try {
            return (ItemStack)EntityEquipment.class.getMethod(name, new Class[0]).invoke(this.entityEquipment, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return new ItemStack(Material.AIR);
        }
    }
    
    protected void printReflectionError(final Exception ex) {
        CustomError.print(ex, this.getClass(), Collections.singletonList("Couldn't get Class Information"));
    }
}
