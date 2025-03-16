package com.elliemcquinn.storageoptions.neoforge

import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import com.duck.elliemcquinn.storageoptions.registration.ModBlocks
import com.duck.elliemcquinn.storageoptions.registration.ModItems
import net.minecraft.core.registries.Registries
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.registries.RegisterEvent

@Mod("ellsso")
class Main(modBus: IEventBus, container: ModContainer) {
    init {
        modBus.addListener(RegisterEvent::class.java) { event ->
            when (event.registryKey) {
                Registries.BLOCK -> ModBlocks.init()
                Registries.BLOCK_ENTITY_TYPE -> ModBlockEntities.init()
                Registries.ITEM -> ModItems.init()
            }
        }
    }
}