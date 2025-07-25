package com.senna.air.entity.client;

import com.senna.air.AirMod;
import com.senna.air.entity.custom.VisitorEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VisitorRenderer extends MobEntityRenderer<VisitorEntity,VisitorModel<VisitorEntity>> {
    private static final Identifier TEXTURE = new Identifier(AirMod.MOD_ID,"texture/entity/visitor.png");
    public VisitorRenderer(EntityRendererFactory.Context context) {
        super(context, new VisitorModel<>(context.getPart(ModModelLayer.VISITOR)),0.5f);
    }

    @Override
    public Identifier getTexture(VisitorEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(VisitorEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,int i){
        matrixStack.scale(2f,2f,2f);
        super.render(mobEntity,f,g,matrixStack,vertexConsumerProvider,i);
    }
}
