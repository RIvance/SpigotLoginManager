package cc.nodev.event;

import cc.nodev.core.Players;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Players.updateSession(player);
        player.setOp(false);
        player.setGameMode(GameMode.SPECTATOR);

        String title = ChatColor.AQUA + "Nodev" + ChatColor.WHITE + "::" + ChatColor.GOLD + "Craft";
        String subtitle = ChatColor.GREEN + "https://nodev.cc/minecraft";
        player.sendTitle(title, subtitle, 10, 100, 10);
    }
}
