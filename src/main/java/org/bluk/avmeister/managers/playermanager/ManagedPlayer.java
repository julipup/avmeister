package org.bluk.avmeister.managers.playermanager;

import lombok.Getter;
import org.bluk.avmeister.managers.playermanager.actions.SkinManager;
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
    private final SkinManager skinManager;

    // Constructor
    public ManagedPlayer(Player player) {
        this.player = player;

        // Initializing player's action containers
        this.skinManager = new SkinManager(this);

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
