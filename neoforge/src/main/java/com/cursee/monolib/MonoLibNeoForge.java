package com.cursee.monolib;

import com.cursee.monolib.core.command.MonoLibCommands;
import com.cursee.monolib.core.registry.RegistryNeoForge;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class MonoLibNeoForge {

    public static IEventBus EVENT_BUS = null;

    public MonoLibNeoForge(IEventBus modEventBus) {
        MonoLib.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        EVENT_BUS = modEventBus;
        NeoForge.EVENT_BUS.addListener((Consumer<EntityJoinLevelEvent>) event -> Sailing.onEntityJoinLevel(event.getEntity(), event.getLevel()));
        // MonoLibNeoForge.EVENT_BUS.addListener((Consumer<RegisterEvent>) event -> {
        //     if (event.getRegistryKey().equals(Registries.COMMAND_ARGUMENT_TYPE)) event.<ArgumentTypeInfo<?, ?>>register(Registries.COMMAND_ARGUMENT_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "item_output"), () -> ArgumentTypeInfos.registerByClass(HandArgument.class, HandArgument.SERIALIZER));
        // });
        NeoForge.EVENT_BUS.addListener((Consumer<RegisterCommandsEvent>) event -> MonoLibCommands.defineCommands(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection()));
        RegistryNeoForge.register(EVENT_BUS);
    }
}