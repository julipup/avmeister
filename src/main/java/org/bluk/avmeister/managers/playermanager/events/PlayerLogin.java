package org.bluk.avmeister.managers.playermanager.events;

import org.bluk.avmeister.managers.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {
    @EventHandler
    public static void handle(PlayerLoginEvent event) {
        // Creating new ManagedPlayer instance and adding this
        // player to PlayerManager
        PlayerManager.handleNewPlayer(event.getPlayer());
    }
}
