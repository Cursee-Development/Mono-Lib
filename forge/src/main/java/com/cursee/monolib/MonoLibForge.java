package com.cursee.monolib;

import com.cursee.monolib.core.command.MonoLibCommands;
import com.cursee.monolib.core.registry.RegistryForge;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class MonoLibForge {

    public static IEventBus EVENT_BUS = null;
    
    public MonoLibForge(FMLJavaModLoadingContext context) {
        MonoLib.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        EVENT_BUS = context.getModEventBus();
        MinecraftForge.EVENT_BUS.addListener((Consumer<EntityJoinLevelEvent>) event -> Sailing.onEntityJoinLevel(event.getEntity(), event.getLevel()));
        // MonoLibForge.EVENT_BUS.addListener((Consumer<RegisterEvent>) event -> {
        //     if (event.getRegistryKey().equals(Registries.COMMAND_ARGUMENT_TYPE)) event.<ArgumentTypeInfo<?, ?>>register(Registries.COMMAND_ARGUMENT_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "item_output"), () -> ArgumentTypeInfos.registerByClass(HandArgument.class, HandArgument.SERIALIZER));
        // });
        MinecraftForge.EVENT_BUS.addListener((Consumer<RegisterCommandsEvent>) event -> MonoLibCommands.defineCommands(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection()));
        RegistryForge.register(EVENT_BUS);
    }
}
