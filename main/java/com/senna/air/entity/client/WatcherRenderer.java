package com.senna.air.entity.client;

import com.senna.air.AirMod;
import com.senna.air.entity.custom.VisitorEntity;
import com.senna.air.entity.custom.WatcherEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WatcherRenderer extends MobEntityRenderer<WatcherEntity,WatcherModel<WatcherEntity>> {

    private  static final Identifier TEXTURE = new Identifier(AirMod.MOD_ID,"textures/entities/watcher.png");

    public WatcherRenderer(EntityRendererFactory.Context context) {
        super(context, new WatcherModel<>(context.getPart(ModModelLayer.WATCHER)),0.5f);
    }

    @Override
    public Identifier getTexture(WatcherEntity entity) {
        return TEXTURE;
    }


    @Override
    public void render(WatcherEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        matrixStack.scale(4f,4f,4f);
        super.render(mobEntity,f,g,matrixStack,vertexConsumerProvider,i);
    }
}
