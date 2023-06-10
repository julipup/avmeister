package org.bluk.avmeister.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.skins.CompleteSkin;
import org.bluk.avmeister.utils.skinrestorer.SerializableSkin;
import org.bluk.avmeister.utils.skinrestorer.SerializableSkinPart;
import org.bukkit.block.data.type.TripwireHook;

import javax.annotation.Nullable;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class SkinRestorer {
    private static final String dataPath = Avmeister.instance.getDataFolder() + "/profiles/";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static @Nullable CompleteSkin getSavedSkin(UUID playerId) {
        final String filePath = dataPath + playerId + ".json";

        if (Files.exists(Path.of(filePath))) {
            try {
                var object = gson.fromJson(new FileReader(filePath), SerializableSkin.class);
                return SerializableSkin.deserialize(object);
            } catch (Throwable e) {
                // @todo normal error handling
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void saveToFile(UUID playerId, CompleteSkin skin) {
        final String json = gson.toJson(new SerializableSkin(skin));
        final String filePath = dataPath + playerId + ".json";

        Avmeister.instance.getLogger().info(String.format("Saving skin to file %s", json));

        try {
            // Deleting old file (if exists)
            if (Files.exists(Path.of(filePath))) Files.delete(Path.of(filePath));
            if (!Files.exists(Path.of(dataPath))) Files.createDirectory(Path.of(dataPath));

            Files.write(Path.of(filePath), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (Throwable e) {
            // @todo yes
            e.printStackTrace();
        }
    }
}
