package org.bluk.avmeister.managers.playermanager.actions;

import lombok.Getter;
import net.skinsrestorer.api.PlayerWrapper;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.managers.playermanager.ManagedPlayer;
import org.bluk.avmeister.skins.CompleteSkin;
import org.bluk.avmeister.utils.SkinRestorer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SkinManager {
    private final ManagedPlayer managedPlayer;
    private final Player player;
    @Getter
    private CompleteSkin skin;

    public SkinManager(ManagedPlayer managedPlayer) {
        this.managedPlayer = managedPlayer;
        this.player = managedPlayer.getPlayer();
        this.skin = new CompleteSkin(new ArrayList<>());

        // Loading skin data from file
        this.loadSkin();
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

            return;
        }

        this.skin = skin;
        this.saveSkin();

        try {
            Avmeister.skinRestorer.setSkin(player.getName(), skin.hash);
            Avmeister.skinRestorer.applySkin(new PlayerWrapper(player.getPlayer()));
        } catch (Throwable e) {
            // @todo report errors normally
            e.printStackTrace();
        }
    }

    public void loadSkin() {
        var loadedSkin = SkinRestorer.getSavedSkin(player.getUniqueId());
        if (loadedSkin != null) this.skin = loadedSkin;
    }

    public void saveSkin() {
        SkinRestorer.saveToFile(player.getUniqueId(), this.skin);
    }
}
