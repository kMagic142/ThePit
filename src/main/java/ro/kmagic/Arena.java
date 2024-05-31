package ro.kmagic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.database.Database;
import ro.kmagic.libapi.database.utils.ColumnInfo;
import ro.kmagic.libapi.placeholder.PlaceholderManager;
import ro.kmagic.libapi.utils.FileManager;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.api.events.arena.ArenaJoinEvent;
import ro.kmagic.api.events.arena.ArenaLeaveEvent;
import ro.kmagic.features.chatoption.ChatOption;
import ro.kmagic.features.chatoption.PlayerChatOption;
import ro.kmagic.features.economy.gold.PlayerEconomy;
import ro.kmagic.features.economy.renown.RenownEconomy;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.features.perk.Perk;
import ro.kmagic.features.perk.PlayerPerk;
import ro.kmagic.features.prestige.PlayerPrestige;
import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.features.sync.Sync;
import ro.kmagic.files.map.Spawn;
import ro.kmagic.support.legacy.SQLite;

import java.util.*;

public class Arena implements Listener {
    private final ArrayList<UUID> players;
    private final ArrayList<UUID> builders;
    private final Random random;
    private boolean joinLeaveMessagesEnabledBoolean;
    private boolean playerPingFixBoolean;
    private boolean isUnlockedFeaturesSetBoolean;
    private boolean bountyAuraBoolean;
    private String joinLeaveMessagesJoinMessage;
    private String respawnFeat;
    private String pitChatFeat;
    private String upgradesFeat;
    private String enderChestFeat;
    private String contractsFeat;
    private String statsFeat;
    private String tradeFeat;
    private String viewFeat;
    private String prestigeFeat;
    private String lockedFeat;
    private String xpLevelMaxMessage;
    private List<Material> removeMaterialList;
    private List<PotionEffectType> removePotionEffectList;
    private Location hologramFeatLocation;
    private Location lobbyLocation;
    private Location spawnLocation;
    private int defaultBalance;
    private ItemStack chainmailChestplate;
    private ItemStack chainmailLeggings;
    private ItemStack chainmailBoots;
    private ItemStack chainmailBootsMTag;
    private ItemStack ironSword;
    private ItemStack bow;
    private ItemStack arrow;
    private ItemStack ironChestplate;
    private ItemStack ironLeggings;
    private ItemStack ironBoots;
    private ItemStack ironBootsMTag;
    private String tag5;
    private String tag10;
    private String tag15;
    private String tag30;
    private String tag50;
    private String tag60;
    private String tag70;
    private String tag120;
    private boolean bbCached;
    private final ArrayList<String> unlockedFeaturesHologramCache;
    
