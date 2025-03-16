package com.duck.elliemcquinn.storageoptions.fabric

import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import com.duck.elliemcquinn.storageoptions.registration.ModBlocks
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import net.fabricmc.api.ModInitializer

object Main : ModInitializer {
    override fun onInitialize() {
        ModBlocks.init()
        ModBlockEntities.init()
        ModItems.init()
    }
}