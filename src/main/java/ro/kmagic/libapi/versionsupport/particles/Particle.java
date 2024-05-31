package ro.kmagic.libapi.versionsupport.particles;

import org.bukkit.entity.Player;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.versionsupport.VersionType;
import java.util.HashMap;

public class Particle {
    public static HashMap<String, Particle> particleCache;
    private final HashMap<VersionType, String> particleVersionCache;
    private final String name;
    
    public Particle(final String name) {
        this.name = name;
        this.particleVersionCache = new HashMap<VersionType, String>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getParticleName() {
        final VersionType value = VersionType.valueOf(API.getVersion());
        if (!this.particleVersionCache.containsKey(value)) {
            return null;
        }
        return this.particleVersionCache.get(value);
    }
    
    public Particle addSupport(final VersionType key, final String value) {
        this.particleVersionCache.put(key, value);
        return this;
    }
    
    public void mergeParticle() {
        final VersionType value = VersionType.valueOf(API.getVersion());
        if (!this.particleVersionCache.containsKey(value)) {
            for (VersionType versionType : this.particleVersionCache.keySet()) {
                final String s = this.particleVersionCache.get(versionType);
                boolean b;
                try {
                    b = true;
                } catch (Exception ex) {
                    b = false;
                }
                if (b) {
                    this.addSupport(value, s);
                }
            }
        }
    }
    
    public Particle save() {
        this.mergeParticle();
        Particle.particleCache.put(this.name, this);
        return this;
    }
    
    public void spawn(final Player player, final int n) {
        final VersionType value = VersionType.valueOf(API.getVersion());
        if (!this.particleVersionCache.containsKey(value)) {
            throw new ParticleError(value);
        }
        API.getVersionSupport().spawnParticle(player, this.particleVersionCache.get(value), n);
    }
    
    public static Particle getParticle(final String s) {
        if (!Particle.particleCache.containsKey(s)) {
            return null;
        }
        return Particle.particleCache.get(s);
    }
    
    static {
        Particle.particleCache = new HashMap<String, Particle>();
    }
}
