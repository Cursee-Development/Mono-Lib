package com.cursee.monolib;

import com.cursee.monolib.core.sailing.warden.SailingWarden;
import com.cursee.monolib.platform.Services;
import net.minecraft.resources.ResourceLocation;

import java.io.File;

public class MonoLib {

    public static void init() {
        SailingWarden.processDirectoryOrFilePathStrings(Services.PLATFORM.getGameDirectory() + File.separator + "mods");
        if (!SailingWarden.UNSAFE_PATH_TO_UNSAFE_HOST_MAP.isEmpty()) {
            Constants.LOG.info("Unsafe download(s):");
            for (String key : SailingWarden.UNSAFE_PATH_TO_UNSAFE_HOST_MAP.keySet()) {
                Constants.LOG.info("- {} from {}", key, SailingWarden.UNSAFE_PATH_TO_UNSAFE_HOST_MAP.get(key));
            }
        }
    }

    public static ResourceLocation identifier(String value) {
        return new ResourceLocation(Constants.MOD_ID, value);
    }
}