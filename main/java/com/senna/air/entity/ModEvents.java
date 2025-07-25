package com.senna.air.entity;

import com.senna.air.entity.client.WatcherSpawner;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModEvents {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                WatcherSpawner.trySpawnWatcher(player.getServerWorld(), player);
            }
        });
    }
}