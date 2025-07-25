package com.senna.air.detecter;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class DimensionChecker implements ServerTickEvents.EndTick {
    private static final int CHECK_INTERVAL = 100; // 每100刻（5秒）检查一次

    @Override
    public void onEndTick(MinecraftServer server) {
        if (server.getTicks() % CHECK_INTERVAL != 0) return;

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            // 获取维度标识符（例如 "minecraft:overworld"）
            Identifier dimensionId = player.getWorld().getRegistryKey().getValue();

            // 获取维度友好名称（例如 "主世界"）
            String dimensionName = player.getWorld().getDimensionKey().getValue().toString();

            // 示例：在控制台输出
            System.out.println(
                    "玩家 " + player.getName().getString() +
                            " 在维度: " + dimensionId +
                            " (" + dimensionName + ")"
            );
        }
    }
}