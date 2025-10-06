package com.resourcechooks;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import org.jetbrains.annotations.Nullable;



public class BreedingTable {
    public static final Item[] ITEMS = new Item[] {
            Items.BAKED_POTATO,
            Items.BLACK_CONCRETE,
            Items.BLACK_CONCRETE,
            Items.BLUE_CONCRETE,
            Items.BLUE_CONCRETE_POWDER,
            Items.BLUE_DYE,
            Items.BONE_MEAL,
            Items.BOOKSHELF,
            Items.BREAD,
            Items.BROWN_CONCRETE,
            Items.BROWN_CONCRETE,
            Items.CAKE,
            Items.CAKE,
            Items.COOKED_BEEF,
            Items.COOKED_CHICKEN,
            Items.COOKED_COD,
            Items.COOKED_PORKCHOP,
            Items.COOKIE,
            Items.COPPER_INGOT,
            Items.CYAN_CONCRETE,
            Items.DIAMOND,
            Items.DIAMOND,
            Items.DIAMOND_BLOCK,
            Items.EMERALD_BLOCK,
            Items.ENDER_EYE,
            Items.GLASS,
            Items.GOLD_BLOCK,
            Items.GOLD_INGOT,
            Items.GRAY_CONCRETE,
            Items.GREEN_CONCRETE,
            Items.GREEN_CONCRETE_POWDER,
            Items.IRON_BLOCK,
            Items.IRON_INGOT,
            Items.ITEM_FRAME,
            Items.LEAD,
            Items.LIGHT_BLUE_CONCRETE,
            Items.LIGHT_BLUE_DYE,
            Items.LIGHT_GRAY_CONCRETE,
            Items.LIME_CONCRETE,
            Items.MAGENTA_CONCRETE,
            Items.ORANGE_CONCRETE,
            Items.PAPER,
            Items.PINK_CONCRETE,
            Items.POISONOUS_POTATO,
            Items.PUMPKIN_PIE,
            Items.PURPLE_CONCRETE,
            Items.REDSTONE_BLOCK,
            Items.RED_CONCRETE,
            Items.RED_CONCRETE_POWDER,
            Items.RED_DYE,
            Items.STONE,
            Items.TRIPWIRE_HOOK,
            Items.WATER_BUCKET,
            Items.WHITE_CONCRETE,
            Items.WHITE_CONCRETE,
            Items.WHITE_DYE,
            Items.YELLOW_CONCRETE,
            Items.YELLOW_DYE,
    };


