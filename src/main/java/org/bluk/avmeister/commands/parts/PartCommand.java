package org.bluk.avmeister.commands.parts;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.abstracts.AbstractCommand;
import org.bluk.avmeister.exceptions.parts.PartNotFoundException;
import org.bluk.avmeister.managers.PlayerManager;
import org.bluk.avmeister.skins.PartsStorage;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PartCommand extends AbstractCommand {
    public PartCommand() {
        withPrefix("avatar");
    }

    @Command(name = "addPart", aliases = {}, desc = "", usage = "<player> <string>")
    public void setPart(@Sender CommandSender sender, Player target, String partId) {
        // @todo implement
        if (!(sender instanceof ConsoleCommandSender) && !sender.hasPermission("avmeister.parts.set")) {
            // @todo move this message to config
            sender.sendMessage("You do not have permissions to this command");
            return;
        }

        // Checking if part with this id exists
        try {
            PartsStorage.getById(partId);
        } catch (PartNotFoundException ignored) {
            sender.sendMessage("This part not found");
            return;
        }

        // Getting target player's skin configuration
        var managedPlayer = PlayerManager.get(target);

        var currentSkin = managedPlayer.getSkinUpdater().getSkin();

        // Adding this part to this player's CompleteSkin
        var skinPart = PartsStorage.getById(partId);
        var newSkin = currentSkin.recreateWithNewPart(skinPart);

        // Generating and updating player's skin
        managedPlayer.getSkinUpdater().setSkin(newSkin);
    }
}
