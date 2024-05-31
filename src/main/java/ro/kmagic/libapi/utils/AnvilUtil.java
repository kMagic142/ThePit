package ro.kmagic.libapi.utils;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import ro.kmagic.libapi.API;
import java.util.UUID;
import java.util.ArrayList;
import org.bukkit.event.Listener;

public class AnvilUtil implements Listener
{
    public static ArrayList<UUID> uuidList;
    
    public AnvilUtil() {
        API.registerEvent(this);
    }
    
    @EventHandler
    private void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        API.getVersionSupport().injectPlayer(playerJoinEvent.getPlayer());
    }
    
    @EventHandler
    private void onPlayerQuit(final PlayerQuitEvent playerQuitEvent) {
        API.getVersionSupport().uninjectPlayer(playerQuitEvent.getPlayer());
    }
    
    @EventHandler
    private void onInventoryClose(final InventoryCloseEvent inventoryCloseEvent) {
        AnvilUtil.uuidList.remove(inventoryCloseEvent.getPlayer().getUniqueId());
    }
    
    static {
        AnvilUtil.uuidList = new ArrayList<UUID>();
    }
}
