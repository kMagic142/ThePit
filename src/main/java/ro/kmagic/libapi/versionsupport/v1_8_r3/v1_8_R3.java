package ro.kmagic.libapi.versionsupport.v1_8_r3;

import net.minecraft.server.v1_8_R3.Container;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ContainerAnvil;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.EntityHuman;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPromise;
import ro.kmagic.libapi.events.misc.AnvilClickEvent;
import net.minecraft.server.v1_8_R3.PacketPlayOutExperience;
import net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot;
import ro.kmagic.libapi.utils.AnvilUtil;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelDuplexHandler;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagString;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Material;

import java.util.*;

import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import org.bukkit.ChatColor;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import net.minecraft.server.v1_8_R3.WorldServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutRespawn;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.Effect;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.player.PlayerInteractEvent;
import ro.kmagic.libapi.API;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.command.Command;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import ro.kmagic.libapi.item.ItemBuilder;
import ro.kmagic.libapi.versionsupport.entity.equipable.EquipmentSlot;
import ro.kmagic.libapi.versionsupport.entity.equipable.EntityEquipableNMS;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_8_R3.WorldBorder;
import ro.kmagic.libapi.versionsupport.BorderColor;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import ro.kmagic.libapi.utils.CustomError;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import ro.kmagic.libapi.placeholder.PlaceholderManager;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.scheduler.BukkitTask;
import ro.kmagic.libapi.versionsupport.VersionSupport;

public class v1_8_R3 extends VersionSupport
{
    private final Random random;
    private final HashMap<UUID, BukkitTask> auraTaskCache;
    private final HashMap<UUID, List<CraftArmorStand>> craftArmorStandCache;
    private final HashMap<String, List<EntityArmorStand>> armorStandMap;
    
    public v1_8_R3() {
        this.random = new Random();
        this.auraTaskCache = new HashMap<UUID, BukkitTask>();
        this.craftArmorStandCache = new HashMap<UUID, List<CraftArmorStand>>();
        this.armorStandMap = new HashMap<String, List<EntityArmorStand>>();
    }
    
    @Override
    public void setInvincible(final Entity entity) {
        final net.minecraft.server.v1_8_R3.Entity handle = ((CraftEntity)entity).getHandle();
        final NBTTagCompound nbtTagCompound = new NBTTagCompound();
        handle.c(nbtTagCompound);
        nbtTagCompound.setByte("Invulnerable", (byte)1);
        handle.f(nbtTagCompound);
    }
    
