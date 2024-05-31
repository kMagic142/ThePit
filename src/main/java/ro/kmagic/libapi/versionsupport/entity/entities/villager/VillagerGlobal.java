package ro.kmagic.libapi.versionsupport.entity.entities.villager;

import ro.kmagic.libapi.API;
import ro.kmagic.libapi.versionsupport.entity.Entities;
import org.bukkit.Location;
import ro.kmagic.libapi.versionsupport.entity.ageable.EntityAgeableGlobal;
import org.bukkit.entity.Villager;
import java.lang.reflect.Method;
import ro.kmagic.libapi.versionsupport.entity.EntityGlobal;

public class VillagerGlobal extends EntityGlobal
{
    private Method craftVillagerGetHandle;
    private Villager villager;
    private EntityAgeableGlobal ageable;
    
    public VillagerGlobal(final Location location) {
        super(location.clone(), Entities.VILLAGER);
        try {
            this.craftVillagerGetHandle = this.getOBC("entity.CraftVillager").getMethod("getHandle");
            this.villager = (Villager)this.getEntity();
            this.ageable = new EntityAgeableGlobal(this.villager);
        }
        catch (Exception ex) {
            this.printReflectionError(ex);
        }
    }
    
    public void setProfession(final VillagerProfessionType villagerProfessionType) {
        Villager.Profession profession = null;
        if (API.getVersionSupport().contains(14, 15, 16)) {
            switch (villagerProfessionType) {
                case BLACKSMITH: {
                    profession = Villager.Profession.valueOf("TOOLSMITH");
                    break;
                }
                case PRIEST: {
                    profession = Villager.Profession.valueOf("CLERIC");
                    break;
                }
                default: {
                    profession = Villager.Profession.valueOf(villagerProfessionType.toString());
                    break;
                }
            }
        }
        else {
            if (villagerProfessionType.equals(VillagerProfessionType.NITWIT)) {
                try {
                    final Object invoke = this.craftVillagerGetHandle.invoke(this.villager);
                    invoke.getClass().getMethod("setProfession", Integer.TYPE).invoke(invoke, VillagerProfessionType.NITWIT.getProfessionId());
                }
                catch (Exception ex) {
                    this.printReflectionError(ex);
                }
                return;
            }
            profession = Villager.Profession.valueOf(villagerProfessionType.toString());
        }
        this.villager.setProfession(profession);
    }
    
    public VillagerProfessionType getProfession() {
        if (!API.getVersionSupport().contains(14, 15, 16)) {
            try {
                final Object invoke = this.craftVillagerGetHandle.invoke(this.villager);
                if ((int)invoke.getClass().getMethod("getProfession", new Class[0]).invoke(invoke, new Object[0]) == VillagerProfessionType.NITWIT.getProfessionId()) {
                    return VillagerProfessionType.NITWIT;
                }
            }
            catch (Exception ex) {
                this.printReflectionError(ex);
            }
            return VillagerProfessionType.valueOf(this.villager.getProfession().toString());
        }
        final String string = this.villager.getProfession().toString();
        switch (string) {
            case "TOOLSMOTH": {
                return VillagerProfessionType.BLACKSMITH;
            }
            case "PRIEST": {
                return VillagerProfessionType.PRIEST;
            }
            default: {
                return VillagerProfessionType.valueOf(this.villager.getProfession().toString());
            }
        }
    }
    
    public EntityAgeableGlobal getAgeable() {
        return this.ageable;
    }
}
