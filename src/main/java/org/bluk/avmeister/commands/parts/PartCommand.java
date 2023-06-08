package org.bluk.avmeister.commands.parts;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import org.bluk.avmeister.abstracts.AbstractCommand;
import org.bukkit.command.CommandSender;

public class PartCommand extends AbstractCommand {
    public PartCommand() {
        withPrefix("skin part");
    }

    @Command(name = "set", aliases = {}, desc = "")
    public void setPart(@Sender CommandSender sender) {
        // @todo implement
    }
}
