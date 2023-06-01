package org.bluk.avmeister.exceptions.queue;

public class QueueAlreadyRunningException extends Exception {
    public QueueAlreadyRunningException() {
        super("Tried to start already running queue");
    }
}
