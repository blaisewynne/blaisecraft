package com.blaisecraft.entity.client.beholder;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;

@Environment(EnvType.CLIENT)
public class BeholderRenderState extends BipedEntityRenderState {
    public boolean attacking;
}