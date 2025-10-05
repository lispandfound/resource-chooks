package com.resourcechooks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceChooks implements ModInitializer {
	public static final String MOD_ID = "resource-chooks";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    public static <T extends Entity>EntityType<T> registerEntity(String namespace, String id, EntityType.Builder<T> type) {
        RegistryKey<EntityType<?>> registryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(namespace, id));
        return Registry.register(Registries.ENTITY_TYPE, registryKey, type.build(registryKey));
    }


    public static final EntityType<Chook> CHOOK = registerEntity("resource-chooks", "chook", EntityType.Builder.create(Chook::new, SpawnGroup.CREATURE).dimensions(0.4f, 0.7f));
    @Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        FabricDefaultAttributeRegistry.register(CHOOK, Chook.createChickenAttributes());
		LOGGER.info("Hello Fabric world!");

                Items.initialize();
	}
}
