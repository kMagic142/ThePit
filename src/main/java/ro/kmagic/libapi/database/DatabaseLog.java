package ro.kmagic.libapi.database;

import org.bukkit.event.EventHandler;
import ro.kmagic.libapi.utils.CustomError;
import java.util.Arrays;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Date;
import ro.kmagic.libapi.events.misc.DatabaseChangeEvent;
import ro.kmagic.libapi.API;
import java.text.SimpleDateFormat;
import java.io.File;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import org.bukkit.event.Listener;

public class DatabaseLog implements Listener
{
    private final DateTimeFormatter dateTimeFormatter;
    private final DateFormat dateFormat;
    private File file;
    private final String pluginName;
    
    public DatabaseLog() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.file = null;
        this.pluginName = API.getPlugin().getDescription().getName();
    }
    
    @EventHandler
    private void onDatabaseChange(final DatabaseChangeEvent databaseChangeEvent) {
        final String string = "mysql-" + this.dateFormat.format(new Date());
        new File("plugins/" + this.pluginName + "/Logs").mkdirs();
        if (this.file == null || !this.file.getName().contains(string)) {
            this.file = new File("plugins/" + this.pluginName + "/Logs", string + ".txt");
        }
        final LocalDateTime now = LocalDateTime.now();
        try {
            final FileWriter fileWriter = new FileWriter(this.file, true);
            fileWriter.write("[" + this.dateTimeFormatter.format(now) + "] Database Action: " + databaseChangeEvent.getDatabaseAction() + " | Table: " + databaseChangeEvent.getTable() + " | Column: " + databaseChangeEvent.getColumn());
            fileWriter.write("\r\n");
            fileWriter.close();
        }
        catch (Exception ex) {
            CustomError.print(ex, this.getClass(), Arrays.asList("Can't write in MySQL Log!", "Probably '/Logs' directory or file doesn't exist"));
        }
    }
}
