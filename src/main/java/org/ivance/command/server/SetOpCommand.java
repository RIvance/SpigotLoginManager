package org.ivance.command.server;

import org.ivance.command.PlayerCommandInterface;
import org.ivance.core.Players;
import org.ivance.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum SetOpCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (args.length >= 1) {
            if (!Players.isOp(player)) {
                String message = Message.error("Only OP can use this command");
                player.sendMessage(message);
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                String message = Message.error("Player {player} is not exist", Message.playerName(args[0]));
                player.sendMessage(message);
                return true;
            }

            if (args.length == 1) {
                target.setOp(true);
            } else {
                if (args[1].equals("true")) {
                    target.setOp(true);
                } else if (args[1].equals("false")) {
                    target.setOp(false);
                } else {
                    String message = Message.error("The 2nd argument must be `true` or `false`");
                    player.sendMessage(message);
                }
            }
            return true;
        }
        return false;
    }
}
