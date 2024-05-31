package ro.kmagic.libapi.versionsupport.particles;

public class ParticleNotFound extends RuntimeException
{
    public ParticleNotFound(final String str) {
        super("The particle " + str + " was not found.");
    }
}
