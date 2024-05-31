package ro.kmagic.libapi.versionsupport;

import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.command.Command;
import org.bukkit.inventory.meta.ItemMeta;
import ro.kmagic.libapi.API;
import org.bukkit.Bukkit;
import java.util.List;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class VersionSupport {
    
    public abstract void sendTitle(final Player p0, final TitleType p1, final String p2, final int p3, final int p4, final int p5);

    public abstract void setInvincible(Entity entity);

    public abstract void sendTablist(final Player p0, final List<String> p1, final List<String> p2);
    
    public abstract void sendActionBar(final Player p0, final String p1);
    
    public abstract void fixPing(final Player p0);
    
    public abstract void sendBorder(final Player p0, final BorderColor p1, final double p2, final double p3, final double p4);
    
    public void registerPlayerPickup() {
        Bukkit.getPluginManager().registerEvents(new PlayerPickup(), API.getPlugin());
    }
    
    public ItemMeta setUnbreakable(final ItemMeta itemMeta, final boolean unbreakable) {
        return itemMeta;
    }
    
    public abstract void registerCommand(final Command p0);

    public abstract boolean interactFromOffHand(PlayerInteractEvent playerInteractEvent);

    public abstract void addMetaData(final ItemStack p0, final String p1, final String p2);
    
    public abstract boolean hasMetaData(final ItemStack p0, final String p1);
    
    public abstract String getMetaData(final ItemStack p0, final String p1);
    
    public abstract void removeMetaData(final ItemStack p0, final String p1);
    
    public abstract String getItemName(final ItemStack p0);
    
    public abstract void spawnParticle(final Player p0, final String p1, final int p2);
    
    public abstract void setAbsorptionHearts(final Player p0, final int p1);
    
    public abstract int getAbsorptionHearts(final Player p0);
    
    public abstract void refreshArmor(final Player p0);
    
    public void setSkullOwner(final ItemStack itemStack, final String name) {
        final SkullMeta itemMeta = (SkullMeta)itemStack.getItemMeta();
        try {
            itemMeta.setOwner(Bukkit.getOfflinePlayer(UUID.fromString(name)).getName());
        }
        catch (Exception ex) {
            itemMeta.setOwner(name);
        }
        itemStack.setItemMeta(itemMeta);
    }
    
    public UUID getSkullOwner(final SkullMeta skullMeta) {
        return Bukkit.getPlayer(skullMeta.getOwner()).getUniqueId();
    }
    
    public String makeFirstLetterUppercase(final String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    public boolean contains(final Integer... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (this.getClass().getSimpleName().contains(String.valueOf(array[i]))) {
                return true;
            }
        }
        return false;
    }

    public abstract void sendBossBar(Player player, String s, float n, BossBarColor bossBarColor, BossBarStyle bossBarStyle);

    public abstract void updateBossBar(Player player, String s, float n);

    public abstract void updateBossBarTitle(Player player, String s);

    public abstract void updateBossBarProgress(Player player, float n);

    public abstract void removeBossBar(Player player);

    public abstract boolean hasBossBar(Player player);

    public void patchProtocolLibRespawn(final Player player) {
    }
    
    public abstract void createPlayerAura(final Player p0, final String p1, final int p2);
    
    public abstract void removePlayerAura(final Player p0);
    
    public abstract boolean playerHasAura(final Player p0);
    
    public abstract void removeAllPlayerAura();
    
    public abstract void createHologram(final Player p0, final String p1, final Location p2, final List<String> p3);
    
    public abstract void removeHologram(final Player p0, final String p1);
    
    public abstract void removeALlHolograms();
    
    public abstract ItemStack modifyItemDamage(final Material p0, final int p1);
    
    public abstract void injectPlayer(final Player p0);
    
    public abstract void uninjectPlayer(final Player p0);
    
    public abstract void openAnvilGUI(final Player p0, final ItemStack p1, final String p2, final String p3);
    
    public enum TitleType
    {
        TITLE, 
        SUBTITLE
    }
    
    public enum BossBarColor
    {
        PINK, 
        BLUE, 
        RED, 
        GREEN, 
        YELLOW, 
        PURPLE, 
        WHITE
    }
    
    public enum BossBarStyle
    {
        SOLID, 
        SEGMENTED_6, 
        SEGMENTED_10, 
        SEGMENTED_12, 
        SEGMENTED_20
    }
}
