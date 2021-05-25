package cc.nodev.command.op;

import cc.nodev.command.PlayerCommandInterface;
import cc.nodev.utils.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum ServerCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (!player.isOp()) {
            String message = Message.error("Only op can use this command");
            player.sendMessage(message);
            return true;
        }

        try {
            switch (args[0]) {
                case "restart":
                    player.getServer().reload();
                    return true;
                case "stop":
                    player.getServer().shutdown();
                    return true;
                default:
                    break;
            }
        }
        catch (IndexOutOfBoundsException exception) {
            String message = Message.error("Invalid number of arguments");
            player.sendMessage(message);
            return false;
        }

        return false;
    }
}
