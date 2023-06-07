package org.bluk.avmeister.skins.parts;

public class SkinPart {
    public final String id;
    public final Double x;
    public final Double y;
    public final String texturePath;

    public SkinPart(String id, Double x, Double y, String texturePath) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.texturePath = texturePath;
    }

    //
    // Builder
    public static class Builder {
        private String id;
        private Double x;
        private Double y;
        private String texturePath;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setX(Double x) {
            this.x = x;
            return this;
        }

        public Builder setY(Double y) {
            this.y = y;
            return this;
        }

        public Builder setTexture(String texturePath) {
            this.texturePath = texturePath;
            return this;
        }

        public SkinPart build() {
            // @todo throw normal errors
            if (this.texturePath == null) throw new RuntimeException("No texturePath provided");
            if (this.id == null) throw new RuntimeException("No id provided");

            return new SkinPart(id, x, y, texturePath);
        }
    }
}
