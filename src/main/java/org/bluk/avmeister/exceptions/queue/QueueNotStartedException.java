package org.bluk.avmeister.exceptions.queue;

public class QueueNotStartedException extends Exception {
    public QueueNotStartedException() {
        super("Tried to stop not running queue");
    }
}
