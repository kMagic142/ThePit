package ro.kmagic.managers;

import com.live.bemmamin.jumppads.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.events.player.PlayerAssistEvent;
import ro.kmagic.libapi.events.player.PlayerPickupItemEvent;
import ro.kmagic.libapi.item.ItemBuilder;
import ro.kmagic.libapi.versionsupport.VersionSupport;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import ro.kmagic.Main;
import ro.kmagic.api.events.player.PlayerArrowHitEvent;
import ro.kmagic.api.events.player.PlayerRankupEvent;
import ro.kmagic.api.events.player.PlayerShootEvent;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.chatoption.PlayerChatOption;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.files.map.Region;
import ro.kmagic.files.map.Spawn;

import java.util.*;

public class GlobalManager implements Listener {
    private final HashMap<UUID, ArrayList<UUID>> contributors;
    private final boolean isProtocolLibEnabled;
    private final boolean spawnTeleportOnBarrierBoolean;
    private final String rankUpTitle;
    private final String rankUpSubtitle;
    private final String rankUpMessage;
    private final String respawnNotAvailableMessage;
    private final String respawnNotAvailableAsBeastMessage;
    private final String respawnCooldownMessage;
    private final String respawnInCombatMessage;
    private final String spawnNotSetMessage;
    private final String killRewardMessage;
    private final String actionBarDeath;
    private final String deathTitleMessage;
    private final String deathSubtitleMessage;
    private final String deathByPlayerMessage;
    private final String deathByUnknownMessage;
    private final String assistMessage;
    private final String economyGoldPickupMessage;
    private final String blockInteractMessage;
    private final Location spawnPos1;
    private final Location spawnPos2;
    private final Location spawnLocation;
    private final int combatLogTimeNumber;
    private final int worldYVoid;
    private final int assistXpNumber;
    private final int economyGoldPickupNumber;
    private final ItemStack ironChestplateItem;
    private final ItemStack ironLeggingsItem;
    private final ItemStack ironBootsItem;
    private final ItemStack diamondChestplateItem;
    private final ItemStack diamondBootsItem;
    private final ItemStack diamondSwordItem;
    private final ItemStack arrowItem;
    private final Material diamondChestplateMaterial;
    private final Material diamondBootsMaterial;
    private final Material diamondSwordMaterial;
    private final Material diamondBlockMaterial;
    private final Material goldIngotMaterial;
    private final Material diamondLeggingsMaterial;
    private final Material chainmailChestplateMaterial;
    private final Material ironChestplateMaterial;
    private final Material chainmailBootsMaterial;
    private final Material ironBootsMaterial;
    private final Material ironLeggingsMaterial;
    private final Material chainmailLeggingsMaterial;
    private final Material barrierMaterial;
    private final Material goldenAppleMaterial;
    private final Material playerHeadMaterial;
    private final Material potatoMaterial;
    private final Material potionMaterial;
    private final Material mushroomStewMaterial;
    private final double assistGoldNumber;
    private final ArrayList<UUID> cooldown;
    public HashMap<UUID, List<UUID>> firstHitMap;
    public List<UUID> soupBuff;
    public HashMap<UUID, Integer> reconBuff;
    public HashMap<UUID, Integer> kungFuKnowledgeBuff;
    private final ArrayList<Material> restrictedInteract;
    
