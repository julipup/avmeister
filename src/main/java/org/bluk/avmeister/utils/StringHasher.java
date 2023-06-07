package org.bluk.avmeister.utils;

import java.util.Base64;

public class StringHasher {
    public static String hash(String stringToHash) {
        byte[] encoded = Base64.getEncoder().encode(stringToHash.getBytes());
        return new String(encoded);
    }
}
