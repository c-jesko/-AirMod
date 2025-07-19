package com.senna.air.block;

import com.senna.air.AirMod;
import com.senna.air.item.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block HIGH_CARBON_INGOT_BLOCK = registerBlocks("high_carbon_ingot_block",new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block LOW_CARBON_INGOT_BLOCK = registerBlocks("low_carbon_ingot_block",new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));


    private static void addBlocksToIG(FabricItemGroupEntries fabricItemGroupEntries) {   //
        fabricItemGroupEntries.add(HIGH_CARBON_INGOT_BLOCK);
        fabricItemGroupEntries.add(LOW_CARBON_INGOT_BLOCK);
    }


    private static Block registerBlocks(String name,Block block){
        registerBlockItems(name,block);
        return Registry.register(Registries.BLOCK,new Identifier(AirMod.MOD_ID,name),block);
    }


    private static Item registerBlockItems(String name, Block block){
        return Registry.register(Registries.ITEM,new Identifier(AirMod.MOD_ID,name),new BlockItem(block,new FabricItemSettings()));
    }



    public static void registerModBlocks(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModBlocks::addBlocksToIG);

    }
}
