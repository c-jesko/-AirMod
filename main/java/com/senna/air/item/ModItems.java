package com.senna.air.item;

import com.senna.air.AirMod;
import com.senna.air.EnchantedBowItem.EnchantedBowItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TINDER_WIRE = registerItems("tinder_wire",new Item(new FabricItemSettings()));
    public static final Item WITHERED_BONE = registerItems("withered_bone",new Item(new FabricItemSettings()));
    public static final Item WITHERED_BONE_POWDER = registerItems("withered_bone_powder",new Item(new FabricItemSettings()));
    public static final Item MILE_STEEL_INGOT = registerItems("mile_steel_ingot",new Item(new FabricItemSettings()));
    public static final Item HIGH_STEEL_INGOT = registerItems("high_steel_ingot",new Item(new FabricItemSettings()));



    public static final Item HIGH_STEEL_INGOT_PICKAXE = registerItems("high_steel_ingot_pickaxe",new PickaxeItem(ModToolMaterial.HIGH_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_STEEL_INGOT_AXE = registerItems("high_steel_ingot_axe",new AxeItem(ModToolMaterial.HIGH_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_STEEL_INGOT_SHOVE = registerItems("high_steel_ingot_shove",new ShovelItem(ModToolMaterial.HIGH_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_STEEL_INGOT_SWORD = registerItems("high_steel_ingot_sword",new SwordItem(ModToolMaterial.HIGH_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_STEEL_INGOT_HOE = registerItems("high_steel_ingot_hoe",new HoeItem(ModToolMaterial.HIGH_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_STEEL_INGOT_PICKAXE = registerItems("mile_steel_ingot_pickaxe",new PickaxeItem(ModToolMaterial.MILE_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_STEEL_INGOT_AXE = registerItems("mile_steel_ingot_axe",new AxeItem(ModToolMaterial.MILE_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_STEEL_INGOT_SHOVE = registerItems("mile_steel_ingot_shove",new ShovelItem(ModToolMaterial.MILE_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_STEEL_INGOT_SWORD = registerItems("mile_steel_ingot_sword",new SwordItem(ModToolMaterial.MILE_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_STEEL_INGOT_HOE = registerItems("mile_steel_ingot_hoe",new HoeItem(ModToolMaterial.MILE_STEEL_INGOT,2,2f,new FabricItemSettings()));

    public static final Item FIRE_BOW = registerItems("fire_bow", new EnchantedBowItem(new FabricItemSettings().maxDamage(500),Enchantments.FLAME));


    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries){   //加入原材料的
        fabricItemGroupEntries.add(TINDER_WIRE);
        fabricItemGroupEntries.add(WITHERED_BONE);
        fabricItemGroupEntries.add(WITHERED_BONE_POWDER);
        fabricItemGroupEntries.add(MILE_STEEL_INGOT);
        fabricItemGroupEntries.add(HIGH_STEEL_INGOT);


    }

    private static void addItemsToIG1(FabricItemGroupEntries fabricItemGroupEntries) {  //加入工具的

        fabricItemGroupEntries.add(MILE_STEEL_INGOT_SHOVE);
        fabricItemGroupEntries.add(MILE_STEEL_INGOT_PICKAXE);
        fabricItemGroupEntries.add(MILE_STEEL_INGOT_AXE);
        fabricItemGroupEntries.add(MILE_STEEL_INGOT_HOE);
        fabricItemGroupEntries.add(HIGH_STEEL_INGOT_SHOVE);
        fabricItemGroupEntries.add(HIGH_STEEL_INGOT_PICKAXE);
        fabricItemGroupEntries.add(HIGH_STEEL_INGOT_AXE);
        fabricItemGroupEntries.add(HIGH_STEEL_INGOT_HOE);

    }
    private static void addItemsToIG2(FabricItemGroupEntries fabricItemGroupEntries){//加入武器装备的
        fabricItemGroupEntries.add(MILE_STEEL_INGOT_SWORD);
        fabricItemGroupEntries.add(HIGH_STEEL_INGOT_SWORD);
        fabricItemGroupEntries.add(MILE_STEEL_INGOT_AXE);
        fabricItemGroupEntries.add(HIGH_STEEL_INGOT_AXE);
        fabricItemGroupEntries.add(FIRE_BOW);

    }


    private static Item registerItems(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(AirMod.MOD_ID,name),item);
    }
    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIG);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToIG1);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToIG2);
    }

}
