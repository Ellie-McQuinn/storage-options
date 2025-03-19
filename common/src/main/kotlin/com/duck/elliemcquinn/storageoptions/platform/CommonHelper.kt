package com.duck.elliemcquinn.storageoptions.platform

import com.duck.elliemcquinn.storageoptions.block.entity.TallBarrelBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import java.util.ServiceLoader

interface CommonHelper {
    companion object {
        val INSTANCE: CommonHelper = ServiceLoader.load(CommonHelper::class.java).first()
    }

    fun getItemAccess(entity: TallBarrelBlockEntity): Any
    fun getItemAccess(first: TallBarrelBlockEntity, second: TallBarrelBlockEntity): Any

    fun invalidateCapabilities(level: Level, pos: BlockPos)
}