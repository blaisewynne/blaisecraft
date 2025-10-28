package com.blaisecraft;

import com.blaisecraft.entity.BlaiseCraftEntities;
import com.blaisecraft.entity.client.VampireModel;
import com.blaisecraft.entity.client.VampireRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BlaiseCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(BlaiseCraftEntities.VAMPIRE, VampireRenderer::new);
    }
}