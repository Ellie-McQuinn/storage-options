package com.duck.elliemcquinn.storageoptions.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item

object ModItems {
    val BARREL: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        "ellsso:barrel",
        BlockItem(ModBlocks.BARREL, Item.Properties())
    )

    fun init() {
        // NO-OP for now
    }
}