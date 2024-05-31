package ro.kmagic.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.Main;
import ro.kmagic.api.events.misc.GoldSpawnEvent;

import java.util.List;

public class RandomGoldManager {
    private final int requiredPlayersNumber;
    private final Location spawnLocation;
    private final boolean isRandomGoldSetBoolean;
    private final List<Location> randomGoldLocations;
    private final ItemStack goldIngot;
    
    public RandomGoldManager() {
        this.requiredPlayersNumber = Main.getRandomGold().getInt("RequiredOnlinePlayers");
        this.spawnLocation = Main.getSpawn().getLocation("Spawn");
        this.isRandomGoldSetBoolean = Main.getRandomGold().isSet();
        this.randomGoldLocations = Main.getRandomGold().getLocations();
        this.goldIngot = Materials.GOLD_INGOT.getItem().build();
        new BukkitRunnable() {
            public void run() {
                if (Bukkit.getOnlinePlayers().size() < RandomGoldManager.this.requiredPlayersNumber || RandomGoldManager.this.spawnLocation == null || !RandomGoldManager.this.isRandomGoldSetBoolean) {
                    return;
                }
                final Location location = RandomGoldManager.this.randomGoldLocations.get(Main.getArena().getRandomInt(RandomGoldManager.this.randomGoldLocations.size() - 1));
                Bukkit.getPluginManager().callEvent(new GoldSpawnEvent(location.getWorld().dropItem(location.add(0.5, 3.0, 0.5), RandomGoldManager.this.goldIngot)));
            }
        }.runTaskTimer(API.getPlugin(), 20L, Main.getRandomGold().getInt("SecondsBetweenGenerating") * 20L);
    }
}