    @Override
    public void sendTablist(final Player player, final List<String> elements, final List<String> elements2) {
        final PacketPlayOutPlayerListHeaderFooter packetPlayOutPlayerListHeaderFooter = new PacketPlayOutPlayerListHeaderFooter();
        try {
            final Field declaredField = packetPlayOutPlayerListHeaderFooter.getClass().getDeclaredField("a");
            declaredField.setAccessible(true);
            declaredField.set(packetPlayOutPlayerListHeaderFooter, IChatBaseComponent.ChatSerializer.a("{text: '" + PlaceholderManager.setPlaceholders(player, String.join("\n", elements)) + "'}"));
            declaredField.setAccessible(false);
            final Field declaredField2 = packetPlayOutPlayerListHeaderFooter.getClass().getDeclaredField("b");
            declaredField2.setAccessible(true);
            declaredField2.set(packetPlayOutPlayerListHeaderFooter, IChatBaseComponent.ChatSerializer.a("{text: '" + PlaceholderManager.setPlaceholders(player, String.join("\n", elements2)) + "'}"));
            declaredField2.setAccessible(false);
        }
        catch (NoSuchFieldException | IllegalAccessException ex2) {
            CustomError.print(ex2, this.getClass(), Collections.singletonList("Can't set TabList Header or Footer"));
        }
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packetPlayOutPlayerListHeaderFooter);
    }
    
    @Override
    public void sendTitle(final Player player, final TitleType titleType, final String s, final int n, final int n2, final int n3) {
        final PlayerConnection playerConnection = ((CraftPlayer)player).getHandle().playerConnection;
        playerConnection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.valueOf(titleType.name()), IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + PlaceholderManager.setPlaceholders(player, s) + "\"}")));
        playerConnection.sendPacket((Packet)new PacketPlayOutTitle(n, n2, n3));
    }
    
    @Override
    public void sendActionBar(final Player player, final String s) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + PlaceholderManager.setPlaceholders(player, s) + "\"}"), (byte)2));
    }
    
    @Override
    public void fixPing(final Player player) {
        final EntityPlayer handle = ((CraftPlayer)player).getHandle();
        try {
            handle.getClass().getDeclaredField("ping").set(handle, 50);
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't modify player's ping"));
        }
    }
    
    @Override
    public void sendBorder(final Player player, final BorderColor borderColor, final double n, final double n2, final double size) {
        if (borderColor == null) {
            return;
        }
        final WorldBorder worldBorder = new WorldBorder();
        worldBorder.setCenter(n, n2);
        worldBorder.setSize(size);
        worldBorder.setWarningDistance(0);
        final EntityPlayer handle = ((CraftPlayer)player).getHandle();
        switch (borderColor) {
            case BLUE: {
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE));
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER));
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS));
                break;
            }
            case RED: {
                worldBorder.transitionSizeBetween(size, size - 1.0, 20000000L);
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE));
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER));
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS));
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.LERP_SIZE));
                break;
            }
            case GREEN: {
                worldBorder.transitionSizeBetween(size - 0.2, size, 20000000L);
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE));
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER));
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS));
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.LERP_SIZE));
                break;
            }
        }
    }
    
    @Override
    public void setAbsorptionHearts(final Player player, final int n) {
        ((CraftPlayer)player).getHandle().setAbsorptionHearts((float)n);
    }
    
    @Override
    public int getAbsorptionHearts(final Player player) {
        return (int)((CraftPlayer)player).getHandle().getAbsorptionHearts();
    }
    
    @Override
    public void refreshArmor(final Player player) {
        final EntityEquipableNMS entityEquipableNMS = new EntityEquipableNMS(((CraftEntity)player).getHandle(), player);
        final PlayerInventory inventory = player.getInventory();
        if (inventory.getHelmet() != null) {
            entityEquipableNMS.set(EquipmentSlot.HEAD, new ItemBuilder(inventory.getHelmet()));
        }
        if (inventory.getChestplate() != null) {
            entityEquipableNMS.set(EquipmentSlot.CHEST, new ItemBuilder(inventory.getChestplate()));
        }
        if (inventory.getLeggings() != null) {
            entityEquipableNMS.set(EquipmentSlot.LEGS, new ItemBuilder(inventory.getLeggings()));
        }
        if (inventory.getBoots() != null) {
            entityEquipableNMS.set(EquipmentSlot.FEET, new ItemBuilder(inventory.getBoots()));
        }
    }
    
    @Override
    public void setSkullOwner(final ItemStack itemStack, final String s) {
        final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        try {
            skullMeta.setOwner(Bukkit.getOfflinePlayer(UUID.fromString(s)).getName());
        }
        catch (Exception ex) {
            skullMeta.setOwner(s);
        }
    }
    
    @Override
    public ItemMeta setUnbreakable(final ItemMeta itemMeta, final boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(unbreakable);
        return itemMeta;
    }
    
    @Override
    public void registerCommand(final Command command) {
        ((CraftServer) API.getPlugin().getServer()).getCommandMap().register(command.getName(), command);
    }
    
    @Override
    public boolean interactFromOffHand(final PlayerInteractEvent playerInteractEvent) {
        return false;
    }
    
    @Override
    public void addMetaData(final ItemStack itemStack, final String s, final String s2) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsCopy.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
            nmsCopy.setTag(tag);
        }
        tag.setString(s, s2);
        itemStack.setItemMeta(CraftItemStack.getItemMeta(nmsCopy));
    }
    
    @Override
    public boolean hasMetaData(final ItemStack itemStack, final String s) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        if (nmsCopy == null) {
            return false;
        }
        final NBTTagCompound tag = nmsCopy.getTag();
        return tag != null && tag.hasKey(s);
    }
    
    @Override
    public String getMetaData(final ItemStack itemStack, final String s) {
        final NBTTagCompound tag = CraftItemStack.asNMSCopy(itemStack).getTag();
        if (tag == null) {
            return "";
        }
        return tag.getString(s);
    }
    
    @Override
    public void removeMetaData(final ItemStack itemStack, final String s) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsCopy.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
            nmsCopy.setTag(tag);
        }
        tag.remove(s);
        itemStack.setItemMeta(CraftItemStack.getItemMeta(nmsCopy));
    }
    
    @Override
    public String getItemName(final ItemStack itemStack) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        return nmsCopy.getItem().a(nmsCopy);
    }
    
    @Override
    public void spawnParticle(final Player player, final String s, final int n) {
        player.playEffect(player.getLocation(), Effect.valueOf(s), n);
    }
    
    @Override
    public void sendBossBar(final Player player, final String s, final float n, final BossBarColor bossBarColor, final BossBarStyle bossBarStyle) {
    }
    
    @Override
    public void updateBossBar(final Player player, final String s, final float n) {
    }
    
    @Override
    public void updateBossBarTitle(final Player player, final String s) {
    }
    
    @Override
    public void updateBossBarProgress(final Player player, final float n) {
    }
    
    @Override
    public void removeBossBar(final Player player) {
    }
    
    @Override
    public boolean hasBossBar(final Player player) {
        return false;
    }
    
    @Override
    public void patchProtocolLibRespawn(final Player player) {
        final WorldServer handle = ((CraftWorld)player.getWorld()).getHandle();
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutRespawn(handle.dimension, handle.worldData.getDifficulty(), handle.worldData.getType(), handle.worldData.getGameType()));
    }
    
    @Override
    public void createPlayerAura(final Player player, final String s, final int n) {}
    
    @Override
    public boolean playerHasAura(final Player player) {
        return this.auraTaskCache.containsKey(player.getUniqueId());
    }
    
    @Override
    public void removePlayerAura(final Player player) {
        if (!this.auraTaskCache.containsKey(player.getUniqueId())) {
            return;
        }
        this.auraTaskCache.get(player.getUniqueId()).cancel();
        for (final CraftArmorStand craftArmorStand : this.craftArmorStandCache.get(player.getUniqueId())) {
            craftArmorStand.remove();
            for (final Player player2 : Bukkit.getOnlinePlayers()) {
                if (player != player2) {
                    ((CraftPlayer)player2).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutEntityDestroy(new int[] { craftArmorStand.getHandle().getId() }));
                }
            }
        }
    }
    
    @Override
    public void removeAllPlayerAura() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (this.auraTaskCache.containsKey(player.getUniqueId())) {
                this.auraTaskCache.get(player.getUniqueId()).cancel();
            }
        }
        final Iterator<Map.Entry<UUID, List<CraftArmorStand>>> iterator2 = this.craftArmorStandCache.entrySet().iterator();
        while (iterator2.hasNext()) {
            for (final CraftArmorStand craftArmorStand : iterator2.next().getValue()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet) new PacketPlayOutEntityDestroy(new int[]{craftArmorStand.getHandle().getId()}));
                }
            }
        }
    }
    
    @Override
    public void createHologram(final Player player, final String str, final Location location, final List<String> list) {
        final Location add = location.clone().add(0.0, 0.23 * list.size() - 1.97, 0.0);
        final ArrayList<EntityArmorStand> value = new ArrayList<EntityArmorStand>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            value.add(this.addLine(player, add, iterator.next()));
            add.add(0.0, -0.23, 0.0);
        }
        this.armorStandMap.put(player.getUniqueId() + str, value);
    }
    
    public EntityArmorStand addLine(final Player player, final Location location, final String s) {
        final EntityArmorStand entityArmorStand = new EntityArmorStand((World)((CraftWorld)location.getWorld()).getHandle());
        entityArmorStand.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        entityArmorStand.setInvisible(true);
        entityArmorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', s));
        entityArmorStand.setCustomNameVisible(true);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutSpawnEntity((net.minecraft.server.v1_8_R3.Entity)entityArmorStand, 78));
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutEntityMetadata(entityArmorStand.getId(), entityArmorStand.getDataWatcher(), false));
        return entityArmorStand;
    }
    
    @Override
    public void removeHologram(final Player player, final String s) {
        if (!this.armorStandMap.containsKey(player.getUniqueId() + s)) {
            return;
        }
        for (final EntityArmorStand entityArmorStand : this.armorStandMap.get(player.getUniqueId() + s)) {
            entityArmorStand.getBukkitEntity().remove();
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutEntityDestroy(new int[] { entityArmorStand.getId() }));
        }
    }
    
    @Override
    public void removeALlHolograms() {
        final Iterator<Map.Entry<String, List<EntityArmorStand>>> iterator = this.armorStandMap.entrySet().iterator();
        while (iterator.hasNext()) {
            for (final EntityArmorStand entityArmorStand : iterator.next().getValue()) {
                entityArmorStand.getBukkitEntity().remove();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet) new PacketPlayOutEntityDestroy(new int[]{entityArmorStand.getId()}));
                }
            }
        }
    }
    
    @Override
    public ItemStack modifyItemDamage(final Material material, final int n) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(new ItemStack(material));
        NBTTagCompound tag = nmsCopy.getTag();
        if (tag == null) {
            nmsCopy.setTag(new NBTTagCompound());
            tag = nmsCopy.getTag();
        }
        final NBTTagList list = new NBTTagList();
        final NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.set("AttributeName", (NBTBase)new NBTTagString("generic.attackDamage"));
        nbtTagCompound.set("Name", (NBTBase)new NBTTagString("generic.attackDamage"));
        nbtTagCompound.set("Amount", (NBTBase)new NBTTagInt(n));
        nbtTagCompound.set("Operation", (NBTBase)new NBTTagInt(0));
        nbtTagCompound.set("UUIDLeast", (NBTBase)new NBTTagInt(894654));
        nbtTagCompound.set("UUIDMost", (NBTBase)new NBTTagInt(2872));
        list.add((NBTBase)nbtTagCompound);
        tag.set("AttributeModifiers", (NBTBase)list);
        nmsCopy.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsCopy);
    }
    
    @Override
    public void injectPlayer(final Player player) {
        ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel.pipeline().addBefore("packet_handler", player.getName(), (ChannelHandler)new ChannelDuplexHandler() {
            public void channelRead(final ChannelHandlerContext channelHandlerContext, final Object o) {
                if (o instanceof PacketPlayInWindowClick) {
                    final PacketPlayInWindowClick packetPlayInWindowClick = (PacketPlayInWindowClick)o;
                    if (packetPlayInWindowClick.e() != null && AnvilUtil.uuidList.contains(player.getUniqueId())) {
                        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutSetSlot(0, 0, packetPlayInWindowClick.e()));
                        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutExperience(player.getExp(), player.getLevel(), player.getLevel()));
                        if (packetPlayInWindowClick.b() == 2) {
                            Bukkit.getPluginManager().callEvent(new AnvilClickEvent(player, CraftItemStack.asBukkitCopy(packetPlayInWindowClick.e()).getItemMeta().getDisplayName()));
                            AnvilUtil.uuidList.remove(player.getUniqueId());
                            player.closeInventory();
                        }
                    }
                }
                try {
                    super.channelRead(channelHandlerContext, o);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            
            public void write(final ChannelHandlerContext channelHandlerContext, final Object o, final ChannelPromise channelPromise) {
                try {
                    super.write(channelHandlerContext, o, channelPromise);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    
    @Override
    public void uninjectPlayer(final Player player) {
        final Channel channel = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }
    
    @Override
    public void openAnvilGUI(final Player player, final ItemStack itemStack, final String displayName, final String s) {
        final EntityPlayer handle = ((CraftPlayer)player).getHandle();
        final AnvilContainer anvilContainer = new AnvilContainer((EntityHuman)handle);
        ((Container)anvilContainer).checkReachable = false;
        handle.activeContainer = handle.defaultContainer;
        handle.playerConnection.sendPacket((Packet)new PacketPlayOutOpenWindow(((Container)anvilContainer).windowId, "minecraft:anvil", (IChatBaseComponent)new ChatMessage(s, new Object[0])));
        handle.playerConnection.sendPacket((Packet)new PacketPlayOutSetSlot(0, 0, CraftItemStack.asNMSCopy(new ItemBuilder(itemStack).setDisplayName(displayName).build())));
        AnvilUtil.uuidList.add(player.getUniqueId());
    }
    
    private class AnvilContainer extends ContainerAnvil
    {
        public AnvilContainer(final EntityHuman entityHuman) {
            super(entityHuman.inventory, entityHuman.world, new BlockPosition(0, 0, 0), entityHuman);
        }
        
        public boolean a(final EntityHuman entityHuman) {
            return true;
        }
        
        public void b(final EntityHuman entityHuman) {
        }
    }
}
