package com.senna.air;

import com.senna.air.block.ModBlocks;
import com.senna.air.detecter.DimensionChecker;
import com.senna.air.entity.ModEntities;
import com.senna.air.entity.ModEvents;
import com.senna.air.entity.custom.StrangerEntity;
import com.senna.air.entity.custom.VisitorEntity;
import com.senna.air.entity.custom.WatcherEntity;
import com.senna.air.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirMod implements ModInitializer {

	private static final Identifier ENTER_NETHER_ID =
			new Identifier("minecraft", "story/enter_the_nether");



	public static final String MOD_ID = "air-mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		


		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		FabricDefaultAttributeRegistry.register(ModEntities.WATCHER,WatcherEntity.createWatcherAttributes());
		ModEvents.register();
		FabricDefaultAttributeRegistry.register(ModEntities.VISITOR, VisitorEntity.createVisitorAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.STRANGER, StrangerEntity.createStrangerAttributes());
		ServerTickEvents.END_SERVER_TICK.register(new DimensionChecker());


	}


}
