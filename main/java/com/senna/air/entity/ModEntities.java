package com.senna.air.entity;


import com.senna.air.AirMod;
import com.senna.air.entity.custom.StrangerEntity;
import com.senna.air.entity.custom.VisitorEntity;
import com.senna.air.entity.custom.WatcherEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.registry.Registry.register;

public class ModEntities {
    public static final EntityType<WatcherEntity> WATCHER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(AirMod.MOD_ID,"watcher"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER,WatcherEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<VisitorEntity> VISITOR = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(AirMod.MOD_ID,"visitor"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER,VisitorEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<StrangerEntity> STRANGER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(AirMod.MOD_ID,"stranger"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER,StrangerEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());
}
