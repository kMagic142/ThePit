package ro.kmagic.libapi.command;

import org.bukkit.Location;
import org.bukkit.ChatColor;
import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.entity.Player;
import java.util.Iterator;
import java.util.Arrays;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;

public abstract class ParentCommand extends Command
{
    private final List<SubCommand> subCommands;
    
    public ParentCommand(final String s) {
        super(s);
        this.subCommands = new ArrayList<SubCommand>();
    }
    
    public boolean execute(final CommandSender commandSender, final String s, final String[] original) {
        if (original.length == 0) {
            this.sendDefaultMessage(commandSender);
            return true;
        }
        for (final SubCommand subCommand : this.getSubCommands()) {
            if (subCommand.getName().equalsIgnoreCase(original[0])) {
                if (subCommand.hasPermission(commandSender)) {
                    subCommand.execute(commandSender, Arrays.copyOfRange(original, 1, original.length));
                    return true;
                }
                commandSender.sendMessage(this.noPermissionMessage(commandSender));
                return true;
            }
        }
        this.sendDefaultMessage(commandSender);
        return true;
    }
    
    public abstract void sendDefaultMessage(final CommandSender p0);
    
    public abstract String noPermissionMessage(final CommandSender p0);
    
    public void showCommandsList(final CommandSender commandSender) {
        for (final SubCommand subCommand : this.subCommands) {
            if (subCommand.canSee(commandSender)) {
                if (commandSender instanceof Player) {
                    TextComponentUtil.sendTextComponent((Player)commandSender, "&a\u2981 &f/" + this.getName() + " " + subCommand.getName() + " &7&o- " + subCommand.getDescription(), "/" + this.getName() + " " + subCommand.getName(), subCommand.getDescription(), subCommand.getClickAction());
                }
                else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/" + this.getName() + " " + subCommand.getName() + " &7&o- " + subCommand.getDescription()));
                }
            }
        }
    }
    
    public List<SubCommand> getSubCommands() {
        return new ArrayList<SubCommand>(this.subCommands);
    }
    
    public boolean hasSubCommand(final String anotherString) {
        final Iterator<SubCommand> iterator = this.subCommands.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equalsIgnoreCase(anotherString)) {
                return true;
            }
        }
        return false;
    }
    
    public SubCommand getSubCommand(final String anotherString) {
        for (final SubCommand subCommand : this.subCommands) {
            if (subCommand.getName().equalsIgnoreCase(anotherString)) {
                return subCommand;
            }
        }
        return null;
    }
    
    public boolean addSubCommand(final SubCommand subCommand, final String description) {
        if (this.hasSubCommand(subCommand.getName())) {
            return false;
        }
        this.subCommands.add(subCommand);
        subCommand.setDescription(description);
        return true;
    }
    
    public boolean removeSubCommand(final String s) {
        return this.subCommands.remove(this.getSubCommand(s));
    }
    
    public List<String> tabComplete(final CommandSender commandSender, final String s, final String[] array, final Location location) {
        if (array.length == 1) {
            final ArrayList<String> list = new ArrayList<String>();
            for (final SubCommand subCommand : this.getSubCommands()) {
                if (subCommand.canSee(commandSender)) {
                    list.add(subCommand.getName() + " ");
                }
            }
            return list;
        }
        if (array.length == 2 && this.hasSubCommand(array[0])) {
            return this.getSubCommand(array[0]).tabComplete(commandSender, s, array, location);
        }
        return null;
    }
}
