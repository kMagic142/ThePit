package ro.kmagic.libapi.shop;

import org.bukkit.entity.Player;
import ro.kmagic.libapi.gui.GuiItem;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public abstract class ShopItem
{
    private String identifier;
    private ItemStack display;
    private int slot;
    private int price;
    private List<GuiItem> confirmGuiItems;
    
    public abstract void onBuy(final Player p0);
}
