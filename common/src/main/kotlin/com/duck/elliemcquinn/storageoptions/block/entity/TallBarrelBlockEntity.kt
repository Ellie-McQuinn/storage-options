package com.duck.elliemcquinn.storageoptions.block.entity

import com.duck.elliemcquinn.storageoptions.block.TallBarrelBlock
import com.duck.elliemcquinn.storageoptions.block.misc.BarrelType
import com.duck.elliemcquinn.storageoptions.block.misc.CachedDoubleBlock
import com.duck.elliemcquinn.storageoptions.platform.CommonHelper
import com.duck.elliemcquinn.storageoptions.registration.ModBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.CompoundContainer
import net.minecraft.world.ContainerHelper
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BarrelBlock
import net.minecraft.world.level.block.entity.ContainerOpenersCounter
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties

class TallBarrelBlockEntity(pos: BlockPos, state: BlockState) : RandomizableContainerBlockEntity(ModBlockEntities.BARREL, pos, state) {
    private var doubleCache: CachedDoubleBlock? = null
    val itemAccess: Any by lazy { CommonHelper.INSTANCE.getItemAccess(this) }
    private var items: NonNullList<ItemStack> = NonNullList.withSize<ItemStack>(27, ItemStack.EMPTY)
    private val openersCounter: ContainerOpenersCounter = object : ContainerOpenersCounter() {
        override fun onOpen(level: Level, pos: BlockPos, state: BlockState) {
            if (state.getValue(TallBarrelBlock.BARREL_TYPE) != BarrelType.BOTTOM) {
                playSound(level, state, pos, SoundEvents.BARREL_OPEN)
                updateOpenState(level, state, pos, true)
            }
        }

        override fun onClose(level: Level, pos: BlockPos, state: BlockState) {
            if (state.getValue(TallBarrelBlock.BARREL_TYPE) != BarrelType.BOTTOM) {
                playSound(level, state, pos, SoundEvents.BARREL_CLOSE)
                updateOpenState(level, state, pos, false)
            }
        }

        override fun openerCountChanged(level: Level, pos: BlockPos, state: BlockState, oldCount: Int, newCount: Int) {

        }

        override fun isOwnContainer(player: Player): Boolean {
            val container = (player.containerMenu as? ChestMenu ?: return false).container
            return container === this@TallBarrelBlockEntity || container is CompoundContainer && container.contains(this@TallBarrelBlockEntity)
        }
    }

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)

        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, items, registries)
        }
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)

        items = NonNullList.withSize<ItemStack>(containerSize, ItemStack.EMPTY)

        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items, registries)
        }
    }

    override fun getContainerSize() = 27

    override fun getItems(): NonNullList<ItemStack> = items

    override fun setItems(items: NonNullList<ItemStack>) {
        this.items = items
    }

    override fun getDefaultName(): Component = Component.translatable("container.barrel")

    override fun createMenu(id: Int, playerInventory: Inventory): AbstractContainerMenu {
        return ChestMenu.threeRows(id, playerInventory, this)
    }

    override fun startOpen(player: Player) {
        if (!remove && !player.isSpectator) {
            openersCounter.incrementOpeners(player, level!!, blockPos, blockState)
        }
    }

    override fun stopOpen(player: Player) {
        if (!remove && !player.isSpectator) {
            openersCounter.decrementOpeners(player, level!!, blockPos, blockState)
        }
    }

    fun recheckOpen() {
        if (!remove) {
            openersCounter.recheckOpeners(level!!, blockPos, blockState)
        }
    }

    private fun ensureDoubleCache(): CachedDoubleBlock? {
        if (doubleCache != null) {
            return doubleCache
        }

        val barrelType = blockState.getValue(TallBarrelBlock.BARREL_TYPE)

        if (barrelType != BarrelType.SINGLE) {
            val otherEntity = level!!.getBlockEntity(blockPos.relative(TallBarrelBlock.getConnectedDirection(blockState)!!)) as? TallBarrelBlockEntity

            if (otherEntity != null) {
                val doubleCache = if (barrelType == BarrelType.TOP) {
                    CachedDoubleBlock(this, otherEntity)
                } else {
                    CachedDoubleBlock(otherEntity, this)
                }

                doubleCache.first.doubleCache = doubleCache
                doubleCache.second.doubleCache = doubleCache

                return doubleCache
            }
        }

        check(doubleCache == null) { "Double cache should be null but it isn't." }

        return null
    }

    fun getDoubleCache(): CachedDoubleBlock? = ensureDoubleCache()

    fun getSharedItemAccess(): Any {
        return ensureDoubleCache()?.itemAccess ?: itemAccess
    }

    override fun setBlockState(state: BlockState) {
        val oldState = blockState
        super.setBlockState(state)

        if ((oldState.getValue(BlockStateProperties.FACING) != state.getValue(BlockStateProperties.FACING))
            || (oldState.getValue(TallBarrelBlock.BARREL_TYPE) != state.getValue(TallBarrelBlock.BARREL_TYPE))
        ) {
            level?.let {
                CommonHelper.INSTANCE.invalidateCapabilities(it, blockPos)
            }

            if (state.getValue(TallBarrelBlock.BARREL_TYPE) == BarrelType.SINGLE) {
                doubleCache?.let {
                    it.first.doubleCache = null
                    it.second.doubleCache = null
                }
            }
        }
    }

    companion object {
        fun updateOpenState(level: Level, state: BlockState, pos: BlockPos, open: Boolean) {
            level.setBlock(pos, state.setValue(BlockStateProperties.OPEN, open), 3)
        }

        fun playSound(level: Level, state: BlockState, pos: BlockPos, sound: SoundEvent) {
            val lidPos = pos.center.relative(state.getValue(BarrelBlock.FACING), 0.5)
            val pitch = level.random.nextFloat() * 0.1F + 0.9F

            level.playSound(null, lidPos.x, lidPos.y, lidPos.z, sound, SoundSource.BLOCKS, 0.5F, pitch)
        }
    }
}