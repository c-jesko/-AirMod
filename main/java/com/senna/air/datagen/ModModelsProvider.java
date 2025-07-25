package com.senna.air.datagen;

import com.senna.air.block.ModBlocks;
import com.senna.air.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HIGH_CARBON_STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MILE_CARBON_STEEL_BLOCK);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.FLAMED_STRING, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITHERED_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITHERED_BONE_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.HIGH_CARBON_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MILE_CARBON_STEEL_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.HIGH_CARBON_STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HIGH_CARBON_STEEL_SHOVE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HIGH_CARBON_STEEL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HIGH_CARBON_STEEL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HIGH_CARBON_STEEL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MILE_CARBON_STEEL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MILE_CARBON_STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MILE_CARBON_STEEL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MILE_CARBON_STEEL_SHOVE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MILE_CARBON_STEEL_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.FIRE_BOW, Models.HANDHELD);

    }
}
