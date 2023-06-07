package org.bluk.avmeister.config.entries;

import org.bluk.avmeister.config.entries.skinpartentry.VariationEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkinPartEntry {
    public String id;
    public PartLocation location;
    public List<VariationEntry> variations;

    public SkinPartEntry(String id, PartLocation location, List<VariationEntry> variations) {
        this.id = id;
        this.location = location;
        this.variations = variations;
    }

    public static class Parser {
        public static SkinPartEntry parse(Map<?, ?> values) {
            // Validating that all fields exist
            // @todo remove this shitty looking hardcode?
            if (!values.containsKey("id") ||
                    !values.containsKey("location") ||
                    !values.containsKey("variations")
                // @todo throw normal error class and message
            ) throw new RuntimeException("Invalid configuration");

            // Parsing
            String id = (String) values.get("id");
            PartLocation location = PartLocation.fromString((String) values.get("location"));

            // Parsing variations
            var rawVariations = (List<Map<?, ?>>) values.get("variations");
            var variations = new ArrayList<VariationEntry>();

            rawVariations.forEach(rawVariation -> {
                var parsedVariation = VariationEntry.Parser.parse(rawVariation);
                variations.add(parsedVariation);
            });

            // Creating new SkinPart instance
            return new SkinPartEntry(id, location, variations);
        }
    }
}
