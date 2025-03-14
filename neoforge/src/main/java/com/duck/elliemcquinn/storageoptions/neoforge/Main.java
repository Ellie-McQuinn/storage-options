package com.duck.elliemcquinn.storageoptions.neoforge;

import com.duck.elliemcquinn.storageoptions.neoforge.registration.ModBlocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = Main.ID)
public class Main {
    public static final String ID = "ellsso";

    public Main(IEventBus modBus, ModContainer container) {
        ModBlocks.BLOCKS.register(modBus);
    }
}
