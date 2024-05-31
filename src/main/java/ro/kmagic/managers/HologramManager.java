package ro.kmagic.managers;

import ro.kmagic.libapi.hologram.StaticHologram;
import org.bukkit.Location;
import ro.kmagic.Main;

public class HologramManager {
    public HologramManager() {
        if (Main.getEnderchest().isSet()) {
            for (final Location location : Main.getEnderchest().getEnderchests()) {
                new StaticHologram(this.getHoloIdFromLocation(location), location.clone().add(0.5, 0.65, 0.5), Main.getEnderchest().getStringList("Hologram"));
            }
        }
        if (Main.getHologram().isPitHoleHologramSet()) {
            new StaticHologram(this.getHoloIdFromLocation(Main.getHologram().getPitHoleLocation()), Main.getHologram().getPitHoleLocation().clone().add(0.5, 0.65, 0.5), Main.getHologram().getPitHoleHologram());
        }
    }
    
    public String getHoloIdFromLocation(final Location location) {
        return "X:" + location.getX() + " Y:" + location.getY() + " Z:" + location.getZ();
    }
}
