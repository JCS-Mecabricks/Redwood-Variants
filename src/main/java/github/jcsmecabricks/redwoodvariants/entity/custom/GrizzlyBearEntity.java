package github.jcsmecabricks.redwoodvariants.entity.custom;

import github.jcsmecabricks.redwoodvariants.entity.ModEntities;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.polarbear.PolarBear;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GrizzlyBearEntity extends TamableAnimal implements NeutralMob {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(GrizzlyBearEntity.class, EntityDataSerializers.BOOLEAN);
    private int warningSoundCooldown;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public static final EntityDataAccessor<Long> LAST_POSE_TICK =
            SynchedEntityData.defineId(GrizzlyBearEntity.class, EntityDataSerializers.LONG);

    public final AnimationState sittingTransitionAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState standingTransitionAnimationState = new AnimationState();

    public GrizzlyBearEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.SALMON);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return ModEntities.GRIZZLY_BEAR.create(world, EntitySpawnReason.BREEDING);
    }
    public static AttributeSupplier.Builder createGrizzlyBearAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 35.0)
                .add(Attributes.FOLLOW_RANGE, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 7.0);
    }

    @Override
    public void setAggressive(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAggressive() {
      return this.entityData.get(ATTACKING);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(1, new GrizzlyBearEntity.AttackGoal());
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.SALMON), false));
        this.goalSelector
                .addGoal(1, new PanicGoal(this, 2.0, grizzlyBear -> grizzlyBear.isBaby() ? DamageTypeTags.PANIC_CAUSES : DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new GrizzlyBearEntity.GrizzlyBearRevengeGoal());
        this.targetSelector.addGoal(2, new GrizzlyBearEntity.ProtectBabiesGoal());
    }

    @Override
    public long getPersistentAngerEndTime() {
        return 0;
    }

    @Override
    public void setPersistentAngerEndTime(long angerEndTime) {

    }

    @Override
    public @org.jspecify.annotations.Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@org.jspecify.annotations.Nullable EntityReference<LivingEntity> angryAt) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

    class ProtectBabiesGoal extends NearestAttackableTargetGoal<Player> {
        public ProtectBabiesGoal() {
            super(GrizzlyBearEntity.this, Player.class, 20, true, true, null);
        }
        @Override
        public boolean canUse() {
            if (GrizzlyBearEntity.this.isBaby()) {
                return false;
            } else {
                if (super.canUse()) {
                    for (GrizzlyBearEntity grizzlyBearEntity : GrizzlyBearEntity.this.level()
                            .getEntitiesOfClass(GrizzlyBearEntity.class, GrizzlyBearEntity.this.getBoundingBox().inflate(8.0, 4.0, 8.0))) {
                        if (grizzlyBearEntity.isBaby()) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }


    class GrizzlyBearRevengeGoal extends HurtByTargetGoal {
        public GrizzlyBearRevengeGoal() {
            super(GrizzlyBearEntity.this);
        }
            @Override
            public void start() {
                super.start();
                if (GrizzlyBearEntity.this.isBaby()) {
                    this.alertOthers();
                    this.stop();
                }
            }

            @Override
            protected void alertOther(Mob mob, LivingEntity target) {
                if (mob instanceof PolarBear && !mob.isBaby()) {
                    super.alertOther(mob, target);
                }
            }
        }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAggressive() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 40;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAggressive()) {
            attackAnimationState.stop();
        }

        if (this.shouldUpdateSittingAnimations()) {
            this.standingTransitionAnimationState.stop();
            if (this.shouldPlaySittingTransitionAnimation()) {
                this.sittingTransitionAnimationState.startIfStopped(this.tickCount);
                this.sittingAnimationState.stop();
            } else {
                this.sittingTransitionAnimationState.stop();
                this.sittingAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            this.sittingTransitionAnimationState.stop();
            this.sittingAnimationState.stop();
            this.standingTransitionAnimationState.animateWhen(this.isChangingPose() && this.getLastPoseTickDelta() >= 0L, this.tickCount);
        }
    }

    private boolean shouldPlaySittingTransitionAnimation() {
        return this.isOrderedToSit() && this.getLastPoseTickDelta() < 40L && this.getLastPoseTickDelta() >= 0L;
    }

    public boolean shouldUpdateSittingAnimations() {
        return this.getLastPoseTickDelta() < 0L != this.isOrderedToSit();
    }

    public long getLastPoseTickDelta() {
        return this.level().getGameTime() - Math.abs(this.entityData.get(LAST_POSE_TICK));
    }

    public boolean isChangingPose() {
        long l = this.getLastPoseTickDelta();
        return l < (long)(this.isOrderedToSit() ? 40 : 52);
    }

    public void setLastPoseTick(long lastPoseTick) {
        this.entityData.set(LAST_POSE_TICK, lastPoseTick);
    }

    private void initLastPoseTick(long time) {
        this.setLastPoseTick(Math.max(0L, time - 52L - 1L));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LAST_POSE_TICK, 0L);
        builder.define(ATTACKING, false);
    }

    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
        if (this.isInvulnerableTo(world, source)) {
            return false;
        } else {
            this.setOrderedToSit(false);
            return super.hurtServer(world, source, amount);
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
        this.initLastPoseTick(world.getLevel().getGameTime());
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    public void toggleSitting() {
        if (this.isOrderedToSit()) {
            startStanding();
        } else {
            startSitting();
        }
    }

    public void startSitting() {
        if (this.isOrderedToSit()) {
            return;
        }
        this.makeSound(SoundEvents.CAMEL_SIT);
        this.setPose(Pose.SITTING);
        this.gameEvent(GameEvent.ENTITY_ACTION);
        this.setLastPoseTick(-this.level().getGameTime());

        setInSittingPose(true);
        setOrderedToSit(true);
    }

    public void startStanding() {
        if (!this.isOrderedToSit()) {
            return;
        }
        this.makeSound(SoundEvents.CAMEL_STAND);
        this.setPose(Pose.STANDING);
        this.gameEvent(GameEvent.ENTITY_ACTION);
        this.setLastPoseTick(this.level().getGameTime());

        setInSittingPose(false);
        setOrderedToSit(false);
    }

    public boolean isOrderedToSit() {
        return this.entityData.get(LAST_POSE_TICK) < 0L;
    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        if (this.isTame()) {
            if (this.isFood(itemStack) && this.getHealth() < this.getMaxHealth()) {
                this.usePlayerItem(player, hand, itemStack);
                FoodProperties foodComponent = itemStack.get(DataComponents.FOOD);
                float f = foodComponent != null ? foodComponent.nutrition() : 1.0F;
                this.heal(2.0F * f);
                return InteractionResult.SUCCESS;
            }


                {

                    InteractionResult actionResult = super.mobInteract(player, hand);
                    if (!actionResult.consumesAction() && this.isOwnedBy(player)) {
                        this.toggleSitting();
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        return InteractionResult.SUCCESS.withoutItem();
                    }

                    return actionResult;
            }
        } else if (!this.level().isClientSide() && itemStack.is(Items.HONEY_BOTTLE) && !this.isAngry()) {
            itemStack.consume(1, player);
            this.tryTame(player);
            return InteractionResult.SUCCESS_SERVER;
        }

        return super.mobInteract(player, hand);
    }


    private void tryTame(Player player) {
        if (this.random.nextInt(3) == 0) {
            this.tame(player);
            this.navigation.stop();
            this.setTarget(null);
            this.setOrderedToSit(true);
            this.level().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
        } else {
            this.level().broadcastEntityEvent(this, EntityEvent.TAMING_FAILED);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isBaby() ? SoundEvents.POLAR_BEAR_AMBIENT_BABY : SoundEvents.POLAR_BEAR_AMBIENT;
    }



    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.POLAR_BEAR_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.POLAR_BEAR_DEATH;
    }

    public void setWarning(boolean warning) {
        this.entityData.set(ATTACKING, warning);
    }

    protected void playWarningSound() {
        if (this.warningSoundCooldown <= 0) {
            this.makeSound(SoundEvents.POLAR_BEAR_WARNING);
            this.warningSoundCooldown = 40;
        }
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal() {
            super(GrizzlyBearEntity.this, 1.3, true);
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity target) {
            if (this.canPerformAttack(target)) {
                this.resetAttackCooldown();
                this.mob.doHurtTarget(getServerLevel(this.mob), target);
                GrizzlyBearEntity.this.setWarning(false);
            } else if (this.mob.distanceToSqr(target) < (target.getBbWidth() + 3.0F) * (target.getBbWidth() + 3.0F)) {
                if (this.isTimeToAttack()) {
                    GrizzlyBearEntity.this.setWarning(false);
                    this.resetAttackCooldown();
                }

                if (this.getTicksUntilNextAttack() <= 10) {
                    GrizzlyBearEntity.this.setWarning(true);
                    GrizzlyBearEntity.this.playWarningSound();
                }
            } else {
                this.resetAttackCooldown();
                GrizzlyBearEntity.this.setWarning(false);
            }
        }
    }
}
