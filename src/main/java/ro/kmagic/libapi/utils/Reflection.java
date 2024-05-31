package ro.kmagic.libapi.utils;

import java.util.Collections;

import org.bukkit.entity.Player;
import ro.kmagic.libapi.API;

public class Reflection
{
    protected Class<?> getNMSClass(final String str) {
        try {
            return Class.forName("net.minecraft.server." + API.getVersion() + "." + str);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected Class<?> getOBC(final String str) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + API.getVersion() + "." + str);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected void sendPacket(final Player obj, final Object o) {
        try {
            final Object invoke = obj.getClass().getMethod("getHandle", new Class[0]).invoke(obj);
            final Object value = invoke.getClass().getField("playerConnection").get(invoke);
            value.getClass().getMethod("sendPacket", this.getNMSClass("Packet")).invoke(value, o);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void printReflectionError(final Exception ex) {
        CustomError.print(ex, this.getClass(), Collections.singletonList("Couldn't get Class Information"));
    }
}
