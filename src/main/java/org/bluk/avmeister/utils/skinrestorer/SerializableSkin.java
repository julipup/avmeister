package org.bluk.avmeister.utils.skinrestorer;

import org.bluk.avmeister.skins.CompleteSkin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SerializableSkin {
    public final String hash;
    public final List<SerializableSkinPart> parts;

    public SerializableSkin(CompleteSkin skin) {
        this.hash = skin.hash;
        this.parts = skin.parts
                .stream()
                .map(SerializableSkinPart::new)
                .collect(Collectors.toList());
    }

    public static CompleteSkin deserialize(HashMap<?, ?> values) {
        // @todo throw normal errors
        if (!values.containsKey("hash")) throw new RuntimeException("Invalid skin data to deserialize");

        String hash = (String) values.get("hash");

        // @todo deserializing parts
        // @todo Calculating parts hash

        return new CompleteSkin(new ArrayList<>());
    }
}
