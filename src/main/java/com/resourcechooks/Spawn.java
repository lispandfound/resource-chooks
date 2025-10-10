package com.resourcechooks;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings.SpawnEntry;

public final class Spawn {
    public static void initialize() {
        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
            SpawnGroup.CREATURE,
            ResourceChooks.CHOOK,
            100, 10, 100
        );
    }
}
