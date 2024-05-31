package ro.kmagic.libapi.versionsupport.entity.ageable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Ageable;

public class EntityAgeableGlobal
{
    private final Ageable ageable;
    
    public EntityAgeableGlobal(final Entity entity) {
        this.ageable = (Ageable)entity;
    }
    
    public int getAge() {
        return this.ageable.getAge();
    }
    
    public void setAge(final int age) {
        this.ageable.setAge(age);
    }
    
    public void setAgeLock(final boolean ageLock) {
        this.ageable.setAgeLock(ageLock);
    }
    
    public boolean getAgeLock() {
        return this.ageable.getAgeLock();
    }
    
    public void setBaby() {
        this.ageable.setBaby();
    }
    
    public void setAdult() {
        this.ageable.setAdult();
    }
    
    public boolean isAdult() {
        return this.ageable.isAdult();
    }
    
    public boolean canBreed() {
        return this.ageable.canBreed();
    }
    
    public void setBreed(final boolean breed) {
        this.ageable.setBreed(breed);
    }
}
