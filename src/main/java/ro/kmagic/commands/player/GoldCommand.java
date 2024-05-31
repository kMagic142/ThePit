package ro.kmagic.commands.player;

import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import java.util.Collections;

import ro.kmagic.libapi.command.ParentCommand;

public class GoldCommand extends ParentCommand {
    public GoldCommand() {
        super("gold");
        this.setAliases(Collections.singletonList("coins"));
    }
    
    @Override
    public void sendDefaultMessage(final CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Main.getMessages().getString("Plugin.NotAvailableInConsole"));
            return;
        }
        final Player player = (Player)commandSender;
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            player.sendMessage(Main.getMessages().getString("Plugin.NotInArena"));
            return;
        }
        player.sendMessage(Main.getMessages().getString("Economy.Balance").replace("%gold%", PlayerEconomy.format(PlayerEconomy.get(player).getBalance())));
    }

    @Override
    public String noPermissionMessage(CommandSender p0) {
        return Main.getMessages().getString("Plugin.NoPermission");
    }
}
