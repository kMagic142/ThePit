package ro.kmagic.commands;

import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.ParentCommand;

public class PitChatCommand extends ParentCommand {
    public PitChatCommand() {
        super("pitChat");
    }
    
    @Override
    public void sendDefaultMessage(final CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
        }
    }
    
    @Override
    public boolean execute(final CommandSender commandSender, final String s, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        final Player player = (Player)commandSender;
        if (!Main.getArena().displayFeature(player, 5)) {
            player.sendMessage(Main.getMessages().getString("Level.Not-Enough"));
            return false;
        }
        Main.getChatOption().openGui(player);
        return true;
    }

    @Override
    public String noPermissionMessage(CommandSender p0) { return Main.getMessages().getString("Plugin.NoPermission");
    }
}
