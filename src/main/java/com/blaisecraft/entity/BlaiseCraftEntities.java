package com.blaisecraft.entity;
import com.blaisecraft.BlaiseCraft;


import com.blaisecraft.entity.custom.AnimatedArmourEntity;
import com.blaisecraft.entity.custom.BeholderEntity;
import com.blaisecraft.entity.custom.VampireEntity;


import com.blaisecraft.entity.custom.WerewolfEntity;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;


import net.minecraft.entity.SpawnGroup;


import net.minecraft.registry.Registries;


import net.minecraft.registry.Registry;


import net.minecraft.registry.RegistryKey;


import net.minecraft.registry.RegistryKeys;


import net.minecraft.util.Identifier;

public class BlaiseCraftEntities implements ModInitializer  {
    private static final RegistryKey<EntityType<?>> VAMPIRE_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "vampire"));
    public static final EntityType<VampireEntity> VAMPIRE = Registry.register(Registries.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "vampire"), EntityType.Builder.create(VampireEntity::new, SpawnGroup.MONSTER).dimensions(0.6f, 2f).build(VAMPIRE_KEY));

    private static final RegistryKey<EntityType<?>> WEREWOLF_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "werewolf"));
    public static final EntityType<WerewolfEntity> WEREWOLF = Registry.register(Registries.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "werewolf"), EntityType.Builder.create(WerewolfEntity::new, SpawnGroup.MONSTER).dimensions(0.6f, 2f).build(WEREWOLF_KEY));

    private static final RegistryKey<EntityType<?>> BEHOLDER_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "beholder"));
    public static final EntityType<BeholderEntity> BEHOLDER = Registry.register(Registries.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "beholder"), EntityType.Builder.create(BeholderEntity::new, SpawnGroup.MONSTER).dimensions(0.6f, 2f).build(BEHOLDER_KEY));


    private static final RegistryKey<EntityType<?>> ANIMATED_ARMOUR_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "animated_armour"));
    public static final EntityType<AnimatedArmourEntity> ANIMATED_ARMOUR = Registry.register(Registries.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "animated_armour"), EntityType.Builder.create(AnimatedArmourEntity::new, SpawnGroup.MONSTER).dimensions(1f, 2.5f).build(ANIMATED_ARMOUR_KEY));

    @Override
    public void onInitialize() {
        BlaiseCraft.LOGGER.info("Registering BlaiseCraft Entities...");
    }
}