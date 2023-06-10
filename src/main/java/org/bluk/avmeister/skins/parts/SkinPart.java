package org.bluk.avmeister.skins.parts;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.skins.groups.PartsGroup;

import javax.annotation.Nullable;

public class SkinPart {
    public final String id;
    public final Integer x;
    public final Integer y;
    @Nullable
    public final PartsGroup group;
    public final String texturePath;
    public final String fullTexturePath;

    public SkinPart(String id, int x, int y, String texturePath, @Nullable PartsGroup group) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.texturePath = texturePath;
        this.group = group;

        // FullTexturePath
        String dataPath = Avmeister.instance.getDataFolder().toPath().toString();

        if (texturePath.startsWith("/"))
            fullTexturePath = dataPath + texturePath;
        else
            fullTexturePath = dataPath + "/" + texturePath;
    }

    //
    // Builder
    public static class Builder {
        private String id;
        private Integer x;
        private Integer y;
        private String texturePath;
        @Nullable
        private PartsGroup group = null;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        public Builder setY(int y) {
            this.y = y;
            return this;
        }

        public Builder setTexture(String texturePath) {
            this.texturePath = texturePath;
            return this;
        }

        public Builder setGroup(PartsGroup group) {
            this.group = group;
            return this;
        }

        public SkinPart build() {
            // @todo throw normal errors
            if (this.texturePath == null) throw new RuntimeException("No texturePath provided");
            if (this.id == null) throw new RuntimeException("No id provided");

            return new SkinPart(id, x, y, texturePath, group);
        }
    }

    //
    // Other
    public String toString() {
        return String.format(
                "SkinPart { id: %s, group: %s, x: %s, y: %s, path: %s }",
                this.id, this.group, this.x, this.y, this.texturePath
        );
    }
}
