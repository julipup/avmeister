package org.bluk.avmeister.config.entries.groups;

import javax.annotation.Nullable;

public class GroupRuleEntry {
    public final String name;

    @Nullable
    public String configuration;

    public GroupRuleEntry(String name, @Nullable String configuration) {
        this.name = name;
        this.configuration = configuration;
    }
}
