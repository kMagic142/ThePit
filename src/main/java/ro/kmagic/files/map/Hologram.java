package ro.kmagic.files.map;

import org.bukkit.Bukkit;
import java.util.List;
import org.bukkit.Location;
import java.util.Arrays;
import ro.kmagic.libapi.utils.FileManager;

public class Hologram extends FileManager {
    private static final String unlockedFeatures = "Unlocked-Features.";
    public static final String pitHoleHologram = "PitHole.Hologram";
    public static final String pitHoleLocation = "PitHole.Location";
    public static final String unlockedFeaturesHologram = "Unlocked-Features.Hologram";
    public static final String unlockedFeaturesLocation = "Unlocked-Features.Location";
    public static final String unlockedFeaturesRespawn = "Unlocked-Features.Respawn";
    public static final String unlockedFeaturesPitChat = "Unlocked-Features.Pit-Chat";
    public static final String unlockedFeaturesUpgrades = "Unlocked-Features.Upgrades";
    public static final String unlockedFeaturesEnderChest = "Unlocked-Features.Ender-Chest";
    public static final String unlockedFeaturesContracts = "Unlocked-Features.Contracts";
    public static final String unlockedFeaturesStats = "Unlocked-Features.Stats";
    public static final String unlockedFeaturesTrade = "Unlocked-Features.Trade";
    public static final String unlockedFeaturesView = "Unlocked-Features.View";
    public static final String unlockedFeaturesPrestige = "Unlocked-Features.Prestige";
    public static final String unlockedFeaturesLocked = "Unlocked-Features.Locked";
    
    public Hologram() {
        super("Hologram", "Map");
        this.addDefault("PitHole.Hologram", Arrays.asList("&eThe Spigot Pit", "&a&lJUMP! &c&lFIGHT!"));
        this.addDefault("Unlocked-Features.Hologram", Arrays.asList("&e&lUNLOCKED FEATURES", " ", "%lvl1feature%", "%lvl5feature%", "%lvl10feature%", "%lvl15feature%", "%lvl30feature%", "%lvl50feature%", "%lvl60feature%", "%lvl70feature%", "%lvl120feature%", "&7Gain levels to unlock more"));
        this.addDefault("Unlocked-Features.Respawn", "%tag% &b/respawn");
        this.addDefault("Unlocked-Features.Pit-Chat", "%tag% &b/pitchat");
        this.addDefault("Unlocked-Features.Upgrades", "%tag% &bUpgrades");
        this.addDefault("Unlocked-Features.Ender-Chest", "%tag% &bEnder chest");
        this.addDefault("Unlocked-Features.Contracts", "%tag% &bContracts");
        this.addDefault("Unlocked-Features.Stats", "%tag% &bStats");
        this.addDefault("Unlocked-Features.Trade", "%tag% &b/trade");
        this.addDefault("Unlocked-Features.View", "%tag% &b/view");
        this.addDefault("Unlocked-Features.Prestige", "%tag% &bPrestige");
        this.addDefault("Unlocked-Features.Locked", "&7???");
        this.copyDefaults();
        this.save();
    }
    
    public boolean isPitHoleHologramSet() {
        return this.contains("PitHole.Location");
    }
    
    public boolean isUnlockedFeaturesSet() {
        return this.contains("Unlocked-Features.Location");
    }
    
    public void setPitHoleHologram(final Location location) {
        this.set("PitHole.Location.World", location.getWorld().getName());
        this.set("PitHole.Location.X", location.getBlockX());
        this.set("PitHole.Location.Y", location.getBlockY());
        this.set("PitHole.Location.Z", location.getBlockZ());
    }
    
    public void setUnlockedFeaturesHologram(final Location location) {
        this.set("Unlocked-Features.Location.World", location.getWorld().getName());
        this.set("Unlocked-Features.Location.X", location.getX());
        this.set("Unlocked-Features.Location.Y", location.getY());
        this.set("Unlocked-Features.Location.Z", location.getZ());
    }
    
    public List<String> getPitHoleHologram() {
        return this.getStringList("PitHole.Hologram");
    }
    
    public List<String> getUnlockedFeaturesHologram() {
        return this.getStringList("Unlocked-Features.Hologram");
    }
    
    public Location getPitHoleLocation() {
        return new Location(Bukkit.getWorld(this.getString("PitHole.Location.World")), this.getInt("PitHole.Location.X"), this.getInt("PitHole.Location.Y"), this.getInt("PitHole.Location.Z"));
    }
    
    public Location getUnlockedFeaturesLocation() {
        return new Location(Bukkit.getWorld(this.getString("Unlocked-Features.Location.World")), this.getDouble("Unlocked-Features.Location.X"), this.getDouble("Unlocked-Features.Location.Y"), this.getDouble("Unlocked-Features.Location.Z"));
    }
}
