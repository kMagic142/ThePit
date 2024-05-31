package ro.kmagic.libapi.versionsupport.sound;

import ro.kmagic.libapi.versionsupport.VersionType;

public class SoundError extends RuntimeException
{
    public SoundError(final VersionType obj) {
        super("This sound does not support version " + obj);
    }
}
