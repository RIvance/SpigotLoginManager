package cc.nodev.command.op;

import cc.nodev.command.PlayerCommandInterface;
import cc.nodev.core.Players;
import cc.nodev.utils.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum OpLoginCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (!Players.isOp(player)) {
            String message = Message.error("Only op can use this command");
            player.sendMessage(message);
            return false;
        }

        if (args.length == 1) {
            if (Players.isPlayerLoggedIn(player)) {
                String message = Message.error("You are already logged in, don't login again");
                player.sendMessage(message);
                return false;
            }

            try {
                if (Players.checkPin(player, Integer.parseInt(args[0]))) {
                    Players.restorePlayerState(player);
                    player.setOp(true);
                    String message = Message.info("Welcome back, {player}!", player);
                    player.sendMessage(message);
                    return true;
                }
                else {
                    String message = Message.error("Pin number is incorrect");
                    player.sendMessage(message);
                    return false;
                }
            }
            catch (NumberFormatException exception) {
                String message = Message.error("Pin number must be an integer");
                player.sendMessage(message);
                return false;
            }
        }
        return false;
    }
}
