package org.bluk.avmeister.bootstrappers;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.config.entries.SkinPartEntry;
import org.bluk.avmeister.config.entries.groups.PartsGroupEntry;
import org.bluk.avmeister.skins.GroupsStorage;
import org.bluk.avmeister.skins.PartsStorage;
import org.bukkit.configuration.file.FileConfiguration;

public class PartGroupsBootstrapper {
    public static void bootstrap() {
        FileConfiguration config = Avmeister.instance.getConfig();

        config.getMapList("groups").forEach(rawGroup -> {
            var parsedGroup = PartsGroupEntry.Parser.parse(rawGroup);
            GroupsStorage.initializeFromEntry(parsedGroup);

            Avmeister.instance.getLogger().info(String.format("Parsed PartsGroupEntry: %s", parsedGroup));
        });
    }

}
