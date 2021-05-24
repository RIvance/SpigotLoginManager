package cc.nodev.command;

import cc.nodev.utils.DataBaseSession;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// login <password>

public class LoginCommand extends Command {

    protected LoginCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String password = args[0];

            if (password == null) {
                player.sendMessage("Usage: /login <password>");
                return false;
            }

            if (DataBaseSession.INSTANCE.checkPlayerExists(player)) {

            }

        }

        return false;
    }
}