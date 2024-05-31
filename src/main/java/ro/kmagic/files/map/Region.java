package ro.kmagic.files.map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import ro.kmagic.libapi.utils.FileManager;

public class Region extends FileManager {
    public Region() {
        super("Region", "Map");
    }
    
    public boolean isSet(final RegionType regionType) {
        return this.contains(regionType.getPathName() + ".POS1") && this.contains(regionType.getPathName() + ".POS2");
    }
    
    public void setPos(final RegionType regionType, final Location location, final int i) {
        this.set(regionType.getPathName() + ".POS" + i, location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
    }
    
    public Location getPos(final RegionType regionType, final int n) {
        if (!this.contains(regionType.getPathName() + ".POS" + n)) {
            return null;
        }
        final String[] split = this.getString(regionType.getPathName() + ".POS" + n).split(" ");
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
    }
    
    public enum RegionType
    {
        SPAWN_REGION("Spawn"), 
        PIT_HOLE_REGION("PitHole");
        
        private final String pathName;
        
        RegionType(final String pathName) {
            this.pathName = pathName;
        }
        
        public String getPathName() {
            return this.pathName;
        }
    }
}
