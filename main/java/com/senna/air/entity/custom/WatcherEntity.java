package com.senna.air.entity.custom;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.*;

public class WatcherEntity extends HostileEntity {
    private static final int TELEPORT_COOLDOWN = 100;
    // 控制实体无敌状态的布尔值
    private boolean invincible = true; // 初始无敌

    // 控制全局效果激活状态的布尔值
    private boolean effectActive = false; // 初始未激活

    // 存储之前的游戏难度，用于效果取消时恢复
    private Difficulty previousDifficulty = null;

    // 效果应用距离（100格）
    private static final double EFFECT_DISTANCE = 100.0;

    // 对视系统相关变量
    private PlayerEntity currentTargetPlayer; // 当前对视的玩家
    private int eyeContactTimer = 0; // 对视计时器（1秒=20tick）
    private static final int EYE_CONTACT_DURATION = 40; // 2秒对视时间
    private final Set<UUID> watchedPlayers = new HashSet<>(); // 已经对视过的玩家

    
    // 效果距离的平方（用于高效距离比较）
    private static final double EFFECT_DISTANCE_SQUARED = EFFECT_DISTANCE * EFFECT_DISTANCE;
    private static final int SIGHT_CHECK_INTERVAL = 40;
    private int sightCheckTimer = 0;
    private int checkTimer = 0;

