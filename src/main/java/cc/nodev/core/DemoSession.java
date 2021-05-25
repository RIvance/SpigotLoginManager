package cc.nodev.core;

import lombok.Data;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoSession extends ConfigFileSession {

    final List<String> opList = Arrays.asList(
        "Ivance", "AnnaGream"
    );

    final Map<Player, PlayerData> playerMap = new HashMap<>();

    @Data
    static final class PlayerData {
        private Location location;
        private GameMode gamemode;
    }

    @Override
    public boolean checkPin(Player player, int pin) {
        return true;
    }

    @Override
    public GameMode getGameMode(Player player) {
        return playerMap.get(player).gamemode;
    }

    @Override
    public Location getLastLocation(Player player) {
        return playerMap.get(player).location;
    }

    @Override
    public void updateLastLocation(Player player) {
        if (!playerMap.containsKey(player)) {
            playerMap.put(player, new PlayerData());
        }
        playerMap.get(player).location = player.getLocation();
    }

    @Override
    public void updateGameMode(Player player) {
        if (!playerMap.containsKey(player)) {
            playerMap.put(player, new PlayerData());
        }
        playerMap.get(player).gamemode = player.getGameMode();
    }

    @Override
    public boolean isOp(Player player) {
        return opList.contains(player.getName());
    }
}
