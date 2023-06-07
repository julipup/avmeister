package org.bluk.avmeister.bootstrappers;

import com.google.gson.Gson;

public class CacheBootstrapper {
    public static void bootstrap() {
        // @todo check if we have {dataFolder}/cache/state.json file
        // if yes - compute current skinParts state and
        // compare it with saved state
        //
        // if no - save currently computed skinParts state
        // to this file
    }

    public static Gson getComputedState() {
        return null;
    }
}
