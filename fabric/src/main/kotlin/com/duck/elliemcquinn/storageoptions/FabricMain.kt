package com.duck.elliemcquinn.storageoptions

import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import com.duck.elliemcquinn.storageoptions.registration.ModBlocks
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant
import net.fabricmc.fabric.api.transfer.v1.storage.Storage

object FabricMain : ModInitializer {
    override fun onInitialize() {
        ModBlocks.init()
        ModBlockEntities.init()
        ModItems.init()

        ItemStorage.SIDED.registerForBlockEntity({ entity, direction ->
            entity.getSharedItemAccess() as Storage<ItemVariant>
        }, ModBlockEntities.BARREL)
    }
}