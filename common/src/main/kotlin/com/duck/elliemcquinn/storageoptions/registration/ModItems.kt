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

    val CLASSIC_CHEST: BlockItem = Registry.register(
        BuiltInRegistries.ITEM,
        "ellsso:classic_chest",
        BlockItem(ModBlocks.CLASSIC_CHEST, Item.Properties())
    )

    fun init() {
        // NO-OP for now
    }
}