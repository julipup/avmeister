package org.bluk.avmeister.abstracts.queue;

public class AbstractQueueTask {
    public QueueTaskStatus status = QueueTaskStatus.RUNNING;

    public void handler() {
    }

    ;

    public void setStatus(QueueTaskStatus status) {
        this.status = status;
    }
}
