package org.bluk.avmeister.bootstrappers;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.config.SkinPartEntry;
import org.bluk.avmeister.skins.PartsStorage;
import org.bukkit.configuration.file.FileConfiguration;

public class SkinPartsBootstrapper {
    public static void bootstrap() {
        // Getting all parts from .parts config array,
        // creating new SkinPart instance and adding
        // them to PartsStorage
        FileConfiguration config = Avmeister.instance.getConfig();

        config.getMapList("parts").forEach(rawPart -> {
            var parsedPart = SkinPartEntry.Parser.parse(rawPart);
            PartsStorage.initializeFromEntry(parsedPart);

            Avmeister.instance.getLogger().info(String.format("Parsed SkinPartEntry: %s", parsedPart));
        });
    }
}
