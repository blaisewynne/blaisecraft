package com.blaisecraft;

import com.blaisecraft.effects.BlaiseCraftEffects;
import com.blaisecraft.entity.BlaiseCraftEntities;
import com.blaisecraft.entity.custom.VampireEntity;
import com.blaisecraft.group.BlaiseCraftGroup;
import com.blaisecraft.items.BlaiseCraftItems;
import com.blaisecraft.items.BlaiseCraftPotions;
import com.blaisecraft.sounds.BlaiseCraftSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlaiseCraft implements ModInitializer {
	public static final String MOD_ID = "blaisecraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        BlaiseCraftGroup.registerBlaiseCraftGroups();
        BlaiseCraftItems.initialize();
        BlaiseCraftPotions.WEREWOLF_POTION.getClass();
        BlaiseCraftEffects.WEREWOLF.getClass();
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (!itemStack.isOf(BlaiseCraftItems.LABUBU_ITEM)) {
                return;
            }
            list.add(Text.translatable("item.blaisecraft.labubu_item.tooltip").formatted(Formatting.LIGHT_PURPLE, Formatting.ITALIC));
        });
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (!itemStack.isOf(BlaiseCraftItems.BLOOD_ITEM)) {
                return;
            }
            list.add(Text.translatable("item.blaisecraft.blood_item.tooltip").formatted(Formatting.RED, Formatting.BOLD));
        });
        FabricDefaultAttributeRegistry.register(BlaiseCraftEntities.VAMPIRE, VampireEntity.createAttributes());
        BlaiseCraftSounds.initialize();
	}
}
