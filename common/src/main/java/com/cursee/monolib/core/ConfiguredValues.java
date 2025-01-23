package com.cursee.monolib.core;

import com.cursee.monolib.core.config.SimpleConfigEntry;

public class ConfiguredValues {

    public static final SimpleConfigEntry<Boolean> ENABLE_DEBUGGING = new SimpleConfigEntry<Boolean>("enable_debugging", false);
    public static final SimpleConfigEntry<Boolean> ENABLE_JAR_VERIFICATION = new SimpleConfigEntry<Boolean>("enable_jar_verification", true);
}
