package ro.kmagic.managers;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.Main;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import org.bukkit.event.Listener;

public class SoundManager implements Listener {
    @EventHandler
    private void onPlayerKill(final PlayerDeathEvent playerDeathEvent) {
        final Player killed = playerDeathEvent.getKilled();
        if (!Main.getArena().getPlayers().contains(killed.getUniqueId()) || playerDeathEvent.getKiller() == null) {
            return;
        }
        Sounds.ORB_PICKUP.getSound().play(killed, 1.0f, 3.0f);
    }
}
