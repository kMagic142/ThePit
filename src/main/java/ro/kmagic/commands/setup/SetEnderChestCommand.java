package ro.kmagic.commands.setup;

import org.bukkit.event.EventHandler;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import ro.kmagic.api.events.misc.SetupSetEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import org.bukkit.ChatColor;
import ro.kmagic.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import ro.kmagic.libapi.API;
import java.util.Arrays;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.commands.setup.utils.SetupUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;
import ro.kmagic.libapi.command.SubCommand;

public class SetEnderChestCommand extends SubCommand implements Listener {
    private final ItemStack wand;
    
    public SetEnderChestCommand() {
        super("setEnderchest", SetupUtils.getSetupPermissions());
        this.wand = Materials.ENDER_EYE.getItem().setUnbreakable(true).enchantment().addUnsafe(Enchantment.DURABILITY, 1).flag().add(ItemFlag.values()).setDisplayName("&f&l[&a&lThePit&f&l] &ePit Ender chest Wand").setLore(Arrays.asList("&7&oSetup item", " ", "&8Right-Click&7: &fAdd block")).build();
        API.registerEvent(this);
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
        player.getInventory().setItem(0, this.wand);
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit Ender chest wand was set on &aSlot 1&7."));
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit Ender chest wand was set on &aSlot 1&7.", 10, 20, 10);
        Sounds.NOTE_PLING.getSound().play(player, 3.0f, 3.0f);
    }
    
    @EventHandler
    private void onPlayerInteract(final PlayerInteractEvent playerInteractEvent) {
        if (playerInteractEvent.getItem() == null || !playerInteractEvent.getItem().equals(this.wand)) {
            return;
        }
        final Player player = playerInteractEvent.getPlayer();
        switch (playerInteractEvent.getAction()) {
            case RIGHT_CLICK_BLOCK: {
                playerInteractEvent.setCancelled(true);
                if (!playerInteractEvent.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
                    player.sendMessage(" ");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &cThe clicked block must be an &fEnder chest&c."));
                    API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                    API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&cThe clicked block must be an &fEnder chest&c.", 10, 20, 10);
                    Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                    return;
                }
                player.sendMessage(" ");
                player.sendMessage(" ");
                final Location location = playerInteractEvent.getClickedBlock().getLocation();
                if (Main.getEnderchest().isEnderchestSet(location)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &fEnder chest &7is &calready set &7to your clicked location."));
                    API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                    API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &fEnder chest &7is &calready set &7to your clicked location.", 10, 20, 10);
                    Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                    break;
                }
                Main.getEnderchest().addEnderchest(playerInteractEvent.getClickedBlock().getLocation());
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.ENDERCHEST, player));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &fEnder chest &7was &aadded &7to your clicked location."));
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
                API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &fEnder chest &7was &aadded &7to your clicked location.", 10, 20, 10);
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, -0.7, 0.5), (Class)ArmorStand.class);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&5Ender chest"));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.5, -0.9, 0.5), (Class)ArmorStand.class);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&5set"));
                break;
            }
            case LEFT_CLICK_BLOCK: {
                playerInteractEvent.setCancelled(true);
                break;
            }
        }
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
