package ro.kmagic.commands.setup.utils;

import ro.kmagic.libapi.utils.TextComponentUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import org.bukkit.ChatColor;
import ro.kmagic.Main;
import org.bukkit.entity.Player;

public class SetupUtils {
    private static final String[] setupPermissions;
    
    public static void notOnMap(final Player player) {
        player.sendMessage("");
        player.sendMessage("");
        final String string = Main.getSettings().getString("Plugin.WorldName");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are not in world: '" + string + "'"));
        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
        TextComponentUtil.sendTextComponent(player, "&7Teleport in ThePit world &e&l[CLICK HERE]", "/thepit setmap " + string, ClickEvent.Action.RUN_COMMAND);
    }
    
    public static String[] getSetupPermissions() {
        return SetupUtils.setupPermissions;
    }
    
    static {
        setupPermissions = new String[] { "thepit.setup", "thepit.*" };
    }
    
    public enum SetupType
    {
        SPAWN("Spawn", "setSpawn"), 
        LOBBY("Lobby", "setLobby"), 
        SPAWN_REGION("Spawn Region", "setSpawnRegion "), 
        PIT_HOLE("Pit Hole", "setPitHole "), 
        NPC_STATS, 
        NPC_NON_PERMANENT_ITEMS, 
        NPC_PERMANENT_UPGRADES, 
        NPC_THE_KEEPER, 
        NPC_PRESTIGE, 
        NPC_QUEST_MASTER, 
        NPCS("NPCs", "setNpc "),
        RANDOM_GOLD("Random Gold", "setRandomGold"), 
        ENDERCHEST("Enderchest", "setEnderchest"), 
        PIT_HOLE_HOLOGRAM("Pit Hole Hologram", "setPitHole hologram"), 
        UNLOCKED_FEATURES_HOLOGRAM("Unlocked features Holo", "setUnlockedFeaturesHologram"), 
        EVENTS_LOCATIONS, 
        EVENTS_HOLOGRAMS, 
        EVENTS_LOCATIONS_HOLOGRAMS("Event Locs & Holos", "setEvent "), 
        KOTH("KOTH", "setEvent koth"), 
        CARE_PACKAGE("Care Package", "setEvent carePackage"), 
        RAGE_PIT("Rage Pit", "setEvent ragePit");
        
        private String name;
        private String command;
        private boolean isScoreboard;
        
        SetupType(final String name2, final String str) {
            this.name = null;
            this.name = name2;
            this.command = "/thepit " + str;
            this.isScoreboard = true;
        }
        
        SetupType() {
            this.name = null;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getCommand() {
            return this.command;
        }
        
        public boolean isScoreboard() {
            return this.isScoreboard;
        }
    }
}
