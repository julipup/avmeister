package org.bluk.avmeister.skins.generator.queue;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.abstracts.queue.AbstractQueueTask;
import org.bluk.avmeister.skins.CompleteSkin;

public class GeneratorTask extends AbstractQueueTask {
    private CompleteSkin skin;

    public GeneratorTask(CompleteSkin skinToProcess) {
        this.skin = skinToProcess;
    }

    @Override
    public void handler() {
        // Processing this skin
        Avmeister.instance.getLogger().info(String.format("Processing skin: %s", skin.toString()));
    }
}
