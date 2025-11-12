package com.blaisecraft.entity.client.animatedarmour;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;

@Environment(EnvType.CLIENT)
public class AnimatedArmourRenderState extends BipedEntityRenderState {
    public boolean attacking;
}