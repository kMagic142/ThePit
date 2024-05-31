package ro.kmagic.commands.setup;

import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.libapi.API;
import ro.kmagic.api.events.misc.SetupSetEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.Location;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import ro.kmagic.files.map.Spawn;
import ro.kmagic.Main;
import ro.kmagic.commands.setup.utils.SetupUtils;
import ro.kmagic.libapi.command.SubCommand;

public class SetSpawnCommand extends SubCommand {
    public SetSpawnCommand() {
        super("setSpawn", SetupUtils.getSetupPermissions());
        if (Main.getSpawn().isSet(Spawn.SpawnType.Spawn)) {
            final Location location = Main.getSpawn().getLocation(Spawn.SpawnType.Spawn.toString());
            final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.5, 0.0), (Class)ArmorStand.class);
            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cSpawn"));
            final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.3, 0.0), (Class)ArmorStand.class);
            armorStand2.setMarker(true);
            armorStand2.setVisible(false);
            armorStand2.setGravity(false);
            armorStand2.setCustomNameVisible(true);
            armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cset"));
        }
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        if (!Main.getWorldManager().isOnMap(player)) {
            SetupUtils.notOnMap(player);
            return;
        }
        final Location location = player.getLocation();
        Main.getSpawn().setLocation(Spawn.SpawnType.Spawn.toString(), location);
        Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.SPAWN, player));
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit's spawn was &aset &7to your current location."));
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit's spawn was &aset &7at your current location.", 10, 20, 10);
        final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.5, 0.0), (Class)ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cSpawn"));
        final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.3, 0.0), (Class)ArmorStand.class);
        armorStand2.setMarker(true);
        armorStand2.setVisible(false);
        armorStand2.setGravity(false);
        armorStand2.setCustomNameVisible(true);
        armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cset"));
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
