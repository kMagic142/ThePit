package ro.kmagic.commands;

import ro.kmagic.Main;
import org.bukkit.ChatColor;
import ro.kmagic.libapi.API;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.ParentCommand;

public class ThePitCommand extends ParentCommand {
    public ThePitCommand() {
        super("thepit");
    }
    
    @Override
    public void sendDefaultMessage(final CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &a" + API.getPlugin().getDescription().getName() + " &7- &a" + API.getPlugin().getDescription().getVersion()));
        this.showCommandsList(player);
    }

    @Override
    public String noPermissionMessage(CommandSender p0) { return Main.getMessages().getString("Plugin.NoPermission");
    }
}
