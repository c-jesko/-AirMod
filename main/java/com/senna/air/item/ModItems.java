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
    public static final Item FLAMED_STRING = registerItems("flamed_string",new Item(new FabricItemSettings()));
    public static final Item WITHERED_BONE = registerItems("withered_bone",new Item(new FabricItemSettings()));
    public static final Item WITHERED_BONE_POWDER = registerItems("withered_bone_powder",new Item(new FabricItemSettings()));
    public static final Item MILE_CARBON_STEEL_INGOT = registerItems("mile_carbon_steel_ingot",new Item(new FabricItemSettings()));
    public static final Item HIGH_CARBON_STEEL_INGOT = registerItems("high_carbon_steel_ingot",new Item(new FabricItemSettings()));



    public static final Item HIGH_CARBON_STEEL_PICKAXE = registerItems("high_carbon_steel_pickaxe",new PickaxeItem(ModToolMaterial.HIGH_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_CARBON_STEEL_AXE = registerItems("high_carbon_steel_axe",new AxeItem(ModToolMaterial.HIGH_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_CARBON_STEEL_SHOVE = registerItems("high_carbon_steel_shove",new ShovelItem(ModToolMaterial.HIGH_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_CARBON_STEEL_SWORD = registerItems("high_carbon_steel_sword",new SwordItem(ModToolMaterial.HIGH_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_CARBON_STEEL_HOE = registerItems("high_carbon_steel_hoe",new HoeItem(ModToolMaterial.HIGH_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_CARBON_STEEL_PICKAXE = registerItems("mile_carbon_steel_pickaxe",new PickaxeItem(ModToolMaterial.MILE_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_CARBON_STEEL_AXE = registerItems("mile_carbon_steel_axe",new AxeItem(ModToolMaterial.MILE_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_CARBON_STEEL_SHOVE = registerItems("mile_carbon_steel_shove",new ShovelItem(ModToolMaterial.MILE_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_CARBON_STEEL_SWORD = registerItems("mile_carbon_steel_sword",new SwordItem(ModToolMaterial.MILE_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));
    public static final Item MILE_CARBON_STEEL_HOE = registerItems("mile_carbon_steel_hoe",new HoeItem(ModToolMaterial.MILE_CARBON_STEEL_INGOT,2,2f,new FabricItemSettings()));

    public static final Item FIRE_BOW = registerItems("fire_bow", new EnchantedBowItem(new FabricItemSettings().maxDamage(500),Enchantments.FLAME));


    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries){   //加入原材料的
        fabricItemGroupEntries.add(FLAMED_STRING);
        fabricItemGroupEntries.add(WITHERED_BONE);
        fabricItemGroupEntries.add(WITHERED_BONE_POWDER);
        fabricItemGroupEntries.add(MILE_CARBON_STEEL_INGOT);
        fabricItemGroupEntries.add(HIGH_CARBON_STEEL_INGOT);


    }

    private static void addItemsToIG1(FabricItemGroupEntries fabricItemGroupEntries) {  //加入工具的

        fabricItemGroupEntries.add(MILE_CARBON_STEEL_SHOVE);
        fabricItemGroupEntries.add(MILE_CARBON_STEEL_PICKAXE);
        fabricItemGroupEntries.add(MILE_CARBON_STEEL_AXE);
        fabricItemGroupEntries.add(MILE_CARBON_STEEL_HOE);
        fabricItemGroupEntries.add(HIGH_CARBON_STEEL_SHOVE);
        fabricItemGroupEntries.add(HIGH_CARBON_STEEL_PICKAXE);
        fabricItemGroupEntries.add(HIGH_CARBON_STEEL_AXE);
        fabricItemGroupEntries.add(HIGH_CARBON_STEEL_HOE);

    }
    private static void addItemsToIG2(FabricItemGroupEntries fabricItemGroupEntries){//加入武器装备的
        fabricItemGroupEntries.add(MILE_CARBON_STEEL_SWORD);
        fabricItemGroupEntries.add(HIGH_CARBON_STEEL_SWORD);
        fabricItemGroupEntries.add(MILE_CARBON_STEEL_AXE);
        fabricItemGroupEntries.add(HIGH_CARBON_STEEL_AXE);
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
