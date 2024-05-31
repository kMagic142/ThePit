package ro.kmagic.libapi.versionsupport.entity;

import org.bukkit.block.BlockFace;
import org.bukkit.ChatColor;
import java.util.UUID;
import org.bukkit.event.entity.EntityDamageEvent;
import ro.kmagic.libapi.API;
import java.util.Iterator;
import java.util.ArrayList;
import org.bukkit.entity.Entity;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import ro.kmagic.libapi.versionsupport.entity.entities.armorstand.ArmorStandNMS;
import java.util.List;
import org.bukkit.entity.Player;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import ro.kmagic.libapi.utils.Reflection;

public class EntityNMS extends Reflection
{
    public Constructor<?> packetPlayOutEntityVelocity;
    public Constructor<?> packetPlayOutEntityDestroy;
    public Constructor<?> packetPlayOutMount;
    public Constructor<?> packetPlayOutEntityMetadata;
    public Constructor<?> nbtTagCompound;
    private Method enumDirectionToString;
    private Method nbtTagCompoundGetByte;
    private Class<?> craftEntity;
    private Class<?> entityClass;
    public Object nmsEntity;
    public Object entity;
    public Object id;
    public Object dataWatcher;
    private Entities entityType;
    private Player p;
    private List<ArmorStandNMS> customName;
    private List<String> customNameLines;
    
