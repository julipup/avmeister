package org.bluk.avmeister.managers.playermanager;

import lombok.Getter;
import net.skinsrestorer.api.PlayerWrapper;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.managers.playermanager.actions.DataSaver;
import org.bluk.avmeister.managers.playermanager.actions.SkinUpdater;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class ManagedPlayer {
    private static final HashMap<HookType, List<Function<ManagedPlayer, Void>>> hooks = new HashMap<>();
    @Getter
    private final Player player;
    //
    // ActionContainers
    @Getter
    private final DataSaver dataSaver;

    @Getter
    private final SkinUpdater skinUpdater;

    // Constructor
    public ManagedPlayer(Player player) {
        this.player = player;

        // Initializing player's action containers
        this.dataSaver = new DataSaver(this);
        this.skinUpdater = new SkinUpdater(this);

        // Running AFTER_CREATE hook
        if (hooks.containsKey(HookType.AFTER_CREATE)) {
            hooks.get(HookType.AFTER_CREATE).forEach(callback -> callback.apply(this));
        }
    }

    public static void hook(HookType type, Function<ManagedPlayer, Void> callback) {
        // Checking if we have an empty array for this hookType in
        // hooks object
        if (!hooks.containsKey(type)) hooks.put(type, new ArrayList<>());

        var hooksArray = hooks.get(type);
        hooksArray.add(callback);

        hooks.put(type, hooksArray);
    }

    //
    // Other methods
    public void destroy() {
        // @todo
        // saving player data?

        // Calling AFTER_DESTROY hook
        if (hooks.containsKey(HookType.AFTER_DESTROY)) {
            hooks.get(HookType.AFTER_DESTROY).forEach(callback -> callback.apply(this));
        }
    }

    //
    // Hooks
    public enum HookType {
        AFTER_CREATE,
        AFTER_DESTROY,
    }
}
