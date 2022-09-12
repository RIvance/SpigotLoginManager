package org.ivance.event;

import org.ivance.core.Players;
import org.ivance.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setOp(false);
        player.setGameMode(GameMode.SPECTATOR);

        String title = ChatColor.AQUA + "Minecraft" + ChatColor.WHITE + "::" + ChatColor.GOLD + "Server";
        String subtitle = ChatColor.GREEN + "https://www.example.com";
        player.sendTitle(title, subtitle, 10, 100, 15);

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 0.0f);
        String message = Message.info("Welcome to {title}, {player}", title, player);
        player.sendMessage(message);

        if (Players.isNewPlayer(player)) {
            message = Message.info("Please use `/register` command to register");
        } else {
            message = Message.info("Please use `/login` command to login");
        }
        player.sendMessage(message);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerExit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Players.isPlayerLoggedIn(player)) {
            Players.savePlayerState(player);
            Players.saveData(player);
        }
    }
}
