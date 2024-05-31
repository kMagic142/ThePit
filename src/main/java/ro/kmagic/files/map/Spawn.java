package ro.kmagic.files.map;

import ro.kmagic.libapi.utils.FileManager;

public class Spawn extends FileManager {
    public Spawn() {
        super("Spawn", "Map");
    }
    
    public boolean isSet(final SpawnType spawnType) {
        return this.contains(spawnType.toString()) && this.getLocation(spawnType.toString()) != null;
    }
    
    public enum SpawnType {
        Spawn, 
        Lobby
    }
}
