package org.bluk.avmeister.config.entries.groups;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class PartsGroupEntry {
    public final String id;

    @Nullable
    public final List<GroupRuleEntry> rules;

    public PartsGroupEntry(String id, @Nullable List<GroupRuleEntry> rules) {
        this.id = id;
        this.rules = rules;
    }

    public static class Parser {
        public static PartsGroupEntry parse(Map<?, ?> values) {
            // @todo throw normal errors
            if (!values.containsKey("id")) throw new RuntimeException("Invalid partsGroup configuration");

            var id = (String) values.get("id");
            List<GroupRuleEntry> rules = null;

            // @todo parse rule entries

            return new PartsGroupEntry(id, rules);
        }
    }
}
