package org.bluk.avmeister.abstracts;

public class AbstractCommand {
    public String prefix;

    public void withPrefix(String prefix) {
        this.prefix = prefix;
    }
}
