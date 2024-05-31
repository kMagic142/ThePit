package ro.kmagic.features.perk;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import ro.kmagic.api.events.player.PlayerGoldenHeadEatEvent;
import org.bukkit.Bukkit;
import ro.kmagic.features.stats.Stats;
import ro.kmagic.features.stats.PlayerStats;
import ro.kmagic.libapi.versionsupport.sound.Sounds;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import java.util.Map;
import java.util.List;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ro.kmagic.features.events.PitEventManager;
import ro.kmagic.libapi.events.player.PlayerDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import ro.kmagic.libapi.events.player.PlayerAssistEvent;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.Main;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.utils.FileManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;
import org.bukkit.event.Listener;

public class PerkEvents implements Listener {
    private final ArrayList<UUID> cooldown;
    private final ArrayList<UUID> shotList;
    private final ArrayList<UUID> refresh;
    private final HashMap<UUID, Integer> seconds;
    private final HashMap<UUID, Integer> gladiator;
    private final HashMap<UUID, ArrayList<UUID>> playerShot;
    private final HashMap<UUID, BukkitTask> goldenHeadsTask;
    private final int goldenHeadsItemStackLimit;
    private final int olympusItemStackLimit;
    private final int soupItemStackLimit;
    private final int goldenAppleItemStackLimit;
    private final int goldenHeadsCooldownSeconds;
    private final int potatoCooldownSeconds;
    private final int potatoRegeneration;
    private final int potatoAbsorption;
    private final ItemStack fishingRod;
    private final ItemStack lavaBucket;
    private final ItemStack diamondPickaxe;
    private final ItemStack cobblestone;
    private final ItemStack chainmailHelmet;
    private final ItemStack air;
    private final ItemStack goldenHead;
    private final ItemStack arrow;
    private final ItemStack potatoItem;
    private final Material potato;
    private final FileManager goldenHeadsFile;
    private final String goldenHeadsCooldownMessage;
    private final String potatoCooldownMessage;
    private final String potatoName;
    
    public PerkEvents() {
        this.cooldown = new ArrayList<UUID>();
        this.shotList = new ArrayList<UUID>();
        this.refresh = new ArrayList<UUID>();
        this.seconds = new HashMap<UUID, Integer>();
        this.gladiator = new HashMap<UUID, Integer>();
        this.playerShot = new HashMap<UUID, ArrayList<UUID>>();
        this.goldenHeadsTask = new HashMap<UUID, BukkitTask>();
        API.registerEvent(this);
        new BukkitRunnable() {
            public void run() {
                final ArrayList<UUID> list = new ArrayList<UUID>();
                for (final UUID uuid : PerkEvents.this.seconds.keySet()) {
                    final int intValue = PerkEvents.this.seconds.get(uuid);
                    if (intValue == 0) {
                        list.add(uuid);
                    }
                    else {
                        PerkEvents.this.seconds.put(uuid, intValue - 1);
                    }
                }
                for (UUID uuid : list) {
                    PerkEvents.this.seconds.remove(uuid);
                }
            }
        }.runTaskTimer(API.getPlugin(), 20L, 20L);
        this.goldenHeadsItemStackLimit = Main.getPerkDescription().get(Perk.PerkType.GOLDEN_HEADS).getInt("Item.Stack-Limit");
        this.olympusItemStackLimit = Main.getPerkDescription().get(Perk.PerkType.OLYMPUS).getInt("Item.Stack-Limit");
        this.soupItemStackLimit = Main.getPerkDescription().get(Perk.PerkType.SOUP).getInt("Item.Stack-Limit");
        this.goldenAppleItemStackLimit = Main.getSettings().getInt("Golden-Apple.Stack-Limit");
        this.fishingRod = Main.getPerk().item(Materials.FISHING_ROD);
        this.lavaBucket = Main.getPerk().item(Materials.LAVA_BUCKET);
        this.diamondPickaxe = Main.getPerk().item(Materials.DIAMOND_PICKAXE);
        this.cobblestone = Materials.COBBLESTONE.getItem().setAmount(24).build();
        this.chainmailHelmet = Main.getPerk().item(Materials.CHAINMAIL_HELMET);
        this.air = Materials.AIR.getItem().build();
        this.goldenHead = Main.getPerk().getGoldenHeads().build();
        this.potato = Materials.POTATO.getItem().getMaterial();
        this.goldenHeadsFile = Main.getPerkDescription().get(Perk.PerkType.GOLDEN_HEADS);
        this.goldenHeadsCooldownMessage = this.goldenHeadsFile.getString("Cooldown.Message");
        this.goldenHeadsCooldownSeconds = this.goldenHeadsFile.getInt("Cooldown.Seconds");
        this.potatoCooldownMessage = Main.getPitEventManager().getString("Event.Major.RAGE_PIT.Potato.Cooldown.Message");
        this.potatoCooldownSeconds = Main.getPitEventManager().getInt("Event.Major.RAGE_PIT.Potato.Cooldown.Seconds");
        this.potatoRegeneration = Main.getPitEventManager().getInt("Event.Major.RAGE_PIT.Potato.Regeneration");
        this.potatoAbsorption = Main.getPitEventManager().getInt("Event.Major.RAGE_PIT.Potato.Absorption");
        this.arrow = Materials.ARROW.getItem().setAmount(3).build();
        this.potatoName = Main.getPitEventManager().getString("Event.Major.RAGE_PIT.Potato.Name");
        this.potatoItem = Materials.POTATO.getItem().setDisplayName(this.potatoName).build();
    }
    
