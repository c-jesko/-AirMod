package com.senna.air;

import com.senna.air.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
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

    }
}
