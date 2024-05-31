package ro.kmagic.files.events;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import java.util.Iterator;
import ro.kmagic.libapi.utils.FileManager;

public class EventsRegion extends FileManager {
    public static final String locations = "Locations";
    public static final String ragePitBlocks = "RagePit.Blocks";
    
    public EventsRegion() {
        super("Region", "Events");
    }
    
    public boolean isSet() {
        return this.contains("Locations");
    }
    
    public boolean isSet(final String str) {
        return this.contains("Locations." + str + ".Name") && this.contains("Locations." + str + ".POS1") && this.contains("Locations." + str + ".POS2");
    }
    
    public boolean isLocationsSet() {
        if (!this.isSet()) {
            return false;
        }
        for (final String s : this.getConfigurationSection("Locations").getKeys(false)) {
            if (this.isSet()) {
                return true;
            }
        }
        return false;
    }
    
    public void setPos(final String s, final Location location, final int i) {
        this.set("Locations." + s + ".Name", s);
        this.set("Locations." + s + ".POS" + i, location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
    }
    
    public List<String> getNodes() {
        if (this.getConfigurationSection("Locations") == null) {
            return null;
        }
        return new ArrayList<String>(this.getConfigurationSection("Locations").getKeys(false));
    }
    
    public Location getPos(final String s, final int n) {
        if (!this.contains("Locations." + s + ".POS" + n)) {
            return null;
        }
        final String[] split = this.getString("Locations." + s + ".POS" + n).split(" ");
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
    }
    
    public List<String> getLocations(final String str, final String str2) {
        return this.getStringList("Locations." + str + "." + str2);
    }
    
    public void setLocation(final String str, final Location location, final String str2) {
        final List<String> locations = this.getLocations(str, str2);
        locations.add(location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
        this.set("Locations." + str + "." + str2, locations);
    }
    
    public boolean isSet(final EventsRegionType eventsRegionType) {
        if (!this.isLocationsSet()) {
            return false;
        }
        switch (eventsRegionType) {
            case KOTH_REGION: {
                if (!this.isLocationsSet()) {
                    return false;
                }
                final Iterator<String> iterator = this.getNodes().iterator();
                while (iterator.hasNext()) {
                    if (this.contains("Locations." + iterator.next() + ".Koth")) {
                        return true;
                    }
                }
                break;
            }
            case CARE_PACKAGE_REGION: {
                if (!this.isLocationsSet()) {
                    return false;
                }
                final Iterator<String> iterator2 = this.getNodes().iterator();
                while (iterator2.hasNext()) {
                    if (this.contains("Locations." + iterator2.next() + ".CarePackage")) {
                        return true;
                    }
                }
                break;
            }
            case RAGE_PIT_REGION: {
                return this.contains("RagePit.Blocks");
            }
        }
        return false;
    }
    
    public boolean isSet(final EventsRegionType eventsRegionType, final String s) {
        switch (eventsRegionType) {
            case KOTH_REGION: {
                return this.contains("Locations." + s + ".Koth");
            }
            case CARE_PACKAGE_REGION: {
                return this.contains("Locations." + s + ".CarePackage");
            }
            default: {
                return false;
            }
        }
    }
    
    public void setRagePit(final List<String> list) {
        this.set("RagePit.Blocks", list);
    }
    
    public boolean isRagePitSet() {
        return this.contains("RagePit.Blocks");
    }
    
    public List<String> getRagePitBlocks() {
        return this.getStringList("RagePit.Blocks");
    }
    
    public String getName(final String str) {
        return this.getString("Locations." + str + ".Name");
    }
    
    public enum EventsRegionType
    {
        KOTH_REGION, 
        CARE_PACKAGE_REGION, 
        RAGE_PIT_REGION
    }
}
