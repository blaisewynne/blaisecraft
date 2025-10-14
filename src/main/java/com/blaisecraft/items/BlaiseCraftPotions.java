package com.blaisecraft.items;

import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.effects.BlaiseCraftEffects;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlaiseCraftPotions implements ModInitializer {
    public static final Potion WEREWOLF_POTION =
            Registry.register(Registries.POTION, Identifier.of(BlaiseCraft.MOD_ID, "werewolf"), new Potion("werewolf", new StatusEffectInstance(BlaiseCraftEffects.WEREWOLF, 1000000, 0)));

    @Override
    public void onInitialize() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.WATER,
                    Items.BONE,
                    Registries.POTION.getEntry(WEREWOLF_POTION)
            );
        });
    }
}
