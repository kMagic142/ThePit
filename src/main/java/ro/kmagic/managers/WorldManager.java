package ro.kmagic.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import ro.kmagic.libapi.API;
import ro.kmagic.Main;

import java.util.ArrayList;
import java.util.UUID;

public class WorldManager {
    private final ArrayList<String> world;
    private final String worldNameString;
    
    public WorldManager() {
        this.world = new ArrayList<>();
        this.worldNameString = Main.getSettings().getString("Plugin.WorldName");
        this.loadWorld();
    }
    
    public void loadWorld() {
        World worldSettings;

        worldSettings = Bukkit.createWorld(new WorldCreator(this.worldNameString));
        this.world.add(this.worldNameString);

        if (worldSettings != null) {
            this.setWorldSettings(worldSettings);
        }
    }
    
    public void unloadWorld() {
        if (Main.isSetupMode()) {
            Bukkit.unloadWorld(Main.getSpawn().getLocation("Spawn").getWorld().getName(), true);
            return;
        }

        for (UUID uuid : Main.getArena().getPlayers()) {
            final Player player = Bukkit.getPlayer(uuid);
            if (Main.getSpawn().getLocation("Lobby") != null) {
                Main.getArena().leaveArena(player);
                player.teleport(Main.getSpawn().getLocation("Lobby"));
            }
        }

    }
    
    public boolean isOnMap(final Player player) {
        return player.getWorld().getName().equalsIgnoreCase(this.worldNameString);
    }
    
    public boolean isOnMap(final World world) {
        return world.getName().equalsIgnoreCase(this.worldNameString);
    }
    
    public void teleport(final Player player) {
        if (this.isWorldLoaded()) {
            player.teleport(Bukkit.getWorld(this.worldNameString).getSpawnLocation());
        }
    }
    
    private boolean isWorldLoaded() {
        return this.world.contains(this.worldNameString);
    }
    
    public void setWorldSettings(final World world) {
        world.setAutoSave(Main.isSetupMode());
        world.setStorm(false);
        world.setWeatherDuration(0);
        world.setThundering(false);
        world.setThunderDuration(0);
        world.setTime(1000L);
        if (!API.getVersionSupport().contains(12, 13, 14, 15, 16)) {
            world.setGameRuleValue("doDaylightCycle", "false");
        }
        else {
            world.setGameRuleValue("announceAdvancements", "false");
        }
    }
}
