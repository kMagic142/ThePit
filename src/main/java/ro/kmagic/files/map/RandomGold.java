package ro.kmagic.files.map;

import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.Location;
import ro.kmagic.Main;
import java.io.File;
import ro.kmagic.libapi.utils.FileManager;

public class RandomGold extends FileManager {
    public static final String secondsBetweenGenerating = "SecondsBetweenGenerating";
    public static final String requiredOnlinePlayers = "RequiredOnlinePlayers";
    public static final String locations = "Locations";
    
    public RandomGold() {
        super("RandomGold", "Map");
        if (new File("plugins/ThePit/Map", "Gold.yml").exists()) {
            final FileManager fileManager = new FileManager("Gold", "Map");
            this.set("SecondsBetweenGenerating", fileManager.getInt("SecondsBetweenGenerating"));
            this.set("RequiredOnlinePlayers", fileManager.getInt("RequiredOnlinePlayers"));
            if (fileManager.contains("Locations")) {
                this.set("Locations", fileManager.getStringList("Locations"));
            }
            new File("plugins/ThePit/Map", "Gold.yml").delete();
        }
        this.addDefault("SecondsBetweenGenerating", 20);
        this.addDefault("RequiredOnlinePlayers", 2);
        this.copyDefaults();
        this.save();
        if (this.isSet() && Main.isSetupMode()) {
            for (final Location location : this.getLocations()) {
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 1.0, 0.5), (Class)ArmorStand.class);
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6Random Gold"));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 0.7, 0.5), (Class)ArmorStand.class);
                armorStand2.setMarker(true);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6set"));
            }
        }
    }
    
    public boolean isSet() {
        return this.contains("Locations");
    }
    
    public List<Location> getLocations() {
        final ArrayList<Location> list = new ArrayList<Location>();
        for (String s : this.getStringList("Locations")) {
            final String[] split = s.split(" ");
            list.add(new Location(Bukkit.getWorld(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
        }
        return list;
    }
    
    public void add(final Location location) {
        final List<String> stringList = this.getStringList("Locations");
        stringList.add(location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
        this.set("Locations", stringList);
    }
    
    public boolean isSet(final Location location) {
        return this.getStringList("Locations").contains(location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
    }
}
