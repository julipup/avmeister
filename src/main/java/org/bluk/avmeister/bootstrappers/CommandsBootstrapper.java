package org.bluk.avmeister.bootstrappers;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.abstracts.AbstractCommand;

import java.util.Set;

public class CommandsBootstrapper {
    public static void bootstrap() {
        Avmeister.instance.getLogger().info("Starting CommandsBootstrapper");

        Set<Class<? extends AbstractCommand>> rawCommands = Avmeister.reflections.getSubTypesOf(AbstractCommand.class);

        rawCommands.forEach(rawCommand -> {
            try {
                AbstractCommand command = rawCommand.getDeclaredConstructor().newInstance();

                Avmeister.commands.register(command, command.prefix);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        // Commiting all registered commands
        Avmeister.commands.registerCommands();
    }
}
