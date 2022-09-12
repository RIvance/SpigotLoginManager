package org.ivance.command.player;

import org.ivance.command.PlayerCommandInterface;
import org.ivance.core.Players;
import org.ivance.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public enum RegisterCommand implements PlayerCommandInterface {
    INSTANCE;
    @Override
    public boolean execute(@NotNull Player player, String[] args) {
        if (args.length == 1) {
            try {
                if (Players.isNewPlayer(player)) {
                    Players.register(player, Integer.parseInt(args[0]));
                    String message = Message.info(ChatColor.AQUA + "Congratulation! You have successfully registered");
                    player.sendMessage(message);
                    message = Message.info("Your pin is {color}{pin}, please remember it carefully, you will need it to login", ChatColor.BLUE, args[0]);
                    player.sendMessage(message);
                    Players.saveData(player);

                    message = Message.info("New player {player} is logged in", player);
                    Bukkit.broadcastMessage(message);

                    message = Message.info("Welcome, {player}!", player);
                    player.sendMessage(message);

                    player.teleport(player.getWorld().getSpawnLocation());
                    player.setGameMode(GameMode.SURVIVAL);
                    player.setOp(false);
                    Players.login(player);
                }
                else {
                    String message = Message.error("You are already registered, use `/login` to login");
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
