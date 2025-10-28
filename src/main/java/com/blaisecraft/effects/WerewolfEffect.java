package com.blaisecraft.effects;


import com.blaisecraft.BlaiseCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;


public class WerewolfEffect extends StatusEffect {
    private static final Identifier SPEED_ID = Identifier.of(BlaiseCraft.MOD_ID, "werewolf");
    public WerewolfEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFFAA00);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {

        if (entity instanceof PlayerEntity && world instanceof ServerWorld serverWorld) {
            if (world.getMoonPhase() == 0 && world.isNight()) {
                addAttributeModifier(EntityAttributes.JUMP_STRENGTH, SPEED_ID, 0.8, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                addAttributeModifier(EntityAttributes.ATTACK_SPEED, SPEED_ID, 3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                addAttributeModifier(EntityAttributes.SAFE_FALL_DISTANCE, SPEED_ID, 12, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                addAttributeModifier(EntityAttributes.BLOCK_BREAK_SPEED, SPEED_ID, 3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            }
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }

}
