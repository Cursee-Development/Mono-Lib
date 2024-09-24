package com.cursee.monolib;

import com.cursee.monolib.core.sailing.Sailing;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

public class MonoLibFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        MonoLib.init();

        ServerEntityEvents.ENTITY_LOAD.register((entity, level) -> {
            Sailing.onEntityJoinLevel(level, entity);
        });
    }
}
