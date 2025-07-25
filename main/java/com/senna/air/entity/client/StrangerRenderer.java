package com.senna.air.entity.client;

import com.senna.air.AirMod;
import com.senna.air.entity.custom.StrangerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class StrangerRenderer extends MobEntityRenderer<StrangerEntity,StrangerModel<StrangerEntity>> {
    private static final Identifier TEXTURE = new Identifier(AirMod.MOD_ID,"texture/entity/stranger.png");
    public StrangerRenderer(EntityRendererFactory.Context context) {
        super(context, new StrangerModel<>(context.getPart(ModModelLayer.STRANGER)),0.5f);
    }

    @Override
    public Identifier getTexture(StrangerEntity entity) {
        return TEXTURE;
    }



    @Override
    public void render(StrangerEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        matrixStack.scale(2f,2f,2f);
        super.render(mobEntity,f,g,matrixStack,vertexConsumerProvider,i);
    }

}
