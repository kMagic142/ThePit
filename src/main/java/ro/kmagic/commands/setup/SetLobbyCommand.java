package ro.kmagic.commands.setup;

import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.libapi.API;
import org.bukkit.ChatColor;
import ro.kmagic.api.events.misc.SetupSetEvent;
import org.bukkit.Bukkit;
import ro.kmagic.files.map.Spawn;
import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.commands.setup.utils.SetupUtils;
import ro.kmagic.libapi.command.SubCommand;

public class SetLobbyCommand extends SubCommand {
    public SetLobbyCommand() {
        super("setLobby", SetupUtils.getSetupPermissions());
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        Main.getSpawn().setLocation(Spawn.SpawnType.Lobby.toString(), player.getLocation());
        Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.LOBBY, player));
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit's Server Spawn was &aset &7at your current location."));
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit's spawn was &aset &7at your current location.", 10, 20, 10);
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
