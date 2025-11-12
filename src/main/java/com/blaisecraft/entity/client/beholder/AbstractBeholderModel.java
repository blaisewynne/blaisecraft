package com.blaisecraft.entity.client.beholder;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;

@Environment(EnvType.CLIENT)
public abstract class AbstractBeholderModel<S extends BeholderRenderState> extends BipedEntityModel<S> {
    protected AbstractBeholderModel(ModelPart modelPart) {
        super(modelPart);
    }

    public void setAngles(S beholderRenderState) {
        super.setAngles(beholderRenderState);
    }
}
