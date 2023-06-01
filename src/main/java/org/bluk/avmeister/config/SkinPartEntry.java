package org.bluk.avmeister.config;

import org.apache.commons.lang.StringUtils;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.bootstrappers.SkinPartsBootstrapper;
import org.bluk.avmeister.config.skinpartentry.PartType;
import org.bluk.avmeister.config.skinpartentry.VariationEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SkinPartEntry {
    public String id;
    public PartType type;
    public List<VariationEntry> variations;

    public SkinPartEntry(String id, PartType type, List<VariationEntry> variations) {
        this.id = id;
        this.type = type;
        this.variations = variations;
    }

    ;

    public static class Parser {
        public static SkinPartEntry parse(Map<?, ?> values) {
            // Validating that all fields exist
            // @todo remove this shitty looking hardcode?
            if (!values.containsKey("id") ||
                    !values.containsKey("type") ||
                    !values.containsKey("variations")
                // @todo throw normal error class and message
            ) throw new RuntimeException("Invalid configuration");

            // Parsing
            String id = (String) values.get("id");
            PartType type = PartType.fromString((String) values.get("type"));

            // Parsing variations
            var rawVariations = (List<Map<?, ?>>) values.get("variations");
            var variations = new ArrayList<VariationEntry>();

            rawVariations.forEach(rawVariation -> {
                var parsedVariation = VariationEntry.Parser.parse(rawVariation);
                variations.add(parsedVariation);
            });

            // Creating new SkinPart instance
            return new SkinPartEntry(id, type, variations);
        }
    }
}
