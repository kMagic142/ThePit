package ro.kmagic.libapi.placeholder;

import ro.kmagic.libapi.API;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class RefreshablePlaceholder
{
    private final String placeholder;
    private String value;
    private BukkitTask task;
    
    public RefreshablePlaceholder(final String placeholder, final int n) {
        this.placeholder = placeholder;
        this.value = this.refresh();
        if (n == 0) {
            return;
        }
        this.task = new BukkitRunnable() {
            public void run() {
                RefreshablePlaceholder.this.value = RefreshablePlaceholder.this.refresh();
            }
        }.runTaskTimerAsynchronously(API.getPlugin(), 0L, n);
    }
    
    public String getPlaceholder() {
        return this.placeholder;
    }
    
    public String getValue() {
        if (this.value != null) {
            return this.value;
        }
        return "";
    }
    
    public void closeRefresh() {
        if (this.task != null) {
            this.task.cancel();
        }
    }
    
    public abstract String refresh();
}
