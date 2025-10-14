package com.blaisecraft.effects;

import com.blaisecraft.BlaiseCraft;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.datafixer.fix.TextComponentStringyFlagsFix;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.ClientPlayerSession;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.io.ObjectInputFilter;

public class WerewolfEffect extends StatusEffect {
    private static final Identifier SPEED_ID = Identifier.of(BlaiseCraft.MOD_ID, "werewolf");
    public WerewolfEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFFAA00);
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