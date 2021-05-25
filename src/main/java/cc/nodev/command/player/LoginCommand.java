package cc.nodev.command.player;

import cc.nodev.command.PlayerCommandInterface;
import cc.nodev.core.Players;
import cc.nodev.utils.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum LoginCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (args.length == 1) {
            try {
                if (Players.checkPin(player, Integer.parseInt(args[0]))) {
                    Players.restorePlayerState(player);
                    player.setOp(false);
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
