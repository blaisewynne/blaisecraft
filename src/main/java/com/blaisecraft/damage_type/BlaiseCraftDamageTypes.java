package com.blaisecraft.damage_type;

import com.blaisecraft.BlaiseCraft;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public interface BlaiseCraftDamageTypes {
    RegistryKey<DamageType> VAMPIRE_BURNING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "vampire_burning"));


    static void bootstrap(Registerable<DamageType> damageTypeRegisterable) {
        damageTypeRegisterable.register(VAMPIRE_BURNING, new DamageType("vampire_burning", 0.1F, DamageEffects.BURNING));

    }
}
