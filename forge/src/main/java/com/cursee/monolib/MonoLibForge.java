package com.cursee.monolib;

import com.cursee.monolib.core.registry.RegistryForge;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class MonoLibForge {
    
    public MonoLibForge() {
        MonoLib.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        MinecraftForge.EVENT_BUS.addListener((Consumer<EntityJoinLevelEvent>) event -> Sailing.onEntityJoinLevel(event.getEntity(), event.getLevel()));
        RegistryForge.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
