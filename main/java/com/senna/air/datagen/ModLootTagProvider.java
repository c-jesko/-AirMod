package com.senna.air.datagen;

import com.senna.air.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTagProvider extends FabricBlockLootTableProvider {
    public ModLootTagProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.HIGH_CARBON_STEEL_BLOCK);
        addDrop(ModBlocks.MILE_CARBON_STEEL_BLOCK);

    }
}
