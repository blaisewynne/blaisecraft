package com.blaisecraft.entity;
import com.blaisecraft.BlaiseCraft;


import com.blaisecraft.entity.custom.VampireEntity;


import com.blaisecraft.items.BlaiseCraftItems;
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
    public static final EntityType<VampireEntity> VAMPIRE = Registry.register(Registries.ENTITY_TYPE, Identifier.of(BlaiseCraft.MOD_ID, "vampire"), EntityType.Builder.create(VampireEntity::new, SpawnGroup.MONSTER).dimensions(1f, 2.5f).build(VAMPIRE_KEY));

    @Override
    public void onInitialize() {
        BlaiseCraft.LOGGER.info("Registering BlaiseCraft Entities...");
    }
}