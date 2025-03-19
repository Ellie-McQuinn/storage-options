package com.duck.elliemcquinn.storageoptions.platform

import com.duck.elliemcquinn.storageoptions.block.entity.TallBarrelBlockEntity
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class FabricCommonHelper : CommonHelper {
    override fun getItemAccess(entity: TallBarrelBlockEntity): Any {
        return InventoryStorage.of(entity, null)
    }

    override fun getItemAccess(
        first: TallBarrelBlockEntity,
        second: TallBarrelBlockEntity
    ): Any {
        return CombinedStorage(listOf(first.itemAccess as InventoryStorage, second.itemAccess as InventoryStorage))
    }

    override fun invalidateCapabilities(level: Level, pos: BlockPos) {
        // NO-OP neoforge specific.
    }
}