package com.blaisecraft.effects;

import com.blaisecraft.BlaiseCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;

public class VampireEffect extends StatusEffect {
    private static final Identifier SPEED_ID = Identifier.of(BlaiseCraft.MOD_ID, "vampire");
    public VampireEffect() {
        super(StatusEffectCategory.HARMFUL, 0x8B0000);
        addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, SPEED_ID, 0.6, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.JUMP_STRENGTH, SPEED_ID, 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.ATTACK_SPEED, SPEED_ID, 0.9f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.SAFE_FALL_DISTANCE, SPEED_ID, 25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.ARMOR, SPEED_ID, 5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.ATTACK_DAMAGE, SPEED_ID, 2.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {

        if (entity instanceof PlayerEntity && world instanceof ServerWorld serverWorld) {
            if (world.isSkyVisible(BlockPos.ofFloored(entity.getX(), entity.getY(), entity.getZ())) && world.isDay() && !world.isRaining() && !world.isThundering()) {
                entity.damage(serverWorld, world.getDamageSources().onFire(), 1);
                entity.isInvulnerableTo(serverWorld, world.getDamageSources().magic());
            }
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}