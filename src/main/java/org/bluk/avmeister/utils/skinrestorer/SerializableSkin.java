package org.bluk.avmeister.utils.skinrestorer;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.skins.CompleteSkin;
import org.bluk.avmeister.skins.PartsStorage;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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

    public static CompleteSkin deserialize(SerializableSkin serialized) {
        // Getting all parts from storage
        List<SkinPart> parts = new ArrayList<>();

        for (SerializableSkinPart serializedPart : serialized.parts) {
            parts.add(PartsStorage.getById(serializedPart.id));
        }

        var skin = new CompleteSkin(parts);

        // Checking hash
        if (!Objects.equals(skin.hash, serialized.hash)) {
            // @todo throw normal error
            throw new RuntimeException(String.format("Invalid hash. Serialized: %s, computed: %s", serialized.hash, skin.hash));
        }

        return skin;
    }

    public String toString() {
        return String.format("SerializableSkin { hash: %s, parts: [%s] }", hash, parts);
    }
}
