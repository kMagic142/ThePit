package ro.kmagic.libapi.versionsupport.entity.ageable;

import java.lang.reflect.Constructor;
import org.bukkit.entity.Player;
import ro.kmagic.libapi.utils.Reflection;

public class EntityAgeableNMS extends Reflection
{
    private Player p;
    private Object entity;
    private Object id;
    private Object dataWatcher;
    private Constructor<?> packetPlayOutEntityMetadata;
    private Class<?> craftAgeable;
    
    public EntityAgeableNMS(final Object obj, final Player p2) {
        try {
            this.p = p2;
            this.entity = this.getNMSClass("Entity").getMethod("getBukkitEntity", new Class[0]).invoke(obj);
            this.packetPlayOutEntityMetadata = this.getNMSClass("PacketPlayOutEntityMetadata").getConstructors()[1];
            this.id = obj.getClass().getMethod("getId", new Class[0]).invoke(obj);
            this.dataWatcher = obj.getClass().getMethod("getDataWatcher", new Class[0]).invoke(obj);
            this.craftAgeable = this.getOBC("entity.CraftAgeable");
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void setBaby() {
        try {
            this.craftAgeable.getMethod("setBaby", new Class[0]).invoke(this.entity);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void setAdult() {
        try {
            this.craftAgeable.getMethod("setAdult", new Class[0]).invoke(this.entity);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isAdult() {
        try {
            return (boolean)this.craftAgeable.getMethod("isAdult", new Class[0]).invoke(this.entity, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
}
