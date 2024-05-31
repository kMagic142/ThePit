package ro.kmagic.commands;

import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.SubCommand;

public class PrestigeCommand extends SubCommand {
    public PrestigeCommand() {
        super("prestige", new String[] { "thepit.*", "thepit.economy" });
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (array.length == 0) {
            this.renown(commandSender);
            return;
        }
        if (array.length <= 2) {
            this.renown(commandSender);
            return;
        }
        final Player player = Bukkit.getPlayer(array[1]);
        if (Integer.parseInt(array[2]) > 35) {
            commandSender.sendMessage(Main.getMessages().getString("Prestige.More-Than-Allowed"));
            return;
        }
        if ("set".equalsIgnoreCase(array[0])) {
            if (player == null) {
                commandSender.sendMessage(Main.getMessages().getString("Economy.PlayerNotExist"));
                return;
            }
            final PlayerPrestige value = PlayerPrestige.get(player);
            value.setPrestige(Integer.parseInt(array[2]));
            commandSender.sendMessage(Main.getMessages().getString("Prestige.Set").replace("%target%", player.getName()).replace("%prestige%", String.valueOf(value.getPrestige())));
        }
        else {
            this.renown(commandSender);
        }
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        if (commandSender instanceof Player) {
            return this.hasPermission(commandSender) && Main.getWorldManager().isOnMap((Player)commandSender);
        }
        return this.hasPermission(commandSender);
    }
    
    private void renown(final CommandSender commandSender) {
        commandSender.sendMessage("");
        commandSender.sendMessage("");
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Command usage:"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit Prestige <&aargument&f> <&aplayer&f> <&aamount&f>"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fSet"));
            return;
        }
        TextComponentUtil.sendTextComponent((Player)commandSender, "&a\u27a6 &fSet", "/thepit prestige set ");
    }
}
