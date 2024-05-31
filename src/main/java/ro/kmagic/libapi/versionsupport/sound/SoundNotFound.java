package ro.kmagic.libapi.versionsupport.sound;

public class SoundNotFound extends RuntimeException
{
    public SoundNotFound(final String str) {
        super("The sound " + str + " was not found.");
    }
}
