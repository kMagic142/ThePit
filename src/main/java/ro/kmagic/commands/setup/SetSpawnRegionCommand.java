package ro.kmagic.commands.setup;

import net.md_5.bungee.api.chat.ClickEvent;
import ro.kmagic.libapi.utils.TextComponentUtil;
import ro.kmagic.api.events.misc.SetupSetEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import org.bukkit.Location;
import ro.kmagic.files.map.Region;
import ro.kmagic.Main;
import ro.kmagic.libapi.API;
import java.util.Arrays;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.commands.setup.utils.SetupUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;
import ro.kmagic.libapi.command.SubCommand;

public class SetSpawnRegionCommand extends SubCommand implements Listener {
    private final ItemStack wand;
    
    public SetSpawnRegionCommand() {
        super("setSpawnRegion", SetupUtils.getSetupPermissions());
        this.wand = Materials.DIAMOND_AXE.getItem().setUnbreakable(true).enchantment().addUnsafe(Enchantment.DURABILITY, 1).flag().add(ItemFlag.values()).setDisplayName("&f&l[&a&lThePit&f&l] &ePit spawn region Wand").setLore(Arrays.asList("&7&oSetup item", " ", "&8Left-Click&7: &fPos 1", "&8Left-Click&7: &fPos 2")).build();
        API.registerEvent(this);
        if (Main.getRegionManager().isSet(Region.RegionType.SPAWN_REGION)) {
            int i = 1;
            for (final Location location : new ArrayList<Location>(Arrays.asList(Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 1), Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 2)))) {
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 0.5, 0.5), (Class)ArmorStand.class);
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cSpawn Region pos " + i));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 0.3, 0.5), (Class)ArmorStand.class);
                armorStand2.setMarker(true);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cset"));
                ++i;
            }
        }
    }
    
    @Override
    public void execute(final CommandSender commandSender, final String[] array) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        final Player player = (Player)commandSender;
        if (!Main.getWorldManager().isOnMap(player)) {
            SetupUtils.notOnMap(player);
            return;
        }
        if (array.length != 1) {
            this.help(player);
            return;
        }
        final String lowerCase = array[0].toLowerCase();
        switch (lowerCase) {
            case "wand": {
                player.getInventory().setItem(0, this.wand);
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit spawn region wand was set on &aSlot 1&7."));
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit spawn region wand was set on &aSlot 1&7.", 10, 20, 10);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 3.0f);
                break;
            }
            case "pos1": {
                this.setPos(player, player.getLocation(), 1, false);
                break;
            }
            case "pos2": {
                this.setPos(player, player.getLocation(), 2, false);
                break;
            }
            default: {
                this.help(player);
                break;
            }
        }
    }
    
    @EventHandler
    private void onPlayerInteract(final PlayerInteractEvent playerInteractEvent) {
        if (playerInteractEvent.getItem() == null || !playerInteractEvent.getItem().equals(this.wand)) {
            return;
        }
        switch (playerInteractEvent.getAction()) {
            case LEFT_CLICK_BLOCK: {
                playerInteractEvent.setCancelled(true);
                this.setPos(playerInteractEvent.getPlayer(), playerInteractEvent.getClickedBlock().getLocation(), 1, true);
                break;
            }
            case RIGHT_CLICK_BLOCK: {
                playerInteractEvent.setCancelled(true);
                this.setPos(playerInteractEvent.getPlayer(), playerInteractEvent.getClickedBlock().getLocation(), 2, true);
                break;
            }
        }
    }
    
    private void setPos(final Player player, final Location location, final int i, final boolean b) {
        Main.getRegionManager().setPos(Region.RegionType.SPAWN_REGION, location, i);
        Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.SPAWN_REGION, player));
        final String s = b ? "clicked" : "current";
        final String s2 = b ? "to" : "at";
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit spawn region &fpos " + i + " &7was &aset &7" + s2 + " your " + s + " location."));
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit spawn region &fpos " + i + " &7was &aset &7" + s2 + " your " + s + " location.", 10, 20, 10);
        final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 0.5, 0.5), (Class)ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cSpawn Region pos " + i));
        final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 0.3, 0.5), (Class)ArmorStand.class);
        armorStand2.setMarker(true);
        armorStand2.setVisible(false);
        armorStand2.setGravity(false);
        armorStand2.setCustomNameVisible(true);
        armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cset"));
    }
    
    private void help(final Player player) {
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &a&lCOMMAND USAGE:"));
        TextComponentUtil.sendTextComponent(player, "&a\u2981 &f/ThePit SetSpawnRegion <&aargument&f>", "/thepit setspawnregion ");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getStatus(SetupUtils.SetupType.SPAWN_REGION, "Wand", true), "/thepit setspawnregion wand", ClickEvent.Action.RUN_COMMAND);
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getSpawnRegionStatus(1, "POS 1"), "/thepit setspawnregion pos1");
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getSpawnRegionStatus(2, "POS 2"), "/thepit setspawnregion pos2");
        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
