package cc.nodev.core;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DataBaseSession implements PlayerDataSession {

    @Override
    public void setOp(Player player, boolean isOp) {

    }

    @Override
    public void updateLastLocation(Player player) {

    }

    @Override
    public void updateSpawnLocation(Player player) {

    }

    @Override
    public void updateGameMode(Player player) {

    }

    @Override
    public boolean isNew(Player player) {
        return false;
    }

    @Override
    public boolean isOp(Player player) {
        return false;
    }

    @Override
    public GameMode getGameMode(Player player) {
        return null;
    }

    @Override
    public Location getLastLocation(Player player) {
        return null;
    }

    @Override
    public Location getSpawnLocation(Player player) {
        return null;
    }

    @Override
    public void newPlayer(Player player, int pin) {

    }

    @Override
    public boolean checkPin(Player player, int pin) {
        return false;
    }
}
