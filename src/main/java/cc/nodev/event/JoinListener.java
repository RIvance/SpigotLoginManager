package cc.nodev.event;

import cc.nodev.core.Players;
import cc.nodev.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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

        String title = ChatColor.AQUA + "Nodev" + ChatColor.WHITE + "::" + ChatColor.GOLD + "Craft";
        String subtitle = ChatColor.GREEN + "https://nodev.cc/minecraft";
        player.sendTitle(title, subtitle, 10, 100, 15);

        String message = Message.info("Welcome to {nodev}, {player}", title, player);
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
