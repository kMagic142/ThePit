package ro.kmagic.features.chatoption;

import ro.kmagic.libapi.API;

import java.util.Map;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;

public class PlayerChatOption {
    private final ArrayList<ChatOption.ChatOptionType> chatOptionTypeMap;
    private final UUID uuid;
    private static HashMap<UUID, PlayerChatOption> playerChatOptionMap;
    
    public PlayerChatOption(final Player player, final HashMap<ChatOption.ChatOptionType, String> hashMap) {
        this.chatOptionTypeMap = new ArrayList<>();
        this.uuid = player.getUniqueId();
        for (final Map.Entry<ChatOption.ChatOptionType, String> entry : hashMap.entrySet()) {
            if (entry.getValue().equals("true")) {
                this.chatOptionTypeMap.add(entry.getKey());
            }
        }
        PlayerChatOption.playerChatOptionMap.put(player.getUniqueId(), this);
    }
    
    public static boolean isCached(final Player player) {
        return PlayerChatOption.playerChatOptionMap.containsKey(player.getUniqueId());
    }
    
    public static PlayerChatOption get(final Player player) {
        return PlayerChatOption.playerChatOptionMap.get(player.getUniqueId());
    }
    
    public void toggle(final ChatOption.ChatOptionType o) {
        if (this.chatOptionTypeMap.contains(o)) {
            this.disable(o);
            return;
        }
        this.enable(o);
    }
    
    public boolean isEnabled(final ChatOption.ChatOptionType o) {
        return this.chatOptionTypeMap.contains(o);
    }
    
    public ArrayList<ChatOption.ChatOptionType> getEnabledChatOptions() {
        return this.chatOptionTypeMap;
    }
    
    private void disable(final ChatOption.ChatOptionType o) {
        this.chatOptionTypeMap.remove(o);
        API.getDatabase().setString(this.uuid, "false", o.name(), ChatOption.getTableName());
    }
    
    private void enable(final ChatOption.ChatOptionType e) {
        this.chatOptionTypeMap.add(e);
        API.getDatabase().setString(this.uuid, "true", e.name(), ChatOption.getTableName());
    }
    
    static {
        PlayerChatOption.playerChatOptionMap = new HashMap<>();
    }
}
