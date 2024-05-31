// 
// Decompiled by Procyon v0.5.36
// 

package ro.kmagic.libapi.versionsupport.sound;

import ro.kmagic.libapi.versionsupport.VersionType;

public enum Sounds
{
    EXPLODE(new Sound("explode").addSupport(VersionType.v1_8_R3, "EXPLODE").addSupport(VersionType.v1_9_R1, "ENTITY_GENERIC_EXPLODE").save()),
    DRAGON_WINGS(new Sound("dragonWings").addSupport(VersionType.v1_8_R3, "ENDERDRAGON_WINGS").addSupport(VersionType.v1_9_R1, "ENTITY_ENDERDRAGON_FLAP").addSupport(VersionType.v1_13_R1, "ENTITY_ENDER_DRAGON_FLAP").save()),
    WITHER_SHOOT(new Sound("witherShoot").addSupport(VersionType.v1_8_R3, "WITHER_SHOOT").addSupport(VersionType.v1_9_R1, "ENTITY_WITHER_SHOOT").save()),
    ORB_PICKUP(new Sound("orbPickup").addSupport(VersionType.v1_8_R3, "ORB_PICKUP").addSupport(VersionType.v1_9_R1, "ENTITY_EXPERIENCE_ORB_PICKUP").save()),
    EQUIP_ARMOR(new Sound("equipArmor").addSupport(VersionType.v1_8_R3, "HORSE_ARMOR").addSupport(VersionType.v1_9_R1, "ENTITY_HORSE_ARMOR").save()),
    GENERIC_EAT(new Sound("genericEat").addSupport(VersionType.v1_8_R3, "EAT").addSupport(VersionType.v1_9_R1, "ENTITY_GENERIC_EAT").save()),
    ARROW_HIT_PLAYER(new Sound("arrowHitPlayer").addSupport(VersionType.v1_8_R3, "SUCCESSFUL_HIT").addSupport(VersionType.v1_9_R1, "ENTITY_ARROW_HIT_PLAYER").save()),
    PLAYER_LEVELUP(new Sound("levelUp").addSupport(VersionType.v1_8_R3, "LEVEL_UP").addSupport(VersionType.v1_9_R1, "ENTITY_PLAYER_LEVELUP").save()),
    ENDERMAN_TELEPORT(new Sound("endermanTeleport").addSupport(VersionType.v1_8_R3, "ENDERMAN_TELEPORT").addSupport(VersionType.v1_9_R1, "ENTITY_ENDERMEN_TELEPORT").addSupport(VersionType.v1_13_R1, "ENTITY_ENDERMAN_TELEPORT").save()),
    VILLAGER_NO(new Sound("villagerNo").addSupport(VersionType.v1_8_R3, "VILLAGER_NO").addSupport(VersionType.v1_9_R1, "ENTITY_VILLAGER_NO").save()),
    NOTE_PLING(new Sound("notePling").addSupport(VersionType.v1_8_R3, "NOTE_PLING").addSupport(VersionType.v1_9_R1, "BLOCK_NOTE_PLING").addSupport(VersionType.v1_13_R1, "BLOCK_NOTE_BLOCK_PLING").save());
    
    private final Sound sound;
    
    Sounds(final Sound sound) {
        this.sound = sound;
    }
    
    public Sound getSound() {
        return this.sound;
    }
}
