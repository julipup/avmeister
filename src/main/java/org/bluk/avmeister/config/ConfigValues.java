package org.bluk.avmeister.config;

import org.bluk.avmeister.config.entries.MinioConfiguration;

public class ConfigValues {
    public static MinioConfiguration storageConfiguration = MinioConfiguration.Parser.parseFromConfig();
}
