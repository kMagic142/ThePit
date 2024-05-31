package ro.kmagic.libapi.hologram;

import org.bukkit.scheduler.BukkitRunnable;
import java.util.Iterator;

import ro.kmagic.libapi.API;
import org.bukkit.ChatColor;
import java.util.ArrayList;
import org.bukkit.Location;
import java.util.HashMap;
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.libapi.placeholder.RefreshablePlaceholder;
import org.bukkit.entity.ArmorStand;

import java.util.List;

public class RefreshableHologram
{
    private final List<ArmorStand> lines;
    private final List<ArmorStand> placeholderLines;
    private final List<RefreshablePlaceholder> placeholders;
    private final List<BukkitTask> tasks;
    private final HashMap<ArmorStand, String> placeholder;
    private final int refreshTick;
    private static HashMap<String, RefreshableHologram> holograms;
    
    public RefreshableHologram(final String key, final Location location, final List<String> list, final List<RefreshablePlaceholder> placeholders, final int refreshTick) {
        this.lines = new ArrayList<ArmorStand>();
        this.placeholders = placeholders;
        this.placeholderLines = new ArrayList<ArmorStand>();
        this.tasks = new ArrayList<BukkitTask>();
        this.placeholder = new HashMap<ArmorStand, String>();
        this.refreshTick = refreshTick;
        final Location add = location.clone().add(0.0, 0.23 * list.size() - 1.97, 0.0);
        for (int i = 0; i < list.size(); ++i) {
            this.addLine(add, list.get(i));
            add.add(0.0, -0.23, 0.0);
        }
        this.refresh();
        RefreshableHologram.holograms.put(key, this);
    }
    
    public static boolean isCreated(final String key) {
        return RefreshableHologram.holograms.containsKey(key);
    }
    
    public static RefreshableHologram getHologram(final String key) {
        return RefreshableHologram.holograms.get(key);
    }
    
    private void addLine(final Location location, final String s) {
        final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location, (Class)ArmorStand.class);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', s));
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        API.getVersionSupport().setInvincible(armorStand);
        this.lines.add(armorStand);
        for (final RefreshablePlaceholder refreshablePlaceholder : this.placeholders) {
            armorStand.setCustomNameVisible(true);
            if (s.contains(refreshablePlaceholder.getPlaceholder())) {
                this.placeholderLines.add(armorStand);
                this.placeholder.put(armorStand, armorStand.getName());
            }
        }
    }
    
    public void remove() {
        for (ArmorStand line : this.lines) {
            line.remove();
        }
        final Iterator<ArmorStand> iterator2 = this.placeholderLines.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().remove();
        }
        final Iterator<BukkitTask> iterator3 = this.tasks.iterator();
        while (iterator3.hasNext()) {
            iterator3.next().cancel();
        }
        this.lines.clear();
        this.placeholderLines.clear();
        this.tasks.clear();
    }
    
    private void refresh() {
        this.tasks.add(new BukkitRunnable() {
            public void run() {
                try {
                    for (final ArmorStand armorStand : RefreshableHologram.this.placeholderLines) {
                        if (!armorStand.isCustomNameVisible()) {
                            armorStand.setCustomNameVisible(true);
                        }
                        if (RefreshableHologram.this.placeholder.containsKey(armorStand)) {
                            String replace = RefreshableHologram.this.placeholder.get(armorStand);
                            for (final RefreshablePlaceholder refreshablePlaceholder : RefreshableHologram.this.placeholders) {
                                if (replace.contains(refreshablePlaceholder.getPlaceholder())) {
                                    replace = replace.replace(refreshablePlaceholder.getPlaceholder(), refreshablePlaceholder.getValue());
                                }
                            }
                            armorStand.setCustomName(replace);
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }.runTaskTimer(API.getPlugin(), 0L, this.refreshTick));
    }
    
    static {
        RefreshableHologram.holograms = new HashMap<String, RefreshableHologram>();
    }
}
