package com.blaisecraft.entity.ai;

import com.blaisecraft.entity.custom.WerewolfEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class WerewolfAttackGoal extends MeleeAttackGoal {
    private final WerewolfEntity werewolf;
    private int ticks;

    public WerewolfAttackGoal(WerewolfEntity werewolf, double speed, boolean pauseWhenMobIdle) {
        super(werewolf, speed, pauseWhenMobIdle);
        this.werewolf = werewolf;
    }

    @Override
    public void start() {
        super.start();
        this.ticks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.werewolf.setAttacking(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.ticks++;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.werewolf.setAttacking(true);
        } else {
            this.werewolf.setAttacking(false);
        }
    }
}