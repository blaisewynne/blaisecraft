package com.blaisecraft.effects;

import com.blaisecraft.BlaiseCraft;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class BlaiseCraftEffects implements ModInitializer {
    public static final RegistryEntry<StatusEffect> WEREWOLF =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(BlaiseCraft.MOD_ID, "werewolf"), new WerewolfEffect());

    @Override
    public void onInitialize() {
        // ...
    }
}