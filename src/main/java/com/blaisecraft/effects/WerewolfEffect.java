package com.blaisecraft.effects;

import com.blaisecraft.BlaiseCraft;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;


public class WerewolfEffect extends StatusEffect {
    private static final Identifier SPEED_ID = Identifier.of(BlaiseCraft.MOD_ID, "werewolf");
    public WerewolfEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFFAA00);
        addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, SPEED_ID, 0.6, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.JUMP_STRENGTH, SPEED_ID, 0.5f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.ATTACK_SPEED, SPEED_ID, 0.9f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL).
                addAttributeModifier(EntityAttributes.SAFE_FALL_DISTANCE, SPEED_ID, 25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}