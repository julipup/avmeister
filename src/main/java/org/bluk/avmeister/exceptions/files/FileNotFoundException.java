package org.bluk.avmeister.exceptions.files;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String filePath) {
        super(String.format("File %s not found", filePath));
    }
}
