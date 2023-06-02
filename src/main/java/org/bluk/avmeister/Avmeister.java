package org.bluk.avmeister;

import com.jonahseguin.drink.CommandService;
import com.jonahseguin.drink.Drink;
import org.bluk.avmeister.bootstrappers.*;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import net.skinsrestorer.api.SkinsRestorerAPI;

public final class Avmeister extends JavaPlugin {
    public static final Reflections reflections = new Reflections("org.bluk.avmeister");
    public static Avmeister instance;
    public static SkinsRestorerAPI skinRestorer;
    public static CommandService commands;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        skinRestorer = SkinsRestorerAPI.getApi();
        commands = Drink.get(this);

        // Bootstrapping our plugin
        // - Order is important!
        try {
            EventsBootstrapper.bootstrap();
            CommandsBootstrapper.bootstrap();
            ConfigBootstrapper.bootstrap();
            ExampleDataBootstrapper.bootstrap();
            SkinPartsBootstrapper.bootstrap();
            SkinsBootstrapper.bootstrap();
        } catch (Throwable e) {
            // @todo normal errors
            e.printStackTrace();
        }
        ;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
