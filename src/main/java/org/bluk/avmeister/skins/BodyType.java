package org.bluk.avmeister.skins;

public enum BodyType {
    SLIM,
    NORMAL;

    public static BodyType fromString(String type) {
        switch (type.toUpperCase()) {
            case "SLIM" -> {
                return BodyType.SLIM;
            }
            case "NORMAL" -> {
                return BodyType.NORMAL;
            }
        }

        // @todo throw normal error with normal message
        throw new RuntimeException("invalid bodyType");
    }
}
