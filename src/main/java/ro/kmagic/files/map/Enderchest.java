package ro.kmagic.files.map;

import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.Location;
import ro.kmagic.Main;
import java.util.Arrays;
import ro.kmagic.libapi.utils.FileManager;

public class Enderchest extends FileManager {
    public static final String cooldownSeconds = "Cooldown.Seconds";
    public static final String cooldownMessage = "Cooldown.Message";
    public static final String invalidItem = "Invalid-Item";
    public static final String cacheEnderchests = "Cache-Enderchests";
    public static final String hologram = "Hologram";
    public static final String enderchests = "Enderchests";
    
    public Enderchest() {
        super("Enderchest", "Map");
        if (this.contains("Location.World")) {
            this.addEnderchest(this.getString("Location.World"), this.getInt("Location.X"), this.getInt("Location.Y"), this.getInt("Location.Z"));
            this.set("Location", null);
        }
        this.addDefault("Cooldown.Seconds", 6);
        this.addDefault("Cooldown.Message", "&cEnderchest on cooldown!");
        this.addDefault("Invalid-Item", "&cYou can't store this item in the ender chest!");
        this.addDefault("Cache-Enderchests", false);
        this.addDefault("Hologram", Arrays.asList("&5&lENDER CHEST", "&7Store items forever"));
        this.copyDefaults();
        this.save();
        if (this.isSet() && Main.isSetupMode()) {
            for (final Location location : this.getEnderchests()) {
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, -0.7, 0.5), (Class)ArmorStand.class);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&5Ender chest"));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, -0.9, 0.5), (Class)ArmorStand.class);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&5set"));
            }
        }
    }
    
    public List<Location> getEnderchests() {
        final ArrayList<Location> list = new ArrayList<Location>();
        for (String s : this.getStringList("Enderchests")) {
            final String[] split = s.split(" ");
            list.add(new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3])));
        }
        return list;
    }
    
    public boolean isSet() {
        return this.contains("Enderchests");
    }
    
    public void addEnderchest(final Location location) {
        final List<String> stringList = this.getStringList("Enderchests");
        stringList.add(location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
        this.set("Enderchests", stringList);
    }
    
    public void addEnderchest(final String str, final int i, final int j, final int k) {
        final List<String> stringList = this.getStringList("Enderchests");
        stringList.add(str + " " + i + " " + j + " " + k);
        this.set("Enderchests", stringList);
    }
    
    public boolean isEnderchestSet(final Location location) {
        return this.getStringList("Enderchests").contains(location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
    }
}
