package ro.kmagic.managers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import ro.kmagic.Main;
import ro.kmagic.features.npcs.NPC;

public class EntityManager implements Listener {

    public EntityManager() {
        final Location location = Main.getSpawn().getLocation("Spawn");
        if (location == null) {
            return;
        }
        final World world = location.getWorld();
        if (world == null) {
            return;
        }
        world.setWaterAnimalSpawnLimit(0);
        world.setAnimalSpawnLimit(0);
        world.setMonsterSpawnLimit(0);
        Main.getWorldManager().setWorldSettings(world);
        for (final Entity entity : world.getEntities()) {
            if (!(entity instanceof Player) && (!(entity instanceof ArmorStand) || entity.isCustomNameVisible())) {
                entity.remove();
            }
        }
        final NPC.NPCType[] values = NPC.NPCType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            Main.getNpc().spawn(values[i]);
        }
    }
}
