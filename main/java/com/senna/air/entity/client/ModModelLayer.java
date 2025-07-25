package com.senna.air.entity.client;

import com.senna.air.AirMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayer {
    public  static final EntityModelLayer WATCHER =
            new EntityModelLayer(new Identifier(AirMod.MOD_ID,"watcher"),"main");
    public  static final EntityModelLayer VISITOR =
            new EntityModelLayer(new Identifier(AirMod.MOD_ID,"visitor"),"main");
    public  static final EntityModelLayer STRANGER =
            new EntityModelLayer(new Identifier(AirMod.MOD_ID,"stranger"),"main");
}
