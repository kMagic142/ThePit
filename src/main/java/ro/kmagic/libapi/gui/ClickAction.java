package ro.kmagic.libapi.gui;

import java.util.Arrays;
import java.util.ArrayList;
import org.bukkit.event.inventory.ClickType;
import java.util.List;

public abstract class ClickAction
{
    private final List<ClickType> clickTypes;
    
    public ClickAction(final ClickType clickType) {
        (this.clickTypes = new ArrayList<ClickType>()).add(clickType);
    }
    
    public ClickAction() {
        this.clickTypes = new ArrayList<ClickType>(Arrays.asList(ClickType.values()));
    }
    
    public ClickAction(final List<ClickType> clickTypes) {
        this.clickTypes = clickTypes;
    }
    
    public abstract void onClick(final GuiItem p0, final GUI p1);
    
    public List<ClickType> getClickTypes() {
        return this.clickTypes;
    }
}
