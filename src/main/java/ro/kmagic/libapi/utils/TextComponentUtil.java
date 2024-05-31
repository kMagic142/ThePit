package ro.kmagic.libapi.utils;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;

public class TextComponentUtil
{
    public static void sendTextComponent(final Player player, final String s, final String s2, final String s3, final ClickEvent.Action action) {
        final TextComponent textComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', s));
        textComponent.setClickEvent(new ClickEvent(action, s2));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', s3)).create()));
        player.spigot().sendMessage(textComponent);
    }
    
    public static void sendTextComponent(final Player player, final String s, final String s2, final ClickEvent.Action action) {
        final TextComponent textComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', s));
        textComponent.setClickEvent(new ClickEvent(action, s2));
        player.spigot().sendMessage(textComponent);
    }
    
    public static void sendTextComponent(final Player player, final String s, final String s2) {
        final TextComponent textComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', s));
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, s2));
        player.spigot().sendMessage(textComponent);
    }
    
    public static void sendTextComponentHover(final Player player, final String s, final String s2) {
        final TextComponent textComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', s));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', s2)).create()));
        player.spigot().sendMessage(textComponent);
    }
}
