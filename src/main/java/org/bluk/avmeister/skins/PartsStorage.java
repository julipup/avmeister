package org.bluk.avmeister.skins;

import lombok.Getter;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.config.entries.SkinPartEntry;
import org.bluk.avmeister.exceptions.files.FileNotFoundException;
import org.bluk.avmeister.exceptions.groups.GroupNotFoundException;
import org.bluk.avmeister.exceptions.parts.PartNotFoundException;
import org.bluk.avmeister.skins.groups.PartsGroup;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

            var partBuilder = new SkinPart.Builder()
                    .setId(entry.id)
                    .setTexture(variationEntry.file);

            if (entry.group != null) partBuilder.setGroup(GroupsStorage.getById(entry.group));

            // Determining x and y coordinates
            // using skinEntry's partLocation and bodyType
            //                  or
            // if entry contains x and y fields - just
            // paste them to our builder
            if (variationEntry.x != null && variationEntry.y != null) {
                partBuilder.setX(variationEntry.x);
                partBuilder.setY(variationEntry.y);
            } else {
                // @todo throw normal error
                if (variationEntry.bodyType == null || entry.location == null)
                    throw new RuntimeException(String.format("SkinPartVariationEntry with id %s and file %s could not be created: - no x && y coordinates provided; - no bodyType or partLocation provided", entry.id, variationEntry.file));

                int x = 0;
                int y = 0;

                switch (variationEntry.bodyType) {
                    case NORMAL -> {
                        switch (entry.location) {
                            case BODY -> {
                                y = 16;
                            }
                            case LEGS -> {
                                y = 52;
                            }
                        }
                    }
                    case SLIM -> {
                        // @todo implement
                        throw new RuntimeException("Not implemented");
                    }
                }

                partBuilder.setX(x);
                partBuilder.setY(y);
            }

            entries.add(partBuilder.build());
        });
    }

    public static SkinPart getById(String id) throws PartNotFoundException {
        var foundPart = entries.stream().filter(x -> Objects.equals(x.id, id)).findFirst();

        // @todo yes, normal errors
        if (foundPart.isEmpty()) throw new PartNotFoundException();

        return foundPart.get();
    }
}
