package org.bluk.avmeister.skins;

import org.bluk.avmeister.skins.parts.SkinPart;

import java.util.ArrayList;
import java.util.List;

public class CompleteSkin {
    public final List<SkinPart> head;
    public final List<SkinPart> body;
    public final List<SkinPart> legs;

    public CompleteSkin(List<SkinPart> head, List<SkinPart> body, List<SkinPart> legs) {
        this.head = head;
        this.body = body;
        this.legs = legs;
    }

    //
    // Builder
    public static class Builder {
        private final List<SkinPart> head = new ArrayList<>();
        private final List<SkinPart> body = new ArrayList<>();
        private final List<SkinPart> legs = new ArrayList<>();

        public Builder addHeadPart(SkinPart part) {
            this.head.add(part);
            return this;
        }

        public Builder addBodyPart(SkinPart part) {
            this.body.add(part);
            return this;
        }

        public Builder addLegsPart(SkinPart part) {
            this.legs.add(part);
            return this;
        }

        public CompleteSkin build() {
            return new CompleteSkin(head, body, legs);
        }
    }
}
