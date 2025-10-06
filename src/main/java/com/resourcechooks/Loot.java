package com.resourcechooks;

import java.util.Set;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;

public final class Loot {
    private static final Set<RegistryKey<LootTable>> LOOT_TABLES = Set.of(
        LootTables.SIMPLE_DUNGEON_CHEST,
        LootTables.ABANDONED_MINESHAFT_CHEST
    );

    public static void initialize() {
        LootTableEvents.MODIFY.register((registryKey, lootTableBuilder, lootTableSource, wrapperLookup) -> {
            if (LOOT_TABLES.contains(registryKey)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .with(ItemEntry.builder(Items.LAXATIVE_ITEM).weight(100));

                lootTableBuilder.pool(poolBuilder);
            }
        });
    }
}
