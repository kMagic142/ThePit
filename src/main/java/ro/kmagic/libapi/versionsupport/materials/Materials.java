package ro.kmagic.libapi.versionsupport.materials;

import java.util.ArrayList;
import org.bukkit.World;
import org.bukkit.block.Block;
import ro.kmagic.libapi.API;
import ro.kmagic.libapi.events.misc.MaterialLegacyEvent;
import org.bukkit.Bukkit;
import ro.kmagic.libapi.item.ItemBuilder;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;

public enum Materials
{
    ACACIA_BOAT(new MatPrep(333), new MatPrep(447), new MatPrep(447), new MatPrep(447), new MatPrep(447), new MatPrep(447), new MatPrep("ACACIA_BOAT"), new MatPrep("ACACIA_BOAT"), new MatPrep("ACACIA_BOAT"), new MatPrep("ACACIA_BOAT")), 
    ACACIA_BUTTON(new MatPrep(143)), 
    ACACIA_DOOR(new MatPrep(430)), 
    ACACIA_FENCE(new MatPrep(192)), 
    ACACIA_FENCE_GATE(new MatPrep(187)), 
    ACACIA_LEAVES(new MatPrep(161)), 
    ACACIA_LOG(new MatPrep(162)), 
    ACACIA_PLANKS(new MatPrep(5, 4)), 
    ACACIA_PRESSURE_PLATE(new MatPrep(72)), 
    ACACIA_SAPLING(new MatPrep(6, 4)), 
    ACACIA_SIGN(new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep("SIGN"), new MatPrep("SIGN"), new MatPrep("ACACIA_SIGN"), new MatPrep("ACACIA_SIGN")), 
    ACACIA_SLAB(new MatPrep(126, 4)), 
    ACACIA_STAIRS(new MatPrep(163)), 
    ACACIA_TRAPDOOR(new MatPrep(96)), 
    ACACIA_WALL_SIGN(new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep("WALL_SIGN"), new MatPrep("WALL_SIGN"), new MatPrep("ACACIA_WALL_SIGN"), new MatPrep("ACACIA_WALL_SIGN")), 
    ACACIA_WOOD(new MatPrep(162, true)), 
    ACTIVATOR_RAIL(new MatPrep(157)), 
    AIR(new MatPrep(0)), 
    ALLIUM(new MatPrep(38, 2)), 
    ANDESITE(new MatPrep(1, 5)), 
    ANDESITE_SLAB(new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep("STONE_SLAB", true), new MatPrep("STONE_SLAB", true), new MatPrep("ANDESITE_SLAB"), new MatPrep("ANDESITE_SLAB")), 
    ANDESITE_STAIRS(new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("ANDESITE_STAIRS"), new MatPrep("ANDESITE_STAIRS")), 
    ANDESITE_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("ANDESITE_WALL"), new MatPrep("ANDESITE_WALL")), 
    ANVIL(new MatPrep(145)), 
    APPLE(new MatPrep(260)), 
    ARMOR_STAND(new MatPrep(416)), 
    ARROW(new MatPrep(262)), 
    ATTACHED_MELON_STEM(new MatPrep(105, 7)), 
    ATTACHED_PUMPKIN_STEM(new MatPrep(104, 7)), 
    AZURE_BLUET(new MatPrep(38, 3)), 
    BAKED_POTATO(new MatPrep(393)), 
    BAMBOO(new MatPrep(338, true), new MatPrep(338, true), new MatPrep(338, true), new MatPrep(338, true), new MatPrep(338, true), new MatPrep(338, true), new MatPrep("SUGAR_CANE", true), new MatPrep("SUGAR_CANE", true), new MatPrep("BAMBOO"), new MatPrep("BAMBOO")), 
    BAMBOO_SAPLING(new MatPrep(338, true), new MatPrep(338, true), new MatPrep(338, true), new MatPrep(338, true), new MatPrep(338, true), new MatPrep(338, true), new MatPrep("SUGAR_CANE", true), new MatPrep("SUGAR_CANE", true), new MatPrep("BAMBOO_SAPLING"), new MatPrep("BAMBOO_SAPLING")), 
    BARREL(new MatPrep(17, true), new MatPrep(17, true), new MatPrep(17, true), new MatPrep(17, true), new MatPrep(17, true), new MatPrep(17, true), new MatPrep("OAK_LOG", true), new MatPrep("OAK_LOG", true), new MatPrep("BARREL"), new MatPrep("BARREL")), 
    BARRIER(new MatPrep(166)), 
    BAT_SPAWN_EGG(new MatPrep(383, 65)), 
    BEACON(new MatPrep(138)), 
    BEDROCK(new MatPrep(7)), 
    BEEF(new MatPrep(363)), 
    BEEHIVE(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("BEEHIVE")), 
    BEETROOT(new MatPrep(0, true), new MatPrep(434), new MatPrep(434), new MatPrep(434), new MatPrep(434), new MatPrep(434), new MatPrep("BEETROOT"), new MatPrep("BEETROOT"), new MatPrep("BEETROOT"), new MatPrep("BEETROOT")), 
    BEETROOTS(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BEETROOT"), new MatPrep("BEETROOTS"), new MatPrep("BEETROOTS"), new MatPrep("BEETROOTS")), 
    BEETROOT_SEEDS(new MatPrep(295, true), new MatPrep(435), new MatPrep(435), new MatPrep(435), new MatPrep(435), new MatPrep(435), new MatPrep("BEETROOT_SEEDS"), new MatPrep("BEETROOT_SEEDS"), new MatPrep("BEETROOT_SEEDS"), new MatPrep("BEETROOT_SEEDS")), 
    BEETROOT_SOUP(new MatPrep(282, true), new MatPrep(436), new MatPrep(436), new MatPrep(436), new MatPrep(436), new MatPrep(436), new MatPrep("BEETROOT_SOUP"), new MatPrep("BEETROOT_SOUP"), new MatPrep("BEETROOT_SOUP"), new MatPrep("BEETROOT_SOUP")), 
    BEE_NEST(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("BEE_NEST")), 
    BEE_SPAWN_EGG(new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep("BAT_SPAWN_EGG"), new MatPrep("BAT_SPAWN_EGG"), new MatPrep("BAT_SPAWN_EGG"), new MatPrep("BEE_SPAWN_EGG")), 
    BELL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("BELL"), new MatPrep("BELL")), 
    BIRCH_BOAT(new MatPrep(333), new MatPrep(445), new MatPrep(445), new MatPrep(445), new MatPrep(445), new MatPrep(445), new MatPrep("BIRCH_BOAT"), new MatPrep("BIRCH_BOAT"), new MatPrep("BIRCH_BOAT"), new MatPrep("BIRCH_BOAT")), 
    BIRCH_BUTTON(new MatPrep(143, true)), 
    BIRCH_DOOR(new MatPrep(428)), 
    BIRCH_FENCE(new MatPrep(189)), 
    BIRCH_FENCE_GATE(new MatPrep(184)), 
    BIRCH_LEAVES(new MatPrep(18, 2)), 
    BIRCH_LOG(new MatPrep(17, 2)), 
    BIRCH_PLANKS(new MatPrep(5, 2)), 
    BIRCH_PRESSURE_PLATE(new MatPrep(72, true)), 
    BIRCH_SAPLING(new MatPrep(6, 2)), 
    BIRCH_SIGN(new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep("SIGN"), new MatPrep("SIGN"), new MatPrep("BIRCH_SIGN"), new MatPrep("BIRCH_SIGN")), 
    BIRCH_SLAB(new MatPrep(126, 2)), 
    BIRCH_STAIRS(new MatPrep(135)), 
    BIRCH_TRAPDOOR(new MatPrep(96, true)), 
    BIRCH_WALL_SIGN(new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep("WALL_SIGN"), new MatPrep("WALL_SIGN"), new MatPrep("BIRCH_WALL_SIGN"), new MatPrep("BIRCH_WALL_SIGN")), 
    BIRCH_WOOD(new MatPrep(17, 2, true)), 
    BLACK_BANNER(new MatPrep(425)), 
    BLACK_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 15), new MatPrep("BLACK_BED"), new MatPrep("BLACK_BED"), new MatPrep("BLACK_BED"), new MatPrep("BLACK_BED")), 
    BLACK_CARPET(new MatPrep(171, 15)), 
    BLACK_CONCRETE(new MatPrep(159, 15, true), new MatPrep(159, 15, true), new MatPrep(159, 15, true), new MatPrep(159, 15, true), new MatPrep(159, 15, true), new MatPrep(251, 15), new MatPrep("BLACK_CONCRETE"), new MatPrep("BLACK_CONCRETE"), new MatPrep("BLACK_CONCRETE"), new MatPrep("BLACK_CONCRETE")), 
    BLACK_CONCRETE_POWDER(new MatPrep(12), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 15), new MatPrep("BLACK_CONCRETE_POWDER"), new MatPrep("BLACK_CONCRETE_POWDER"), new MatPrep("BLACK_CONCRETE_POWDER"), new MatPrep("BLACK_CONCRETE_POWDER")), 
    BLACK_DYE(new MatPrep(351), new MatPrep(351), new MatPrep(351), new MatPrep(351), new MatPrep(351), new MatPrep(351), new MatPrep("INK_SAC"), new MatPrep("INK_SAC"), new MatPrep("BLACK_DYE"), new MatPrep("BLACK_DYE")), 
    BLACK_GLAZED_TERRACOTTA(new MatPrep(159, 15, true), new MatPrep(159, 15, true), new MatPrep(159, 15, true), new MatPrep(159, 15, true), new MatPrep(159, 15, true), new MatPrep(250), new MatPrep("BLACK_GLAZED_TERRACOTTA"), new MatPrep("BLACK_GLAZED_TERRACOTTA"), new MatPrep("BLACK_GLAZED_TERRACOTTA"), new MatPrep("BLACK_GLAZED_TERRACOTTA")), 
    BLACK_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(234), new MatPrep(234), new MatPrep("BLACK_SHULKER_BOX"), new MatPrep("BLACK_SHULKER_BOX"), new MatPrep("BLACK_SHULKER_BOX"), new MatPrep("BLACK_SHULKER_BOX")), 
    BLACK_STAINED_GLASS(new MatPrep(95, 15)), 
    BLACK_STAINED_GLASS_PANE(new MatPrep(160, 15)), 
    BLACK_TERRACOTTA(new MatPrep(159, 15)), 
    BLACK_WALL_BANNER(new MatPrep(425)), 
    BLACK_WOOL(new MatPrep(35, 15)), 
    BLAST_FURNACE(new MatPrep(61, true), new MatPrep(61, true), new MatPrep(61, true), new MatPrep(61, true), new MatPrep(61, true), new MatPrep(61, true), new MatPrep("FURNACE", true), new MatPrep("FURNACE", true), new MatPrep("BLAST_FURNACE"), new MatPrep("BLAST_FURNACE")), 
    BLAZE_POWDER(new MatPrep(377)), 
    BLAZE_ROD(new MatPrep(369)), 
    BLAZE_SPAWN_EGG(new MatPrep(383, 61)), 
    BLUE_BANNER(new MatPrep(425, 4)), 
    BLUE_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 11), new MatPrep("BLUE_BED"), new MatPrep("BLUE_BED"), new MatPrep("BLUE_BED"), new MatPrep("BLUE_BED")), 
    BLUE_CARPET(new MatPrep(171, 11)), 
    BLUE_CONCRETE(new MatPrep(159, 11, true), new MatPrep(159, 11, true), new MatPrep(159, 11, true), new MatPrep(159, 11, true), new MatPrep(159, 11, true), new MatPrep(251, 11), new MatPrep("BLUE_CONCRETE"), new MatPrep("BLUE_CONCRETE"), new MatPrep("BLUE_CONCRETE"), new MatPrep("BLUE_CONCRETE")), 
    BLUE_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 11), new MatPrep("BLUE_CONCRETE_POWDER"), new MatPrep("BLUE_CONCRETE_POWDER"), new MatPrep("BLUE_CONCRETE_POWDER"), new MatPrep("BLUE_CONCRETE_POWDER")), 
    BLUE_DYE(new MatPrep(351, 4), new MatPrep(351, 4), new MatPrep(351, 4), new MatPrep(351, 4), new MatPrep(351, 4), new MatPrep(351, 4), new MatPrep("LAPIS_LAZULI"), new MatPrep("LAPIS_LAZULI"), new MatPrep("BLUE_DYE"), new MatPrep("BLUE_DYE")), 
    BLUE_GLAZED_TERRACOTTA(new MatPrep(159, 11, true), new MatPrep(159, 11, true), new MatPrep(159, 11, true), new MatPrep(159, 11, true), new MatPrep(159, 11, true), new MatPrep(246), new MatPrep("BLUE_GLAZED_TERRACOTTA"), new MatPrep("BLUE_GLAZED_TERRACOTTA"), new MatPrep("BLUE_GLAZED_TERRACOTTA"), new MatPrep("BLUE_GLAZED_TERRACOTTA")), 
    BLUE_ICE(new MatPrep(174)), 
    BLUE_ORCHID(new MatPrep(38, 1)), 
    BLUE_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(230), new MatPrep(230), new MatPrep("BLUE_SHULKER_BOX"), new MatPrep("BLUE_SHULKER_BOX"), new MatPrep("BLUE_SHULKER_BOX"), new MatPrep("BLUE_SHULKER_BOX")), 
    BLUE_STAINED_GLASS(new MatPrep(95, 11)), 
    BLUE_STAINED_GLASS_PANE(new MatPrep(160, 11)), 
    BLUE_TERRACOTTA(new MatPrep(159, 11)), 
    BLUE_WALL_BANNER(new MatPrep(425, 4)), 
    BLUE_WOOL(new MatPrep(35, 11)), 
    BONE(new MatPrep(352)), 
    BONE_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(216), new MatPrep(216), new MatPrep(216), new MatPrep("BONE_BLOCK"), new MatPrep("BONE_BLOCK"), new MatPrep("BONE_BLOCK"), new MatPrep("BONE_BLOCK")), 
    BONE_MEAL(new MatPrep(351, 15)), 
    BOOK(new MatPrep(340)), 
    BOOKSHELF(new MatPrep(47)), 
    BOW(new MatPrep(261)), 
    BOWL(new MatPrep(281)), 
    BRAIN_CORAL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BRAIN_CORAL"), new MatPrep("BRAIN_CORAL"), new MatPrep("BRAIN_CORAL"), new MatPrep("BRAIN_CORAL")), 
    BRAIN_CORAL_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BRAIN_CORAL_BLOCK"), new MatPrep("BRAIN_CORAL_BLOCK"), new MatPrep("BRAIN_CORAL_BLOCK"), new MatPrep("BRAIN_CORAL_BLOCK")), 
    BRAIN_CORAL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BRAIN_CORAL_FAN"), new MatPrep("BRAIN_CORAL_FAN"), new MatPrep("BRAIN_CORAL_FAN"), new MatPrep("BRAIN_CORAL_FAN")), 
    BRAIN_CORAL_WALL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BRAIN_CORAL_WALL_FAN"), new MatPrep("BRAIN_CORAL_WALL_FAN"), new MatPrep("BRAIN_CORAL_WALL_FAN"), new MatPrep("BRAIN_CORAL_WALL_FAN")), 
    BREAD(new MatPrep(297)), 
    BREWING_STAND(new MatPrep(379)), 
    BRICK(new MatPrep(336)), 
    BRICKS(new MatPrep(45)), 
    BRICK_SLAB(new MatPrep(44, 4)), 
    BRICK_STAIRS(new MatPrep(108)), 
    BRICK_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("BRICK_WALL"), new MatPrep("BRICK_WALL")), 
    BROWN_BANNER(new MatPrep(425, 3)), 
    BROWN_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 12), new MatPrep("BROWN_BED"), new MatPrep("BROWN_BED"), new MatPrep("BROWN_BED"), new MatPrep("BROWN_BED")), 
    BROWN_CARPET(new MatPrep(171, 12)), 
    BROWN_CONCRETE(new MatPrep(159, 12, true), new MatPrep(159, 12, true), new MatPrep(159, 12, true), new MatPrep(159, 12, true), new MatPrep(159, 12, true), new MatPrep(251, 12), new MatPrep("BROWN_CONCRETE"), new MatPrep("BROWN_CONCRETE"), new MatPrep("BROWN_CONCRETE"), new MatPrep("BROWN_CONCRETE")), 
    BROWN_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 12), new MatPrep("BROWN_CONCRETE_POWDER"), new MatPrep("BROWN_CONCRETE_POWDER"), new MatPrep("BROWN_CONCRETE_POWDER"), new MatPrep("BROWN_CONCRETE_POWDER")), 
    BROWN_DYE(new MatPrep(351, 3), new MatPrep(351, 3), new MatPrep(351, 3), new MatPrep(351, 3), new MatPrep(351, 3), new MatPrep(351, 3), new MatPrep("COCOA_BEANS"), new MatPrep("COCOA_BEANS"), new MatPrep("BROWN_DYE"), new MatPrep("BROWN_DYE")), 
    BROWN_GLAZED_TERRACOTTA(new MatPrep(159, 12, true), new MatPrep(159, 12, true), new MatPrep(159, 12, true), new MatPrep(159, 12, true), new MatPrep(159, 12, true), new MatPrep(247), new MatPrep("BROWN_GLAZED_TERRACOTTA"), new MatPrep("BROWN_GLAZED_TERRACOTTA"), new MatPrep("BROWN_GLAZED_TERRACOTTA"), new MatPrep("BROWN_GLAZED_TERRACOTTA")), 
    BROWN_MUSHROOM(new MatPrep(39)), 
    BROWN_MUSHROOM_BLOCK(new MatPrep(99, true)), 
    BROWN_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(231), new MatPrep(231), new MatPrep("BROWN_SHULKER_BOX"), new MatPrep("BROWN_SHULKER_BOX"), new MatPrep("BROWN_SHULKER_BOX"), new MatPrep("BROWN_SHULKER_BOX")), 
    BROWN_STAINED_GLASS(new MatPrep(95, 12)), 
    BROWN_STAINED_GLASS_PANE(new MatPrep(160, 12)), 
    BROWN_TERRACOTTA(new MatPrep(159, 12)), 
    BROWN_WALL_BANNER(new MatPrep(425, 3)), 
    BROWN_WOOL(new MatPrep(35, 12)), 
    BUBBLE_COLUMN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BUBBLE_COLUMN"), new MatPrep("BUBBLE_COLUMN"), new MatPrep("BUBBLE_COLUMN"), new MatPrep("BUBBLE_COLUMN")), 
    BUBBLE_CORAL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BUBBLE_CORAL"), new MatPrep("BUBBLE_CORAL"), new MatPrep("BUBBLE_CORAL"), new MatPrep("BUBBLE_CORAL")), 
    BUBBLE_CORAL_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BUBBLE_CORAL_BLOCK"), new MatPrep("BUBBLE_CORAL_BLOCK"), new MatPrep("BUBBLE_CORAL_BLOCK"), new MatPrep("BUBBLE_CORAL_BLOCK")), 
    BUBBLE_CORAL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BUBBLE_CORAL_FAN"), new MatPrep("BUBBLE_CORAL_FAN"), new MatPrep("BUBBLE_CORAL_FAN"), new MatPrep("BUBBLE_CORAL_FAN")), 
    BUBBLE_CORAL_WALL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("BUBBLE_CORAL_WALL_FAN"), new MatPrep("BUBBLE_CORAL_WALL_FAN"), new MatPrep("BUBBLE_CORAL_WALL_FAN"), new MatPrep("BUBBLE_CORAL_WALL_FAN")), 
    BUCKET(new MatPrep(325)), 
    CACTUS(new MatPrep(81)), 
    CAKE(new MatPrep(354)), 
    CAMPFIRE(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("CAMPFIRE"), new MatPrep("CAMPFIRE")), 
    CARROT(new MatPrep(391)), 
    CARROTS(new MatPrep(141)), 
    CARROT_ON_A_STICK(new MatPrep(398)), 
    CARTOGRAPHY_TABLE(new MatPrep(58, true), new MatPrep(58, true), new MatPrep(58, true), new MatPrep(58, true), new MatPrep(58, true), new MatPrep(58, true), new MatPrep("CRAFTING_TABLE", true), new MatPrep("CRAFTING_TABLE", true), new MatPrep("CARTOGRAPHY_TABLE"), new MatPrep("CARTOGRAPHY_TABLE")), 
    CARVED_PUMPKIN(new MatPrep(86)), 
    CAT_SPAWN_EGG(new MatPrep(383, 98, true), new MatPrep(383, 98, true), new MatPrep(383, 98, true), new MatPrep(383, 98, true), new MatPrep(383, 98, true), new MatPrep(383, 98, true), new MatPrep("OCELOT_SPAWN_EGG", true), new MatPrep("OCELOT_SPAWN_EGG", true), new MatPrep("CAT_SPAWN_EGG"), new MatPrep("CAT_SPAWN_EGG")), 
    CAULDRON(new MatPrep(380)), 
    CAVE_AIR(new MatPrep(0, true)), 
    CAVE_SPIDER_SPAWN_EGG(new MatPrep(383, 59)), 
    CHAINMAIL_BOOTS(new MatPrep(305)), 
    CHAINMAIL_CHESTPLATE(new MatPrep(303)), 
    CHAINMAIL_HELMET(new MatPrep(302)), 
    CHAINMAIL_LEGGINGS(new MatPrep(304)), 
    CHAIN_COMMAND_BLOCK(new MatPrep(137, true)), 
    CHARCOAL(new MatPrep(263, 1)), 
    CHEST(new MatPrep(54)), 
    CHEST_MINECART(new MatPrep(342)), 
    CHICKEN(new MatPrep(365)), 
    CHICKEN_SPAWN_EGG(new MatPrep(383, 93)), 
    CHIPPED_ANVIL(new MatPrep(145, 1)), 
    CHISELED_QUARTZ_BLOCK(new MatPrep(155, 1)), 
    CHISELED_RED_SANDSTONE(new MatPrep(179, 1)), 
    CHISELED_SANDSTONE(new MatPrep(24, 1)), 
    CHISELED_STONE_BRICKS(new MatPrep(98, 3)), 
    CHORUS_FLOWER(new MatPrep(0, true), new MatPrep(200), new MatPrep(200), new MatPrep(200), new MatPrep(200), new MatPrep(200), new MatPrep("CHORUS_FLOWER"), new MatPrep("CHORUS_FLOWER"), new MatPrep("CHORUS_FLOWER"), new MatPrep("CHORUS_FLOWER")), 
    CHORUS_FRUIT(new MatPrep(0, true), new MatPrep(432), new MatPrep(432), new MatPrep(432), new MatPrep(432), new MatPrep(432), new MatPrep("CHORUS_FRUIT"), new MatPrep("CHORUS_FRUIT"), new MatPrep("CHORUS_FRUIT"), new MatPrep("CHORUS_FRUIT")), 
    CHORUS_PLANT(new MatPrep(0, true), new MatPrep(199), new MatPrep(199), new MatPrep(199), new MatPrep(199), new MatPrep(199), new MatPrep("CHORUS_PLANT"), new MatPrep("CHORUS_PLANT"), new MatPrep("CHORUS_PLANT"), new MatPrep("CHORUS_PLANT")), 
    CLAY(new MatPrep(82)), 
    CLAY_BALL(new MatPrep(337)), 
    CLOCK(new MatPrep(347)), 
    COAL(new MatPrep(263)), 
    COAL_BLOCK(new MatPrep(173)), 
    COAL_ORE(new MatPrep(16)), 
    COARSE_DIRT(new MatPrep(3, 1)), 
    COBBLESTONE(new MatPrep(4)), 
    COBBLESTONE_SLAB(new MatPrep(44, 3)), 
    COBBLESTONE_STAIRS(new MatPrep(67)), 
    COBBLESTONE_WALL(new MatPrep(139)), 
    COBWEB(new MatPrep(30)), 
    COCOA(new MatPrep(351, 3)), 
    COCOA_BEANS(new MatPrep(351, 3)), 
    COD(new MatPrep(349, 2, true)), 
    COD_BUCKET(new MatPrep(326, true)), 
    COD_SPAWN_EGG(new MatPrep(383, 94, true)), 
    COMMAND_BLOCK(new MatPrep(137)), 
    COMMAND_BLOCK_MINECART(new MatPrep(422)), 
    COMPARATOR(new MatPrep(404)), 
    COMPASS(new MatPrep(345)), 
    COMPOSTER(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("COMPOSTER"), new MatPrep("COMPOSTER")), 
    CONDUIT(new MatPrep(0, true)), 
    COOKED_BEEF(new MatPrep(364)), 
    COOKED_CHICKEN(new MatPrep(366)), 
    COOKED_COD(new MatPrep(350)), 
    COOKED_MUTTON(new MatPrep(424)), 
    COOKED_PORKCHOP(new MatPrep(320)), 
    COOKED_RABBIT(new MatPrep(412)), 
    COOKED_SALMON(new MatPrep(350, 1)), 
    COOKIE(new MatPrep(357)), 
    CORNFLOWER(new MatPrep(38, 1, true), new MatPrep(38, 1, true), new MatPrep(38, 1, true), new MatPrep(38, 1, true), new MatPrep(38, 1, true), new MatPrep(38, 1, true), new MatPrep("BLUE_ORCHID", true), new MatPrep("BLUE_ORCHID", true), new MatPrep("CORNFLOWER"), new MatPrep("CORNFLOWER")), 
    COW_SPAWN_EGG(new MatPrep(383, 92)), 
    CRACKED_STONE_BRICKS(new MatPrep(98, 2)), 
    CRAFTING_TABLE(new MatPrep(58)), 
    CREEPER_BANNER_PATTERN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("CREEPER_BANNER_PATTERN"), new MatPrep("CREEPER_BANNER_PATTERN")), 
    CREEPER_HEAD(new MatPrep(397, 4)), 
    CREEPER_SPAWN_EGG(new MatPrep(383, 50)), 
    CREEPER_WALL_HEAD(new MatPrep(397, 4)), 
    CROSSBOW(new MatPrep(261), new MatPrep(261), new MatPrep(261), new MatPrep(261), new MatPrep(261), new MatPrep(261), new MatPrep("BOW", true), new MatPrep("BOW", true), new MatPrep("CROSSBOW"), new MatPrep("CROSSBOW")), 
    CUT_RED_SANDSTONE(new MatPrep(179, 2), new MatPrep(179, 2), new MatPrep(179, 2), new MatPrep(179, 2), new MatPrep(179, 2), new MatPrep(179, 2), new MatPrep("CUT_RED_SANDSTONE"), new MatPrep("CUT_RED_SANDSTONE"), new MatPrep("CUT_RED_SANDSTONE"), new MatPrep("CUT_RED_SANDSTONE")), 
    CUT_RED_SANDSTONE_SLAB(new MatPrep(182, true), new MatPrep(182, true), new MatPrep(182, true), new MatPrep(182, true), new MatPrep(182, true), new MatPrep(182, true), new MatPrep("RED_SANDSTONE_SLAB", true), new MatPrep("RED_SANDSTONE_SLAB", true), new MatPrep("CUT_RED_SANDSTONE_SLAB"), new MatPrep("CUT_RED_SANDSTONE_SLAB")), 
    CUT_SANDSTONE(new MatPrep(24, 2), new MatPrep(24, 2), new MatPrep(24, 2), new MatPrep(24, 2), new MatPrep(24, 2), new MatPrep(24, 2), new MatPrep("CUT_SANDSTONE"), new MatPrep("CUT_SANDSTONE"), new MatPrep("CUT_SANDSTONE"), new MatPrep("CUT_SANDSTONE")), 
    CUT_SANDSTONE_SLAB(new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep("SANDSTONE_SLAB", true), new MatPrep("SANDSTONE_SLAB", true), new MatPrep("CUT_SANDSTONE_SLAB"), new MatPrep("CUT_SANDSTONE_SLAB")), 
    CYAN_BANNER(new MatPrep(425, 6)), 
    CYAN_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 9), new MatPrep("CYAN_BED"), new MatPrep("CYAN_BED"), new MatPrep("CYAN_BED"), new MatPrep("CYAN_BED")), 
    CYAN_CARPET(new MatPrep(171, 9)), 
    CYAN_CONCRETE(new MatPrep(159, 9, true), new MatPrep(159, 9, true), new MatPrep(159, 9, true), new MatPrep(159, 9, true), new MatPrep(159, 9, true), new MatPrep(251, 9), new MatPrep("CYAN_CONCRETE"), new MatPrep("CYAN_CONCRETE"), new MatPrep("CYAN_CONCRETE"), new MatPrep("CYAN_CONCRETE")), 
    CYAN_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 9), new MatPrep("CYAN_CONCRETE_POWDER"), new MatPrep("CYAN_CONCRETE_POWDER"), new MatPrep("CYAN_CONCRETE_POWDER"), new MatPrep("CYAN_CONCRETE_POWDER")), 
    CYAN_DYE(new MatPrep(351, 6), new MatPrep(351, 6), new MatPrep(351, 6), new MatPrep(351, 6), new MatPrep(351, 6), new MatPrep(351, 6), new MatPrep("CYAN_DYE"), new MatPrep("CYAN_DYE"), new MatPrep("CYAN_DYE"), new MatPrep("CYAN_DYE")), 
    CYAN_GLAZED_TERRACOTTA(new MatPrep(159, 9, true), new MatPrep(159, 9, true), new MatPrep(159, 9, true), new MatPrep(159, 9, true), new MatPrep(159, 9, true), new MatPrep(244), new MatPrep("CYAN_GLAZED_TERRACOTTA"), new MatPrep("CYAN_GLAZED_TERRACOTTA"), new MatPrep("CYAN_GLAZED_TERRACOTTA"), new MatPrep("CYAN_GLAZED_TERRACOTTA")), 
    CYAN_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(228), new MatPrep(228), new MatPrep("CYAN_SHULKER_BOX"), new MatPrep("CYAN_SHULKER_BOX"), new MatPrep("CYAN_SHULKER_BOX"), new MatPrep("CYAN_SHULKER_BOX")), 
    CYAN_STAINED_GLASS(new MatPrep(95, 9)), 
    CYAN_STAINED_GLASS_PANE(new MatPrep(160, 9)), 
    CYAN_TERRACOTTA(new MatPrep(159, 9)), 
    CYAN_WALL_BANNER(new MatPrep(425, 6)), 
    CYAN_WOOL(new MatPrep(35, 9)), 
    DAMAGED_ANVIL(new MatPrep(145, 2)), 
    DANDELION(new MatPrep(37)), 
    DARK_OAK_BOAT(new MatPrep(333), new MatPrep(448), new MatPrep(448), new MatPrep(448), new MatPrep(448), new MatPrep(448), new MatPrep("DARK_OAK_BOAT"), new MatPrep("DARK_OAK_BOAT"), new MatPrep("DARK_OAK_BOAT"), new MatPrep("DARK_OAK_BOAT")), 
    DARK_OAK_BUTTON(new MatPrep(143, true)), 
    DARK_OAK_DOOR(new MatPrep(431)), 
    DARK_OAK_FENCE(new MatPrep(191)), 
    DARK_OAK_FENCE_GATE(new MatPrep(186)), 
    DARK_OAK_LEAVES(new MatPrep(161, 1)), 
    DARK_OAK_LOG(new MatPrep(162, 1)), 
    DARK_OAK_PLANKS(new MatPrep(5, 5)), 
    DARK_OAK_PRESSURE_PLATE(new MatPrep(72, true)), 
    DARK_OAK_SAPLING(new MatPrep(6, 5)), 
    DARK_OAK_SIGN(new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep("SIGN"), new MatPrep("SIGN"), new MatPrep("DARK_OAK_SIGN"), new MatPrep("DARK_OAK_SIGN")), 
    DARK_OAK_SLAB(new MatPrep(126, 5)), 
    DARK_OAK_STAIRS(new MatPrep(164)), 
    DARK_OAK_TRAPDOOR(new MatPrep(96, true)), 
    DARK_OAK_WALL_SIGN(new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep("WALL_SIGN"), new MatPrep("WALL_SIGN"), new MatPrep("DARK_OAK_WALL_SIGN"), new MatPrep("DARK_OAK_WALL_SIGN")), 
    DARK_OAK_WOOD(new MatPrep(162, 1, true)), 
    DARK_PRISMARINE(new MatPrep(168, 2)), 
    DARK_PRISMARINE_SLAB(new MatPrep(44, 5, true)), 
    DARK_PRISMARINE_STAIRS(new MatPrep(109, true)), 
    DAYLIGHT_DETECTOR(new MatPrep(151)), 
    DEAD_BRAIN_CORAL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("DEAD_BRAIN_CORAL"), new MatPrep("DEAD_BRAIN_CORAL"), new MatPrep("DEAD_BRAIN_CORAL")), 
    DEAD_BRAIN_CORAL_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_BRAIN_CORAL_BLOCK"), new MatPrep("DEAD_BRAIN_CORAL_BLOCK"), new MatPrep("DEAD_BRAIN_CORAL_BLOCK"), new MatPrep("DEAD_BRAIN_CORAL_BLOCK")), 
    DEAD_BRAIN_CORAL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_BRAIN_CORAL_FAN"), new MatPrep("DEAD_BRAIN_CORAL_FAN"), new MatPrep("DEAD_BRAIN_CORAL_FAN"), new MatPrep("DEAD_BRAIN_CORAL_FAN")), 
    DEAD_BRAIN_CORAL_WALL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_BRAIN_CORAL_WALL_FAN"), new MatPrep("DEAD_BRAIN_CORAL_WALL_FAN"), new MatPrep("DEAD_BRAIN_CORAL_WALL_FAN"), new MatPrep("DEAD_BRAIN_CORAL_WALL_FAN")), 
    DEAD_BUBBLE_CORAL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("DEAD_BUBBLE_CORAL"), new MatPrep("DEAD_BUBBLE_CORAL"), new MatPrep("DEAD_BUBBLE_CORAL")), 
    DEAD_BUBBLE_CORAL_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_BUBBLE_CORAL_BLOCK"), new MatPrep("DEAD_BUBBLE_CORAL_BLOCK"), new MatPrep("DEAD_BUBBLE_CORAL_BLOCK"), new MatPrep("DEAD_BUBBLE_CORAL_BLOCK")), 
    DEAD_BUBBLE_CORAL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_BUBBLE_CORAL_FAN"), new MatPrep("DEAD_BUBBLE_CORAL_FAN"), new MatPrep("DEAD_BUBBLE_CORAL_FAN"), new MatPrep("DEAD_BUBBLE_CORAL_FAN")), 
    DEAD_BUBBLE_CORAL_WALL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_BUBBLE_CORAL_WALL_FAN"), new MatPrep("DEAD_BUBBLE_CORAL_WALL_FAN"), new MatPrep("DEAD_BUBBLE_CORAL_WALL_FAN"), new MatPrep("DEAD_BUBBLE_CORAL_WALL_FAN")), 
    DEAD_BUSH(new MatPrep(32)), 
    DEAD_FIRE_CORAL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("DEAD_FIRE_CORAL"), new MatPrep("DEAD_FIRE_CORAL"), new MatPrep("DEAD_FIRE_CORAL")), 
    DEAD_FIRE_CORAL_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_FIRE_CORAL_BLOCK"), new MatPrep("DEAD_FIRE_CORAL_BLOCK"), new MatPrep("DEAD_FIRE_CORAL_BLOCK"), new MatPrep("DEAD_FIRE_CORAL_BLOCK")), 
    DEAD_FIRE_CORAL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_FIRE_CORAL_FAN"), new MatPrep("DEAD_FIRE_CORAL_FAN"), new MatPrep("DEAD_FIRE_CORAL_FAN"), new MatPrep("DEAD_FIRE_CORAL_FAN")), 
    DEAD_FIRE_CORAL_WALL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_FIRE_CORAL_WALL_FAN"), new MatPrep("DEAD_FIRE_CORAL_WALL_FAN"), new MatPrep("DEAD_FIRE_CORAL_WALL_FAN"), new MatPrep("DEAD_FIRE_CORAL_WALL_FAN")), 
    DEAD_HORN_CORAL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("DEAD_HORN_CORAL"), new MatPrep("DEAD_HORN_CORAL"), new MatPrep("DEAD_HORN_CORAL")), 
    DEAD_HORN_CORAL_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_HORN_CORAL_BLOCK"), new MatPrep("DEAD_HORN_CORAL_BLOCK"), new MatPrep("DEAD_HORN_CORAL_BLOCK"), new MatPrep("DEAD_HORN_CORAL_BLOCK")), 
    DEAD_HORN_CORAL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_HORN_CORAL_FAN"), new MatPrep("DEAD_HORN_CORAL_FAN"), new MatPrep("DEAD_HORN_CORAL_FAN"), new MatPrep("DEAD_HORN_CORAL_FAN")), 
    DEAD_HORN_CORAL_WALL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_HORN_CORAL_WALL_FAN"), new MatPrep("DEAD_HORN_CORAL_WALL_FAN"), new MatPrep("DEAD_HORN_CORAL_WALL_FAN"), new MatPrep("DEAD_HORN_CORAL_WALL_FAN")), 
    DEAD_TUBE_CORAL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("DEAD_TUBE_CORAL"), new MatPrep("DEAD_TUBE_CORAL"), new MatPrep("DEAD_TUBE_CORAL")), 
    DEAD_TUBE_CORAL_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_TUBE_CORAL_BLOCK"), new MatPrep("DEAD_TUBE_CORAL_BLOCK"), new MatPrep("DEAD_TUBE_CORAL_BLOCK"), new MatPrep("DEAD_TUBE_CORAL_BLOCK")), 
    DEAD_TUBE_CORAL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_TUBE_CORAL_FAN"), new MatPrep("DEAD_TUBE_CORAL_FAN"), new MatPrep("DEAD_TUBE_CORAL_FAN"), new MatPrep("DEAD_TUBE_CORAL_FAN")), 
    DEAD_TUBE_CORAL_WALL_FAN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DEAD_TUBE_CORAL_WALL_FAN"), new MatPrep("DEAD_TUBE_CORAL_WALL_FAN"), new MatPrep("DEAD_TUBE_CORAL_WALL_FAN"), new MatPrep("DEAD_TUBE_CORAL_WALL_FAN")), 
    DEBUG_STICK(new MatPrep(280, true)), 
    DETECTOR_RAIL(new MatPrep(28)), 
    DIAMOND(new MatPrep(264)), 
    DIAMOND_AXE(new MatPrep(279)), 
    DIAMOND_BLOCK(new MatPrep(57)), 
    DIAMOND_BOOTS(new MatPrep(313)), 
    DIAMOND_CHESTPLATE(new MatPrep(311)), 
    DIAMOND_HELMET(new MatPrep(310)), 
    DIAMOND_HOE(new MatPrep(293)), 
    DIAMOND_HORSE_ARMOR(new MatPrep(419)), 
    DIAMOND_LEGGINGS(new MatPrep(312)), 
    DIAMOND_ORE(new MatPrep(56)), 
    DIAMOND_PICKAXE(new MatPrep(278)), 
    DIAMOND_SHOVEL(new MatPrep(277)), 
    DIAMOND_SWORD(new MatPrep(276)), 
    DIORITE(new MatPrep(1, 3)), 
    DIORITE_SLAB(new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("DIORITE_SLAB"), new MatPrep("DIORITE_SLAB")), 
    DIORITE_STAIRS(new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("DIORITE_STAIRS"), new MatPrep("DIORITE_STAIRS")), 
    DIORITE_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("DIORITE_WALL"), new MatPrep("DIORITE_WALL")), 
    DIRT(new MatPrep(3), new MatPrep(3), new MatPrep(3), new MatPrep(3), new MatPrep(3), new MatPrep(3), new MatPrep("DIRT"), new MatPrep("DIRT"), new MatPrep("DIRT"), new MatPrep("DIRT")), 
    DISPENSER(new MatPrep(23)), 
    DOLPHIN_SPAWN_EGG(new MatPrep(383, 94, true)), 
    DONKEY_SPAWN_EGG(new MatPrep(383, 100, true)), 
    DRAGON_BREATH(new MatPrep(0, true), new MatPrep(437), new MatPrep(437), new MatPrep(437), new MatPrep(437), new MatPrep(437), new MatPrep("DRAGON_BREATH"), new MatPrep("DRAGON_BREATH"), new MatPrep("DRAGON_BREATH"), new MatPrep("DRAGON_BREATH")), 
    DRAGON_EGG(new MatPrep(122)), 
    DRAGON_HEAD(new MatPrep(0, true), new MatPrep(397, 5), new MatPrep(397, 5), new MatPrep(397, 5), new MatPrep(397, 5), new MatPrep(397, 5), new MatPrep("DRAGON_HEAD"), new MatPrep("DRAGON_HEAD"), new MatPrep("DRAGON_HEAD"), new MatPrep("DRAGON_HEAD")), 
    DRAGON_WALL_HEAD(new MatPrep(397, true), new MatPrep(397, 5), new MatPrep(397, 5), new MatPrep(397, 5), new MatPrep(397, 5), new MatPrep(397, 5), new MatPrep("DRAGON_WALL_HEAD"), new MatPrep("DRAGON_WALL_HEAD"), new MatPrep("DRAGON_WALL_HEAD"), new MatPrep("DRAGON_WALL_HEAD")), 
    DRIED_KELP(new MatPrep(392, true), new MatPrep(392, true), new MatPrep(392, true), new MatPrep(392, true), new MatPrep(392, true), new MatPrep(392, true), new MatPrep("DRIED_KELP"), new MatPrep("DRIED_KELP"), new MatPrep("DRIED_KELP"), new MatPrep("DRIED_KELP")), 
    DRIED_KELP_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("DRIED_KELP_BLOCK"), new MatPrep("DRIED_KELP_BLOCK"), new MatPrep("DRIED_KELP_BLOCK"), new MatPrep("DRIED_KELP_BLOCK")), 
    DROPPER(new MatPrep(158)), 
    DROWNED_SPAWN_EGG(new MatPrep(383, 54, true)), 
    EGG(new MatPrep(344)), 
    ELDER_GUARDIAN_SPAWN_EGG(new MatPrep(383, 68, true)), 
    ELYTRA(new MatPrep(299, true), new MatPrep(443), new MatPrep(443), new MatPrep(443), new MatPrep(443), new MatPrep(443), new MatPrep("ELYTRA"), new MatPrep("ELYTRA"), new MatPrep("ELYTRA"), new MatPrep("ELYTRA")), 
    EMERALD(new MatPrep(388)), 
    EMERALD_BLOCK(new MatPrep(133)), 
    EMERALD_ORE(new MatPrep(129)), 
    ENCHANTED_BOOK(new MatPrep(403)), 
    ENCHANTED_GOLDEN_APPLE(new MatPrep(322, 1)), 
    ENCHANTING_TABLE(new MatPrep(116)), 
    ENDERMAN_SPAWN_EGG(new MatPrep(383, 58)), 
    ENDERMITE_SPAWN_EGG(new MatPrep(383, 67)), 
    ENDER_CHEST(new MatPrep(130)), 
    ENDER_EYE(new MatPrep(381)), 
    ENDER_PEARL(new MatPrep(368)), 
    END_CRYSTAL(new MatPrep(46, true), new MatPrep(426), new MatPrep(426), new MatPrep(426), new MatPrep(426), new MatPrep(426), new MatPrep("END_CRYSTAL"), new MatPrep("END_CRYSTAL"), new MatPrep("END_CRYSTAL"), new MatPrep("END_CRYSTAL")), 
    END_GATEWAY(new MatPrep(209)), 
    END_PORTAL(new MatPrep(119)), 
    END_PORTAL_FRAME(new MatPrep(120)), 
    END_ROD(new MatPrep(50, true), new MatPrep(198), new MatPrep(198), new MatPrep(198), new MatPrep(198), new MatPrep(198), new MatPrep("END_ROD"), new MatPrep("END_ROD"), new MatPrep("END_ROD"), new MatPrep("END_ROD")), 
    END_STONE(new MatPrep(121)), 
    END_STONE_BRICKS(new MatPrep(121, true), new MatPrep(206), new MatPrep(206), new MatPrep(206), new MatPrep(206), new MatPrep(206), new MatPrep("END_STONE_BRICKS"), new MatPrep("END_STONE_BRICKS"), new MatPrep("END_STONE_BRICKS"), new MatPrep("END_STONE_BRICKS")), 
    END_STONE_BRICK_SLAB(new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep("STONE_BRICK_SLAB", true), new MatPrep("STONE_BRICK_SLAB", true), new MatPrep("END_STONE_BRICK_SLAB"), new MatPrep("END_STONE_BRICK_SLAB")), 
    END_STONE_BRICK_STAIRS(new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep("STONE_BRICK_STAIRS", true), new MatPrep("STONE_BRICK_STAIRS", true), new MatPrep("END_STONE_BRICK_STAIRS"), new MatPrep("END_STONE_BRICK_STAIRS")), 
    END_STONE_BRICK_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("END_STONE_BRICK_WALL"), new MatPrep("END_STONE_BRICK_WALL")), 
    EVOKER_SPAWN_EGG(new MatPrep(383, 120, true)), 
    EXPERIENCE_BOTTLE(new MatPrep(384)), 
    FARMLAND(new MatPrep(60)), 
    FEATHER(new MatPrep(288)), 
    FERMENTED_SPIDER_EYE(new MatPrep(376)), 
    FERN(new MatPrep(31, 2)), 
    FILLED_MAP(new MatPrep(358)), 
    FIRE, 
    FIREWORK_ROCKET(new MatPrep(401)), 
    FIREWORK_STAR(new MatPrep(402)), 
    FIRE_CHARGE(new MatPrep(385)), 
    FIRE_CORAL(new MatPrep(0, true)), 
    FIRE_CORAL_BLOCK(new MatPrep(0, true)), 
    FIRE_CORAL_FAN(new MatPrep(0, true)), 
    FIRE_CORAL_WALL_FAN(new MatPrep(0, true)), 
    FISHING_ROD(new MatPrep(346)), 
    FLETCHING_TABLE(new MatPrep(58, true), new MatPrep(58, true), new MatPrep(58, true), new MatPrep(58, true), new MatPrep(58, true), new MatPrep(58, true), new MatPrep("CRAFTING_TABLE", true), new MatPrep("CRAFTING_TABLE", true), new MatPrep("FLETCHING_TABLE"), new MatPrep("FLETCHING_TABLE")), 
    FLINT(new MatPrep(318)), 
    FLINT_AND_STEEL(new MatPrep(259)), 
    FLOWER_BANNER_PATTERN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("FLOWER_BANNER_PATTERN"), new MatPrep("FLOWER_BANNER_PATTERN")), 
    FLOWER_POT(new MatPrep(390)), 
    FOX_SPAWN_EGG(new MatPrep(383, 95, true), new MatPrep(383, 95, true), new MatPrep(383, 95, true), new MatPrep(383, 95, true), new MatPrep(383, 95, true), new MatPrep(383, 95, true), new MatPrep("WOLF_SPAWN_EGG", true), new MatPrep("WOLF_SPAWN_EGG", true), new MatPrep("FOX_SPAWN_EGG"), new MatPrep("FOX_SPAWN_EGG")), 
    FROSTED_ICE(new MatPrep(212)), 
    FURNACE(new MatPrep(61)), 
    BURNING_FURNACE(new MatPrep(62), new MatPrep("LEGACY_BURNING_FURNACE")), 
    FURNACE_MINECART(new MatPrep(343)), 
    GHAST_SPAWN_EGG(new MatPrep(383, 56)), 
    GHAST_TEAR(new MatPrep(370)), 
    GLASS(new MatPrep(20)), 
    GLASS_BOTTLE(new MatPrep(374)), 
    GLASS_PANE(new MatPrep(102)), 
    GLISTERING_MELON_SLICE(new MatPrep(382)), 
    GLOBE_BANNER_PATTERN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("GLOBE_BANNER_PATTERN"), new MatPrep("GLOBE_BANNER_PATTERN")), 
    GLOWSTONE(new MatPrep(89)), 
    GLOWSTONE_DUST(new MatPrep(348)), 
    GOLDEN_APPLE(new MatPrep(322)), 
    GOLDEN_AXE(new MatPrep(286)), 
    GOLDEN_BOOTS(new MatPrep(317)), 
    GOLDEN_CARROT(new MatPrep(396)), 
    GOLDEN_CHESTPLATE(new MatPrep(315)), 
    GOLDEN_HELMET(new MatPrep(314)), 
    GOLDEN_HOE(new MatPrep(294)), 
    GOLDEN_HORSE_ARMOR(new MatPrep(418)), 
    GOLDEN_LEGGINGS(new MatPrep(316)), 
    GOLDEN_PICKAXE(new MatPrep(285)), 
    GOLDEN_SHOVEL(new MatPrep(284)), 
    GOLDEN_SWORD(new MatPrep(283)), 
    GOLD_BLOCK(new MatPrep(41)), 
    GOLD_INGOT(new MatPrep(266)), 
    GOLD_NUGGET(new MatPrep(371)), 
    GOLD_ORE(new MatPrep(14)), 
    GRANITE(new MatPrep(1, 1)), 
    GRANITE_SLAB(new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("GRANITE_SLAB"), new MatPrep("GRANITE_SLAB")), 
    GRANITE_STAIRS(new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("GRANITE_STAIRS"), new MatPrep("GRANITE_STAIRS")), 
    GRANITE_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("GRANITE_WALL"), new MatPrep("GRANITE_WALL")), 
    GRASS(new MatPrep(2)), 
    GRASS_BLOCK(new MatPrep(2)), 
    GRASS_PATH(new MatPrep(2, true), new MatPrep(208), new MatPrep(208), new MatPrep(208), new MatPrep(208), new MatPrep(208), new MatPrep("GRASS_PATH"), new MatPrep("GRASS_PATH"), new MatPrep("GRASS_PATH"), new MatPrep("GRASS_PATH")), 
    GRAVEL(new MatPrep(13)), 
    GRAY_BANNER(new MatPrep(425, 8)), 
    GRAY_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 7), new MatPrep("GRAY_BED"), new MatPrep("GRAY_BED"), new MatPrep("GRAY_BED"), new MatPrep("GRAY_BED")), 
    GRAY_CARPET(new MatPrep(171, 7)), 
    GRAY_CONCRETE(new MatPrep(159, 7, true), new MatPrep(159, 7, true), new MatPrep(159, 7, true), new MatPrep(159, 7, true), new MatPrep(159, 7, true), new MatPrep(251, 7), new MatPrep("GRAY_CONCRETE"), new MatPrep("GRAY_CONCRETE"), new MatPrep("GRAY_CONCRETE"), new MatPrep("GRAY_CONCRETE")), 
    GRAY_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 7), new MatPrep("GRAY_CONCRETE_POWDER"), new MatPrep("GRAY_CONCRETE_POWDER"), new MatPrep("GRAY_CONCRETE_POWDER"), new MatPrep("GRAY_CONCRETE_POWDER")), 
    GRAY_DYE(new MatPrep(351, 8), new MatPrep(351, 8), new MatPrep(351, 8), new MatPrep(351, 8), new MatPrep(351, 8), new MatPrep(351, 8), new MatPrep("GRAY_DYE"), new MatPrep("GRAY_DYE"), new MatPrep("GRAY_DYE"), new MatPrep("GRAY_DYE")), 
    GRAY_GLAZED_TERRACOTTA(new MatPrep(159, 7, true), new MatPrep(159, 7, true), new MatPrep(159, 7, true), new MatPrep(159, 7, true), new MatPrep(159, 7, true), new MatPrep(242), new MatPrep("GRAY_GLAZED_TERRACOTTA"), new MatPrep("GRAY_GLAZED_TERRACOTTA"), new MatPrep("GRAY_GLAZED_TERRACOTTA"), new MatPrep("GRAY_GLAZED_TERRACOTTA")), 
    GRAY_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(226), new MatPrep(226), new MatPrep("GRAY_SHULKER_BOX"), new MatPrep("GRAY_SHULKER_BOX"), new MatPrep("GRAY_SHULKER_BOX"), new MatPrep("GRAY_SHULKER_BOX")), 
    GRAY_STAINED_GLASS(new MatPrep(95, 7)), 
    GRAY_STAINED_GLASS_PANE(new MatPrep(160, 7)), 
    GRAY_TERRACOTTA(new MatPrep(159, 7)), 
    GRAY_WALL_BANNER(new MatPrep(425, 8)), 
    GRAY_WOOL(new MatPrep(35, 7)), 
    GREEN_BANNER(new MatPrep(425, 2)), 
    GREEN_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 13), new MatPrep("GREEN_BED"), new MatPrep("GREEN_BED"), new MatPrep("GREEN_BED"), new MatPrep("GREEN_BED")), 
    GREEN_CARPET(new MatPrep(171, 13)), 
    GREEN_CONCRETE(new MatPrep(159, 13, true), new MatPrep(159, 13, true), new MatPrep(159, 13, true), new MatPrep(159, 13, true), new MatPrep(159, 13, true), new MatPrep(251, 13), new MatPrep("GREEN_CONCRETE"), new MatPrep("GREEN_CONCRETE"), new MatPrep("GREEN_CONCRETE"), new MatPrep("GREEN_CONCRETE")), 
    GREEN_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 13), new MatPrep("GREEN_CONCRETE_POWDER"), new MatPrep("GREEN_CONCRETE_POWDER"), new MatPrep("GREEN_CONCRETE_POWDER"), new MatPrep("GREEN_CONCRETE_POWDER")), 
    GREEN_DYE(new MatPrep(351, 2), new MatPrep(351, 2), new MatPrep(351, 2), new MatPrep(351, 2), new MatPrep(351, 2), new MatPrep(351, 2), new MatPrep("CACTUS_GREEN"), new MatPrep("CACTUS_GREEN"), new MatPrep("GREEN_DYE"), new MatPrep("GREEN_DYE")), 
    GREEN_GLAZED_TERRACOTTA(new MatPrep(159, 13, true), new MatPrep(159, 13, true), new MatPrep(159, 13, true), new MatPrep(159, 13, true), new MatPrep(159, 13, true), new MatPrep(248), new MatPrep("GREEN_GLAZED_TERRACOTTA"), new MatPrep("GREEN_GLAZED_TERRACOTTA"), new MatPrep("GREEN_GLAZED_TERRACOTTA"), new MatPrep("GREEN_GLAZED_TERRACOTTA")), 
    GREEN_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(232), new MatPrep(232), new MatPrep("GREEN_SHULKER_BOX"), new MatPrep("GREEN_SHULKER_BOX"), new MatPrep("GREEN_SHULKER_BOX"), new MatPrep("GREEN_SHULKER_BOX")), 
    GREEN_STAINED_GLASS(new MatPrep(95, 13)), 
    GREEN_STAINED_GLASS_PANE(new MatPrep(160, 13)), 
    GREEN_TERRACOTTA(new MatPrep(159, 13)), 
    GREEN_WALL_BANNER(new MatPrep(425, 2)), 
    GREEN_WOOL(new MatPrep(35, 13)), 
    GRINDSTONE(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("GRINDSTONE"), new MatPrep("GRINDSTONE")), 
    GUARDIAN_SPAWN_EGG(new MatPrep(383, 68), new MatPrep(383, 68), new MatPrep(383, 68), new MatPrep(383, 68), new MatPrep(383, 68), new MatPrep(383, 68), new MatPrep("GUARDIAN_SPAWN_EGG"), new MatPrep("GUARDIAN_SPAWN_EGG"), new MatPrep("GUARDIAN_SPAWN_EGG"), new MatPrep("GUARDIAN_SPAWN_EGG")), 
    GUNPOWDER(new MatPrep(289)), 
    HAY_BLOCK(new MatPrep(170)), 
    HEART_OF_THE_SEA(new MatPrep(0, true)), 
    HEAVY_WEIGHTED_PRESSURE_PLATE(new MatPrep(148)), 
    HONEYCOMB(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("HONEYCOMB")), 
    HONEYCOMB_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("HONEYCOMB_BLOCK")), 
    HONEY_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("HONEY_BLOCK")), 
    HONEY_BOTTLE(new MatPrep(374, true), new MatPrep(374, true), new MatPrep(374, true), new MatPrep(374, true), new MatPrep(374, true), new MatPrep(374, true), new MatPrep("GLASS_BOTTLE", true), new MatPrep("GLASS_BOTTLE", true), new MatPrep("GLASS_BOTTLE", true), new MatPrep("HONEY_BOTTLE")), 
    HOPPER(new MatPrep(154)), 
    HOPPER_MINECART(new MatPrep(408)), 
    HORN_CORAL(new MatPrep(0, true)), 
    HORN_CORAL_BLOCK(new MatPrep(0, true)), 
    HORN_CORAL_FAN(new MatPrep(0, true)), 
    HORN_CORAL_WALL_FAN(new MatPrep(0, true)), 
    HORSE_SPAWN_EGG(new MatPrep(383, 100)), 
    HUSK_SPAWN_EGG(new MatPrep(383, 54, true)), 
    ICE(new MatPrep(79)), 
    INFESTED_CHISELED_STONE_BRICKS(new MatPrep(97, 5)), 
    INFESTED_COBBLESTONE(new MatPrep(97, 1)), 
    INFESTED_CRACKED_STONE_BRICKS(new MatPrep(97, 4)), 
    INFESTED_MOSSY_STONE_BRICKS(new MatPrep(97, 3)), 
    INFESTED_STONE(new MatPrep(97)), 
    INFESTED_STONE_BRICKS(new MatPrep(97, 2)), 
    INK_SAC(new MatPrep(351)), 
    IRON_AXE(new MatPrep(258)), 
    IRON_BARS(new MatPrep(101)), 
    IRON_BLOCK(new MatPrep(42)), 
    IRON_BOOTS(new MatPrep(309)), 
    IRON_CHESTPLATE(new MatPrep(307)), 
    IRON_DOOR(new MatPrep(330)), 
    IRON_HELMET(new MatPrep(306)), 
    IRON_HOE(new MatPrep(292)), 
    IRON_HORSE_ARMOR(new MatPrep(417)), 
    IRON_INGOT(new MatPrep(265)), 
    IRON_LEGGINGS(new MatPrep(308)), 
    IRON_NUGGET(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(452), new MatPrep(452), new MatPrep("IRON_NUGGET"), new MatPrep("IRON_NUGGET"), new MatPrep("IRON_NUGGET"), new MatPrep("IRON_NUGGET")), 
    IRON_ORE(new MatPrep(15)), 
    IRON_PICKAXE(new MatPrep(257)), 
    IRON_SHOVEL(new MatPrep(256)), 
    IRON_SWORD(new MatPrep(267)), 
    IRON_TRAPDOOR(new MatPrep(167)), 
    ITEM_FRAME(new MatPrep(389)), 
    JACK_O_LANTERN(new MatPrep(91)), 
    JIGSAW(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("JIGSAW"), new MatPrep("JIGSAW")), 
    JUKEBOX(new MatPrep(84)), 
    JUNGLE_BOAT(new MatPrep(333), new MatPrep(446), new MatPrep(446), new MatPrep(446), new MatPrep(446), new MatPrep(446), new MatPrep("JUNGLE_BOAT"), new MatPrep("JUNGLE_BOAT"), new MatPrep("JUNGLE_BOAT"), new MatPrep("JUNGLE_BOAT")), 
    JUNGLE_BUTTON(new MatPrep(143, true)), 
    JUNGLE_DOOR(new MatPrep(429)), 
    JUNGLE_FENCE(new MatPrep(190)), 
    JUNGLE_FENCE_GATE(new MatPrep(185)), 
    JUNGLE_LEAVES(new MatPrep(18, 3)), 
    JUNGLE_LOG(new MatPrep(17, 3)), 
    JUNGLE_PLANKS(new MatPrep(5, 3)), 
    JUNGLE_PRESSURE_PLATE(new MatPrep(72, true)), 
    JUNGLE_SAPLING(new MatPrep(6, 3)), 
    JUNGLE_SIGN(new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep("SIGN"), new MatPrep("SIGN"), new MatPrep("JUNGLE_SIGN"), new MatPrep("JUNGLE_SIGN")), 
    JUNGLE_SLAB(new MatPrep(126, 3)), 
    JUNGLE_STAIRS(new MatPrep(136)), 
    JUNGLE_TRAPDOOR(new MatPrep(96, true)), 
    JUNGLE_WALL_SIGN(new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep("WALL_SIGN"), new MatPrep("WALL_SIGN"), new MatPrep("JUNGLE_WALL_SIGN"), new MatPrep("JUNGLE_WALL_SIGN")), 
    JUNGLE_WOOD(new MatPrep(17, 3, true)), 
    KELP(new MatPrep(295, true)), 
    KELP_PLANT(new MatPrep(83, true)), 
    KNOWLEDGE_BOOK(new MatPrep(340, true)), 
    LADDER(new MatPrep(65)), 
    LANTERN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("LANTERN"), new MatPrep("LANTERN")), 
    LAPIS_BLOCK(new MatPrep(22)), 
    LAPIS_LAZULI(new MatPrep(351, 4)), 
    LAPIS_ORE(new MatPrep(21)), 
    LARGE_FERN(new MatPrep(175, 3)), 
    LAVA, 
    LAVA_BUCKET(new MatPrep(327)), 
    LEAD(new MatPrep(420)), 
    LEATHER(new MatPrep(334)), 
    LEATHER_BOOTS(new MatPrep(301)), 
    LEATHER_CHESTPLATE(new MatPrep(299)), 
    LEATHER_HELMET(new MatPrep(298)), 
    LEATHER_HORSE_ARMOR(new MatPrep(418, true), new MatPrep(418, true), new MatPrep(418, true), new MatPrep(418, true), new MatPrep(418, true), new MatPrep(418, true), new MatPrep("IRON_HORSE_ARMOR", true), new MatPrep("IRON_HORSE_ARMOR", true), new MatPrep("LEATHER_HORSE_ARMOR"), new MatPrep("LEATHER_HORSE_ARMOR")), 
    LEATHER_LEGGINGS(new MatPrep(300)), 
    LECTERN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("LECTERN"), new MatPrep("LECTERN")), 
    LEVER(new MatPrep(69)), 
    LIGHT_BLUE_BANNER(new MatPrep(425, 12)), 
    LIGHT_BLUE_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 3), new MatPrep("LIGHT_BLUE_BED"), new MatPrep("LIGHT_BLUE_BED"), new MatPrep("LIGHT_BLUE_BED"), new MatPrep("LIGHT_BLUE_BED")), 
    LIGHT_BLUE_CARPET(new MatPrep(171, 3)), 
    LIGHT_BLUE_CONCRETE(new MatPrep(159, 3, true), new MatPrep(159, 3, true), new MatPrep(159, 3, true), new MatPrep(159, 3, true), new MatPrep(159, 3, true), new MatPrep(251, 3), new MatPrep("LIGHT_BLUE_CONCRETE"), new MatPrep("LIGHT_BLUE_CONCRETE"), new MatPrep("LIGHT_BLUE_CONCRETE"), new MatPrep("LIGHT_BLUE_CONCRETE")), 
    LIGHT_BLUE_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 3), new MatPrep("LIGHT_BLUE_CONCRETE_POWDER"), new MatPrep("LIGHT_BLUE_CONCRETE_POWDER"), new MatPrep("LIGHT_BLUE_CONCRETE_POWDER"), new MatPrep("LIGHT_BLUE_CONCRETE_POWDER")), 
    LIGHT_BLUE_DYE(new MatPrep(351, 12), new MatPrep(351, 12), new MatPrep(351, 12), new MatPrep(351, 12), new MatPrep(351, 12), new MatPrep(351, 12), new MatPrep("LIGHT_BLUE_DYE"), new MatPrep("LIGHT_BLUE_DYE"), new MatPrep("LIGHT_BLUE_DYE"), new MatPrep("LIGHT_BLUE_DYE")), 
    LIGHT_BLUE_GLAZED_TERRACOTTA(new MatPrep(159, 3, true), new MatPrep(159, 3, true), new MatPrep(159, 3, true), new MatPrep(159, 3, true), new MatPrep(159, 3, true), new MatPrep(238), new MatPrep("LIGHT_BLUE_GLAZED_TERRACOTTA"), new MatPrep("LIGHT_BLUE_GLAZED_TERRACOTTA"), new MatPrep("LIGHT_BLUE_GLAZED_TERRACOTTA"), new MatPrep("LIGHT_BLUE_GLAZED_TERRACOTTA")), 
    LIGHT_BLUE_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(222), new MatPrep(222), new MatPrep("LIGHT_BLUE_SHULKER_BOX"), new MatPrep("LIGHT_BLUE_SHULKER_BOX"), new MatPrep("LIGHT_BLUE_SHULKER_BOX"), new MatPrep("LIGHT_BLUE_SHULKER_BOX")), 
    LIGHT_BLUE_STAINED_GLASS(new MatPrep(95, 3)), 
    LIGHT_BLUE_STAINED_GLASS_PANE(new MatPrep(160, 3)), 
    LIGHT_BLUE_TERRACOTTA(new MatPrep(159, 3)), 
    LIGHT_BLUE_WALL_BANNER(new MatPrep(425, 12)), 
    LIGHT_BLUE_WOOL(new MatPrep(35, 3)), 
    LIGHT_GRAY_BANNER(new MatPrep(425, 7)), 
    LIGHT_GRAY_BED(new MatPrep(355, 8)), 
    LIGHT_GRAY_CARPET(new MatPrep(171, 8)), 
    LIGHT_GRAY_CONCRETE(new MatPrep(159, 8, true), new MatPrep(159, 8, true), new MatPrep(159, 8, true), new MatPrep(159, 8, true), new MatPrep(159, 8, true), new MatPrep(251, 8), new MatPrep("LIGHT_GRAY_CONCRETE"), new MatPrep("LIGHT_GRAY_CONCRETE"), new MatPrep("LIGHT_GRAY_CONCRETE"), new MatPrep("LIGHT_GRAY_CONCRETE")), 
    LIGHT_GRAY_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 8), new MatPrep("LIGHT_GRAY_CONCRETE_POWDER"), new MatPrep("LIGHT_GRAY_CONCRETE_POWDER"), new MatPrep("LIGHT_GRAY_CONCRETE_POWDER"), new MatPrep("LIGHT_GRAY_CONCRETE_POWDER")), 
    LIGHT_GRAY_DYE(new MatPrep(351, 7), new MatPrep(351, 7), new MatPrep(351, 7), new MatPrep(351, 7), new MatPrep(351, 7), new MatPrep(351, 7), new MatPrep("LIGHT_GRAY_DYE"), new MatPrep("LIGHT_GRAY_DYE"), new MatPrep("LIGHT_GRAY_DYE"), new MatPrep("LIGHT_GRAY_DYE")), 
    LIGHT_GRAY_GLAZED_TERRACOTTA(new MatPrep(159, 8, true), new MatPrep(159, 8, true), new MatPrep(159, 8, true), new MatPrep(159, 8, true), new MatPrep(159, 8, true), new MatPrep(243), new MatPrep("LIGHT_GRAY_GLAZED_TERRACOTTA"), new MatPrep("LIGHT_GRAY_GLAZED_TERRACOTTA"), new MatPrep("LIGHT_GRAY_GLAZED_TERRACOTTA"), new MatPrep("LIGHT_GRAY_GLAZED_TERRACOTTA")), 
    LIGHT_GRAY_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(227), new MatPrep(227), new MatPrep("LIGHT_GRAY_SHULKER_BOX"), new MatPrep("LIGHT_GRAY_SHULKER_BOX"), new MatPrep("LIGHT_GRAY_SHULKER_BOX"), new MatPrep("LIGHT_GRAY_SHULKER_BOX")), 
    LIGHT_GRAY_STAINED_GLASS(new MatPrep(95, 8)), 
    LIGHT_GRAY_STAINED_GLASS_PANE(new MatPrep(160, 8)), 
    LIGHT_GRAY_TERRACOTTA(new MatPrep(159, 8)), 
    LIGHT_GRAY_WALL_BANNER(new MatPrep(425, 7)), 
    LIGHT_GRAY_WOOL(new MatPrep(35, 8)), 
    LIGHT_WEIGHTED_PRESSURE_PLATE(new MatPrep(147)), 
    LILAC(new MatPrep(175, 1), new MatPrep(175, 1), new MatPrep(175, 1), new MatPrep(175, 1), new MatPrep(175, 1), new MatPrep(175, 1), new MatPrep("LILAC"), new MatPrep("LILAC"), new MatPrep("LILAC"), new MatPrep("LILAC")), 
    LILY_OF_THE_VALLEY(new MatPrep(175, 1, true), new MatPrep(175, 1, true), new MatPrep(175, 1, true), new MatPrep(175, 1, true), new MatPrep(175, 1, true), new MatPrep(175, 1, true), new MatPrep("LILAC", true), new MatPrep("LILAC", true), new MatPrep("LILY_OF_THE_VALLEY"), new MatPrep("LILY_OF_THE_VALLEY")), 
    LILY_PAD(new MatPrep(111)), 
    LIME_BANNER(new MatPrep(425, 10)), 
    LIME_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 5), new MatPrep("LIME_BED"), new MatPrep("LIME_BED"), new MatPrep("LIME_BED"), new MatPrep("LIME_BED")), 
    LIME_CARPET(new MatPrep(171, 5)), 
    LIME_CONCRETE(new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(251, 10), new MatPrep("LIME_CONCRETE"), new MatPrep("LIME_CONCRETE"), new MatPrep("LIME_CONCRETE"), new MatPrep("LIME_CONCRETE")), 
    LIME_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 5), new MatPrep("LIME_CONCRETE_POWDER"), new MatPrep("LIME_CONCRETE_POWDER"), new MatPrep("LIME_CONCRETE_POWDER"), new MatPrep("LIME_CONCRETE_POWDER")), 
    LIME_DYE(new MatPrep(351, 10), new MatPrep(351, 10), new MatPrep(351, 10), new MatPrep(351, 10), new MatPrep(351, 10), new MatPrep(351, 10), new MatPrep("LIME_DYE"), new MatPrep("LIME_DYE"), new MatPrep("LIME_DYE"), new MatPrep("LIME_DYE")), 
    LIME_GLAZED_TERRACOTTA(new MatPrep(159, 5, true), new MatPrep(159, 5, true), new MatPrep(159, 5, true), new MatPrep(159, 5, true), new MatPrep(159, 5, true), new MatPrep(240), new MatPrep("LIME_GLAZED_TERRACOTTA"), new MatPrep("LIME_GLAZED_TERRACOTTA"), new MatPrep("LIME_GLAZED_TERRACOTTA"), new MatPrep("LIME_GLAZED_TERRACOTTA")), 
    LIME_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(224), new MatPrep(224), new MatPrep("LIME_SHULKER_BOX"), new MatPrep("LIME_SHULKER_BOX"), new MatPrep("LIME_SHULKER_BOX"), new MatPrep("LIME_SHULKER_BOX")), 
    LIME_STAINED_GLASS(new MatPrep(95, 5)), 
    LIME_STAINED_GLASS_PANE(new MatPrep(160, 5)), 
    LIME_TERRACOTTA(new MatPrep(159, 5)), 
    LIME_WALL_BANNER(new MatPrep(425, 10)), 
    LIME_WOOL(new MatPrep(35, 5)), 
    LINGERING_POTION(new MatPrep(373, true), new MatPrep(441), new MatPrep(441), new MatPrep(441), new MatPrep(441), new MatPrep(441), new MatPrep("LINGERING_POTION"), new MatPrep("LINGERING_POTION"), new MatPrep("LINGERING_POTION"), new MatPrep("LINGERING_POTION")), 
    LLAMA_SPAWN_EGG(new MatPrep(383, 100, true), new MatPrep(383, 100, true), new MatPrep(383, 100, true), new MatPrep(383, 100, true), new MatPrep(383, 103), new MatPrep(383, 103), new MatPrep("LLAMA_SPAWN_EGG"), new MatPrep("LLAMA_SPAWN_EGG"), new MatPrep("LLAMA_SPAWN_EGG"), new MatPrep("LLAMA_SPAWN_EGG")), 
    LOOM(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("LOOM"), new MatPrep("LOOM")), 
    MAGENTA_BANNER(new MatPrep(425, 13)), 
    MAGENTA_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 2), new MatPrep("MAGENTA_BED"), new MatPrep("MAGENTA_BED"), new MatPrep("MAGENTA_BED"), new MatPrep("MAGENTA_BED")), 
    MAGENTA_CARPET(new MatPrep(171, 2)), 
    MAGENTA_CONCRETE(new MatPrep(159, 2, true), new MatPrep(159, 2, true), new MatPrep(159, 2, true), new MatPrep(159, 2, true), new MatPrep(159, 2, true), new MatPrep(251, 2), new MatPrep("MAGENTA_CONCRETE"), new MatPrep("MAGENTA_CONCRETE"), new MatPrep("MAGENTA_CONCRETE"), new MatPrep("MAGENTA_CONCRETE")), 
    MAGENTA_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 2), new MatPrep("MAGENTA_CONCRETE_POWDER"), new MatPrep("MAGENTA_CONCRETE_POWDER"), new MatPrep("MAGENTA_CONCRETE_POWDER"), new MatPrep("MAGENTA_CONCRETE_POWDER")), 
    MAGENTA_DYE(new MatPrep(351, 13), new MatPrep(351, 13), new MatPrep(351, 13), new MatPrep(351, 13), new MatPrep(351, 13), new MatPrep(351, 13), new MatPrep("MAGENTA_DYE"), new MatPrep("MAGENTA_DYE"), new MatPrep("MAGENTA_DYE"), new MatPrep("MAGENTA_DYE")), 
    MAGENTA_GLAZED_TERRACOTTA(new MatPrep(159, 2, true), new MatPrep(159, 2, true), new MatPrep(159, 2, true), new MatPrep(159, 2, true), new MatPrep(159, 2, true), new MatPrep(237), new MatPrep("MAGENTA_GLAZED_TERRACOTTA"), new MatPrep("MAGENTA_GLAZED_TERRACOTTA"), new MatPrep("MAGENTA_GLAZED_TERRACOTTA"), new MatPrep("MAGENTA_GLAZED_TERRACOTTA")), 
    MAGENTA_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(221), new MatPrep(221), new MatPrep("MAGENTA_SHULKER_BOX"), new MatPrep("MAGENTA_SHULKER_BOX"), new MatPrep("MAGENTA_SHULKER_BOX"), new MatPrep("MAGENTA_SHULKER_BOX")), 
    MAGENTA_STAINED_GLASS(new MatPrep(95, 2)), 
    MAGENTA_STAINED_GLASS_PANE(new MatPrep(160, 2)), 
    MAGENTA_TERRACOTTA(new MatPrep(159, 2)), 
    MAGENTA_WALL_BANNER(new MatPrep(425, 13)), 
    MAGENTA_WOOL(new MatPrep(35, 2), new MatPrep(35, 2), new MatPrep(35, 2), new MatPrep(35, 2), new MatPrep(35, 2), new MatPrep(35, 2), new MatPrep("MAGENTA_WOOL"), new MatPrep("MAGENTA_WOOL"), new MatPrep("MAGENTA_WOOL"), new MatPrep("MAGENTA_WOOL")), 
    MAGMA_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(213), new MatPrep(213), new MatPrep(213), new MatPrep("MAGMA_BLOCK"), new MatPrep("MAGMA_BLOCK"), new MatPrep("MAGMA_BLOCK"), new MatPrep("MAGMA_BLOCK")), 
    MAGMA_CREAM(new MatPrep(378)), 
    MAGMA_CUBE_SPAWN_EGG(new MatPrep(383, 62)), 
    MAP(new MatPrep(395)), 
    MELON(new MatPrep(360), new MatPrep(360), new MatPrep(360), new MatPrep(360), new MatPrep(360), new MatPrep(103), new MatPrep("MELON"), new MatPrep("MELON"), new MatPrep("MELON"), new MatPrep("MELON")), 
    MELON_SEEDS(new MatPrep(362)), 
    MELON_SLICE(new MatPrep(360)), 
    MELON_STEM(new MatPrep(105)), 
    MILK_BUCKET(new MatPrep(335)), 
    MINECART(new MatPrep(328)), 
    MOJANG_BANNER_PATTERN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("MOJANG_BANNER_PATTERN"), new MatPrep("MOJANG_BANNER_PATTERN")), 
    MOOSHROOM_SPAWN_EGG(new MatPrep(383, 96)), 
    MOSSY_COBBLESTONE(new MatPrep(48)), 
    MOSSY_COBBLESTONE_SLAB(new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep(44, 3, true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("MOSSY_COBBLESTONE_SLAB"), new MatPrep("MOSSY_COBBLESTONE_SLAB")), 
    MOSSY_COBBLESTONE_STAIRS(new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("MOSSY_COBBLESTONE_STAIRS"), new MatPrep("MOSSY_COBBLESTONE_STAIRS")), 
    MOSSY_COBBLESTONE_WALL(new MatPrep(139, 1), new MatPrep(139, 1), new MatPrep(139, 1), new MatPrep(139, 1), new MatPrep(139, 1), new MatPrep(139, 1), new MatPrep("MOSSY_COBBLESTONE_WALL"), new MatPrep("MOSSY_COBBLESTONE_WALL"), new MatPrep("MOSSY_COBBLESTONE_WALL"), new MatPrep("MOSSY_COBBLESTONE_WALL")), 
    MOSSY_STONE_BRICKS(new MatPrep(98, 1)), 
    MOSSY_STONE_BRICK_SLAB(new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep(44, 5, true), new MatPrep("STONE_BRICK_SLAB", true), new MatPrep("STONE_BRICK_SLAB", true), new MatPrep("MOSSY_STONE_BRICK_SLAB"), new MatPrep("MOSSY_STONE_BRICK_SLAB")), 
    MOSSY_STONE_BRICK_STAIRS(new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep(109, true), new MatPrep("STONE_BRICK_STAIRS", true), new MatPrep("STONE_BRICK_STAIRS", true), new MatPrep("MOSSY_STONE_BRICK_STAIRS"), new MatPrep("MOSSY_STONE_BRICK_STAIRS")), 
    MOSSY_STONE_BRICK_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("MOSSY_STONE_BRICK_WALL"), new MatPrep("MOSSY_STONE_BRICK_WALL")), 
    MOVING_PISTON(new MatPrep(0, true)), 
    MULE_SPAWN_EGG(new MatPrep(383, 100, true), new MatPrep(383, 100, true), new MatPrep(383, 100, true), new MatPrep(383, 100, true), new MatPrep(383, 32), new MatPrep(383, 32), new MatPrep("MULE_SPAWN_EGG"), new MatPrep("MULE_SPAWN_EGG"), new MatPrep("MULE_SPAWN_EGG"), new MatPrep("MULE_SPAWN_EGG")), 
    MUSHROOM_STEM(new MatPrep(99)), 
    MUSHROOM_STEW(new MatPrep(282)), 
    MUSIC_DISC_11(new MatPrep(2266)), 
    MUSIC_DISC_13(new MatPrep(2256)), 
    MUSIC_DISC_BLOCKS(new MatPrep(2258)), 
    MUSIC_DISC_CAT(new MatPrep(2257)), 
    MUSIC_DISC_CHIRP(new MatPrep(2259)), 
    MUSIC_DISC_FAR(new MatPrep(2260)), 
    MUSIC_DISC_MALL(new MatPrep(2261)), 
    MUSIC_DISC_MELLOHI(new MatPrep(2262)), 
    MUSIC_DISC_STAL(new MatPrep(2263)), 
    MUSIC_DISC_STRAD(new MatPrep(2264)), 
    MUSIC_DISC_WAIT(new MatPrep(2267)), 
    MUSIC_DISC_WARD(new MatPrep(2265)), 
    MUTTON(new MatPrep(423)), 
    MYCELIUM(new MatPrep(110)), 
    NAME_TAG(new MatPrep(421)), 
    NAUTILUS_SHELL(new MatPrep(0, true)), 
    NETHERRACK(new MatPrep(87)), 
    NETHER_BRICK(new MatPrep(405)), 
    NETHER_BRICKS(new MatPrep(112)), 
    NETHER_BRICK_FENCE(new MatPrep(113)), 
    NETHER_BRICK_SLAB(new MatPrep(44, 6)), 
    NETHER_BRICK_STAIRS(new MatPrep(114)), 
    NETHER_BRICK_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("NETHER_BRICK_WALL"), new MatPrep("NETHER_BRICK_WALL")), 
    NETHER_PORTAL(new MatPrep(90)), 
    NETHER_QUARTZ_ORE(new MatPrep(153)), 
    NETHER_STAR(new MatPrep(399)), 
    NETHER_WART(new MatPrep(372)), 
    NETHER_WART_BLOCK(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(214), new MatPrep(214), new MatPrep(214), new MatPrep("NETHER_WART_BLOCK"), new MatPrep("NETHER_WART_BLOCK"), new MatPrep("NETHER_WART_BLOCK"), new MatPrep("NETHER_WART_BLOCK")), 
    NOTE_BLOCK(new MatPrep(25)), 
    OAK_BOAT(new MatPrep(333)), 
    OAK_BUTTON(new MatPrep(143)), 
    OAK_DOOR(new MatPrep(324)), 
    OAK_FENCE(new MatPrep(85)), 
    OAK_FENCE_GATE(new MatPrep(107)), 
    OAK_LEAVES(new MatPrep(18)), 
    OAK_LOG(new MatPrep(17)), 
    OAK_PLANKS(new MatPrep(5)), 
    OAK_PRESSURE_PLATE(new MatPrep(72)), 
    OAK_SAPLING(new MatPrep(6)), 
    OAK_SIGN(new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep("SIGN"), new MatPrep("SIGN"), new MatPrep("OAK_SIGN"), new MatPrep("OAK_SIGN")), 
    OAK_SLAB(new MatPrep(126)), 
    OAK_STAIRS(new MatPrep(53)), 
    OAK_TRAPDOOR(new MatPrep(96)), 
    OAK_WALL_SIGN(new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep("WALL_SIGN"), new MatPrep("WALL_SIGN"), new MatPrep("OAK_WALL_SIGN"), new MatPrep("OAK_WALL_SIGN")), 
    OAK_WOOD(new MatPrep(17, true)), 
    OBSERVER(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(218), new MatPrep(218), new MatPrep("OBSERVER"), new MatPrep("OBSERVER"), new MatPrep("OBSERVER"), new MatPrep("OBSERVER")), 
    OBSIDIAN(new MatPrep(49)), 
    OCELOT_SPAWN_EGG(new MatPrep(383, 98)), 
    ORANGE_BANNER(new MatPrep(425, 14)), 
    ORANGE_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 1), new MatPrep("ORANGE_BED"), new MatPrep("ORANGE_BED"), new MatPrep("ORANGE_BED"), new MatPrep("ORANGE_BED")), 
    ORANGE_CARPET(new MatPrep(171, 1)), 
    ORANGE_CONCRETE(new MatPrep(159, 1, true), new MatPrep(159, 1, true), new MatPrep(159, 1, true), new MatPrep(159, 1, true), new MatPrep(159, 1, true), new MatPrep(251, 1), new MatPrep("ORANGE_CONCRETE"), new MatPrep("ORANGE_CONCRETE"), new MatPrep("ORANGE_CONCRETE"), new MatPrep("ORANGE_CONCRETE")), 
    ORANGE_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 1), new MatPrep("ORANGE_CONCRETE_POWDER"), new MatPrep("ORANGE_CONCRETE_POWDER"), new MatPrep("ORANGE_CONCRETE_POWDER"), new MatPrep("ORANGE_CONCRETE_POWDER")), 
    ORANGE_DYE(new MatPrep(351, 14), new MatPrep(351, 14), new MatPrep(351, 14), new MatPrep(351, 14), new MatPrep(351, 14), new MatPrep(351, 14), new MatPrep("ORANGE_DYE"), new MatPrep("ORANGE_DYE"), new MatPrep("ORANGE_DYE"), new MatPrep("ORANGE_DYE")), 
    ORANGE_GLAZED_TERRACOTTA(new MatPrep(159, 1, true), new MatPrep(159, 1, true), new MatPrep(159, 1, true), new MatPrep(159, 1, true), new MatPrep(159, 1, true), new MatPrep(236), new MatPrep("ORANGE_GLAZED_TERRACOTTA"), new MatPrep("ORANGE_GLAZED_TERRACOTTA"), new MatPrep("ORANGE_GLAZED_TERRACOTTA"), new MatPrep("ORANGE_GLAZED_TERRACOTTA")), 
    ORANGE_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(220), new MatPrep(220), new MatPrep("ORANGE_SHULKER_BOX"), new MatPrep("ORANGE_SHULKER_BOX"), new MatPrep("ORANGE_SHULKER_BOX"), new MatPrep("ORANGE_SHULKER_BOX")), 
    ORANGE_STAINED_GLASS(new MatPrep(95, 1)), 
    ORANGE_STAINED_GLASS_PANE(new MatPrep(160, 1)), 
    ORANGE_TERRACOTTA(new MatPrep(159, 1)), 
    ORANGE_TULIP(new MatPrep(38, 5)), 
    ORANGE_WALL_BANNER(new MatPrep(425, 14)), 
    ORANGE_WOOL(new MatPrep(35, 1)), 
    OXEYE_DAISY(new MatPrep(38, 8)), 
    PACKED_ICE(new MatPrep(174)), 
    PAINTING(new MatPrep(321)), 
    PANDA_SPAWN_EGG(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(383, 102, true), new MatPrep(383, 102, true), new MatPrep(383, 102, true), new MatPrep("POLAR_BEAR_SPAWN_EGG", true), new MatPrep("POLAR_BEAR_SPAWN_EGG", true), new MatPrep("PANDA_SPAWN_EGG"), new MatPrep("PANDA_SPAWN_EGG")), 
    PAPER(new MatPrep(339)), 
    PARROT_SPAWN_EGG(new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep(383, 65, true), new MatPrep(383, 105), new MatPrep("PARROT_SPAWN_EGG"), new MatPrep("PARROT_SPAWN_EGG"), new MatPrep("PARROT_SPAWN_EGG"), new MatPrep("PARROT_SPAWN_EGG")), 
    PEONY(new MatPrep(175, 5)), 
    PETRIFIED_OAK_SLAB(new MatPrep(126, true)), 
    PHANTOM_MEMBRANE(new MatPrep(0, true)), 
    PHANTOM_SPAWN_EGG(new MatPrep(383, 65, true)), 
    PIG_SPAWN_EGG(new MatPrep(383, 90)), 
    PILLAGER_SPAWN_EGG(new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep("VILLAGER_SPAWN_EGG", true), new MatPrep("VILLAGER_SPAWN_EGG", true), new MatPrep("PILLAGER_SPAWN_EGG"), new MatPrep("PILLAGER_SPAWN_EGG")), 
    PINK_BANNER(new MatPrep(425, 9)), 
    PINK_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 6), new MatPrep("PINK_BED"), new MatPrep("PINK_BED"), new MatPrep("PINK_BED"), new MatPrep("PINK_BED")), 
    PINK_CARPET(new MatPrep(171, 6)), 
    PINK_CONCRETE(new MatPrep(159, 6, true), new MatPrep(159, 6, true), new MatPrep(159, 6, true), new MatPrep(159, 6, true), new MatPrep(159, 6, true), new MatPrep(251, 6), new MatPrep("PINK_CONCRETE"), new MatPrep("PINK_CONCRETE"), new MatPrep("PINK_CONCRETE"), new MatPrep("PINK_CONCRETE")), 
    PINK_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 6), new MatPrep("PINK_CONCRETE_POWDER"), new MatPrep("PINK_CONCRETE_POWDER"), new MatPrep("PINK_CONCRETE_POWDER"), new MatPrep("PINK_CONCRETE_POWDER")), 
    PINK_DYE(new MatPrep(351, 9), new MatPrep(351, 9), new MatPrep(351, 9), new MatPrep(351, 9), new MatPrep(351, 9), new MatPrep(351, 9), new MatPrep("PINK_DYE"), new MatPrep("PINK_DYE"), new MatPrep("PINK_DYE"), new MatPrep("PINK_DYE")), 
    PINK_GLAZED_TERRACOTTA(new MatPrep(159, 6, true), new MatPrep(159, 6, true), new MatPrep(159, 6, true), new MatPrep(159, 6, true), new MatPrep(159, 6, true), new MatPrep(241), new MatPrep("PINK_GLAZED_TERRACOTTA"), new MatPrep("PINK_GLAZED_TERRACOTTA"), new MatPrep("PINK_GLAZED_TERRACOTTA"), new MatPrep("PINK_GLAZED_TERRACOTTA")), 
    PINK_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(225), new MatPrep(225), new MatPrep("PINK_SHULKER_BOX"), new MatPrep("PINK_SHULKER_BOX"), new MatPrep("PINK_SHULKER_BOX"), new MatPrep("PINK_SHULKER_BOX")), 
    PINK_STAINED_GLASS(new MatPrep(95, 6)), 
    PINK_STAINED_GLASS_PANE(new MatPrep(160, 6)), 
    PINK_TERRACOTTA(new MatPrep(159, 6)), 
    PINK_TULIP(new MatPrep(38, 7)), 
    PINK_WALL_BANNER(new MatPrep(425, 9)), 
    PINK_WOOL(new MatPrep(35, 6)), 
    PISTON(new MatPrep(33)), 
    PISTON_HEAD(new MatPrep(34, 2)), 
    PLAYER_HEAD(new MatPrep(397, 3)), 
    PLAYER_WALL_HEAD(new MatPrep(397, 3)), 
    PODZOL(new MatPrep(3, 2)), 
    POISONOUS_POTATO(new MatPrep(394)), 
    POLAR_BEAR_SPAWN_EGG(new MatPrep(383, 101, true), new MatPrep(383, 101, true), new MatPrep(383, 101, true), new MatPrep(383, 102), new MatPrep(383, 102), new MatPrep(383, 102), new MatPrep("POLAR_BEAR_SPAWN_EGG"), new MatPrep("POLAR_BEAR_SPAWN_EGG"), new MatPrep("POLAR_BEAR_SPAWN_EGG"), new MatPrep("POLAR_BEAR_SPAWN_EGG")), 
    POLISHED_ANDESITE(new MatPrep(1, 6)), 
    POLISHED_ANDESITE_SLAB(new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("POLISHED_ANDESITE_SLAB"), new MatPrep("POLISHED_ANDESITE_SLAB")), 
    POLISHED_ANDESITE_STAIRS(new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("POLISHED_ANDESITE_STAIRS"), new MatPrep("POLISHED_ANDESITE_STAIRS")), 
    POLISHED_DIORITE(new MatPrep(1, 4)), 
    POLISHED_DIORITE_SLAB(new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("POLISHED_DIORITE_SLAB"), new MatPrep("POLISHED_DIORITE_SLAB")), 
    POLISHED_DIORITE_STAIRS(new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("POLISHED_DIORITE_STAIRS"), new MatPrep("POLISHED_DIORITE_STAIRS")), 
    POLISHED_GRANITE(new MatPrep(1, 2)), 
    POLISHED_GRANITE_SLAB(new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("COBBLESTONE_SLAB", true), new MatPrep("POLISHED_GRANITE_SLAB"), new MatPrep("POLISHED_GRANITE_SLAB")), 
    POLISHED_GRANITE_STAIRS(new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("POLISHED_GRANITE_STAIRS"), new MatPrep("POLISHED_GRANITE_STAIRS")), 
    POPPED_CHORUS_FRUIT(new MatPrep(0, true), new MatPrep(433), new MatPrep(433), new MatPrep(433), new MatPrep(433), new MatPrep(433), new MatPrep("POPPED_CHORUS_FRUIT"), new MatPrep("POPPED_CHORUS_FRUIT"), new MatPrep("POPPED_CHORUS_FRUIT"), new MatPrep("POPPED_CHORUS_FRUIT")), 
    POPPY(new MatPrep(38)), 
    PORKCHOP(new MatPrep(319)), 
    POTATO(new MatPrep(392)), 
    POTATOES(new MatPrep(142)), 
    POTION(new MatPrep(373)), 
    POTTED_ACACIA_SAPLING(new MatPrep(390, true)), 
    POTTED_ALLIUM(new MatPrep(390, true)), 
    POTTED_AZURE_BLUET(new MatPrep(390, true)), 
    POTTED_BAMBOO(new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep("FLOWER_POT", true), new MatPrep("FLOWER_POT", true), new MatPrep("POTTED_BAMBOO"), new MatPrep("POTTED_BAMBOO")), 
    POTTED_BIRCH_SAPLING(new MatPrep(390, true)), 
    POTTED_BLUE_ORCHID(new MatPrep(390, true)), 
    POTTED_BROWN_MUSHROOM(new MatPrep(390, true)), 
    POTTED_CACTUS(new MatPrep(390, true)), 
    POTTED_CORNFLOWER(new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep("FLOWER_POT", true), new MatPrep("FLOWER_POT", true), new MatPrep("POTTED_CORNFLOWER"), new MatPrep("POTTED_CORNFLOWER")), 
    POTTED_DANDELION(new MatPrep(390, true)), 
    POTTED_DARK_OAK_SAPLING(new MatPrep(390, true)), 
    POTTED_DEAD_BUSH(new MatPrep(390, true)), 
    POTTED_FERN(new MatPrep(390, true)), 
    POTTED_JUNGLE_SAPLING(new MatPrep(390, true)), 
    POTTED_LILY_OF_THE_VALLEY(new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep("FLOWER_POT", true), new MatPrep("FLOWER_POT", true), new MatPrep("POTTED_LILY_OF_THE_VALLEY"), new MatPrep("POTTED_LILY_OF_THE_VALLEY")), 
    POTTED_OAK_SAPLING(new MatPrep(390, true)), 
    POTTED_ORANGE_TULIP(new MatPrep(390, true)), 
    POTTED_OXEYE_DAISY(new MatPrep(390, true)), 
    POTTED_PINK_TULIP(new MatPrep(390, true)), 
    POTTED_POPPY(new MatPrep(390, true)), 
    POTTED_RED_MUSHROOM(new MatPrep(390, true)), 
    POTTED_RED_TULIP(new MatPrep(390, true)), 
    POTTED_SPRUCE_SAPLING(new MatPrep(390, true)), 
    POTTED_WHITE_TULIP(new MatPrep(390, true)), 
    POTTED_WITHER_ROSE(new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep(390, true), new MatPrep("FLOWER_POT", true), new MatPrep("FLOWER_POT", true), new MatPrep("POTTED_WITHER_ROSE"), new MatPrep("POTTED_WITHER_ROSE")), 
    POWERED_RAIL(new MatPrep(27), new MatPrep(27), new MatPrep(27), new MatPrep(27), new MatPrep(27), new MatPrep(27), new MatPrep("POWERED_RAIL"), new MatPrep("POWERED_RAIL"), new MatPrep("POWERED_RAIL"), new MatPrep("POWERED_RAIL")), 
    PRISMARINE(new MatPrep(168)), 
    PRISMARINE_BRICKS(new MatPrep(168, 1)), 
    PRISMARINE_BRICK_SLAB(new MatPrep(44, 5, true)), 
    PRISMARINE_BRICK_STAIRS(new MatPrep(109, true)), 
    PRISMARINE_CRYSTALS(new MatPrep(410)), 
    PRISMARINE_SHARD(new MatPrep(409)), 
    PRISMARINE_SLAB(new MatPrep(44, 5, true)), 
    PRISMARINE_STAIRS(new MatPrep(109, true)), 
    PRISMARINE_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("PRISMARINE_WALL"), new MatPrep("PRISMARINE_WALL")), 
    PUFFERFISH(new MatPrep(349, 3)), 
    PUFFERFISH_BUCKET(new MatPrep(326, true)), 
    PUFFERFISH_SPAWN_EGG(new MatPrep(383, 94, true)), 
    PUMPKIN(new MatPrep(86)), 
    PUMPKIN_PIE(new MatPrep(400)), 
    PUMPKIN_SEEDS(new MatPrep(361)), 
    PUMPKIN_STEM(new MatPrep(104)), 
    PURPLE_BANNER(new MatPrep(425, 5)), 
    PURPLE_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 10), new MatPrep("PURPLE_BED"), new MatPrep("PURPLE_BED"), new MatPrep("PURPLE_BED"), new MatPrep("PURPLE_BED")), 
    PURPLE_CARPET(new MatPrep(171, 10)), 
    PURPLE_CONCRETE(new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(251, 10), new MatPrep("PURPLE_CONCRETE"), new MatPrep("PURPLE_CONCRETE"), new MatPrep("PURPLE_CONCRETE"), new MatPrep("PURPLE_CONCRETE")), 
    PURPLE_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 10), new MatPrep("PURPLE_CONCRETE_POWDER"), new MatPrep("PURPLE_CONCRETE_POWDER"), new MatPrep("PURPLE_CONCRETE_POWDER"), new MatPrep("PURPLE_CONCRETE_POWDER")), 
    PURPLE_DYE(new MatPrep(351, 5), new MatPrep(351, 5), new MatPrep(351, 5), new MatPrep(351, 5), new MatPrep(351, 5), new MatPrep(351, 5), new MatPrep("PURPLE_DYE"), new MatPrep("PURPLE_DYE"), new MatPrep("PURPLE_DYE"), new MatPrep("PURPLE_DYE")), 
    PURPLE_GLAZED_TERRACOTTA(new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(159, 10, true), new MatPrep(245), new MatPrep("PURPLE_GLAZED_TERRACOTTA"), new MatPrep("PURPLE_GLAZED_TERRACOTTA"), new MatPrep("PURPLE_GLAZED_TERRACOTTA"), new MatPrep("PURPLE_GLAZED_TERRACOTTA")), 
    PURPLE_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(229), new MatPrep(229), new MatPrep("PURPLE_SHULKER_BOX"), new MatPrep("PURPLE_SHULKER_BOX"), new MatPrep("PURPLE_SHULKER_BOX"), new MatPrep("PURPLE_SHULKER_BOX")), 
    PURPLE_STAINED_GLASS(new MatPrep(95, 10)), 
    PURPLE_STAINED_GLASS_PANE(new MatPrep(160, 10)), 
    PURPLE_TERRACOTTA(new MatPrep(159, 10)), 
    PURPLE_WALL_BANNER(new MatPrep(425, 5)), 
    PURPLE_WOOL(new MatPrep(35, 10)), 
    PURPUR_BLOCK(new MatPrep(155, true), new MatPrep(201), new MatPrep(201), new MatPrep(201), new MatPrep(201), new MatPrep(201), new MatPrep("PURPUR_BLOCK"), new MatPrep("PURPUR_BLOCK"), new MatPrep("PURPUR_BLOCK"), new MatPrep("PURPUR_BLOCK")), 
    PURPUR_PILLAR(new MatPrep(155, 2, true), new MatPrep(202), new MatPrep(202), new MatPrep(202), new MatPrep(202), new MatPrep(202), new MatPrep("PURPUR_PILLAR"), new MatPrep("PURPUR_PILLAR"), new MatPrep("PURPUR_PILLAR"), new MatPrep("PURPUR_PILLAR")), 
    PURPUR_SLAB(new MatPrep(44, 7, true), new MatPrep(205), new MatPrep(205), new MatPrep(205), new MatPrep(205), new MatPrep(205), new MatPrep("PURPUR_SLAB"), new MatPrep("PURPUR_SLAB"), new MatPrep("PURPUR_SLAB"), new MatPrep("PURPUR_SLAB")), 
    PURPUR_STAIRS(new MatPrep(156), new MatPrep(203), new MatPrep(203), new MatPrep(203), new MatPrep(203), new MatPrep(203), new MatPrep("PURPUR_STAIRS"), new MatPrep("PURPUR_STAIRS"), new MatPrep("PURPUR_STAIRS"), new MatPrep("PURPUR_STAIRS")), 
    QUARTZ(new MatPrep(406)), 
    QUARTZ_BLOCK(new MatPrep(155)), 
    QUARTZ_PILLAR(new MatPrep(155, 2)), 
    QUARTZ_SLAB(new MatPrep(44, 7)), 
    QUARTZ_STAIRS(new MatPrep(156)), 
    RABBIT(new MatPrep(411)), 
    RABBIT_FOOT(new MatPrep(414)), 
    RABBIT_HIDE(new MatPrep(415)), 
    RABBIT_SPAWN_EGG(new MatPrep(383, 101)), 
    RABBIT_STEW(new MatPrep(413)), 
    RAIL(new MatPrep(66)), 
    RAVAGER_SPAWN_EGG(new MatPrep(383, 62, true), new MatPrep(383, 62, true), new MatPrep(383, 62, true), new MatPrep(383, 62, true), new MatPrep(383, 62, true), new MatPrep(383, 62, true), new MatPrep("MAGMA_CUBE_SPAWN_EGG", true), new MatPrep("MAGMA_CUBE_SPAWN_EGG", true), new MatPrep("RAVAGER_SPAWN_EGG"), new MatPrep("RAVAGER_SPAWN_EGG")), 
    REDSTONE(new MatPrep(331)), 
    REDSTONE_BLOCK(new MatPrep(152)), 
    REDSTONE_LAMP(new MatPrep(123)), 
    REDSTONE_ORE(new MatPrep(73)), 
    REDSTONE_TORCH(new MatPrep(76)), 
    REDSTONE_WALL_TORCH(new MatPrep(76)), 
    REDSTONE_WIRE(new MatPrep(331)), 
    RED_BANNER(new MatPrep(425, 1)), 
    RED_BED(new MatPrep(355), new MatPrep(355), new MatPrep(355), new MatPrep(355), new MatPrep(355), new MatPrep(355, 14), new MatPrep("RED_BED"), new MatPrep("RED_BED"), new MatPrep("RED_BED"), new MatPrep("RED_BED")), 
    RED_CARPET(new MatPrep(171, 14)), 
    RED_CONCRETE(new MatPrep(159, 14, true), new MatPrep(159, 14, true), new MatPrep(159, 14, true), new MatPrep(159, 14, true), new MatPrep(159, 14, true), new MatPrep(251, 14), new MatPrep("RED_CONCRETE"), new MatPrep("RED_CONCRETE"), new MatPrep("RED_CONCRETE"), new MatPrep("RED_CONCRETE")), 
    RED_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 14), new MatPrep("RED_CONCRETE_POWDER"), new MatPrep("RED_CONCRETE_POWDER"), new MatPrep("RED_CONCRETE_POWDER"), new MatPrep("RED_CONCRETE_POWDER")), 
    RED_DYE(new MatPrep(351, 1), new MatPrep(351, 1), new MatPrep(351, 1), new MatPrep(351, 1), new MatPrep(351, 1), new MatPrep(351, 1), new MatPrep("ROSE_RED"), new MatPrep("ROSE_RED"), new MatPrep("RED_DYE"), new MatPrep("RED_DYE")), 
    RED_GLAZED_TERRACOTTA(new MatPrep(159, 14, true), new MatPrep(159, 14, true), new MatPrep(159, 14, true), new MatPrep(159, 14, true), new MatPrep(159, 14, true), new MatPrep(249), new MatPrep("RED_GLAZED_TERRACOTTA"), new MatPrep("RED_GLAZED_TERRACOTTA"), new MatPrep("RED_GLAZED_TERRACOTTA"), new MatPrep("RED_GLAZED_TERRACOTTA")), 
    RED_MUSHROOM(new MatPrep(40)), 
    RED_MUSHROOM_BLOCK(new MatPrep(100)), 
    RED_NETHER_BRICKS(new MatPrep(112, true), new MatPrep(112, true), new MatPrep(112, true), new MatPrep(215), new MatPrep(215), new MatPrep(215), new MatPrep("RED_NETHER_BRICKS"), new MatPrep("RED_NETHER_BRICKS"), new MatPrep("RED_NETHER_BRICKS"), new MatPrep("RED_NETHER_BRICKS")), 
    RED_NETHER_BRICK_SLAB(new MatPrep(44, 6, true), new MatPrep(44, 6, true), new MatPrep(44, 6, true), new MatPrep(44, 6, true), new MatPrep(44, 6, true), new MatPrep(44, 6, true), new MatPrep("NETHER_BRICK_SLAB", true), new MatPrep("NETHER_BRICK_SLAB", true), new MatPrep("RED_NETHER_BRICK_SLAB"), new MatPrep("RED_NETHER_BRICK_SLAB")), 
    RED_NETHER_BRICK_STAIRS(new MatPrep(114, true), new MatPrep(114, true), new MatPrep(114, true), new MatPrep(114, true), new MatPrep(114, true), new MatPrep(114, true), new MatPrep("NETHER_BRICK_STAIRS", true), new MatPrep("NETHER_BRICK_STAIRS", true), new MatPrep("RED_NETHER_BRICK_STAIRS"), new MatPrep("RED_NETHER_BRICK_STAIRS")), 
    RED_NETHER_BRICK_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("RED_NETHER_BRICK_WALL"), new MatPrep("RED_NETHER_BRICK_WALL")), 
    RED_SAND(new MatPrep(12, 1)), 
    RED_SANDSTONE(new MatPrep(179)), 
    RED_SANDSTONE_SLAB(new MatPrep(182), new MatPrep(182), new MatPrep(182), new MatPrep(182), new MatPrep(182), new MatPrep(182), new MatPrep("RED_SANDSTONE_SLAB"), new MatPrep("RED_SANDSTONE_SLAB"), new MatPrep("RED_SANDSTONE_SLAB"), new MatPrep("RED_SANDSTONE_SLAB")), 
    RED_SANDSTONE_STAIRS(new MatPrep(180), new MatPrep(180), new MatPrep(180), new MatPrep(180), new MatPrep(180), new MatPrep(180), new MatPrep("RED_SANDSTONE_STAIRS"), new MatPrep("RED_SANDSTONE_STAIRS"), new MatPrep("RED_SANDSTONE_STAIRS"), new MatPrep("RED_SANDSTONE_STAIRS")), 
    RED_SANDSTONE_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("RED_SANDSTONE_WALL"), new MatPrep("RED_SANDSTONE_WALL")), 
    RED_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(233), new MatPrep(233), new MatPrep("RED_SHULKER_BOX"), new MatPrep("RED_SHULKER_BOX"), new MatPrep("RED_SHULKER_BOX"), new MatPrep("RED_SHULKER_BOX")), 
    RED_STAINED_GLASS(new MatPrep(95, 14)), 
    RED_STAINED_GLASS_PANE(new MatPrep(160, 14)), 
    RED_TERRACOTTA(new MatPrep(159, 14)), 
    RED_TULIP(new MatPrep(38, 4)), 
    RED_WALL_BANNER(new MatPrep(425, 1)), 
    RED_WOOL(new MatPrep(35, 14)), 
    REPEATER(new MatPrep(356)), 
    REPEATING_COMMAND_BLOCK(new MatPrep(137, true)), 
    ROSE_BUSH(new MatPrep(175, 4)), 
    ROTTEN_FLESH(new MatPrep(367)), 
    SADDLE(new MatPrep(329)), 
    SALMON(new MatPrep(349, 1)), 
    SALMON_BUCKET(new MatPrep(326, true)), 
    SALMON_SPAWN_EGG(new MatPrep(383, 94, true)), 
    SAND(new MatPrep(12)), 
    SANDSTONE(new MatPrep(24)), 
    SANDSTONE_SLAB(new MatPrep(44, 1)), 
    SANDSTONE_STAIRS(new MatPrep(128)), 
    SANDSTONE_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("SANDSTONE_WALL"), new MatPrep("SANDSTONE_WALL")), 
    SCAFFOLDING(new MatPrep(65, true), new MatPrep(65, true), new MatPrep(65, true), new MatPrep(65, true), new MatPrep(65, true), new MatPrep(65, true), new MatPrep("LADDER", true), new MatPrep("LADDER", true), new MatPrep("SCAFFOLDING"), new MatPrep("SCAFFOLDING")), 
    SCUTE(new MatPrep(0, true)), 
    SEAGRASS(new MatPrep(0, true)), 
    SEA_LANTERN(new MatPrep(169)), 
    SEA_PICKLE(new MatPrep(169, true)), 
    SHEARS(new MatPrep(359)), 
    SHEEP_SPAWN_EGG(new MatPrep(383, 91)), 
    SHIELD(new MatPrep(0, true), new MatPrep(442), new MatPrep(442), new MatPrep(442), new MatPrep(442), new MatPrep(442), new MatPrep("SHIELD"), new MatPrep("SHIELD"), new MatPrep("SHIELD"), new MatPrep("SHIELD")), 
    SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(219), new MatPrep(219), new MatPrep("SHULKER_BOX"), new MatPrep("SHULKER_BOX"), new MatPrep("SHULKER_BOX"), new MatPrep("SHULKER_BOX")), 
    SHULKER_SHELL(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(450), new MatPrep(450), new MatPrep("SHULKER_SHELL"), new MatPrep("SHULKER_SHELL"), new MatPrep("SHULKER_SHELL"), new MatPrep("SHULKER_SHELL")), 
    SHULKER_SPAWN_EGG(new MatPrep(383, 60, true), new MatPrep(383, 60, true), new MatPrep(383, 60, true), new MatPrep(383, 60, true), new MatPrep(383, 69), new MatPrep(383, 69), new MatPrep("SHULKER_SPAWN_EGG"), new MatPrep("SHULKER_SPAWN_EGG"), new MatPrep("SHULKER_SPAWN_EGG"), new MatPrep("SHULKER_SPAWN_EGG")), 
    SILVERFISH_SPAWN_EGG(new MatPrep(383, 60)), 
    SKELETON_HORSE_SPAWN_EGG(new MatPrep(383, 100, true)), 
    SKELETON_SKULL(new MatPrep(397)), 
    SKELETON_SPAWN_EGG(new MatPrep(383, 51)), 
    SKELETON_WALL_SKULL(new MatPrep(397)), 
    SKULL_BANNER_PATTERN(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("SKULL_BANNER_PATTERN"), new MatPrep("SKULL_BANNER_PATTERN")), 
    SLIME_BALL(new MatPrep(341)), 
    SLIME_BLOCK(new MatPrep(165)), 
    SLIME_SPAWN_EGG(new MatPrep(383, 55)), 
    SMITHING_TABLE(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("SMITHING_TABLE"), new MatPrep("SMITHING_TABLE")), 
    SMOKER(new MatPrep(61, true), new MatPrep(61, true), new MatPrep(61, true), new MatPrep(61, true), new MatPrep(61, true), new MatPrep(61, true), new MatPrep("FURNACE", true), new MatPrep("FURNACE", true), new MatPrep("SMOKER"), new MatPrep("SMOKER")), 
    SMOOTH_QUARTZ(new MatPrep(155, true)), 
    SMOOTH_QUARTZ_SLAB(new MatPrep(44, 7, true), new MatPrep(44, 7, true), new MatPrep(44, 7, true), new MatPrep(44, 7, true), new MatPrep(44, 7, true), new MatPrep(44, 7, true), new MatPrep("QUARTZ_SLAB", true), new MatPrep("QUARTZ_SLAB", true), new MatPrep("SMOOTH_QUARTZ_SLAB"), new MatPrep("SMOOTH_QUARTZ_SLAB")), 
    SMOOTH_QUARTZ_STAIRS(new MatPrep(156, true), new MatPrep(156, true), new MatPrep(156, true), new MatPrep(156, true), new MatPrep(156, true), new MatPrep(156, true), new MatPrep("QUARTZ_STAIRS", true), new MatPrep("QUARTZ_STAIRS", true), new MatPrep("SMOOTH_QUARTZ_STAIRS"), new MatPrep("SMOOTH_QUARTZ_STAIRS")), 
    SMOOTH_RED_SANDSTONE(new MatPrep(179, 2)), 
    SMOOTH_RED_SANDSTONE_SLAB(new MatPrep(182, true), new MatPrep(182, true), new MatPrep(182, true), new MatPrep(182, true), new MatPrep(182, true), new MatPrep(182, true), new MatPrep("RED_SANDSTONE_SLAB", true), new MatPrep("RED_SANDSTONE_SLAB", true), new MatPrep("SMOOTH_RED_SANDSTONE_SLAB"), new MatPrep("SMOOTH_RED_SANDSTONE_SLAB")), 
    SMOOTH_RED_SANDSTONE_STAIRS(new MatPrep(180, true), new MatPrep(180, true), new MatPrep(180, true), new MatPrep(180, true), new MatPrep(180, true), new MatPrep(180, true), new MatPrep("RED_SANDSTONE_STAIRS", true), new MatPrep("RED_SANDSTONE_STAIRS", true), new MatPrep("SMOOTH_RED_SANDSTONE_STAIRS"), new MatPrep("SMOOTH_RED_SANDSTONE_STAIRS")), 
    SMOOTH_SANDSTONE(new MatPrep(24, 2)), 
    SMOOTH_SANDSTONE_SLAB(new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep(44, 1, true), new MatPrep("SANDSTONE_SLAB", true), new MatPrep("SANDSTONE_SLAB", true), new MatPrep("SMOOTH_SANDSTONE_SLAB"), new MatPrep("SMOOTH_SANDSTONE_SLAB")), 
    SMOOTH_SANDSTONE_STAIRS(new MatPrep(128, true), new MatPrep(128, true), new MatPrep(128, true), new MatPrep(128, true), new MatPrep(128, true), new MatPrep(128, true), new MatPrep("SANDSTONE_STAIRS", true), new MatPrep("SANDSTONE_STAIRS", true), new MatPrep("SMOOTH_SANDSTONE_STAIRS"), new MatPrep("SMOOTH_SANDSTONE_STAIRS")), 
    SMOOTH_STONE(new MatPrep(1, true)), 
    SMOOTH_STONE_SLAB(new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep(44, true), new MatPrep("STONE_SLAB", true), new MatPrep("STONE_SLAB", true), new MatPrep("SMOOTH_STONE_SLAB"), new MatPrep("SMOOTH_STONE_SLAB")), 
    SNOW(new MatPrep(78)), 
    SNOWBALL(new MatPrep(332)), 
    SNOW_BLOCK(new MatPrep(80)), 
    SOUL_SAND(new MatPrep(88)), 
    SPAWNER(new MatPrep(52)), 
    SPECTRAL_ARROW(new MatPrep(262, true), new MatPrep(439), new MatPrep(439), new MatPrep(439), new MatPrep(439), new MatPrep(439), new MatPrep("SPECTRAL_ARROW"), new MatPrep("SPECTRAL_ARROW"), new MatPrep("SPECTRAL_ARROW"), new MatPrep("SPECTRAL_ARROW")), 
    SPIDER_EYE(new MatPrep(375)), 
    SPIDER_SPAWN_EGG(new MatPrep(383, 52)), 
    SPLASH_POTION(new MatPrep(373, true), new MatPrep(438), new MatPrep(438), new MatPrep(438), new MatPrep(438), new MatPrep(438), new MatPrep("SPLASH_POTION"), new MatPrep("SPLASH_POTION"), new MatPrep("SPLASH_POTION"), new MatPrep("SPLASH_POTION")), 
    SPONGE(new MatPrep(19)), 
    SPRUCE_BOAT(new MatPrep(333, true), new MatPrep(444), new MatPrep(444), new MatPrep(444), new MatPrep(444), new MatPrep(444), new MatPrep("SPRUCE_BOAT"), new MatPrep("SPRUCE_BOAT"), new MatPrep("SPRUCE_BOAT"), new MatPrep("SPRUCE_BOAT")), 
    SPRUCE_BUTTON(new MatPrep(143, true)), 
    SPRUCE_DOOR(new MatPrep(427)), 
    SPRUCE_FENCE(new MatPrep(188)), 
    SPRUCE_FENCE_GATE(new MatPrep(183)), 
    SPRUCE_LEAVES(new MatPrep(18, 1)), 
    SPRUCE_LOG(new MatPrep(17, 1)), 
    SPRUCE_PLANKS(new MatPrep(5, 1)), 
    SPRUCE_PRESSURE_PLATE(new MatPrep(72, true)), 
    SPRUCE_SAPLING(new MatPrep(6, 1)), 
    SPRUCE_SIGN(new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep(323), new MatPrep("SIGN"), new MatPrep("SIGN"), new MatPrep("SPRUCE_SIGN"), new MatPrep("SPRUCE_SIGN")), 
    SPRUCE_SLAB(new MatPrep(126, 1)), 
    SPRUCE_STAIRS(new MatPrep(134)), 
    SPRUCE_TRAPDOOR(new MatPrep(96, true)), 
    SPRUCE_WALL_SIGN(new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep(68), new MatPrep("WALL_SIGN"), new MatPrep("WALL_SIGN"), new MatPrep("SPRUCE_WALL_SIGN"), new MatPrep("SPRUCE_WALL_SIGN")), 
    SPRUCE_WOOD(new MatPrep(17, 1, true)), 
    SQUID_SPAWN_EGG(new MatPrep(383, 94)), 
    STATIONARY_LAVA(new MatPrep("STATIONARY_LAVA"), new MatPrep("LEGACY_STATIONARY_LAVA")), 
    STATIONARY_WATER(new MatPrep("STATIONARY_WATER"), new MatPrep("LEGACY_STATIONARY_WATER")), 
    STICK(new MatPrep(280)), 
    STICKY_PISTON(new MatPrep(29)), 
    STONE(new MatPrep(1)), 
    STONECUTTER(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("STONECUTTER"), new MatPrep("STONECUTTER")), 
    STONE_AXE(new MatPrep(275)), 
    STONE_BRICKS(new MatPrep(98)), 
    STONE_BRICK_SLAB(new MatPrep(44, 5), new MatPrep(44, 5), new MatPrep(44, 5), new MatPrep(44, 5), new MatPrep(44, 5), new MatPrep(44, 5), new MatPrep("STONE_BRICK_SLAB"), new MatPrep("STONE_BRICK_SLAB"), new MatPrep("STONE_BRICK_SLAB"), new MatPrep("STONE_BRICK_SLAB")), 
    STONE_BRICK_STAIRS(new MatPrep(109), new MatPrep(109), new MatPrep(109), new MatPrep(109), new MatPrep(109), new MatPrep(109), new MatPrep("STONE_BRICK_STAIRS"), new MatPrep("STONE_BRICK_STAIRS"), new MatPrep("STONE_BRICK_STAIRS"), new MatPrep("STONE_BRICK_STAIRS")), 
    STONE_BRICK_WALL(new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep(139, true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("COBBLESTONE_WALL", true), new MatPrep("STONE_BRICK_WALL"), new MatPrep("STONE_BRICK_WALL")), 
    STONE_BUTTON(new MatPrep(77)), 
    STONE_HOE(new MatPrep(291)), 
    STONE_PICKAXE(new MatPrep(274)), 
    STONE_PRESSURE_PLATE(new MatPrep(70)), 
    STONE_SHOVEL(new MatPrep(273)), 
    STONE_SLAB(new MatPrep(44)), 
    STONE_STAIRS(new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep(67, true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("COBBLESTONE_STAIRS", true), new MatPrep("STONE_STAIRS"), new MatPrep("STONE_STAIRS")), 
    STONE_SWORD(new MatPrep(272)), 
    STRAY_SPAWN_EGG(new MatPrep(383, 51, true)), 
    STRING(new MatPrep(287)), 
    STRIPPED_ACACIA_LOG(new MatPrep(162, true)), 
    STRIPPED_ACACIA_WOOD(new MatPrep(5, 4, true)), 
    STRIPPED_BIRCH_LOG(new MatPrep(17, 2, true)), 
    STRIPPED_BIRCH_WOOD(new MatPrep(5, 2, true)), 
    STRIPPED_DARK_OAK_LOG(new MatPrep(162, 1, true)), 
    STRIPPED_DARK_OAK_WOOD(new MatPrep(5, 5, true)), 
    STRIPPED_JUNGLE_LOG(new MatPrep(17, 3, true)), 
    STRIPPED_JUNGLE_WOOD(new MatPrep(5, 3, true)), 
    STRIPPED_OAK_LOG(new MatPrep(17, true)), 
    STRIPPED_OAK_WOOD(new MatPrep(5, true)), 
    STRIPPED_SPRUCE_LOG(new MatPrep(17, 1, true)), 
    STRIPPED_SPRUCE_WOOD(new MatPrep(5, 1, true)), 
    STRUCTURE_BLOCK(new MatPrep(0, true)), 
    STRUCTURE_VOID(new MatPrep(0, true)), 
    SUGAR(new MatPrep(353)), 
    SUGAR_CANE(new MatPrep(338)), 
    SUNFLOWER(new MatPrep(175)), 
    SUSPICIOUS_STEW(new MatPrep(349, 3, true), new MatPrep(349, 3, true), new MatPrep(349, 3, true), new MatPrep(349, 3, true), new MatPrep(349, 3, true), new MatPrep(349, 3, true), new MatPrep("PUFFERFISH", true), new MatPrep("PUFFERFISH", true), new MatPrep("SUSPICIOUS_STEW"), new MatPrep("SUSPICIOUS_STEW")), 
    SWEET_BERRIES(new MatPrep(0, true), new MatPrep(434, true), new MatPrep(434, true), new MatPrep(434, true), new MatPrep(434, true), new MatPrep(434, true), new MatPrep("BEETROOT", true), new MatPrep("BEETROOT", true), new MatPrep("SWEET_BERRIES"), new MatPrep("SWEET_BERRIES")), 
    SWEET_BERRY_BUSH(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep("AIR", true), new MatPrep("AIR", true), new MatPrep("SWEET_BERRY_BUSH"), new MatPrep("SWEET_BERRY_BUSH")), 
    TALL_GRASS(new MatPrep(31, 1)), 
    TALL_SEAGRASS(new MatPrep(0, true)), 
    TERRACOTTA(new MatPrep(172)), 
    TIPPED_ARROW(new MatPrep(262, true), new MatPrep(440), new MatPrep(440), new MatPrep(440), new MatPrep(440), new MatPrep(440), new MatPrep("TIPPED_ARROW"), new MatPrep("TIPPED_ARROW"), new MatPrep("TIPPED_ARROW"), new MatPrep("TIPPED_ARROW")), 
    TNT(new MatPrep(46)), 
    TNT_MINECART(new MatPrep(407)), 
    TORCH(new MatPrep(50)), 
    TOTEM_OF_UNDYING(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(449), new MatPrep(449), new MatPrep("TOTEM_OF_UNDYING"), new MatPrep("TOTEM_OF_UNDYING"), new MatPrep("TOTEM_OF_UNDYING"), new MatPrep("TOTEM_OF_UNDYING")), 
    TRADER_LLAMA_SPAWN_EGG(new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(0, true), new MatPrep(383, 103, true), new MatPrep(383, 103, true), new MatPrep("LLAMA_SPAWN_EGG", true), new MatPrep("LLAMA_SPAWN_EGG", true), new MatPrep("TRADER_LLAMA_SPAWN_EGG"), new MatPrep("TRADER_LLAMA_SPAWN_EGG")), 
    TRAPPED_CHEST(new MatPrep(146)), 
    TRIDENT(new MatPrep(0, true)), 
    TRIPWIRE(new MatPrep(131)), 
    TRIPWIRE_HOOK(new MatPrep(131)), 
    TROPICAL_FISH(new MatPrep(349)), 
    TROPICAL_FISH_BUCKET(new MatPrep(326)), 
    TROPICAL_FISH_SPAWN_EGG(new MatPrep(383, 94, true)), 
    TUBE_CORAL(new MatPrep(175, 2, true)), 
    TUBE_CORAL_BLOCK(new MatPrep(175, 2, true)), 
    TUBE_CORAL_FAN(new MatPrep(175, 2, true)), 
    TUBE_CORAL_WALL_FAN(new MatPrep(175, 2, true)), 
    TURTLE_EGG(new MatPrep(0, true)), 
    TURTLE_HELMET(new MatPrep(298, true)), 
    TURTLE_SPAWN_EGG(new MatPrep(383, 94, true)), 
    VEX_SPAWN_EGG(new MatPrep(383, 54, true)), 
    VILLAGER_SPAWN_EGG(new MatPrep(383, 120)), 
    VINDICATOR_SPAWN_EGG(new MatPrep(383, 54, true)), 
    VINE(new MatPrep(106)), 
    VOID_AIR(new MatPrep(0)), 
    WALL_TORCH(new MatPrep(50, 4)), 
    WANDERING_TRADER_SPAWN_EGG(new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep(383, 120, true), new MatPrep("VILLAGER_SPAWN_EGG", true), new MatPrep("VILLAGER_SPAWN_EGG", true), new MatPrep("WANDERING_TRADER_SPAWN_EGG"), new MatPrep("WANDERING_TRADER_SPAWN_EGG")), 
    WATER, 
    WATER_BUCKET(new MatPrep(326)), 
    WET_SPONGE(new MatPrep(19, 1)), 
    WHEAT(new MatPrep(296)), 
    WHEAT_SEEDS(new MatPrep(295)), 
    WHITE_BANNER(new MatPrep(425, 15)), 
    WHITE_BED(new MatPrep(355)), 
    WHITE_CARPET(new MatPrep(171)), 
    WHITE_CONCRETE(new MatPrep(159, true)), 
    WHITE_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252), new MatPrep("WHITE_CONCRETE_POWDER"), new MatPrep("WHITE_CONCRETE_POWDER"), new MatPrep("WHITE_CONCRETE_POWDER"), new MatPrep("WHITE_CONCRETE_POWDER")), 
    WHITE_DYE(new MatPrep(351, 15), new MatPrep(351, 15), new MatPrep(351, 15), new MatPrep(351, 15), new MatPrep(351, 15), new MatPrep(351, 15), new MatPrep("BONE_MEAL"), new MatPrep("BONE_MEAL"), new MatPrep("WHITE_DYE"), new MatPrep("WHITE_DYE")), 
    WHITE_GLAZED_TERRACOTTA(new MatPrep(159, true), new MatPrep(159, true), new MatPrep(159, true), new MatPrep(159, true), new MatPrep(159, true), new MatPrep(235), new MatPrep("WHITE_GLAZED_TERRACOTTA"), new MatPrep("WHITE_GLAZED_TERRACOTTA"), new MatPrep("WHITE_GLAZED_TERRACOTTA"), new MatPrep("WHITE_GLAZED_TERRACOTTA")), 
    WHITE_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep("WHITE_SHULKER_BOX"), new MatPrep("WHITE_SHULKER_BOX"), new MatPrep("WHITE_SHULKER_BOX"), new MatPrep("WHITE_SHULKER_BOX")), 
    WHITE_STAINED_GLASS(new MatPrep(95)), 
    WHITE_STAINED_GLASS_PANE(new MatPrep(160)), 
    WHITE_TERRACOTTA(new MatPrep(159)), 
    WHITE_TULIP(new MatPrep(38, 6)), 
    WHITE_WALL_BANNER(new MatPrep(425, 15)), 
    WHITE_WOOL(new MatPrep(35)), 
    WITCH_SPAWN_EGG(new MatPrep(383, 66)), 
    WITHER_ROSE(new MatPrep(38, 4, true), new MatPrep(38, 4, true), new MatPrep(38, 4, true), new MatPrep(38, 4, true), new MatPrep(38, 4, true), new MatPrep(38, 4, true), new MatPrep("RED_TULIP", true), new MatPrep("RED_TULIP", true), new MatPrep("WITHER_ROSE"), new MatPrep("WITHER_ROSE")), 
    WITHER_SKELETON_SKULL(new MatPrep(397, 1)), 
    WITHER_SKELETON_SPAWN_EGG(new MatPrep(383, 51, true), new MatPrep(383, 51, true), new MatPrep(383, 51, true), new MatPrep(383, 51, true), new MatPrep(383, 5, true), new MatPrep(383, 5, true), new MatPrep("WITHER_SKELETON_SPAWN_EGG"), new MatPrep("WITHER_SKELETON_SPAWN_EGG"), new MatPrep("WITHER_SKELETON_SPAWN_EGG"), new MatPrep("WITHER_SKELETON_SPAWN_EGG")), 
    WITHER_SKELETON_WALL_SKULL(new MatPrep(397, 1)), 
    WOLF_SPAWN_EGG(new MatPrep(383, 95)), 
    WOODEN_AXE(new MatPrep(271)), 
    WOODEN_HOE(new MatPrep(290)), 
    WOODEN_PICKAXE(new MatPrep(270)), 
    WOODEN_SHOVEL(new MatPrep(269)), 
    WOODEN_SWORD(new MatPrep(268)), 
    WRITABLE_BOOK(new MatPrep(386)), 
    WRITTEN_BOOK(new MatPrep(387)), 
    YELLOW_BANNER(new MatPrep(425, 11)), 
    YELLOW_BED(new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, true), new MatPrep(355, 4), new MatPrep("YELLOW_BED"), new MatPrep("YELLOW_BED"), new MatPrep("YELLOW_BED"), new MatPrep("YELLOW_BED")), 
    YELLOW_CARPET(new MatPrep(171, 4)), 
    YELLOW_CONCRETE(new MatPrep(159, 4, true), new MatPrep(159, 4, true), new MatPrep(159, 4, true), new MatPrep(159, 4, true), new MatPrep(159, 4, true), new MatPrep(251, 4), new MatPrep("YELLOW_CONCRETE"), new MatPrep("YELLOW_CONCRETE"), new MatPrep("YELLOW_CONCRETE"), new MatPrep("YELLOW_CONCRETE")), 
    YELLOW_CONCRETE_POWDER(new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(12, true), new MatPrep(252, 4), new MatPrep("YELLOW_CONCRETE_POWDER"), new MatPrep("YELLOW_CONCRETE_POWDER"), new MatPrep("YELLOW_CONCRETE_POWDER"), new MatPrep("YELLOW_CONCRETE_POWDER")), 
    YELLOW_DYE(new MatPrep(351, 11), new MatPrep(351, 11), new MatPrep(351, 11), new MatPrep(351, 11), new MatPrep(351, 11), new MatPrep(351, 11), new MatPrep("DANDELION_YELLOW"), new MatPrep("DANDELION_YELLOW"), new MatPrep("YELLOW_DYE"), new MatPrep("YELLOW_DYE")), 
    YELLOW_GLAZED_TERRACOTTA(new MatPrep(159, 4, true), new MatPrep(159, 4, true), new MatPrep(159, 4, true), new MatPrep(159, 4, true), new MatPrep(159, 4, true), new MatPrep(239), new MatPrep("YELLOW_GLAZED_TERRACOTTA"), new MatPrep("YELLOW_GLAZED_TERRACOTTA"), new MatPrep("YELLOW_GLAZED_TERRACOTTA"), new MatPrep("YELLOW_GLAZED_TERRACOTTA")), 
    YELLOW_SHULKER_BOX(new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(130, true), new MatPrep(223), new MatPrep(223), new MatPrep("YELLOW_SHULKER_BOX"), new MatPrep("YELLOW_SHULKER_BOX"), new MatPrep("YELLOW_SHULKER_BOX"), new MatPrep("YELLOW_SHULKER_BOX")), 
    YELLOW_STAINED_GLASS(new MatPrep(95, 4)), 
    YELLOW_STAINED_GLASS_PANE(new MatPrep(160, 4)), 
    YELLOW_TERRACOTTA(new MatPrep(159, 4)), 
    YELLOW_WALL_BANNER(new MatPrep(425, 11)), 
    YELLOW_WOOL(new MatPrep(35, 4)), 
    ZOMBIE_HEAD(new MatPrep(397, 2)), 
    ZOMBIE_HORSE_SPAWN_EGG(new MatPrep(383, 29)), 
    ZOMBIE_SPAWN_EGG(new MatPrep(383, 54)), 
    ZOMBIE_VILLAGER_SPAWN_EGG(new MatPrep(383, 27)), 
    ZOMBIE_WALL_HEAD(new MatPrep(397, 2)), 
    CRIMSON_NYLIUM(Materials.NETHERRACK), 
    WARPED_NYLIUM(Materials.NETHERRACK), 
    CRIMSON_PLANKS(Materials.OAK_WOOD), 
    WARPED_PLANKS(Materials.DARK_OAK_WOOD), 
    NETHER_GOLD_ORE(Materials.NETHER_QUARTZ_ORE), 
    CRIMSON_STEM(Materials.OAK_LOG), 
    WARPED_STEM(Materials.DARK_OAK_LOG), 
    STRIPPED_CRIMSON_STEM(Materials.STRIPPED_OAK_LOG), 
    STRIPPED_WARPED_STEM(Materials.STRIPPED_DARK_OAK_LOG), 
    STRIPPED_CRIMSON_HYPHAE(Materials.STRIPPED_OAK_WOOD), 
    STRIPPED_WARPED_HYPHAE(Materials.STRIPPED_DARK_OAK_WOOD), 
    CRIMSON_HYPHAE(Materials.OAK_WOOD), 
    WARPED_HYPHAE(Materials.DARK_OAK_WOOD), 
    CRIMSON_FUNGUS(Materials.NETHER_WART), 
    WARPED_FUNGUS(Materials.NETHER_WART), 
    CRIMSON_ROOTS(Materials.NETHER_WART), 
    WARPED_ROOTS(Materials.NETHER_WART), 
    NETHER_SPROUTS(Materials.AIR), 
    WEEPING_VINES(Materials.VINE), 
    TWISTING_VINES(Materials.VINE), 
    CRIMSON_SLAB(Materials.VINE), 
    WARPED_SLAB(Materials.NETHER_BRICK_SLAB), 
    CRIMSON_PRESSURE_PLATE(Materials.OAK_PRESSURE_PLATE), 
    WARPED_PRESSURE_PLATE(Materials.DARK_OAK_PRESSURE_PLATE), 
    POLISHED_BLACKSTONE_PRESSURE_PLATE(Materials.OAK_PRESSURE_PLATE), 
    CRIMSON_FENCE(Materials.OAK_FENCE), 
    WARPED_FENCE(Materials.DARK_OAK_FENCE), 
    SOUL_SOIL(Materials.SOUL_SAND), 
    BASALT(Materials.COBBLESTONE), 
    POLISHED_BASALT(Materials.STONE_BRICKS), 
    SOUL_TORCH(Materials.TORCH), 
    CRIMSON_TRAPDOOR(Materials.OAK_TRAPDOOR), 
    WARPED_TRAPDOOR(Materials.DARK_OAK_TRAPDOOR), 
    CHAIN(Materials.IRON_BARS), 
    CRIMSON_FENCE_GATE(Materials.OAK_FENCE_GATE), 
    WARPED_FENCE_GATE(Materials.DARK_OAK_FENCE_GATE), 
    CRACKED_NETHER_BRICKS(Materials.NETHER_BRICKS), 
    CHISELED_NETHER_BRICKS(Materials.NETHER_BRICKS), 
    CRIMSON_STAIRS(Materials.OAK_STAIRS), 
    WARPED_STAIRS(Materials.DARK_OAK_STAIRS), 
    BLACKSTONE_WALL(Materials.COBBLESTONE_WALL), 
    POLISHED_BLACKSTONE_WALL(Materials.COBBLESTONE_WALL), 
    POLISHED_BLACKSTONE_BRICK_WALL(Materials.COBBLESTONE_WALL), 
    CRIMSON_BUTTON(Materials.OAK_BUTTON), 
    WARPED_BUTTON(Materials.DARK_OAK_BUTTON), 
    POLISHED_BLACKSTONE_BUTTON(Materials.STONE_BUTTON), 
    QUARTZ_BRICKS(Materials.QUARTZ_BLOCK), 
    WARPED_WART_BLOCK(Materials.AIR), 
    CRIMSON_DOOR(Materials.OAK_DOOR), 
    WARPED_DOOR(Materials.DARK_OAK_DOOR), 
    NETHERITE_INGOT(Materials.DIAMOND), 
    NETHERITE_SCRAP(Materials.AIR), 
    NETHERITE_SWORD(Materials.DIAMOND_SWORD), 
    NETHERITE_SHOVEL(Materials.DIAMOND_SHOVEL), 
    NETHERITE_PICKAXE(Materials.DIAMOND_PICKAXE), 
    NETHERITE_AXE(Materials.DIAMOND_AXE), 
    NETHERITE_HOE(Materials.DIAMOND_HOE), 
    NETHERITE_HELMET(Materials.DIAMOND_HELMET), 
    NETHERITE_CHESTPLATE(Materials.DIAMOND_CHESTPLATE), 
    NETHERITE_LEGGINGS(Materials.DIAMOND_LEGGINGS), 
    NETHERITE_BOOTS(Materials.DIAMOND_BOOTS), 
    CRIMSON_SIGN(Materials.OAK_SIGN), 
    WARPED_SIGN(Materials.DARK_OAK_SIGN), 
    HOGLIN_SPAWN_EGG(Materials.AIR), 
    PIGLIN_SPAWN_EGG(new MatPrep(383, 57), new MatPrep("ZOMBIE_PIGMAN_SPAWN_EGG"), null),
    PIGLIN_BRUTE_SPAWN_EGG(new MatPrep(383, 57, true), new MatPrep("ZOMBIE_PIGMAN_SPAWN_EGG", true), new MatPrep("PIGLIN_SPAWN_EGG", true), null),
    STRIDER_SPAWN_EGG(Materials.AIR), 
    ZOGLIN_SPAWN_EGG(Materials.AIR), 
    ZOMBIFIED_PIGLIN_SPAWN_EGG(new MatPrep(383, 57), new MatPrep("ZOMBIE_PIGMAN_SPAWN_EGG"), null),
    WARPED_FUNGUS_ON_A_STICK(Materials.CARROT_ON_A_STICK), 
    MUSIC_DISC_PIGSTEP(Materials.MUSIC_DISC_13), 
    PIGLIN_BANNER_PATTERN(Materials.CREEPER_BANNER_PATTERN), 
    SOUL_LANTERN(Materials.SEA_LANTERN), 
    SOUL_CAMPFIRE(Materials.AIR), 
    SHROOMLIGHT(Materials.GLOWSTONE), 
    LODESTONE(Materials.CHISELED_STONE_BRICKS), 
    NETHERITE_BLOCK(Materials.DIAMOND_BLOCK), 
    ANCIENT_DEBRIS(Materials.GRANITE), 
    TARGET(Materials.AIR), 
    CRYING_OBSIDIAN(Materials.OBSIDIAN), 
    BLACKSTONE(Materials.COBBLESTONE), 
    BLACKSTONE_SLAB(Materials.COBBLESTONE_SLAB), 
    BLACKSTONE_STAIRS(Materials.COBBLESTONE_STAIRS), 
    GILDED_BLACKSTONE(Materials.MOSSY_COBBLESTONE), 
    POLISHED_BLACKSTONE(Materials.POLISHED_DIORITE), 
    POLISHED_BLACKSTONE_SLAB(Materials.POLISHED_DIORITE_SLAB), 
    POLISHED_BLACKSTONE_STAIRS(Materials.POLISHED_DIORITE_STAIRS), 
    CHISELED_POLISHED_BLACKSTONE(Materials.POLISHED_DIORITE), 
    POLISHED_BLACKSTONE_BRICKS(Materials.POLISHED_DIORITE), 
    POLISHED_BLACKSTONE_BRICK_SLAB(Materials.POLISHED_DIORITE_SLAB), 
    POLISHED_BLACKSTONE_BRICK_STAIRS(Materials.POLISHED_DIORITE_SLAB), 
    CRACKED_POLISHED_BLACKSTONE_BRICKS(Materials.POLISHED_DIORITE_SLAB), 
    RESPAWN_ANCHOR(Materials.AIR), 
    SOUL_FIRE(Materials.FIRE), 
    SOUL_WALL_TORCH(Materials.WALL_TORCH), 
    WEEPING_VINES_PLANT(Materials.VINE), 
    TWISTING_VINES_PLANT(Materials.VINE), 
    CRIMSON_WALL_SIGN(Materials.OAK_WALL_SIGN), 
    WARPED_WALL_SIGN(Materials.DARK_OAK_WALL_SIGN), 
    POTTED_CRIMSON_FUNGUS(Materials.AIR), 
    POTTED_WARPED_FUNGUS(Materials.AIR), 
    POTTED_CRIMSON_ROOTS(Materials.AIR), 
    POTTED_WARPED_ROOTS(Materials.AIR);
    
    private static String sess;
    private static HashMap<Materials, ItemStack> cache;
    private static List<Materials> materialsWithPlaceholder;
    private MatPrep prep;
    private Materials prepared;
    
    public static void createCache() {
        for (final Materials key : values()) {
            if (key.isPlaceholder()) {
                Materials.materialsWithPlaceholder.add(key);
            }
            Materials.cache.put(key, key.getItem().build());
        }
    }
    
    public ItemBuilder getItem() {
        ItemBuilder itemBuilder = null;
        if (this.prepared != null) {
            return this.prepared.getItem();
        }
        switch (this.prep.getPrepType()) {
            case MAT: {
                itemBuilder = new ItemBuilder(this.prep.getMaterial());
                break;
            }
            case ID: {
                itemBuilder = new ItemBuilder(this.prep.getId());
                break;
            }
            case ID_DATA: {
                itemBuilder = new ItemBuilder(this.prep.getId(), this.prep.getData());
                break;
            }
        }
        if (this.prep.isPlaceholder()) {
            Bukkit.getPluginManager().callEvent(new MaterialLegacyEvent(this.toString(), itemBuilder.getMaterial() + (this.prep.getPrepType().equals(MatPrep.PrepType.ID_DATA) ? (":" + this.prep.getData()) : "")));
        }
        return itemBuilder;
    }
    
    public boolean isPlaceholder() {
        return this.prepared != null || this.prep.isPlaceholder();
    }
    
    public boolean hasData() {
        return !API.getVersionSupport().contains(13, 14, 15, 16) && this.prep.getPrepType().equals(MatPrep.PrepType.ID_DATA);
    }
    
    public int getData() {
        return this.prep.getData();
    }
    
    public static Materials getItemStackMaterial(final ItemStack itemStack) {
        for (final Materials materials : values()) {
            if (!Materials.materialsWithPlaceholder.contains(materials)) {
                boolean b = false;
                if (API.getVersionSupport().contains(8, 9, 10, 11, 12)) {
                    if (Materials.cache.get(materials).getTypeId() == itemStack.getTypeId()) {
                        b = true;
                    }
                }
                else {
                    b = true;
                }
                if (b && Materials.cache.get(materials).getType().equals(itemStack.getType())) {
                    return materials;
                }
            }
        }
        return Materials.AIR;
    }
    
    public static Materials getBlockMaterial(final Block block) {
        final ItemBuilder itemBuilder = new ItemBuilder(block.getType());
        if (API.getVersionSupport().contains(8, 9, 10, 11, 12)) {
            itemBuilder.setData(block.getTypeId());
        }
        final ItemStack build = itemBuilder.build();
        for (final Materials materials : values()) {
            if (!Materials.materialsWithPlaceholder.contains(materials)) {
                boolean b = false;
                if (API.getVersionSupport().contains(8, 9, 10, 11, 12)) {
                    if (Materials.cache.get(materials).getTypeId() == build.getTypeId()) {
                        b = true;
                    }
                }
                else {
                    b = true;
                }
                if (b && Materials.cache.get(materials).getType().equals(build.getType())) {
                    return materials;
                }
            }
        }
        return Materials.AIR;
    }
    
    public static Materials getBlockMaterial(final World world, final int n, final int n2, final int n3) {
        final Block block = world.getBlockAt(n, n2, n3);
        final ItemBuilder itemBuilder = new ItemBuilder(block.getType());
        if (API.getVersionSupport().contains(8, 9, 10, 11, 12)) {
            itemBuilder.setData(block.getTypeId());
        }
        final ItemStack build = itemBuilder.build();
        for (final Materials materials : values()) {
            if (!Materials.materialsWithPlaceholder.contains(materials)) {
                boolean b = false;
                if (API.getVersionSupport().contains(8, 9, 10, 11, 12)) {
                    if (Materials.cache.get(materials).getTypeId() == build.getTypeId()) {
                        b = true;
                    }
                }
                else {
                    b = true;
                }
                if (b && Materials.cache.get(materials).getType().equals(build.getType())) {
                    return materials;
                }
            }
        }
        return Materials.AIR;
    }
    
    Materials(final MatPrep prep, final MatPrep prep2, final MatPrep prep3, final MatPrep prep4, final MatPrep prep5, final MatPrep prep6, final MatPrep prep7, final MatPrep prep8, final MatPrep prep9, final MatPrep matPrep) {
        this.prepared = null;
        final String version = API.getVersion();
        switch (version) {
            case "v1_8_R3": {
                this.prep = prep;
                break;
            }
            case "v1_9_R1": {
                this.prep = prep2;
                break;
            }
            case "v1_9_R2": {
                this.prep = prep3;
                break;
            }
            case "v1_10_R1": {
                this.prep = prep4;
                break;
            }
            case "v1_11_R1": {
                this.prep = prep5;
                break;
            }
            case "v1_12_R1": {
                this.prep = prep6;
                break;
            }
            case "v1_13_R1": {
                this.prep = prep7;
                break;
            }
            case "v1_13_R2": {
                this.prep = prep8;
                break;
            }
            case "v1_14_R1": {
                this.prep = prep9;
                break;
            }
            case "v1_15_R1": {
                this.prep = matPrep;
                break;
            }
            case "v1_16_R1":
            case "v1_16_R2":
            case "v1_16_R3": {
                this.prep = matPrep;
                break;
            }
        }
    }
    
    Materials(final Materials prepared) {
        this.prepared = null;
        final String version = API.getVersion();
        switch (version) {
            case "v1_8_R3":
            case "v1_9_R1":
            case "v1_9_R2":
            case "v1_10_R1":
            case "v1_11_R1":
            case "v1_12_R1":
            case "v1_13_R1":
            case "v1_13_R2":
            case "v1_14_R1":
            case "v1_15_R1": {
                this.prepared = prepared;
                break;
            }
            case "v1_16_R1":
            case "v1_16_R2":
            case "v1_16_R3": {
                this.prep = new MatPrep(this.toString());
                break;
            }
        }
    }
    
    Materials(final MatPrep prep, final MatPrep prep2) {
        this.prepared = null;
        final String version = API.getVersion();
        switch (version) {
            case "v1_8_R3":
            case "v1_9_R1":
            case "v1_9_R2":
            case "v1_10_R1":
            case "v1_11_R1":
            case "v1_12_R1": {
                this.prep = prep;
                break;
            }
            case "v1_13_R1":
            case "v1_13_R2":
            case "v1_14_R1":
            case "v1_15_R1":
            case "v1_16_R1":
            case "v1_16_R2":
            case "v1_16_R3": {
                this.prep = prep2;
                break;
            }
        }
    }
    
    Materials() {
        this.prepared = null;
        this.prep = new MatPrep(this.toString());
    }
    
    Materials(final MatPrep prep) {
        this.prepared = null;
        final String version = API.getVersion();
        switch (version) {
            case "v1_8_R3":
            case "v1_9_R1":
            case "v1_9_R2":
            case "v1_10_R1":
            case "v1_11_R1":
            case "v1_12_R1": {
                this.prep = prep;
                break;
            }
            case "v1_13_R1":
            case "v1_13_R2":
            case "v1_14_R1":
            case "v1_15_R1":
            case "v1_16_R1":
            case "v1_16_R2":
            case "v1_16_R3": {
                this.prep = new MatPrep(this.toString());
                break;
            }
        }
    }
    
    Materials(final MatPrep prep, final MatPrep prep2, final Object o) {
        this.prepared = null;
        final String version = API.getVersion();
        switch (version) {
            case "v1_8_R3":
            case "v1_9_R1":
            case "v1_9_R2":
            case "v1_10_R1":
            case "v1_11_R1":
            case "v1_12_R1": {
                this.prep = prep;
                break;
            }
            case "v1_13_R1":
            case "v1_13_R2":
            case "v1_14_R1":
            case "v1_15_R1": {
                this.prep = prep2;
                break;
            }
            case "v1_16_R1":
            case "v1_16_R2":
            case "v1_16_R3": {
                this.prep = new MatPrep(this.toString());
                break;
            }
        }
    }
    
    Materials(final MatPrep prep, final MatPrep prep2, final MatPrep prep3, final Object o) {
        this.prepared = null;
        final String version = API.getVersion();
        switch (version) {
            case "v1_8_R3":
            case "v1_9_R1":
            case "v1_9_R2":
            case "v1_10_R1":
            case "v1_11_R1":
            case "v1_12_R1": {
                this.prep = prep;
                break;
            }
            case "v1_13_R1":
            case "v1_13_R2":
            case "v1_14_R1":
            case "v1_15_R1": {
                this.prep = prep2;
                break;
            }
            case "v1_16_R1": {
                this.prep = prep3;
                break;
            }
            case "v1_16_R2":
            case "v1_16_R3": {
                this.prep = new MatPrep(this.toString());
                break;
            }
        }
    }
    
    static {
        Materials.sess = "U: 81440 R: 60380 N: 384421075";
        Materials.cache = new HashMap<Materials, ItemStack>();
        Materials.materialsWithPlaceholder = new ArrayList<Materials>();
    }
}
