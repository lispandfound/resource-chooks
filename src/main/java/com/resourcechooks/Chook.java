package com.resourcechooks;

import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class Chook extends ChickenEntity {

    private Item layItem;

    public Chook(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
        this.layItem = Items.AIR;
    }

    @Override
    public EntityData initialize(ServerWorldAccess worldAccess, LocalDifficulty localDifficulty, SpawnReason spawnReason, EntityData entityData) {
        super.initialize(worldAccess, localDifficulty, spawnReason, entityData);

        // We only care about natural spawns/summons
        if (!(SpawnReason.NATURAL.equals(spawnReason) || SpawnReason.MOB_SUMMONED.equals(spawnReason))) {
            return entityData;
        }

        RegistryEntry<Biome> biome = worldAccess.getBiome(this.getBlockPos());
        if (biome.getKey().isEmpty()) {
            return entityData;
        }

        if (BiomeKeys.PLAINS.equals(biome.getKey().get())) {
            this.layItem = Items.FERMENTED_SPIDER_EYE;
        } else {
            this.layItem = Items.DIAMOND;
        }

        return entityData;
    }

    @Override
    public void tickMovement() {
        int save = this.eggLayTime;
        // Override the egg lay time so that the chicken eggs do not lay.
        this.eggLayTime = Integer.MAX_VALUE;
        super.tickMovement();
        // Restore the egg lay time and handle the implementation ourselves.
        this.eggLayTime = save;

        World entityWorld = this.getEntityWorld();
        if (!entityWorld.isClient() && this.isAlive() && !this.isBaby() && --this.eggLayTime <= 0) {
            this.lay(entityWorld);
        }
    }

    public void lay(World world) {
        // According to MC Wiki the pitch is between 0.8-1.2.
        this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, this.random.nextFloat() * 0.4f + 0.8f);
        // TODO: how the heck do I drop an item.
        ItemStack stack = new ItemStack(this.layItem, 1);
        ItemEntity entity = new ItemEntity(world, this.lastX, this.lastY, this.lastZ, stack);
        double offsetX = (this.random.nextDouble() - 0.5) * 0.3;
        double offsetZ = (this.random.nextDouble() - 0.5) * 0.3;
        entity.setVelocity(offsetX, 0.2, offsetZ);
        world.spawnEntity(entity);
        this.eggLayTime = this.random.nextInt(6000) + 6000;
    }

    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        view.putString("layItem", this.layItem.toString());
    }

    @Override
    public void readData(ReadView view) {
        super.readData(view);
        String itemIdStr = view.getString("layItem", "");
        if (!itemIdStr.isEmpty()) {
            Identifier ident = Identifier.of(itemIdStr);
            Item item =  Registries.ITEM.get(ident);
            if (item != Items.AIR) {
                this.layItem = item;
            }
        }

    }

}
