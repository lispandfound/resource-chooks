package com.resourcechooks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.resourcechooks.ChookEntity;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

@Mixin(ChickenEntity.class)
public class ChookMixin implements ChookEntity {
    private Item layItem;
    private int savedLayTime;

    @Inject(at = @At("HEAD"), method = "tickMovement")
    private void beforeTickMovement(CallbackInfo info) {
        ChickenEntity self = (ChickenEntity)(Object)this;
        this.savedLayTime = self.eggLayTime;
        self.eggLayTime = Integer.MAX_VALUE;
    }

    @Inject(at = @At("TAIL"), method = "tickMovement")
    private void afterTickMovement(CallbackInfo info) {
        ChickenEntity self = (ChickenEntity)(Object)this;

        self.eggLayTime = this.savedLayTime;
        World entityWorld = self.getEntityWorld();

        if (!entityWorld.isClient() && self.isAlive() && !self.isBaby() && --self.eggLayTime <= 0) {
            this.layItem(entityWorld);
        }
    }

    @Inject(at = @At("TAIL"), method = "writeCustomData(Lnet/minecraft/storage/WriteView;)V")
    private void saveLayItem(WriteView view, CallbackInfo info) {
        if (this.layItem == null) {
            return;
        }
        view.putString("LayItem", this.layItem.toString());
    }

    @Inject(at = @At("TAIL"), method = "readCustomData(Lnet/minecraft/storage/ReadView;)V")
    private void readLayItem(ReadView view, CallbackInfo info) {
        String itemIdStr = view.getString("LayItem", "");
        if (!itemIdStr.isEmpty()) {
            Identifier ident = Identifier.of(itemIdStr);
            Item item = Registries.ITEM.get(ident);

            if (item != Items.AIR) {
                this.layItem = item;
            }
        }
    }

    @Override
    public int getRadioactivity() {
        if (this.layItem == null) {
            return 0;
        }
        
        return 1;
    }

    @Override
    public Item getLayItem() {
        return this.layItem;
    }

    @Override
    public void setLayItem(Item item) {
        this.layItem = item;
    }

    @Override
    public void layItem(World world) {
        Item item = this.layItem != null ? this.layItem : Items.EGG;
        ChickenEntity self = (ChickenEntity)(Object)this;

        self.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0f, self.getRandom().nextFloat() * 0.4f + 0.8f);

        ItemStack stack = new ItemStack(item, 1);
        ItemEntity entity = new ItemEntity(world, self.lastX, self.lastY, self.lastZ, stack);
        double offsetX = (self.getRandom().nextDouble() - 0.5) * 0.3;
        double offsetZ = (self.getRandom().nextDouble() - 0.5) * 0.3;
        entity.setVelocity(offsetX, 0.2, offsetZ);
        world.spawnEntity(entity);

        self.eggLayTime = self.getRandom().nextInt(6000) + 6000;
    }
}
