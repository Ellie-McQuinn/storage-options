package com.duck.elliemcquinn.storageoptions.block.misc

import com.duck.elliemcquinn.storageoptions.block.entity.TallBarrelBlockEntity
import com.duck.elliemcquinn.storageoptions.platform.CommonHelper
import net.minecraft.world.CompoundContainer

class CachedDoubleBlock(val first: TallBarrelBlockEntity, val second: TallBarrelBlockEntity) {
    val itemAccess by lazy { CommonHelper.INSTANCE.getItemAccess(first, second) }
    val container by lazy { CompoundContainer(first, second) }
}