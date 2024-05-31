package ro.kmagic.commands.setup;

import net.md_5.bungee.api.chat.ClickEvent;
import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import ro.kmagic.api.events.misc.SetupSetEvent;
import org.bukkit.Bukkit;
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

public class SetPitHoleCommand extends SubCommand implements Listener {
    private final ItemStack wand;
    
    public SetPitHoleCommand() {
        super("setPitHole", SetupUtils.getSetupPermissions());
        this.wand = Materials.IRON_AXE.getItem().setUnbreakable(true).enchantment().addUnsafe(Enchantment.DURABILITY, 1).flag().add(ItemFlag.values()).setDisplayName("&f&l[&a&lThePit&f&l] &ePit hole region Wand").setLore(Arrays.asList("&7&oSetup item", " ", "&8Left-Click&7: &fPos 1", "&8Left-Click&7: &fPos 2")).build();
        API.registerEvent(this);
        if (Main.getRegionManager().isSet(Region.RegionType.PIT_HOLE_REGION)) {
            int i = 1;
            for (final Location location : new ArrayList<Location>(Arrays.asList(Main.getRegionManager().getPos(Region.RegionType.PIT_HOLE_REGION, 1), Main.getRegionManager().getPos(Region.RegionType.PIT_HOLE_REGION, 2)))) {
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 1.5, 0.5), (Class)ArmorStand.class);
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&ePit Hole pos " + i));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 1.3, 0.5), (Class)ArmorStand.class);
                armorStand2.setMarker(true);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eset"));
                ++i;
            }
        }
        if (Main.getHologram().isPitHoleHologramSet()) {
            final Location pitHoleLocation = Main.getHologram().getPitHoleLocation();
            final ArmorStand armorStand3 = (ArmorStand)pitHoleLocation.getWorld().spawn(pitHoleLocation.clone().add(0.0, 1.5, 0.0), (Class)ArmorStand.class);
            armorStand3.setMarker(true);
            armorStand3.setVisible(false);
            armorStand3.setGravity(false);
            armorStand3.setCustomNameVisible(true);
            armorStand3.setCustomName(ChatColor.translateAlternateColorCodes('&', "&ePit Hole hologram"));
            final ArmorStand armorStand4 = (ArmorStand)pitHoleLocation.getWorld().spawn(pitHoleLocation.clone().add(0.0, 1.3, 0.0), (Class)ArmorStand.class);
            armorStand4.setMarker(true);
            armorStand4.setVisible(false);
            armorStand4.setGravity(false);
            armorStand4.setCustomNameVisible(true);
            armorStand4.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eset"));
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
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit hole region wand was set on &aSlot 1&7."));
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit hole region wand was set on &aSlot 1&7.", 10, 20, 10);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 3.0f);
                break;
            }
            case "hologram": {
                final Location location = player.getLocation();
                Main.getHologram().setPitHoleHologram(location);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.PIT_HOLE_HOLOGRAM, player));
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &fHole hologram &7was set at your current location."));
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &fHole hologram &7was set at your current location.", 10, 20, 10);
                Sounds.NOTE_PLING.getSound().play(player, 3.0f, 3.0f);
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 1.5, 0.0), (Class)ArmorStand.class);
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&ePit Hole hologram"));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 1.3, 0.0), (Class)ArmorStand.class);
                armorStand2.setMarker(true);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eset"));
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
        final Player player = playerInteractEvent.getPlayer();
        switch (playerInteractEvent.getAction()) {
            case LEFT_CLICK_BLOCK: {
                this.setPos(player, playerInteractEvent, 1);
                break;
            }
            case RIGHT_CLICK_BLOCK: {
                this.setPos(player, playerInteractEvent, 2);
                break;
            }
        }
    }
    
    private void setPos(final Player player, final PlayerInteractEvent playerInteractEvent, final int i) {
        playerInteractEvent.setCancelled(true);
        final Location location = playerInteractEvent.getClickedBlock().getLocation();
        Main.getRegionManager().setPos(Region.RegionType.PIT_HOLE_REGION, location, i);
        Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.PIT_HOLE, player));
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit hole region &fpos " + i + " &7was &aset &7to your clicked location."));
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit hole region &fpos " + i + " &7was &aset &7to your clicked location.", 10, 20, 10);
        final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 1.5, 0.5), (Class)ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&ePit Hole pos " + i));
        final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 1.3, 0.5), (Class)ArmorStand.class);
        armorStand2.setMarker(true);
        armorStand2.setVisible(false);
        armorStand2.setGravity(false);
        armorStand2.setCustomNameVisible(true);
        armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eset"));
    }
    
    private void help(final Player player) {
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &a&lCOMMAND USAGE:"));
        TextComponentUtil.sendTextComponent(player, "&a\u2981 &f/ThePit SetPitHole <&aargument&f>", "/thepit setpithole ");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getStatus(SetupUtils.SetupType.PIT_HOLE, "Wand", true), "/thepit setpithole wand", ClickEvent.Action.RUN_COMMAND);
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getStatus(SetupUtils.SetupType.PIT_HOLE_HOLOGRAM, "Hologram", true), "/thepit setpithole hologram");
        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
