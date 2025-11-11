package com.blaisecraft;

import com.blaisecraft.entity.BlaiseCraftEntities;
import com.blaisecraft.entity.client.beholder.BeholderRenderer;
import com.blaisecraft.entity.client.vampire.VampireRenderer;
import com.blaisecraft.entity.client.werewolf.WerewolfRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BlaiseCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(BlaiseCraftEntities.VAMPIRE, VampireRenderer::new);
        EntityRendererRegistry.register(BlaiseCraftEntities.WEREWOLF, WerewolfRenderer::new);
        EntityRendererRegistry.register(BlaiseCraftEntities.BEHOLDER, BeholderRenderer::new);
    }
}