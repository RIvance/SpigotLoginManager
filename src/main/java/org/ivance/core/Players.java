package org.ivance.core;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public enum Players {
    INSTANCE;

    private final PlayerDataSession dataSession = new ConfigFileSession();
    private final Map<Player, Boolean> loginCondition = new HashMap<>();

    public static void restorePlayerState(Player player) {
        player.setGameMode(INSTANCE.dataSession.getGameMode(player));
        player.teleport(INSTANCE.dataSession.getLastLocation(player));
        player.setBedSpawnLocation(INSTANCE.dataSession.getSpawnLocation(player));
    }

    public static void savePlayerState(Player player) {
        INSTANCE.dataSession.updateGameMode(player);
        INSTANCE.dataSession.updateLastLocation(player);
        INSTANCE.dataSession.updateSpawnLocation(player);
    }

    public static boolean isNewPlayer(Player player) {
        return INSTANCE.dataSession.isNew(player);
    }

    public static void setOp(Player player) {
        INSTANCE.dataSession.setOp(player, true);
    }

    public static void removeOp(Player player) {
        INSTANCE.dataSession.setOp(player, false);
    }

    public static boolean isOp(Player player) {
        return INSTANCE.dataSession.isOp(player);
    }

    public static void register(Player player, int pin) {
        INSTANCE.dataSession.newPlayer(player, pin);
    }

    public static boolean checkPin(Player player, int pin) {
        return INSTANCE.dataSession.checkPin(player, pin);
    }

    public static void login(Player player) {
        INSTANCE.loginCondition.put(player, true);
    }

    public static void logout(Player player) {
        INSTANCE.loginCondition.put(player, false);
    }

    public static boolean isPlayerLoggedIn(Player player) {
        if (!INSTANCE.loginCondition.containsKey(player)) {
            INSTANCE.loginCondition.put(player, false);
            return false;
        }
        return INSTANCE.loginCondition.get(player);
    }

    public static void saveData(Player player) {
        try {
            INSTANCE.dataSession.trySaveData(player);
        }
        catch (Exception exception) {
            System.err.println("Cannot save data of player " + player.getName());
        }
    }
}
