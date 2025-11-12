package com.blaisecraft.entity.client.animatedarmour;

import com.blaisecraft.entity.client.beholder.AbstractBeholderModel;
import com.blaisecraft.entity.client.beholder.BeholderRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;

@Environment(EnvType.CLIENT)
public class AnimatedArmourModel<S extends AnimatedArmourRenderState> extends AbstractAnimatedArmourModel<S> {
    public AnimatedArmourModel(ModelPart modelPart) {
        super(modelPart);
    }
}