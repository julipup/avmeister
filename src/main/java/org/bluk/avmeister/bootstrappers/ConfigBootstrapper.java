package org.bluk.avmeister.bootstrappers;

import org.bluk.avmeister.Avmeister;

public class ConfigBootstrapper {
    public static void bootstrap() {
        // Saving default config (if not exists)
        Avmeister.instance.saveDefaultConfig();

        // @todo
        // Loading basic configuration
    }
}