    public EntityNMS(final Player p3, final Location location, final Entities entityType) {
        this.customName = null;
        this.customNameLines = null;
        try {
            this.entityType = entityType;
            this.p = p3;
            this.packetPlayOutEntityVelocity = this.getNMSClass("PacketPlayOutEntityVelocity").getConstructors()[1];
            this.packetPlayOutEntityDestroy = this.getNMSClass("PacketPlayOutEntityDestroy").getConstructors()[1];
            this.packetPlayOutEntityMetadata = this.getNMSClass("PacketPlayOutEntityMetadata").getConstructors()[1];
            this.nbtTagCompound = this.getNMSClass("NBTTagCompound").getConstructors()[0];
            this.craftEntity = this.getOBC("entity.CraftEntity");
            this.entityClass = this.getNMSClass("Entity");
            this.nmsEntity = this.getNMSClass("Entity" + entityType.getNMSName()).getDeclaredConstructors()[0].newInstance(this.getOBC("CraftWorld").getMethod("getHandle", new Class[0]).invoke(location.getWorld()));
            this.entity = this.getNMSClass("Entity").getMethod("getBukkitEntity", new Class[0]).invoke(this.nmsEntity);
            this.id = this.entity.getClass().getMethod("getId", new Class[0]).invoke(this.entity);
            this.dataWatcher = this.entity.getClass().getMethod("getDataWatcher", new Class[0]).invoke(this.entity);
            this.enumDirectionToString = this.getNMSClass("EnumDirection").getMethod("toString");
            this.nbtTagCompoundGetByte = this.getNMSClass("NBTTagCompound").getMethod("getByte", String.class);
            this.entity.getClass().getMethod("setLocation", Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE).invoke(this.entity, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            this.sendPacket(p3, this.getNMSClass("PacketPlayOutSpawnEntityLiving").getConstructors()[1].newInstance(this.nmsEntity));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public Location getLocation() {
        try {
            return (Location)this.craftEntity.getMethod("getLocation", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public void setVelocity(final Vector vector, final Player player) {
        try {
            this.craftEntity.getMethod("setVelocity", Vector.class).invoke(this.entity, vector);
            this.sendPacket(player, this.packetPlayOutEntityVelocity.newInstance(this.entity));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public Vector getVelocity() {
        try {
            return (Vector)this.craftEntity.getMethod("getVelocity", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public boolean isOnGround() {
        try {
            return (boolean)this.craftEntity.getMethod("isOnGround", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public World getWorld() {
        try {
            return (World)this.craftEntity.getMethod("getWorld", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public void teleport(final Location location) {
        try {
            this.craftEntity.getMethod("teleport", Location.class).invoke(this.entity, location);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void teleport(final Player player) {
        try {
            this.craftEntity.getMethod("teleport", Location.class).invoke(this.entity, player.getLocation());
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public List<Entity> getNearbyEntities(final double d, final double d2, final double d3) {
        try {
            return (List<Entity>)this.craftEntity.getMethod("getNearbyEntities", Double.TYPE, Double.TYPE, Double.TYPE).invoke(this.entity, d, d2, d3);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return new ArrayList<Entity>();
        }
    }
    
    public int getEntityId() {
        try {
            return (int)this.craftEntity.getMethod("getEntityId", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return 0;
        }
    }
    
    public int getFireTicks() {
        try {
            return (int)this.craftEntity.getMethod("getFireTicks", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return 0;
        }
    }
    
    public int getMaxFireTicks() {
        try {
            return (int)this.craftEntity.getMethod("getMaxFireTicks", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return 0;
        }
    }
    
    public int setFireTicks(final int i) {
        try {
            return (int)this.craftEntity.getMethod("setFireTicks", Integer.TYPE).invoke(this.entity, i);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return 0;
        }
    }
    
    public void remove() {
        try {
            this.sendPacket(this.p, this.packetPlayOutEntityDestroy.newInstance(new int[] { (int)this.id }));
            if (this.customName == null) {
                return;
            }
            this.customNameLines.clear();
            final Iterator<ArmorStandNMS> iterator = this.customName.iterator();
            while (iterator.hasNext()) {
                iterator.next().remove();
            }
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isDead() {
        try {
            return (boolean)this.craftEntity.getMethod("isDead", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public boolean isValid() {
        try {
            return (boolean)this.craftEntity.getMethod("isValid", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public List<Entity> getPassengers() {
        try {
            if (API.getVersionSupport().contains(8, 9, 10)) {
                final ArrayList<Entity> list = new ArrayList<Entity>();
                list.add((Entity)this.craftEntity.getMethod("getPassenger", new Class[0]).invoke(this.entity, new Object[0]));
                return list;
            }
            return (List<Entity>)this.craftEntity.getMethod("getPassengers", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public void addPassenger(final Object o, final Player player) {
        try {
            ArrayList.class.getMethod("add", Object.class).invoke(this.entityClass.getField("passengers").get(this.nmsEntity), o);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean hasPassenger() {
        try {
            return (boolean)this.craftEntity.getMethod("isEmpty", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void ejectPassengers(final Player player) {
        try {
            ArrayList.class.getMethod("clear", new Class[0]).invoke(this.entityClass.getField("passengers").get(this.nmsEntity));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public float getFallDistance() {
        try {
            return (float)this.craftEntity.getMethod("getFallDistance", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return 0.0f;
        }
    }
    
    public void setFallDistance(final float f) {
        try {
            this.craftEntity.getMethod("setFallDistance", Float.TYPE).invoke(this.entity, f);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void setLastDamageCause(final EntityDamageEvent entityDamageEvent) {
        try {
            this.craftEntity.getMethod("setFallDistance", EntityDamageEvent.class).invoke(this.entity, entityDamageEvent);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public EntityDamageEvent getLastDamageCause() {
        try {
            return (EntityDamageEvent)this.craftEntity.getMethod("getLastDamageCause", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public UUID getUniqueId() {
        try {
            return (UUID)this.craftEntity.getMethod("getUniqueId", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public int getTicksLived() {
        try {
            return (int)this.craftEntity.getMethod("getTicksLived", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return 0;
        }
    }
    
    public void setTicksLived(final int i) {
        try {
            this.craftEntity.getMethod("setTicksLived", Integer.TYPE).invoke(this.entity, i);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public Entities getEntityType() {
        return this.entityType;
    }
    
    public boolean isInsideVehicle() {
        try {
            return (boolean)this.craftEntity.getMethod("isInsideVehicle", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void leaveVehicle(final Object obj, final Object o, final Player player) {
        try {
            ArrayList.class.getMethod("remove", Object.class).invoke(this.entityClass.getField("passengers").get(obj), o);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void setName(final String s) {
        try {
            this.craftEntity.getMethod("setCustomName", String.class).invoke(this.entity, s);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void setName(final List<String> list) {
        if (this.customName != null) {
            this.customNameLines.clear();
            final Iterator<ArmorStandNMS> iterator = this.customName.iterator();
            while (iterator.hasNext()) {
                iterator.next().remove();
            }
        }
        this.customName = new ArrayList<ArmorStandNMS>();
        this.customNameLines = new ArrayList<String>();
        final Location add = this.getLocation().clone().add(0.0, 1.6, 0.0).add(0.0, 0.23 * list.size() - 1.97, 0.0);
        for (int i = 0; i < list.size(); ++i) {
            final ArmorStandNMS armorStandNMS = new ArmorStandNMS(this.p, add);
            final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', list.get(i));
            this.customNameLines.add(i, translateAlternateColorCodes);
            armorStandNMS.setName(translateAlternateColorCodes);
            armorStandNMS.setNameVisible(true);
            armorStandNMS.setGravity(false);
            armorStandNMS.setVisible(false);
            armorStandNMS.setHitBox(false);
            this.customName.add(armorStandNMS);
        }
    }
    
    public String getName() {
        try {
            return (String)this.craftEntity.getMethod("getCustomName", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public void setNameVisible(final boolean b) {
        try {
            this.craftEntity.getMethod("setCustomNameVisible", Boolean.TYPE).invoke(this.entity, b);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isNameVisible(final Object obj) {
        try {
            return (boolean)this.craftEntity.getMethod("isCustomNameVisible", new Class[0]).invoke(obj, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setGlowing(final boolean b) {
        if (API.getVersionSupport().contains(8)) {
            return;
        }
        try {
            this.craftEntity.getMethod("setGlowing", Boolean.TYPE).invoke(this.entity, b);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isGlowing() {
        if (API.getVersionSupport().contains(8)) {
            return false;
        }
        try {
            return (boolean)this.craftEntity.getMethod("isGlowing", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setInvulnerable(final boolean b) {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            instance.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance, "Invulnerable", (byte)(b ? 1 : 0));
            this.entityClass.getMethod("f", instance.getClass()).invoke(this.nmsEntity, instance);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isInvulnerable() {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            return (byte)this.nbtTagCompoundGetByte.invoke(instance, "Invulnerable") == 1;
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setSilent(final boolean b) {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            instance.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance, "Silent", (byte)(b ? 1 : 0));
            this.entityClass.getMethod("f", instance.getClass()).invoke(this.nmsEntity, instance);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isSilent() {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            return (byte)this.nbtTagCompoundGetByte.invoke(instance, "Silent") == 1;
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setGravity(final boolean b) {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            instance.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance, "Gravity", (byte)(b ? 1 : 0));
            this.entityClass.getMethod("f", instance.getClass()).invoke(this.nmsEntity, instance);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean hasGravity() {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            return (byte)this.nbtTagCompoundGetByte.invoke(instance, "Gravity") == 1;
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setAi(final boolean b) {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            instance.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance, "NoAI", (byte)(b ? 1 : 0));
            this.entityClass.getMethod("f", instance.getClass()).invoke(this.nmsEntity, instance);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean hasAi() {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            return (byte)this.nbtTagCompoundGetByte.invoke(instance, "NoAI") == 1;
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public BlockFace getFacing() {
        try {
            return BlockFace.valueOf(((String)this.enumDirectionToString.invoke(this.entityClass.getMethod("getDirection", new Class[0]).invoke(this.nmsEntity), new Object[0])).toUpperCase());
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public Object getEntity() {
        return this.entity;
    }
}