    @Nullable
    public static Item lookup(Item itemA, Item itemB, boolean reflect) {
        if (itemA == Items.GLASS && itemB == Items.BLUE_DYE) {
            return Items.DIAMOND;
        }
        else if (itemA == Items.GLASS && itemB == Items.LIGHT_BLUE_DYE) {
            return Items.DIAMOND;
        }
        else if (itemA == Items.IRON_INGOT && itemB == Items.REDSTONE) {
            return Items.IRON_BLOCK;
        }
        else if (itemA == Items.GOLD_INGOT && itemB == Items.REDSTONE) {
            return Items.GOLD_BLOCK;
        }else if (itemA == Items.DIAMOND && itemB == Items.EMERALD) {
            return Items.DIAMOND_BLOCK;
        }
        else if (itemA == Items.REDSTONE && itemB == Items.REDSTONE) {
            return Items.REDSTONE_BLOCK;
        }
        else if (itemA == Items.EMERALD && itemB == Items.EMERALD) {
            return Items.EMERALD_BLOCK;
        }
        else if (itemA == Items.SAND && itemB == Items.RED_DYE) {
            return Items.RED_CONCRETE_POWDER;
        }
        else if (itemA == Items.SAND && itemB == Items.BLUE_DYE) {
            return Items.BLUE_CONCRETE_POWDER;
        }
        else if (itemA == Items.SAND && itemB == Items.GREEN_DYE) {
            return Items.GREEN_CONCRETE_POWDER;
        }
        else if (itemA == Items.COAL && itemB == Items.COBBLESTONE) {
            return Items.STONE;
        }
        else if (itemA == Items.OBSIDIAN && itemB == Items.BUCKET) {
            return Items.WATER_BUCKET;
        }
        else if (itemA == Items.SUGAR && itemB == Items.EGG) {
            return Items.CAKE;
        }
        else if (itemA == Items.COCOA_BEANS && itemB == Items.SUGAR) {
            return Items.COOKIE;
        }
        else if (itemA == Items.ENDER_PEARL && itemB == Items.BLAZE_POWDER) {
            return Items.ENDER_EYE;
        }
        else if (itemA == Items.FEATHER && itemB == Items.STRING) {
            return Items.LEAD;
        }
        else if (itemA == Items.BONE && itemB == Items.STRING) {
            return Items.BONE_MEAL;
        }
        else if (itemA == Items.ROTTEN_FLESH && itemB == Items.CARROT) {
            return Items.POISONOUS_POTATO;
        }
        else if (itemA == Items.POTATO && itemB == Items.FIRE_CHARGE) {
            return Items.BAKED_POTATO;
        }
        else if (itemA == Items.WHEAT && itemB == Items.WHEAT) {
            return Items.BREAD;
        }
        else if (itemA == Items.PUMPKIN && itemB == Items.EGG) {
            return Items.PUMPKIN_PIE;
        }
        else if (itemA == Items.BEEF && itemB == Items.COAL) {
            return Items.COOKED_BEEF;
        }
        else if (itemA == Items.CHICKEN && itemB == Items.COAL) {
            return Items.COOKED_CHICKEN;
        }
        else if (itemA == Items.PORKCHOP && itemB == Items.COAL) {
            return Items.COOKED_PORKCHOP;
        }
        else if (itemA == Items.COD && itemB == Items.COAL) {
            return Items.COOKED_COD;
        }
        else if (itemA == Items.EGG && itemB == Items.SUGAR) {
            return Items.CAKE;
        }
        else if (itemA == Items.LEATHER && itemB == Items.STRING) {
            return Items.ITEM_FRAME;
        }
        else if (itemA == Items.STRING && itemB == Items.STRING) {
            return Items.TRIPWIRE_HOOK;
        }
        else if (itemA == Items.SUGAR_CANE && itemB == Items.SUGAR_CANE) {
            return Items.PAPER;
        }
        else if (itemA == Items.OAK_PLANKS && itemB == Items.PAPER) {
            return Items.BOOKSHELF;
        }
        else if (itemA == Items.BONE_MEAL && itemB == Items.BONE_MEAL) {
            return Items.WHITE_DYE;
        }
        else if (itemA == Items.ROSE_BUSH && itemB == Items.ROSE_BUSH) {
            return Items.RED_DYE;
        }
        else if (itemA == Items.DANDELION && itemB == Items.DANDELION) {
            return Items.YELLOW_DYE;
        }
        else if (itemA == Items.WHITE_DYE && itemB == Items.BLUE_DYE) {
            return Items.LIGHT_BLUE_DYE;
        }
        else if (itemA == Items.LAPIS_LAZULI && itemB == Items.LAPIS_LAZULI) {
            return Items.BLUE_DYE;
        }
        else if (itemA == Items.SAND && itemB == Items.WHITE_DYE) {
            return Items.WHITE_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.BLACK_DYE) {
            return Items.BLACK_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.ORANGE_DYE) {
            return Items.ORANGE_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.PINK_DYE) {
            return Items.PINK_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.BROWN_DYE) {
            return Items.BROWN_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.LIME_DYE) {
            return Items.LIME_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.CYAN_DYE) {
            return Items.CYAN_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.GRAY_DYE) {
            return Items.GRAY_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.LIGHT_BLUE_DYE) {
            return Items.LIGHT_BLUE_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.MAGENTA_DYE) {
            return Items.MAGENTA_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.LIGHT_GRAY_DYE) {
            return Items.LIGHT_GRAY_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.PURPLE_DYE) {
            return Items.PURPLE_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.BLUE_DYE) {
            return Items.BLUE_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.YELLOW_DYE) {
            return Items.YELLOW_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.GREEN_DYE) {
            return Items.GREEN_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.RED_DYE) {
            return Items.RED_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.BLACK_DYE) {
            return Items.BLACK_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.BONE_MEAL) {
            return Items.WHITE_CONCRETE;
        }
        else if (itemA == Items.SAND && itemB == Items.COCOA_BEANS) {
            return Items.BROWN_CONCRETE;
        }
        else if (itemA == Items.RAW_IRON && itemB == Items.COAL) {
            return Items.IRON_INGOT;
        }
        else if (itemA == Items.RAW_COPPER && itemB == Items.COAL) {
            return Items.COPPER_INGOT;
        }
        else if (itemA == Items.RAW_GOLD && itemB == Items.COAL) {
            return Items.GOLD_INGOT;
        }
        else if (itemA == Items.COAL && itemB == Items.SAND) {
            return Items.GLASS;
        }
        if (reflect) {
            return BreedingTable.lookup(itemB, itemA, false);
        }
	return null;
    }

}
