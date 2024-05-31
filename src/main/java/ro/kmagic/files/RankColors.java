package ro.kmagic.files;

import ro.kmagic.libapi.utils.FileManager;

public class RankColors extends FileManager {
    public RankColors() {
        super("RankColors");
        this.addDefault("Rank.Default.Color", "&7");
        this.addDefault("Rank.Owner.Color", "&4");
        this.copyDefaults();
        this.save();
    }
}
