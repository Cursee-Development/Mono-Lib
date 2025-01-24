package com.cursee.monolib;

import com.cursee.monolib.core.command.MonoLibCommands;
import com.cursee.monolib.core.registry.RegistryFabric;
import com.cursee.monolib.core.sailing.Sailing;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.resources.ResourceLocation;

public class MonoLibFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        MonoLib.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        ServerEntityEvents.ENTITY_LOAD.register(Sailing::onEntityJoinLevel);
        // ArgumentTypeRegistry.registerArgumentType(new ResourceLocation(Constants.MOD_ID, "item_output"), HandArgument.class, HandArgument.SERIALIZER);
        CommandRegistrationCallback.EVENT.register(MonoLibCommands::defineCommands);
        RegistryFabric.register();
    }
}
