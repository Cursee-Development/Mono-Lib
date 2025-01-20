package com.cursee.monolib.core.registry;

import com.cursee.monolib.MonoLib;
import com.cursee.monolib.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.BiConsumer;

public class ModBlocks {

    public static final Block EXAMPLE_BLOCK = new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY));

    public static void register(BiConsumer<Block, ResourceLocation> consumer) {
        if (Services.PLATFORM.isDevelopmentEnvironment()) {
            consumer.accept(EXAMPLE_BLOCK, MonoLib.identifier("example_block"));
        }
    }
}