    public Arena() {
        this.random = new Random();
        this.bbCached = false;
        this.unlockedFeaturesHologramCache = new ArrayList<String>(Main.getHologram().getUnlockedFeaturesHologram());
        this.players = new ArrayList<UUID>();
        this.builders = new ArrayList<UUID>();
        API.registerEvent(this);
        if (!Main.isSetupMode()) {
            this.joinLeaveMessagesEnabledBoolean = Main.getSettings().getBoolean("JoinLeaveMessages.Enabled");
            this.joinLeaveMessagesJoinMessage = Main.getSettings().getString("JoinLeaveMessages.Join");
            this.playerPingFixBoolean = Main.getSettings().getBoolean("Player.PingFix");
            this.removeMaterialList = new ArrayList<Material>(Arrays.asList(Materials.GOLDEN_APPLE.getItem().getMaterial(), Materials.PLAYER_HEAD.getItem().getMaterial(), Materials.POTATO.getItem().getMaterial(), Materials.POTION.getItem().getMaterial(), Materials.MUSHROOM_STEW.getItem().getMaterial()));
            this.removePotionEffectList = new ArrayList<PotionEffectType>(Arrays.asList(PotionEffectType.SPEED, PotionEffectType.ABSORPTION, PotionEffectType.REGENERATION, PotionEffectType.DAMAGE_RESISTANCE));
            this.isUnlockedFeaturesSetBoolean = Main.getHologram().isUnlockedFeaturesSet();
            this.respawnFeat = Main.getHologram().getString("Unlocked-Features.Respawn");
            this.pitChatFeat = Main.getHologram().getString("Unlocked-Features.Pit-Chat");
            this.upgradesFeat = Main.getHologram().getString("Unlocked-Features.Upgrades");
            this.enderChestFeat = Main.getHologram().getString("Unlocked-Features.Ender-Chest");
            this.contractsFeat = Main.getHologram().getString("Unlocked-Features.Contracts");
            this.statsFeat = Main.getHologram().getString("Unlocked-Features.Stats");
            this.tradeFeat = Main.getHologram().getString("Unlocked-Features.Trade");
            this.viewFeat = Main.getHologram().getString("Unlocked-Features.View");
            this.prestigeFeat = Main.getHologram().getString("Unlocked-Features.Prestige");
            this.lockedFeat = Main.getHologram().getString("Unlocked-Features.Locked");
            this.hologramFeatLocation = Main.getHologram().isUnlockedFeaturesSet() ? Main.getHologram().getUnlockedFeaturesLocation() : null;
            this.defaultBalance = Main.getSettings().getInt("Economy.DefaultBalance");
            this.bountyAuraBoolean = Main.getSettings().getBoolean("Bounty.Aura");
            this.lobbyLocation = Main.getSpawn().getLocation(Spawn.SpawnType.Lobby.name());
            this.spawnLocation = Main.getSpawn().getLocation(Spawn.SpawnType.Spawn.toString());
            this.chainmailChestplate = this.item(Materials.CHAINMAIL_CHESTPLATE);
            this.chainmailLeggings = this.item(Materials.CHAINMAIL_LEGGINGS);
            this.chainmailBoots = this.item(Materials.CHAINMAIL_BOOTS);
            this.chainmailBootsMTag = Materials.CHAINMAIL_BOOTS.getItem().metadata().add("MARATHON").setUnbreakable(true).build();
            this.ironSword = this.item(Materials.IRON_SWORD);
            this.bow = this.item(Materials.BOW);
            this.arrow = Materials.ARROW.getItem().setAmount(32).build();
            this.ironChestplate = this.item(Materials.IRON_CHESTPLATE);
            this.ironLeggings = this.item(Materials.IRON_LEGGINGS);
            this.ironBoots = this.item(Materials.IRON_BOOTS);
            this.ironBootsMTag = Materials.IRON_BOOTS.getItem().metadata().add("MARATHON").setUnbreakable(true).build();
            this.xpLevelMaxMessage = Main.getMessages().getString("XP.Level.Max");
            return;
        }
        World world = Bukkit.getWorld(Main.getSettings().getString("Plugin.WorldName"));

        if (world == null) {
            return;
        }

        for (final Entity entity : world.getEntities()) {
            if (!(entity instanceof Player) && !(entity instanceof Villager) && (!(entity instanceof ArmorStand) || entity.isCustomNameVisible())) {
                entity.remove();
            }
        }
        world.setWaterAnimalSpawnLimit(0);
        world.setAnimalSpawnLimit(0);
        world.setMonsterSpawnLimit(0);
    }
    
    @EventHandler
    private void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        final Player player = playerJoinEvent.getPlayer();

