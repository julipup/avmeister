package org.bluk.avmeister.abstracts.queue;

import java.util.Objects;

public class AbstractQueueTask {
    public QueueTaskStatus status = QueueTaskStatus.RUNNING;
    private AbstractQueue queue;

    public AbstractQueueTask(AbstractQueue queue) {
        this.queue = queue;
    }

    public void handler() {
    }

    public void setStatus(QueueTaskStatus status) {
        this.status = status;

        // Special cases
        if (status == QueueTaskStatus.FULFILLED) {
            // Removing this task from queue
            queue.remove(this);
        }
    }
}
