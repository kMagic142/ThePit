package ro.kmagic.commands;

import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.kmagic.Main;
import org.bukkit.Bukkit;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.command.SubCommand;

public class GoldEconomyCommand extends SubCommand {
    public GoldEconomyCommand() {
        super("gold", new String[] { "thepit.*", "thepit.economy" });
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (array.length == 0) {
            this.gold(commandSender);
            return;
        }
        if (array.length <= 2) {
            this.gold(commandSender);
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
        PlayerEconomy.EconomyAction economyAction = null;
        String s = null;
        switch (n) {
            case 0: {
                economyAction = PlayerEconomy.EconomyAction.ADD;
                s = "Economy.GiveExecuted";
                break;
            }
            case 1: {
                economyAction = PlayerEconomy.EconomyAction.SUBTRACT;
                s = "Economy.TakeExectuted";
                break;
            }
            case 2: {
                economyAction = PlayerEconomy.EconomyAction.SET;
                s = "Economy.SetExected";
                break;
            }
            default: {
                this.gold(commandSender);
                return;
            }
        }
        final double double1 = Double.parseDouble(array[2]);
        final Player player = Bukkit.getPlayer(array[1]);
        if (player == null) {
            commandSender.sendMessage(Main.getMessages().getString("Economy.PlayerNotExist"));
            return;
        }
        PlayerEconomy.get(player).setBalance(economyAction, double1);
        commandSender.sendMessage(Main.getMessages().getString(s).replace("%target%", player.getName()).replace("%gold%", PlayerEconomy.format(double1)));
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        if (commandSender instanceof Player) {
            return this.hasPermission(commandSender) && Main.getWorldManager().isOnMap((Player)commandSender);
        }
        return this.hasPermission(commandSender);
    }
    
    private void gold(final CommandSender commandSender) {
        commandSender.sendMessage("");
        commandSender.sendMessage("");
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Command usage:"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit Gold <&aargument&f> <&aplayer&f> <&aamount&f>"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fGive"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fTake"));
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u27a6 &fSet"));
            return;
        }
        final Player player = (Player)commandSender;
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fGive", "/thepit gold give ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fTake", "/thepit gold take ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fSet", "/thepit gold set ");
    }
}
