package com.blaisecraft.entity.client.vampire;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;

@Environment(EnvType.CLIENT)
public abstract class AbstractVampireModel<S extends VampireRenderState> extends BipedEntityModel<S> {
    protected AbstractVampireModel(ModelPart modelPart) {
        super(modelPart);
    }

    public void setAngles(S vampireRenderState) {
        super.setAngles(vampireRenderState);
    }
}
