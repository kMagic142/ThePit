package ro.kmagic.libapi.hologram;

import java.util.ArrayList;
import org.bukkit.Location;
import java.util.HashMap;
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import org.bukkit.entity.ArmorStand;

import java.util.List;

public class Hologram
{
    private final List<ArmorStand> lines;
    private final List<ArmorStand> placeholderLines;
    private final List<RefreshablePlaceholder> placeholders;
    private final List<BukkitTask> tasks;
    private final HashMap<ArmorStand, String> placeholder;
    private final int refreshTick;
    private static HashMap<String, Hologram> holograms;
    
    public Hologram(final String key, final Location location, final List<String> list, final List<RefreshablePlaceholder> placeholders, final int refreshTick, final SendType sendType) {
        this.lines = new ArrayList<ArmorStand>();
        this.placeholders = placeholders;
        this.placeholderLines = new ArrayList<ArmorStand>();
        this.tasks = new ArrayList<BukkitTask>();
        this.placeholder = new HashMap<ArmorStand, String>();
        this.refreshTick = refreshTick;
        final Location add = location.clone().add(0.0, 0.23 * list.size() - 1.97, 0.0);
        for (int i = 0; i < list.size(); ++i) {
            add.add(0.0, -0.23, 0.0);
        }
        Hologram.holograms.put(key, this);
    }
    
    static {
        Hologram.holograms = new HashMap<String, Hologram>();
    }
    
    public enum HologramType
    {
        REFRESHABLE, 
        STATIC
    }
    
    public enum SendType
    {
        GLOBAL, 
        PERSONAL
    }
}
