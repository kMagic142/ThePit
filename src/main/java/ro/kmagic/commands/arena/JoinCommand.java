package ro.kmagic.commands.arena;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.Main;
import java.util.List;
import ro.kmagic.libapi.command.SubCommand;

public class JoinCommand extends SubCommand {
    private final List<String> allowedWords;
    private final String notAvailableWorldMessage;
    
    public JoinCommand() {
        super("join");
        this.allowedWords = Main.getSettings().getStringList("Plugin.Allowed-Command-In-World");
        this.notAvailableWorldMessage = Main.getMessages().getString("Plugin.Command-Not-Available-In-World");
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Main.getMessages().getString("Plugin.NotAvailableInConsole"));
            return;
        }
        final Player player = (Player)commandSender;
        if (!this.allowedWords.contains(player.getWorld().getName())) {
            player.sendMessage(this.notAvailableWorldMessage);
            return;
        }
        if (Main.getArena().getPlayers().contains(player.getUniqueId())) {
            player.sendMessage(Main.getMessages().getString("Plugin.AlreadyInArena"));
            return;
        }
        Main.getArena().joinArena(player);
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return commandSender instanceof Player && !Main.getArena().getPlayers().contains(((Player)commandSender).getUniqueId());
    }
}
