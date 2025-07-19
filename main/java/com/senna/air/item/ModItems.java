package com.senna.air.item;

import com.senna.air.AirMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item Low_Carbon_Ingot = registerItems("low_carbon_ingot",new Item(new FabricItemSettings()));
    public static final Item Low_Carbon_Ingot_Block = registerItems("low_carbon_ingot_block",new Item(new FabricItemSettings()));
    public static final Item High_Carbon_Ingot = registerItems("high_carbon_ingot",new Item(new FabricItemSettings()));
    public static final Item High_Carbon_Ingot_Block = registerItems("high_carbon_ingot_block",new Item(new FabricItemSettings()));

    private static void addItemToItem_group(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(Low_Carbon_Ingot);
        fabricItemGroupEntries.add(Low_Carbon_Ingot_Block);
        fabricItemGroupEntries.add(High_Carbon_Ingot);
        fabricItemGroupEntries.add(High_Carbon_Ingot_Block);
    }

    private static Item registerItems(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(AirMod.MOD_ID,name),item);
    }
    //初始化方法
    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToItem_group);

    }
}
