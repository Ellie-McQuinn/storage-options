package com.duck.elliemcquinn.storageoptions.registration

import com.duck.elliemcquinn.storageoptions.block.entity.TallBarrelBlockEntity
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.entity.BlockEntityType

object ModBlockEntities {
    val BARREL: BlockEntityType<TallBarrelBlockEntity> = Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        "ellsso:barrel",
        BlockEntityType.Builder.of(::TallBarrelBlockEntity, ModBlocks.BARREL).build(null)
    )

    fun init() {
        // NO-OP for now
    }
}