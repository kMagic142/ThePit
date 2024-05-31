package ro.kmagic.libapi.versionsupport.particles;

import ro.kmagic.libapi.versionsupport.VersionType;

public enum Particles
{
    EXPLOSION_HUGE(new Particle("explosionHuge").addSupport(VersionType.v1_8_R3, "EXPLOSION_HUGE").save());
    
    private final Particle particle;
    
    Particles(final Particle particle) {
        this.particle = particle;
    }
    
    public Particle getParticle() {
        return this.particle;
    }
}
