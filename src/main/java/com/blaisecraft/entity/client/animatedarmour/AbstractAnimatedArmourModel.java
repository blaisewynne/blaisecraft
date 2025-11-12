package com.blaisecraft.entity.client.animatedarmour;

import com.blaisecraft.entity.client.beholder.BeholderRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;

@Environment(EnvType.CLIENT)
public abstract class AbstractAnimatedArmourModel<S extends AnimatedArmourRenderState> extends BipedEntityModel<S> {
    protected AbstractAnimatedArmourModel(ModelPart modelPart) {
        super(modelPart);
    }

    public void setAngles(S vampireRenderState) {
        super.setAngles(vampireRenderState);
    }
}
