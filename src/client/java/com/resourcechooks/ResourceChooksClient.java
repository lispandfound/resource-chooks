package com.resourcechooks;

import net.fabricmc.api.ClientModInitializer;
// import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
// import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
// import net.minecraft.client.render.entity.ChickenEntityRenderer;
// import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ResourceChooksClient implements ClientModInitializer {
    public static final EntityModelLayer CHOOK_LAYER = new EntityModelLayer(Identifier.of("resource-chooks", "chook"), "main");
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        // EntityRendererRegistry.register(ResourceChooks.CHOOK, ChickenEntityRenderer::new);
        // EntityModelLayerRegistry.registerModelLayer(CHOOK_LAYER, ChickenEntityModel::getTexturedModelData);
	}
}
