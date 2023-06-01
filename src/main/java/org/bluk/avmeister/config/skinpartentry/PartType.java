package org.bluk.avmeister.config.skinpartentry;

public enum PartType {
    BODY,
    HEAD,
    LEGS;

    public static PartType fromString(String type) {
        switch (type.toUpperCase()) {
            case "BODY" -> {
                return PartType.BODY;
            }
            case "HEAD" -> {
                return PartType.HEAD;
            }
            case "LEGS" -> {
                return PartType.LEGS;
            }
        }

        // @todo throw normal error message
        throw new RuntimeException("invalid partType");
    }
}
