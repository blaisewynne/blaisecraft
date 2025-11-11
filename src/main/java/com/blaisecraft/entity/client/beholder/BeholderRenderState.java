package com.blaisecraft.entity.client.vampire;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;

@Environment(EnvType.CLIENT)
public class VampireRenderState extends BipedEntityRenderState {
    public boolean attacking;
}