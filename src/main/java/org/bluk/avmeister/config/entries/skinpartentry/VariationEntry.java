package org.bluk.avmeister.config.entries.skinpartentry;

import org.bluk.avmeister.skins.BodyType;

import java.util.Map;

public class VariationEntry {
    public String file;
    public BodyType bodyType;

    public Double x;
    public Double y;

    public VariationEntry(String file) {
        this(file, BodyType.NORMAL);
    }

    public VariationEntry(String file, BodyType bodyType) {
        this.file = file;
        this.bodyType = bodyType;
    }

    public VariationEntry(String file, BodyType bodyType, Double x, Double y) {
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
                Double x = Double.parseDouble(values.get("x").toString());
                Double y = Double.parseDouble(values.get("y").toString());

                return new VariationEntry(file, bodyType, x, y);
            } else {
                return new VariationEntry(file, bodyType);
            }
        }
    }
}
