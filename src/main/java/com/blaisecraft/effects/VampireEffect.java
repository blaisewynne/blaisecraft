package com.blaisecraft.effects;

import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.damage_type.BlaiseCraftDamageTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

import static net.minecraft.registry.RegistryKeys.DAMAGE_TYPE;

public class VampireEffect extends StatusEffect {
    private static final Identifier SPEED_ID = Identifier.of(BlaiseCraft.MOD_ID, "vampire");
    public VampireEffect() {
        super(StatusEffectCategory.HARMFUL, 0x8B0000);
        addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, SPEED_ID, 0.6, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.JUMP_STRENGTH, SPEED_ID, 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                addAttributeModifier(EntityAttributes.ATTACK_SPEED, SPEED_ID, 0.9f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                addAttributeModifier(EntityAttributes.SAFE_FALL_DISTANCE, SPEED_ID, 25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                addAttributeModifier(EntityAttributes.ARMOR, SPEED_ID, 5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                addAttributeModifier(EntityAttributes.ATTACK_DAMAGE, SPEED_ID, 2.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public DamageSource vampireBurningDamageSource(ServerWorld world) {
        return new DamageSource(world.getRegistryManager().getEntryOrThrow(BlaiseCraftDamageTypes.VAMPIRE_BURNING));
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        DamageSource damageSource = vampireBurningDamageSource(world);
        if (entity instanceof PlayerEntity && world instanceof ServerWorld serverWorld) {
            if (world.isSkyVisible(BlockPos.ofFloored(entity.getX(), entity.getY(), entity.getZ())) && world.isDay() && !world.isRaining() && !world.isThundering()) {
                entity.damage(serverWorld, world.getDamageSources().onFire(), 1);
            }
            BlockState state =  (entity).getEntityWorld().getBlockState(entity.getBlockPos());
            for (int i = 1; i < 10; i++) {
                if (Objects.equals(state.toString(), String.format("Block{minecraft:water}[level=%d]", i))) {
                    entity.damage(serverWorld, damageSource, 5.0f);
                }
            }
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
