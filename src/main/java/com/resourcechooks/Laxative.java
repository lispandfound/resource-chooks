package com.resourcechooks;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class Laxative extends Item {
    public Laxative(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);

            if (!(entity instanceof ChickenEntity) || !stack.isOf(Items.LAXATIVE_ITEM)) {
                return ActionResult.PASS;
            }

            ChookEntity chicken = (ChookEntity)entity;
            chicken.layItem(player.getEntityWorld());
            stack.decrement(1);

            return ActionResult.SUCCESS;
        });
    }
}
