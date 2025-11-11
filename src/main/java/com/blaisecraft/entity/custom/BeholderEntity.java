package com.blaisecraft.entity.custom;

import com.blaisecraft.entity.BlaiseCraftEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.BooleanSupplier;

public class BeholderEntity extends MobEntity implements Monster {
    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(BeholderEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final byte DEFAULT_LASER_STRENGTH = 1;
    private int laserStrength = 1;

    public BeholderEntity(EntityType<? extends BeholderEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
        this.moveControl = new BeholderEntity.BeholderMoveControl(this, false, () -> false);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(5, new BeholderEntity.FlyRandomlyGoal(this));
        this.goalSelector.add(7, new BeholderEntity.LookAtTargetGoal(this));
        this.goalSelector.add(7, new BeholderEntity.ShootFireballGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, (entity, world) -> Math.abs(entity.getY() - this.getY()) <= 4.0));
    }

    public boolean isShooting() {
        return this.dataTracker.get(SHOOTING);
    }

    public void setShooting(boolean shooting) {
        this.dataTracker.set(SHOOTING, shooting);
    }

    public int getFireballStrength() {
        return this.laserStrength;
    }

    /**
     * {@return whether {@code damageSource} is caused by a player's fireball}
     *
     * <p>This returns {@code true} for ghast fireballs reflected by a player,
     * since the attacker is set as the player in that case.
     */
    private static boolean isFireballFromPlayer(DamageSource damageSource) {
        return damageSource.getSource() instanceof FireballEntity && damageSource.getAttacker() instanceof PlayerEntity;
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld world, DamageSource source) {
        return this.isInvulnerable() && !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)
                || !isFireballFromPlayer(source) && super.isInvulnerableTo(world, source);
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    @Override
    public boolean isClimbing() {
        return false;
    }

