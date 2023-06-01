package org.bluk.avmeister.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import org.bluk.avmeister.abstracts.AbstractCommand;
import org.bluk.avmeister.managers.PlayerManager;
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
            managedPlayer.getSkinUpdater().setSkin();

            sender.sendMessage("Skin set");
        }
    }
}
