package ro.kmagic.libapi.versionsupport.entity.entities.villager;

import ro.kmagic.libapi.versionsupport.entity.Entities;
import org.bukkit.Location;
import ro.kmagic.libapi.versionsupport.entity.ageable.EntityAgeableNMS;
import org.bukkit.entity.Player;
import java.lang.reflect.Method;
import ro.kmagic.libapi.versionsupport.entity.EntityNMS;

public class VillagerNMS extends EntityNMS
{
    private Method craftVillagerGetHandle;
    private Object villager;
    private Player p;
    private EntityAgeableNMS ageable;
    
    public VillagerNMS(final Player p2, final Location location) {
        super(p2, location, Entities.VILLAGER);
        try {
            this.p = p2;
            this.craftVillagerGetHandle = this.getOBC("entity.CraftVillager").getMethod("getHandle");
            this.villager = this.getEntity();
            this.ageable = new EntityAgeableNMS(this.villager, p2);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void setProfession(final VillagerProfessionType villagerProfessionType) {
        try {
            final Object invoke = this.craftVillagerGetHandle.invoke(this.villager);
            invoke.getClass().getMethod("setProfession", Integer.TYPE).invoke(invoke, villagerProfessionType.getProfessionId());
            this.sendPacket(this.p, this.packetPlayOutEntityMetadata.newInstance(this.id, this.dataWatcher, false));
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public VillagerProfessionType getProfession() {
        try {
            final Object invoke = this.craftVillagerGetHandle.invoke(this.villager);
            final int intValue = (int)invoke.getClass().getMethod("getProfession", new Class[0]).invoke(invoke, new Object[0]);
            for (final VillagerProfessionType villagerProfessionType : VillagerProfessionType.values()) {
                if (villagerProfessionType.getProfessionId() == intValue) {
                    return villagerProfessionType;
                }
            }
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
        return null;
    }
    
    public EntityAgeableNMS getAgeable() {
        return this.ageable;
    }
}
