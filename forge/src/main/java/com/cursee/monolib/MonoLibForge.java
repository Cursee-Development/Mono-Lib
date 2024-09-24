package com.cursee.monolib;

import com.cursee.monolib.core.sailing.Sailing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class MonoLibForge {
    
    public MonoLibForge() {
        MonoLib.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::loadComplete);
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        MinecraftForge.EVENT_BUS.register(SailingEvent.class);
    }

    @Mod.EventBusSubscriber
    public static class SailingEvent {
        @SubscribeEvent
        public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
            if (!Sailing.onEntityJoinLevel(event.getLevel(), event.getEntity())) {
                event.setCanceled(true);
            }
        }
    }
}
