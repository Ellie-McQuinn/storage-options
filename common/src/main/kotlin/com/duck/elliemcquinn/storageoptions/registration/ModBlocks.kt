package com.duck.elliemcquinn.storageoptions.registration

import com.duck.elliemcquinn.storageoptions.block.ClassicChestBlock
import com.duck.elliemcquinn.storageoptions.block.TallBarrelBlock
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour

object ModBlocks {
    val BARREL: TallBarrelBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        "ellsso:barrel",
        TallBarrelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL))
    )

    val CLASSIC_CHEST: ClassicChestBlock = Registry.register(
        BuiltInRegistries.BLOCK,
        "ellsso:classic_chest",
        ClassicChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS))
    )

    fun init() {
        // NO-OP for now.
    }
}