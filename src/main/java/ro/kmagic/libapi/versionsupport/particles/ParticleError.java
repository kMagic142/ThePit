package ro.kmagic.libapi.versionsupport.particles;

import ro.kmagic.libapi.versionsupport.VersionType;

public class ParticleError extends RuntimeException
{
    public ParticleError(final VersionType obj) {
        super("This particle does not support version " + obj);
    }
}
