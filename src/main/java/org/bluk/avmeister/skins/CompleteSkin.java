package org.bluk.avmeister.skins;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.abstracts.groups.AbstractGroupRule;
import org.bluk.avmeister.skins.generator.SkinGenerator;
import org.bluk.avmeister.skins.generator.queue.GeneratorTask;
import org.bluk.avmeister.skins.groups.PartsGroup;
import org.bluk.avmeister.skins.groups.rules.MaxPartsRule;
import org.bluk.avmeister.skins.parts.SkinPart;
import org.bluk.avmeister.utils.SkinCacher;
import org.bluk.avmeister.utils.StringHasher;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class CompleteSkin {
    public final List<SkinPart> parts;
    public final String hash;
    @Nullable
    public SkinTexture texture;
    public BodyType bodyType = BodyType.NORMAL;

    public CompleteSkin(List<SkinPart> parts) {
        this.parts = parts;

        // Generating this skin's hash
        StringBuilder unhashedString = new StringBuilder();

        parts.stream()
                .sorted(new Comparator<SkinPart>() {
                    @Override
                    public int compare(SkinPart o1, SkinPart o2) {
                        return o1.texturePath.compareTo(o2.texturePath);
                    }
                })
                .forEach(part -> {
                    unhashedString.append(part.texturePath);
                });

        this.hash = StringHasher.hash(unhashedString.toString());

        // Checking if this skin is generated or no
        var cachedTexture = SkinCacher.getCachedTextures(this.hash);
        if (cachedTexture != null) {
            this.texture = cachedTexture;
        }
    }

    public void generate() {
        // Asking our GeneratorQueue to generate this skin
        SkinGenerator.getQueue().add(
                new GeneratorTask(this)
        );
    }

    public void generate(Runnable callback) {
        SkinGenerator.getQueue().add(
                new GeneratorTask(this, callback)
        );
    }

    public CompleteSkin recreateWithNewPart(SkinPart part) {
        var skinBuilder = new CompleteSkin.Builder();
        var oldParts = new HashSet<SkinPart>(this.parts);

        skinBuilder.addPart(part);

        if (part.group != null) {
            // Checking if we need to remove any parts from our
            // oldParts array due to group rules

            // @todo get rules from group configuration
            // List<? extends AbstractGroupRule>
            List<MaxPartsRule> rules = new ArrayList<>();
            rules.add(new MaxPartsRule());

            rules.forEach(rule -> {
                rule.calculatePartsToRemove(oldParts.stream().toList(), part).forEach(oldParts::remove);
            });
        }

        // Adding all other skin parts
        oldParts.forEach(skinBuilder::addPart);

        return skinBuilder.build();
    }

    //
    // Other
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("CompleteSkin {");
        builder.append(String.format("Hash: %s, BodyType: %s,", this.hash, this.bodyType));

        this.parts.forEach(part -> {
            builder.append(String.format(" SkinPart { id: %s, x: %s, y: %s }", part.id, part.x, part.y));
        });

        builder.append("}");

        return builder.toString();
    }

    //
    // Builder
    public static class Builder {
        private final List<SkinPart> parts = new ArrayList<>();

        public Builder addPart(SkinPart part) {
            parts.add(part);
            return this;
        }

        public CompleteSkin build() {
            return new CompleteSkin(parts);
        }
    }
}
