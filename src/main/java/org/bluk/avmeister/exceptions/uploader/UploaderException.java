package org.bluk.avmeister.exceptions.uploader;

public class UploaderException extends RuntimeException {
    public Throwable exception;

    public UploaderException(Throwable exception) {
        // @todo add more information about this exception
        super("Uploader exception");

        this.exception = exception;
    }
}
