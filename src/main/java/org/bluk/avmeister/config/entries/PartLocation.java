package org.bluk.avmeister.config.entries;

public enum PartLocation {
    BODY,
    HEAD,
    LEGS;

    public static PartLocation fromString(String location) {
        switch (location.toUpperCase()) {
            case "BODY" -> {
                return PartLocation.BODY;
            }
            case "HEAD" -> {
                return HEAD;
            }
            case "LEGS" -> {
                return LEGS;
            }
        }

        // @todo throw normal error message
        throw new RuntimeException("invalid partLocation");
    }
}
