package com.cursee.monolib.mixin;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.ParsedArgument;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(CommandContext.class)
public interface AccessorCommandContext {

    @Accessor("arguments")
    Map<String, ParsedArgument> monolib$getArguments();
}