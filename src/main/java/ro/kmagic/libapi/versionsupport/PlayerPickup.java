package ro.kmagic.libapi.versionsupport;

import org.bukkit.event.EventHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.Listener;

public class PlayerPickup implements Listener
{
    @EventHandler
    private void onPlayerPickup(final PlayerPickupItemEvent playerPickupItemEvent) {
        final ro.kmagic.libapi.events.player.PlayerPickupItemEvent playerPickupItemEvent2 = new ro.kmagic.libapi.events.player.PlayerPickupItemEvent(playerPickupItemEvent.getPlayer(), playerPickupItemEvent.getItem(), playerPickupItemEvent.getRemaining());
        Bukkit.getPluginManager().callEvent(playerPickupItemEvent2);
        playerPickupItemEvent.setCancelled(playerPickupItemEvent2.isCancelled());
    }
}
