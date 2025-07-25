package com.senna.air;

import com.senna.air.entity.ModEntities;
import com.senna.air.entity.client.*;
import com.senna.air.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class AirModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        ModelPredicateProviderRegistry.register(ModItems.FIRE_BOW,
                new Identifier("pull"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0.0f;
                    return entity.getActiveItem() != stack ? 0.0f
                            : (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
                });

        ModelPredicateProviderRegistry.register(ModItems.FIRE_BOW,
                new Identifier("pulling"), (stack, world, entity, seed) -> {
                    return entity != null && entity.isUsingItem()
                            && entity.getActiveItem() == stack ? 1.0f : 0.0f;
                });

        EntityModelLayerRegistry.registerModelLayer(ModModelLayer.WATCHER, WatcherModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WATCHER, WatcherRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayer.VISITOR, VisitorModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.VISITOR, VisitorRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayer.STRANGER, StrangerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.STRANGER, StrangerRenderer::new);



    }
}
