package com.blaisecraft.entity.client.werewolf;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;

@Environment(EnvType.CLIENT)
public abstract class AbstractWerewolfModel<S extends WerewolfRenderState> extends BipedEntityModel<S> {
    protected AbstractWerewolfModel(ModelPart modelPart) {
        super(modelPart);
    }

    public void setAngles(S werewolfRenderState) {
        super.setAngles(werewolfRenderState);
    }
}

