package ro.kmagic.libapi.gui;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ro.kmagic.libapi.placeholder.PlaceholderManager;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import ro.kmagic.libapi.exceptions.SlotOccupiedException;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ro.kmagic.libapi.exceptions.InitializationException;
import ro.kmagic.libapi.API;
import org.bukkit.event.inventory.InventoryType;
import java.util.HashMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GUI implements Listener
{
    private final Player player;
    private String title;
    private Inventory inventory;
    private final HashMap<Integer, GuiItem> items;
    private boolean closeable;
    private boolean opened;
    private boolean externalInteract;
    private boolean eventsRegistered;
    
    public GUI(final Player player, final InventoryType inventoryType, final String title) {
        if (API.getPlugin() == null) {
            throw new InitializationException("The GUI plugin must not be null!");
        }
        this.title = title;
        this.player = player;
        this.inventory = Bukkit.createInventory(player, inventoryType, ChatColor.translateAlternateColorCodes('&', title));
        this.items = new HashMap<Integer, GuiItem>();
        this.closeable = true;
        this.opened = false;
        this.externalInteract = false;
        this.registerEvent(API.getPlugin());
    }
    
    public GUI(final Player player, final int n, final String title) {
        final Plugin plugin = API.getPlugin();
        if (plugin == null) {
            throw new InitializationException("The GUI plugin must not be null!");
        }
        this.title = title;
        this.player = player;
        this.inventory = Bukkit.createInventory(player, n, ChatColor.translateAlternateColorCodes('&', title));
        this.items = new HashMap<Integer, GuiItem>();
        this.closeable = true;
        this.opened = false;
        this.externalInteract = false;
        this.registerEvent(plugin);
    }
    
    public Player getOwner() {
        return this.player;
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public boolean isCloseable() {
        return this.closeable;
    }
    
    public boolean isExternalInteract() {
        return this.externalInteract;
    }
    
    public boolean isOppened() {
        return this.opened;
    }
    
    public void setCloseable(final boolean closeable) {
        this.closeable = closeable;
    }
    
    public void setTitle(final String s) {
        this.title = ChatColor.translateAlternateColorCodes('&', s);
        this.inventory = Bukkit.createInventory(this.player, this.inventory.getSize(), this.title);
        if (this.opened) {
            this.open(this.player);
        }
    }
    
    public void setTitle(final String title, final boolean b) {
        if (!b) {
            this.setTitle(title);
            return;
        }
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.inventory = Bukkit.createInventory(this.player, this.inventory.getType(), title);
        if (this.opened) {
            this.open();
        }
    }
    
    public void open() {
        if (!this.eventsRegistered) {
            this.registerEvent(API.getPlugin());
        }
        this.setItems();
        this.refreshItems();
        this.opened = true;
        this.player.openInventory(this.inventory);
    }
    
    public void open(final Player player) {
        if (!this.eventsRegistered) {
            this.registerEvent(API.getPlugin());
        }
        this.setItems();
        this.opened = true;
        player.openInventory(this.inventory);
    }
    
    public void close() {
        if (this.player.getOpenInventory().getTopInventory().equals(this.inventory)) {
            this.opened = false;
            this.player.closeInventory();
            this.player.updateInventory();
        }
    }
    
    public void definitivelyClose() {
        if (this.player.getOpenInventory().getTopInventory().equals(this.inventory)) {
            this.opened = false;
            this.player.closeInventory();
        }
        this.unregisterEvent();
    }
    
    public void setItems() {
        for (final int intValue : this.items.keySet()) {
            this.inventory.setItem(intValue, this.items.get(intValue).getItemStack());
        }
    }
    
    public GuiItem getItem(final int i) {
        return this.items.get(i);
    }
    
    public void addItem(final GuiItem value) {
        if (this.getItem(value.getSlot()) != null) {
            throw new SlotOccupiedException();
        }
        this.items.put(value.getSlot(), value);
    }
    
    public void setExternalInteract(final boolean externalInteract) {
        this.externalInteract = externalInteract;
    }
    
    private void registerEvent(final Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.eventsRegistered = true;
    }
    
    private void unregisterEvent() {
        InventoryClickEvent.getHandlerList().unregister(this);
        InventoryCloseEvent.getHandlerList().unregister(this);
        this.eventsRegistered = false;
    }
    
    public void refreshItems() {
        for (final int intValue : this.items.keySet()) {
            final ItemStack clone = this.items.get(intValue).getItemStack().clone();
            if (PlaceholderManager.hasPlaceholders(clone)) {
                this.inventory.setItem(intValue, PlaceholderManager.setPlaceholders(this.player, clone));
            }
        }
    }
    
    @EventHandler
    private void onClick(final InventoryClickEvent inventoryClickEvent) {
        if (this.inventory.equals(inventoryClickEvent.getView().getTopInventory())) {
            boolean b = this.inventory.equals(inventoryClickEvent.getClickedInventory()) || !this.externalInteract;
            if (b) {
                inventoryClickEvent.setCancelled(true);
            }
            if (this.player == null) {
                return;
            }
            if (!inventoryClickEvent.getWhoClicked().getUniqueId().equals(this.player.getUniqueId())) {
                return;
            }
            if (inventoryClickEvent.getCurrentItem() != null && inventoryClickEvent.getCurrentItem().getType() != Material.AIR) {
                final GuiItem item = this.getItem(inventoryClickEvent.getSlot());
                if (item == null) {
                    return;
                }
                item.executeClickAction(this, inventoryClickEvent.getClick());
            }
        }
    }
    
    @EventHandler
    private void onClose(final InventoryCloseEvent inventoryCloseEvent) {
        if (inventoryCloseEvent.getInventory().equals(this.inventory)) {
            if (this.player == null) {
                return;
            }
            if (!inventoryCloseEvent.getPlayer().getUniqueId().equals(this.player.getUniqueId())) {
                return;
            }
            if (!this.closeable && this.opened) {
                new BukkitRunnable() {
                    public void run() {
                        GUI.this.player.openInventory(GUI.this.inventory);
                    }
                }.runTaskLater(API.getPlugin(), 5L);
            }
        }
    }
}
