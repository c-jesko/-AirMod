package com.senna.air.item;

import com.senna.air.AirMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item TINDER_WIRE = registerItems("tinder_wire",new Item(new FabricItemSettings()));
    public static final Item WITHERED_BONE = registerItems("withered_bone",new Item(new FabricItemSettings()));
    public static final Item WITHERED_BONE_POWDER = registerItems("withered_bone_powder",new Item(new FabricItemSettings()));
    public static final Item Low_Carbon_Ingot = registerItems("low_carbon_ingot",new Item(new FabricItemSettings()));
    public static final Item High_Carbon_Ingot = registerItems("high_carbon_ingot",new Item(new FabricItemSettings()));



    public static final Item HIGH_CARBON_INGOT_PICKAXE = registerItems("high_carbon_ingot_pickaxe",new PickaxeItem(ModToolMaterial.HIGH_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_CARBON_INGOT_AXE = registerItems("high_carbon_ingot_axe",new AxeItem(ModToolMaterial.HIGH_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_CARBON_INGOT_SHOVE = registerItems("high_carbon_ingot_shove",new ShovelItem(ModToolMaterial.HIGH_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_CARBON_INGOT_SWORD = registerItems("high_carbon_ingot_sword",new SwordItem(ModToolMaterial.HIGH_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item HIGH_CARBON_INGOT_HOE = registerItems("high_carbon_ingot_hoe",new HoeItem(ModToolMaterial.HIGH_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item LOW_CARBON_INGOT_PICKAXE = registerItems("low_carbon_ingot_pickaxe",new PickaxeItem(ModToolMaterial.LOW_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item LOW_CARBON_INGOT_AXE = registerItems("low_carbon_ingot_axe",new AxeItem(ModToolMaterial.LOW_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item LOW_CARBON_INGOT_SHOVE = registerItems("low_carbon_ingot_shove",new ShovelItem(ModToolMaterial.LOW_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item LOW_CARBON_INGOT_SWORD = registerItems("low_carbon_ingot_sword",new SwordItem(ModToolMaterial.LOW_CARBON_INGOT,2,2f,new FabricItemSettings()));
    public static final Item LOW_CARBON_INGOT_HOE = registerItems("low_carbon_ingot_hoe",new HoeItem(ModToolMaterial.LOW_CARBON_INGOT,2,2f,new FabricItemSettings()));


    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries){   //加入原材料的
        fabricItemGroupEntries.add(TINDER_WIRE);
        fabricItemGroupEntries.add(WITHERED_BONE);
        fabricItemGroupEntries.add(WITHERED_BONE_POWDER);
        fabricItemGroupEntries.add(Low_Carbon_Ingot);
        fabricItemGroupEntries.add(High_Carbon_Ingot);

    }

    private static void addItemsToIG1(FabricItemGroupEntries fabricItemGroupEntries) {   //加入武器装备的
        fabricItemGroupEntries.add(HIGH_CARBON_INGOT_AXE);
        fabricItemGroupEntries.add(HIGH_CARBON_INGOT_SHOVE);
        fabricItemGroupEntries.add(HIGH_CARBON_INGOT_PICKAXE);
        fabricItemGroupEntries.add(HIGH_CARBON_INGOT_SWORD);
        fabricItemGroupEntries.add(HIGH_CARBON_INGOT_HOE);
        fabricItemGroupEntries.add(LOW_CARBON_INGOT_AXE);
        fabricItemGroupEntries.add(LOW_CARBON_INGOT_SHOVE);
        fabricItemGroupEntries.add(LOW_CARBON_INGOT_PICKAXE);
        fabricItemGroupEntries.add(LOW_CARBON_INGOT_SWORD);
        fabricItemGroupEntries.add(LOW_CARBON_INGOT_HOE);

    }


    private static Item registerItems(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(AirMod.MOD_ID,name),item);
    }
    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIG);
    }

}
