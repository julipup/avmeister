package org.bluk.avmeister.skins.generator.queue;

import org.bluk.avmeister.abstracts.queue.AbstractQueue;
import org.bluk.avmeister.exceptions.queue.QueueAlreadyRunningException;

public class GeneratorQueue extends AbstractQueue<GeneratorTask> {
    public GeneratorQueue() throws QueueAlreadyRunningException {
        super(false);
    }
}
