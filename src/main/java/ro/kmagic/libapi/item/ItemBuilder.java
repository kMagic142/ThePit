package ro.kmagic.libapi.item;

import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.FireworkEffect;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.block.banner.Pattern;
import java.util.List;
import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import java.lang.reflect.Field;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.GameProfile;
import ro.kmagic.libapi.API;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import java.util.UUID;

public class ItemBuilder
{
    private static UUID randomUUID;
    private ItemStack customItem;
    private ItemFlags flag;
    private ItemSkull skull;
    private ItemMetaData metaData;
    private ItemEnchantment enchantment;
    private ItemBanner banner;
    private ItemPotion potion;
    private ItemBook book;
    private ItemFirework firework;
    private ItemLeatherArmor leatherArmor;
    private ItemMap map;
    private ItemSpawnEgg spawnEgg;
    private final ItemBuilder item;
    
    private ItemMeta getItemMeta() {
        return this.customItem.getItemMeta();
    }
    
    private BannerMeta getBannerMeta() {
        return (BannerMeta)this.getItemMeta();
    }
    
    private BookMeta getBookMeta() {
        return (BookMeta)this.getItemMeta();
    }
    
    private FireworkMeta getFireworkMeta() {
        return (FireworkMeta)this.getItemMeta();
    }
    
    private FireworkEffectMeta getFireworkEffectMeta() {
        return (FireworkEffectMeta)this.getItemMeta();
    }
    
    private LeatherArmorMeta getLeatherArmorMeta() {
        return (LeatherArmorMeta)this.getItemMeta();
    }
    
    private MapMeta getMapMeta() {
        return (MapMeta)this.getItemMeta();
    }
    
    private SkullMeta getSkullMeta() {
        return (SkullMeta)this.getItemMeta();
    }
    
    private PotionMeta getPotionMeta() {
        return (PotionMeta)this.getItemMeta();
    }
    
    public ItemBuilder(final String s) {
        this.item = this;
        this.customItem = new ItemStack(Material.valueOf(s));
        this.initialize();
    }
    
    public ItemBuilder(final Material material) {
        this.item = this;
        this.customItem = new ItemStack(material);
        this.initialize();
    }
    
    public ItemBuilder(final ItemStack itemStack) {
        this.item = this;
        this.customItem = itemStack.clone();
        this.initialize();
    }
    
    public ItemBuilder(final int n) {
        this.item = this;
        this.customItem = new ItemStack(n);
        this.initialize();
    }
    
    public ItemBuilder(final int n, final int n2) {
        this.item = this;
        this.customItem = new ItemStack(n, 1, (short)n2);
        this.initialize();
    }
    
    public ItemFlags flag() {
        return this.flag;
    }
    
    public ItemSkull skull() {
        return this.skull;
    }
    
    public ItemMetaData metadata() {
        return this.metaData;
    }
    
    public ItemEnchantment enchantment() {
        return this.enchantment;
    }
    
    public ItemBanner banner() {
        return this.banner;
    }
    
    public ItemPotion potion() {
        return this.potion;
    }
    
    public ItemBook book() {
        return this.book;
    }
    
    public ItemFirework firework() {
        return this.firework;
    }
    
    public ItemLeatherArmor leatherarmor() {
        return this.leatherArmor;
    }
    
    public ItemMap map() {
        return this.map;
    }
    
    public ItemSpawnEgg spawnEgg() {
        return this.spawnEgg;
    }
    
