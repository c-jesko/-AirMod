package com.senna.air.entity.client;


import com.senna.air.entity.ModEntities;
import com.senna.air.entity.custom.WatcherEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import static com.senna.air.entity.ModEntities.WATCHER;

public class WatcherSpawner {
    // 生成参数配置
    private static final int MIN_DISTANCE = 64; // 最小生成距离（距玩家）
    private static final int MAX_DISTANCE = 80; // 最大生成距离
    private static final int SPAWN_INTERVAL = 72000;// 生成间隔


    public static void trySpawnWatcher(ServerWorld world, PlayerEntity player) {
        // 检查是否为主世界
        if (!isOverworld(world)) {
            return; // 非主世界不生成
        }
        //检测是否进入过地狱
        //if () return;

        // 检查是否达到生成间隔
        if (world.getTime() % SPAWN_INTERVAL != 0) return;

        if (!world.getEntitiesByType(WATCHER, Box.from(player.getPos()).expand(500), e -> true).isEmpty()) {
            return; // 已有Watcher存在，不生成新的
        }


        // 检查玩家周围是否已存在Watcher
        if (!world.getEntitiesByClass(
                WatcherEntity.class,
                player.getBoundingBox().expand(MAX_DISTANCE), // 搜索范围
                e -> true // 所有Watcher实体
        ).isEmpty()) {
            return; // 已存在则跳过生成
        }

        // 随机生成方向（水平360度）
        Vec3d spawnDir = Vec3d.fromPolar(0, world.random.nextFloat() * 360).normalize();
        // 随机生成距离（64-80格之间）
        double spawnDist = MIN_DISTANCE + world.random.nextDouble() * (MAX_DISTANCE - MIN_DISTANCE);
        // 计算生成位置
        BlockPos spawnPos = BlockPos.ofFloored(player.getPos().add(spawnDir.multiply(spawnDist)));

        // 获取地表生成位置（考虑地形高度）
        spawnPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, spawnPos);

        if (SpawnHelper.isClearForSpawn(
                world,
                spawnPos,
                world.getBlockState(spawnPos),
                world.getFluidState(spawnPos),
                WATCHER // 实体类型参数
        )) {
            WatcherEntity watcher = WATCHER.create(world);
            // 设置位置和朝向
            if (watcher != null) {
                watcher.refreshPositionAndAngles(
                        spawnPos,
                        world.random.nextFloat() * 360, // 随机水平旋转
                        0 // 垂直旋转
                );
            }
            world.spawnEntity(watcher); // 生成实体

        }
    }

    /**
     * 检查世界是否为主世界
     *
     * @param world 待检查的世界
     * @return 是否为主世界
     */
    private static boolean isOverworld(ServerWorld world) {
        // 获取世界的维度类型
        RegistryEntry<DimensionType> dimensionType = world.getDimensionEntry();

        // 检查是否为OVERWORLD维度
        return dimensionType.matchesKey(DimensionTypes.OVERWORLD);
    }
}

