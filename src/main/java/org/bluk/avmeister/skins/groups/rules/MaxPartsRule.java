package org.bluk.avmeister.skins.groups.rules;

import org.bluk.avmeister.abstracts.groups.AbstractGroupRule;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaxPartsRule extends AbstractGroupRule {
    // @todo get this value from parsed configuration value;
    private int maxParts = 1;

    @Override
    public List<SkinPart> calculatePartsToRemove(List<SkinPart> allParts, SkinPart partToAdd) {
        List<SkinPart> groupParts = allParts.stream().filter(x -> x.group == partToAdd.group).toList();
        List<SkinPart> partsToLeave = new ArrayList<>();

        // @todo refactor this logic
        if (maxParts == 1) {
            partsToLeave.add(partToAdd);
        } else {
            throw new RuntimeException("Unimplemented behavior");
        }

        return groupParts.stream()
                .filter(part -> !partsToLeave.contains(part))
                .collect(Collectors.toList());
    }

    // @todo add parser
}
