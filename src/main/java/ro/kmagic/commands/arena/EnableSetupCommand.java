package ro.kmagic.commands.arena;

import ro.kmagic.libapi.API;
import org.bukkit.Bukkit;
import ro.kmagic.Main;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.commands.setup.utils.SetupUtils;
import ro.kmagic.libapi.command.SubCommand;

public class EnableSetupCommand extends SubCommand {
    public EnableSetupCommand() {
        super("enableSetup", SetupUtils.getSetupPermissions());
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lThePit &7Setup mode was enabled. &c&oThe server will restart in 5 seconds."));
        new BukkitRunnable() {
            public void run() {
                Main.getSettings().set("Plugin.SetupMode", true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe server is now restarting..."));
                Bukkit.spigot().restart();
            }
        }.runTaskLater(API.getPlugin(), 100L);
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
