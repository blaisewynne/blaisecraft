package com.blaisecraft;

import com.blaisecraft.entity.BlaiseCraftEntities;
import com.blaisecraft.entity.client.animatedarmour.AnimatedArmourRenderer;
import com.blaisecraft.entity.client.beholder.BeholderRenderer;
import com.blaisecraft.entity.client.vampire.VampireRenderer;
import com.blaisecraft.entity.client.werewolf.WerewolfRenderer;
import net.fabricmc.api.ClientModInitializer;

import static net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.*;

public class BlaiseCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        register(BlaiseCraftEntities.VAMPIRE, VampireRenderer::new);
        register(BlaiseCraftEntities.WEREWOLF, WerewolfRenderer::new);
        register(BlaiseCraftEntities.BEHOLDER, BeholderRenderer::new);
        register(BlaiseCraftEntities.ANIMATED_ARMOUR, AnimatedArmourRenderer::new);
    }
}