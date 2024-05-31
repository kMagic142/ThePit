package ro.kmagic.libapi.versionsupport.sound;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.Iterator;

import ro.kmagic.libapi.API;
import ro.kmagic.libapi.versionsupport.VersionType;
import java.util.HashMap;

public class Sound
{
    public static HashMap<String, Sound> soundCache;
    private final HashMap<VersionType, String> soundVersionCache;
    private final String name;
    private float volume;
    private float pitch;
    
    public Sound(final String name) {
        this.volume = 10.0f;
        this.pitch = 0.0f;
        this.name = name;
        this.soundVersionCache = new HashMap<VersionType, String>();
    }
    
    public Sound(final String name, final float volume, final float pitch) {
        this.volume = 10.0f;
        this.pitch = 0.0f;
        this.name = name;
        this.soundVersionCache = new HashMap<VersionType, String>();
        this.volume = volume;
        this.pitch = pitch;
    }
    
    public Sound(final String name, final String value) {
        this.volume = 10.0f;
        this.pitch = 0.0f;
        this.name = name;
        (this.soundVersionCache = new HashMap<VersionType, String>()).put(VersionType.valueOf(API.getVersion()), value);
    }
    
    public Sound(final String name, final String value, final float volume, final float pitch) {
        this.volume = 10.0f;
        this.pitch = 0.0f;
        this.name = name;
        (this.soundVersionCache = new HashMap<VersionType, String>()).put(VersionType.valueOf(API.getVersion()), value);
        this.volume = volume;
        this.pitch = pitch;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSoundName() {
        final VersionType value = VersionType.valueOf(API.getVersion());
        if (!this.soundVersionCache.containsKey(value)) {
            return null;
        }
        return this.soundVersionCache.get(value);
    }
    
    public Sound addSupport(final VersionType key, final String value) {
        this.soundVersionCache.put(key, value);
        return this;
    }
    
    public void mergeSound() {
        final VersionType value = VersionType.valueOf(API.getVersion());
        if (!this.soundVersionCache.containsKey(value)) {
            final Iterator<VersionType> iterator = this.soundVersionCache.keySet().iterator();
            while (iterator.hasNext()) {
                final String s = this.soundVersionCache.get(iterator.next());
                boolean b;
                try {
                    org.bukkit.Sound.valueOf(s);
                    b = true;
                }
                catch (Exception ex) {
                    b = false;
                }
                if (b) {
                    this.addSupport(value, s);
                }
            }
        }
    }
    
    public Sound save() {
        this.mergeSound();
        Sound.soundCache.put(this.name, this);
        return this;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getVolume() {
        return this.volume;
    }
    
    public void play(final Player player, final Location location, final float n, final float n2) {
        final VersionType value = VersionType.valueOf(API.getVersion());
        if (!this.soundVersionCache.containsKey(value)) {
            throw new SoundError(value);
        }
        player.playSound(location, org.bukkit.Sound.valueOf(this.soundVersionCache.get(value)), n, n2);
    }
    
    public void play(final Player player, final float n, final float n2) {
        this.play(player, player.getLocation(), n, n2);
    }
    
    public void play(final Player player) {
        this.play(player, player.getLocation(), this.volume, this.pitch);
    }
    
    public void play(final Player player, final Location location) {
        this.play(player, location, this.volume, this.pitch);
    }
    
    public static Sound getSound(final String s) {
        if (!Sound.soundCache.containsKey(s)) {
            return null;
        }
        return Sound.soundCache.get(s);
    }
    
    public static void playSound(final Player player, final String s, final Location location, final float n, final float n2) {
        final Sound sound = getSound(s);
        if (sound == null) {
            throw new SoundNotFound(s);
        }
        sound.play(player, location, n, n2);
    }
    
    public static void playSound(final Player player, final String s, final float n, final float n2) {
        final Sound sound = getSound(s);
        if (sound == null) {
            throw new SoundNotFound(s);
        }
        sound.play(player, n, n2);
    }
    
    public static void playSound(final Player player, final String s) {
        final Sound sound = getSound(s);
        if (sound == null) {
            throw new SoundNotFound(s);
        }
        sound.play(player);
    }
    
    public static void playSound(final Player player, final String s, final Location location) {
        final Sound sound = getSound(s);
        if (sound == null) {
            throw new SoundNotFound(s);
        }
        sound.play(player, location);
    }
    
    static {
        Sound.soundCache = new HashMap<String, Sound>();
    }
}
