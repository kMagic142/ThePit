package ro.kmagic.managers;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.Main;
import ro.kmagic.api.events.player.PlayerPlaceEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockManager implements Listener {
    private final ArrayList<Material> materials;
    private final HashMap<Location, BlockState> blockCache;
    private final HashMap<Location, Material> liquidCache;
    private ArrayList<Block> permanentBlocks;
    private ArrayList<Block> removeNextRun;
    private boolean firstRun;
    private final Material lava;
    private final Material stationaryLava;
    private final Location spawnLocation;
    private final int cobblestoneTime;
    private final int obsidianTime;
    
    @EventHandler(priority = EventPriority.LOWEST)
    private void onBlockBreak(final BlockBreakEvent blockBreakEvent) {
        if (!Main.getArena().getPlayers().contains(blockBreakEvent.getPlayer().getUniqueId())) {
            return;
        }
        if (Main.getArena().getBuilders().contains(blockBreakEvent.getPlayer().getUniqueId())) {
            return;
        }
        if (this.blockCache.containsKey(blockBreakEvent.getBlock().getLocation())) {
            return;
        }
        blockBreakEvent.setCancelled(true);
    }
    
    public BlockManager() {
        this.materials = new ArrayList<Material>();
        this.blockCache = new HashMap<Location, BlockState>();
        this.liquidCache = new HashMap<Location, Material>();
        this.permanentBlocks = null;
        this.removeNextRun = null;
        API.registerEvent(this);
        this.materials.add(Material.AIR);
        this.materials.add(Material.WATER);
        this.materials.add(Material.LAVA);
        this.materials.add(Materials.STATIONARY_WATER.getItem().getMaterial());
        this.materials.add(Materials.STATIONARY_LAVA.getItem().getMaterial());
        this.lava = Materials.LAVA.getItem().getMaterial();
        this.stationaryLava = Materials.STATIONARY_LAVA.getItem().getMaterial();
        this.spawnLocation = Main.getSpawn().getLocation("Spawn");
        this.cobblestoneTime = Main.getSettings().getInt("TemporaryBlocks.Cobblestone");
        this.obsidianTime = Main.getSettings().getInt("TemporaryBlocks.Obsidian");
        if (Main.getSettings().getBoolean("TemporaryBlocks.ForceRemoveCheck")) {
            this.permanentBlocks = new ArrayList<Block>();
            this.removeNextRun = new ArrayList<Block>();
            this.firstRun = true;
            new BukkitRunnable() {
                public void run() {
                    final World world = BlockManager.this.spawnLocation.getWorld();
                    if (BlockManager.this.firstRun) {
                        for (final Chunk chunk : world.getLoadedChunks()) {
                            final int n = chunk.getX() << 4;
                            final int n2 = chunk.getZ() << 4;
                            for (int j = n; j < n + 16; ++j) {
                                for (int k = n2; k < n2 + 16; ++k) {
                                    for (int l = 0; l < 128; ++l) {
                                        final Block block = chunk.getWorld().getBlockAt(j, l, k);
                                        if (block.getType().equals(BlockManager.this.lava) || block.getType().equals(BlockManager.this.stationaryLava)) {
                                            BlockManager.this.permanentBlocks.add(block);
                                        }
                                    }
                                }
                            }
                        }
                        BlockManager.this.firstRun = false;
                    }
                    else {
                        for (Block block : BlockManager.this.removeNextRun) {
                            block.setType(Material.AIR);
                        }
                        BlockManager.this.removeNextRun.clear();
                        for (final Chunk chunk2 : world.getLoadedChunks()) {
                            final int n4 = chunk2.getX() << 4;
                            final int n5 = chunk2.getZ() << 4;
                            for (int n6 = n4; n6 < n4 + 16; ++n6) {
                                for (int n7 = n5; n7 < n5 + 16; ++n7) {
                                    for (int n8 = 0; n8 < 128; ++n8) {
                                        final Block block2 = chunk2.getWorld().getBlockAt(n6, n8, n7);
                                        if ((block2.getType().equals(BlockManager.this.lava) || block2.getType().equals(BlockManager.this.stationaryLava)) && !BlockManager.this.permanentBlocks.contains(block2)) {
                                            BlockManager.this.removeNextRun.add(block2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }.runTaskTimer(API.getPlugin(), 600L, 1800L);
        }
    }
    
    @EventHandler
    private void onPlayerPlace(final BlockPlaceEvent blockPlaceEvent) {
        final Player player = blockPlaceEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (Main.getArena().getBuilders().contains(player.getUniqueId())) {
            return;
        }
        if (this.spawnLocation == null || !blockPlaceEvent.getBlockAgainst().getWorld().equals(this.spawnLocation.getWorld())) {
            return;
        }
        final BlockState blockReplacedState = blockPlaceEvent.getBlockReplacedState();
        final Block block = blockPlaceEvent.getBlock();
        final Material type = blockPlaceEvent.getBlockPlaced().getType();
        if (block == null || blockReplacedState == null) {
            return;
        }
        if (type.equals(Material.COBBLESTONE)) {
            this.recoverBlock(blockReplacedState, blockPlaceEvent, this.cobblestoneTime);
        }
        else if (type.equals(Material.OBSIDIAN)) {
            this.recoverBlock(blockReplacedState, blockPlaceEvent, this.obsidianTime);
        }
    }
    
    @EventHandler
    private void onPlayerLiquid(final PlayerBucketEmptyEvent playerBucketEmptyEvent) {
        final Player player = playerBucketEmptyEvent.getPlayer();
        if (!Main.getArena().getPlayers().contains(player.getUniqueId())) {
            return;
        }
        if (Main.getArena().getBuilders().contains(player.getUniqueId())) {
            return;
        }
        if (!Main.getArena().getPlayers().contains(player.getUniqueId()) || this.spawnLocation == null || !playerBucketEmptyEvent.getBlockClicked().getWorld().equals(this.spawnLocation.getWorld())) {
            return;
        }
        final Location location = playerBucketEmptyEvent.getBlockClicked().getLocation();
        switch (playerBucketEmptyEvent.getBlockFace()) {
            case UP: {
                location.add(0.0, 1.0, 0.0);
                break;
            }
            case NORTH: {
                location.add(0.0, 0.0, -1.0);
                break;
            }
            case EAST: {
                location.add(1.0, 0.0, 0.0);
                break;
            }
            case WEST: {
                location.add(-1.0, 0.0, 0.0);
                break;
            }
            case SOUTH: {
                location.add(0.0, 0.0, 1.0);
                break;
            }
            case DOWN: {
                location.add(0.0, -1.0, 0.0);
                break;
            }
        }
        this.recoverLiquid(location);
        new BukkitRunnable() {
            public void run() {
                player.getInventory().remove(Material.BUCKET);
            }
        }.runTaskLater(API.getPlugin(), 2L);
        Bukkit.getPluginManager().callEvent(new PlayerPlaceEvent(player, playerBucketEmptyEvent.getBlockClicked()));
    }
    
    private void recoverBlock(final BlockState value, final BlockPlaceEvent blockPlaceEvent, final int n) {
        if (!this.materials.contains(value.getType())) {
            return;
        }
        Bukkit.getPluginManager().callEvent(new PlayerPlaceEvent(blockPlaceEvent.getPlayer(), blockPlaceEvent.getBlock()));
        if (!this.blockCache.containsKey(value.getLocation())) {
            this.blockCache.put(value.getLocation(), value);
            Main.getMapRecoverManager().addBlock(MapRecoverManager.RecoverType.REPLACE, this.blockCache.get(value.getLocation()).getBlock(), this.blockCache.get(value.getLocation()).getType());
        }
        new BukkitRunnable() {
            public void run() {
                if (!BlockManager.this.blockCache.containsKey(value.getLocation())) {
                    return;
                }
                blockPlaceEvent.getBlockPlaced().setType(BlockManager.this.blockCache.get(value.getLocation()).getType());
                Main.getMapRecoverManager().removeBlock(MapRecoverManager.RecoverType.REPLACE, BlockManager.this.blockCache.get(value.getLocation()).getBlock());
            }
        }.runTaskLater(API.getPlugin(), n * 20L);
    }
    
    private void recoverLiquid(final Location key) {
        final Block block = key.getWorld().getBlockAt(key.getBlockX(), key.getBlockY(), key.getBlockZ());
        if (!this.materials.contains(block.getType())) {
            return;
        }
        if (!this.liquidCache.containsKey(key)) {
            this.liquidCache.put(key, block.getType());
            Main.getMapRecoverManager().addBlock(MapRecoverManager.RecoverType.REPLACE, block, this.liquidCache.get(key));
        }
        new BukkitRunnable() {
            public void run() {
                if (!BlockManager.this.liquidCache.containsKey(key)) {
                    return;
                }
                key.getWorld().getBlockAt(key.getBlockX(), key.getBlockY(), key.getBlockZ()).setType(BlockManager.this.liquidCache.get(key));
                Main.getMapRecoverManager().removeBlock(MapRecoverManager.RecoverType.REPLACE, key.getWorld().getBlockAt(key.getBlockX(), key.getBlockY(), key.getBlockZ()));
                if (BlockManager.this.removeNextRun != null) {
                    BlockManager.this.removeNextRun.remove(key.getWorld().getBlockAt(key.getBlockX(), key.getBlockY(), key.getBlockZ()));
                }
            }
        }.runTaskLater(API.getPlugin(), Main.getSettings().getInt("TemporaryBlocks.Liquid") * 20L);
    }
}
