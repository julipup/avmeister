package org.bluk.avmeister.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.skins.SkinTexture;

import javax.annotation.Nullable;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class SkinCacher {
    private static final String dataPath = Avmeister.instance.getDataFolder() + "/cache/";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static @Nullable SkinTexture getCachedTextures(String hash) {
        final String filePath = dataPath + hash + ".json";

        // Checking if we have this hash or no
        if (Files.exists(Path.of(filePath))) {
            try {
                var values = gson.fromJson(new FileReader(filePath), HashMap.class);

                if (values.containsKey("value") && values.containsKey("signature")) {
                    // @todo add some kind of timestamp check?
                    return new SkinTexture((String) values.get("value"), (String) values.get("signature"));
                }
            } catch (Throwable e) {
                // @todo normal error reporting
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void saveToCache(String hash, SkinTexture texture) {
        final String json = gson.toJson(texture);
        final String filePath = dataPath + hash + ".json";

        try {
            // Deleting old file (if exists)
            if (Files.exists(Path.of(filePath))) Files.delete(Path.of(filePath));

            if (!Files.exists(Path.of(dataPath))) Files.createDirectory(Path.of(dataPath));

            Files.write(Path.of(filePath), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (Throwable e) {
            // @todo normal error reporting
            e.printStackTrace();
        }
    }
}
