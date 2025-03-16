package com.duck.elliemcquinn.storageoptions.block.misc

import net.minecraft.util.StringRepresentable

enum class BarrelType : StringRepresentable{
    SINGLE,
    TOP,
    BOTTOM;

    override fun getSerializedName() = when (this) {
        SINGLE -> "single"
        TOP -> "top"
        BOTTOM -> "bottom"
    }
}