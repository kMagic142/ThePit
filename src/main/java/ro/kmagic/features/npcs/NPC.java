package ro.kmagic.features.npcs;

import ro.kmagic.libapi.versionsupport.entity.entities.villager.VillagerProfessionType;
import java.util.List;
import org.bukkit.Location;
import ro.kmagic.libapi.hologram.StaticHologram;
import ro.kmagic.libapi.versionsupport.entity.entities.villager.VillagerGlobal;
import java.util.HashMap;
import ro.kmagic.libapi.utils.FileManager;

public class NPC extends FileManager {
    private static final String name = ".Name";
    private static final String profession = ".Profession";
    private final HashMap<NPCType, VillagerGlobal> npcList;
    
    public NPC() {
        super("NPCs", "Map");
        this.npcList = new HashMap<NPCType, VillagerGlobal>();
    }
    
    public void spawn(final NPCType key) {
        if (!this.isSet(key)) {
            return;
        }
        if (this.npcList.containsKey(key)) {
            this.npcList.get(key).remove();
        }
        final Location location = this.getLocation(key);
        final VillagerGlobal value = new VillagerGlobal(location);
        value.setNameVisible(false);
        value.setProfession(this.getProfession(key));
        value.setAi(false);
        value.setSilent(true);
        value.setGravity(false);
        new StaticHologram(key.toString(), location.clone().add(0.0, 1.6, 0.0), this.getName(key));
        this.npcList.put(key, value);
    }
    
    public VillagerGlobal get(final NPCType npcType) {
        if (this.npcList.containsKey(npcType)) {
            return this.npcList.get(npcType);
        }
        return null;
    }
    
    public void set(final NPCType npcType, final List<String> list, final VillagerProfessionType villagerProfessionType, final Location location) {
        this.set(npcType.toString() + ".Name", list);
        this.set(npcType + ".Profession", villagerProfessionType.name());
        this.setLocation(npcType.toString(), location);
    }
    
    public boolean isSet(final NPCType npcType) {
        return this.contains(npcType.toString() + ".Name");
    }
    
    public List<String> getName(final NPCType npcType) {
        return this.getStringList(npcType.toString() + ".Name");
    }
    
    public VillagerProfessionType getProfession(final NPCType npcType) {
        return VillagerProfessionType.valueOf(this.getString(npcType.toString() + ".Profession").toUpperCase());
    }
    
    public Location getLocation(final NPCType npcType) {
        return this.getLocation(npcType.toString());
    }
    
    public enum NPCType
    {
        Stats("Stats"), 
        NonPermanentItems("Non Permanent Items"), 
        PermanentUpgrades("Permanent Upgrades"), 
        TheKeeper("The Keeper"), 
        Prestige("Prestige"), 
        QuestMaster("Quest Master");
        
        private final String name;
        
        NPCType(final String name2) {
            this.name = name2;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
