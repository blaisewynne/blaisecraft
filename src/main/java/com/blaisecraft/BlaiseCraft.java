package com.blaisecraft;

import com.blaisecraft.effects.BlaiseCraftEffects;
import com.blaisecraft.entity.BlaiseCraftEntities;
import com.blaisecraft.entity.client.VampireModel;
import com.blaisecraft.entity.client.VampireRenderer;
import com.blaisecraft.entity.custom.VampireEntity;
import com.blaisecraft.group.BlaiseCraftGroup;
import com.blaisecraft.items.BlaiseCraftItems;
import com.blaisecraft.items.BlaiseCraftPotions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlaiseCraft implements ModInitializer {
	public static final String MOD_ID = "blaisecraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        BlaiseCraftGroup.registerBlaiseCraftGroups();
        BlaiseCraftItems.initialize();
        BlaiseCraftEffects.WEREWOLF.getClass();
        BlaiseCraftPotions.WEREWOLF_POTION.getClass();
        BlaiseCraftEffects.VAMPIRE.getClass();
        EntityModelLayerRegistry.registerModelLayer(VampireModel.VAMPIRE, VampireModel::getTexturedModelData);
        EntityRendererRegistry.register(BlaiseCraftEntities.VAMPIRE, VampireRenderer::new);
        FabricDefaultAttributeRegistry.register(BlaiseCraftEntities.VAMPIRE, VampireEntity.createAttributes());
    }
}