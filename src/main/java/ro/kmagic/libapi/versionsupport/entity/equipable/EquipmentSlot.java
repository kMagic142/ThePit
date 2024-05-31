package ro.kmagic.libapi.versionsupport.entity.equipable;

public enum EquipmentSlot
{
    HEAD(3, "Helmet"), 
    CHEST(2, "Chestplate"), 
    LEGS(1, "Leggings"), 
    FEET(0, "Boots"), 
    LEFT_HAND(1, "ItemInOffHand"), 
    RIGHT_HAND(0, "ItemInMainHand", "ItemInHand");
    
    private final int slot;
    private final String name;
    private String legacy;
    
    EquipmentSlot(final int slot, final String name2) {
        this.slot = slot;
        this.name = name2;
    }
    
    EquipmentSlot(final int slot, final String name2, final String legacy) {
        this.slot = slot;
        this.name = name2;
        this.legacy = legacy;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getLegacy() {
        return this.legacy;
    }
}
