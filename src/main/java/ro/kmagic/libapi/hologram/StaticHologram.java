package ro.kmagic.libapi.hologram;

import java.util.Iterator;

import ro.kmagic.libapi.API;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import java.util.ArrayList;
import org.bukkit.Location;
import java.util.HashMap;
import org.bukkit.entity.ArmorStand;
import java.util.List;

public class StaticHologram
{
    private final List<ArmorStand> lines;
    private static HashMap<String, StaticHologram> holograms;
    
    public StaticHologram(final String key, final Location location, final List<String> list) {
        this.lines = new ArrayList<ArmorStand>();
        final Location add = location.clone().add(0.0, 0.23 * list.size() - 1.97, 0.0);
        for (int i = 0; i < list.size(); ++i) {
            this.addLine(add, list.get(i));
            add.add(0.0, -0.23, 0.0);
        }
        StaticHologram.holograms.put(key, this);
    }
    
    public static boolean isCreated(final String key) {
        return StaticHologram.holograms.containsKey(key);
    }
    
    public static StaticHologram getHologram(final String key) {
        return StaticHologram.holograms.get(key);
    }
    
    private void addLine(final Location location, final String s) {
        final ArmorStand invincible = (ArmorStand)location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        invincible.setCustomName(ChatColor.translateAlternateColorCodes('&', s));
        invincible.setCustomNameVisible(true);
        invincible.setGravity(false);
        invincible.setVisible(false);
        API.getVersionSupport().setInvincible(invincible);
        this.lines.add(invincible);
    }
    
    public void removeHologram() {
        final Iterator<ArmorStand> iterator = this.lines.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
        this.lines.clear();
    }
    
    static {
        StaticHologram.holograms = new HashMap<String, StaticHologram>();
    }
}
