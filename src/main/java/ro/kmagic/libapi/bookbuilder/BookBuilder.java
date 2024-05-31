package ro.kmagic.libapi.bookbuilder;

import org.bukkit.Bukkit;
import java.lang.reflect.Field;
import org.bukkit.inventory.ItemStack;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import ro.kmagic.libapi.versionsupport.materials.Materials;
import ro.kmagic.libapi.item.ItemBuilder;

public class BookBuilder
{
    private final ItemBuilder itemBuilder;
    
    public BookBuilder() {
        this.itemBuilder = Materials.WRITTEN_BOOK.getItem();
    }
    
    public BookBuilder addPage(final String... array) {
        final StringBuilder sb = new StringBuilder();
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(ChatColor.translateAlternateColorCodes('&', array[i])).append("\n");
        }
        this.itemBuilder.book().addPages(sb.toString());
        return this;
    }
    
    public void openBook(final Player obj) {
        final int heldItemSlot = obj.getInventory().getHeldItemSlot();
        final ItemStack item = obj.getInventory().getItem(heldItemSlot);
        obj.getInventory().setItem(heldItemSlot, this.itemBuilder.build());
        final ByteBuf buffer = Unpooled.buffer(256);
        buffer.setByte(0, 0);
        buffer.writerIndex(1);
        try {
            final Object invoke = obj.getClass().getMethod("getHandle", new Class[0]).invoke(obj);
            final Field declaredField = invoke.getClass().getDeclaredField("playerConnection");
            declaredField.setAccessible(true);
            declaredField.getType().getDeclaredMethod("sendPacket", getNMSClass("Packet")).invoke(declaredField.get(invoke), getNMSClass("PacketPlayOutCustomPayload").getConstructor(String.class, getNMSClass("PacketDataSerializer")).newInstance("MC|BOpen", getNMSClass("PacketDataSerializer").getConstructor(ByteBuf.class).newInstance(buffer)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        obj.getInventory().setItem(heldItemSlot, item);
    }
    
    private static Class<?> getNMSClass(final String str) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + str);
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
