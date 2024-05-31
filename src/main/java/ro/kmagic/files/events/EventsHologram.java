package ro.kmagic.files.events;

import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.Location;
import ro.kmagic.Main;
import java.util.Arrays;
import ro.kmagic.libapi.utils.FileManager;

public class EventsHologram extends FileManager {
    private static final String minorEvent = "MinorEvent.";
    private static final String majorEvent = "MajorEvent.";
    private static final String locations = "Locations";
    public static final String minorEventPreStart = "MinorEvent.PreStart";
    public static final String minorEventStart = "MinorEvent.Start";
    public static final String majorEventPreStart = "MajorEvent.PreStart";
    public static final String majorEventStart = "MajorEvent.Start";
    public static final String carePackageHologram = "CarePackage.Hologram";
    
    public EventsHologram() {
        super("Hologram", "Events");
        this.addDefault("MinorEvent.PreStart", Arrays.asList("&d&lMINOR EVENT!", "%eventDescription%", "&ein &6%eventLocation%", "Starting in: &a%eventTime%"));
        this.addDefault("MinorEvent.Start", Arrays.asList("&d&lMINOR EVENT!", "%eventDescription%", "&ein &6%eventLocation%", "Ending in: &a%eventTime%"));
        this.addDefault("MajorEvent.PreStart", Arrays.asList("&6&lMAJOR EVENT!", "%eventDescription%", "Starting in: &a%eventTime%"));
        this.addDefault("MajorEvent.Start", Arrays.asList("&6&lMAJOR EVENT!", "%eventDescription%", "Ending in: &a%eventTime%"));
        this.addDefault("CarePackage.Hologram", Arrays.asList("&a&l%clicksLeft%", "&2&lLEFT-CLICK"));
        this.copyDefaults();
        this.save();
        if (Main.isSetupMode() && this.isSet()) {
            for (final Location location : this.getLocations()) {
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.5, 0.0), (Class)ArmorStand.class);
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&bEvent Hologram"));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.3, 0.0), (Class)ArmorStand.class);
                armorStand2.setMarker(true);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&bset"));
            }
        }
    }
    
    public boolean isSet() {
        return this.contains("Locations");
    }
    
    public void addHologram(final Location location) {
        final List<String> stringList = this.getStringList("Locations");
        stringList.add(location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
        this.set("Locations", stringList);
    }
    
    public List<Location> getLocations() {
        if (!this.isSet()) {
            return null;
        }
        final ArrayList<Location> list = new ArrayList<Location>();
        for (String s : this.getStringList("Locations")) {
            final String[] split = s.split(" ");
            list.add(new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]) + 2.0, Double.parseDouble(split[3])));
        }
        return list;
    }
}
