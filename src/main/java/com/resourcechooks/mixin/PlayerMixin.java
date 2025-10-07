package com.resourcechooks.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.resourcechooks.Chook;
import com.resourcechooks.ChookEntity;
import com.resourcechooks.ResourceChooks;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public class PlayerMixin {
    @Inject(at = @At("TAIL"), method = "tickMovement")
    private void countChooks(CallbackInfo info) {
        PlayerEntity playerEntity = (PlayerEntity)(Object)this;
        Vec3d playerPosition = playerEntity.getEntityPos();
        World world = playerEntity.getEntityWorld();

        List<ChickenEntity> chickens = world.getEntitiesByClass(
            ChickenEntity.class,
            new Box(
                playerPosition.subtract(new Vec3d(10, 10, 10)),
                playerPosition.add(new Vec3d(10, 10, 10))
            ),
            (entity) -> {
                return true;
            }
        );

        int radioactivity = chickens.stream().reduce(
            0,
            (total, entity) -> {
                return total + ((ChookEntity)entity).getRadioactivity();
            },
            Integer::sum
        );

        if (chickens.size() == 0) {
            return;
        }

        if (radioactivity > 5) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100));
        }

        if (radioactivity > 10) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100));
        }

        if (radioactivity > 20) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 300));
        }

        if (radioactivity > 50) {
            playerEntity.setFireTicks(500);
        }
    }
}
