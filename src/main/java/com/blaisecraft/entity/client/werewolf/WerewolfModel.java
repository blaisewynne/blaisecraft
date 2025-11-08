package com.blaisecraft.entity.client.werewolf;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;

@Environment(EnvType.CLIENT)
public class WerewolfModel<S extends WerewolfRenderState> extends AbstractWerewolfModel<S> {
    public WerewolfModel(ModelPart modelPart) {
        super(modelPart);
    }
}