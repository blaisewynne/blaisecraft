package com.blaisecraft.entity.client.beholder;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;

@Environment(EnvType.CLIENT)
public class BeholderModel<S extends BeholderRenderState> extends AbstractBeholderModel<S> {
    public BeholderModel(ModelPart modelPart) {
        super(modelPart);
    }
}