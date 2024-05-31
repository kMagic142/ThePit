package ro.kmagic.libapi.versionsupport.entity;

import org.bukkit.block.BlockFace;
import org.bukkit.ChatColor;
import java.util.UUID;
import org.bukkit.event.entity.EntityDamageEvent;
import java.util.ArrayList;
import ro.kmagic.libapi.API;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.bukkit.entity.EntityType;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import java.util.List;
import java.lang.reflect.Method;
import org.bukkit.entity.Entity;
import java.lang.reflect.Constructor;
import ro.kmagic.libapi.utils.Reflection;

public class EntityGlobal extends Reflection {
    private Constructor<?> nbtTagCompound;
    private Entity entity;
    private Object nmsEntity;
    private Method enumDirectionToString;
    private Method nbtTagCompoundGetByte;
    private Entities entityType;
    private Class<?> entityClass;
    private List<ArmorStand> customName;
    private List<String> customNameLines;

    public EntityGlobal(final Location location, final Entities entityType) {
        this.customName = null;
        this.customNameLines = null;
        try {
            this.entityType = entityType;
            this.entity = location.getWorld().spawnEntity(location.clone(), EntityType.valueOf(entityType.getBukkitName()));
            this.nbtTagCompound = this.getNMSClass("NBTTagCompound").getConstructors()[0];
            this.nmsEntity = this.getOBC("entity.CraftEntity").getMethod("getHandle", new Class[0]).invoke(this.entity);
            this.nbtTagCompoundGetByte = this.getNMSClass("NBTTagCompound").getMethod("getByte", String.class);
            this.entityClass = this.getNMSClass("Entity");
            this.enumDirectionToString = this.getNMSClass("EnumDirection").getMethod("toString");
        } catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }

    public Location getLocation() {
        return this.entity.getLocation();
    }

    public void setVelocity(final Vector velocity) {
        this.entity.setVelocity(velocity);
    }

    public Vector getVelocity() {
        return this.entity.getVelocity();
    }

    public boolean isOnGround() {
        return this.entity.isOnGround();
    }

    public World getWorld() {
        return this.entity.getWorld();
    }

    public void teleport(final Location location) {
        this.entity.teleport(location);
    }

    public void teleport(final Player player) {
        this.entity.teleport(player.getLocation());
    }

    public List<Entity> getNearbyEntities(final double n, final double n2, final double n3) {
        return this.entity.getNearbyEntities(n, n2, n3);
    }

    public int getEntityId() {
        return this.entity.getEntityId();
    }

    public int getFireTicks() {
        return this.entity.getFireTicks();
    }

    public int getMaxFireTicks() {
        return this.entity.getFireTicks();
    }

    public void setFireTicks(final int fireTicks) {
        this.entity.setFireTicks(fireTicks);
    }

    public void remove() {
        this.entity.remove();
        if (this.customName == null) {
            return;
        }
        this.customNameLines.clear();
        final Iterator<ArmorStand> iterator = this.customName.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
    }

    public boolean isDead() {
        return this.entity.isDead();
    }

    public boolean isValid() {
        return this.entity.isValid();
    }

    public List<Entity> getPassengers() {

        final ArrayList<Entity> list = new ArrayList<Entity>();
        list.add(this.entity.getPassenger());
        return list;

    }

    public void addPassenger(final Entity passenger) {
        this.entity.setPassenger(passenger);
    }

    public void removePassenger(final Entity entity) {
        this.entity.setPassenger(null);
        return;
    }

    public boolean hasPassenger() {
        return this.entity.isEmpty();
    }

    public void ejectPassengers() {
        this.entity.eject();
    }

    public float getFallDistance() {
        return this.entity.getFallDistance();
    }

    public void setFallDistance(final float fallDistance) {
        this.entity.setFallDistance(fallDistance);
    }

    public void setLastDamageCause(final EntityDamageEvent lastDamageCause) {
        this.entity.setLastDamageCause(lastDamageCause);
    }

    public EntityDamageEvent getLastDamageCause() {
        return this.entity.getLastDamageCause();
    }

    public UUID getUniqueId() {
        return this.entity.getUniqueId();
    }

    public int getTicksLived() {
        return this.entity.getTicksLived();
    }