    private int teleportCooldown = 0;

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
        if (!invincible) {
            Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(1.0);
            this.setHealth(1.0f);
            this.experiencePoints = 100;

        }else {
            // 无敌时设置无限生命
            Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(Double.MAX_VALUE);
            this.setHealth((float) Double.MAX_VALUE);
            this.experiencePoints = 0; // 无敌状态下死亡无经验
        }
    }




    public WatcherEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 0;
    }

    public static DefaultAttributeContainer.Builder createWatcherAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, Double.MAX_VALUE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_ARMOR, Double.MAX_VALUE);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (invincible) {
            if (!this.getWorld().isClient()) {
                this.getWorld().sendEntityStatus(this, (byte) 2);
            }
            return false;
        }
        return super.damage(source, amount);
    }

    @Override
    public void kill() {
        if (invincible) return;
        super.kill();
    }

    @Override
    public boolean isDead() {
        if (invincible) return false;
        return super.isDead();
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new AttackGoal(this));
        this.goalSelector.add(0, new LookAtEntityGoal(this, PlayerEntity.class, 100.0F));
    }

    @Override
    public void tick() {
        super.tick();

        if (invincible) {
            this.setHealth(this.getMaxHealth());
        }

        if (!this.getWorld().isClient) {
            sightCheckTimer++;

            if (sightCheckTimer >= SIGHT_CHECK_INTERVAL) {
                sightCheckTimer = 0; // 重置计时器

                for (PlayerEntity player : getWorld().getPlayers()) {
                    // 首先检查玩家是否在100格范围内
                    if (isPlayerWithinDistance(player)) {
                        applySlowness(player);
                        applyMiningFatigue(player);
                    }
                }
            }
            if (!this.getWorld().isClient) {
                checkTimer++;
                if (checkTimer >= 40) { // 40 tick = 2秒
                    checkTimer = 0;
                    checkForStrangerAndVisitor(); // 执行检测逻辑
                }
            }

            // 如果效果激活，持续给予玩家虚弱效果
            if (effectActive && !this.getWorld().isClient()) {
                applyWeaknessEffect(); // 应用虚弱效果
            }
            if (teleportCooldown > 0) {
                teleportCooldown--;
            }

            if (!this.getWorld().isClient && this.age % 200 == 0) {
                checkPlayerDistance();
            }
        }
        // 处理玩家对视逻辑（只在服务器端）
        if (!this.getWorld().isClient()) {
            processEyeContact();
        }
    }

    private void processEyeContact() {
        // 如果没有当前目标玩家，选择一个新的
        if (currentTargetPlayer == null || !currentTargetPlayer.isAlive()) {
            selectNextTargetPlayer();
            return;
        }

        // 检查是否正在与当前目标玩家对视
        if (isMakingEyeContact(currentTargetPlayer)) {
            eyeContactTimer++;


            // 对视达到2秒
            if (eyeContactTimer >= EYE_CONTACT_DURATION) {
                // 标记玩家已完成对视
                watchedPlayers.add(currentTargetPlayer.getUuid());


                // 移动到下一个玩家
                teleportToNextPlayer();
            }
        } else {
            // 如果没有对视，重置计时器
            eyeContactTimer = 0;
        }
    }

    private void selectNextTargetPlayer() {
        // 获取所有在线玩家
        List<? extends PlayerEntity> playerList = this.getWorld().getPlayers();
        List<PlayerEntity> players = new ArrayList<>(playerList);

        // 过滤掉已经对视过的玩家
        List<PlayerEntity> availablePlayers = new ArrayList<>();
        for (PlayerEntity player : players) {
            if (!watchedPlayers.contains(player.getUuid())) {
                availablePlayers.add(player);
            }
        }

        // 如果没有可用的玩家，清除自己
        if (availablePlayers.isEmpty()) {
            this.discard();
            return;
        }

        // 随机选择一个玩家
        currentTargetPlayer = availablePlayers.get(this.random.nextInt(availablePlayers.size()));
        eyeContactTimer = 0;

    }

    private void teleportToNextPlayer() {
        // 保存当前目标玩家
        PlayerEntity previousTarget = currentTargetPlayer;

        // 选择下一个目标
        selectNextTargetPlayer();

        // 如果已经清除（没有更多玩家），则返回
        if (this.isRemoved()) {
            return;
        }

        // 在下一个目标玩家64格内随机位置
        double angle = Math.random() * Math.PI * 2;
        double distance = 32 + (Math.random() * 32); // 32-64格距离
        double dx = Math.cos(angle) * distance;
        double dz = Math.sin(angle) * distance;

        // 计算目标位置
        BlockPos targetPos = new BlockPos(
                (int)(currentTargetPlayer.getX() + dx),
                (int)currentTargetPlayer.getY(),
                (int)(currentTargetPlayer.getZ() + dz)
        );

        // 获取安全的地表高度
        targetPos = this.getWorld().getTopPosition(Heightmap.Type.WORLD_SURFACE, targetPos);

        // 确保位置有效
        if (targetPos != null) {
            // 执行瞬移
            this.teleport(targetPos.getX(), targetPos.getY(), targetPos.getZ());



        }
    }

    private boolean isMakingEyeContact(PlayerEntity player) {
        // 1. 检查距离（最大100格）
        double distance = this.getPos().distanceTo(player.getPos());
        if (distance > 100.0) {
            return false;
        }

        // 2. 检查玩家是否看着Watcher
        Vec3d playerLookVec = player.getRotationVec(1.0F).normalize();
        Vec3d playerToWatcherVec = new Vec3d(
                this.getX() - player.getX(),
                this.getEyeY() - player.getEyeY(),
                this.getZ() - player.getZ()
        ).normalize();

        double dotProduct = playerLookVec.dotProduct(playerToWatcherVec);
        if (dotProduct < 0.95) { // 大约18度内
            return false;
        }

        // 3. 检查Watcher是否看着玩家
        Vec3d watcherLookVec = this.getRotationVec(1.0F).normalize();
        Vec3d watcherToPlayerVec = new Vec3d(
                player.getX() - this.getX(),
                player.getEyeY() - this.getEyeY(),
                player.getZ() - this.getZ()
        ).normalize();

        return watcherLookVec.dotProduct(watcherToPlayerVec) > 0.95;
    }

    private void applyWeaknessEffect() {
        if (this.getWorld().isClient()) return;

        ServerWorld serverWorld = (ServerWorld) this.getWorld();
        MinecraftServer server = serverWorld.getServer();

        // 给所有在线玩家施加虚弱2效果
        server.getPlayerManager().getPlayerList().forEach(player -> {
            // 检查玩家是否已有虚弱效果
            StatusEffectInstance existingEffect = player.getStatusEffect(StatusEffects.WEAKNESS);

            // 如果没有效果或效果即将结束（少于5秒），则重新应用
            if (existingEffect == null || existingEffect.getDuration() < 100) {
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.WEAKNESS, // 虚弱效果
                        200,   // 持续时间：10秒（200 tick）
                        1,     // 等级2（攻击伤害减少4点）
                        true,  // 环境粒子可见
                        true   // 显示在HUD上
                ));
            }
        });
    }

    private void checkForStrangerAndVisitor() {
        Box box = this.getBoundingBox().expand(100);

        // 检查是否有visitor
        boolean hasVisitor = !this.getWorld().getEntitiesByClass(
                VisitorEntity.class, box, e -> true).isEmpty();

        // 检查是否有stranger
        boolean hasStranger = !this.getWorld().getEntitiesByClass(
                StrangerEntity.class, box, e -> true).isEmpty();

        // 判断条件是否满足
        boolean conditionMet = hasVisitor && hasStranger;

        // 如果条件满足且之前未激活
        if (conditionMet && !this.effectActive) {
            activateEffects(); // 激活全局效果
        }
        // 如果条件不再满足且之前激活
        else if (!conditionMet && this.effectActive) {
            deactivateEffects(); // 取消全局效果
        }

        this.setInvincible(!conditionMet || !this.invincible); // 设置为可击败
    }

    private void deactivateEffects() {
        if (this.getWorld().isClient()) return;

        // 确保之前保存了难度设置
        if (previousDifficulty != null) {
            ServerWorld serverWorld = (ServerWorld) this.getWorld();
            MinecraftServer server = serverWorld.getServer();

            // 恢复之前的难度
            server.setDifficulty(previousDifficulty, true);
        }
        this.effectActive = false;
    }

    private void activateEffects() {
        if (this.getWorld().isClient()) return;

        ServerWorld serverWorld = (ServerWorld) this.getWorld();
        MinecraftServer server = serverWorld.getServer();

        // 保存当前难度（第一次激活时）
        if (previousDifficulty == null) {
            previousDifficulty = server.getSaveProperties().getDifficulty();
        }

        // 设置困难模式（true表示立即应用）
        server.setDifficulty(Difficulty.HARD, true);
        this.effectActive = true;
    }

    private boolean isPlayerWithinDistance(PlayerEntity player) {
        // 计算距离平方（避免开方运算，提高性能）
        double distanceSquared = this.squaredDistanceTo(player);
        return distanceSquared <= EFFECT_DISTANCE_SQUARED;
    }




    private void applySlowness(PlayerEntity player) {
        // 创建缓慢II效果（100刻=5秒，等级1=II级）
        player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.SLOWNESS,  // 缓慢效果
                160,                     // 持续时间（5秒）
                0,                       // 效果等级（0=I, 1=II）
                false,                   // 无环境粒子
                true,                    // 显示粒子
                true                     // 在HUD显示图标
        ));
    }

    private void applyMiningFatigue(PlayerEntity player) {
        // 创建缓慢II效果（100刻=5秒，等级1=II级）
        player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.MINING_FATIGUE,  // 挖掘效果
                160,                     // 持续时间（5秒）
                0,                       // 效果等级（0=I, 1=II）
                false,                   // 无环境粒子
                true,                    // 显示粒子
                true                     // 在HUD显示图标
        ));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false; // 防止实体立即消失（保持存在）
    }

    /**
     * 检查玩家距离（太远则移除实体）
     */
    private void checkPlayerDistance() {

        if (teleportCooldown > 0) {
            return;
        }
        // 查找256格内最近的玩家
        PlayerEntity nearestPlayer = this.getWorld().getClosestPlayer(this, 256);

        if (nearestPlayer == null) {
            teleportToNearestPlayer();
        }
        
    }

    private void teleportToNearestPlayer() {
        if (teleportCooldown > 0) {
            return;
        }

        // 获取世界上所有玩家
        PlayerEntity nearestPlayer = this.getWorld().getClosestPlayer(this, -1); // -1表示无距离限制

        // 如果没有玩家，则不进行瞬移
        if (nearestPlayer == null) {
            return;
        }

        // 计算瞬移位置：在玩家64格外的位置
        double angle = Math.random() * Math.PI * 2;
        double distance = 64 + (Math.random() * 32); // 64-96格距离
        double dx = Math.cos(angle) * distance;
        double dz = Math.sin(angle) * distance;

        // 计算目标位置
        BlockPos targetPos = new BlockPos(
                (int)(nearestPlayer.getX() + dx),
                (int)nearestPlayer.getY(),
                (int)(nearestPlayer.getZ() + dz)
        );

        // 获取安全的地表高度
        targetPos = this.getWorld().getTopPosition(Heightmap.Type.WORLD_SURFACE, targetPos);

        // 确保位置有效
        if (targetPos != null) {
            // 执行瞬移
            this.teleport(targetPos.getX(), targetPos.getY(), targetPos.getZ());

            // 播放瞬移音效
            this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);

            // 设置瞬移冷却
            teleportCooldown = TELEPORT_COOLDOWN;
        }
    }

}