        if (Main.isSetupMode()) {
            this.joinSetup(player);
            return;
        }
        this.joinArena(player);
        if (!this.joinLeaveMessagesEnabledBoolean) {
            playerJoinEvent.setJoinMessage(null);
            return;
        }
        String joinMessage = this.joinLeaveMessagesJoinMessage.replace("%player%", player.getName());
        if (PlaceholderManager.isPapiEnabled()) {
            joinMessage = PlaceholderManager.setPlaceholders(player, joinMessage);
        }
        playerJoinEvent.setJoinMessage(joinMessage);
    }
    
    private void joinSetup(final Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
        player.setFoodLevel(20);
    }
    
    @EventHandler
    private void onPlayerDamage(final EntityDamageEvent entityDamageEvent) {
        if (!Main.isSetupMode()) {
            return;
        }
        if (entityDamageEvent.getEntityType().equals(EntityType.PLAYER) && Main.getWorldManager().isOnMap((Player)entityDamageEvent.getEntity())) {
            entityDamageEvent.setCancelled(true);
        }
        if (entityDamageEvent.getEntityType().equals(EntityType.VILLAGER)) {
            entityDamageEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerInteract(final PlayerInteractEvent playerInteractEvent) {
        if (Main.isSetupMode() && Main.getWorldManager().isOnMap(playerInteractEvent.getPlayer()) && playerInteractEvent.getClickedBlock() != null && playerInteractEvent.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
            playerInteractEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerInteractEntity(final PlayerInteractEntityEvent playerInteractEntityEvent) {
        if (Main.isSetupMode() && Main.getWorldManager().isOnMap(playerInteractEntityEvent.getPlayer()) && playerInteractEntityEvent.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            playerInteractEntityEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onPlayerHunger(final FoodLevelChangeEvent foodLevelChangeEvent) {
        if (!Main.isSetupMode()) {
            return;
        }
        if (!foodLevelChangeEvent.getEntityType().equals(EntityType.PLAYER)) {
            return;
        }
        if (Main.getWorldManager().isOnMap((Player)foodLevelChangeEvent.getEntity())) {
            foodLevelChangeEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onWeatherChange(final WeatherChangeEvent weatherChangeEvent) {
        if (Main.getWorldManager().isOnMap(weatherChangeEvent.getWorld()) && weatherChangeEvent.toWeatherState()) {
            weatherChangeEvent.setCancelled(true);
        }
    }
    
    public void joinArena(final Player player) {
        if (this.players.contains(player.getUniqueId())) {
            return;
        }
        this.players.add(player.getUniqueId());
        player.setFoodLevel(20);
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
        if (Database.getMySQLCredentials().isEnabled()) {
            if (!API.getDatabase().hasAccount(player.getUniqueId(), PlayerEconomy.getTableName())) {
                API.getDatabase().createPlayer(player.getUniqueId(), PlayerEconomy.getTableName(), Arrays.asList(new ColumnInfo("Player", player.getName()), new ColumnInfo("UUID", player.getUniqueId().toString()), new ColumnInfo("Balance", String.valueOf(this.defaultBalance))));
            }
            if (!API.getDatabase().hasAccount(player.getUniqueId(), Stats.getTableName())) {
                API.getDatabase().createPlayer(player.getUniqueId(), Stats.getTableName(), Arrays.asList(new ColumnInfo("Player", player.getName()), new ColumnInfo("UUID", player.getUniqueId().toString())));
            }
            if (!API.getDatabase().hasAccount(player.getUniqueId(), Perk.getTableName())) {
                API.getDatabase().createPlayer(player.getUniqueId(), Perk.getTableName(), Arrays.asList(new ColumnInfo("Player", player.getName()), new ColumnInfo("UUID", player.getUniqueId().toString())));
            }
            if (Main.isSyncEnabled() && !API.getDatabase().hasAccount(player.getUniqueId(), Sync.getTableName())) {
                API.getDatabase().createPlayer(player.getUniqueId(), Sync.getTableName(), Arrays.asList(new ColumnInfo("Player", player.getName()), new ColumnInfo("UUID", player.getUniqueId().toString())));
            }
            if (!API.getDatabase().hasAccount(player.getUniqueId(), ChatOption.getTableName())) {
                API.getDatabase().createPlayer(player.getUniqueId(), ChatOption.getTableName(), Arrays.asList(new ColumnInfo("Player", player.getName()), new ColumnInfo("UUID", player.getUniqueId().toString())));
            }
            if (!API.getDatabase().hasAccount(player.getUniqueId(), RenownEconomy.getTableName())) {
                API.getDatabase().createPlayer(player.getUniqueId(), RenownEconomy.getTableName(), Arrays.asList(new ColumnInfo("Player", player.getName()), new ColumnInfo("UUID", player.getUniqueId().toString())));
            }
        }
        else {
            if (SQLite.legacyExists() && Main.getSqLiteConverter().hasLegacyAccount(player)) {
                Main.getSqLiteConverter().convert(player);
            }
            if (Main.isMySQLConverterEnabled() && !Main.getMySQLConverter().hasAccount(player)) {
                Main.getMySQLConverter().convert(player);
            }
            this.patchProfile(player.getUniqueId());
        }
        if (API.getDatabase().hasAccount(player.getUniqueId(), PlayerEconomy.getTableName())) {
            new BukkitRunnable() {
                public void run() {
                    if (Main.getBounty() != null && API.getDatabase().getInt(player.getUniqueId(), "Bounty", PlayerEconomy.getTableName()) != 0) {
                        Main.getBounty().setBounty(player, API.getDatabase().getInt(player.getUniqueId(), "Bounty", PlayerEconomy.getTableName()), false);
                    }
                }
            }.runTaskLater(API.getPlugin(), 20L);
        }
        if (!PlayerEconomy.isCached(player)) {
            new PlayerEconomy(player, API.getDatabase().getInt(player.getUniqueId(), "Balance", PlayerEconomy.getTableName()));
        }
        if (!PlayerStats.isCached(player)) {
            Main.getStats().addPlayer(player);
        }
        if (!PlayerPerk.isCached(player)) {
            Main.getPerk().addPlayer(player);
        }
        if (!PlayerChatOption.isCached(player)) {
            Main.getChatOption().addPlayer(player);
        }
        if (!RenownEconomy.isCached(player)) {
            new RenownEconomy(player, API.getDatabase().getInt(player.getUniqueId(), "Renown", RenownEconomy.getTableName()));
        }
        if (!PlayerPrestige.isCached(player)) {
            new PlayerPrestige(player, API.getDatabase().getInt(player.getUniqueId(), "Prestige", RenownEconomy.getTableName()), API.getDatabase().getInt(player.getUniqueId(), "Grinded", RenownEconomy.getTableName()));
        }
        if (Main.isSyncEnabled() && Database.getMySQLCredentials().isEnabled()) {
            Main.getSync().addPlayer(player);
        }
        new BukkitRunnable() {
            public void run() {
                if (player.getInventory().getChestplate() == null && player.getInventory().getLeggings() == null && !player.getInventory().contains(Material.BOW)) {
                    Arena.this.giveKit(player, true, 35);
                }
                else {
                    Arena.this.giveKit(player, false, 35);
                    new BukkitRunnable() {
                        public void run() {
                            Main.getPerk().giveItems(player);
                        }
                    }.runTaskLater(API.getPlugin(), 40L);
                }
            }
        }.runTaskLater(API.getPlugin(), 10L);
        for (Material material : this.removeMaterialList) {
            player.getInventory().remove(material);
        }
        if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType().name().contains("WOOL")) {
            player.getInventory().setHelmet(null);
        }
        int n = 20;
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.RAMBO)) {
            n = 10;
        }
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.THICK)) {
            n += 4;
        }
        if (Main.getPitEventManager().isStarted()) {
            if (Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT)) {
                n = 40;
            }
            else if (Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH)) {
                Main.getPitEventManager().assignTeamForPlayer(player);
            }
        }
        if (this.playerPingFixBoolean) {
            API.getVersionSupport().fixPing(player);
        }
        if ((int)player.getMaxHealth() != n) {
            player.setHealthScale(n);
            player.setHealthScaled(true);
            player.setMaxHealth(n);

        }
        player.setHealth(n);
        this.giveArrows(player);
        new BukkitRunnable() {
            public void run() {
                if (player.getLevel() > 1 && API.getDatabase().getInt(player.getUniqueId(), "Level", PlayerEconomy.getTableName()) == 1) {
                    API.getDatabase().setInt(player.getUniqueId(), player.getLevel(), "Level", PlayerEconomy.getTableName());
                }
                else {
                    player.setLevel(API.getDatabase().getInt(player.getUniqueId(), "Level", PlayerEconomy.getTableName()));
                }
            }
        }.runTaskLater(API.getPlugin(), 40L);
        this.spawn(player);
        Bukkit.getPluginManager().callEvent(new ArenaJoinEvent(player));
        for (PotionEffectType potionEffectType : this.removePotionEffectList) {
            player.removePotionEffect(potionEffectType);
        }
        API.getVersionSupport().setAbsorptionHearts(player, 0);
        this.patchItems(player);
        if (Main.getPitEventManager().getEventType() == null || !Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH) || !Main.getPitEventManager().isStarted()) {
            Main.getPlayerManager().restoreCachedHelmet(player);
        }
        this.patchEnderChest(player);
        if (Main.getPlayerManager().isTheBeastCached(player)) {
            Main.getPlayerManager().restoreTheBeastCached(player);
        }
        Main.getEnderchestManager().onJoin(player);
        this.displayUnlockedFeatures(player);
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.FIRST_STRIKE)) {
            Main.getGlobalManager().firstHitMap.put(player.getUniqueId(), new ArrayList<UUID>());
        }
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.RECON)) {
            Main.getGlobalManager().reconBuff.put(player.getUniqueId(), 0);
        }
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.KUNG_FU_KNOWLEDGE)) {
            Main.getGlobalManager().kungFuKnowledgeBuff.put(player.getUniqueId(), 0);
        }
    }
    
    public void leaveArena(final Player player) {
        this.players.remove(player.getUniqueId());

        Main.getCombatManager().quitPlayer(player);
        Main.getPitEventManager().quitPlayer(player);
        if (player.getMaxHealth() > 20.0) {
            player.setHealthScale(20.0);
            player.setHealthScaled(true);
            player.setMaxHealth(20.0);
        }

        if (Main.getPitEventManager() != null && Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType() != null && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH)) {
            Main.getPitEventManager().unAssignTeamForPlayer(player);
        }
        Main.getEnderchestManager().onQuit(player);
        if (this.bountyAuraBoolean) {
            API.getVersionSupport().removePlayerAura(player);
        }
        if (!this.bbCached) {
            this.bbCached = true;
        }
        API.getVersionSupport().removeHologram(player, "UnlockedFeatures");
        if (Main.isSyncEnabled() && Database.getMySQLCredentials().isEnabled()) {
            Main.getSync().removePlayer(player);
        }
        Bukkit.getPluginManager().callEvent(new ArenaLeaveEvent(player));
        player.teleport(this.lobbyLocation);
    }
    
    public ArrayList<UUID> getPlayers() {
        return this.players;
    }
    
    public ArrayList<UUID> getBuilders() {
        return this.builders;
    }
    
    public void sendPlayersToSpawn() {
        for (UUID player : this.players) {
            this.spawn(Bukkit.getPlayer(player));
        }
    }
    
    public void spawn(final Player player) {
        player.setFireTicks(0);
        int n = 20;
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.RAMBO)) {
            n = 10;
        }
        if (PlayerPerk.get(player).isActivated(Perk.PerkType.THICK)) {
            n += 4;
        }
        if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT)) {
            n = 40;
        }
        if ((int)player.getMaxHealth() != n) {
            player.setMaxHealth(n);
            player.setHealthScale(n);
            player.setHealthScaled(true);
        }
        player.setHealth(n);
        player.teleport(this.spawnLocation);
        player.setVelocity(new Vector(0, 0, 0).multiply(0));
    }
    
    public int getRandomInt(final int n) {
        return this.random.nextInt(n + 1);
    }
    
    private void patchEnderChest(final Player player) {
        boolean b = false;
        for (final ItemStack itemStack : player.getEnderChest().getContents()) {
            if (itemStack != null && !Main.getEnderchestManager().getAllowedItems().contains(itemStack.getType())) {
                b = true;
            }
        }
        if (!b) {
            return;
        }
        for (final Material o : Material.values()) {
            if (!Main.getEnderchestManager().getAllowedItems().contains(o)) {
                player.getEnderChest().remove(o);
            }
        }
    }
    
    public void giveKit(final Player player, final boolean b, final int n) {
        new BukkitRunnable() {
            public void run() {
                final PlayerInventory inventory = player.getInventory();
                if (b) {
                    inventory.setChestplate(Arena.this.chainmailChestplate);
                    inventory.setLeggings(Arena.this.chainmailLeggings);
                    if (!PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                        inventory.setBoots(Arena.this.chainmailBoots);
                    }
                    else {
                        inventory.addItem(Arena.this.chainmailBootsMTag);
                    }
                    inventory.setItem(0, PlayerPerk.get(player).isActivated(Perk.PerkType.BARBARIAN) ? Main.getPerk().getBarbarianAxe().build() : Arena.this.ironSword);
                    inventory.setItem(1, Arena.this.item(Materials.BOW));
                    inventory.setItem(8, Materials.ARROW.getItem().setAmount(32).build());
                    Arena.this.giveRandomArmor(player);
                    Main.getPerk().giveItems(player);
                    player.updateInventory();
                    return;
                }
                final ArrayList<ItemStack> list = new ArrayList<ItemStack>();
                for (final ItemStack itemStack : inventory.getContents()) {
                    if (itemStack != null) {
                        list.add(itemStack);
                    }
                }
                list.add(inventory.getHelmet());
                list.add(inventory.getChestplate());
                list.add(inventory.getLeggings());
                list.add(inventory.getBoots());
                final String string = list.toString();
                boolean b = !string.contains("CHESTPLATE") && !string.contains("LEGGINGS") && !string.contains("BOOTS");
                if (!string.contains("CHESTPLATE")) {
                    inventory.setChestplate(Arena.this.chainmailChestplate);
                }
                if (!string.contains("LEGGINGS")) {
                    inventory.setLeggings(Arena.this.chainmailLeggings);
                }
                if (!string.contains("BOOTS")) {
                    if (!PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                        inventory.setBoots(Arena.this.chainmailBoots);
                    }
                    else {
                        inventory.addItem(Arena.this.chainmailBootsMTag);
                    }
                }
                final boolean activated = PlayerPerk.get(player).isActivated(Perk.PerkType.BARBARIAN);
                if (!string.contains(activated ? "AXE" : "SWORD")) {
                    final ItemStack itemStack2 = activated ? Main.getPerk().getBarbarianAxe().build() : Arena.this.ironSword;
                    if (inventory.getItem(0) == null) {
                        inventory.setItem(0, itemStack2);
                    }
                    else {
                        inventory.addItem(itemStack2);
                    }
                }
                if (!string.contains("BOW")) {
                    if (inventory.getItem(1) == null) {
                        inventory.setItem(1, Arena.this.bow);
                    }
                    else {
                        inventory.addItem(Arena.this.bow);
                    }
                }
                if (!string.contains("ARROW")) {
                    if (inventory.getItem(8) == null) {
                        inventory.setItem(8, Arena.this.arrow);
                    }
                    else {
                        inventory.addItem(Arena.this.arrow);
                    }
                }
                if (b) {
                    Arena.this.giveRandomArmor(player);
                }
                Main.getPerk().giveItems(player);
                player.updateInventory();
            }
        }.runTaskLater(API.getPlugin(), n);
    }
    
    private void giveRandomArmor(final Player player) {
        switch (this.getRandomInt(3)) {
            case 1: {
                player.getInventory().setChestplate(this.ironChestplate);
                break;
            }
            case 2: {
                player.getInventory().setLeggings(this.ironLeggings);
                break;
            }
            case 3: {
                if (!PlayerPerk.get(player).isActivated(Perk.PerkType.MARATHON)) {
                    player.getInventory().setBoots(this.ironBoots);
                    break;
                }
                player.getInventory().addItem(this.ironBootsMTag);
                break;
            }
        }
    }
    
    public void giveArrows(final Player player) {
        new BukkitRunnable() {
            public void run() {
                if (player.getInventory().getContents().length == 0) {
                    return;
                }
                player.getInventory().remove(Material.ARROW);
                if (player.getInventory().getItem(8) == null) {
                    player.getInventory().setItem(8, Arena.this.arrow);
                }
                else {
                    player.getInventory().addItem(Arena.this.arrow);
                }
            }
        }.runTaskLater(API.getPlugin(), 45L);
    }
    
    public ItemStack item(final Materials materials) {
        return materials.getItem().setUnbreakable(true).build();
    }
    
    public String getRequiredXP(final Player player, final boolean b) {
        final int level = player.getLevel();
        if (level == 120) {
            return b ? String.valueOf(120) : this.xpLevelMaxMessage;
        }
        int n;
        if (level <= 15) {
            n = 2 * level + 7;
        }
        else if (level <= 30) {
            n = 5 * level - 38;
        }
        else {
            n = 9 * level - 158;
        }
        return String.valueOf((int)(n * (1.0f - player.getExp())) + 1);
    }
    
    private void patchProfile(final UUID uuid) {
        final FileManager profile = API.getDatabase().getProfile(uuid);
        profile.addDefault(PlayerEconomy.getTableName() + ".Balance", this.defaultBalance);
        profile.addDefault(PlayerEconomy.getTableName() + ".Bounty", 0);
        profile.addDefault(PlayerEconomy.getTableName() + ".Level", 1);
        for (final Perk.PerkType perkType : Perk.PerkType.values()) {
            profile.addDefault(Perk.getTableName() + "." + perkType + "_OWNED", "false");
            profile.addDefault(Perk.getTableName() + "." + perkType + "_SLOT", 0);
        }
        final Stats.StatsType[] values2 = Stats.StatsType.values();
        for (int length2 = values2.length, j = 0; j < length2; ++j) {
            profile.addDefault(Stats.getTableName() + "." + values2[j], 0);
        }
        final ChatOption.ChatOptionType[] values3 = ChatOption.ChatOptionType.values();
        for (int length3 = values3.length, k = 0; k < length3; ++k) {
            profile.addDefault(ChatOption.getTableName() + "." + values3[k], "true");
        }
        profile.addDefault(RenownEconomy.getTableName() + ".Prestige", 0);
        profile.addDefault(RenownEconomy.getTableName() + ".Renown", 0);
        profile.addDefault(RenownEconomy.getTableName() + ".Grinded", 0);
        profile.copyDefaults();
        profile.save();
        profile.reload();
    }
    
    private void patchItems(final Player player) {
        new BukkitRunnable() {
            public void run() {
                for (final ItemStack itemStack : player.getInventory().getContents()) {
                    if (itemStack != null && itemStack.getType().name().contains("IRON")) {
                        API.getVersionSupport().addMetaData(itemStack, "upgradeable", "false");
                    }
                }
                for (final ItemStack itemStack2 : player.getInventory().getArmorContents()) {
                    if (itemStack2 != null && itemStack2.getType().name().contains("IRON")) {
                        API.getVersionSupport().addMetaData(itemStack2, "upgradeable", "false");
                    }
                }
                for (final ItemStack itemStack3 : player.getEnderChest().getContents()) {
                    if (itemStack3 != null && itemStack3.getType().name().contains("IRON")) {
                        API.getVersionSupport().addMetaData(itemStack3, "upgradeable", "false");
                    }
                }
            }
        }.runTaskLater(API.getPlugin(), 20L);
    }
    
    public void displayUnlockedFeatures(final Player player) {
        if (!this.isUnlockedFeaturesSetBoolean) {
            return;
        }
        if (this.tag5 == null) {
            this.tag5 = Main.getXpTag().get(5, 0);
            this.tag10 = Main.getXpTag().get(10, 0);
            this.tag15 = Main.getXpTag().get(15, 0);
            this.tag30 = Main.getXpTag().get(30, 0);
            this.tag50 = Main.getXpTag().get(50, 0);
            this.tag60 = Main.getXpTag().get(60, 0);
            this.tag70 = Main.getXpTag().get(70, 0);
            this.tag120 = Main.getXpTag().get(120, 0);
        }
        API.getVersionSupport().removeHologram(player, "UnlockedFeatures");
        final ArrayList<String> list = new ArrayList<String>();
        for (String s : this.unlockedFeaturesHologramCache) {
            list.add(s.replace("%lvl1feature%", this.respawnFeat).replace("%tag%", Main.getXpTag().get(1, 0)).replace("%lvl5feature%", this.displayFeature(player, 5) ? this.pitChatFeat.replace("%tag%", this.tag5) : this.lockedFeat).replace("%lvl10feature%", this.displayFeature(player, 10) ? this.upgradesFeat.replace("%tag%", this.tag10) : this.lockedFeat).replace("%lvl15feature%", this.displayFeature(player, 15) ? this.enderChestFeat.replace("%tag%", this.tag15) : this.lockedFeat).replace("%lvl30feature%", this.displayFeature(player, 30) ? this.contractsFeat.replace("%tag%", this.tag30) : this.lockedFeat).replace("%lvl50feature%", this.displayFeature(player, 50) ? this.statsFeat.replace("%tag%", this.tag50) : this.lockedFeat).replace("%lvl60feature%", this.displayFeature(player, 60) ? this.tradeFeat.replace("%tag%", this.tag60) : this.lockedFeat).replace("%lvl70feature%", this.displayFeature(player, 70) ? this.viewFeat.replace("%tag%", this.tag70) : this.lockedFeat).replace("%lvl120feature%", this.displayFeature(player, 120) ? this.prestigeFeat.replace("%tag%", this.tag120) : this.lockedFeat));
        }
        new BukkitRunnable() {
            public void run() {
                API.getVersionSupport().createHologram(player, "UnlockedFeatures", Arena.this.hologramFeatLocation, list);
            }
        }.runTaskAsynchronously(API.getPlugin());
    }
    
    public boolean displayFeature(final Player player, final int n) {
        return PlayerPrestige.get(player).getPrestige() != 0 || player.getLevel() >= n;
    }
    
    public String getNameColorCode(final Player player) {
        if (Main.getPitEventManager() == null) {
            return Main.getPlayerRankColor().getColorTag(player);
        }
        if (Main.getPitEventManager().getTeamColorCode(player) == null) {
            return Main.getPlayerRankColor().getColorTag(player);
        }
        if (Main.getPitEventManager().getTeamColorCode(player).contains("&7")) {
            return Main.getPlayerRankColor().getColorTag(player);
        }
        return Main.getPitEventManager().getTeamColorCode(player);
    }
}
