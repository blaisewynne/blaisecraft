package com.blaisecraft.entity.ai;

import com.blaisecraft.entity.custom.VampireEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class VampireAttackGoal extends MeleeAttackGoal {
    private final VampireEntity vampire;
    private int ticks;

    public VampireAttackGoal(VampireEntity vampire, double speed, boolean pauseWhenMobIdle) {
        super(vampire, speed, pauseWhenMobIdle);
        this.vampire = vampire;
    }

    @Override
    public void start() {
        super.start();
        this.ticks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.vampire.setAttacking(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.ticks++;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.vampire.setAttacking(true);
        } else {
            this.vampire.setAttacking(false);
        }
    }
}