package org.bluk.avmeister.bootstrappers;

import org.bluk.avmeister.Avmeister;
import org.bukkit.event.Listener;

import java.util.Set;

/*
 | Gets all events using Reflection and registers
 | them in bukkit system
 */
public class EventsBootstrapper {
    public static void bootstrap() {
        Avmeister.instance.getLogger().info("Starting EventsBootstapper");

        Set<Class<? extends Listener>> rawListeners = Avmeister.reflections.getSubTypesOf(Listener.class);

        rawListeners.forEach((rawListener) -> {
            try {
                // Creating new instance of this listener
                Listener listener = rawListener.getDeclaredConstructor().newInstance();

                // Registering
                Avmeister.instance.getServer().getPluginManager().registerEvents(listener, Avmeister.instance);

                Avmeister.instance.getLogger().info(String.format("Events %s was registered", rawListener.getName()));
            } catch (Throwable e) {
                e.printStackTrace();
            }
            ;
        });
    }
}
