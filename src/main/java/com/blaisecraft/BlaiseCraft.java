package com.blaisecraft;

import com.blaisecraft.effects.BlaiseCraftEffects;
import com.blaisecraft.group.BlaiseCraftGroup;
import com.blaisecraft.items.BlaiseCraftItems;
import com.blaisecraft.items.BlaiseCraftPotions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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
    }
}