package ro.kmagic.libapi.utils;

import org.bukkit.Location;

public class RelativeLocation
{
    private final float x;
    private final float y;
    private final float z;
    
    public RelativeLocation(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public RelativeLocation(final Location location, final Location location2) {
        this.x = (float)(location2.getBlockX() - location.getBlockX());
        this.y = (float)(location2.getBlockY() - location.getBlockY());
        this.z = (float)(location2.getBlockZ() - location.getBlockZ());
    }
    
    public float getRelativeX() {
        return this.x;
    }
    
    public float getRelativeY() {
        return this.y;
    }
    
    public float getRelativeZ() {
        return this.z;
    }
    
    public Location getLocationRelativeTo(final Location location) {
        return new Location(location.getWorld(), location.getBlockX() + this.x, location.getBlockY() + this.y, location.getBlockZ() + this.z);
    }
}
