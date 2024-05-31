package ro.kmagic.commands.setup;

import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.libapi.utils.TextComponentUtil;
import org.bukkit.block.Block;
import org.bukkit.Material;
import java.util.List;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.libapi.API;
import ro.kmagic.api.events.misc.SetupSetEvent;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import ro.kmagic.files.events.EventsRegion;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import ro.kmagic.Main;
import ro.kmagic.commands.setup.utils.SetupUtils;
import java.util.ArrayList;
import org.bukkit.Location;
import ro.kmagic.libapi.command.SubCommand;

public class SetEventCommand extends SubCommand {
    private Location pos1;
    private Location pos2;
    private final ArrayList<String> blocks;
    
    public SetEventCommand() {
        super("setEvent", SetupUtils.getSetupPermissions());
        this.pos1 = null;
        this.pos2 = null;
        this.blocks = new ArrayList<String>();
        if (Main.getEventsRegion().isSet()) {
            for (final String str : Main.getEventsRegion().getNodes()) {
                for (int i = 1; i <= 2; ++i) {
                    if (Main.getEventsRegion().isSet(str)) {
                        final Location pos = Main.getEventsRegion().getPos(str, i);
                        final ArmorStand armorStand = (ArmorStand)pos.getWorld().spawn(pos.clone().add(0.0, 0.5, 0.0), (Class)ArmorStand.class);
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&9Region &f" + str + " &9pos " + i));
                        final ArmorStand armorStand2 = (ArmorStand)pos.getWorld().spawn(pos.clone().add(0.0, 0.3, 0.0), (Class)ArmorStand.class);
                        armorStand2.setMarker(true);
                        armorStand2.setVisible(false);
                        armorStand2.setGravity(false);
                        armorStand2.setCustomNameVisible(true);
                        armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&9set"));
                    }
                }
                if (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.KOTH_REGION, str)) {
                    for (String s : Main.getEventsRegion().getLocations(str, "Koth")) {
                        final String[] split = s.split(" ");
                        final Location location = new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
                        final ArmorStand armorStand3 = (ArmorStand) location.getWorld().spawn(location.clone().add(0.5, 0.5, 0.5), (Class) ArmorStand.class);
                        armorStand3.setMarker(true);
                        armorStand3.setVisible(false);
                        armorStand3.setGravity(false);
                        armorStand3.setCustomNameVisible(true);
                        armorStand3.setCustomName(ChatColor.translateAlternateColorCodes('&', "&bKing of the Hill in &f" + str + ""));
                        final ArmorStand armorStand4 = (ArmorStand) location.getWorld().spawn(location.clone().add(0.5, 0.3, 0.5), (Class) ArmorStand.class);
                        armorStand4.setMarker(true);
                        armorStand4.setVisible(false);
                        armorStand4.setGravity(false);
                        armorStand4.setCustomNameVisible(true);
                        armorStand4.setCustomName(ChatColor.translateAlternateColorCodes('&', "&bset"));
                    }
                }
                if (Main.getEventsRegion().isSet(EventsRegion.EventsRegionType.CARE_PACKAGE_REGION, str)) {
                    for (String s : Main.getEventsRegion().getLocations(str, "CarePackage")) {
                        final String[] split2 = s.split(" ");
                        final Location location2 = new Location(Bukkit.getWorld(split2[0]), Double.parseDouble(split2[1]), Double.parseDouble(split2[2]), Double.parseDouble(split2[3]));
                        final ArmorStand armorStand5 = (ArmorStand) location2.getWorld().spawn(location2.clone().add(0.5, 0.5, 0.5), (Class) ArmorStand.class);
                        armorStand5.setMarker(true);
                        armorStand5.setVisible(false);
                        armorStand5.setGravity(false);
                        armorStand5.setCustomNameVisible(true);
                        armorStand5.setCustomName(ChatColor.translateAlternateColorCodes('&', "&aCare Package in &f" + str + ""));
                        final ArmorStand armorStand6 = (ArmorStand) location2.getWorld().spawn(location2.clone().add(0.5, 0.3, 0.5), (Class) ArmorStand.class);
                        armorStand6.setMarker(true);
                        armorStand6.setVisible(false);
                        armorStand6.setGravity(false);
                        armorStand6.setCustomNameVisible(true);
                        armorStand6.setCustomName(ChatColor.translateAlternateColorCodes('&', "&aset"));
                    }
                }
            }
        }
        if (Main.getEventsRegion().isRagePitSet()) {
            for (String s : Main.getEventsRegion().getRagePitBlocks()) {
                final String[] split3 = s.split(" ");
                final Location location3 = new Location(Bukkit.getWorld(split3[0]), Double.parseDouble(split3[1]), Double.parseDouble(split3[2]), Double.parseDouble(split3[3]));
                final ArmorStand armorStand7 = (ArmorStand) location3.getWorld().spawn(location3.clone().add(0.5, 0.4, 0.5), (Class) ArmorStand.class);
                armorStand7.setMarker(true);
                armorStand7.setVisible(false);
                armorStand7.setGravity(false);
                armorStand7.setCustomNameVisible(true);
                armorStand7.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cCage set"));
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
        if (array.length == 0) {
            this.availableEvent(player);
            return;
        }
        final String lowerCase = array[0].toLowerCase();
        switch (lowerCase) {
            case "locations": {
                if (array.length != 3) {
                    this.availableEventLocations(player);
                    return;
                }
                final String lowerCase2 = array[1].toLowerCase();
                switch (lowerCase2) {
                    case "pos1": {
                        this.setAreaPos(player, array[2], 1);
                        break;
                    }
                    case "pos2": {
                        this.setAreaPos(player, array[2], 2);
                        break;
                    }
                    default: {
                        this.availableEventLocations(player);
                        break;
                    }
                }
                break;
            }
            case "holograms": {
                final Location location = player.getLocation();
                Main.getEventsHologram().addHologram(location);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.EVENTS_HOLOGRAMS, player));
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &fEvent Hologram &7was &aset &7at your current location."));
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &fEvent Hologram &7was &aset &7at your current location.", 10, 20, 10);
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.5, 0.0), (Class)ArmorStand.class);
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&bEvent Hologram"));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.3, 0.0), (Class)ArmorStand.class);
                armorStand2.setMarker(true);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&bset"));
                break;
            }
            case "koth": {
                if (Main.getEventsRegion().isSet()) {
                    this.addLocation(player, EventsRegion.EventsRegionType.KOTH_REGION);
                    break;
                }
                this.eventsLocationsNotSet(player);
                break;
            }
            case "carepackage": {
                if (Main.getEventsRegion().isSet()) {
                    this.addLocation(player, EventsRegion.EventsRegionType.CARE_PACKAGE_REGION);
                    break;
                }
                this.eventsLocationsNotSet(player);
                break;
            }
            case "ragepit": {
                if (array.length != 2) {
                    this.availableRagePit(player);
                    return;
                }
                final String lowerCase3 = array[1].toLowerCase();
                switch (lowerCase3) {
                    case "pos1": {
                        this.setRagePitPos(player, 1);
                        break;
                    }
                    case "pos2": {
                        this.setRagePitPos(player, 2);
                        break;
                    }
                    default: {
                        this.availableRagePit(player);
                        break;
                    }
                }
                break;
            }
            default: {
                this.availableEvent(player);
                break;
            }
        }
    }
    
    private void setAreaPos(final Player player, final String str, final int i) {
        final Location location = player.getLocation();
        Main.getEventsRegion().setPos(str, location, i);
        Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.EVENTS_LOCATIONS, player));
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &fEvent Region &9'" + str + "' &fpos " + i + " &7was &aset &7at your current location."));
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &fEvent Region &9'" + str + "' &fpos " + i + " &7was &aset &7at your current location.", 10, 20, 10);
        final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 0.5, 0.5), (Class)ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&9Region &f" + str + " &9pos " + i));
        final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 0.3, 0.5), (Class)ArmorStand.class);
        armorStand2.setMarker(true);
        armorStand2.setVisible(false);
        armorStand2.setGravity(false);
        armorStand2.setCustomNameVisible(true);
        armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&9set"));
    }
    
    private void addLocation(final Player player, final EventsRegion.EventsRegionType eventsRegionType) {
        final List<String> nodes = Main.getEventsRegion().getNodes();
        String s = "CarePackage";
        SetupUtils.SetupType setupType = SetupUtils.SetupType.CARE_PACKAGE;
        if (eventsRegionType.equals(EventsRegion.EventsRegionType.KOTH_REGION)) {
            s = "Koth";
            setupType = SetupUtils.SetupType.KOTH;
        }
        if (nodes == null) {
            this.eventsLocationsNotSet(player);
            return;
        }
        boolean b = false;
        for (final String str : nodes) {
            final Location pos = Main.getEventsRegion().getPos(str, 1);
            final Location pos2 = Main.getEventsRegion().getPos(str, 2);
            if (pos != null && pos2 != null && Main.getRegionManager().inRegion(player.getLocation(), pos, pos2)) {
                final Location location = player.getLocation();
                Main.getEventsRegion().setLocation(str, location, s);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(setupType, player));
                player.sendMessage(" ");
                player.sendMessage(" ");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &fEvent " + s.replace("Care", "Care ") + " &7was &aset &7at your current location, in region &f'" + str + "'&7."));
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &fEvent " + s.replace("Care", "Care ") + " &7was &aset &7at your current location, in region &f'" + str + "'&7.", 10, 20, 10);
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.5, 0.0), (Class)ArmorStand.class);
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                final String str2 = setupType.equals(SetupUtils.SetupType.KOTH) ? "&bKing of the Hill" : "&aCare Package";
                final String str3 = setupType.equals(SetupUtils.SetupType.KOTH) ? "&b" : "&a";
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', str2 + " in &f" + str + ""));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 0.3, 0.0), (Class)ArmorStand.class);
                armorStand2.setMarker(true);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', str3 + "set"));
                b = true;
                break;
            }
        }
        if (!b) {
            this.eventsLocationsNotSet(player);
        }
    }
    
    private void setRagePitPos(final Player player, final int n) {
        if (n == 1) {
            this.pos1 = player.getLocation();
        }
        else {
            this.pos2 = player.getLocation();
        }
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &fRage Pit pos " + n + " &7was &aset &7at your current location."));
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &fRage Pit pos " + n + " &7was &aset &7at your current location.", 10, 20, 10);
        if (this.pos1 == null || this.pos2 == null) {
            return;
        }
        final int max = Math.max(this.pos1.getBlockX(), this.pos2.getBlockX());
        final int min = Math.min(this.pos1.getBlockX(), this.pos2.getBlockX());
        final int max2 = Math.max(this.pos1.getBlockY(), this.pos2.getBlockY());
        final int min2 = Math.min(this.pos1.getBlockY(), this.pos2.getBlockY());
        final int max3 = Math.max(this.pos1.getBlockZ(), this.pos2.getBlockZ());
        final int min3 = Math.min(this.pos1.getBlockZ(), this.pos2.getBlockZ());
        for (int i = min; i <= max; ++i) {
            for (int j = min3; j <= max3; ++j) {
                for (int k = min2; k <= max2; ++k) {
                    final Block block = this.pos1.getWorld().getBlockAt(i, k, j);
                    if (block.getType().equals(Material.BEDROCK)) {
                        this.blocks.add(block.getWorld().getName() + " " + block.getX() + " " + block.getY() + " " + block.getZ());
                        block.setType(Material.AIR);
                        final Location location = block.getLocation();
                        final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, 0.4, 0.5), (Class)ArmorStand.class);
                        armorStand.setMarker(true);
                        armorStand.setVisible(false);
                        armorStand.setGravity(false);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cCage set"));
                    }
                }
            }
        }
        Main.getEventsRegion().setRagePit(this.blocks);
        Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.RAGE_PIT, player));
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &fRage Pit blocks &7were &asaved&7! &8(&f" + this.blocks.size() + " blocks &7were &asaved&8)"));
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &fRage Pit blocks &7were &asaved&7! &8(&f" + this.blocks.size() + " blocks &7were &asaved&8)", 10, 20, 10);
        this.blocks.clear();
    }
    
    private void availableEvent(final Player player) {
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &a&lCOMMAND USAGE:"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit SetEvent <&aargument&f>"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fLocations", "/thepit setEvent locations");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fHolograms", "/thepit setEvent holograms");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fKoth", "/thepit setEvent koth");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fCarePackage", "/thepit setEvent carePackage");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fRagePit", "/thepit setEvent ragePit");
        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
    }
    
    private void availableEventLocations(final Player player) {
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &a&lCOMMAND USAGE:"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit SetEvent locations <&aargument&f> <&aname&f>"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fPos1", "/thepit setEvent locations pos1 ");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fPos2", "/thepit setEvent locations pos2 ");
        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
    }
    
    private void availableRagePit(final Player player) {
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &a&lCOMMAND USAGE:"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u2981 &f/ThePit SetEvent ragePit <&aargument&f>"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available arguments:"));
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fPos1", "/thepit setEvent ragePit pos1");
        TextComponentUtil.sendTextComponent(player, "&a\u27a6 &fPos2", "/thepit setEvent ragePit pos2");
        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
    }
    
    private void eventsLocationsNotSet(final Player player) {
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &a&lCOMMAND USAGE:"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7In this area, there isn't any event location set."));
        TextComponentUtil.sendTextComponent(player, "&a\u2981 &f/ThePit Setup SetEvent Locations", "/thepit setEvent locations");
        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
