package org.bluk.avmeister.managers.playermanager.events;

import org.bluk.avmeister.managers.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public static void handle(PlayerQuitEvent event) {
        // Getting ManagedPlayer from PlayerManager, saving player
        // information and removing this player from its manager
        PlayerManager.removePlayer(event.getPlayer());
    }
}
