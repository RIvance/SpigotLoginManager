package cc.nodev.core;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;

@SuppressWarnings("unused")
public interface PlayerDataSession {
    void setOp(Player player, boolean isOp);
    void updateLastLocation(Player player);
    void updateSpawnLocation(Player player);
    void updateGameMode(Player player);
    boolean isNew(Player player);
    boolean isOp(Player player);
    GameMode getGameMode(Player player);
    Location getLastLocation(Player player);
    Location getSpawnLocation(Player player);
    void newPlayer(Player player, int pin);
    boolean checkPin(Player player, int pin);
    default void playerLeave(Player player) { }
    default void trySaveData(Player player) throws IOException { }
}
