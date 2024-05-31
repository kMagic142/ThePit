package ro.kmagic.commands.player;

import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.files.map.Spawn;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.files.map.Region;
import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import java.util.*;

import ro.kmagic.libapi.command.ParentCommand;

public class SpawnCommand extends ParentCommand {
    private final ArrayList<UUID> cooldown;
    
    public SpawnCommand() {
        super("spawn");
        this.cooldown = new ArrayList<UUID>();
        this.setAliases(Collections.singletonList("respawn"));
    }
    
    @Override
    public void sendDefaultMessage(final CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Main.getMessages().getString("Plugin.NotAvailableInConsole"));
            return;
        }
        final Player player = (Player)commandSender;
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            player.sendMessage(Main.getMessages().getString("Plugin.NotInArena"));
            return;
        }
        if (Main.getRegionManager().inRegion(player.getLocation(), Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 1), Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 2))) {
            player.sendMessage(Main.getMessages().getString("Respawn.NotAvailable"));
            return;
        }
        if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.THE_BEAST) && Main.getPitEventManager().isBeast(player)) {
            player.sendMessage(Main.getMessages().getString("Respawn.Not-Available-As-Beast"));
            return;
        }
        if (this.cooldown.contains(player.getUniqueId())) {
            player.sendMessage(Main.getMessages().getString("Respawn.Cooldown"));
            return;
        }
        if (Main.getCombatManager().inCombat(player)) {
            player.sendMessage(Main.getMessages().getString("Respawn.InCombat").replace("%seconds%", String.valueOf(Main.getSettings().getInt("CombatLog.Time") - Main.getCombatManager().getSeconds(player))));
            return;
        }
        if (!Main.getSpawn().isSet(Spawn.SpawnType.Spawn)) {
            player.sendMessage(Main.getMessages().getString("SpawnNotSet"));
            return;
        }
        this.cooldown.add(player.getUniqueId());
        Main.getArena().giveKit(player, false, 15);
        Main.getArena().giveArrows(player);
        Main.getArena().spawn(player);
        Main.getPerk().giveItems(player);
        Sounds.WITHER_SHOOT.getSound().play(player, 1.0f, 1.0f);
        player.getInventory().remove(Materials.GOLDEN_APPLE.getItem().getMaterial());
        player.getInventory().remove(Materials.PLAYER_HEAD.getItem().getMaterial());
        player.getInventory().remove(Materials.POTION.getItem().getMaterial());
        player.getInventory().remove(Materials.POTATO.getItem().getMaterial());
        player.getInventory().remove(Materials.MUSHROOM_STEW.getItem().getMaterial());
        new BukkitRunnable() {
            public void run() {
                SpawnCommand.this.cooldown.remove(player.getUniqueId());
            }
        }.runTaskLater(API.getPlugin(), 200L);
    }

    @Override
    public String noPermissionMessage(CommandSender p0) {
        return Main.getMessages().getString("Plugin.NoPermission");
    }
}
