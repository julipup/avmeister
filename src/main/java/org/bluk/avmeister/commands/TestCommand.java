package org.bluk.avmeister.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.abstracts.AbstractCommand;
import org.bluk.avmeister.managers.PlayerManager;
import org.bluk.avmeister.skins.CompleteSkin;
import org.bluk.avmeister.skins.parts.SkinPart;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {
    public TestCommand() {
        withPrefix("test");
    }

    @Command(name = "", aliases = {}, desc = "Test command")
    public void exampleCommand(@Sender CommandSender sender) {
        if (sender instanceof Player player) {
            var managedPlayer = PlayerManager.get(player);

            try {
                // Test skin
                CompleteSkin skin = new CompleteSkin.Builder()
                        .addPart(new SkinPart.Builder()
                                .setId("test-head")
                                .setTexture("")
                                .build())
                        .addPart(new SkinPart.Builder()
                                .setId("test-body")
                                .setTexture("")
                                .build())
                        .addPart(new SkinPart.Builder()
                                .setId("test-legs")
                                .setTexture("parts/")
                                .build())
                        .build();

                Avmeister.instance.getLogger().info("Asking our generator to generate new skin...");
                managedPlayer.getSkinManager().setSkin(skin);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
