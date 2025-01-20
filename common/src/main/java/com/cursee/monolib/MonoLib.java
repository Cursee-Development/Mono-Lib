package com.cursee.monolib;

import net.minecraft.resources.ResourceLocation;

public class MonoLib {

    public static void init() {}

    public static ResourceLocation identifier(String value) {
        return new ResourceLocation(Constants.MOD_ID, value);
    }
}