package org.bluk.avmeister.skins;

import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import lombok.Getter;
import lombok.Setter;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.skins.generator.ImageUploader;
import org.bluk.avmeister.skins.generator.SkinGenerator;
import org.bluk.avmeister.skins.generator.queue.GeneratorTask;
import org.bluk.avmeister.skins.parts.SkinPart;
import org.bluk.avmeister.utils.SkinCacher;
import org.bluk.avmeister.utils.StringHasher;

import javax.annotation.Nullable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class CompleteSkin {
    public final List<SkinPart> parts;

    public final String hash;

    @Nullable
    public SkinTexture texture;

    public CompleteSkin(List<SkinPart> parts) {
        this.parts = parts;

        // Generating this skin's hash
        StringBuilder unhashedString = new StringBuilder();

        parts.forEach(part -> {
            unhashedString.append(part.texturePath);
        });

        this.hash = StringHasher.hash(unhashedString.toString());

        // Checking if this skin is generated or no
        var cachedTexture = SkinCacher.getCachedTextures(this.hash);
        Avmeister.instance.getLogger().info(String.format("New CompleteSkin entry with cachedTexture:", cachedTexture));

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

    //
    // Builder
    public static class Builder {
        private List<SkinPart> parts = new ArrayList<>();

        public Builder addPart(SkinPart part) {
            parts.add(part);
            return this;
        }

        public CompleteSkin build() {
            return new CompleteSkin(parts);
        }
    }
}
