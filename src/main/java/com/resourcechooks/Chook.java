package com.resourcechooks;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class Chook extends ChickenEntity {

    private Item layItem;

    public Chook(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
        this.layItem = BreedingTable.ITEMS[this.random.nextInt(BreedingTable.ITEMS.length)];
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
        ItemStack stack = new ItemStack(this.layItem, 1);
        ItemEntity entity = new ItemEntity(world, this.getX(), this.getY(), this.getZ(), stack);
        double offsetX = (this.random.nextDouble() - 0.5) * 0.3;
        double offsetZ = (this.random.nextDouble() - 0.5) * 0.3;
        entity.setVelocity(offsetX, 0.2, offsetZ);
        world.spawnEntity(entity);
        this.eggLayTime = this.random.nextInt(6000) + 6000;
    }

    @Override
    public Chook createChild(ServerWorld serverWorld, PassiveEntity other) {
        if (other instanceof Chook) {
            Chook otherChook = (Chook)other;
            Item childItem = BreedingTable.lookup(this.layItem, otherChook.layItem, true);


            Chook chook = ResourceChooks.CHOOK.create(serverWorld, SpawnReason.BREEDING);
            if (chook != null && childItem != null) {
                Item items[] = new Item[] {childItem, otherChook.layItem, this.layItem};
                int itemIndex = this.random.nextInt(items.length - 1);
                chook.layItem = items[itemIndex];
            } else if (chook != null) {
                chook.layItem = Items.EGG;
            }

            return chook;
        }
        return null;
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
