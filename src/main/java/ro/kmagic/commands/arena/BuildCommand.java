package ro.kmagic.commands.arena;

import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.SubCommand;

public class BuildCommand extends SubCommand {
    public BuildCommand() {
        super("build", new String[] { "thepit.*", "thepit.build" });
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Main.getMessages().getString("Plugin.NotAvailableInConsole"));
            return;
        }
        final Player player = (Player)commandSender;
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            player.sendMessage(Main.getMessages().getString("Plugin.NotInArena"));
            return;
        }
        if (Main.getArena().getBuilders().contains(player.getUniqueId())) {
            Main.getArena().getBuilders().remove(player.getUniqueId());
            player.sendMessage(Main.getMessages().getString("Plugin.Build.Off"));
            return;
        }
        Main.getArena().getBuilders().add(player.getUniqueId());
        player.sendMessage(Main.getMessages().getString("Plugin.Build.On"));
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        if (commandSender instanceof Player) {
            return this.hasPermission(commandSender) && Main.getArena().getPlayers().contains(((Player)commandSender).getUniqueId());
        }
        return this.hasPermission(commandSender);
    }
}
