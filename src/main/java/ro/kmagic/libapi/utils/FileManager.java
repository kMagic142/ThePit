package ro.kmagic.libapi.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.versionsupport.materials.Materials;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileManager
{
    private static UUID randomUUID;
    private YamlConfiguration yml;
    private File file;
    private String name;
    private String fullPath;
    public List<String> commentPath;
    public HashMap<String, List<String>> comments;
    public HashMap<String, Integer> commentLine;
    
    public FileManager(final String s, final String s2, final boolean b) {
        this.commentPath = new ArrayList<String>();
        this.comments = new HashMap<String, List<String>>();
        this.commentLine = new HashMap<String, Integer>();
        this.initializeLegacy(s, s2);
    }
    
    public FileManager(final String s, final String s2) {
        this.commentPath = new ArrayList<String>();
        this.comments = new HashMap<String, List<String>>();
        this.commentLine = new HashMap<String, Integer>();
        this.initialize(s, s2);
    }
    
    public FileManager(final String s) {
        this.commentPath = new ArrayList<String>();
        this.comments = new HashMap<String, List<String>>();
        this.commentLine = new HashMap<String, Integer>();
        this.initialize(s, "");
    }
    
    private void initialize(final String str, String string) {
        string = "plugins/" + API.getPlugin().getDescription().getName() + "/" + string;
        new File(string).mkdirs();
        this.file = new File(string, str + ".yml");
        this.name = str;
        this.fullPath = "plugins/" + API.getPlugin().getDescription().getName() + "/" + string;
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            }
            catch (IOException ex) {
                CustomError.print(ex, this.getClass(), Arrays.asList("Can't create file: " + str + ".yml", "with path: " + string));
            }
        }
        this.yml = YamlConfiguration.loadConfiguration(this.file);
    }
    
    private void initializeLegacy(final String str, String s) {
        s = s;
        new File(s).mkdirs();
        this.file = new File(s, str + ".yml");
        this.name = str;
        this.fullPath = s;
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            }
            catch (IOException ex) {
                CustomError.print(ex, this.getClass(), Arrays.asList("Can't create file: " + str + ".yml", "with path: " + s));
            }
        }
        this.yml = YamlConfiguration.loadConfiguration(this.file);
    }
    
    public void reload() {
        try {
            this.yml.load(this.file);
            this.yml = YamlConfiguration.loadConfiguration(this.file);
        }
        catch (IOException | InvalidConfigurationException ex2) {
            CustomError.print(ex2, this.getClass(), Arrays.asList("Can't load file: " + this.file.getName(), "with path: " + this.file.getPath()));
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getFullPath() {
        return this.fullPath;
    }
    
    public static boolean exists(final String str, final String str2) {
        return new File("plugins/" + API.getPlugin().getDescription().getName() + "/" + str2 + "/", str + ".yml").exists();
    }
    
    public static File getFile(final String str, final String str2) {
        return new File("plugins/" + API.getPlugin().getDescription().getName() + "/" + str2 + "/", str + ".yml");
    }
    
    public void save() {
        try {
            this.yml.save(this.file);
        }
        catch (IOException ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't save file: " + this.file.getName()));
        }
    }
    
    public void set(final String s, final Object o) {
        this.yml.set(s, o);
        this.save();
    }
    
    public Object get(final String s) {
        return this.yml.get(s);
    }
    
    public void setSerializeItemStack(final String s, final ItemStack itemStack) {
    }
    
    public void setItemStack(final String str, final ItemStack itemStack) {
        this.yml.set(str + ".Material", itemStack.getType().name());
        if (itemStack.getAmount() > 1) {
            this.yml.set(str + ".Amount", itemStack.getAmount());
        }
        if (itemStack.getDurability() != itemStack.getType().getMaxDurability()) {
            this.yml.set(str + ".Durability", itemStack.getDurability());
        }
        if (API.getVersionSupport().hasMetaData(itemStack, "SkullPlayer")) {
            this.yml.set(str + ".SkullPlayer", API.getVersionSupport().getMetaData(itemStack, "SkullPlayer"));
        }
        if (API.getVersionSupport().hasMetaData(itemStack, "SkullSkin")) {
            this.yml.set(str + ".SkullSkin", API.getVersionSupport().getMetaData(itemStack, "SkullSkin"));
        }
        if (itemStack.hasItemMeta()) {
            final ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta.hasDisplayName()) {
                this.yml.set(str + ".DisplayName", UtilMethods.decolorize(itemMeta.getDisplayName()));
            }
            if (itemMeta.hasLore()) {
                this.yml.set(str + ".Lore", UtilMethods.decolorize(itemMeta.getLore()));
            }
            if (itemMeta.hasEnchants()) {
                for (final Enchantment enchantment : itemMeta.getEnchants().keySet()) {
                    this.yml.set(str + ".Enchantments." + enchantment.getName(), itemMeta.getEnchants().get(enchantment));
                }
            }
            final ArrayList<String> list = new ArrayList<String>();
            for (final ItemFlag itemFlag : ItemFlag.values()) {
                if (itemMeta.hasItemFlag(itemFlag)) {
                    list.add(itemFlag.name());
                }
            }
            if (list.size() > 0) {
                this.yml.set(str + ".ItemFlags", list);
            }
        }
        this.save();
    }
    
    public ItemStack getItemStack(final String s) {
        if (!this.contains(s)) {
            return null;
        }
        final ItemStack itemStack = new ItemStack(Material.valueOf(this.getString(s + ".Material")));
        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (this.contains(s + ".Amount")) {
            itemStack.setAmount(this.getInt(s + ".Amount"));
        }
        if (this.contains(s + ".Durability")) {
            itemStack.setDurability((short)this.getInt(s + ".Durability"));
        }
        if (this.contains(s + ".DisplayName")) {
            itemMeta.setDisplayName(UtilMethods.colorize(this.getString(s + ".DisplayName")));
        }
        if (this.contains(s + ".Lore")) {
            itemMeta.setLore(UtilMethods.colorize(this.getStringList(s + ".Lore")));
        }
        if (this.contains(s + ".Enchantments")) {
            for (final String str : this.getConfigurationSection(s + ".Enchantments").getKeys(false)) {
                itemMeta.addEnchant(Enchantment.getByName(str), this.getInt(s + ".Enchantments." + str), true);
            }
        }
        if (this.contains(s + ".ItemFlags")) {
            final Iterator<String> iterator2 = this.getStringList(s + ".ItemFlags").iterator();
            while (iterator2.hasNext()) {
                itemMeta.addItemFlags(ItemFlag.valueOf(iterator2.next()));
            }
        }
        itemStack.setItemMeta(itemMeta);
        if (this.contains(s + ".SkullPlayer")) {
            final String string = this.getString(s + ".SkullPlayer");
            API.getVersionSupport().setSkullOwner(itemStack, string);
            API.getVersionSupport().addMetaData(itemStack, "SkullPlayer", string);
        }
        if (this.contains(s + ".SkullSkin")) {
            final String string2 = this.getString(s + ".SkullSkin");
            final String name = itemStack.getType().name();
            if (!name.equals("PLAYER_HEAD") && !name.equals("SKULL_ITEM")) {
                return itemStack;
            }
            if (string2 == null) {
                return itemStack;
            }
            final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
            value.getProperties().put("textures", new Property("textures", new String(Base64.getEncoder().encode((String.format("{textures:{SKIN:{url:\"%s\"}}}", string2).getBytes())))));
            try {
                final Field declaredField = itemMeta.getClass().getDeclaredField("profile");
                declaredField.setAccessible(true);
                declaredField.set(itemMeta, value);
            }
            catch (Exception ex) {
                CustomError.print(ex, this.getClass(), Collections.singletonList("API: Can't set Skull Skin"));
            }
            itemStack.setItemMeta(itemMeta);
            API.getVersionSupport().addMetaData(itemStack, "SkullSkin", string2);
        }
        return itemStack;
    }
    
    public boolean contains(final String s) {
        return this.yml.contains(s);
    }
    
    public List<String> getStringList(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        for (String value : this.yml.getStringList(s)) {
            list.add(ChatColor.translateAlternateColorCodes('&', value));
        }
        return list;
    }
    
    public String getString(final String s) {
        return ChatColor.translateAlternateColorCodes('&', this.yml.getString(s));
    }
    
    public String getUncoloredString(final String s) {
        return this.yml.getString(s);
    }
    
    public double getDouble(final String s) {
        return this.yml.getDouble(s);
    }
    
    public void setLocation(final String s, final Location location) {
        this.yml.set(s + ".World", location.getWorld().getName());
        this.yml.set(s + ".X", location.getX());
        this.yml.set(s + ".Y", location.getY());
        this.yml.set(s + ".Z", location.getZ());
        this.yml.set(s + ".Yaw", location.getYaw());
        this.yml.set(s + ".Pitch", location.getPitch());
        this.save();
    }
    
    public Location getLocation(final String s) {
        return new Location(Bukkit.getWorld(this.getString(s + ".World")), this.getDouble(s + ".X"), this.getDouble(s + ".Y"), this.getDouble(s + ".Z"), (float)this.getDouble(s + ".Yaw"), (float)this.getDouble(s + ".Pitch"));
    }
    
    public List<?> getList(final String s) {
        return this.yml.getList(s);
    }
    
    public void addDefault(final String s, final Object o) {
        this.yml.addDefault(s, o);
    }
    
    public void copyDefaults() {
        this.yml.options().copyDefaults(true);
    }
    
    public boolean getBoolean(final String s) {
        return this.yml.getBoolean(s);
    }
    
    public int getInt(final String s) {
        return this.yml.getInt(s);
    }
    
    public long getLong(final String s) {
        return this.yml.getLong(s);
    }
    
    public ConfigurationSection getConfigurationSection(final String s) {
        return this.yml.getConfigurationSection(s);
    }
    
    public Object getObject(final String s) {
        return this.yml.get(s);
    }
    
    public Materials getMaterial(final String s) {
        return Materials.valueOf(this.yml.getString(s));
    }
    
    public Color getColor(final String s) {
        return this.yml.getColor(s);
    }
    
    public void addComment(final String key, final List<String> value) {
        this.commentPath.add(key);
        this.comments.put(key, value);
    }
    
    public void copyComments() {
        try {
            final List<String> allLines = Files.readAllLines(Paths.get(this.getFullPath()));
            for (final String s : this.commentPath) {
                s.split("\\.");
                boolean b = false;
                for (int i = 0; i <= allLines.size(); ++i) {
                    if (allLines.get(i).contains(s)) {
                        b = true;
                        break;
                    }
                    if (b) {}
                }
            }
        }
        catch (IOException ex) {
            CustomError.print(ex, this.getClass(), Collections.singletonList("Can't read file " + this.getName()));
        }
    }
    
    static {
        FileManager.randomUUID = UUID.fromString("eb38bdd9-a3d9-41c5-9f03-2273ed0e8bc7");
    }
}
