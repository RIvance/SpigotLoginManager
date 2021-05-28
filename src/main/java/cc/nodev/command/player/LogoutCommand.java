package cc.nodev.command.player;

import cc.nodev.command.PlayerCommandInterface;
import cc.nodev.core.Players;
import cc.nodev.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum LogoutCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (!Players.isPlayerLoggedIn(player)) {
            String message = Message.error("You are already logged out, don't logout again");
            player.sendMessage(message);
            return true;
        }

        Players.savePlayerState(player);
        Players.saveData(player);
        player.setOp(false);
        player.setGameMode(GameMode.SPECTATOR);
        Players.logout(player);

        String message = Message.info("{player} is logged out", player);
        Bukkit.broadcastMessage(message);

        message = Message.info("You have logged out, use `/login` to login");
        player.sendMessage(message);
        return true;
    }
}
