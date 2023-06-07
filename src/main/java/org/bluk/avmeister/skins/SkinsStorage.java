package org.bluk.avmeister.skins;

import lombok.Getter;
import org.bluk.avmeister.skins.generator.SkinGenerator;
import org.bluk.avmeister.skins.generator.queue.GeneratorQueue;
import org.bluk.avmeister.skins.generator.queue.GeneratorTask;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.ArrayList;
import java.util.List;

public class SkinsStorage {
    @Getter
    private static final List<CompleteSkin> entries = new ArrayList<>();

    public static void initializeFromParts(List<SkinPart> parts) {
        // Creating new instance of CompleteSkin, adding it to our storage
        // and calling postAddHook() on it
        var skin = new CompleteSkin(parts);

        entries.add(skin);
        SkinsStorage.postAddHook(skin);
    }

    public static void addToStorage(CompleteSkin skin) {
        // Adding this skin and calling postAddHook on it
        entries.add(skin);
        SkinsStorage.postAddHook(skin);
    }

    private static void postAddHook(CompleteSkin skin) {
        // @todo Checking if we have this skin stored in SkinsRestorer cache (name is skin.hash)
        SkinGenerator.getQueue().add(new GeneratorTask(skin));
    }
}
