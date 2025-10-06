package com.resourcechooks;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
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

            if (!(entity instanceof Chook) || !stack.isOf(Items.LAXATIVE_ITEM)) {
                return ActionResult.PASS;
            }

            Chook chook = (Chook)entity;
            chook.lay(player.getEntityWorld());
            stack.decrement(1);

            return ActionResult.SUCCESS;
        });
    }
}
