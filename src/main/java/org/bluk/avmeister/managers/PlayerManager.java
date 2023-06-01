package org.bluk.avmeister.managers;

import org.bluk.avmeister.managers.playermanager.ManagedPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
    public static HashMap<UUID, ManagedPlayer> players = new HashMap<>();

    public static ManagedPlayer get(Player player) {
        if (!players.containsKey(player.getUniqueId())) PlayerManager.handleNewPlayer(player);

        return players.get(player.getUniqueId());
    }

    public static void handleNewPlayer(Player player) {
        // Creating new ManagedPlayer instance and adding this player
        // to this manager (if not there already)
        if (players.containsKey(player.getUniqueId())) return;

        var managedPlayer = new ManagedPlayer(player);
        players.put(player.getUniqueId(), managedPlayer);
    }

    public static void removePlayer(Player player) {
        if (!players.containsKey(player.getUniqueId())) return;

        var managedPlayer = players.get(player.getUniqueId());
        managedPlayer.destroy();

        players.remove(player.getUniqueId());
    }
}
