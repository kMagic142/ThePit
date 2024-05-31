package ro.kmagic.commands;

import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.libapi.API;
import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.ParentCommand;

public class ViewCommand extends ParentCommand {
    public ViewCommand() {
        super("view");
    }
    
    @Override
    public void sendDefaultMessage(final CommandSender commandSender) {
    }
    
    @Override
    public boolean execute(final CommandSender commandSender, final String s, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        final Player player = (Player)commandSender;
        if (!Main.getArena().displayFeature(player, 70)) {
            player.sendMessage(Main.getMessages().getString("Level.Not-Enough"));
            return false;
        }
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, Main.getMessages().getString("Soon"), 10, 20, 10);
        return true;
    }

    @Override
    public String noPermissionMessage(CommandSender p0) {
        return Main.getMessages().getString("Plugin.NoPermission");
    }
}
