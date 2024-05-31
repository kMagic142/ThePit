package ro.kmagic.commands.setup;

import org.bukkit.ChatColor;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.commands.setup.utils.SetupUtils;
import ro.kmagic.libapi.command.SubCommand;

public class SetMapCommand extends SubCommand {
    public SetMapCommand() {
        super("setMap", SetupUtils.getSetupPermissions());
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        Main.getWorldManager().teleport(player);
        new BukkitRunnable() {
            public void run() {
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7You have been teleported in &fThePit World&7.", 10, 20, 10);
            }
        }.runTaskLater(API.getPlugin(), 40L);
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7You have been teleported in &fThePit World&7."));
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
