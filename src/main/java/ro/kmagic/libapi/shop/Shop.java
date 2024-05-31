package ro.kmagic.libapi.shop;

import ro.kmagic.libapi.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public class Shop
{
    private String title;
    private int size;
    private List<ShopItem> items;
    private List<ItemStack> placeholders;
    
    public Shop(final String s, final int n) {
    }
    
    public void open(final Player player) {
        final GUI gui = new GUI(player, this.size, ChatColor.translateAlternateColorCodes('&', this.title));
    }
}
