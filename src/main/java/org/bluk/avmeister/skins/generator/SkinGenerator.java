package org.bluk.avmeister.skins.generator;

import lombok.Getter;
import org.bluk.avmeister.skins.generator.queue.GeneratorQueue;


public class SkinGenerator {
    @Getter
    private static GeneratorQueue queue;

    static {
        try {
            queue = new GeneratorQueue();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
