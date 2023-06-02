package org.bluk.avmeister.skins.parts;

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
                return PartLocation.HEAD;
            }
            case "LEGS" -> {
                return PartLocation.LEGS;
            }
        }

        // @todo throw normal error message
        throw new RuntimeException("invalid partType");
    }
}
