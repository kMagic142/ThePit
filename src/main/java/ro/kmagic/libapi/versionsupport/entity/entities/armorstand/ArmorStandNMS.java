package ro.kmagic.libapi.versionsupport.entity.entities.armorstand;

import org.bukkit.util.EulerAngle;
import ro.kmagic.libapi.versionsupport.entity.Entities;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ro.kmagic.libapi.versionsupport.entity.EntityNMS;

public class ArmorStandNMS extends EntityNMS
{
    private final Object armorStand;
    private final Player p;
    
    public ArmorStandNMS(final Player p2, final Location location) {
        super(p2, location, Entities.ARMOR_STAND);
        this.p = p2;
        this.armorStand = this.getEntity();
    }
    
    public EulerAngle get(final EulerAngleType eulerAngleType) {
        try {
            return (EulerAngle)this.armorStand.getClass().getMethod("get" + eulerAngleType.getPoseName(), new Class[0]).invoke(this.armorStand, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return null;
        }
    }
    
    public void set(final EulerAngleType eulerAngleType, final EulerAngle eulerAngle) {
        try {
            this.armorStand.getClass().getMethod("set" + eulerAngleType.getPoseName(), EulerAngle.class).invoke(this.armorStand, eulerAngle);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean hasBasePlate() {
        try {
            return (boolean)this.armorStand.getClass().getMethod("hasBasePlate", new Class[0]).invoke(this.armorStand, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setBasePlate(final boolean b) {
        try {
            this.armorStand.getClass().getMethod("setBasePlate", Boolean.TYPE).invoke(this.armorStand, b);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return (boolean)this.armorStand.getClass().getMethod("isVisible", new Class[0]).invoke(this.armorStand, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setVisible(final boolean b) {
        try {
            this.armorStand.getClass().getMethod("setVisible", Boolean.TYPE).invoke(this.armorStand, b);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean hasArms() {
        try {
            return (boolean)this.armorStand.getClass().getMethod("hasArms", new Class[0]).invoke(this.armorStand, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setArms(final boolean b) {
        try {
            this.armorStand.getClass().getMethod("setArms", Boolean.TYPE).invoke(this.armorStand, b);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean isSmall() {
        try {
            return (boolean)this.armorStand.getClass().getMethod("isSmall", new Class[0]).invoke(this.armorStand, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setSmall(final boolean b) {
        try {
            this.armorStand.getClass().getMethod("setSmall", Boolean.TYPE).invoke(this.armorStand, b);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public boolean hasHitBox() {
        try {
            return (boolean)this.armorStand.getClass().getMethod("isMarker", new Class[0]).invoke(this.armorStand, new Object[0]);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
            return false;
        }
    }
    
    public void setHitBox(final boolean b) {
        try {
            this.armorStand.getClass().getMethod("setMarker", Boolean.TYPE).invoke(this.armorStand, b);
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
}
