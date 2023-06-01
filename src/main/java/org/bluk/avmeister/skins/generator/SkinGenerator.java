package org.bluk.avmeister.skins.generator;

import lombok.Getter;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.skins.CompleteSkin;
import org.bluk.avmeister.skins.SkinsStorage;
import org.bluk.avmeister.skins.generator.queue.GeneratorQueue;
import org.bluk.avmeister.skins.generator.queue.GeneratorTask;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.ArrayList;
import java.util.List;

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
