package org.bluk.avmeister.bootstrappers;

import org.bluk.avmeister.Avmeister;

public class ConfigBootstrapper {
    public static void bootstrap() {
        // Saving default config (if not exists)
        // @todo check if this config exists or no
        Avmeister.instance.saveDefaultConfig();
    }
}
