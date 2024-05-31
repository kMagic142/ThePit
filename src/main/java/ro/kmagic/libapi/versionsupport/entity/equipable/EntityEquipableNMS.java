package ro.kmagic.libapi.versionsupport.entity.equipable;

import org.bukkit.Material;
import org.bukkit.inventory.EntityEquipment;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.item.ItemBuilder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import ro.kmagic.libapi.utils.Reflection;

public class EntityEquipableNMS extends Reflection
{
    private Constructor<?> packetPlayOutEntityEquipment;
    private Method getEquipment;
    private Method asNMSCopy;
    private Object entity;
    private Object id;
    private Player p;
    
    public EntityEquipableNMS(final Object o, final Player p2) {
        try {
            this.packetPlayOutEntityEquipment = this.getNMSClass("PacketPlayOutEntityEquipment").getConstructors()[1];
            this.getEquipment = this.getOBC("entity.CraftLivingEntity").getMethod("getEquipment");
            this.asNMSCopy = this.getOBC("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class);
            this.entity = this.getNMSClass("Entity").getMethod("getBukkitEntity", new Class[0]).invoke(o);
            this.id = o.getClass().getMethod("getId", new Class[0]).invoke(o);
            this.p = p2;
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void set(final EquipmentSlot equipmentSlot, final ItemBuilder itemBuilder) {
        try {
            final Object invoke = this.getEquipment.invoke(this.entity);
            final ItemStack build = itemBuilder.build();
            final Object invoke2 = this.asNMSCopy.invoke(null, build);
            Object o;
            if (!API.getVersion().equals("v1_8_R3")) {
                EntityEquipment.class.getMethod("set" + equipmentSlot.getName(), ItemStack.class).invoke(invoke, build);
                final Class<?> nmsClass = this.getNMSClass("EnumItemSlot");
                o = this.packetPlayOutEntityEquipment.newInstance(this.id, nmsClass.getMethod("valueOf", String.class).invoke(nmsClass.getEnumConstants()[0], equipmentSlot.toString()), invoke2);
            }
            else {
                String str = equipmentSlot.getName();
                switch (equipmentSlot) {
                    case RIGHT_HAND: {
                        str = equipmentSlot.getLegacy();
                        break;
                    }
                    case LEFT_HAND: {
                        return;
                    }
                }
                EntityEquipment.class.getMethod("set" + str, ItemStack.class).invoke(invoke, build);
                o = this.packetPlayOutEntityEquipment.newInstance(this.id, equipmentSlot.getSlot(), invoke2);
            }
            this.sendPacket(this.p, o);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public ItemStack get(final EquipmentSlot equipmentSlot) {
        try {
            final Object invoke = this.getEquipment.invoke(this.entity);
            String str = equipmentSlot.getName();
            if (API.getVersion().equals("v1_8_R3")) {
                switch (equipmentSlot) {
                    case RIGHT_HAND: {
                        str = equipmentSlot.getLegacy();
                        break;
                    }
                    case LEFT_HAND: {
                        return new ItemStack(Material.AIR);
                    }
                }
            }
            return (ItemStack)EntityEquipment.class.getMethod("get" + str, new Class[0]).invoke(invoke, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return new ItemStack(Material.AIR);
        }
    }
}