    @EventHandler
    private void onPlayerAssist(final PlayerAssistEvent playerAssistEvent) {
        if (playerAssistEvent.getKiller() != null) {
            return;
        }
        for (final Player player : playerAssistEvent.getDamagers()) {
            if (Main.getArena().getPlayers().contains(player.getUniqueId()) && !PlayerPerk.get(player).isActivated(Perk.PerkType.VAMPIRE) && !PlayerPerk.get(player).isActivated(Perk.PerkType.DIRTY) && PlayerPerk.get(player).isActivated(Perk.PerkType.SOUP)) {
                final int n = PlayerPerk.get(player).isActivated(Perk.PerkType.OVERHEAL) ? 2 : 1;
                final ItemStack build = Main.getPerk().getSoup().build();
                int n2 = 0;
                for (final ItemStack itemStack : player.getInventory().getContents()) {
                    if (itemStack != null && itemStack.isSimilar(build)) {
                        n2 += itemStack.getAmount();
                    }
                }
                if (n2 >= Main.getPerkDescription().get(Perk.PerkType.SOUP).getInt("Item.Stack-Limit") * n) {
                    continue;
                }
                player.getInventory().addItem(build);
            }
        }
    }
    
    @EventHandler
    private void onPlayerKillEvent(final PlayerDeathEvent playerDeathEvent) {
        if (playerDeathEvent.getKiller() == null) {
            return;
        }
        final Player killed = playerDeathEvent.getKilled();
        final Player killer = playerDeathEvent.getKiller();
        if (!Main.getArena().getPlayers().contains(killer.getUniqueId())) {
            return;
        }
        if (Main.getPitEventManager().getEventType() != null && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT) && Main.getPitEventManager().isStarted()) {
            if (Main.getPitEventManager().isStarted()) {
                killer.getInventory().addItem(this.potato());
            }
        }
        else if (!PlayerPerk.get(killer).isActivated(Perk.PerkType.VAMPIRE)) {
            final int n = PlayerPerk.get(killer).isActivated(Perk.PerkType.OVERHEAL) ? 2 : 1;
            if (PlayerPerk.get(killer).isActivated(Perk.PerkType.GOLDEN_HEADS)) {
                final ItemStack build = Main.getPerk().getGoldenHeads().build();
                int n2 = 0;
                for (final ItemStack itemStack : killer.getInventory().getContents()) {
                    if (itemStack != null && itemStack.isSimilar(build)) {
                        n2 += itemStack.getAmount();
                    }
                }
                if (n2 < this.goldenHeadsItemStackLimit * n) {
                    killer.getInventory().addItem(build);
                }
                killer.sendMessage(build.getItemMeta().getDisplayName());
            }
            else if (PlayerPerk.get(killer).isActivated(Perk.PerkType.OLYMPUS)) {
                final ItemStack build2 = Main.getPerk().getOlympusPotion().build();
                int n3 = 0;
                for (final ItemStack itemStack2 : killer.getInventory().getContents()) {
                    if (itemStack2 != null && itemStack2.isSimilar(build2)) {
                        n3 += itemStack2.getAmount();
                    }
                }
                if (n3 < this.olympusItemStackLimit * n) {
                    killer.getInventory().addItem(build2);
                }
            }
            else if (PlayerPerk.get(killer).isActivated(Perk.PerkType.SOUP)) {
                final ItemStack build3 = Main.getPerk().getSoup().build();
                int n4 = 0;
                for (final ItemStack itemStack3 : killer.getInventory().getContents()) {
                    if (itemStack3 != null && itemStack3.isSimilar(build3)) {
                        n4 += itemStack3.getAmount();
                    }
                }
                if (n4 < this.soupItemStackLimit * n) {
                    killer.getInventory().addItem(build3);
                }
            }
            else {
                final ItemStack build4 = Materials.GOLDEN_APPLE.getItem().build();
                int n5 = 0;
                for (final ItemStack itemStack4 : killer.getInventory().getContents()) {
                    if (itemStack4 != null && itemStack4.isSimilar(build4)) {
                        n5 += itemStack4.getAmount();
                    }
                }
                if (n5 < this.goldenAppleItemStackLimit * n) {
                    killer.getInventory().addItem(build4);
                }
            }
        }
        if (PlayerPerk.get(killed).isActivated(Perk.PerkType.FISHING_ROD)) {
            new BukkitRunnable() {
                public void run() {
                    killed.getInventory().addItem(PerkEvents.this.fishingRod);
                }
            }.runTaskLater(API.getPlugin(), 5L);
        }
        if (PlayerPerk.get(killed).isActivated(Perk.PerkType.LAVA_BUCKET)) {
            new BukkitRunnable() {
                public void run() {
                    killed.getInventory().addItem(PerkEvents.this.lavaBucket);
                }
            }.runTaskLater(API.getPlugin(), 5L);
        }
        if (PlayerPerk.get(killed).isActivated(Perk.PerkType.MINEMAN)) {
            new BukkitRunnable() {
                public void run() {
                    killed.getInventory().addItem(PerkEvents.this.diamondPickaxe);
                    killed.getInventory().addItem(PerkEvents.this.cobblestone);
                }
            }.runTaskLater(API.getPlugin(), 5L);
        }
        if (PlayerPerk.get(killed).isActivated(Perk.PerkType.SAFETY_FIRST)) {
            new BukkitRunnable() {
                public void run() {
                    if (Main.getPitEventManager().getEventType() == null || !Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.TEAM_DEATHMATCH) || !Main.getPitEventManager().isStarted()) {
                        killed.getInventory().setHelmet(PerkEvents.this.chainmailHelmet);
                    }
                }
            }.runTaskLater(API.getPlugin(), 5L);
        }
        if (PlayerPerk.get(killer).isActivated(Perk.PerkType.STRENGTH_CHAINING)) {
            if (this.seconds.containsKey(killer.getUniqueId())) {
                if (this.seconds.get(killer.getUniqueId()) < 21) {
                    this.seconds.put(killer.getUniqueId(), this.seconds.get(killer.getUniqueId()) + 7);
                }
            }
            else {
                this.seconds.put(killer.getUniqueId(), 7);
            }
        }
        if (this.playerShot.containsKey(killed.getUniqueId())) {
            new BukkitRunnable() {
                public void run() {
                    PerkEvents.this.playerShot.remove(killed.getUniqueId());
                }
            }.runTaskLater(API.getPlugin(), 20L);
        }
        if (PlayerPerk.get(killer).isActivated(Perk.PerkType.DIRTY)) {
            killer.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            killer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1));
        }
        for (Map.Entry<UUID, List<UUID>> uuidListEntry : Main.getGlobalManager().firstHitMap.entrySet()) {
            uuidListEntry.getValue().remove(killed.getUniqueId());
        }
    }
    
    @EventHandler
    private void onPlayerConsume(final PlayerItemConsumeEvent playerItemConsumeEvent) {
        final Player player = playerItemConsumeEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (!playerItemConsumeEvent.getItem().getType().equals(Material.POTION)) {
            return;
        }
        playerItemConsumeEvent.setCancelled(true);
        player.getInventory().remove(playerItemConsumeEvent.getItem());
        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 480, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 1));
        player.giveExp(27);
    }
    
    @EventHandler
    private void onSoupInteract(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (!player.getItemInHand().getType().equals(Main.getPerk().getSoup().getMaterial())) {
            return;
        }
        playerInteractEvent.setCancelled(true);
        player.setItemInHand(this.air);
        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 140, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 140, 0));
        if ((int)player.getHealth() + 3 >= (int)player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
        else {
            player.setHealth(player.getHealth() + 3.0);
        }
        API.getVersionSupport().setAbsorptionHearts(player, API.getVersionSupport().getAbsorptionHearts(player) + 2);
        Main.getGlobalManager().soupBuff.add(player.getUniqueId());
        Sounds.GENERIC_EAT.getSound().play(player, 1.0f, 1.0f);
    }
    
    @EventHandler
    private void onGoldenHeadsInteract(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        final ItemStack itemInHand = player.getItemInHand();
        if (!itemInHand.getType().equals(this.goldenHead.getType()) && !itemInHand.getType().equals(this.potato().getType())) {
            return;
        }
        playerInteractEvent.setCancelled(true);
        boolean b = false;
        boolean b2 = false;
        if (itemInHand.getType().equals(this.goldenHead.getType()) && itemInHand.isSimilar(this.goldenHead)) {
            b = true;
        }
        if (Main.getPitEventManager().isStarted() && Main.getPitEventManager().getEventType().equals(PitEventManager.PitEventType.RAGE_PIT) && itemInHand.getType().equals(this.potato)) {
            b2 = true;
        }
        if (this.cooldown.contains(player.getUniqueId())) {
            if (b) {
                player.sendMessage(this.goldenHeadsCooldownMessage.replace("%seconds%", String.valueOf(this.goldenHeadsCooldownSeconds)));
            }
            else if (b2) {
                player.sendMessage(this.potatoCooldownMessage);
            }
            return;
        }
        if (!b2 && !b) {
            return;
        }
        this.cooldown.add(player.getUniqueId());
        int n = this.goldenHeadsCooldownSeconds;
        if (!b) {
            n = this.potatoCooldownSeconds;
        }
        new BukkitRunnable() {
            public void run() {
                PerkEvents.this.cooldown.remove(player.getUniqueId());
            }
        }.runTaskLater(API.getPlugin(), n * 20L);
        player.removePotionEffect(PotionEffectType.ABSORPTION);
        final int amount = itemInHand.getAmount();
        if (amount > 1) {
            itemInHand.setAmount(amount - 1);
        }
        else {
            player.setItemInHand(this.air);
        }
        if (b) {
            PlayerStats.get(player).add(Stats.StatsType.GOLDEN_HEADS_EATEN, 1);
            player.removePotionEffect(PotionEffectType.REGENERATION);
            player.removePotionEffect(PotionEffectType.SPEED);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 160, 0));
            if (this.goldenHeadsTask.containsKey(player.getUniqueId())) {
                this.goldenHeadsTask.get(player.getUniqueId()).cancel();
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0));
            API.getVersionSupport().setAbsorptionHearts(player, 6);
            this.goldenHeadsTask.put(player.getUniqueId(), new BukkitRunnable() {
                public void run() {
                    API.getVersionSupport().setAbsorptionHearts(player, 0);
                }
            }.runTaskLater(API.getPlugin(), 2400L));
            Sounds.GENERIC_EAT.getSound().play(player, 1.0f, 1.0f);
            Bukkit.getPluginManager().callEvent(new PlayerGoldenHeadEatEvent(player));
        }
        else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, this.potatoRegeneration - 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, this.potatoAbsorption - 1));
        }
    }
    
    @EventHandler
    private void onInventoryClick(final InventoryClickEvent inventoryClickEvent) {
        if (inventoryClickEvent.getClickedInventory() == null) {
            return;
        }
        if (!inventoryClickEvent.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            return;
        }
        if (!Main.getArena().getPlayers().contains(inventoryClickEvent.getWhoClicked().getUniqueId())) {
            return;
        }
        if (!inventoryClickEvent.getCursor().isSimilar(this.goldenHead)) {
            return;
        }
        if (inventoryClickEvent.getSlotType().equals(InventoryType.SlotType.ARMOR)) {
            inventoryClickEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    private void onEndlessQuiverShot(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)entityDamageByEntityEvent.getEntity();
        Player player2 = null;
        boolean b = false;
        if (entityDamageByEntityEvent.getDamager() instanceof Arrow && ((Arrow)entityDamageByEntityEvent.getDamager()).getShooter() instanceof Player) {
            b = true;
            player2 = (Player)((Arrow)entityDamageByEntityEvent.getDamager()).getShooter();
        }
        else if (entityDamageByEntityEvent.getDamager() instanceof Player) {
            player2 = (Player)entityDamageByEntityEvent.getDamager();
        }
        if (player2 == null || !Main.getArena().getPlayers().contains(player2.getUniqueId()) || player2 == player) {
            return;
        }
        if (PlayerPerk.get(player2).isActivated(Perk.PerkType.STRENGTH_CHAINING) && this.seconds.containsKey(player2.getUniqueId())) {
            entityDamageByEntityEvent.setDamage(entityDamageByEntityEvent.getDamage() / 8.0 + entityDamageByEntityEvent.getDamage());
        }
        if (PlayerPerk.get(player2).isActivated(Perk.PerkType.ENDLESS_QUIVER) && b) {
            player2.getInventory().addItem(this.arrow);
        }
        if (!b) {
            return;
        }
        if (!PlayerPerk.get(player2).isActivated(Perk.PerkType.SPAMMER)) {
            return;
        }
        if (!this.playerShot.containsKey(player.getUniqueId())) {
            this.playerShot.put(player.getUniqueId(), this.shotList);
        }
        if (!this.playerShot.get(player.getUniqueId()).contains(player2.getUniqueId())) {
            this.playerShot.get(player.getUniqueId()).add(player2.getUniqueId());
        }
    }
    
    @EventHandler
    private void onGladiatorUpdate(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) || this.refresh.contains(player.getUniqueId())) {
            return;
        }
        this.refresh.add(player.getUniqueId());
        new BukkitRunnable() {
            public void run() {
                PerkEvents.this.refresh.remove(player.getUniqueId());
            }
        }.runTaskLater(API.getPlugin(), 100L);
        final PlayerPerk value = PlayerPerk.get(player);
        if (value == null) {
            return;
        }
        if (!value.isActivated(Perk.PerkType.GLADIATOR)) {
            return;
        }
        final ArrayList<Player> list = new ArrayList<Player>();
        for (final Entity entity : player.getNearbyEntities(6.0, 6.0, 6.0)) {
            if (entity.getType().equals(EntityType.PLAYER)) {
                list.add((Player)entity);
            }
        }
        this.gladiator.put(player.getUniqueId(), (list.size() <= 10) ? (list.size() * 3) : 30);
    }
    
    private ItemStack potato() {
        return this.potatoItem;
    }
    
    public boolean isInPlayerShot(final Player player) {
        return this.playerShot.containsKey(player.getUniqueId());
    }
    
    public ArrayList<UUID> getPlayerShot(final Player player) {
        return this.playerShot.get(player.getUniqueId());
    }
    
    public int getGladiatorPercentage(final Player player) {
        if (!this.gladiator.containsKey(player.getUniqueId())) {
            return 0;
        }
        final int intValue = this.gladiator.get(player.getUniqueId());
        if (intValue >= 9) {
            return intValue;
        }
        return 0;
    }
}
