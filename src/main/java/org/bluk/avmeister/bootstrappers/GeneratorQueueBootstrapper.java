package org.bluk.avmeister.bootstrappers;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.skins.generator.SkinGenerator;

public class GeneratorQueueBootstrapper {
    public static void bootstrap() {
        Avmeister.instance.getLogger().info("Starting GeneratorQueue...");

        // @todo handle error normally
        try {
            SkinGenerator.getQueue().start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        try {
            SkinGenerator.getQueue().stop();
        } catch (Throwable ignored) {
        }
    }
}
