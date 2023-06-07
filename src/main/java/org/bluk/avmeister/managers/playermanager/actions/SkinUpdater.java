package org.bluk.avmeister.managers.playermanager.actions;

import net.skinsrestorer.api.PlayerWrapper;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.managers.playermanager.ManagedPlayer;
import org.bluk.avmeister.skins.CompleteSkin;

public class SkinUpdater {
    private final ManagedPlayer player;

    public SkinUpdater(ManagedPlayer managedPlayer) {
        this.player = managedPlayer;
    }

    //
    // Actions
    public void setSkin(CompleteSkin skin) {
        if (skin.texture == null) {
            var skinUpdater = this;

            skin.generate(
                    new Runnable() {
                        @Override
                        public void run() {
                            skinUpdater.setSkin(skin);
                        }
                    }
            );
        }

        try {
            Avmeister.skinRestorer.setSkin(player.getPlayer().getName(), skin.hash);
            Avmeister.skinRestorer.applySkin(new PlayerWrapper(player.getPlayer()));
        } catch (Throwable e) {
            // @todo report errors normally
            e.printStackTrace();
        }
    }

    //
    // Events

}
