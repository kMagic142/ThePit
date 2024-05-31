package ro.kmagic.commands.setup;

import ro.kmagic.libapi.utils.TextComponentUtil;
import ro.kmagic.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.commands.setup.utils.SetupUtils;
import ro.kmagic.libapi.command.SubCommand;

public class SetupProgressCommand extends SubCommand {
    public SetupProgressCommand() {
        super("setupProgress", SetupUtils.getSetupPermissions());
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Setup progress:"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Required:"));
        for (final SetupUtils.SetupType setupType : SetupUtils.SetupType.values()) {
            if (setupType.getName() != null) {
                TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getStatus(setupType, setupType.getName(), true), setupType.getCommand());
                if (setupType.equals(SetupUtils.SetupType.RANDOM_GOLD)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Optional:"));
                }
            }
        }
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
