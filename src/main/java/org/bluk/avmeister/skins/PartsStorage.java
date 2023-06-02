package org.bluk.avmeister.skins;

import lombok.Getter;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.config.SkinPartEntry;
import org.bluk.avmeister.exceptions.files.FileNotFoundException;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartsStorage {
    @Getter
    private static final List<SkinPart> entries = new ArrayList<>();

    public static void initializeFromEntry(SkinPartEntry entry) {
        entry.variations.forEach(variationEntry -> {
            String filePath = Avmeister.instance.getDataFolder().toPath().toString();

            if (variationEntry.file.startsWith("/"))
                filePath = filePath + variationEntry.file;
            else
                filePath = filePath + "/" + variationEntry.file;

            if (!Files.exists(Path.of(filePath)))
                throw new FileNotFoundException(filePath);

            entries.add(new SkinPart.Builder()
                    .setId(entry.id)
                    .setTexture(variationEntry.file)
                    .withBodyType(variationEntry.bodyType)
                    .setPartLocation(entry.location)
                    .build());
        });
    }
}
