package com.blaisecraft.entity.client;

import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.entity.custom.VampireEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VampireRenderer extends MobEntityRenderer<VampireEntity, VampireRenderState, VampireModel> {

    public VampireRenderer(EntityRendererFactory.Context context) {
        super(context, new VampireModel(context.getPart(VampireModel.VAMPIRE)), 0.75f);
    }

    @Override
    public Identifier getTexture(VampireRenderState state) {
        return Identifier.of(BlaiseCraft.MOD_ID, "textures/entity/vampire/vampire.png");
    }

    @Override
    public VampireRenderState createRenderState() {
        return new VampireRenderState();
    }

    @Override
    public void render(VampireRenderState state, MatrixStack matrixStack,
                       OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {

        super.render(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }

    @Override
    public void updateRenderState(VampireEntity livingEntity, VampireRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
    }
}
