package cc.nodev.command.player;

import cc.nodev.command.PlayerCommandInterface;
import cc.nodev.core.Players;
import cc.nodev.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum LoginCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (args.length == 1) {
            try {
                if (Players.isPlayerLoggedIn(player)) {
                    String message = Message.error("You have already logged in, don't login again");
                    player.sendMessage(message);
                    return true;
                }

                if (Players.checkPin(player, Integer.parseInt(args[0]))) {
                    Players.restorePlayerState(player);
                    player.setOp(false);
                    Players.login(player);

                    String message = Message.info("{player} is logged in", player);
                    Bukkit.broadcastMessage(message);

                    message = Message.info("Welcome back, {player}!", player);
                    player.sendMessage(message);
                }
                else {
                    String message = Message.error("Pin number is incorrect");
                    player.sendMessage(message);
                }
                return true;
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
