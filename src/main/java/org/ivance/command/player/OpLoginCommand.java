package org.ivance.command.player;

import org.ivance.command.PlayerCommandInterface;
import org.ivance.core.Players;
import org.ivance.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum OpLoginCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (!Players.isOp(player)) {
            String message = Message.error("Only op can use this command");
            player.sendMessage(message);
            return true;
        }

        if (args.length == 1) {
            if (Players.isPlayerLoggedIn(player)) {
                String message = Message.error("You are already logged in, don't login again");
                player.sendMessage(message);
                return true;
            }

            try {
                if (Players.checkPin(player, Integer.parseInt(args[0]))) {
                    Players.restorePlayerState(player);
                    player.setOp(true);
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
