package com.senna.air.detecter;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetherAdvancementHandler {

    private static final Identifier ENTER_NETHER_ADVANCEMENT =
            new Identifier("minecraft", "story/enter_the_nether");

    // 跟踪玩家维度变化
    private static final Map<UUID, World> playerDimensions = new HashMap<>();

    public static void register() {
        // 使用服务器tick事件检测维度变化
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                World currentDimension = player.getWorld();
                World lastDimension = playerDimensions.get(player.getUuid());

                // 如果维度发生变化
                if (lastDimension != null && lastDimension != currentDimension) {
                    // 如果进入下界
                    if (currentDimension.getRegistryKey() == World.NETHER) {
                        System.out.print("11111");
                    }
                }

                // 更新维度记录
                playerDimensions.put(player.getUuid(), currentDimension);
            }
        });
    }

    // grantNetherAdvancement 方法保持不变...
}