    public GlobalManager() {
        this.contributors = new HashMap<UUID, ArrayList<UUID>>();
        this.cooldown = new ArrayList<UUID>();
        this.firstHitMap = new HashMap<UUID, List<UUID>>();
        this.soupBuff = new ArrayList<UUID>();
        this.reconBuff = new HashMap<UUID, Integer>();
        this.kungFuKnowledgeBuff = new HashMap<UUID, Integer>();
        this.restrictedInteract = new ArrayList<Material>(Arrays.asList(Materials.ANVIL.getItem().getMaterial(), Materials.CHIPPED_ANVIL.getItem().getMaterial(), Materials.DAMAGED_ANVIL.getItem().getMaterial(), Materials.FURNACE.getItem().getMaterial(), Materials.CRAFTING_TABLE.getItem().getMaterial(), Materials.HOPPER.getItem().getMaterial(), Materials.DISPENSER.getItem().getMaterial(), Materials.DROPPER.getItem().getMaterial(), Materials.NOTE_BLOCK.getItem().getMaterial()));
        API.registerEvent(this);
        this.isProtocolLibEnabled = API.isPluginEnabled("ProtocolLib");
        this.rankUpTitle = Main.getMessages().getString("XP.RankupTitleLine1");
        this.rankUpSubtitle = Main.getMessages().getString("XP.RankupTitleLine2").replace("[", "").replace("]", "").replace("(", "").replace(")", "");
        this.rankUpMessage = Main.getMessages().getString("XP.Rankup-Message");
        this.spawnPos1 = Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 1);
        this.spawnPos2 = Main.getRegionManager().getPos(Region.RegionType.SPAWN_REGION, 2);
        this.respawnNotAvailableMessage = Main.getMessages().getString("Respawn.NotAvailable");
        this.respawnNotAvailableAsBeastMessage = Main.getMessages().getString("Respawn.Not-Available-As-Beast");
        this.respawnCooldownMessage = Main.getMessages().getString("Respawn.Cooldown");
        this.respawnInCombatMessage = Main.getMessages().getString("Respawn.InCombat");
        this.combatLogTimeNumber = Main.getSettings().getInt("CombatLog.Time");
        this.spawnNotSetMessage = Main.getMessages().getString("SpawnNotSet");
        this.ironChestplateItem = Materials.IRON_CHESTPLATE.getItem().setUnbreakable(true).build();
        this.ironLeggingsItem = Materials.IRON_LEGGINGS.getItem().setUnbreakable(true).build();
        this.ironBootsItem = Materials.IRON_BOOTS.getItem().setUnbreakable(true).build();
        this.diamondChestplateMaterial = Materials.DIAMOND_CHESTPLATE.getItem().getMaterial();
        this.diamondBootsMaterial = Materials.DIAMOND_BOOTS.getItem().getMaterial();
        this.diamondSwordMaterial = Materials.DIAMOND_SWORD.getItem().getMaterial();
        this.diamondChestplateItem = Materials.DIAMOND_CHESTPLATE.getItem().setUnbreakable(true).build();
        this.diamondBootsItem = Materials.DIAMOND_BOOTS.getItem().setUnbreakable(true).build();
        this.diamondSwordItem = Materials.DIAMOND_SWORD.getItem().setUnbreakable(true).build();
        this.spawnLocation = Main.getSpawn().getLocation(Spawn.SpawnType.Spawn.toString());
        this.worldYVoid = Main.getSettings().getInt("World.Void");
        this.diamondBlockMaterial = Materials.DIAMOND_BLOCK.getItem().getMaterial();
        this.killRewardMessage = Main.getMessages().getString("Economy.KillReward");
        this.actionBarDeath = Main.getMessages().getString("Death.ActionBar");
        this.deathTitleMessage = Main.getMessages().getString("Death.Title");
        this.deathSubtitleMessage = Main.getMessages().getString("Death.SubTitle");
        this.deathByPlayerMessage = Main.getMessages().getString("Death.ByPlayer");
        this.deathByUnknownMessage = Main.getMessages().getString("Death.ByUnknown");
        this.assistGoldNumber = Main.getSettings().getDouble("Assist.GoldIncrease");
        this.assistXpNumber = Main.getSettings().getInt("Assist.XPIncrease");
        this.assistMessage = Main.getMessages().getString("Economy.Assist");
        this.goldIngotMaterial = Materials.GOLD_INGOT.getItem().getMaterial();
        this.economyGoldPickupNumber = Main.getSettings().getInt("Economy.GoldPickup");
        this.economyGoldPickupMessage = Main.getMessages().getString("Economy.GoldPickup");
        this.diamondLeggingsMaterial = Materials.DIAMOND_LEGGINGS.getItem().getMaterial();
        this.chainmailChestplateMaterial = Materials.CHAINMAIL_CHESTPLATE.getItem().getMaterial();
        this.ironChestplateMaterial = Materials.IRON_CHESTPLATE.getItem().getMaterial();
        this.chainmailBootsMaterial = Materials.CHAINMAIL_BOOTS.getItem().getMaterial();
        this.ironBootsMaterial = Materials.IRON_BOOTS.getItem().getMaterial();
        this.ironLeggingsMaterial = Materials.IRON_LEGGINGS.getItem().getMaterial();
        this.chainmailLeggingsMaterial = Materials.CHAINMAIL_LEGGINGS.getItem().getMaterial();
        this.spawnTeleportOnBarrierBoolean = Main.getSettings().getBoolean("Spawn.Teleport-On-Barrier");
        this.barrierMaterial = Materials.BARRIER.getItem().getMaterial();
        this.goldenAppleMaterial = Materials.GOLDEN_APPLE.getItem().getMaterial();
        this.playerHeadMaterial = Materials.PLAYER_HEAD.getItem().getMaterial();
        this.potatoMaterial = Materials.POTATO.getItem().getMaterial();
        this.potionMaterial = Materials.POTION.getItem().getMaterial();
        this.mushroomStewMaterial = Materials.MUSHROOM_STEW.getItem().getMaterial();
        this.blockInteractMessage = Main.getMessages().getString("Plugin.Block-Interact");
        this.arrowItem = Materials.ARROW.getItem().setAmount(32).build();
    }
    
    @EventHandler
    private void onPlayerRankup(final PlayerLevelChangeEvent playerLevelChangeEvent) {
        final Player player = playerLevelChangeEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (player.getLevel() > 120) {
            player.setLevel(120);
            return;
        }
        if (player.getLevel() == 0) {
            API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, this.rankUpTitle, 15, 20, 15);
            API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, this.rankUpSubtitle.replace("%oldlvl%", Main.getXpTag().get(120, 0)).replace("%newlvl%", Main.getXpTag().get(player)), 15, 20, 15);
            return;
        }
        final int level = player.getLevel();
        if (level - 1 != 0) {
            API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.TITLE, this.rankUpTitle, 15, 20, 15);
            API.getVersionSupport().sendTitle(player, VersionSupport.TitleType.SUBTITLE, this.rankUpSubtitle.replace("%oldlvl%", Main.getXpTag().get(level - 1, PlayerPrestige.get(player).getPrestige())).replace("%newlvl%", Main.getXpTag().get(player.getLevel(), PlayerPrestige.get(player).getPrestige())), 15, 20, 15);
            player.sendMessage(this.rankUpMessage.replace("%oldlvl%", Main.getXpTag().get(player.getLevel() - 1, PlayerPrestige.get(player).getPrestige())).replace("%newlvl%", Main.getXpTag().get(player)));
            Sounds.PLAYER_LEVELUP.getSound().play(player, 3.0f, 3.0f);
        }
        Bukkit.getPluginManager().callEvent(new PlayerRankupEvent(player, level - 1, level));
        new BukkitRunnable() {
            public void run() {
                API.getDatabase().setInt(player.getUniqueId(), level, "Level", PlayerEconomy.getTableName());
                Main.getArena().displayUnlockedFeatures(player);
            }
        }.runTask(API.getPlugin());
    }
    
    @EventHandler
    private void onPlayerCommand(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final Player player = playerCommandPreprocessEvent.getPlayer();
        final String lowerCase = playerCommandPreprocessEvent.getMessage().toLowerCase();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (!lowerCase.contains("spawn")) {
            return;
        }
        if (Main.getRegionManager().inRegion(player.getLocation(), this.spawnPos1, this.spawnPos2)) {
            player.sendMessage(this.respawnNotAvailableMessage);
            return;
        }
        if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.THE_BEAST) && Main.getPitEventManager().isBeast(player)) {
            player.sendMessage(this.respawnNotAvailableAsBeastMessage);
            return;
        }
        if (this.cooldown.contains(player.getUniqueId())) {
            player.sendMessage(this.respawnCooldownMessage);
            return;
        }
        if (Main.getCombatManager().inCombat(player)) {
            player.sendMessage(this.respawnInCombatMessage.replace("%seconds%", String.valueOf(this.combatLogTimeNumber - Main.getCombatManager().getSeconds(player))));
            return;
        }
        if (!Main.getSpawn().isSet(Spawn.SpawnType.Spawn)) {
            player.sendMessage(this.spawnNotSetMessage);
            return;
        }
        this.cooldown.add(player.getUniqueId());
        Main.getArena().giveKit(player, false, 15);
        Main.getArena().giveArrows(player);
        Main.getArena().spawn(player);
        Main.getPerk().giveItems(player);
        Sounds.WITHER_SHOOT.getSound().play(player, 1.0f, 1.0f);
        player.getInventory().remove(Materials.GOLDEN_APPLE.getItem().getMaterial());
        player.getInventory().remove(Materials.PLAYER_HEAD.getItem().getMaterial());
        player.getInventory().remove(Materials.POTATO.getItem().getMaterial());
        player.getInventory().remove(Materials.POTION.getItem().getMaterial());
        player.getInventory().remove(Materials.MUSHROOM_STEW.getItem().getMaterial());
        new BukkitRunnable() {
            public void run() {
                GlobalManager.this.cooldown.remove(player.getUniqueId());
            }
        }.runTaskLater(API.getPlugin(), 200L);
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerDeath(final PlayerDeathEvent playerDeathEvent) {
        final Player player = playerDeathEvent.getEntity().getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        playerDeathEvent.setKeepLevel(true);
        playerDeathEvent.setDeathMessage("");
        player.setHealth(player.getMaxHealth());
        playerDeathEvent.setDroppedExp(0);
        if (playerDeathEvent.getDrops().contains(this.ironChestplateItem)) {
            final ItemStack ironChestplateItem = this.ironChestplateItem;
            API.getVersionSupport().addMetaData(ironChestplateItem, "upgradeable", "true");
            player.getWorld().dropItemNaturally(player.getLocation(), ironChestplateItem);
        }
        else if (playerDeathEvent.getDrops().contains(this.ironLeggingsItem)) {
            final ItemStack ironLeggingsItem = this.ironLeggingsItem;
            API.getVersionSupport().addMetaData(ironLeggingsItem, "upgradeable", "true");
            player.getWorld().dropItemNaturally(player.getLocation(), ironLeggingsItem);
        }
        else if (playerDeathEvent.getDrops().contains(this.ironBootsItem)) {
            final ItemStack ironBootsItem = this.ironBootsItem;
            API.getVersionSupport().addMetaData(ironBootsItem, "upgradeable", "true");
            player.getWorld().dropItemNaturally(player.getLocation(), ironBootsItem);
        }
        boolean b = false;
        for (final ItemStack itemStack : playerDeathEvent.getDrops()) {
            if (itemStack.getType().equals(this.diamondChestplateMaterial) || itemStack.getType().equals(this.diamondBootsMaterial) || itemStack.getType().equals(this.diamondSwordMaterial)) {
                b = true;
                break;
            }
        }
        final int randomInt = Main.getArena().getRandomInt(6);
        if (b) {
            if (randomInt == 1 && playerDeathEvent.getDrops().contains(this.diamondChestplateItem)) {
                player.getWorld().dropItemNaturally(player.getLocation(), this.diamondChestplateItem);
            }
            if (randomInt == 3 && playerDeathEvent.getDrops().contains(this.diamondBootsItem)) {
                player.getWorld().dropItemNaturally(player.getLocation(), this.diamondBootsItem);
            }
            if (randomInt == 5 && playerDeathEvent.getDrops().contains(this.diamondSwordItem)) {
                player.getWorld().dropItemNaturally(player.getLocation(), this.diamondSwordItem);
            }
        }
        playerDeathEvent.getDrops().clear();
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        for (PotionEffect potionEffect : new ArrayList<PotionEffect>(player.getActivePotionEffects())) {
            player.removePotionEffect(potionEffect.getType());
        }
        player.setFireTicks(0);
        if (this.isProtocolLibEnabled) {
            new BukkitRunnable() {
                public void run() {
                    API.getVersionSupport().patchProtocolLibRespawn(player);
                    player.teleport(GlobalManager.this.spawnLocation);
                    player.setVelocity(new Vector(0, 0, 0).multiply(0));
                    player.setHealth(player.getMaxHealth());
                }
            }.runTaskLater(API.getPlugin(), 1L);
        }
        else {
            player.teleport(this.spawnLocation);
            player.setVelocity(new Vector(0, 0, 0).multiply(0));
        }
        new BukkitRunnable() {
            public void run() {
                player.setLevel(API.getDatabase().getInt(player.getUniqueId(), "Level", PlayerEconomy.getTableName()));
            }
        }.runTaskLater(API.getPlugin(), 10L);
        Bukkit.getPluginManager().callEvent(new ro.kmagic.libapi.events.player.PlayerDeathEvent(player.getKiller(), player));
    }

    @EventHandler
    private void onPlayerLaunch(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player);

        if(playerData != null && playerData.isLaunched()) {
            PlayerStats.get(player).add(Stats.StatsType.LAUNCHES, 1);
        }
    }

    @EventHandler
    private void onPlayerMoveVoid(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        if (playerMoveEvent.getTo().getY() > this.worldYVoid) {
            return;
        }
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        for (PotionEffect potionEffect : new ArrayList<PotionEffect>(player.getActivePotionEffects())) {
            player.removePotionEffect(potionEffect.getType());
        }
        player.setHealth(player.getMaxHealth());
        player.teleport(this.spawnLocation);
        player.setFireTicks(0);
        player.setVelocity(new Vector(0, 0, 0).multiply(0));
        new BukkitRunnable() {
            public void run() {
                player.setLevel(API.getDatabase().getInt(player.getUniqueId(), "Level", PlayerEconomy.getTableName()));
            }
        }.runTaskLater(API.getPlugin(), 10L);
        Bukkit.getPluginManager().callEvent(new ro.kmagic.libapi.events.player.PlayerDeathEvent(player.getKiller(), player));
    }
    
    @EventHandler(priority = EventPriority.LOW)
    private void onPlayerDeathMain(final ro.kmagic.libapi.events.player.PlayerDeathEvent playerDeathEvent) {
        final Player killed = playerDeathEvent.getKilled();
        if (Main.getPitEventManager().isBeast(killed)) {
            Main.getPitEventManager().selectRandomBeast();
        }
        if (Main.getCombatManager().inCombat(killed) && Main.getCombatManager().getPlayerDisconnected().contains(killed.getUniqueId())) {
            final Player killer = playerDeathEvent.getKiller();
            if (killer == null || killer.equals(killed)) {
                return;
            }
            int n = 1;
            int n2 = 1;
            int n3 = 1;
            int n4 = 1;
            int n5 = 0;
            if (PlayerPerk.get(killer).isActivated(Perk.PerkType.BOUNTY_HUNTER)) {
                n5 = 2;
            }
            if (PlayerPerk.get(killer).isActivated(Perk.PerkType.SPAMMER) && Main.getPerkEvents().isInPlayerShot(killed) && Main.getPerkEvents().getPlayerShot(killed).contains(killer.getUniqueId())) {
                n = 2;
            }
            if (PlayerPerk.get(killer).isActivated(Perk.PerkType.STREAKER)) {
                n2 = 3;
            }
            final PitEventManager pitEventManager = Main.getPitEventManager();
            if (pitEventManager.isStarted()) {
                if (pitEventManager.getEventType().equals(PitEventManager.PitEventType.X2_REWARD) && Main.getRegionManager().inRegion(killer.getLocation(), Main.getEventsRegion().getPos(pitEventManager.getLocationName(), 1), Main.getEventsRegion().getPos(pitEventManager.getLocationName(), 2))) {
                    n3 = 2;
                    n4 = 2;
                }
                else if (pitEventManager.getEventType().equals(PitEventManager.PitEventType.KOTH) && Main.getRegionManager().inRegion(killer.getLocation(), Main.getEventsRegion().getPos(pitEventManager.getLocationName(), 1), Main.getEventsRegion().getPos(pitEventManager.getLocationName(), 2)) && killed.getLocation().clone().add(0.0, -1.0, 0.0).getBlock().getType().equals(this.diamondBlockMaterial)) {
                    n3 = 4;
                    n4 = 4;
                }
                else if (pitEventManager.getEventType().equals(PitEventManager.PitEventType.RAGE_PIT) || pitEventManager.getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH)) {
                    n3 = 2;
                    n4 = 2;
                }
            }
            final int i = 14 * n * n4 * Main.getThePitAPI().getGoldAmplifier() + n5;
            final int j = 9 * n2 * n3 * Main.getThePitAPI().getXPAmplifier();
            if (!PlayerPerk.get(killer).isActivated(Perk.PerkType.CONGLOMERATE)) {
                if (killer.getLevel() < 120) {
                    killer.giveExp(j);
                }
            }
            else {
                PlayerEconomy.get(killer).setBalance(PlayerEconomy.EconomyAction.ADD, Main.getGlobalManager().getPercentage(20.0, j));
            }
            if (PlayerChatOption.get(killer).isEnabled(ChatOption.ChatOptionType.KILL_FEED)) {
                killer.sendMessage(this.killRewardMessage.replace("%killed%", Main.getXpTag().get(killed) + " " + killed.getName()).replace("%xp%", String.valueOf(j)).replace("%coins%", String.valueOf(i)));
            }
            API.getVersionSupport().sendActionBar(killer, this.actionBarDeath.replace("%victim%", killed.getName()));
            PlayerEconomy.get(killer).setBalance(PlayerEconomy.EconomyAction.ADD, i);
        }
        else {
            if (!Main.getArena().getPlayers().contains(killed.getUniqueId())) {
                return;
            }
            API.getVersionSupport().sendTitle(killed, VersionSupport.TitleType.TITLE, this.deathTitleMessage, 10, 20, 10);
            API.getVersionSupport().sendTitle(killed, VersionSupport.TitleType.SUBTITLE, this.deathSubtitleMessage, 10, 20, 10);
            final Player killer2 = playerDeathEvent.getKiller();
            if (killer2 != null && !killer2.equals(killed)) {
                if (PlayerChatOption.get(killed).isEnabled(ChatOption.ChatOptionType.KILL_FEED)) {
                    killed.sendMessage(this.deathByPlayerMessage.replace("%killer%", Main.getXpTag().get(killer2) + " " + killer2.getName()));
                }
                Sounds.ARROW_HIT_PLAYER.getSound().play(killer2, 1.0f, 3.0f);
            }
            else if (PlayerChatOption.get(killed).isEnabled(ChatOption.ChatOptionType.KILL_FEED)) {
                killed.sendMessage(this.deathByUnknownMessage);
            }
            final PitEventManager pitEventManager2 = Main.getPitEventManager();
            new BukkitRunnable() {
                public void run() {
                    Main.getArena().giveKit(killed, true, 15);
                }
            }.runTaskLaterAsynchronously(API.getPlugin(), 4L);
            new BukkitRunnable() {
                public void run() {
                    killed.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
                }
            }.runTaskLater(API.getPlugin(), 4L);
            new BukkitRunnable() {
                public void run() {
                    if (pitEventManager2.isStarted() && pitEventManager2.getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH)) {
                        Main.getPitEventManager().giveHead(killed);
                    }
                }
            }.runTaskLater(API.getPlugin(), 20L);
            if (killer2 == null || killer2.equals(killed)) {
                return;
            }
            int n6 = 1;
            int n7 = 1;
            int n8 = 1;
            int n9 = 1;
            int n10 = 0;
            if (PlayerPerk.get(killer2).isActivated(Perk.PerkType.BOUNTY_HUNTER)) {
                n10 = 2;
            }
            if (PlayerPerk.get(killer2).isActivated(Perk.PerkType.SPAMMER) && Main.getPerkEvents().isInPlayerShot(killed) && Main.getPerkEvents().getPlayerShot(killed).contains(killer2.getUniqueId())) {
                n6 = 2;
            }
            if (PlayerPerk.get(killer2).isActivated(Perk.PerkType.STREAKER)) {
                n7 = 3;
            }
            if (this.contributors.containsKey(killed.getUniqueId())) {
                for (UUID uuid : this.contributors.get(killed.getUniqueId())) {
                    final Player player = Bukkit.getPlayer(uuid);
                    if (player != null && PlayerPerk.isCached(player) && PlayerPerk.get(player).isActivated(Perk.PerkType.BOUNTY_HUNTER) && player != killer2) {
                        n10 = 2;
                    }
                }
            }
            if (pitEventManager2.isStarted()) {
                if (pitEventManager2.getEventType().equals(PitEventManager.PitEventType.X2_REWARD) && Main.getRegionManager().inRegion(killer2.getLocation(), Main.getEventsRegion().getPos(pitEventManager2.getLocationName(), 1), Main.getEventsRegion().getPos(pitEventManager2.getLocationName(), 2))) {
                    n8 = 2;
                    n9 = 2;
                }
                else if (pitEventManager2.getEventType().equals(PitEventManager.PitEventType.KOTH) && Main.getRegionManager().inRegion(killer2.getLocation(), Main.getEventsRegion().getPos(pitEventManager2.getLocationName(), 1), Main.getEventsRegion().getPos(pitEventManager2.getLocationName(), 2)) && killed.getLocation().add(0.0, -1.0, 0.0).getBlock().getType().equals(this.diamondBlockMaterial)) {
                    n8 = 4;
                    n9 = 4;
                }
                else if (pitEventManager2.getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH)) {
                    n8 = 2;
                    n9 = 2;
                }
                else if (pitEventManager2.getEventType().equals(PitEventManager.PitEventType.RAGE_PIT)) {
                    n8 = 2;
                    n9 = 2;
                    pitEventManager2.addRagePitKill();
                }
            }
            final int k = 14 * n6 * n9 * Main.getThePitAPI().getGoldAmplifier() + n10;
            final int l = 9 * n7 * n8 * Main.getThePitAPI().getXPAmplifier();
            if (!PlayerPerk.get(killer2).isActivated(Perk.PerkType.CONGLOMERATE)) {
                if (killer2.getLevel() < 120) {
                    killer2.giveExp(l);
                }
            }
            else {
                PlayerEconomy.get(killer2).setBalance(PlayerEconomy.EconomyAction.ADD, Main.getGlobalManager().getPercentage(20.0, l));
            }
            if (PlayerChatOption.get(killer2).isEnabled(ChatOption.ChatOptionType.KILL_FEED)) {
                killer2.sendMessage(this.killRewardMessage.replace("%killed%", Main.getXpTag().get(killed) + " " + killed.getName()).replace("%xp%", String.valueOf(l)).replace("%coins%", String.valueOf(k)));
            }
            API.getVersionSupport().sendActionBar(killer2, this.actionBarDeath.replace("%victim%", killed.getName()));
            PlayerEconomy.get(killer2).setBalance(PlayerEconomy.EconomyAction.ADD, k);
            if (PlayerPerk.get(killer2).isActivated(Perk.PerkType.RAMBO)) {
                killer2.setHealth(killer2.getMaxHealth());
            }
        }
    }
    
    @EventHandler
    private void on(final EntityDamageEvent entityDamageEvent) {
        if (!(entityDamageEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)entityDamageEvent.getEntity();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (entityDamageEvent.getCause() != null && (entityDamageEvent.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK) || entityDamageEvent.getCause().equals(EntityDamageEvent.DamageCause.LAVA))) {
            player.setFireTicks(20);
        }
    }
    
    @EventHandler
    private void onPlayerAssist(final PlayerAssistEvent playerAssistEvent) {
        for (final Player player : playerAssistEvent.getDamagers()) {
            int n = 0;
            if (PlayerPerk.get(player).isActivated(Perk.PerkType.SPAMMER) && Main.getPerkEvents().isInPlayerShot(playerAssistEvent.getKiller())) {
                n = 2;
            }
            final double d = this.assistGoldNumber + n;
            if (!PlayerPerk.get(player).isActivated(Perk.PerkType.CO_OP_CAT)) {
                PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, d);
                player.giveExp(this.assistXpNumber);
            }
            else {
                PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, this.getPercentage(50.0, d));
                player.giveExp((int)this.getPercentage(50.0, this.assistXpNumber));
            }
            if (PlayerChatOption.get(player).isEnabled(ChatOption.ChatOptionType.KILL_FEED)) {
                player.sendMessage(this.assistMessage.replace("%victim%", Main.getXpTag().get(playerAssistEvent.getVictim()) + " " + playerAssistEvent.getVictim().getName()).replace("%xp%", String.valueOf(this.assistXpNumber)).replace("%gold%", String.valueOf(d)));
            }
            Sounds.ARROW_HIT_PLAYER.getSound().play(player, 1.0f, 2.0f);
        }
    }
    
    @EventHandler
    private void onGoldStack(final ItemMergeEvent itemMergeEvent) {
        if (!itemMergeEvent.getEntity().getItemStack().getType().equals(this.goldIngotMaterial)) {
            return;
        }
        itemMergeEvent.setCancelled(true);
        itemMergeEvent.getEntity().remove();
    }
    
    @EventHandler
    private void onInventoryClickPitEvent(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        ItemBuilder itemBuilder;
        if (inventoryClickEvent.getClick().equals(ClickType.NUMBER_KEY)) {
            final ItemStack item = player.getInventory().getItem(inventoryClickEvent.getSlot());
            final ItemStack item2 = player.getInventory().getItem(inventoryClickEvent.getHotbarButton());
            if (item != null) {
                itemBuilder = new ItemBuilder(item);
            }
            else {
                if (item2 == null) {
                    return;
                }
                itemBuilder = new ItemBuilder(item2);
            }
        }
        else {
            if (inventoryClickEvent.getCurrentItem() == null) {
                return;
            }
            itemBuilder = new ItemBuilder(inventoryClickEvent.getCurrentItem());
        }
        if (!itemBuilder.getMaterial().name().contains("DIAMOND")) {
            return;
        }
        if (itemBuilder.metadata().has() && itemBuilder.metadata().get().equals("TheBeast")) {
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onInventoryClickMarathon(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON) && inventoryClickEvent.getSlot() == 36) {
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onInventoryClick(final InventoryClickEvent inventoryClickEvent) {
        if (Main.getArena().getPlayers().contains(inventoryClickEvent.getWhoClicked().getUniqueId()) && inventoryClickEvent.getCurrentItem() != null && inventoryClickEvent.getCurrentItem().getType().name().contains("WOOL")) {
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.LOW)
    private void onPvP(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.isCancelled() || !(entityDamageByEntityEvent.getEntity() instanceof Player) || !Main.getArena().getPlayers().contains(entityDamageByEntityEvent.getEntity().getUniqueId())) {
            return;
        }
        final Player obj = (Player)entityDamageByEntityEvent.getEntity();
        Player obj2 = null;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        if (entityDamageByEntityEvent.getCause() != EntityDamageEvent.DamageCause.PROJECTILE && entityDamageByEntityEvent.getDamager() instanceof Player && !obj.equals(entityDamageByEntityEvent.getDamager())) {
            obj2 = (Player)entityDamageByEntityEvent.getDamager();
            b = true;
        }
        else if (entityDamageByEntityEvent.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            obj2 = (Player)((Projectile)entityDamageByEntityEvent.getDamager()).getShooter();
            if (entityDamageByEntityEvent.getDamager().getType().equals(EntityType.ARROW)) {
                b3 = true;
            }
            if (entityDamageByEntityEvent.getDamager().getType().equals(EntityType.FISHING_HOOK)) {
                b2 = true;
            }
        }
        if (obj2 == null) {
            return;
        }
        if (obj2.equals(obj)) {
            entityDamageByEntityEvent.setCancelled(true);
            return;
        }
        if (Main.getRegionManager().inRegion(obj.getLocation(), this.spawnPos1, this.spawnPos2) && Main.getRegionManager().inRegion(obj2.getLocation(), this.spawnPos1, this.spawnPos2)) {
            return;
        }
        if (b && PlayerPerk.get(obj).isActivated(Perk.PerkType.GLADIATOR)) {
            final int gladiatorPercentage = Main.getPerkEvents().getGladiatorPercentage(obj);
            if (gladiatorPercentage != 0) {
                entityDamageByEntityEvent.setDamage(entityDamageByEntityEvent.getDamage() - entityDamageByEntityEvent.getDamage() / gladiatorPercentage);
            }
        }
        if (Main.getBounty().hasBounty(obj) && PlayerPerk.get(obj2).isActivated(Perk.PerkType.BOUNTY_HUNTER)) {
            if (!this.contributors.containsKey(obj.getUniqueId())) {
                this.contributors.put(obj.getUniqueId(), new ArrayList<UUID>());
            }
            if (!this.contributors.get(obj.getUniqueId()).contains(obj2.getUniqueId())) {
                this.contributors.get(obj.getUniqueId()).add(obj2.getUniqueId());
            }
        }
        if (PlayerPerk.get(obj2).isActivated(Perk.PerkType.KUNG_FU_KNOWLEDGE)) {
            final ItemStack itemInHand = obj2.getItemInHand();
            if (obj2.getItemInHand() != null && (itemInHand.getType().name().contains("SWORD") || itemInHand.getType().name().contains("AXE"))) {
                entityDamageByEntityEvent.setDamage(0.0);
            }
            if (obj2.getItemInHand() != null && obj2.getItemInHand().getType().equals(Material.AIR)) {
                entityDamageByEntityEvent.setDamage(7.4);
                this.kungFuKnowledgeBuff.put(obj2.getUniqueId(), this.kungFuKnowledgeBuff.get(obj2.getUniqueId()) + 1);
                if (this.kungFuKnowledgeBuff.get(obj2.getUniqueId()) == 4) {
                    obj2.removePotionEffect(PotionEffectType.SPEED);
                    obj2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
                    this.kungFuKnowledgeBuff.put(obj2.getUniqueId(), 0);
                }
            }
        }
        if (PlayerPerk.get(obj2).isActivated(Perk.PerkType.FIRST_STRIKE) && !this.firstHitMap.get(obj2.getUniqueId()).contains(obj.getUniqueId())) {
            this.firstHitMap.get(obj2.getUniqueId()).add(obj.getUniqueId());
            obj2.removePotionEffect(PotionEffectType.SPEED);
            obj2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
            final double damage = entityDamageByEntityEvent.getDamage();
            entityDamageByEntityEvent.setDamage(damage + this.getPercentage(40.0, damage));
        }
        if (obj2.hasPotionEffect(PotionEffectType.SPEED) && PlayerPerk.get(obj2).isActivated(Perk.PerkType.MARATHON)) {
            final double damage2 = entityDamageByEntityEvent.getDamage();
            entityDamageByEntityEvent.setDamage(damage2 + this.getPercentage(15.0, damage2));
        }
        if (obj.hasPotionEffect(PotionEffectType.SPEED) && PlayerPerk.get(obj).isActivated(Perk.PerkType.MARATHON)) {
            final double damage3 = entityDamageByEntityEvent.getDamage();
            entityDamageByEntityEvent.setDamage(damage3 - this.getPercentage(15.0, damage3));
        }
        if (this.soupBuff.contains(obj2.getUniqueId())) {
            final double damage4 = entityDamageByEntityEvent.getDamage();
            entityDamageByEntityEvent.setDamage(damage4 + this.getPercentage(15.0, damage4));
            this.soupBuff.remove(obj2.getUniqueId());
        }
        if (b3 && this.reconBuff.containsKey(obj2.getUniqueId())) {
            this.reconBuff.put(obj2.getUniqueId(), this.reconBuff.get(obj2.getUniqueId()) + 1);
            if (this.reconBuff.get(obj2.getUniqueId()) == 4) {
                obj2.giveExp(40);
                final double damage5 = entityDamageByEntityEvent.getDamage();
                entityDamageByEntityEvent.setDamage(damage5 + this.getPercentage(50.0, damage5));
                this.reconBuff.put(obj2.getUniqueId(), 0);
            }
        }
        if (PlayerPerk.get(obj2).isActivated(Perk.PerkType.VAMPIRE) && obj.getLastDamageCause() != null && entityDamageByEntityEvent.getDamage() != 0.0) {
            if (!obj.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                this.executeVampire(obj2, 1);
            }
            else if (!b2) {
                this.executeVampire(obj2, 3);
            }
        }
        if (!obj.equals(obj2)) {
            this.actionBarHealth(obj, obj2, (int)(obj.getHealth() - entityDamageByEntityEvent.getDamage()));
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    private void onChatMessage(final AsyncPlayerChatEvent asyncPlayerChatEvent) {
        for (final UUID uuid : Main.getArena().getPlayers()) {
            if (PlayerChatOption.get(Bukkit.getPlayer(uuid)) != null && !PlayerChatOption.get(Bukkit.getPlayer(uuid)).isEnabled(ChatOption.ChatOptionType.KILL_FEED)) {
                asyncPlayerChatEvent.getRecipients().remove(Bukkit.getPlayer(uuid));
            }
        }
    }
    
    @EventHandler
    private void onPlayerPickupItem(final PlayerPickupItemEvent playerPickupItemEvent) {
        final Player player = playerPickupItemEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        final Item item = playerPickupItemEvent.getItem();
        final PlayerInventory inventory = player.getInventory();
        boolean b = false;
        final String name = item.getItemStack().getType().name();
        switch (name) {
            case "IRON_CHESTPLATE": {
                if (inventory.getChestplate() == null) {
                    b = true;
                    break;
                }
                break;
            }
            case "IRON_LEGGINGS": {
                if (inventory.getLeggings() == null) {
                    b = true;
                    break;
                }
                break;
            }
            case "IRON_BOOTS": {
                if (inventory.getBoots() == null) {
                    b = true;
                    break;
                }
                break;
            }
        }
        if (!b && inventory.contains(item.getItemStack().getType())) {
            playerPickupItemEvent.setCancelled(true);
            return;
        }
        if (item.getItemStack().isSimilar(Materials.GOLD_INGOT.getItem().build())) {
            int i = PlayerPerk.get(player).isActivated(Perk.PerkType.TRICKLE_DOWN) ? 10 : this.economyGoldPickupNumber;
            if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.X2_REWARD) && Main.getRegionManager().inRegion(player.getLocation(), Main.getEventsRegion().getPos(Main.getPitEventManager().getLocationName(), 1), Main.getEventsRegion().getPos(Main.getPitEventManager().getLocationName(), 2))) {
                i *= 2;
            }
            int n2 = 20;
            if (PlayerPerk.get(player).isActivated(Perk.PerkType.RAMBO)) {
                n2 = 10;
            }
            if (PlayerPerk.get(player).isActivated(Perk.PerkType.THICK)) {
                n2 += 4;
            }
            if (Main.getPitEventManager().isStarted()) {
                if (Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT)) {
                    n2 = 40;
                }
                if (Main.getPitEventManager().isBeast(player)) {
                    n2 = 40;
                }
            }
            if (PlayerPerk.get(player).isActivated(Perk.PerkType.TRICKLE_DOWN) && player.getHealth() <= n2 - 4) {
                player.setHealth((int)player.getHealth() + 4);
            }
            if (PlayerChatOption.get(player).isEnabled(ChatOption.ChatOptionType.MISCELLANEOUS)) {
                player.sendMessage(this.economyGoldPickupMessage.replace("%coins%", String.valueOf(i)));
            }
            PlayerEconomy.get(player).setBalance(PlayerEconomy.EconomyAction.ADD, i);
            playerPickupItemEvent.setCancelled(true);
            item.remove();
            Sounds.ORB_PICKUP.getSound().play(player, 1.0f, 3.0f);
        }
        else if (PlayerPerk.get(player).isActivated(Perk.PerkType.LUCKY_DIAMOND) && !Main.getRegionManager().inRegion(player.getLocation(), this.spawnPos1, this.spawnPos2) && API.getVersionSupport().hasMetaData(item.getItemStack(), "upgradeable") && API.getVersionSupport().getMetaData(item.getItemStack(), "upgradeable").equals("true")) {
            final int randomInt = Main.getArena().getRandomInt(100);
            if (randomInt >= 1 && randomInt <= 30) {
                boolean b2 = false;
                if (item.getItemStack().getType().equals(Main.getArena().item(Materials.IRON_CHESTPLATE).getType())) {
                    if (player.getInventory().getChestplate() != null && !player.getInventory().getChestplate().getType().equals(this.diamondBlockMaterial)) {
                        inventory.setChestplate(Main.getArena().item(Materials.DIAMOND_CHESTPLATE));
                        b2 = true;
                    }
                }
                else if (item.getItemStack().getType().equals(Main.getArena().item(Materials.IRON_LEGGINGS).getType())) {
                    if (player.getInventory().getLeggings() != null && !player.getInventory().getLeggings().getType().equals(this.diamondLeggingsMaterial)) {
                        inventory.setLeggings(Main.getArena().item(Materials.DIAMOND_LEGGINGS));
                        b2 = true;
                    }
                }
                else if (item.getItemStack().getType().equals(Main.getArena().item(Materials.IRON_BOOTS).getType()) && player.getInventory().getBoots() != null && !player.getInventory().getBoots().getType().equals(this.diamondBootsMaterial) && !PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                    inventory.setBoots(Main.getArena().item(Materials.DIAMOND_BOOTS));
                    b2 = true;
                }
                item.remove();
                playerPickupItemEvent.setCancelled(true);
                if (b2) {
                    Sounds.EQUIP_ARMOR.getSound().play(player, 1.0f, 1.0f);
                }
            }
            else {
                API.getVersionSupport().addMetaData(item.getItemStack(), "upgradeable", "false");
            }
        }
        boolean b3 = false;
        if (item.getItemStack().getType().equals(this.diamondChestplateMaterial)) {
            if (inventory.getChestplate() == null || inventory.getChestplate().getType().equals(this.chainmailChestplateMaterial)) {
                item.remove();
                inventory.setChestplate(item.getItemStack());
                playerPickupItemEvent.setCancelled(true);
                b3 = true;
            }
            else if (inventory.getChestplate().getType().equals(this.ironChestplateMaterial)) {
                item.remove();
                inventory.setChestplate(item.getItemStack());
                inventory.setItem(9, Main.getArena().item(Materials.IRON_CHESTPLATE));
                playerPickupItemEvent.setCancelled(true);
                b3 = true;
            }
        }
        if (item.getItemStack().getType().equals(this.diamondBootsMaterial)) {
            if (inventory.getBoots() == null || inventory.getBoots().getType().equals(this.chainmailBootsMaterial)) {
                item.remove();
                if (!PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                    inventory.setBoots(item.getItemStack());
                }
                else {
                    inventory.addItem(item.getItemStack());
                }
                playerPickupItemEvent.setCancelled(true);
                b3 = true;
            }
            else if (inventory.getBoots().getType().equals(this.ironBootsMaterial)) {
                item.remove();
                if (!PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                    inventory.setBoots(item.getItemStack());
                }
                else {
                    inventory.addItem(item.getItemStack());
                }
                inventory.setItem(10, Main.getArena().item(Materials.IRON_BOOTS));
                playerPickupItemEvent.setCancelled(true);
                b3 = true;
            }
        }
        if (item.getItemStack().getType().equals(this.ironChestplateMaterial) && (inventory.getChestplate() == null || inventory.getChestplate().getType().equals(this.chainmailChestplateMaterial))) {
            item.remove();
            inventory.setChestplate(item.getItemStack());
            playerPickupItemEvent.setCancelled(true);
            b3 = true;
        }
        if (item.getItemStack().getType().equals(this.ironLeggingsMaterial) && (inventory.getLeggings() == null || inventory.getLeggings().getType().equals(this.chainmailLeggingsMaterial))) {
            item.remove();
            inventory.setLeggings(item.getItemStack());
            playerPickupItemEvent.setCancelled(true);
            b3 = true;
        }
        if (item.getItemStack().getType().equals(this.ironBootsMaterial) && (inventory.getBoots() == null || inventory.getBoots().getType().equals(this.chainmailBootsMaterial))) {
            item.remove();
            if (!PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                inventory.setBoots(item.getItemStack());
            }
            else {
                inventory.addItem(item.getItemStack());
            }
            playerPickupItemEvent.setCancelled(true);
            b3 = true;
        }
        if (b3) {
            Sounds.EQUIP_ARMOR.getSound().play(player, 1.0f, 1.0f);
        }
    }
    
    @EventHandler
    private void onPlayerMove(final PlayerMoveEvent playerMoveEvent) {
        if (!this.spawnTeleportOnBarrierBoolean) {
            return;
        }
        final Player player = playerMoveEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) || !playerMoveEvent.getTo().clone().add(0.0, 0.5, 0.0).getBlock().getRelative(BlockFace.DOWN).getType().equals(this.barrierMaterial)) {
            return;
        }
        Main.getArena().spawn(player);
        player.getInventory().remove(this.goldenAppleMaterial);
        player.getInventory().remove(this.playerHeadMaterial);
        player.getInventory().remove(this.potatoMaterial);
        player.getInventory().remove(this.potionMaterial);
        player.getInventory().remove(this.mushroomStewMaterial);
        Sounds.WITHER_SHOOT.getSound().play(player, 1.0f, 1.0f);
    }
    
    @EventHandler
    private void onFallDamage(final EntityDamageEvent entityDamageEvent) {
        if (!(entityDamageEvent.getEntity() instanceof Player)) {
            return;
        }
        if (!Main.getArena().getPlayers().contains(entityDamageEvent.getEntity().getUniqueId()) || !entityDamageEvent.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            return;
        }
        entityDamageEvent.setCancelled(true);
    }
    
    @EventHandler
    private void onPlayerHunger(final FoodLevelChangeEvent foodLevelChangeEvent) {
        if (!(foodLevelChangeEvent.getEntity() instanceof Player)) {
            return;
        }
        if (!Main.getArena().getPlayers().contains(foodLevelChangeEvent.getEntity().getUniqueId())) {
            return;
        }
        foodLevelChangeEvent.setCancelled(true);
        foodLevelChangeEvent.setFoodLevel(20);
    }
    
    @EventHandler(priority = EventPriority.LOW)
    private void onPlayerBreak(final BlockBreakEvent blockBreakEvent) {
        if (blockBreakEvent.isCancelled()) {
            return;
        }
        final Player player = blockBreakEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) || Main.getArena().getBuilders().contains(player.getUniqueId())) {
            return;
        }
        final Material type = blockBreakEvent.getBlock().getType();
        if (type.equals(Material.COBBLESTONE) || type.equals(Material.OBSIDIAN)) {
            blockBreakEvent.getBlock().setType(Material.AIR);
        }
        else {
            blockBreakEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerPlace(final BlockPlaceEvent blockPlaceEvent) {
        final Player player = blockPlaceEvent.getPlayer();
        final Material type = blockPlaceEvent.getBlock().getType();
        boolean b = false;
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (Main.getArena().getBuilders().contains(player.getUniqueId())) {
            b = true;
        }
        if (!b && !type.equals(Material.COBBLESTONE) && !type.equals(Material.OBSIDIAN)) {
            blockPlaceEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerInteract(final PlayerInteractAtEntityEvent playerInteractAtEntityEvent) {
        if (playerInteractAtEntityEvent.getRightClicked().getType().equals(EntityType.ARMOR_STAND) && !Main.getArena().getBuilders().contains(playerInteractAtEntityEvent.getPlayer().getUniqueId())) {
            playerInteractAtEntityEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!(entityDamageByEntityEvent.getDamager() instanceof Player) || !(entityDamageByEntityEvent.getEntity() instanceof ArmorStand)) {
            return;
        }
        final Player player = (Player)entityDamageByEntityEvent.getDamager();
        if (Main.getArena().getPlayers().contains(player.getUniqueId()) && !Main.getArena().getBuilders().contains(player.getUniqueId())) {
            entityDamageByEntityEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerSpawn(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        if (Main.getArena().getPlayers().contains(player.getUniqueId()) && !Main.getArena().getBuilders().contains(player.getUniqueId()) && playerInteractEvent.getMaterial().equals(Material.ARMOR_STAND)) {
            playerInteractEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerInteract(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (!playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (playerInteractEvent.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK) && Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.KOTH)) {
            playerInteractEvent.setCancelled(true);
            return;
        }
        if (this.restrictedInteract.contains(playerInteractEvent.getClickedBlock().getType())) {
            playerInteractEvent.setCancelled(true);
            Sounds.ENDERMAN_TELEPORT.getSound().play(player, 3.0f, 0.0f);
            player.sendMessage(this.blockInteractMessage);
        }
    }
    
    @EventHandler
    private void onPlayerDrop(final PlayerDropItemEvent playerDropItemEvent) {
        if (!Main.getArena().getPlayers().contains(playerDropItemEvent.getPlayer().getUniqueId())) {
            return;
        }
        final Material type = playerDropItemEvent.getItemDrop().getItemStack().getType();
        if (!type.equals(Material.IRON_CHESTPLATE) && !type.equals(Material.IRON_LEGGINGS) && !type.equals(Material.IRON_BOOTS) && !type.equals(Material.DIAMOND_CHESTPLATE) && !type.equals(Material.DIAMOND_BOOTS) && !type.equals(Material.ARROW)) {
            playerDropItemEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onBLockFromTo(final BlockFromToEvent blockFromToEvent) {
        if (this.spawnLocation.getWorld().equals(blockFromToEvent.getBlock().getWorld())) {
            blockFromToEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerWorldChange(final PlayerChangedWorldEvent playerChangedWorldEvent) {
        final Player player = playerChangedWorldEvent.getPlayer();
        if (player.getWorld().equals(this.spawnLocation.getWorld())) {
            Main.getArena().joinArena(player);
            player.updateInventory();
        }
        else if (Main.getArena().getPlayers().contains(player.getUniqueId())) {
            Main.getArena().leaveArena(player);
        }
    }
    
    @EventHandler
    private void onWeatherChange(final WeatherChangeEvent weatherChangeEvent) {
        if (!weatherChangeEvent.getWorld().hasStorm() && weatherChangeEvent.getWorld().equals(this.spawnLocation.getWorld())) {
            weatherChangeEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerBucket(final PlayerBucketFillEvent playerBucketFillEvent) {
        if (Main.getArena().getPlayers().contains(playerBucketFillEvent.getPlayer().getUniqueId())) {
            playerBucketFillEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onArrowHit(final ProjectileHitEvent projectileHitEvent) {
        if (!(projectileHitEvent.getEntity().getShooter() instanceof Player) || !(projectileHitEvent.getEntity() instanceof Arrow)) {
            return;
        }
        final Player player = (Player)projectileHitEvent.getEntity().getShooter();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) && Main.getRegionManager().inRegion(player.getLocation(), this.spawnPos1, this.spawnPos2)) {
            return;
        }
        projectileHitEvent.getEntity().remove();
        Bukkit.getPluginManager().callEvent(new PlayerArrowHitEvent(player));
    }
    
    @EventHandler
    private void onPlayerShoot(final ProjectileLaunchEvent projectileLaunchEvent) {
        if (!(projectileLaunchEvent.getEntity().getShooter() instanceof Player) || !(projectileLaunchEvent.getEntity() instanceof Arrow)) {
            return;
        }
        final Player player = (Player)projectileLaunchEvent.getEntity().getShooter();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) || !Main.getRegionManager().inRegion(player.getLocation(), this.spawnPos1, this.spawnPos2)) {
            return;
        }
        player.getInventory().remove(Material.ARROW);
        player.getInventory().setItem(8, this.arrowItem);
        projectileLaunchEvent.getEntity().remove();
        Bukkit.getPluginManager().callEvent(new PlayerShootEvent(player));
    }
    
    @EventHandler
    private void onPlayerFish(final PlayerFishEvent playerFishEvent) {
        if (!Main.getArena().getPlayers().contains(playerFishEvent.getPlayer().getUniqueId()) && playerFishEvent.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            return;
        }
        playerFishEvent.setExpToDrop(0);
        if (playerFishEvent.getCaught() == null) {
            return;
        }
        if (!playerFishEvent.getCaught().getType().equals(EntityType.VILLAGER) && !playerFishEvent.getCaught().getType().equals(EntityType.ARMOR_STAND) && !playerFishEvent.getCaught().getType().equals(EntityType.PLAYER)) {
            playerFishEvent.getCaught().remove();
        }
    }
    
    private void actionBarHealth(final Player player, final Player player2, final int n) {
        String str = "&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
        if (Main.getPitEventManager().isStarted() && (Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT) || Main.getPitEventManager().isBeast(player))) {
            str = "&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
            switch (n) {
                case 40: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 39: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764";
                    break;
                }
                case 38: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764";
                    break;
                }
                case 37: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764";
                    break;
                }
                case 36: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764";
                    break;
                }
                case 35: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764";
                    break;
                }
                case 34: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764";
                    break;
                }
                case 33: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764";
                    break;
                }
                case 32: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764";
                    break;
                }
                case 31: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764";
                    break;
                }
                case 30: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 29: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 28: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 27: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 26: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 25: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 24: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 23: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 22: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&4\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 21: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 20: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 19: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 18: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 17: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 16: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 15: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 14: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 13: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 12: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 11: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 10: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 9: {
                    str = "&4\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 8: {
                    str = "&4\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 7: {
                    str = "&4\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 6: {
                    str = "&4\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 5: {
                    str = "&4\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 4: {
                    str = "&4\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 3: {
                    str = "&4\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 2: {
                    str = "&4\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 1: {
                    str = "&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&7|&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
            }
        }
        else {
            switch (n) {
                case 20: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 19: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764";
                    break;
                }
                case 18: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764";
                    break;
                }
                case 17: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764";
                    break;
                }
                case 16: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764";
                    break;
                }
                case 15: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764";
                    break;
                }
                case 14: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764";
                    break;
                }
                case 13: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764";
                    break;
                }
                case 12: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764";
                    break;
                }
                case 11: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764";
                    break;
                }
                case 10: {
                    str = "&4\u2764\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 9: {
                    str = "&4\u2764\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 8: {
                    str = "&4\u2764\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 7: {
                    str = "&4\u2764\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 6: {
                    str = "&4\u2764\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 5: {
                    str = "&4\u2764\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 4: {
                    str = "&4\u2764\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 3: {
                    str = "&4\u2764&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 2: {
                    str = "&4\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
                case 1: {
                    str = "&c\u2764&0\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764";
                    break;
                }
            }
        }
        String string = "";
        final int n2 = API.getVersionSupport().getAbsorptionHearts(player) / 2;
        if (n2 != 0) {
            for (int i = 1; i <= n2; ++i) {
                string += "&6\u2764";
            }
        }
        API.getVersionSupport().sendActionBar(player2, "&7" + player.getName() + " " + str + string);
    }
    
    private void executeVampire(final Player player, final int n) {
        int n2 = 20;
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.RAMBO)) {
            n2 = 10;
        }
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.THICK)) {
            n2 += 4;
        }
        if (Main.getPitEventManager().isStarted()) {
            if (Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT)) {
                n2 = 40;
            }
            if (Main.getPitEventManager().isBeast(player)) {
                n2 = 40;
            }
        }
        if (player.getHealth() <= n2 - n) {
            player.setHealth(player.getHealth() + n);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 160, 0, false, false));
    }
    
    public double getPercentage(final double n, final double n2) {
        return n / 100.0 * n2;
    }
}
