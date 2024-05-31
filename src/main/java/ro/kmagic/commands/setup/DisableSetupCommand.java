package ro.kmagic.commands.setup;

import ro.kmagic.libapi.API;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.ChatColor;
import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.commands.setup.utils.SetupUtils;
import ro.kmagic.libapi.command.SubCommand;

public class DisableSetupCommand extends SubCommand {
    private final String dot = "&a\u2981 ";
    
    public DisableSetupCommand() {
        super("disableSetup", SetupUtils.getSetupPermissions());
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        for (final SetupUtils.SetupType setupType : SetupUtils.SetupType.values()) {
            if (!setupType.toString().contains("NPC_") && !setupType.equals(SetupUtils.SetupType.ENDERCHEST) && !setupType.equals(SetupUtils.SetupType.PIT_HOLE_HOLOGRAM) && !setupType.equals(SetupUtils.SetupType.EVENTS_LOCATIONS) && !setupType.equals(SetupUtils.SetupType.EVENTS_HOLOGRAMS) && !setupType.equals(SetupUtils.SetupType.EVENTS_LOCATIONS_HOLOGRAMS) && !setupType.equals(SetupUtils.SetupType.KOTH) && !setupType.equals(SetupUtils.SetupType.CARE_PACKAGE) && !setupType.equals(SetupUtils.SetupType.RAGE_PIT) && Main.getSetupAssister().getStatus(setupType, "", true).contains("\u2716")) {
                this.notSetHelp(player, setupType);
                return;
            }
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lThePit &7Setup mode was disabled, the server will restart in 5 seconds."));
        new BukkitRunnable() {
            public void run() {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&o&lRestarting the server..."));
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    if (player.hasPermission("thepit.setup")) {
                        player.getInventory().clear();
                        player.getInventory().setHelmet(null);
                        player.getInventory().setChestplate(null);
                        player.getInventory().setLeggings(null);
                        player.getInventory().setBoots(null);
                    }
                }
                Main.getSettings().set("Plugin.SetupMode", false);
                Bukkit.spigot().restart();
            }
        }.runTaskLater(API.getPlugin(), 100L);
    }
    
    private void notSetHelp(final Player player, final SetupUtils.SetupType setupType) {
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getSetupAssister().getStatus(setupType, setupType.getName(), true)));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 /thepit " + setupType.getCommand()));
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
