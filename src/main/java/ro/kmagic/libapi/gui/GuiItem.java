package ro.kmagic.libapi.gui;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public class GuiItem
{
    private ItemStack itemStack;
    private final int slot;
    private final List<ClickAction> clickActions;
    
    public GuiItem(final ItemStack itemStack, final int slot) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.clickActions = new ArrayList<ClickAction>();
    }
    
    public GuiItem(final ItemStack itemStack, final int slot, final ClickAction clickAction) {
        this.itemStack = itemStack;
        this.slot = slot;
        (this.clickActions = new ArrayList<ClickAction>()).add(clickAction);
    }
    
    public GuiItem(final ItemStack itemStack, final int slot, final List<ClickAction> clickActions) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.clickActions = clickActions;
    }
    
    public GuiItem addClickAction(final ClickAction clickAction) {
        this.clickActions.add(clickAction);
        return this;
    }
    
    public List<ClickAction> getActions() {
        return this.clickActions;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    public void editItemStack(final Inventory inventory, final ItemStack itemStack) {
        this.itemStack = itemStack;
        inventory.setItem(this.slot, itemStack);
    }
    
    public void editItemStack(final ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    
    public void executeClickAction(final GUI gui, final ClickType clickType) {
        if (this.clickActions.size() == 0) {
            return;
        }
        for (final ClickAction clickAction : this.clickActions) {
            if (clickAction.getClickTypes().contains(clickType)) {
                clickAction.onClick(this, gui);
            }
        }
    }
    
    public GuiItem clone() {
        return new GuiItem(this.itemStack.clone(), this.slot, this.clickActions);
    }
}
