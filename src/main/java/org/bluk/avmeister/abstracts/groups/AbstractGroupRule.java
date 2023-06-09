package org.bluk.avmeister.abstracts.groups;

import com.google.gson.Gson;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.List;

public class AbstractGroupRule {
    public Gson configuration;

    public List<SkinPart> calculatePartsToRemove(List<SkinPart> groupParts, SkinPart partToAdd) {
        return null;
    }

    ;
}
