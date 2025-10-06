package com.resourcechooks;

import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class Chook extends ChickenEntity {

    private Item layItem;

    public Chook(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
        this.layItem = Items.DIAMOND;
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
