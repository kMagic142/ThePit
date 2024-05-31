package ro.kmagic.features.npcs;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import org.bukkit.entity.Villager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import ro.kmagic.files.map.Region;
import ro.kmagic.Main;
import ro.kmagic.libapi.API;
import org.bukkit.Location;
import org.bukkit.event.Listener;

public class NPCsInteract implements Listener {
    private final String notEnoughLevel;
    private final String notEnoughLevelMisc;
    private final String lobbyServer;
    private final String soonMessage;
    private final int slot1LevelRequired;
    private final Location pos1;
    private final Location pos2;
    
    public NPCsInteract() {
        API.registerEvent(this);
        this.notEnoughLevel = Main.getMessages().getString("Level.Not-Enough");
        this.slot1LevelRequired = Main.getPermanentUpgrades().getInt("Player.PerkSlot.1.Level.Required");
        this.notEnoughLevelMisc = Main.getPermanentUpgrades().getString("Misc.NotEnoughLevel");
        this.lobbyServer = Main.getSettings().getString("Plugin.LobbyServer");
        this.soonMessage = Main.getMessages().getString("Soon");
        this.pos1 = Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 1);
        this.pos2 = Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 2);
    }
    
    @EventHandler
    private void onNpcInteract(final PlayerInteractEntityEvent playerInteractEntityEvent) {
        final Entity rightClicked = playerInteractEntityEvent.getRightClicked();
        final Player player = playerInteractEntityEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) || !rightClicked.getType().equals(EntityType.VILLAGER)) {
            return;
        }
        playerInteractEntityEvent.setCancelled(true);
        for (final NPC.NPCType npcType : NPC.NPCType.values()) {
            if(Main.getNpc().get(npcType) == null) continue;
            final Villager villager = (Villager)Main.getNpc().get(npcType).getEntity();
            if (villager != null && villager.getLocation().equals(rightClicked.getLocation())) {
                switch (npcType) {
                    case Stats: {
                        if (!Main.getArena().displayFeature(player, 50)) {
                            player.sendMessage(this.notEnoughLevel);
                            Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                            break;
                        }
                        Main.getStatsGUI().open(player);
                        break;
                    }
                    case NonPermanentItems: {
                        Main.getNonPermanentItemsGUI().open(player);
                        break;
                    }
                    case PermanentUpgrades: {
                        if (Main.getArena().displayFeature(player, this.slot1LevelRequired)) {
                            Main.getPerkGui().open(player);
                            break;
                        }
                        player.sendMessage(this.notEnoughLevelMisc);
                        break;
                    }
                    case TheKeeper: {
                        Main.getArena().leaveArena(player);
                        break;
                    }
                    case Prestige: {
                        if (PlayerPrestige.get(player).getPrestige() != 0) {
                            Main.getPrestigeGui().openGui(player);
                            break;
                        }
                        if (player.getLevel() >= 120) {
                            Main.getPrestigeGui().openGui(player);
                            break;
                        }
                        player.sendMessage(this.notEnoughLevel);
                        Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                        break;
                    }
                    case QuestMaster: {
                        if (!Main.getArena().displayFeature(player, 30)) {
                            player.sendMessage(this.notEnoughLevel);
                            Sounds.VILLAGER_NO.getSound().play(player, 3.0f, 1.0f);
                            break;
                        }
                        API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, this.soonMessage, 10, 30, 10);
                        break;
                    }
                }
            }
        }
    }
    
    @EventHandler
    private void onNpcDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final Entity entity = entityDamageByEntityEvent.getEntity();
        if (!Main.getRegionManager().inRegion(entity.getLocation(), this.pos1, this.pos2) || !entity.getType().equals(EntityType.VILLAGER)) {
            return;
        }
        entityDamageByEntityEvent.setCancelled(true);
    }
}