    public void setTicksLived(final int ticksLived) {
        this.entity.setTicksLived(ticksLived);
    }

    public Entities getEntityType() {
        return this.entityType;
    }

    public boolean isInsideVehicle() {
        return this.entity.isInsideVehicle();
    }

    public void leaveVehicle() {
        this.entity.leaveVehicle();
    }

    public void setName(final String customName) {
        this.entity.setCustomName(customName);
    }

    public void setName(final List<String> list) {
        if (this.customName != null) {
            this.customNameLines.clear();
            final Iterator<ArmorStand> iterator = this.customName.iterator();
            while (iterator.hasNext()) {
                iterator.next().remove();
            }
        }
        this.customName = new ArrayList<ArmorStand>();
        this.customNameLines = new ArrayList<String>();
        final Location add = this.getLocation().clone().add(0.0, 1.6, 0.0).add(0.0, 0.23 * list.size() - 1.97, 0.0);
        for (int i = 0; i < list.size(); ++i) {
            final ArmorStand armorStand = (ArmorStand) add.getWorld().spawn(add, (Class) ArmorStand.class);
            final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', list.get(i));
            this.customNameLines.add(i, translateAlternateColorCodes);
            armorStand.setCustomName(translateAlternateColorCodes);
            armorStand.setCustomNameVisible(true);
            armorStand.setVisible(false);
            this.customName.add(armorStand);
            add.add(0.0, -0.23, 0.0);
        }
    }

    public String getName() {
        return this.entity.getCustomName();
    }

    public List<String> getNameList() {
        return this.customNameLines;
    }

    public void setNameVisible(final boolean customNameVisible) {
        this.entity.setCustomNameVisible(customNameVisible);
    }

    public boolean isNameVisible() {
        return this.entity.isCustomNameVisible();
    }

    public void setInvulnerable(final boolean invulnerable) {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            instance.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance, "Invulnerable", (byte) (invulnerable ? 1 : 0));
            this.entityClass.getMethod("f", instance.getClass()).invoke(this.nmsEntity, instance);
        } catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isInvulnerable() {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            return (byte) this.nbtTagCompoundGetByte.invoke(instance, "Invulnerable") == 1;
        } catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }


    }

    
    public void setSilent(final boolean silent) {

        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            instance.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance, "Silent", (byte) (silent ? 1 : 0));
            this.entityClass.getMethod("f", instance.getClass()).invoke(this.nmsEntity, instance);
        } catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isSilent() {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            return (byte) this.nbtTagCompoundGetByte.invoke(instance, "Silent") == 1;
        } catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }

    }
    
    public void setGravity(final boolean gravity) {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            instance.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance, "Gravity", (byte) (gravity ? 1 : 0));
            this.entityClass.getMethod("f", instance.getClass()).invoke(this.nmsEntity, instance);
        } catch (Exception ex) {
            this.printReflectionError(ex);
        }

    }
    
    public boolean hasGravity() {
        try {
            final Object instance = this.nbtTagCompound.newInstance();
            this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
            return (byte) this.nbtTagCompoundGetByte.invoke(instance, "Gravity") == 1;
        } catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setAi(final boolean b) {
        if (!API.getVersionSupport().contains(16)) {
            try {
                final Object instance = this.nbtTagCompound.newInstance();
                this.entityClass.getMethod("c", instance.getClass()).invoke(this.nmsEntity, instance);
                instance.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance, "NoAI", (byte)(b ? 0 : 1));
                this.entityClass.getMethod("f", instance.getClass()).invoke(this.nmsEntity, instance);
            }
            catch (Exception ex) {
                this.printReflectionError(ex);
            }
        }
        else {
            try {
                final Object instance2 = this.nbtTagCompound.newInstance();
                this.entityClass.getMethod("a_", instance2.getClass()).invoke(this.nmsEntity, instance2);
                instance2.getClass().getMethod("setByte", String.class, Byte.TYPE).invoke(instance2, "NoAI", (byte)(b ? 0 : 1));
                this.entityClass.getMethod("load", instance2.getClass()).invoke(this.nmsEntity, instance2);
            }
            catch (Exception ex2) {
                this.printReflectionError(ex2);
            }
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
    
    public Entity getEntity() {
        return this.entity;
    }
}
