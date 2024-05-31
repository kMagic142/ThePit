package ro.kmagic.libapi.versionsupport.entity;

public enum Entities
{
    ARMOR_STAND("ArmorStand"), 
    BAT("Bat"), 
    BEE("Bee"), 
    BLAZE("Blaze"), 
    CAT("Cat"), 
    CAVE_SPIDER("CaveSpider"), 
    CHICKEN("Chicken"), 
    COD("Cod"), 
    COW("Cow"), 
    CREEPER("Creeper"), 
    DOLPHIN("Dolphin"), 
    DONKEY("HorseDonkey"), 
    DROWNED("Drowned"), 
    ELDER_GUARDIAN("GuardianElder"), 
    ENDER_DRAGON("EnderDragon"), 
    ENDERMAN("Enderman"), 
    ENDERMITE("Endermite"), 
    EVOKER("Evoker"), 
    FOX("Fox"), 
    GHAST("Ghast"), 
    GIANT("GiantZombie"), 
    GUARDIAN("Guardian"), 
    HORSE("Horse"), 
    HUSK("ZombieHusk"), 
    ILLUSIONER("IllagerIllusioner"), 
    IRON_GOLEM("IronGolem"), 
    LLAMA("Llama"), 
    MAGMA_CUBE("MagmaCube"), 
    MULE("HorseMule"), 
    MUSHROOM_COW("MushroomCow"), 
    OCELOT("Ocelot"), 
    PANDA("Panda"), 
    PARROT("Parrot"), 
    PHANTOM("Phantom"), 
    PIG("Pig"), 
    PIG_ZOMBIE("PigZombie"), 
    PILLAGER("Pillager"), 
    PLAYER("Player"), 
    POLAR_BEAR("PolarBear"), 
    PUFFERFISH("PufferFish"), 
    RABBIT("Rabbit"), 
    RAVAGER("Ravager"), 
    SALMON("Salmon"), 
    SHEEP("Sheep"), 
    SILVERFISH("Silverfish"), 
    SKELETON("Skeleton"), 
    SKELETON_HORSE("HorseSkeleton"), 
    SNOWMAN("Snowman"), 
    SPIDER("Spider"), 
    SQUID("Squid"), 
    STRAY("SkeletonStray"), 
    TRADER_LLAMA("LlamaTrader"), 
    TROPICAL_FISH("TropicalFish"), 
    TURTLE("Turtle"), 
    VEX("Vex"), 
    VILLAGER("Villager"), 
    VINDICATOR("Vindicator"), 
    WANDERING_TRADER("VillagerTrader"), 
    WITCH("Witch"), 
    WITHER("Wither"), 
    WITHER_SKELETON("WitherSkeleton"), 
    WOLF("Wolf"), 
    ZOMBIE("Zombie"), 
    ZOMBIE_HORSE("ZombieHorse"), 
    ZOMBIE_VILLAGER("ZombieVillager");
    
    private final String nmsName;
    
    Entities(final String nmsName) {
        this.nmsName = nmsName;
    }
    
    public String getBukkitName() {
        return this.toString();
    }
    
    public String getNMSName() {
        return this.nmsName;
    }
}
