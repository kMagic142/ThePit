package ro.kmagic.commands;

import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.kmagic.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.SubCommand;

public class BountyCommand extends SubCommand {
    public BountyCommand() {
        super("bounty", new String[] { "thepit.*", "thepit.economy" });
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (array.length == 0) {
            this.bounty(commandSender);
            return;
        }
        if (array.length <= 2) {
            this.bounty(commandSender);
            return;
        }
        final String lowerCase = array[0].toLowerCase();
        int n = -1;
        switch (lowerCase.hashCode()) {
            case 3173137: {
                if (lowerCase.equals("give")) {
                    n = 0;
                    break;
                }
                break;
            }
            case 3552391: {
                if (lowerCase.equals("take")) {
                    n = 1;
                    break;
                }
                break;
            }
            case 113762: {
                if (lowerCase.equals("set")) {
                    n = 2;
                    break;
                }
                break;
            }
        }
        BountyAction bountyAction = null;
        String s = null;
        switch (n) {
            case 0: {
                bountyAction = BountyAction.ADD;
                s = "Bounty.Give-Executed";
                break;
            }
            case 1: {
                bountyAction = BountyAction.SUBTRACT;
                s = "Bounty.Take-Executed";
                break;
            }
            case 2: {
                bountyAction = BountyAction.SET;
                s = "Bounty.Set-Executed";
                break;
            }
            default: {
                this.bounty(commandSender);
                return;
            }
        }
        final int int1 = Integer.parseInt(array[2]);
        final Player player = Bukkit.getPlayer(array[1]);
        if (player == null) {
            commandSender.sendMessage(Main.getMessages().getString("Economy.PlayerNotExist"));
            return;
        }
        Main.getBounty().setPlayerBounty(player, bountyAction, int1);
        commandSender.sendMessage(Main.getMessages().getString(s).replace("%target%", player.getName()).replace("%bounty%", Main.getBounty().format(int1)));
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        if (commandSender instanceof Player) {
            return this.hasPermission(commandSender) && Main.getWorldManager().isOnMap((Player)commandSender);
        }
        return this.hasPermission(commandSender);
    }
    
    private void bounty(final CommandSender commandSender) {
        commandSender.sendMessage("");
        commandSender.sendMessage("");
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Command usage:"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit Bounty <&aargument&f> <&aplayer&f> <&aamount&f>"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fGive"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fTake"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fSet"));
            return;
        }
        final Player player = (Player)commandSender;
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fGive", "/thepit bounty give ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fTake", "/thepit bounty take ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fSet", "/thepit bounty set ");
    }
    
    public enum BountyAction
    {
        ADD, 
        SUBTRACT, 
        SET
    }
}
