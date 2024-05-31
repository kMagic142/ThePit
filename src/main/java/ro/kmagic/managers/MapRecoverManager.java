package ro.kmagic.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import ro.kmagic.libapi.utils.FileManager;

import java.util.ArrayList;
import java.util.List;

public class MapRecoverManager extends FileManager {

    public MapRecoverManager() {
        super("MapRecover", "Cache");
        this.addDefault("RemoveBlocks", new ArrayList<>());
        this.addDefault("ReplaceBlocks", new ArrayList<>());
        this.copyDefaults();
        this.save();
        for (String s : this.getStringList("RemoveBlocks")) {
            final String[] split = s.split(", ");
            Bukkit.getWorld(split[0]).getBlockAt(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])).setType(Material.AIR);
        }
        this.set("RemoveBlocks", new ArrayList<>());
        this.set("ReplaceBlocks", new ArrayList<>());
    }
    
    public void addBlock(final RecoverType recoverType, final Block block) {
        switch (recoverType) {
            case REMOVE: {
                final List<String> stringList = this.getStringList("RemoveBlocks");
                final Location location = block.getLocation();
                final String string = location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
                if (!stringList.contains(string)) {
                    stringList.add(string);
                    this.set("RemoveBlocks", stringList);
                    break;
                }
                break;
            }
            case REPLACE: {
                final List<String> stringList2 = this.getStringList("ReplaceBlocks");
                final Location location2 = block.getLocation();
                String str = location2.getWorld().getName() + ", " + location2.getBlockX() + ", " + location2.getBlockY() + ", " + location2.getBlockZ() + ", " + block.getType();
                if (!stringList2.contains(str)) {
                    stringList2.add(str);
                    this.set("ReplaceBlocks", stringList2);
                    break;
                }
                break;
            }
        }
    }
    
    public void addBlock(final RecoverType recoverType, final Block block, final Material obj) {
        switch (recoverType) {
            case REMOVE: {
                final List<String> stringList = this.getStringList("RemoveBlocks");
                final Location location = block.getLocation();
                final String string = location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
                if (!stringList.contains(string)) {
                    stringList.add(string);
                    this.set("RemoveBlocks", stringList);
                    break;
                }
                break;
            }
            case REPLACE: {
                final List<String> stringList2 = this.getStringList("ReplaceBlocks");
                final Location location2 = block.getLocation();
                String str = location2.getWorld().getName() + ", " + location2.getBlockX() + ", " + location2.getBlockY() + ", " + location2.getBlockZ() + ", " + obj;
                if (!stringList2.contains(str)) {
                    stringList2.add(str);
                    this.set("ReplaceBlocks", stringList2);
                    break;
                }
                break;
            }
        }
    }
    
    public void addBlocks(final RecoverType recoverType, final List<Block> list) {
        switch (recoverType) {
            case REMOVE: {
                final List<String> stringList = this.getStringList("RemoveBlocks");
                for (Block block : list) {
                    final Location location = block.getLocation();
                    final String string = location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
                    if (!stringList.contains(string)) {
                        stringList.add(string);
                    }
                }
                this.set("RemoveBlocks", stringList);
                break;
            }
            case REPLACE: {
                final List<String> stringList2 = this.getStringList("ReplaceBlocks");
                for (final Block block : list) {
                    final Location location2 = block.getLocation();
                    String str = location2.getWorld().getName() + ", " + location2.getBlockX() + ", " + location2.getBlockY() + ", " + location2.getBlockZ() + ", " + block.getType();
                    if (!stringList2.contains(str)) {
                        stringList2.add(str);
                    }
                }
                this.set("ReplaceBlocks", stringList2);
                break;
            }
        }
    }
    
    public void removeBlock(final RecoverType recoverType, final Block block) {
        switch (recoverType) {
            case REMOVE: {
                final List<String> stringList = this.getStringList("RemoveBlocks");
                final Location location = block.getLocation();
                final String string = location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
                if (stringList.contains(string)) {
                    stringList.remove(string);
                    this.set("RemoveBlocks", stringList);
                    break;
                }
                break;
            }
            case REPLACE: {
                final List<String> stringList2 = this.getStringList("ReplaceBlocks");
                final Location location2 = block.getLocation();
                for (final String s : this.getStringList("ReplaceBlocks")) {
                    if (s.contains(location2.getWorld().getName() + ", " + location2.getBlockX() + ", " + location2.getBlockY() + ", " + location2.getBlockZ())) {
                        stringList2.remove(s);
                        this.set("ReplaceBlocks", stringList2);
                    }
                }
                break;
            }
        }
    }
    
    public void removeBlocks(final RecoverType recoverType, final List<Block> list) {
        switch (recoverType) {
            case REMOVE: {
                final List<String> stringList = this.getStringList("RemoveBlocks");
                for (Block block : list) {
                    final Location location = block.getLocation();
                    stringList.remove(location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
                }
                this.set("RemoveBlocks", stringList);
                break;
            }
            case REPLACE: {
                final List<String> stringList2 = this.getStringList("ReplaceBlocks");
                for (Block block : list) {
                    final Location location2 = block.getLocation();
                    for (final String s : this.getStringList("ReplaceBlocks")) {
                        if (s.contains(location2.getWorld().getName() + ", " + location2.getBlockX() + ", " + location2.getBlockY() + ", " + location2.getBlockZ())) {
                            stringList2.remove(s);
                        }
                    }
                }
                this.set("ReplaceBlocks", stringList2);
                break;
            }
        }
    }
    
    public enum RecoverType {
        REMOVE, 
        REPLACE
    }
}
