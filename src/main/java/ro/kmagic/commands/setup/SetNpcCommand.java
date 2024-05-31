package ro.kmagic.commands.setup;

import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.libapi.utils.TextComponentUtil;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.libapi.API;
import ro.kmagic.api.events.misc.SetupSetEvent;
import org.bukkit.Bukkit;
import ro.kmagic.libapi.versionsupport.entity.entities.villager.VillagerProfessionType;
import java.util.Arrays;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.Location;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import ro.kmagic.Main;
import ro.kmagic.features.npcs.NPC;
import ro.kmagic.commands.setup.utils.SetupUtils;
import ro.kmagic.libapi.command.SubCommand;

public class SetNpcCommand extends SubCommand {
    public SetNpcCommand() {
        super("setNpc", SetupUtils.getSetupPermissions());
        for (final NPC.NPCType npcType : NPC.NPCType.values()) {
            if (Main.getNpc().isSet(npcType)) {
                final Location location = Main.getNpc().getLocation(npcType);
                final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 2.2, 0.0), (Class)ArmorStand.class);
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&aNPC " + npcType.getName()));
                final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 2.0, 0.0), (Class)ArmorStand.class);
                armorStand2.setMarker(true);
                armorStand2.setVisible(false);
                armorStand2.setGravity(false);
                armorStand2.setCustomNameVisible(true);
                armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&aset"));
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
            this.availableNPCs(player);
            return;
        }
        boolean b = true;
        final String lowerCase = array[0].toLowerCase();
        NPC.NPCType npcType = null;
        switch (lowerCase) {
            case "stats": {
                npcType = NPC.NPCType.Stats;
                Main.getNpc().set(NPC.NPCType.Stats, Arrays.asList("&3&lSTATS", "&7View your stats"), VillagerProfessionType.FARMER, player.getLocation());
                Main.getNpc().spawn(NPC.NPCType.Stats);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.NPC_STATS, player));
                break;
            }
            case "nonpermanentitems": {
                npcType = NPC.NPCType.NonPermanentItems;
                Main.getNpc().set(NPC.NPCType.NonPermanentItems, Arrays.asList("&6&lITEMS", "&7Non-permanent"), VillagerProfessionType.BLACKSMITH, player.getLocation());
                Main.getNpc().spawn(NPC.NPCType.NonPermanentItems);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.NPC_NON_PERMANENT_ITEMS, player));
                break;
            }
            case "permanentupgrades": {
                npcType = NPC.NPCType.PermanentUpgrades;
                Main.getNpc().set(NPC.NPCType.PermanentUpgrades, Arrays.asList("&a&lUPGRADES", "&7Permanent"), VillagerProfessionType.NITWIT, player.getLocation());
                Main.getNpc().spawn(NPC.NPCType.PermanentUpgrades);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.NPC_PERMANENT_UPGRADES, player));
                break;
            }
            case "thekeeper": {
                npcType = NPC.NPCType.TheKeeper;
                Main.getNpc().set(NPC.NPCType.TheKeeper, Arrays.asList("&2&lTHE KEEPER", "&7Back to Lobby!"), VillagerProfessionType.BUTCHER, player.getLocation());
                Main.getNpc().spawn(NPC.NPCType.TheKeeper);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.NPC_THE_KEEPER, player));
                break;
            }
            case "prestige": {
                npcType = NPC.NPCType.Prestige;
                Main.getNpc().set(NPC.NPCType.Prestige, Arrays.asList("&e&lPRESTIGE", "&7Resets & Renown"), VillagerProfessionType.PRIEST, player.getLocation());
                Main.getNpc().spawn(NPC.NPCType.Prestige);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.NPC_PRESTIGE, player));
                break;
            }
            case "questmaster": {
                npcType = NPC.NPCType.QuestMaster;
                Main.getNpc().set(NPC.NPCType.QuestMaster, Arrays.asList("&b&lQUEST MASTER", "&7Quests & Contracts"), VillagerProfessionType.LIBRARIAN, player.getLocation());
                Main.getNpc().spawn(NPC.NPCType.QuestMaster);
                Bukkit.getPluginManager().callEvent(new SetupSetEvent(SetupUtils.SetupType.NPC_QUEST_MASTER, player));
                break;
            }
            default: {
                b = false;
                npcType = null;
                this.availableNPCs(player);
                break;
            }
        }
        if (b) {
            final Location location = Main.getNpc().getLocation(npcType);
            final ArmorStand armorStand = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 2.8, 0.0), (Class)ArmorStand.class);
            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&aNPC " + npcType.getName()));
            final ArmorStand armorStand2 = (ArmorStand)location.getWorld().spawn(location.clone().add(0.0, 2.6, 0.0), (Class)ArmorStand.class);
            armorStand2.setMarker(true);
            armorStand2.setVisible(false);
            armorStand2.setGravity(false);
            armorStand2.setCustomNameVisible(true);
            armorStand2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&aset"));
            player.sendMessage(" ");
            player.sendMessage(" ");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&a&lThePit&f&l] &7Pit &f" + npcType.getName() + " NPC &7was &aset &7at your current location."));
            API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, "", 5, 10, 5);
            API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, "&7Pit &f" + npcType.getName() + " NPC &7was &aset &7at your current location.", 10, 20, 10);
        }
    }
    
    private void availableNPCs(final Player player) {
        player.sendMessage("");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "        &a&lCOMMAND USAGE:"));
        TextComponentUtil.sendTextComponent(player, "&a\u2981 &f/ThePit SetNPC <&aNPC&f>", "/thepit setnpc ");
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Available NPCs:"));
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getNPCStatus(NPC.NPCType.Stats), "/thepit setnpc stats");
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getNPCStatus(NPC.NPCType.NonPermanentItems), "/thepit setnpc nonpermanentitems");
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getNPCStatus(NPC.NPCType.PermanentUpgrades), "/thepit setnpc permanentupgrades");
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getNPCStatus(NPC.NPCType.TheKeeper), "/thepit setnpc thekeeper");
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getNPCStatus(NPC.NPCType.Prestige), "/thepit setnpc prestige");
        TextComponentUtil.sendTextComponent(player, Main.getSetupAssister().getNPCStatus(NPC.NPCType.QuestMaster), "/thepit setnpc questmaster");
        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
    }
    
    @Override
    public boolean canSee(final CommandSender commandSender) {
        return this.hasPermission(commandSender);
    }
}
