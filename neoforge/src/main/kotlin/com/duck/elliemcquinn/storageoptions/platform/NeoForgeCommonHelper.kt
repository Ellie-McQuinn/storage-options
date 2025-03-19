package com.duck.elliemcquinn.storageoptions.platform

import com.duck.elliemcquinn.storageoptions.block.entity.TallBarrelBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper
import net.neoforged.neoforge.items.wrapper.InvWrapper

class NeoForgeCommonHelper : CommonHelper {
    override fun getItemAccess(entity: TallBarrelBlockEntity): Any {
        return InvWrapper(entity)
    }

    override fun getItemAccess(
        first: TallBarrelBlockEntity,
        second: TallBarrelBlockEntity
    ): Any {
        return CombinedInvWrapper(first.itemAccess as InvWrapper, second.itemAccess as InvWrapper)
    }

    override fun invalidateCapabilities(level: Level, pos: BlockPos) {
        level.invalidateCapabilities(pos)
    }
}