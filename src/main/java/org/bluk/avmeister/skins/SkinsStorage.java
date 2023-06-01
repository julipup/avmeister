package org.bluk.avmeister.skins;

import lombok.Getter;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.ArrayList;
import java.util.List;

public class SkinsStorage {
    @Getter
    private static List<CompleteSkin> entries = new ArrayList<>();

    static {
        // testing
        entries.add(new CompleteSkin.Builder()
                .addBodyPart(new SkinPart.Builder()
                        .setTexture("")
                        .build())
                .build());
    }
}