    private void initialize() {
        this.flag = new ItemFlags() {
            @Override
            public ItemBuilder add(final ItemFlag... array) {
                final ItemMeta access$000 = ItemBuilder.this.getItemMeta();
                access$000.addItemFlags(array);
                ItemBuilder.this.customItem.setItemMeta(access$000);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder remove(final ItemFlag... array) {
                final ItemMeta access$000 = ItemBuilder.this.getItemMeta();
                access$000.removeItemFlags();
                ItemBuilder.this.customItem.setItemMeta(access$000);
                return ItemBuilder.this.item;
            }
            
            @Override
            public boolean has(final ItemFlag itemFlag) {
                return ItemBuilder.this.getItemMeta().hasItemFlag(itemFlag);
            }
        };
        this.skull = new ItemSkull() {
            @Override
            public UUID getOwner() {
                return API.getVersionSupport().getSkullOwner(ItemBuilder.this.getSkullMeta());
            }
            
            @Override
            public boolean hasOwner() {
                return ItemBuilder.this.getSkullMeta().hasOwner();
            }
            
            @Override
            public ItemBuilder setOwner(final String owner) {
                final String name = ItemBuilder.this.customItem.getType().name();
                if (!name.equals("PLAYER_HEAD") && !name.equals("SKULL_ITEM")) {
                    return ItemBuilder.this.item;
                }
                final SkullMeta access$300 = ItemBuilder.this.getSkullMeta();
                access$300.setOwner(owner);
                ItemBuilder.this.customItem.setItemMeta(access$300);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder setSkin(final String s) {
                final String name = ItemBuilder.this.customItem.getType().name();
                if (!name.equals("PLAYER_HEAD") && !name.equals("SKULL_ITEM")) {
                    return ItemBuilder.this.item;
                }
                if (s == null) {
                    return ItemBuilder.this.item;
                }
                final ItemMeta access$000 = ItemBuilder.this.getItemMeta();
                final GameProfile value = new GameProfile(ItemBuilder.randomUUID, (String)null);
                if (API.getVersionSupport().contains(14, 15, 16)) {
                    value.getProperties().put("textures", new Property("textures", new String(Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", s).getBytes()))));
                }
                else {
                    value.getProperties().put("textures", new Property("textures", new String(Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", s).getBytes()))));
                }
                try {
                    final Field declaredField = access$000.getClass().getDeclaredField("profile");
                    declaredField.setAccessible(true);
                    declaredField.set(access$000, value);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                ItemBuilder.this.customItem.setItemMeta(access$000);
                return ItemBuilder.this.item;
            }
        };
        this.metaData = new ItemMetaData() {
            @Override
            public ItemBuilder add(final String s, final String s2) {
                API.getVersionSupport().addMetaData(ItemBuilder.this.customItem, s, s2);
                return ItemBuilder.this.item;
            }
            
            @Override
            public String get(final String s) {
                return API.getVersionSupport().getMetaData(ItemBuilder.this.customItem, s);
            }
            
            @Override
            public boolean has(final String s) {
                return API.getVersionSupport().hasMetaData(ItemBuilder.this.customItem, s);
            }
            
            @Override
            public ItemBuilder remove(final String s) {
                API.getVersionSupport().removeMetaData(ItemBuilder.this.customItem, s);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder add(final String s) {
                API.getVersionSupport().addMetaData(ItemBuilder.this.customItem, "API", s);
                return ItemBuilder.this.item;
            }
            
            @Override
            public String get() {
                return API.getVersionSupport().getMetaData(ItemBuilder.this.customItem, "API");
            }
            
            @Override
            public boolean has() {
                return API.getVersionSupport().hasMetaData(ItemBuilder.this.customItem, "API");
            }
            
            @Override
            public ItemBuilder remove() {
                API.getVersionSupport().removeMetaData(ItemBuilder.this.customItem, "API");
                return ItemBuilder.this.item;
            }
        };
        this.enchantment = new ItemEnchantment() {
            @Override
            public ItemBuilder add(final Enchantment enchantment, final int n) {
                ItemBuilder.this.customItem.addEnchantment(enchantment, n);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder addUnsafe(final Enchantment enchantment, final int n) {
                ItemBuilder.this.customItem.addUnsafeEnchantment(enchantment, n);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder remove(final Enchantment enchantment) {
                ItemBuilder.this.customItem.removeEnchantment(enchantment);
                return ItemBuilder.this.item;
            }
            
            @Override
            public int getLevel(final Enchantment enchantment) {
                return ItemBuilder.this.customItem.getEnchantmentLevel(enchantment);
            }
            
            @Override
            public Map<Enchantment, Integer> getList() {
                return ItemBuilder.this.customItem.getEnchantments();
            }
            
            @Override
            public boolean has(final Enchantment enchantment) {
                return ItemBuilder.this.customItem.containsEnchantment(enchantment);
            }
        };
        this.banner = new ItemBanner() {
            @Override
            public ItemBuilder setPatterns(final List<Pattern> patterns) {
                final BannerMeta access$500 = ItemBuilder.this.getBannerMeta();
                access$500.setPatterns(patterns);
                ItemBuilder.this.customItem.setItemMeta(access$500);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder setPattern(final int n, final Pattern pattern) {
                final BannerMeta access$500 = ItemBuilder.this.getBannerMeta();
                access$500.setPattern(n, pattern);
                ItemBuilder.this.customItem.setItemMeta(access$500);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder addPattern(final Pattern pattern) {
                final BannerMeta access$500 = ItemBuilder.this.getBannerMeta();
                access$500.addPattern(pattern);
                ItemBuilder.this.customItem.setItemMeta(access$500);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder removePattern(final int n) {
                final BannerMeta access$500 = ItemBuilder.this.getBannerMeta();
                access$500.removePattern(n);
                ItemBuilder.this.customItem.setItemMeta(access$500);
                return ItemBuilder.this.item;
            }
            
            @Override
            public List<Pattern> getPatterns() {
                return ItemBuilder.this.getBannerMeta().getPatterns();
            }
            
            @Override
            public Pattern getPattern(final int n) {
                return ItemBuilder.this.getBannerMeta().getPattern(n);
            }
            
            @Override
            public int getNumberOfPatterns() {
                return ItemBuilder.this.getBannerMeta().numberOfPatterns();
            }
        };
        this.potion = new ItemPotion() {
            @Override
            public boolean hasCustomEffects() {
                return ItemBuilder.this.getPotionMeta().hasCustomEffects();
            }
            
            @Override
            public List<PotionEffect> getCustomEffects() {
                return ItemBuilder.this.getPotionMeta().getCustomEffects();
            }
            
            @Override
            public ItemBuilder removeCustomEffect(final PotionEffectType potionEffectType) {
                final PotionMeta access$600 = ItemBuilder.this.getPotionMeta();
                access$600.removeCustomEffect(potionEffectType);
                ItemBuilder.this.customItem.setItemMeta(access$600);
                return ItemBuilder.this.item;
            }
            
            @Override
            public boolean hasColor() {
                return false;
            }
            
            @Override
            public Color getColor() {
                return Color.WHITE;
            }
            
            @Override
            public ItemBuilder setColor(final Color color) {
                if (!API.getVersionSupport().contains(11, 12, 13, 14, 15, 16)) {
                    return ItemBuilder.this.item;
                }
                final PotionMeta access$600 = ItemBuilder.this.getPotionMeta();
                ItemBuilder.this.customItem.setItemMeta(access$600);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder setSplash(final PotionType potionType, final boolean splash) {
                if (API.getVersionSupport().contains(8)) {
                    final Potion potion = new Potion(potionType);
                    potion.setSplash(splash);
                    potion.apply(ItemBuilder.this.customItem);
                }
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder addCustomEffect(final PotionEffect potionEffect) {
                final PotionMeta access$600 = ItemBuilder.this.getPotionMeta();
                access$600.addCustomEffect(potionEffect, true);
                ItemBuilder.this.customItem.setItemMeta(access$600);
                return ItemBuilder.this.item;
            }
        };
        this.book = new ItemBook() {
            @Override
            public boolean hasTitle() {
                return ItemBuilder.this.getBookMeta().hasTitle();
            }
            
            @Override
            public String getTitle() {
                return ItemBuilder.this.getBookMeta().getTitle();
            }
            
            @Override
            public ItemBuilder setTitle(final String title) {
                final BookMeta access$700 = ItemBuilder.this.getBookMeta();
                access$700.setTitle(title);
                ItemBuilder.this.customItem.setItemMeta(access$700);
                return ItemBuilder.this.item;
            }
            
            @Override
            public boolean hasAuthor() {
                return ItemBuilder.this.getBookMeta().hasAuthor();
            }
            
            @Override
            public String getAuthor() {
                return ItemBuilder.this.getBookMeta().getAuthor();
            }
            
            @Override
            public ItemBuilder setAuthor(final String author) {
                final BookMeta access$700 = ItemBuilder.this.getBookMeta();
                access$700.setAuthor(author);
                ItemBuilder.this.customItem.setItemMeta(access$700);
                return ItemBuilder.this.item;
            }
            
            @Override
            public boolean hasPages() {
                return ItemBuilder.this.getBookMeta().hasPages();
            }
            
            @Override
            public String getPage(final int n) {
                return ItemBuilder.this.getBookMeta().getPage(n);
            }
            
            @Override
            public ItemBuilder setPage(final int n, final String s) {
                final BookMeta access$700 = ItemBuilder.this.getBookMeta();
                access$700.setPage(n, s);
                ItemBuilder.this.customItem.setItemMeta(access$700);
                return ItemBuilder.this.item;
            }
            
            @Override
            public List<String> getPages() {
                return ItemBuilder.this.getBookMeta().getPages();
            }
            
            @Override
            public ItemBuilder setPages(final List<String> pages) {
                final BookMeta access$700 = ItemBuilder.this.getBookMeta();
                access$700.setPages(pages);
                ItemBuilder.this.customItem.setItemMeta(access$700);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder addPages(final String... array) {
                final BookMeta access$700 = ItemBuilder.this.getBookMeta();
                access$700.addPage(array);
                ItemBuilder.this.customItem.setItemMeta(access$700);
                return ItemBuilder.this.item;
            }
            
            @Override
            public int getPageCount() {
                return ItemBuilder.this.getBookMeta().getPageCount();
            }
        };
        this.firework = new ItemFirework() {
            @Override
            public ItemBuilder addEffect(final FireworkEffect fireworkEffect) {
                final FireworkMeta access$800 = ItemBuilder.this.getFireworkMeta();
                access$800.addEffect(fireworkEffect);
                ItemBuilder.this.customItem.setItemMeta(access$800);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder addEffects(final FireworkEffect... array) {
                final FireworkMeta access$800 = ItemBuilder.this.getFireworkMeta();
                access$800.addEffects(array);
                ItemBuilder.this.customItem.setItemMeta(access$800);
                return ItemBuilder.this.item;
            }
            
            @Override
            public List<FireworkEffect> getEffects() {
                return ItemBuilder.this.getFireworkMeta().getEffects();
            }
            
            @Override
            public int getEffectsSize() {
                return ItemBuilder.this.getFireworkMeta().getEffectsSize();
            }
            
            @Override
            public ItemBuilder removeEffect(final int n) {
                final FireworkMeta access$800 = ItemBuilder.this.getFireworkMeta();
                access$800.removeEffect(n);
                ItemBuilder.this.customItem.setItemMeta(access$800);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder clearEffects() {
                final FireworkMeta access$800 = ItemBuilder.this.getFireworkMeta();
                access$800.clearEffects();
                ItemBuilder.this.customItem.setItemMeta(access$800);
                return ItemBuilder.this.item;
            }
            
            @Override
            public boolean hasEffects() {
                return ItemBuilder.this.getFireworkMeta().hasEffects();
            }
            
            @Override
            public int getPower() {
                return ItemBuilder.this.getFireworkMeta().getPower();
            }
            
            @Override
            public ItemBuilder setPower(final int power) {
                final FireworkMeta access$800 = ItemBuilder.this.getFireworkMeta();
                access$800.setPower(power);
                ItemBuilder.this.customItem.setItemMeta(access$800);
                return ItemBuilder.this.item;
            }
            
            @Override
            public ItemBuilder setEffect(final FireworkEffect effect) {
                final FireworkEffectMeta access$900 = ItemBuilder.this.getFireworkEffectMeta();
                access$900.setEffect(effect);
                ItemBuilder.this.customItem.setItemMeta(access$900);
                return ItemBuilder.this.item;
            }
            
            @Override
            public boolean hasEffect() {
                return ItemBuilder.this.getFireworkEffectMeta().hasEffect();
            }
            
            @Override
            public FireworkEffect getEffect() {
                return ItemBuilder.this.getFireworkEffectMeta().getEffect();
            }
        };
        this.leatherArmor = new ItemLeatherArmor() {
            @Override
            public Color getColor() {
                return ItemBuilder.this.getLeatherArmorMeta().getColor();
            }
            
            @Override
            public ItemBuilder setColor(final Color color) {
                final LeatherArmorMeta access$1000 = ItemBuilder.this.getLeatherArmorMeta();
                access$1000.setColor(color);
                ItemBuilder.this.customItem.setItemMeta(access$1000);
                return ItemBuilder.this.item;
            }
        };
        this.map = new ItemMap() {
            @Override
            public boolean isScaling() {
                return ItemBuilder.this.getMapMeta().isScaling();
            }
            
            @Override
            public ItemBuilder setScaling(final boolean scaling) {
                final MapMeta access$1100 = ItemBuilder.this.getMapMeta();
                access$1100.setScaling(scaling);
                ItemBuilder.this.customItem.setItemMeta(access$1100);
                return ItemBuilder.this.item;
            }
        };
        this.spawnEgg = new ItemSpawnEgg() {
            @Override
            public EntityType getEntityType() {
                return null;
            }
            
            @Override
            public ItemBuilder setEntityType(final EntityType spawnedType) {
                return ItemBuilder.this.item;
            }
        };
    }
    
    public ItemBuilder setDisplayName(final String s) {
        final ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
        this.customItem.setItemMeta(itemMeta);
        return this.item;
    }
    
    public String getDisplayName() {
        return this.customItem.getItemMeta().getDisplayName();
    }
    
    public ItemBuilder setLore(final List<String> list) {
        final ItemMeta itemMeta = this.getItemMeta();
        final ArrayList<String> lore = new ArrayList<String>();
        for (String s : list) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        itemMeta.setLore(lore);
        this.customItem.setItemMeta(itemMeta);
        return this.item;
    }
    
    public ItemBuilder setUnbreakable(final boolean b) {
        final ItemMeta itemMeta = this.getItemMeta();
        API.getVersionSupport().setUnbreakable(itemMeta, b);
        this.customItem.setItemMeta(itemMeta);
        return this.item;
    }
    
    public ItemBuilder setAmount(final int amount) {
        this.customItem.setAmount(amount);
        return this.item;
    }
    
    public ItemBuilder setData(final int n) {
        if (API.getVersionSupport().contains(13, 14, 15, 16)) {
            return this.item;
        }
        this.customItem.setDurability((short)n);
        return this.item;
    }
    
    public ItemBuilder modifyDamage(final int n) {
        this.customItem = API.getVersionSupport().modifyItemDamage(this.getMaterial(), n);
        return this.item;
    }
    
    public int getAmount() {
        return this.customItem.getAmount();
    }
    
    public ItemBuilder setMaterial(final Material type) {
        this.customItem.setType(type);
        return this.item;
    }
    
    public Material getMaterial() {
        return this.customItem.getType();
    }
    
    @Override
    public boolean equals(final Object o) {
        return this.customItem.equals(o);
    }
    
    public boolean isSimilar(final ItemStack itemStack) {
        return this.customItem.isSimilar(itemStack);
    }
    
    public ItemStack build() {
        return this.customItem;
    }
    
    static {
        ItemBuilder.randomUUID = UUID.fromString("eb38bdd9-a3d9-41c5-9f03-2273ed0e8bc7");
    }
}
