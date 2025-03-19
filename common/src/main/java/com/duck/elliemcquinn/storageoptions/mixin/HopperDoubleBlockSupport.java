package com.duck.elliemcquinn.storageoptions.mixin;

import com.duck.elliemcquinn.storageoptions.block.entity.TallBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperDoubleBlockSupport {
    @Inject(
            method = "getBlockContainer(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/Container;",
            at = @At(value = "RETURN", ordinal = 1),
            cancellable = true
    )
    private static void ellsso_changeContainer(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Container> cir) {
        var returnValue = cir.getReturnValue();

        if (returnValue instanceof TallBarrelBlockEntity entity) {
            var doubleCache = entity.getDoubleCache();

            if (doubleCache != null) {
                cir.setReturnValue(doubleCache.getContainer());
            }
        }
    }
}
