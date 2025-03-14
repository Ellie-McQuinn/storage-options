package com.duck.elliemcquinn.storageoptions.neoforge.registration;

import com.duck.elliemcquinn.storageoptions.neoforge.Main;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    private ModBlocks() {
        // Utility class.
    }

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Main.ID);

    public static final DeferredBlock<Block> TALL_BARREL = BLOCKS.register("barrel", () -> new Block(BlockBehaviour.Properties.of()));
}
