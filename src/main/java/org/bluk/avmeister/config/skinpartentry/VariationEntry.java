package org.bluk.avmeister.config.skinpartentry;

import org.bluk.avmeister.skins.BodyType;
import org.xerial.snappy.pool.BufferPool;

import java.util.Map;

public class VariationEntry {
    public String file;
    public BodyType bodyType;

    public VariationEntry(String file) {
        this(file, BodyType.NORMAL);
    }

    public VariationEntry(String file, BodyType bodyType) {
        this.file = file;
        this.bodyType = bodyType;
    }

    public static class Parser {
        public static VariationEntry parse(Map<?, ?> values) {
            // Validating that all fields exist
            // @todo remove this shitty looking hardcode?
            // @todo throw normal error class and message
            if (!values.containsKey("file")) throw new RuntimeException("Invalid configuration");

            // Parsing
            String file = (String) values.get("file");
            BodyType bodyType = BodyType.NORMAL;

            if (values.containsKey("bodyType")) {
                bodyType = BodyType.fromString((String) values.get("bodyType"));
            }
            ;

            return new VariationEntry(file, bodyType);
        }
    }
}
