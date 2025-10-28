package com.blaisecraft.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;

@Environment(EnvType.CLIENT)
public class VampireModel<S extends VampireRenderState> extends AbstractVampireModel<S> {
    public VampireModel(ModelPart modelPart) {
        super(modelPart);
    }
}