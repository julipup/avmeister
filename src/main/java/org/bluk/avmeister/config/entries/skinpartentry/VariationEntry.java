package org.bluk.avmeister.config.entries.skinpartentry;

import org.bluk.avmeister.skins.BodyType;

import java.util.Map;

public class VariationEntry {
    public String file;
    public BodyType bodyType;

    public Integer x;
    public Integer y;

    public VariationEntry(String file) {
        this(file, BodyType.NORMAL);
    }

    public VariationEntry(String file, BodyType bodyType) {
        this.file = file;
        this.bodyType = bodyType;
    }

    public VariationEntry(String file, BodyType bodyType, int x, int y) {
        this.file = file;
        this.bodyType = bodyType;
        this.x = x;
        this.y = y;
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

            if (values.containsKey("x") && values.containsKey("y")) {
                int x = Integer.parseInt(values.get("x").toString());
                int y = Integer.parseInt(values.get("y").toString());

                return new VariationEntry(file, bodyType, x, y);
            } else {
                return new VariationEntry(file, bodyType);
            }
        }
    }
}
