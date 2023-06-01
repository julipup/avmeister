package org.bluk.avmeister.skins.parts;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.skins.BodyType;

public class SkinPart {
    public final String id;
    public final int x;
    public final int y;
    public final String texturePath;
    public final BodyType bodyType;

    public SkinPart(String id, int x, int y, String texturePath, BodyType bodyType) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.texturePath = texturePath;
        this.bodyType = bodyType;
    }

    //
    // Builder
    public static class Builder {
        private String id;
        private int x = 0;
        private int y = 0;
        private String texturePath;
        private BodyType bodyType = BodyType.NORMAL;

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

        public Builder setBodyType(BodyType type) {
            this.bodyType = type;
            return this;
        }

        public SkinPart build() {
            // @todo throw normal errors
            if (this.texturePath == null) throw new RuntimeException("No texturePath provided");
            if (this.id == null) throw new RuntimeException("No id provided");

            return new SkinPart(id, x, y, texturePath, bodyType);
        }
    }
}