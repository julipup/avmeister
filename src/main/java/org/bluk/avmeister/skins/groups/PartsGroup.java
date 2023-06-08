package org.bluk.avmeister.skins.groups;

import org.bluk.avmeister.abstracts.groups.AbstractGroupRule;
import org.bluk.avmeister.config.entries.groups.PartsGroupEntry;

import java.util.List;

public class PartsGroup {
    public final String id;
    public final List<? extends AbstractGroupRule> rules;

    public PartsGroup(String id, List<? extends AbstractGroupRule> rules) {
        this.id = id;
        this.rules = rules;
    }
}
