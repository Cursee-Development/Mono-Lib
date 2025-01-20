package com.cursee.examplemod;

import com.cursee.examplemod.core.registry.RegistryForge;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ExampleModForge {
    
    public ExampleModForge(FMLJavaModLoadingContext context) {
        ExampleMod.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
        RegistryForge.register(context.getModEventBus());
    }
}