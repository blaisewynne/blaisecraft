package com.blaisecraft.entity.ai;

import com.blaisecraft.entity.custom.AnimatedArmourEntity;
import com.blaisecraft.entity.custom.VampireEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class AnimatedArmourAttackGoal extends MeleeAttackGoal {
    private final AnimatedArmourEntity animatedarmour;
    private int ticks;

    public AnimatedArmourAttackGoal(AnimatedArmourEntity animatedarmour, double speed, boolean pauseWhenMobIdle) {
        super(animatedarmour, speed, pauseWhenMobIdle);
        this.animatedarmour = animatedarmour;
    }

    @Override
    public void start() {
        super.start();
        this.ticks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.animatedarmour.setAttacking(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.ticks++;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.animatedarmour.setAttacking(true);
        } else {
            this.animatedarmour.setAttacking(false);
        }
    }
}