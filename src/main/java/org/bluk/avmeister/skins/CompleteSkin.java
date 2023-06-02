package org.bluk.avmeister.skins;

import lombok.Getter;
import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.ArrayList;
import java.util.List;

public class CompleteSkin {
    public final SkinPart head;
    public final SkinPart body;
    public final SkinPart legs;

    // @todo implement hash generator, isGenerated logic
    public final String hash = "0";

    @Getter
    private boolean isGenerated = false;

    public CompleteSkin(SkinPart head, SkinPart body, SkinPart legs) {
        this.head = head;
        this.body = body;
        this.legs = legs;
    }

    //
    // Builder
    public static class Builder {
        private SkinPart head;
        private SkinPart body;
        private SkinPart legs;

        public Builder setHeadPart(SkinPart part) {
            this.head = part;
            return this;
        }

        public Builder setBodyPart(SkinPart part) {
            this.body = part;
            return this;
        }

        public Builder setLegsPart(SkinPart part) {
            this.legs = part;
            return this;
        }

        public CompleteSkin build() {
            return new CompleteSkin(head, body, legs);
        }
    }
}
