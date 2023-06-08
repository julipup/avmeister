package org.bluk.avmeister.skins;

import org.bluk.avmeister.config.entries.groups.PartsGroupEntry;
import org.bluk.avmeister.exceptions.groups.GroupNotFoundException;
import org.bluk.avmeister.skins.groups.PartsGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupsStorage {
    public static List<PartsGroup> entries = new ArrayList<>();

    public static void initializeFromEntry(PartsGroupEntry entry) {
        if (entries.stream().anyMatch(x -> Objects.equals(x.id, entry.id))) {
            // @todo urgh
            throw new RuntimeException("yes 22901");
        }

        // @todo Checking rules

        // Adding this group to groups storage
        entries.add(new PartsGroup(entry.id, null));
    }

    public static PartsGroup getById(String id) throws GroupNotFoundException {
        var foundGroup = entries.stream().filter(x -> Objects.equals(x.id, id)).findFirst();

        // @todo yes, normal errors
        if (foundGroup.isEmpty()) throw new RuntimeException();

        return foundGroup.get();
    }
}
