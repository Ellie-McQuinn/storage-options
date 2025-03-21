package com.duck.elliemcquinn.storageoptions.datagen

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = "ellsso")
object NeoForgeData {
    @JvmStatic
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generateCommonData = System.getProperty("ellsso.datagen.common", "false") == "true"

        if (generateCommonData) {

        } else {

        }
    }
}