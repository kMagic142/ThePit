package ro.kmagic.libapi.versionsupport.entity.entities.armorstand;

import org.bukkit.util.EulerAngle;
import ro.kmagic.libapi.versionsupport.entity.Entities;
import org.bukkit.Location;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.entity.ArmorStand;
import ro.kmagic.libapi.versionsupport.entity.EntityGlobal;

public class ArmorStandGlobal extends EntityGlobal
{
    private final ArmorStand armorStand;
    private static HashMap<UUID, ArmorStandGlobal> entityCache;
    
    public ArmorStandGlobal(final Location location) {
        super(location.clone(), Entities.ARMOR_STAND);
        this.armorStand = (ArmorStand)this.getEntity();
        ArmorStandGlobal.entityCache.put(this.getUniqueId(), this);
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
    
    public static boolean entityExists(final UUID key) {
        return ArmorStandGlobal.entityCache.containsKey(key);
    }
    
    public static ArmorStandGlobal getEntityFromUUID(final UUID key) {
        return ArmorStandGlobal.entityCache.getOrDefault(key, null);
    }
    
    public void removeFromCache() {
        ArmorStandGlobal.entityCache.remove(this.armorStand.getUniqueId());
    }
    
    public boolean hasBasePlate() {
        return this.armorStand.hasBasePlate();
    }
    
    public void setBasePlate(final boolean basePlate) {
        this.armorStand.setBasePlate(basePlate);
    }
    
    public boolean isVisible() {
        return this.armorStand.isVisible();
    }
    
    public void setVisible(final boolean visible) {
        this.armorStand.setVisible(visible);
    }
    
    public boolean hasArms() {
        return this.armorStand.hasArms();
    }
    
    public void setArms(final boolean arms) {
        this.armorStand.setArms(arms);
    }
    
    public boolean isSmall() {
        return this.armorStand.isSmall();
    }
    
    public void setSmall(final boolean small) {
        this.armorStand.setSmall(small);
    }
    
    public boolean hasHitBox() {
        return this.armorStand.isMarker();
    }
    
    public void setHitBox(final boolean marker) {
        this.armorStand.setMarker(marker);
    }
    
    static {
        ArmorStandGlobal.entityCache = new HashMap<UUID, ArmorStandGlobal>();
    }
}
