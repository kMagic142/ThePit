package ro.kmagic.commands.arena;

import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.SubCommand;

public class LeaveCommand extends SubCommand {
    public LeaveCommand() {
        super("leave");
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
        Main.getArena().leaveArena(player);
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return commandSender instanceof Player && Main.getArena().getPlayers().contains(((Player)commandSender).getUniqueId());
    }
}
