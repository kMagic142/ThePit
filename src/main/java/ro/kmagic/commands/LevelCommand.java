package ro.kmagic.commands;

import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.kmagic.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.SubCommand;

public class LevelCommand extends SubCommand {
    public LevelCommand() {
        super("level", new String[] { "thepit.*", "thepit.economy" });
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (array.length == 0) {
            this.level(commandSender);
            return;
        }
        if (array.length <= 2) {
            this.level(commandSender);
            return;
        }
        final Player player = Bukkit.getPlayer(array[1]);
        if (Integer.parseInt(array[2]) > 120) {
            commandSender.sendMessage(Main.getMessages().getString("Level.More-Than-Allowed"));
            return;
        }
        if (player == null) {
            commandSender.sendMessage(Main.getMessages().getString("Economy.PlayerNotExist"));
            return;
        }
        final String lowerCase = array[0].toLowerCase();
        switch (lowerCase) {
            case "set": {
                player.setLevel(Integer.parseInt(array[2]));
                break;
            }
            case "add": {
                player.setLevel(player.getLevel() + Integer.parseInt(array[2]));
                break;
            }
            case "subtract": {
                player.setLevel(player.getLevel() - Integer.parseInt(array[2]));
                break;
            }
            default: {
                this.level(commandSender);
                break;
            }
        }
        commandSender.sendMessage(Main.getMessages().getString("Level.Set").replace("%target%", player.getName()).replace("%level%", String.valueOf(player.getLevel())));
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        if (commandSender instanceof Player) {
            return this.hasPermission(commandSender) && Main.getWorldManager().isOnMap((Player)commandSender);
        }
        return this.hasPermission(commandSender);
    }
    
    private void level(final CommandSender commandSender) {
        commandSender.sendMessage("");
        commandSender.sendMessage("");
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Command usage:"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit level <&aargument&f> <&aplayer&f> <&aamount&f>"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fSet"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fAdd"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fSubtract"));
            return;
        }
        final Player player = (Player)commandSender;
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fSet", "/thepit level set ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fAdd", "/thepit level add ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fSubtract", "/thepit level subtract ");
    }
}
