package ro.kmagic.commands;

import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.kmagic.Main;
import org.bukkit.Bukkit;
import ro.kmagic.features.economy.renown.RenownEconomy;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.SubCommand;

public class RenownEconomyCommand extends SubCommand {
    public RenownEconomyCommand() {
        super("renown", new String[] { "thepit.*", "thepit.economy" });
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
        RenownEconomy.RenownAction renownAction = null;
        String s = null;
        switch (n) {
            case 0: {
                renownAction = RenownEconomy.RenownAction.ADD;
                s = "Economy.GiveExecuted";
                break;
            }
            case 1: {
                renownAction = RenownEconomy.RenownAction.SUBTRACT;
                s = "Economy.TakeExectuted";
                break;
            }
            case 2: {
                renownAction = RenownEconomy.RenownAction.SET;
                s = "Economy.SetExected";
                break;
            }
            default: {
                this.renown(commandSender);
                return;
            }
        }
        final int int1 = Integer.parseInt(array[2]);
        final Player player = Bukkit.getPlayer(array[1]);
        if (player == null) {
            commandSender.sendMessage(Main.getMessages().getString("Economy.PlayerNotExist"));
            return;
        }
        RenownEconomy.get(player).setBalance(renownAction, int1);
        commandSender.sendMessage(Main.getMessages().getString(s).replace("%target%", player.getName()).replace("%gold%", RenownEconomy.format(int1)));
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
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit Renown <&aargument&f> <&aplayer&f> <&aamount&f>"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fGive"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fTake"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fSet"));
            return;
        }
        final Player player = (Player)commandSender;
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fGive", "/thepit renown give ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fTake", "/thepit renown take ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fSet", "/thepit renown set ");
    }
}