    @Override
    public void travel(Vec3d movementInput) {
        this.travelFlying(movementInput, 0.02F);
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (isFireballFromPlayer(source)) {
            super.damage(world, source, 1000.0F);
            return true;
        } else {
            return this.isInvulnerableTo(world, source) ? false : super.damage(world, source, amount);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SHOOTING, false);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.FOLLOW_RANGE, 100.0)
                .add(EntityAttributes.CAMERA_DISTANCE, 8.0)
                .add(EntityAttributes.FLYING_SPEED, 0.06);
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_GHAST_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_GHAST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GHAST_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 5.0F;
    }

    @Override
    public int getLimitPerChunk() {
        return 1;
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putByte("ExplosionPower", (byte)this.laserStrength);
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.laserStrength = view.getByte("ExplosionPower", (byte)1);
    }

    @Override
    public boolean hasQuadLeashAttachmentPoints() {
        return true;
    }

    @Override
    public double getElasticLeashDistance() {
        return 10.0;
    }

    @Override
    public double getLeashSnappingDistance() {
        return 16.0;
    }

    public static void updateYaw(BeholderEntity beholder) {
        if (beholder.getTarget() == null) {
            Vec3d vec3d = beholder.getVelocity();
            beholder.setYaw(-((float) MathHelper.atan2(vec3d.x, vec3d.z)) * (180.0F / (float)Math.PI));
            beholder.bodyYaw = beholder.getYaw();
        } else {
            LivingEntity livingEntity = beholder.getTarget();
            double d = 64.0;
            if (livingEntity.squaredDistanceTo(beholder) < 4096.0) {
                double e = livingEntity.getX() - beholder.getX();
                double f = livingEntity.getZ() - beholder.getZ();
                beholder.setYaw(-((float)MathHelper.atan2(e, f)) * (180.0F / (float)Math.PI));
                beholder.bodyYaw = beholder.getYaw();
            }
        }
    }

    public static class FlyRandomlyGoal extends Goal {
        private static final int field_59707 = 64;
        private final MobEntity beholder;
        private final int blockCheckDistance;

        public FlyRandomlyGoal(BeholderEntity beholder) {
            this(beholder, 0);
        }

        public FlyRandomlyGoal(BeholderEntity beholder, int blockCheckDistance) {
            this.beholder = beholder;
            this.blockCheckDistance = blockCheckDistance;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        @Override
        public boolean canStart() {
            MoveControl moveControl = this.beholder.getMoveControl();
            if (!moveControl.isMoving()) {
                return true;
            } else {
                double d = moveControl.getTargetX() - this.beholder.getX();
                double e = moveControl.getTargetY() - this.beholder.getY();
                double f = moveControl.getTargetZ() - this.beholder.getZ();
                double g = d * d + e * e + f * f;
                return g < 1.0 || g > 3600.0;
            }
        }

        @Override
        public boolean shouldContinue() {
            return false;
        }

        @Override
        public void start() {
            Vec3d vec3d = locateTarget((BeholderEntity) this.beholder, this.blockCheckDistance);
            this.beholder.getMoveControl().moveTo(vec3d.getX(), vec3d.getY(), vec3d.getZ(), 1.0);
        }

        public static Vec3d locateTarget(BeholderEntity beholder, int blockCheckDistance) {
            World world = beholder.getEntityWorld();
            Random random = beholder.getRandom();
            Vec3d vec3d = beholder.getEntityPos();
            Vec3d vec3d2 = null;

            for (int i = 0; i < 64; i++) {
                vec3d2 = getTargetPos(beholder, vec3d, random);
                if (vec3d2 != null && isTargetValid(world, vec3d2, blockCheckDistance)) {
                    return vec3d2;
                }
            }

            if (vec3d2 == null) {
                vec3d2 = addRandom(vec3d, random);
            }

            BlockPos blockPos = BlockPos.ofFloored(vec3d2);
            int j = world.getTopY(Heightmap.Type.MOTION_BLOCKING, blockPos.getX(), blockPos.getZ());
            if (j < blockPos.getY() && j > world.getBottomY()) {
                vec3d2 = new Vec3d(vec3d2.getX(), beholder.getY() - Math.abs(beholder.getY() - vec3d2.getY()), vec3d2.getZ());
            }

            return vec3d2;
        }

        private static boolean isTargetValid(World world, Vec3d pos, int blockCheckDistance) {
            if (blockCheckDistance <= 0) {
                return true;
            } else {
                BlockPos blockPos = BlockPos.ofFloored(pos);
                if (!world.getBlockState(blockPos).isAir()) {
                    return false;
                } else {
                    for (Direction direction : Direction.values()) {
                        for (int i = 1; i < blockCheckDistance; i++) {
                            BlockPos blockPos2 = blockPos.offset(direction, i);
                            if (!world.getBlockState(blockPos2).isAir()) {
                                return true;
                            }
                        }
                    }

                    return false;
                }
            }
        }

        private static Vec3d addRandom(Vec3d pos, Random random) {
            double d = pos.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double e = pos.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double f = pos.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            return new Vec3d(d, e, f);
        }

        @Nullable
        private static Vec3d getTargetPos(BeholderEntity beholder, Vec3d pos, Random random) {
            Vec3d vec3d = addRandom(pos, random);
            return beholder.hasPositionTarget() && !beholder.isInPositionTargetRange(vec3d) ? null : vec3d;
        }
    }

    public static class BeholderMoveControl extends MoveControl {
        private final BeholderEntity beholder;
        private int collisionCheckCooldown;
        private final boolean happy;
        private final BooleanSupplier shouldStayStill;

        public BeholderMoveControl(BeholderEntity beholder, boolean happy, BooleanSupplier shouldStayStill) {
            super(beholder);
            this.beholder = beholder;
            this.happy = happy;
            this.shouldStayStill = shouldStayStill;
        }

        @Override
        public void tick() {
            if (this.shouldStayStill.getAsBoolean()) {
                this.state = State.WAIT;
                this.beholder.stopMovement();
            }

            if (this.state == State.MOVE_TO) {
                if (this.collisionCheckCooldown-- <= 0) {
                    this.collisionCheckCooldown = this.collisionCheckCooldown + this.beholder.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.targetX - this.beholder.getX(), this.targetY - this.beholder.getY(), this.targetZ - this.beholder.getZ());
                    if (this.willCollide(vec3d)) {
                        this.beholder.setVelocity(this.beholder.getVelocity().add(vec3d.normalize().multiply(this.beholder.getAttributeValue(EntityAttributes.FLYING_SPEED) * 5.0 / 3.0)));
                    } else {
                        this.state = State.WAIT;
                    }
                }
            }
        }

        private boolean willCollide(Vec3d movement) {
            Box box = this.beholder.getBoundingBox();
            Box box2 = box.offset(movement);
            if (this.happy) {
                for (BlockPos blockPos : BlockPos.iterate(box2.expand(1.0))) {
                    if (!this.canPassThrough(this.beholder.getEntityWorld(), null, null, blockPos, false, false)) {
                        return false;
                    }
                }
            }

            boolean bl = this.beholder.isTouchingWater();
            boolean bl2 = this.beholder.isInLava();
            Vec3d vec3d = this.beholder.getEntityPos();
            Vec3d vec3d2 = vec3d.add(movement);
            return BlockView.collectCollisionsBetween(
                    vec3d, vec3d2, box2, (pos, version) -> box.contains(pos) ? true : this.canPassThrough(this.beholder.getEntityWorld(), vec3d, vec3d2, pos, bl, bl2)
            );
        }

        private boolean canPassThrough(BlockView world, @Nullable Vec3d oldPos, @Nullable Vec3d newPos, BlockPos blockPos, boolean waterAllowed, boolean lavaAllowed) {
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isAir()) {
                return true;
            } else {
                boolean bl = oldPos != null && newPos != null;
                boolean bl2 = bl
                        ? !this.beholder.collides(oldPos, newPos, blockState.getCollisionShape(world, blockPos).offset(new Vec3d(blockPos)).getBoundingBoxes())
                        : blockState.getCollisionShape(world, blockPos).isEmpty();
                if (!this.happy) {
                    return bl2;
                } else if (blockState.isIn(BlockTags.HAPPY_GHAST_AVOIDS)) {
                    return false;
                } else {
                    FluidState fluidState = world.getFluidState(blockPos);
                    if (!fluidState.isEmpty() && (!bl || this.beholder.collidesWithFluid(fluidState, blockPos, oldPos, newPos))) {
                        if (fluidState.isIn(FluidTags.WATER)) {
                            return waterAllowed;
                        }

                        if (fluidState.isIn(FluidTags.LAVA)) {
                            return lavaAllowed;
                        }
                    }

                    return bl2;
                }
            }
        }
    }

    public static class LookAtTargetGoal extends Goal {
        private final BeholderEntity beholder;

        public LookAtTargetGoal(BeholderEntity beholder) {
            this.beholder = beholder;
            this.setControls(EnumSet.of(Control.LOOK));
        }

        @Override
        public boolean canStart() {
            return true;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            BeholderEntity.updateYaw(this.beholder);
        }
    }

    static class ShootFireballGoal extends Goal {
        private final BeholderEntity beholder;
        public int cooldown;

        public ShootFireballGoal(BeholderEntity beholder) {
            this.beholder = beholder;
        }

        @Override
        public boolean canStart() {
            return this.beholder.getTarget() != null;
        }

        @Override
        public void start() {
            this.cooldown = 0;
        }

        @Override
        public void stop() {
            this.beholder.setShooting(false);
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.beholder.getTarget();
            if (livingEntity != null) {
                double d = 64.0;
                if (livingEntity.squaredDistanceTo(this.beholder) < 4096.0 && this.beholder.canSee(livingEntity)) {
                    World world = this.beholder.getEntityWorld();
                    this.cooldown++;
                    if (this.cooldown == 10 && !this.beholder.isSilent()) {
                        world.syncWorldEvent(null, WorldEvents.GHAST_WARNS, this.beholder.getBlockPos(), 0);
                    }

                    if (this.cooldown == 20) {
                        double e = 4.0;
                        Vec3d vec3d = this.beholder.getRotationVec(1.0F);
                        double f = livingEntity.getX() - (this.beholder.getX() + vec3d.x * 4.0);
                        double g = livingEntity.getBodyY(0.5) - (0.5 + this.beholder.getBodyY(0.5));
                        double h = livingEntity.getZ() - (this.beholder.getZ() + vec3d.z * 4.0);
                        Vec3d vec3d2 = new Vec3d(f, g, h);
                        if (!this.beholder.isSilent()) {
                            world.syncWorldEvent(null, WorldEvents.GHAST_SHOOTS, this.beholder.getBlockPos(), 0);
                        }

                        FireballEntity fireballEntity = new FireballEntity(world, this.beholder, vec3d2.normalize(), this.beholder.getFireballStrength());
                        fireballEntity.setPosition(this.beholder.getX() + vec3d.x * 4.0, this.beholder.getBodyY(0.5) + 0.5, fireballEntity.getZ() + vec3d.z * 4.0);
                        world.spawnEntity(fireballEntity);
                        this.cooldown = -40;
                    }
                } else if (this.cooldown > 0) {
                    this.cooldown--;
                }

                this.beholder.setShooting(this.cooldown > 10);
            }
        }
    }
}
