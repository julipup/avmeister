package org.bluk.avmeister.utils.skinrestorer;

import org.bluk.avmeister.skins.parts.SkinPart;

public class SerializableSkinPart {
    public final String id;

    // @todo file hash
    public final String hash = "";

    public SerializableSkinPart(SkinPart part) {
        this.id = part.id;
    }
}
