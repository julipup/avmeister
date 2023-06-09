package org.bluk.avmeister.exceptions.parts;

public class PartNotFoundException extends RuntimeException {
    public PartNotFoundException() {
        super("Part not found");
    }
}
