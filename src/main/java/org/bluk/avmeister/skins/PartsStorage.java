package org.bluk.avmeister.skins;

import lombok.Getter;
import org.bluk.avmeister.config.SkinPartEntry;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.ArrayList;
import java.util.List;

public class PartsStorage {
    @Getter
    private static final List<SkinPart> entries = new ArrayList<>();

    public static void initializeFromEntry(SkinPartEntry entry) {
        // @todo
        // check if this file exists
        entry.variations.forEach(variationEntry -> {
            entries.add(new SkinPart.Builder()
                    .setId(entry.id)
                    .setTexture(variationEntry.file)
                    .setBodyType(variationEntry.bodyType)
                    .build());
        });
    }
}
